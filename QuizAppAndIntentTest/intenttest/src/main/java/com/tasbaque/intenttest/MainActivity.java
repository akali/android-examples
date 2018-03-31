package com.tasbaque.intenttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText firstNameEditText, secondNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        firstNameEditText = findViewById(R.id.firstNameEditText);
        secondNameEditText = findViewById(R.id.secondNameEditText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick();
            }
        });
    }

    private void onButtonClick() {
        Credentials credentials = new Credentials(String.valueOf(firstNameEditText.getText()), String.valueOf(secondNameEditText.getText()));
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        Gson gson = new Gson();
        String data = gson.toJson(credentials);

        Log.d("MainActivity", data);

        intent.putExtra("cred", data);
        startActivity(intent);
    }
}
