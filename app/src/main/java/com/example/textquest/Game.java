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
    private static int count = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        Character manager;
        Story story;
        manager = new Character("Менеджер");
        story = new Story();

        manager.position += story.current_situation.dA;
        manager.money += story.current_situation.dK;
        manager.reputation += story.current_situation.dR;

        Button oneQuestionButton = findViewById(R.id.one_question);
        Button twoeQuestionButton = findViewById(R.id.two_question);
        Button threeQuestionButton = findViewById(R.id.three_question);
        Button exitGameButton = findViewById(R.id.exit_game_button);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView TextViewHistory = findViewById(R.id.text_history);
        TextViewHistory.setText(getString(R.string.one_history));

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView TextViewSituation = findViewById(R.id.text_situation);
        TextViewSituation.setText(getString(R.string.one_situation));

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView TextViewInfo = findViewById(R.id.text_info);
        TextViewInfo.setText("Карьера: " + manager.position + " Активы: " + manager.money + " Репутация: " + manager.reputation);
        Log.d(TAG, "~~~ " + "Карьера: " + manager.position + " Активы: " + manager.money + " Репутация: " + manager.reputation + " ~~~");

        oneQuestionButton.setOnClickListener(view -> {
            Log.d(TAG, "~~~ Pressing the button 'one_question' ~~~");

            story.go(1);
            manager.position += story.current_situation.dA;
            manager.money += story.current_situation.dK;
            manager.reputation += story.current_situation.dR;

            Log.e(TAG, story.current_situation.text);
            TextViewHistory.setText(story.current_situation.text);
            TextViewInfo.setText("Карьера: " + manager.position + " Активы: " + manager.money + " Репутация: " + manager.reputation);
        });

        twoeQuestionButton.setOnClickListener(view -> {
            Log.d(TAG, "~~~ Pressing the button 'two_question' ~~~");

            story.go(2);
            manager.position += story.current_situation.dA;
            manager.money += story.current_situation.dK;
            manager.reputation += story.current_situation.dR;

            Log.e(TAG, story.current_situation.text);
            TextViewHistory.setText(story.current_situation.text);
            TextViewInfo.setText("Карьера: " + manager.position + " Активы: " + manager.money + " Репутация: " + manager.reputation);
        });

        threeQuestionButton.setOnClickListener(view -> {
            Log.d(TAG, "~~~ Pressing the button 'three_question' ~~~");

            story.go(3);
            manager.position += story.current_situation.dA;
            manager.money += story.current_situation.dK;
            manager.reputation += story.current_situation.dR;

            Log.e(TAG, story.current_situation.text);
            TextViewHistory.setText(story.current_situation.text);
            TextViewInfo.setText("Карьера: " + manager.position + " Активы: " + manager.money + " Репутация: " + manager.reputation);
        });


        exitGameButton.setOnClickListener(view -> {
            Log.d(TAG, "~~~ Pressing the button 'exit_game_button' ~~~");

            Intent intent = new Intent(Game.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        if (story.isEnd()) {
            TextViewHistory.setText("====================the end!===================");
        }

    }
}

//=======персонаж=======
class Character {
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

//=======ситуация=======
class Situation {
    Situation[] direction;
    String subject,text;
    int dK,dA,dR;
    public Situation (String subject, String text, int variants, int dk,int da,int dr) {
        this.subject=subject;
        this.text=text;
        dK=dk;
        dA=da;
        dR=dr;
        direction=new Situation[variants];
    }
}

// =======история=======
class Story {
    private Situation start_story;
    public Situation current_situation;
    Story() {
        start_story = new Situation(
                "первая сделка (Windows)",
                "Только вы начали работать и тут же удача! Вы нашли клиента и продаете ему "
                        + "партию ПО MS Windows. Ему в принципе достаточно взять 100 коробок версии HOME.\n"
                        + "(1)у клиента денег много, а у меня нет - вы выпишете ему счет на 120 коробок ULTIMATE по 50тр\n"
                        + "(2)чуть дороже сделаем, кто там заметит - вы выпишете ему счет на 100 коробок PRO по 10тр\n"
                        + "(3)как надо так и сделаем - вы выпишете ему счет на 100 коробок HOME по 5тр ",
                3, 0, 0, 0);
        start_story.direction[0] = new Situation(
                "корпоратив",
                "Неудачное начало, ну что ж, сегодня в конторе корпоратив! "
                        + "Познакомлюсь с коллегами, людей так сказать посмотрю, себя покажу",
                0, 0, -10, -10);
        start_story.direction[1] = new Situation(
                "совещание, босс доволен",
                "Сегодня будет совещание, меня неожиданно вызвали,"
                        + "есть сведения что \n босс доволен сегодняшней сделкой.",
                0, 1, 100, 0);
        start_story.direction[2] = new Situation(
                "мой первый лояльный клиент",
                "Мой первый клиент доволен скоростью и качеством "
                        + "моей работы. Сейчас мне звонил лично \nдиректор компании,  сообщил что скоро состоится еще более крупная сделка"
                        + " и он хотел, чтобы по ней работал именно я!", 0, 0,
                50, 1);
        current_situation = start_story;
    }
    public void go(int num) {
        if (num <= current_situation.direction.length)
            current_situation = current_situation.direction[num - 1];
        else
            System.out.println("Вы можете выбирать из "
                    + current_situation.direction.length + " вариантов");
    }
    public boolean isEnd() {
        return current_situation.direction.length == 0;
    }
}

//=======игра=======
class Games {
    public static Character manager;
    public static Story story;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Вы прошли собеседование и вот-вот станете сотрудником Корпорации. \n "
                + "Осталось уладить формальности - подпись под договором (Введите ваше имя):");
        manager = new Character(in.next());
        story = new Story();
        while (true) {
            manager.money += story.current_situation.dA;
            manager.position += story.current_situation.dK;
            manager.reputation += story.current_situation.dR;
            System.out.println("=====\nКарьера:" + manager.position + "\tАктивы:"
                    + manager.money + "\tРепутация:" + manager.reputation + "\n=====");
            System.out.println("============="
                    + story.current_situation.subject + "==============");
            System.out.println(story.current_situation.text);
            if (story.isEnd()) {
                System.out
                        .println("====================the end!===================");
                return;
            }
            story.go(in.nextInt());
        }
    }
}
