package kz.tasbaque.fragmentstest.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kz.tasbaque.fragmentstest.R;
import kz.tasbaque.fragmentstest.UserAdapter;
import kz.tasbaque.fragmentstest.interfaces.OnUserClickListener;
import kz.tasbaque.fragmentstest.model.User;


public class RListFragment extends Fragment {
  private static final String TAG = RListFragment.class.getSimpleName();
  private List<User> users;
  private UserAdapter userAdapter;
  private OnUserClickListener onUserClickListener;

  public RListFragment() {
  }

  public static RListFragment newInstance(
    List<User> users,
    OnUserClickListener onUserClickListener) {

    RListFragment fragment = new RListFragment();
    fragment.setList(users);
    fragment.onUserClickListener = onUserClickListener;
    Log.d(TAG, "users: " + users);
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState) {

    View currentView = inflater.inflate(R.layout.fragment_rlist, container, false);

    Log.d(TAG, "Creating View");

    RecyclerView rv = currentView.findViewById(R.id.recyclerView);
    rv.setLayoutManager(new LinearLayoutManager(getContext()));

    userAdapter = new UserAdapter(users, onUserClickListener);
    rv.setAdapter(userAdapter);

    return currentView;
  }

  public void setList(List<User> list) {
    this.users = list;
  }
}
