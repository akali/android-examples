package com.example.aqali.findgooglebook;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BooksAdapter extends BaseAdapter {

    Context context;
    JSONArray books;
    LayoutInflater inflater;
    ImageLoader mImageLoader;
    RequestQueue mRequestQueue;

	public BooksAdapter(Context context, JSONArray books) {
        this.context = context;
        this.books = books;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRequestQueue = Volley.newRequestQueue(context);
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
    }

    @Override
    public int getCount() {
        return books.length();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_book_item, null);
            viewHolder = new ViewHolder();
            viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
            viewHolder.subtitleTextView = (TextView) convertView.findViewById(R.id.subtitleTextView);
            viewHolder.authorTextView = (TextView) convertView.findViewById(R.id.authorTextView);

            viewHolder.coverImageView = (NetworkImageView) convertView.findViewById(R.id.coverImageView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position >= books.length() - 1) {
	        updateData(books.length());
        }

	    try {
            JSONObject book = books.getJSONObject(position);
            JSONObject volumeInfo = book.getJSONObject("volumeInfo");

            String title = volumeInfo.getString("title");
            String printType = volumeInfo.getString("printType");
	        Log.d("BooksAdapter printType", printType);
	        if (!printType.equals("MAGAZINE")) {
		        String subtitle = volumeInfo.getString("subtitle");
		        JSONArray authors = volumeInfo.getJSONArray("authors");

		        String author = "";
		        for (int i = 0; i < authors.length() - 1; ++i) {
			        author += authors.getString(i) + ", ";
		        }
		        author += authors.getString(authors.length() - 1);
		        viewHolder.authorTextView.setText(author);
		        viewHolder.subtitleTextView.setText(subtitle);
	        } else {
		        viewHolder.authorTextView.setText("");
	            viewHolder.subtitleTextView.setText("");
	        }

            viewHolder.titleTextView.setText(title);

            JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
            String thumbnailUrl = imageLinks.getString("thumbnail");

            viewHolder.coverImageView.setImageUrl(thumbnailUrl, mImageLoader);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }

	private void updateData(int startIndex) {
		String bookName = QueryDataHolder.query;
		String printType = QueryDataHolder.printType;
		String orderBy = QueryDataHolder.orderBy;
		String url = "https://www.googleapis.com/books/v1/volumes?key=AIzaSyB5JdIhiqiuzgaXWRburE4uKmv21RjJc_Y&q="
				     +bookName.replace(" ", "%20")+"&printType="
				     +printType+"&orderBy="+orderBy
					 +"&startIndex="+startIndex;
		JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
//					for (JSONObject item : response.getJSONArray("items")) {
//						books.put(response.getJSONArray("items"));
//					}
					books = response.getJSONArray("items");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("BooksAdapter", "" + error.getMessage());
			}
		});
		mRequestQueue.add(request);

	}

	private class ViewHolder {
        TextView titleTextView, subtitleTextView, authorTextView;
        NetworkImageView coverImageView;
    }
}
