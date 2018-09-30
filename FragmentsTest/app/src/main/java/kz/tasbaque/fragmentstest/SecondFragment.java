package kz.tasbaque.fragmentstest;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;


public class SecondFragment extends Fragment {
  private View currentView;
  private Button clickHereButton;

  public SecondFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    currentView = inflater.inflate(R.layout.fragment_second, container, false);
    clickHereButton = currentView.findViewById(R.id.clickHereButton);
    clickHereButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        changeBackground();
      }
    });
    return currentView;
  }

  private void changeBackground() {
    Random rnd = new Random();
    int color =
      Color.argb(
        rnd.nextInt(256),
        rnd.nextInt(256),
        rnd.nextInt(256),
        rnd.nextInt(256));
    currentView.setBackgroundColor(color);
  }
}
