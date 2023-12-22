package com.example.textquest;


import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Button;

import android.widget.ImageView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStartTheGame = findViewById(R.id.start_the_game);

        buttonStartTheGame.setOnClickListener(view -> {
            Log.d(TAG, "~~~ Pressing the button 'start_the_game' ~~~");

            startActivity(new Intent(MainActivity.this, Game.class));
        });

        ImageView buttonOpenGitHub = findViewById(R.id.open_github);

        buttonOpenGitHub.setOnClickListener(view -> {
            Log.d(TAG, "~~~ Pressing the button 'open_github' ~~~");

            startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://github.com/LebedevSergeyVach")));
        });
    }
}
