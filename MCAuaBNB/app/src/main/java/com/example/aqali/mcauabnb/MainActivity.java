package com.example.aqali.mcauabnb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "MainActivity";
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Backendless.initApp(this, BackendlessConstData.APP_ID, BackendlessConstData.ANDROID_KEY, BackendlessConstData.VERSION);

		listView = (ListView) findViewById(R.id.listView);

		downloadCities();
	}

	private void downloadCities() {
		Backendless.Persistence.of(City.class).find(new AsyncCallback<BackendlessCollection<City>>() {
			@Override
			public void handleResponse(BackendlessCollection<City> response) {
				displayCities(response.getData());
			}

			@Override
			public void handleFault(BackendlessFault fault) {
				Log.e(TAG, "Failed to download: " + fault.getMessage());
			}
		});
	}

	private void displayCities(List<City> data) {
		CitiesAdapter adapter = new CitiesAdapter(this, (ArrayList<City>) data);
		listView.setAdapter(adapter);
	}
}
