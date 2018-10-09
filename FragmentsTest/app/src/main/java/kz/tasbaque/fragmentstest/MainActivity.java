package kz.tasbaque.fragmentstest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    FragmentManager fm = getSupportFragmentManager();

    Fragment fragment = new SearchFragment();

    fm.beginTransaction()
      .add(
        R.id.container,
        fragment)
      .commit();
  }
}
