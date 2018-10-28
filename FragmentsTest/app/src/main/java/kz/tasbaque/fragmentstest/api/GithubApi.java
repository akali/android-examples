package kz.tasbaque.fragmentstest.api;

import java.util.List;

import kz.tasbaque.fragmentstest.model.Repository;
import kz.tasbaque.fragmentstest.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GithubApi {
  @GET("/users/{username}")
  Call<User> getUser(@Path("username") String username);

  @GET("/users/{username}/followers")
  Call<List<User>> getFollowers(@Path("username") String username);

  @GET("/users/{username}/following")
  Call<List<User>> getFollowing(@Path("username") String username);

  @GET("/users/{username}/starred")
  Call<List<Repository>> getStarredRepos(@Path("username") String username);

  @GET("/users/{username}/subscriptions")
  Call<List<Repository>> getSubsctiptions(@Path("username") String username);

  @GET("/users/{username}/repos")
  Call<List<Repository>> getRepos(@Path("username") String username);

  @POST("/user/repos")
  Call<Repository> createRepo(@Body CreateRepoData body);

  @PATCH("/repos/{owner}/{repo}")
  Call<Repository> editRepo(
    @Path("owner") String owner,
    @Path("repo") String repo,
    @Body CreateRepoData body);
}
