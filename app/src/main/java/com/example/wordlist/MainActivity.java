package com.example.wordlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static String LIST = "LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void reviewWords(int listNumber) {
        Intent intent = new Intent(this, ReviewWordsActivity.class);
        intent.putExtra(LIST, listNumber);
        if (listNumber < 1) {
            startActivity(intent);
        }
    }

    public void listDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose a list:");
        String[] options = {"List 1", "List 2", "List 3", "List 4", "List 5"};
        builder.setItems(options, (dialog, which) -> {
            reviewWords(which);
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}