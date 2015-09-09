public class Dialog extends Page {
  private boolean enabled; 
  private String[] stringArr;

  public Dialog(boolean b, String[] s) {
    enabled = b;
    stringArr = s;
  }

  public void runDialog(boolean b, String[] s, float w, float h) {
    if (b) {
      fill(200, 220);
      rect(width/2, height/2, w, h);

      for (int i = 0; i < s.length; i++) {
        Text text = new Text(s[i], 20, 0, 0, width/2, height/2 - 150+(75*i));
        text.buildText(width/2, 200);
      }

      Button backButton = new Button(width/2, height/2 + h/4, 200, 50, 200, "Back", 25);
      super.buttonsArr = new ArrayList<Button>(1);
      super.buttonsArr.add(backButton);
      super.runPage();
    }
  }

  public void setEnabled(boolean b) {
    enabled = b;
  }

  public boolean getEnabled() {
    return enabled;
  }

  public String[] getStringArr() {
    return stringArr;
  }

  public void setStringArr(String[] s) {
    stringArr = s;
  }
}
