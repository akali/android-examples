package kz.tasbaque.fragmentstest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import kz.tasbaque.fragmentstest.App;
import kz.tasbaque.fragmentstest.R;
import kz.tasbaque.fragmentstest.interfaces.OnUserClickListener;
import kz.tasbaque.fragmentstest.model.User;

public class SearchFragment extends Fragment {
  private static final String TAG = SearchFragment.class.getSimpleName();
  private View.OnClickListener onSearch = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      onSearch(String.valueOf(searchEditText.getText()));
    }
  };

  private void onSearch(final String username) {
    App.getGithubApi().getUser(username).enqueue(new retrofit2.Callback<User>() {
      @Override
      public void onResponse(retrofit2.Call<User> call, retrofit2.Response<User> response) {
        User user = response.body();
        FragmentManager fm = getFragmentManager();
        if (fm != null) {
          fm.beginTransaction()
            .replace(R.id.searchContainer, InfoFragment.newInstance(user, new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                replaceFragment(username);
              }
            }, new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                showReposFragment(username);
              }
            }))
            .commit();
        }
      }

      @Override
      public void onFailure(retrofit2.Call<User> call, Throwable t) {

      }
    });
  }

  private void showReposFragment(String username) {

  }

  private void replaceFragment(String username) {
    App.getGithubApi().getFollowers(username).enqueue(new retrofit2.Callback<List<User>>() {
      @Override
      public void onResponse(retrofit2.Call<List<User>> call, retrofit2.Response<List<User>> response) {
        List<User> followers = response.body();

        FragmentManager fm = getFragmentManager();

        if (fm != null) {
          fm.beginTransaction()
            .replace(R.id.searchContainer,
              RListFragment.newInstance(
                followers, new OnUserClickListener() {
                  @Override
                  public void onClick(User user) {
                    onSearch(user.getLogin());
                  }
                }))
            .commit();
        }
      }

      @Override
      public void onFailure(retrofit2.Call<List<User>> call, Throwable t) {
        Toast.makeText(getContext(), t.getMessage() + "", Toast.LENGTH_SHORT).show();
      }
    });
  }

  public SearchFragment() {
  }

  public static SearchFragment newInstance() {
    SearchFragment fragment = new SearchFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  private EditText searchEditText;
  private Button searchButton;

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState) {

    View currentView =
      inflater.inflate(R.layout.fragment_search, container, false);

    searchEditText = currentView.findViewById(R.id.searchEditText);
    searchButton = currentView.findViewById(R.id.searchButton);

    searchButton.setOnClickListener(onSearch);

    return currentView;
  }

  @Override
  public void onDetach() {
    super.onDetach();
  }
}
