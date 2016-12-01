package com.example.aqali.database;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

	private ArrayList<String> list;
	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		Gson gson = new Gson();
		list = gson.fromJson(getIntent().getStringExtra("target"), ArrayList.class);
		listView = (ListView) findViewById(R.id.listView);

		MyAdapter adapter = new MyAdapter(this, list);
		listView.setAdapter(adapter);

	}
}
