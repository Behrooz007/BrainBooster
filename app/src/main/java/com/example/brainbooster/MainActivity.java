package com.example.brainbooster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int locatonOfCorrectAnswer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView sumTextView;
    Button goButton;
    TextView resultTextView;
    int score = 0;
    int numberOfQuastion = 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;

    public void playAgain(View view){
        score = 0;
        numberOfQuastion = 0;
        timerTextView.setText("30s");
        playAgainButton.setVisibility(View.INVISIBLE);
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuastion));
        resultTextView.setText(" ");
        newQuestion();
        new CountDownTimer(30000,1000){


            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000 + "s"));
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);

            }
        }.start();

    }

    public void start(View view) {
        gameLayout.setVisibility(View.VISIBLE);
        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }
    public void chooseAnswer(View view){

        if(Integer.toString(locatonOfCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct!");
            score++;

        }else{
            resultTextView.setText(" Wrong :( ");
        }
        numberOfQuastion++;

        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuastion));
        newQuestion();
    }

    public void newQuestion(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        locatonOfCorrectAnswer = random.nextInt(4);
        answers.clear();

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        for(int i = 0 ; i<4 ; i++){

            if( i == locatonOfCorrectAnswer  ){
                answers.add(a+b);
            }else{
                int wrongAnswer = random.nextInt(41);

                while(wrongAnswer == a+b){
                    wrongAnswer = random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);

        scoreTextView = findViewById(R.id.scoreTextView);
        resultTextView = findViewById(R.id.resultTextView);
        goButton = findViewById(R.id.goButton);
        sumTextView = findViewById(R.id.sumTextView);
        gameLayout.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);


    }
}