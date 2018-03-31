package tech.afalse.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int RESULT_REQ_CODE = 103;
    private Button trueButton, falseButton, nextButton;
    private TextView questionTextView;
    private ArrayList<Question> questions = Singleton.getInstance().questions;
    private int cur = 0;
    private int correctCnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);
        nextButton = findViewById(R.id.nextButton);
        questionTextView = findViewById(R.id.questionTextView);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkState(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkState(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextButtonClick();
            }
        });
    }

    private void checkState(boolean result) {
        if (result == questions.get(cur).isAnswer()) {
            ++correctCnt;
        }
        trueButton.setClickable(false);
        falseButton.setClickable(false);
    }

    private void onNextButtonClick() {
        ++cur;
        if (cur < questions.size()) {
            trueButton.setClickable(true);
            falseButton.setClickable(true);
            questionTextView.setText(questions.get(cur).getText());
        } else {
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("count", questions.size());
            intent.putExtra("correct", correctCnt);
            startActivityForResult(intent, RESULT_REQ_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_REQ_CODE) {
            finish();
        }
    }

    private void showText(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
