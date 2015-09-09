public class Dot extends Shape {
  Dot (float x, float y, float w, float h, float sw, color s, color c) {
    super(x, y, w, h, sw, s, c);
  }

  public void constructShape() {
    super.constructShape();
    for (int i = 0; i < getQuantity () * 25; i++) {
      super.constructShape();
      float w = canvasPage.configureShapeSizes(canvasPage.getCurrentShapeSizesButton(), width);
      float h = canvasPage.configureShapeSizes(canvasPage.getCurrentShapeSizesButton(), height);
      setRandomWidth((w/2)/100, h/100);
      setRandomHeight((h/2)/100, w/100);
      ellipse (super.xPos, super.yPos, super.shapeWidth, super.shapeHeight);
    }
  }
}
