package com.example.aqali.wordnik;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class WordFragment extends Fragment {
	private static final String ARG_WORD = "word";
	private static final String TAG = "WordFragment";

	private String word;
	private String wordDefinition;

	public WordFragment() {
		// Required empty public constructor
	}

	public static WordFragment newInstance(String param1) {
		WordFragment fragment = new WordFragment();
		Bundle args = new Bundle();
		args.putString(ARG_WORD, param1);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			word = getArguments().getString(ARG_WORD);
//			getDefinition(word);
//			Log.e(TAG, "Definition -> " + wordDefinition);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_word, container, false);
		TextView wordTextView = (TextView) v.findViewById(R.id.wordTextView);
		final TextView definitionTextView = (TextView) v.findViewById(R.id.definitionTextView);
		Button lookSynonymButton = (Button) v.findViewById(R.id.lookSynonymButton);

		lookSynonymButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onLookSynonymButtonClick();
			}
		});

		wordTextView.setText(word);
		String url = "http://api.wordnik.com/v4/word.json/" + word + "/definitions?limit=200&includeRelated=true&useCanonical=false&includeTags=false&api_key=a2a73e7b926c924fad7001ca3111acd55af2ffabf50eb4ae5";
		RequestQueue queue = Volley.newRequestQueue(getActivity());
		JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray response) {
				if (response.length() > 0) {
					try {
						JSONObject jsonObject = response.getJSONObject(0);
						definitionTextView.setText(jsonObject.getString("text"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("WordFragment", error.getMessage() + "");
			}
		});
		queue.add(request);
		return v;
	}

	private void onLookSynonymButtonClick() {
		Intent intent = new Intent(getActivity(), MainActivity.class);
		intent.putExtra("word", word);
		startActivity(intent);
	}
}
