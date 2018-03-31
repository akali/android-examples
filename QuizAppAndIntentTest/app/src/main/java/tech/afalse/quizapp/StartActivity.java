package tech.afalse.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

public class StartActivity extends AppCompatActivity {

    private static final int START_QUIZ_REQ_CODE = 101;
    private static final int ADD_REQ_CODE = 102;
    private Button startQuizButton, addQuestionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startQuizButton = findViewById(R.id.startQuizButton);
        addQuestionButton = findViewById(R.id.addQuestionButton);

        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartButtonClick();
            }
        });
        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClick();
            }
        });
    }

    private void onAddButtonClick() {
        Intent intent = new Intent(StartActivity.this, AddQuestionActivity.class);
        startActivityForResult(intent, ADD_REQ_CODE);
    }

    private void onStartButtonClick() {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        startActivityForResult(intent, START_QUIZ_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch(requestCode) {
                case START_QUIZ_REQ_CODE:

                    break;
                case ADD_REQ_CODE:
                    String questionJson = data.getStringExtra("question");
                    Question question = (new Gson()).fromJson(questionJson, Question.class);
                    Singleton.getInstance().questions.add(question);
                    break;
            }
        }
    }
}
