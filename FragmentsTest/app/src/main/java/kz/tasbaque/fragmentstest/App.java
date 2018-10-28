package kz.tasbaque.fragmentstest;

import android.app.Application;
import android.content.Context;

import java.io.IOException;

import kz.tasbaque.fragmentstest.api.GithubApi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class App extends Application {

  private static GithubApi githubApi;
  private Retrofit retrofit;

  public Context getContext() {
    return getApplicationContext();
  }

  @Override
  public void onCreate() {
    super.onCreate();

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    httpClient.addInterceptor(new Interceptor() {
      @Override
      public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder().addHeader(
          "Authorization", "token 2c92e96fcfad7046114e2ef606155af908367d8b"
        ).build();
        return chain.proceed(request);
      }
    });

    retrofit = new Retrofit.Builder()
      .baseUrl("https://api.github.com")
      .client(httpClient.build())
      .addConverterFactory(JacksonConverterFactory.create())
      .build();

    githubApi = retrofit.create(GithubApi.class);
  }

  public App() {

  }


  public static GithubApi getGithubApi() {
    return githubApi;
  }

  public void create() {
    onCreate();
  }
}
