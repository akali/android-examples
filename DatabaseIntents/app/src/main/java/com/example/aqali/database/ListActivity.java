package com.example.aqali.database;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
	private ArrayList<String> list;
	private ListView listView;

	private Button firstButton, secondButton, thirdButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		Gson gson = new Gson();
		String target = getIntent().getStringExtra("target");
		list = gson.fromJson(target, ArrayList.class);
		listView = (ListView) findViewById(R.id.listView);

		MyAdapter adapter = new MyAdapter(this, list);
		listView.setAdapter(adapter);

		firstButton = (Button) findViewById(R.id.FirstButton);
		secondButton = (Button) findViewById(R.id.SecondButton);
		thirdButton = (Button) findViewById(R.id.ThirdButton);

		View.OnClickListener listener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (view.getId() == R.id.FirstButton) {
					finishIt(1);
				}
				if (view.getId() == R.id.SecondButton) {
					finishIt(2);
				}
				if (view.getId() == R.id.ThirdButton) {
					finishIt(3);
				}
			}
		};

		firstButton.setOnClickListener(listener);
		secondButton.setOnClickListener(listener);
		thirdButton.setOnClickListener(listener);
	}

	private void finishIt(int i) {
		Intent intent = new Intent();
		intent.putExtra("data", i);
		setResult(RESULT_OK, intent);
		finish();
	}
}
