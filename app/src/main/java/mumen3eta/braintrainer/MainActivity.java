package mumen3eta.braintrainer;

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

    Button goButton;
    TextView question;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView feedBack;
    TextView scoreView;
    TextView timerView;
    Button playAgainButton;
    ConstraintLayout goLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int correctAnswerLocation;
    int score = 0;
    int numberOfQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = (Button) findViewById(R.id.goButten);
        question = (TextView) findViewById(R.id.question);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        feedBack = (TextView) findViewById(R.id.feedBack);
        scoreView = (TextView) findViewById(R.id.score);
        timerView = (TextView) findViewById(R.id.timer);
        playAgainButton = (Button) findViewById(R.id.playAgain);
        goLayout = (ConstraintLayout) findViewById(R.id.goLayout);

    }

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        goLayout.setVisibility(View.INVISIBLE);
        playAgain(playAgainButton);
    }

    public void chooseAnswer(View view){
        if (Integer.toString(correctAnswerLocation).equals(view.getTag().toString())){
            feedBack.setText("اجابة صحيحة :)");
            score++;
        }else {
            feedBack.setText("اجابة خاطئة ):");
        }
        numberOfQuestions++;
        scoreView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }

    public void newQuestion(){
        Random random = new Random();

        int firstNumber = random.nextInt(10);
        int secondNumber = random.nextInt(10);

        correctAnswerLocation = random.nextInt(4);

        answers.clear();

        for (int i=0; i<4; i++){
            if (i==correctAnswerLocation){
                answers.add(firstNumber*secondNumber);
            }else {
                int wrongAnswer = random.nextInt(82);
                while (wrongAnswer == firstNumber*secondNumber){
                    wrongAnswer = random.nextInt(82);
                }
                answers.add(wrongAnswer);
            }
        }

        question.setText(Integer.toString(firstNumber) + "x" + Integer.toString(secondNumber));
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

    }

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerView.setText("60s");
        scoreView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        feedBack.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        newQuestion();
        new CountDownTimer(60100, 1000){

            @Override
            public void onTick(long l) {
                timerView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                feedBack.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
            }
        }.start();
    }

}