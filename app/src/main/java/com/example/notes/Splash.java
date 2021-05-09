package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    ImageView tcklogoSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tcklogoSelected = findViewById(R.id.tcklogo2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tcklogoSelected.setVisibility(View.VISIBLE);
                tcklogoSelected.animate().alpha(1.0f).setDuration(1000);
            }
        }, 500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent toMain = new Intent(Splash.this, MainActivity.class);
                startActivity(toMain);
                finish();
            }
        }, 1700);
    }
}
