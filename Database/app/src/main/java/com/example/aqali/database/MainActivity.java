package com.example.aqali.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	private Button enterButton, listButton;
	private EditText editText;
	private ArrayList<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		enterButton = (Button) findViewById(R.id.enterButton);
		listButton = (Button) findViewById(R.id.listButton);
		editText = (EditText) findViewById(R.id.editText);

		list = new ArrayList<>();

		enterButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onEnterButtonClick();
			}
		});

		listButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onListButtonClick();
			}
		});
	}

	private void onListButtonClick() {
		Gson gson = new Gson();
		String target = gson.toJson(list);
		Intent intent = new Intent(this, ListActivity.class);
		intent.putExtra("target", target);
		startActivity(intent);
	}

	private void onEnterButtonClick() {
		String s = editText.getText().toString();
		list.add(s);
		editText.setText("");
		Toast.makeText(this, s + " have been added", Toast.LENGTH_SHORT).show();
	}
}
