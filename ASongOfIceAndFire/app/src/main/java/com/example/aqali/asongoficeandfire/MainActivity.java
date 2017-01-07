package com.example.aqali.asongoficeandfire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "MainActivity";
	private ListView listView;
	private ProgressBar progressBar;
	private RequestQueue mRequestQueue;
	private JsonRequest jsonRequest;
	private ArrayList<Book> list;
	private Gson gson = new Gson();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.listView);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);

		mRequestQueue = Volley.newRequestQueue(this);

		String url = "http://www.anapioficeandfire.com/api/books";

		jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray response) {
				onLoaded(response);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e(TAG, error.getMessage() + "");
			}
		});

		mRequestQueue.add(jsonRequest);
	}

	private void onLoaded(JSONArray response) {
		Log.e(TAG, "Loaded: " + response.toString());
		list = new ArrayList<>();
		for (int i = 0; i < response.length(); ++i) {
			try {
				JSONObject curBook = (JSONObject) response.get(i);
				Book book = gson.fromJson(curBook.toString(), Book.class);
				list.add(book);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		BooksAdapter adapter = new BooksAdapter(this, list);
		listView.setAdapter(adapter);
		progressBar.setVisibility(View.GONE);
		listView.setVisibility(View.VISIBLE);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				onListItemClick(i);
			}
		});
	}

	private void onListItemClick(int i) {
		Intent intent = new Intent(this, CharacterActivity.class);
		intent.putExtra("book", gson.toJson(list.get(i)));
		startActivity(intent);
	}
}

