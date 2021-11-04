package com.example.wordlist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReviewWordsActivity extends AppCompatActivity {

    private static class Word {
        String word;
        String meaning;
    }

    private final ArrayList<Word> words = new ArrayList<>();
    private int listNumber;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_words);
        Intent intent = getIntent();
        listNumber = intent.getIntExtra(MainActivity.LIST, 0);
        index = Index.getIndex(listNumber);
        InputStream in;
        switch (listNumber) {
            case 0:
                in = getResources().openRawResource(R.raw.list1);
                break;
//            case 1:
//                in = getResources().openRawResource(R.raw.list2);
//                break;
//            case 2:
//                in = getResources().openRawResource(R.raw.list3);
//                break;
//            case 3:
//                in = getResources().openRawResource(R.raw.list4);
//                break;
//            case 4:
//                in = getResources().openRawResource(R.raw.list5);
//                break;
            default:
                throw new IllegalStateException("Unexpected value: " + listNumber);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                Word word = new Word();
                String[] records = line.split(",");
                word.word = records[0];
                word.meaning = records[1];
                words.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        showWord(index);
    }

    @SuppressLint("SetTextI18n")
    private void showWord(int index) {
        TextView word = findViewById(R.id.word);
        word.setText(words.get(index).word);
        TextView meaning = findViewById(R.id.meaning);
        meaning.setText(words.get(index).meaning);
        TextView sum = findViewById(R.id.sum);
        sum.setText(index + 1 + " / " + words.size());
        Index.setIndex(listNumber, index);
    }

    public void nextWord(View view) {
        index = (index + 1) % words.size();
        showWord(index);
    }

    public void prevWord(View view) {
        index = (index == 0) ? words.size() - 1 : index - 1;
        showWord(index);
    }

    public void restart(View view) {
        index = 0;
        showWord(0);
    }

}