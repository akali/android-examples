package com.example.aqali.mcauabnb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";
  private ListView listView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    listView = findViewById(R.id.listView);

    downloadCities();
  }

  private void downloadCities() {
		List<City> data =
      Arrays.asList(
        new City("Almaty", "https://static-nur.akamaized.net/pogudx66n08fs478l.bb23e33d.jpg")
      );
    displayCities(data);
  }

  private void displayCities(List<City> data) {
    CitiesAdapter adapter = new CitiesAdapter(this, data, new CitiesAdapter.OnClickListener() {
      @Override
      public void onClick(City city) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("name", city.getTitle());
        intent.putExtra("url", city.getImage());
        intent.putExtra("desc", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus aliquam animi consectetur consequuntur dolor, ducimus eaque esse excepturi id illum ipsa ipsam magnam, molestias neque perferendis provident quas sapiente vero.");
        startActivity(intent);
        // Toast.makeText(MainActivity.this, city.toString(), Toast.LENGTH_SHORT).show();
      }
    });
    listView.setAdapter(adapter);
  }
}
