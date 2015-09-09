public class Text {
  private String text;
  private float size, stroke, xPos, yPos;
  private color col;

  public Text(String t, float si, float st, color c, float x, float y) {
    text = t;
    size = si;
    stroke = st;
    col = c;
    xPos = x;
    yPos = y;
  }

  public void buildText() {
    textAlign(CENTER, CENTER);
    textSize(size);
    stroke(stroke);
    fill(col);
    text(text, xPos, yPos);
  }

  public void buildText(float w, float h) {
    textAlign(CENTER, CENTER);
    textSize(size);
    stroke(stroke);
    fill(col);
    text(text, xPos, yPos, w, h);
  }
}
