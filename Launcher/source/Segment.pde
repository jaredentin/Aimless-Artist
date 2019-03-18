public class Segment extends Shape {
  Segment(float x, float y, float w, float h, float sw, color s, color c) {
    super(x, y, w, h, sw, s, c);
  }

  public void constructShape() {
    super.constructShape();
    for (int i = 0; i < getQuantity (); i++) {
      super.constructShape();

      float w = canvasPage.configureShapeSizes(canvasPage.getCurrentShapeSizesButton(), width);
      float h = canvasPage.configureShapeSizes(canvasPage.getCurrentShapeSizesButton(), height);
      setRandomXPos(0-width/2, width+width/2);
      setRandomYPos(0-height/2, height+height/2);
      setRandomWidth(0, width/2);
      setRandomHeight(0, height/2);
      line(super.xPos, super.yPos, super.xPos + random(-w, w), super.yPos + random(-h, h));
    }
  }
}
