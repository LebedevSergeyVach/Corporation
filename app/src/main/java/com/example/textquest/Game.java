package com.example.textquest;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;

import android.os.Bundle;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Scanner;


public class Game extends AppCompatActivity {
    private static final String TAG = "MyApp";

    private TextView TextViewSituation;
    private TextView TextViewHistory;
    private TextView TextViewInfo;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        Character manager;
        Story story;
        manager = new Character("Менеджер");
        story = new Story();

        Button oneQuestionButton = findViewById(R.id.one_question);
        Button twoeQuestionButton = findViewById(R.id.two_question);
        Button threeQuestionButton = findViewById(R.id.three_question);
        Button exitGameButton = findViewById(R.id.exit_game_button);

        TextViewSituation = findViewById(R.id.text_situation);
        TextViewHistory = findViewById(R.id.text_history);
        TextViewInfo = findViewById(R.id.text_info);

        TextViewSituation.setText(getString(R.string.one_situation));
        TextViewHistory.setText(getString(R.string.one_history));
        TextViewInfo = findViewById(R.id.text_info);

        TextViewInfo.setText("Карьера: " + manager.position + " Активы: " + manager.money + " Репутация: " + manager.reputation);
        Log.d(TAG, "~~~ " + "Карьера: " + manager.position + " Активы: " + manager.money + " Репутация: " + manager.reputation + " ~~~");

        oneQuestionButton.setOnClickListener(view -> {
            Log.d(TAG, "~~~ Pressing the button 'one_question' ~~~");

            story.go(1);

            manager.position += story.current_situation.dA;
            manager.money += story.current_situation.dK;
            manager.reputation += story.current_situation.dR;

            TextViewSituation.setText(story.current_situation.subject);
            Log.d(TAG, story.current_situation.subject);

            TextViewHistory.setText(story.current_situation.text);
            Log.d(TAG, story.current_situation.text);

            TextViewInfo.setText("Карьера: " + manager.position + " Активы: " + manager.money + " Репутация: " + manager.reputation);
            Log.d(TAG, "~~~ " + "Карьера: " + manager.position + " Активы: " + manager.money + " Репутация: " + manager.reputation + " ~~~");

            if (story.isEnd()) {
                TextViewInfo.setText(getString(R.string.game_over) + "\nКарьера: " + manager.position + " Активы: " + manager.money + " Репутация: " + manager.reputation);
            }
        });

        twoeQuestionButton.setOnClickListener(view -> {
            Log.d(TAG, "~~~ Pressing the button 'two_question' ~~~");

            story.go(2);

            manager.position += story.current_situation.dA;
            manager.money += story.current_situation.dK;
            manager.reputation += story.current_situation.dR;

            TextViewSituation.setText(story.current_situation.subject);
            Log.d(TAG, story.current_situation.subject);

            TextViewHistory.setText(story.current_situation.text);
            Log.d(TAG, story.current_situation.text);

            TextViewInfo.setText("Карьера: " + manager.position + " Активы: " + manager.money + " Репутация: " + manager.reputation);
            Log.d(TAG, "~~~ " + "Карьера: " + manager.position + " Активы: " + manager.money + " Репутация: " + manager.reputation + " ~~~");

            if (story.isEnd()) {
                TextViewInfo.setText(getString(R.string.game_over) + "\nКарьера: " + manager.position + " Активы: " + manager.money + " Репутация: " + manager.reputation);
            }
        });

        threeQuestionButton.setOnClickListener(view -> {
            Log.d(TAG, "~~~ Pressing the button 'three_question' ~~~");

            story.go(3);

            manager.position += story.current_situation.dA;
            manager.money += story.current_situation.dK;
            manager.reputation += story.current_situation.dR;

            TextViewSituation.setText(story.current_situation.subject);
            Log.d(TAG, story.current_situation.subject);

            TextViewHistory.setText(story.current_situation.text);
            Log.d(TAG, story.current_situation.text);

            TextViewInfo.setText("Карьера: " + manager.position + " Активы: " + manager.money + " Репутация: " + manager.reputation);
            Log.d(TAG, "~~~ " + "Карьера: " + manager.position + " Активы: " + manager.money + " Репутация: " + manager.reputation + " ~~~");

            if (story.isEnd()) {
                TextViewInfo.setText(getString(R.string.game_over) + "\nКарьера: " + manager.position + " Активы: " + manager.money + " Репутация: " + manager.reputation);
            }
        });

        exitGameButton.setOnClickListener(view -> {
            Log.d(TAG, "~~~ Pressing the button 'exit_game_button' ~~~");

            Intent intent = new Intent(Game.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    static class Character {
        public int position;
        public int money;
        public int reputation;

        public String name;

        public Character(String name) {
            position = 1;
            money = 100;
            reputation = 50;
            this.name = name;
        }
    }

    public class Situation {
        Situation[] direction;
        String subject, text;
        int dK, dA, dR;

        public Situation(String subject, String text, int variants, int dk, int da, int dr) {
            this.subject = subject;
            this.text = text;
            dK = dk;
            dA = da;
            dR = dr;
            direction = new Situation[variants];
        }
    }

    public class Story {
        private Situation start_story;
        public Situation current_situation;

        Story() {
            start_story = new Situation(
                    getString(R.string.one_situation), getString(R.string.one_history), 3, 0, 0, 0
            );
            start_story.direction[0] = new Situation(
                    getString(R.string.two_situation), getString(R.string.two_history), 0, 0, -10, -10
            );
            start_story.direction[1] = new Situation(
                    getString(R.string.three_situation), getString(R.string.three_history), 0, 1, 100, 0
            );
            start_story.direction[2] = new Situation(
                    getString(R.string.four_situation), getString(R.string.four_history), 0, 0, 50, 1
            );

            current_situation = start_story;
        }

        public void go(int num) {
            if (num <= current_situation.direction.length)
                current_situation = current_situation.direction[num - 1];
            else {
                Log.e(TAG, "++++++++++++++++++++++++++++++++++++++++++++++++");
            }
        }

        public boolean isEnd() {
            return current_situation.direction.length == 0;
        }
    }
}
