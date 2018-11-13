package kz.tasbaque.mapboxlocation;

import android.support.multidex.MultiDexApplication;

import com.mapbox.mapboxsdk.Mapbox;

public class MyApplication extends MultiDexApplication {
  @Override
  public void onCreate() {
    super.onCreate();
    Mapbox.getInstance(getApplicationContext(), getString(R.string.mapbox_access_token));
  }
}
