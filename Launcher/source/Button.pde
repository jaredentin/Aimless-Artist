public class Button
{
  private color currentColor;
  private float rectX, rectY, rectWidth, rectHeight;
  private String buttonLabel;
  private float labelSize;
  private float mouseXMin;
  private float mouseXMax;
  private float mouseYMin;
  private float mouseYMax;

  public Button(float x, float y, float w, float h, color c, String l, int size) {
    rectX = x;
    rectY = y;
    rectWidth = w;
    rectHeight = h;
    currentColor = c;
    buttonLabel = l;
    labelSize = size + rectHeight/size;
    mouseXMin = rectX - (rectWidth/2);
    mouseXMax = rectX + (rectWidth/2);
    mouseYMin = rectY - (rectHeight/2);
    mouseYMax = rectY + (rectHeight/2);
  }

  public void buildButton(color c, String s, float si) {
    stroke(0);
    strokeWeight(1);
    rectMode(CENTER);
    fill(c);
    rect(rectX, rectY, rectWidth, rectHeight);
    Text text = new Text(s, si, 0, 0, rectX, rectY);
    text.buildText();
  }

  public boolean checkMouseHover() {
    if (mouseX >= mouseXMin && mouseX <= mouseXMax && mouseY >= mouseYMin && mouseY <= mouseYMax) {
      this.setCurrentColor(0);
      return true;
    } else {
      return false;
    }
  }

  public float getRectX() {
    return rectX;
  }

  public void setRectX(float x) {
    rectX = x;
  }

  public float getRectY() {
    return rectY;
  }

  public void setRectY(float y) {
    rectY = y;
  }

  public float getRectWidth() {
    return rectWidth;
  }

  public void setRectWidth(float w) {
    rectWidth = w;
  }

  public float getRectHeight() {
    return rectHeight;
  }

  public void setRectHeight(float h) {
    rectHeight = h;
  }

  public color getCurrentColor() {
    return currentColor;
  }

  public void setCurrentColor(color c) {
    currentColor = c;
  }

  public float getMouseXMin() {
    return mouseXMin;
  }

  public float getMouseXMax() {
    return mouseXMax;
  }

  public float getMouseYMin() {
    return mouseYMin;
  }

  public float getMouseYMax() {
    return mouseYMax;
  }

  public void setMouseXMin(float x) {
    mouseXMin = x;
  }

  public void setMouseXMax(float x) {
    mouseXMax = x;
  }

  public void setMouseYMin(float y) {
    mouseYMin = y;
  }

  public void setMouseYMax(float y) {
    mouseYMax = y;
  }

  public String getButtonLabel() {
    return buttonLabel;
  }

  public void setButtonlabel(String s) {
    buttonLabel = s;
  }

  public float getLabelSize() {
    return labelSize;
  }

  public void setLabelSize(float s) {
    labelSize = s;
  }
}
