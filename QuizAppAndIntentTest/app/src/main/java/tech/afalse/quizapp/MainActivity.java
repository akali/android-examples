package tech.afalse.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // MVC - Model View Controller
    // MVC - pattern

    private Button trueButton, falseButton, nextButton;
    private TextView questionTextView;
    private ArrayList<Question> questions = new ArrayList<>();
    private int cur = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);
        nextButton = findViewById(R.id.nextButton);
        questionTextView = findViewById(R.id.questionTextView);

        questions.add(new Question("Is Astana the capital of Kazakhstan?", true));
        questions.add(new Question("Is Moscow the capital of Russian Federation?", true));
        questions.add(new Question("Is Moscow the capital of Kazakhstan?", false));

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTrueButtonClick();
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFalseButtonClick();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextButtonClick();
            }
        });
    }

    private void onNextButtonClick() {
        ++cur;
        if (cur >= 2) return;
        questionTextView.setText();
    }

    private void showText(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void onFalseButtonClick() {
        if (!questions.get(cur).isAnswer()) showText("You are right!");
        else showText("You lose!");
    }

    private void onTrueButtonClick() {
        if (questions.get(cur).isAnswer()) showText("You are right!");
        else showText("You lose!");
    }
}
