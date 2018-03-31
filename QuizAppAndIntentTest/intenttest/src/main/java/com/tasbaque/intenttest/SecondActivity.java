package com.tasbaque.intenttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class SecondActivity extends AppCompatActivity {

    Button button;
    TextView firstNameTextView, secondNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Gson gson = new Gson();
        Credentials credentials = gson.fromJson(getIntent().getStringExtra("cred"), Credentials.class);

        firstNameTextView = findViewById(R.id.firstNameTextView);
        secondNameTextView = findViewById(R.id.secondNameTextView);
        button = findViewById(R.id.button);

        firstNameTextView.setText(credentials.getFirstName());
        secondNameTextView.setText(credentials.getSecondName());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
