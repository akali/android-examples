package com.example.aqali.findgooglebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
	private Button searchButton;
	private EditText bookNameEditText;
	private ListView listView;
	public JSONArray books;
    public Spinner printTypeSpinner, orderBySpinner;
    private String[] printTypes = new String[]{"all", "books", "magazines"};
	private String[] orderByTypes = new String[]{"newest", "relevance"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        printTypeSpinner = (Spinner) findViewById(R.id.printTypeSpinner);
	    orderBySpinner = (Spinner) findViewById(R.id.orderBySpinner);
        bookNameEditText = (EditText) findViewById(R.id.bookNameEditText);
	    searchButton = (Button) findViewById(R.id.searchButton);
        listView = (ListView) findViewById(R.id.listView);

        QueryDataHolder.query = "";
	    QueryDataHolder.printType = "all";
	    QueryDataHolder.orderBy = "relevance";

	    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, printTypes);
        printTypeSpinner.setAdapter(spinnerAdapter);

        ArrayAdapter<String> orderBySpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, orderByTypes);
	    orderBySpinner.setAdapter(orderBySpinnerAdapter);

	    orderBySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			    onOrderBySelected(position);
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parent) {
			    onOrderBySelected(0);
		    }
	    });

	    printTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onPrintTypeSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                onPrintTypeSelected(0);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemClickMethod(position);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchClick();
            }
        });
    }

	private void onOrderBySelected(int position) {
		QueryDataHolder.orderBy = (orderByTypes[position]);
	}

	private void onPrintTypeSelected(int position) {
        QueryDataHolder.printType = (printTypes[position]);
    }

    private void onItemClickMethod(int position) {
        Intent intent = new Intent(this, ListItemActivity.class);
        intent.putExtra("books", books.toString());
        intent.putExtra("position", position);
        startActivity(intent);
    }

    private void onSearchClick() {
        String bookName = bookNameEditText.getText().toString();
	    QueryDataHolder.query = bookName;
	    String printType = QueryDataHolder.printType;
	    String orderBy = QueryDataHolder.orderBy;
//        String APIKey = "AIzaSyAhqGPXlYAb43fLRFhy3P9CU9lFeBgqd7A";
        String url = "https://www.googleapis.com/books/v1/volumes?key=AIzaSyB5JdIhiqiuzgaXWRburE4uKmv21RjJc_Y&q=" +bookName.replace(" ", "%20")+"&printType="+printType+"&orderBy="+orderBy;
//        String url = "https://www.googleapis.com/books/v1/volumes?q=" + bookName.replace(" ", "%20") + "inauthor:keyes&key=" + APIKey;
//	    String url = "https://www.googleapis.com/books/v1/volumes?q=" + bookName.replace(" ", "%20");
        Log.d(TAG, url);
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                try {
                    books = response.getJSONArray("items");
                    Log.d(TAG, books.toString());
	                displayBooks(books);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "" + error.getMessage());
            }
        });
        queue.add(request);
    }

    private void displayBooks(JSONArray books) {
        BooksAdapter adapter = new BooksAdapter(this, books);
        listView.setAdapter(adapter);
    }
}
