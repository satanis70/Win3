package com.example.win3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    ProgressBar progressBar;
    String nameApp;
    TextView textViewSplash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.progress_bar_splash);
        textViewSplash = findViewById(R.id.text_view_splash_screen);
        nameApp = "<font color=#ffffff>WIN</font><font color=#1AF423>SPORT2</font>";
        textViewSplash.setText(Html.fromHtml(nameApp));
        progressBar.setMax(3);
        splashTimer(progressBar);
    }

    private void splashTimer(ProgressBar progressBar) {
        CountDownTimer countDownTimer = new CountDownTimer(3000,1000) {
            int currentProgress = 0;
            @Override
            public void onTick(long l) {
                currentProgress+=1;
                progressBar.setProgress(currentProgress);
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(3);
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        countDownTimer.start();
    }
}