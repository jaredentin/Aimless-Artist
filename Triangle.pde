public class Triangle extends Shape {
  Triangle(float x, float y, float tx1, float ty1, float tx2, float ty2, float sw, color s, color c) {
    super(x, y, tx1, ty1, tx2, ty2, sw, s, c);
  }

  public void constructShape() {
    super.constructShape();
    for (int i = 0; i < getQuantity (); i++) {
      super.constructShape();
      float w = canvasPage.configureShapeSizes(canvasPage.getCurrentShapeSizesButton(), width);
      float h = canvasPage.configureShapeSizes(canvasPage.getCurrentShapeSizesButton(), height);

      setTriangleXPt1(getXPos(), getXPos() + w/2);
      setTriangleYPt1(getYPos(), getYPos() + h/2);
      setTriangleXPt2(getXPos(), getXPos() + w/2);
      setTriangleYPt2(getYPos(), getYPos() + h/2); 
      triangle(super.xPos, super.yPos, super.triX1, super.triY1, super.triX2, super.triY2);
    }
  }

  public void setTriangleXPt1(float min, float max) {
    super.triX1 = random(min, max);
  }

  public void setTriangleYPt1(float min, float max) {
    super.triY1 = random(min, max);
  }

  public void setTriangleXPt2(float min, float max) {
    super.triX2 = random(min, max);
  }

  public void setTriangleYPt2(float min, float max) {
    super.triY2 = random(min, max);
  }
}
