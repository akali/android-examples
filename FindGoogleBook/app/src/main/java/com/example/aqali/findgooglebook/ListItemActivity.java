package com.example.aqali.findgooglebook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LruCache;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListItemActivity extends AppCompatActivity {

	ImageLoader mImageLoader;
	RequestQueue mRequestQueue;
	TextView titleTextView, subtitleTextView, authorTextView, printTypeTextView;
	NetworkImageView coverImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item);

		titleTextView = (TextView) findViewById(R.id.title2TextView);
		subtitleTextView = (TextView) findViewById(R.id.subtitle2TextView);
		authorTextView = (TextView) findViewById(R.id.author2TextView);
		printTypeTextView = (TextView) findViewById(R.id.printTypeTextView);

		coverImageView = (NetworkImageView) findViewById(R.id.cover2ImageView);

		mRequestQueue = Volley.newRequestQueue(this);
		mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {

			private final LruCache<String, Bitmap> cache = new LruCache<>(20);

			@Override
			public Bitmap getBitmap(String url) {
				return cache.get(url);
			}

			@Override
			public void putBitmap(String url, Bitmap bitmap) {
				cache.put(url, bitmap);
			}
		});

		Intent intent = getIntent();
		int position = intent.getIntExtra("position", 0);
		String booksStringArray = intent.getStringExtra("books");
		try {
			JSONArray books = new JSONArray(booksStringArray);
			JSONObject book = books.getJSONObject(position);
			JSONObject volumeInfo = book.getJSONObject("volumeInfo");
			String title = volumeInfo.getString("title");
			String printType = volumeInfo.getString("printType");
			if (!printType.equals("MAGAZINE")) {
				String subtitle = volumeInfo.getString("subtitle");
				JSONArray authors = volumeInfo.getJSONArray("authors");
				String author = "";
				for (int i = 0; i < authors.length() - 1; ++i) {
					author += authors.getString(i) + ", ";
				}
				author += authors.getString(authors.length() - 1);
				authorTextView.setText(author);
				subtitleTextView.setText(subtitle);
			} else {
				authorTextView.setText("");
				subtitleTextView.setText("");
			}

			titleTextView.setText(title);
			printTypeTextView.setText(printType);

			JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
			String thumbnailUrl = imageLinks.getString("thumbnail");

			coverImageView.setImageUrl(thumbnailUrl, mImageLoader);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
