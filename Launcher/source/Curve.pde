public class Curve extends Shape {
  Curve(float x, float y, float w, float h, float b, float sw, color s, color c) {
    super(x, y, w, h, b, sw, s, c);
  }

  public void constructShape() {
    super.constructShape();
    for (int i = 0; i < getQuantity (); i++) {
      super.constructShape();
      setRandomXPos(-200, width+200);
      setRandomYPos(-200, height+200);
      setRandomWidth(0, width);
      setRandomHeight(0, height);
      setRandomBezier(0, 5);

      float a = getXPos();
      float b = getYPos();
      float c = getWidth();
      float d = getHeight();
      float e = getBezier();

      noFill();
      bezier(a, b, c, d, e*a, e*b, c, d);
    }
  }

  public float getBezier() {
    return super.bezier;
  }

  public void setRandomBezier(float min, float max) {
    super.bezier = random(min, max);
  }
}
