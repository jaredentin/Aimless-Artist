public class Page {
  private PImage bgImg;
  private color bgCol;
  private ArrayList<Button> buttonsArr;

  private Button[] layoutButtonsArr = new Button[5];
  private Button[] tintButtonsArr = new Button[4];
  private Button[] musicButtonsArr = new Button[2];
  private Button[] keyboardControlsButtonsArr = new Button[2];
  private Button[] shapeQuantitiesButtonsArr = new Button[3];
  private Button[] shapeSizesButtonsArr = new Button[3];

  public Page() {
  }

  public Page(PImage bg) {
    bgImg = bg;
  }

  public Page(color c) {
    bgCol = c;
  }

  public void runPage() {
    if (getBackgroundImage() != null) {
      setBackgroundImage(bgImg);
    }
    currentPage = this;

    for (int i = 0; i < getButtonsArr ().size (); i++) {
      getButtonsArr().get(i).buildButton(getButtonsArr().get(i).getCurrentColor(), getButtonsArr().get(i).getButtonLabel(), getButtonsArr().get(i).getLabelSize());
    }
  }

  public void checkButtons(Page p, Button[] arr) {
    for (int i=0; i < arr.length; i++) {
      if (arr[i].checkMouseHover()) {
        if (mousePressed) {
          arr[i].setCurrentColor(100);
          arr[i].buildButton(arr[i].getCurrentColor(), arr[i].getButtonLabel(), arr[i].getLabelSize());
        } else {
          arr[i].setCurrentColor(255);
          arr[i].buildButton(arr[i].getCurrentColor(), arr[i].getButtonLabel(), arr[i].getLabelSize());
        }
      } else {
        arr[i].setCurrentColor(200);
        arr[i].buildButton(arr[i].getCurrentColor(), arr[i].getButtonLabel(), arr[i].getLabelSize());
      }
    }
  }

  public ArrayList<Button> getButtonsArr() {
    return buttonsArr;
  }

  public Page getPage() {
    return this;
  }

  public PImage getBackgroundImage() {
    return bgImg;
  }

  public void setBackgroundImage(PImage bg) {
    bg.resize(width, height);
    background(bg);
  }

  public color getBackgroundColor() { 
    return bgCol;
  }

  public void setBackgroundColor(color c) {
    bgCol = c;
  }
}
