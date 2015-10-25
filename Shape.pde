public class Shape {
  private float xPos, yPos, shapeWidth, shapeHeight, bezier, triX1, triY1, triX2, triY2, strokeW, quantity;
  private color shapeCol, stroke, col1, col2, col3;

  public Shape(float x, float y, float w, float h, float sw, color s, color c) {
    xPos = x;
    yPos = y;
    shapeWidth = w;
    shapeHeight = h;
    strokeW = sw;
    stroke = s;
    shapeCol = c;
  }

  public Shape(float x, float y, float w, float h, float b, float sw, color s, color c) {
    xPos = x;
    yPos = y;
    shapeWidth = w;
    shapeHeight = h;
    bezier = b;
    strokeW = sw;
    stroke = s;
    shapeCol = c;
  }

  public Shape(float x, float y, float tx1, float ty1, float tx2, float ty2, float sw, color s, color c) {
    xPos = x;
    yPos = y;
    triX1 = tx1;
    triY1 = ty1;
    triX2 = tx2;
    triY2 = ty2;
    strokeW = sw;
    stroke = s;
    shapeCol = c;
  }

  public void constructShape() {
    float q = canvasPage.configureShapeQuantities(canvasPage.getCurrentShapeQuantitiesButton());
    float w = canvasPage.configureShapeSizes(canvasPage.getCurrentShapeSizesButton(), width);
    float h = canvasPage.configureShapeSizes(canvasPage.getCurrentShapeSizesButton(), height);

    setRandomXPos(-200, width + 200);
    setRandomYPos(-200, height + 200);
    setRandomWidth(0, w/2);
    setRandomHeight(0, h/2);
    setRandomStrokeWeight(0, 10);
    setRandomStroke(0, 0, 0, 255, 255, 255);
    setRandomFill(0, 0, 0, 255, 255, 255);
    setRandomQuantity(q/2, q);

    fill(shapeCol);
    stroke(stroke);
    strokeWeight(strokeW);
  }

  public void setRandomXPos(float min, float max) {
    xPos = random(min, max);
  }

  public void setRandomYPos(float min, float max) {
    yPos = random(min, max);
  }

  public void setRandomWidth(float min, float max) {
    shapeWidth = random(min, max);
  }

  public void setRandomHeight(float min, float max) {
    shapeHeight = random(min, max);
  }

  public void setRandomStrokeWeight(float min, float max) {
    strokeW = random(min, max);
  }

  public void setRandomStroke(float minR, float minG, float minB, float maxR, float maxG, float maxB) {
    stroke = color(random(minR, maxR), random(minG, maxG), random(minB, maxB));
  }

  public void setRandomFill(float minR, float minG, float minB, float maxR, float maxG, float maxB) {
    shapeCol = color(random(minR, maxR), random(minG, maxG), random(minB, maxB));
  }

  public void setRandomQuantity(float min, float max) {
    quantity =random(min, max);
  }

  public float getXPos() {
    return xPos;
  }

  public float getYPos() {
    return yPos;
  }

  public float getWidth() {
    return shapeWidth;
  }

  public float getHeight() {
    return shapeHeight;
  }

  public float getStrokeW() {
    return strokeW;
  }

  public float getQuantity() {
    return quantity;
  }

  public color getStroke() {
    return stroke;
  }

  public color getShapeColor() {
    return shapeCol;
  }
}
