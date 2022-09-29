package com.example.win3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

public class InfoGamesActivity extends AppCompatActivity {

    String nameApp;
    TextView textViewInfoGames, description_text, text_view_info_games_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_games);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        nameApp = "<font color=#ffffff>WIN</font><font color=#1AF423>SPORT2</font>";
        textViewInfoGames = findViewById(R.id.text_view_info_games);
        textViewInfoGames.setText(Html.fromHtml(nameApp));
        description_text = findViewById(R.id.description_text);
        text_view_info_games_title = findViewById(R.id.text_view_info_games_title);
        text_view_info_games_title.setText(title);
        description_text.setText(description);
        Log.i("INFOGAMES", description);
    }
}