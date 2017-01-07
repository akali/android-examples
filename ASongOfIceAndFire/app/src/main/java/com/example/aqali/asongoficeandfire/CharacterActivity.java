package com.example.aqali.asongoficeandfire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

public class CharacterActivity extends AppCompatActivity {

	private static final String TAG = "CharacterActivity";
	private ArrayList<KCharacter> charList;
	private int cnt, e = 1;
	private Book book;

	private ListView listView;
	private ProgressBar progressBar;

	private Gson gson = new Gson();

	private CharactersAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character);

		listView = (ListView) findViewById(R.id.listView);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);

		charList = new ArrayList<>();

		book = gson.fromJson(getIntent().getStringExtra("book"), Book.class);
		RequestQueue mRequestQueue = Volley.newRequestQueue(this);
		for (int i = 0; i < book.getCharacters().size(); ++i) {
			String url = book.getCharacters().get(i);
			JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {
					addCharacter(response);
				}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					Log.e(TAG, String.valueOf(error.getMessage()));
				}
			});
			mRequestQueue.add(request);
		}
	}

	private void addCharacter(JSONObject response) {
		KCharacter self = gson.fromJson(response.toString(), KCharacter.class);
		charList.add(self);
		++cnt;
		if (cnt % e == 0 || cnt == book.getCharacters().size()) {
			display();
			e += e;
		}
	}

	private void display() {
		if (adapter == null) {
			adapter = new CharactersAdapter(this, charList);
			listView.setAdapter(adapter);
			progressBar.setVisibility(View.GONE);
			listView.setVisibility(View.VISIBLE);
		} else {
			listView.requestLayout();
			adapter.setList(charList);
			adapter.notifyDataSetChanged();
			listView.deferNotifyDataSetChanged();
		}
	}
}
