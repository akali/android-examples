package kz.tasbaque.lists.model;

public class ItemProfile {
  private String name;
  private String url;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public ItemProfile(String name, String url) {

    this.name = name;
    this.url = url;
  }
}
