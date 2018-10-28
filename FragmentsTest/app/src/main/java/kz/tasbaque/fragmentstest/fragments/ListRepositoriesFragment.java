package kz.tasbaque.fragmentstest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kz.tasbaque.fragmentstest.App;
import kz.tasbaque.fragmentstest.R;
import kz.tasbaque.fragmentstest.model.Repository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRepositoriesFragment extends Fragment {
  private String username;

  public ListRepositoriesFragment() {
  }

  public static ListRepositoriesFragment newInstance(String username) {
    ListRepositoriesFragment fragment = new ListRepositoriesFragment();
    fragment.setUsername(username);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view =
      inflater.inflate(R.layout.fragment_list_repositories, container, false);

    final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    App.getGithubApi().getRepos(username).enqueue(new Callback<List<Repository>>() {
      @Override
      public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
        recyclerView.setAdapter(new ReposAdapter(response.body()));
      }

      @Override
      public void onFailure(Call<List<Repository>> call, Throwable t) {

      }
    });

    return view;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
