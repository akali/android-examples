package kz.tasbaque.fragmentstest.api;

public class CreateRepoData {
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CreateRepoData(String name, String description) {
    this.name = name;
    this.description = description;
  }

  private String name;
  private String description;
}
