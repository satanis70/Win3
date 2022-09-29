package com.example.win3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String nameApp;
    TextView textViewMain;
    AppCompatButton buttonGames, buttonUpcoming, buttonPopular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMain = findViewById(R.id.text_view_main);
        nameApp = "<font color=#ffffff>WIN</font><font color=#1AF423>SPORT2</font>";
        textViewMain.setText(Html.fromHtml(nameApp));
        buttonGames = findViewById(R.id.button_games);
        buttonUpcoming = findViewById(R.id.button_upcoming_matches);
        buttonPopular = findViewById(R.id.button_popular_team);
        buttonGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GamesActivityList.class);
                startActivity(intent);
            }
        });
        buttonUpcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UpComingGameActivity.class);
                startActivity(intent);
            }
        });
        buttonPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PopularTeamActivity.class);
                startActivity(intent);
            }
        });

    }
}