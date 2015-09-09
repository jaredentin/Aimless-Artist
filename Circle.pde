public class Circle extends Shape {
  Circle(float x, float y, float w, float h, float sw, color s, color c) {
    super(x, y, w, h, sw, s, c);
  }

  public void constructShape() {
    super.constructShape();
    for (int i = 0; i < getQuantity (); i++) {
      super.constructShape();
      ellipse(super.xPos, super.yPos, super.shapeWidth, super.shapeHeight);
    }
  }
}
