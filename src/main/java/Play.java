public class Play {

  public String name;
  public playType type;

  public Play(String name, playType type) {
    this.name = name;
    this.type = type;
  }
  public enum playType {
    TRAGEDY, COMEDY
  }

}
