package kz.tasbaque.fragmentstest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
  private String login;
  private String avatar_url;
  private String bio;
  private String name;

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getAvatar_url() {
    return avatar_url;
  }

  public void setAvatar_url(String avatar_url) {
    this.avatar_url = avatar_url;
  }

  @Override
  public String toString() {
    return "User{" +
      "login='" + login + '\'' +
      ", avatar_url='" + avatar_url + '\'' +
      ", bio='" + bio + '\'' +
      ", name='" + name + '\'' +
      '}';
  }
}
