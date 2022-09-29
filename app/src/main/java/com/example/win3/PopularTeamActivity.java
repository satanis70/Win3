package com.example.win3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PopularTeamActivity extends AppCompatActivity {

    AppCompatButton buttonDota2, buttonCSGO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_team);
        buttonDota2 = findViewById(R.id.button_dota2);
        buttonCSGO = findViewById(R.id.button_csgo);
        buttonDota2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopularTeamActivity.this, InfoTeamsActivity.class);
                intent.putExtra("team", "dota2");
                startActivity(intent);
            }
        });
        buttonCSGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopularTeamActivity.this, InfoTeamsActivity.class);
                intent.putExtra("team", "csgo");
                startActivity(intent);
            }
        });
    }
}