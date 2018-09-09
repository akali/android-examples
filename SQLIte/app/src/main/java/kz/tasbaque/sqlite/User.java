package kz.tasbaque.sqlite;

public class User {
  public int id;
  public String name;
  public String surname;

  public User(int id, String name, String surname) {
    this.id = id;
    this.name = name;
    this.surname = surname;
  }

  public User(String user, String surname) {
    this.name = user;
    this.surname = surname;
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", surname='" + surname + '\'' +
      '}';
  }
}
