package tech.afalse.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

public class AddQuestionActivity extends AppCompatActivity {

    private EditText editText;
    private Button trueButton, falseButton;
    private Button okButton, cancelButton;
    private Question question = new Question();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        editText = findViewById(R.id.editText);
        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);
        okButton = findViewById(R.id.okButton);
        cancelButton = findViewById(R.id.cancelButton);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.setAnswer(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.setAnswer(false);
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOkClick();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelClick();
            }
        });
    }

    private void onCancelClick() {
        setResult(RESULT_CANCELED);
        finish();
    }

    private void onOkClick() {
        question.setText(editText.getText().toString());
        Gson gson = new Gson();
        String questionJson = gson.toJson(question);

        Intent result = new Intent();
        result.putExtra("question", questionJson);
        setResult(RESULT_OK, result);
        finish();
    }
}
