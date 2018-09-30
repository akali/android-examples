package kz.tasbaque.fragmentstest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

public class FirstFragment extends Fragment {
  private View.OnClickListener mListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      changeBackground();
    }
  };

  private View currentView;

  private void changeBackground() {
    Random rnd = new Random();
    int color =
      Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    currentView.setBackgroundColor(color);
  }

  public FirstFragment() {
    // Required empty public constructor
  }

  public static FirstFragment newInstance() {
    FirstFragment fragment = new FirstFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    currentView = inflater.inflate(R.layout.fragment_first, container, false);
    currentView.setOnClickListener(mListener);
    return currentView;
  }

  @Override
  public void onDetach() {
    super.onDetach();
  }
}
