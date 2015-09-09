public class About extends Dialog {
  public About(boolean b, String[] s) {
    super(b, s);
  }

  public void displayAbout() {
    runDialog(getEnabled(), getStringArr(), 300, height/2 - 100);
  }
}
