package com.example.aqali.wordnik;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by aqali on 16.06.2016.
 */
public class DefinitionGetter {
	public static String get(String word, Context context) {
		String url = "http://api.wordnik.com/v4/word.json/" + word + "/definitions?limit=200&includeRelated=true&useCanonical=false&includeTags=false&api_key=a2a73e7b926c924fad7001ca3111acd55af2ffabf50eb4ae5";
		RequestQueue queue = Volley.newRequestQueue(context);
		final String[] result = new String[1];
		JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray response) {
				if (response.length() > 0) {
					try {
						JSONObject jsonObject = response.getJSONObject(0);
						result[0] = jsonObject.getString("text");
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
		return result[0];
	}
}
