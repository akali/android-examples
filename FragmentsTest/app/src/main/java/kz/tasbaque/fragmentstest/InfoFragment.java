package kz.tasbaque.fragmentstest;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kz.tasbaque.fragmentstest.model.User;

public class InfoFragment extends Fragment {
  private User user;
  private View.OnClickListener onClickListener;

  public InfoFragment() {
  }

  public static InfoFragment newInstance(User user, View.OnClickListener onClickListener) {
    InfoFragment infoFragment = new InfoFragment();
    infoFragment.setUser(user);
    infoFragment.setOnClickListener(onClickListener);
    return infoFragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  private ImageView profileImageView;
  private TextView loginTextView, bioTextView, nameTextView;
  private Button showFollowersButton;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view =
      inflater.inflate(R.layout.fragment_info, container, false);

    profileImageView = view.findViewById(R.id.profileImageView);
    loginTextView = view.findViewById(R.id.loginTextView);
    bioTextView = view.findViewById(R.id.bioTextView);
    nameTextView = view.findViewById(R.id.nameTextView);
    showFollowersButton = view.findViewById(R.id.showFollowersButton);

    Glide.with(getContext())
      .load(user.getAvatar_url())
      .into(profileImageView);

    loginTextView.setText(user.getLogin());
    bioTextView.setText(user.getBio());
    nameTextView.setText(user.getName());

    showFollowersButton.setOnClickListener(onClickListener);

    return view;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setOnClickListener(View.OnClickListener onClickListener) {
    this.onClickListener = onClickListener;
  }
}
