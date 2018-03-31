package tech.afalse.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private TextView percentTextView, messageTextView;
    private Button backButton;
    private ArrayList <String> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_activty);

        int count = getIntent().getIntExtra("count", 1);
        int correctCnt = getIntent().getIntExtra("correct", 0);

        percentTextView = findViewById(R.id.percentTextView);
        messageTextView = findViewById(R.id.messageTextView);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        messages.add("Bad");
        messages.add("Not Bad");
        messages.add("OK");
        messages.add("Excellent!!!!!11!!");

        percentTextView.setText(String.valueOf(1.0 * correctCnt / count * 100));
        messageTextView.setText(messages.get((int)(1.0 * correctCnt / count * 100)/(100 / messages.size())));
    }

}
