package kz.tasbaque.fragmentstest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import kz.tasbaque.fragmentstest.model.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchFragment extends Fragment {
  private static final String TAG = SearchFragment.class.getSimpleName();
  private View.OnClickListener onSearch = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      onSearch(String.valueOf(searchEditText.getText()));
    }
  };

  private OkHttpClient client = new OkHttpClient();

  private void onSearch(final String username) {
    Request request = new Request.Builder()
      .url(String.format("https://api.github.com/users/%s", username))
//      .url(String.format("https://api.github.com/users/%s/followers", username))
      .build();


    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        Toast.makeText(getContext(), "Failed to fetch", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        ObjectMapper om = new ObjectMapper();

        User user = om.readValue(response.body().string(), User.class);

        FragmentManager fm = getFragmentManager();

        if (fm != null) {
          fm.beginTransaction()
            .replace(R.id.searchContainer, InfoFragment.newInstance(user, new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                replaceFragment(username);
              }
            }))
            .commit();
        }
      }
    });
  }

  private void replaceFragment(String username) {
    Request request = new Request.Builder()
      .url(String.format("https://api.github.com/users/%s/followers", username))
      .build();

    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        Toast.makeText(getContext(), "Failed to fetch", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        ObjectMapper om = new ObjectMapper();

        List<User> users =
          om.readValue(response.body().string(), new TypeReference<List<User>>() {});

        FragmentManager fm = getFragmentManager();

        if (fm != null) {
          fm.beginTransaction()
            .replace(R.id.searchContainer, RListFragment.newInstance(users))
            .commit();
        }
      }
    });
  }

  public SearchFragment() {
    // Required empty public constructor
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
