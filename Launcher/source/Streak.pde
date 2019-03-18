public class Streak extends Shape {
  Streak (float x, float y, float w, float h, float sw, color s, color c) {
    super(x, y, w, h, sw, s, c);
  }

  void constructShape() { 
    super.constructShape();
    for (int i = 0; i < getQuantity (); i++) {
      boolean direction = randomBool();
      boolean col1Up = true, col2Up = true, col3Up = true;
      int alpha = 255;
      float thickness = 0;

      super.col1 = (int)random(255);
      super.col2 = (int)random(255);
      super.col3 = (int)random(255);
      super.constructShape();

      if (direction) {
        setRandomWidth(5, 15);
        thickness = getHeight();
      } else {
        setRandomHeight(5, 15);
        thickness = getWidth();
      }

      noStroke();
      for (int j = 0; j < thickness; j++) {
        col1Up = inverseColorDirection(super.col1, col1Up);
        col2Up = inverseColorDirection(super.col2, col2Up);
        col3Up = inverseColorDirection(super.col3, col3Up);

        if (col1Up) {
          super.col1++;
        } else
        {
          super.col1--;
        }
        if (col2Up) {
          super.col2++;
        } else
        {
          super.col2--;
        }
        if (col3Up) {
          super.col3++;
        } else
        {
          super.col3--;
        }

        fill(super.col1, super.col2, super.col3);
        if (direction) {
          rect(getXPos(), getYPos()+j, getWidth(), getHeight()-j);
        } else {
          rect(getXPos()+j, getYPos(), getWidth()-j, getHeight());
        }
      }
    }
  }

  public boolean randomBool() {
    return random(1) > .5;
  }

  public boolean inverseColorDirection(color col, boolean b) {
    if (col >= 255 || col <+ 0) {
      b = !b;
    }
    return b;
  }
}
