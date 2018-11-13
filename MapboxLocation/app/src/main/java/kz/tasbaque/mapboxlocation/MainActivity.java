package kz.tasbaque.mapboxlocation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.maps.MapView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

  private MapView mapView;
  private PermissionsManager permissionsManager;
  private PermissionsListener permissionsListener = new PermissionsListener() {
    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {

    }

    @Override
    public void onPermissionResult(boolean granted) {
      if (granted) {

      } else {
        // User denied the permission
      }
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mapView = findViewById(R.id.mapView);
    permissionsManager = new PermissionsManager(permissionsListener);

    if (PermissionsManager.areLocationPermissionsGranted(this)) {

    } else {
      permissionsManager = new PermissionsManager(permissionsListener);
      permissionsManager.requestLocationPermissions(this);
    }


    mapView.onCreate(savedInstanceState);

    mapView.getMapAsync(mapboxMap -> {

    });
  }

  // Add the mapView lifecycle to the activity's lifecycle methods
  @Override
  public void onResume() {
    super.onResume();
    mapView.onResume();
  }

  @Override
  public void onPause() {
    super.onPause();
    mapView.onPause();
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
    mapView.onLowMemory();
  }

  @Override
  protected void onStart() {
    super.onStart();
    mapView.onStart();
  }

  @Override
  protected void onStop() {
    super.onStop();
    mapView.onStop();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mapView.onDestroy();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mapView.onSaveInstanceState(outState);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }

}
