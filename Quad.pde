public class Quad extends Shape {
  Quad(float x, float y, float w, float h, float sw, color s, color c) {
    super(x, y, w, h, sw, s, c);
  }

  public void constructShape() {
    super.constructShape();
    for (int i = 0; i < getQuantity (); i++) {
      super.constructShape();
      rect(getXPos(), super.yPos, super.shapeWidth, super.shapeHeight);
    }
  }
}
