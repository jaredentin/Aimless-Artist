public class CanvasPage extends Page {
  private Button currentLayoutButton;
  private Button currentTintButton;
  private Button currentMusicButton;
  private Button currentKeyboardControlsButton;
  private Button currentShapeQuantitiesButton;
  private Button currentShapeSizesButton;

  private Button[] currentButtonsArr = new Button[6];

  private File fileImg = null;
  private color col;

  public CanvasPage() {
  }

  public CanvasPage(PImage img) {
    super(img);
  }

  public CanvasPage(color bg) {
    super(bg);
  }

  public void runPage() {
    super.buttonsArr = new ArrayList<Button>(10);

    Button newButton = new Button(75, 25, 150, 50, 200, "New", 25);
    Button openCanvasButton = new Button(75, 75, 150, 50, 200, "Open", 25);
    Button saveButton = new Button(75, 125, 150, 50, 200, "Save", 25);
    Button aboutButton = new Button(75, 175, 150, 50, 200, "About", 25);
    Button helpButton = new Button(75, 225, 150, 50, 200, "Help", 25);
    Button backButton = new Button(75, 275, 150, 50, 200, "Back", 25);

    Button layoutBlankButton = new Button(width-175, 25, 350, 50, 200, "Layout: Blank", 25);
    Button layoutSquareButton = new Button(width-175, 25, 350, 50, 200, "Layout: Square", 25);
    Button layoutSmileButton = new Button(width-175, 25, 350, 50, 200, "Layout: Smile", 25);
    Button layoutHypnosisButton = new Button(width-175, 25, 350, 50, 200, "Layout: Hypnosis", 25);
    Button layoutStreaksButton = new Button(width-175, 25, 350, 50, 200, "Layout: Streaks", 25);

    Button tintNoneButton = new Button(width-175, 75, 350, 50, 200, "Tint: None", 25);
    Button tintRedButton = new Button(width-175, 75, 350, 50, 200, "Tint: Red", 25);
    Button tintYellowButton = new Button(width-175, 75, 350, 50, 200, "Tint: Yellow", 25);
    Button tintBlueButton = new Button(width-175, 75, 350, 50, 200, "Tint: Blue", 25);

    Button bgMusicOffButton = new Button(width-175, 125, 350, 50, 200, "Music: None", 25);
    Button bgMusicOnButton = new Button(width-175, 125, 350, 50, 200, "Music: Playing", 25);

    Button keyboardControlsDefaultButton = new Button(width-175, 175, 350, 50, 200, "Keyboard Preset: Default", 25);
    Button keyboardControlsRandomButton = new Button(width-175, 175, 350, 50, 200, "Keyboard Preset: Random", 25);

    Button shapeQuantitiesNormalButton = new Button(width-175, 225, 350, 50, 200, "Shape Quantity: Normal", 25);
    Button shapeQuantitiesManyButton = new Button(width-175, 225, 350, 50, 200, "Shape Quantity: Many", 25);
    Button shapeQuantitiesFewButton = new Button(width-175, 225, 350, 50, 200, "Shape Quantity: Few", 25);

    Button shapeSizesNormalButton = new Button(width-175, 275, 350, 50, 200, "Shape Sizes: Normal", 25);
    Button shapeSizesLargeButton = new Button(width-175, 275, 350, 50, 200, "Shape Sizes: Large", 25);
    Button shapeSizesSmallButton = new Button(width-175, 275, 350, 50, 200, "Shape Sizes: Small", 25);

    //Canvas Options
    super.buttonsArr.add(newButton);
    super.buttonsArr.add(openCanvasButton);
    super.buttonsArr.add(saveButton);
    super.buttonsArr.add(helpButton);
    super.buttonsArr.add(aboutButton);
    super.buttonsArr.add(backButton);

    //Canvas Manipulation Options - Categories
    super.layoutButtonsArr[0] = layoutBlankButton;
    super.layoutButtonsArr[1] = layoutSquareButton;
    super.layoutButtonsArr[2] = layoutSmileButton;
    super.layoutButtonsArr[3] = layoutHypnosisButton;
    super.layoutButtonsArr[4] = layoutStreaksButton;

    super.tintButtonsArr[0] = tintNoneButton;
    super.tintButtonsArr[1] = tintRedButton;
    super.tintButtonsArr[2] = tintYellowButton;
    super.tintButtonsArr[3] = tintBlueButton;

    super.musicButtonsArr[0] = bgMusicOffButton;
    super.musicButtonsArr[1] = bgMusicOnButton;

    super.keyboardControlsButtonsArr[0] = keyboardControlsDefaultButton;
    super.keyboardControlsButtonsArr[1] = keyboardControlsRandomButton;

    super.shapeQuantitiesButtonsArr[0] = shapeQuantitiesNormalButton;
    super.shapeQuantitiesButtonsArr[1] = shapeQuantitiesManyButton;
    super.shapeQuantitiesButtonsArr[2] = shapeQuantitiesFewButton;

    super.shapeSizesButtonsArr[0] = shapeSizesNormalButton;
    super.shapeSizesButtonsArr[1] = shapeSizesLargeButton;
    super.shapeSizesButtonsArr[2] = shapeSizesSmallButton;

    currentLayoutButton = layoutBlankButton;
    currentTintButton = tintNoneButton;
    currentMusicButton = bgMusicOffButton;
    currentKeyboardControlsButton = keyboardControlsDefaultButton;
    currentShapeQuantitiesButton = shapeQuantitiesNormalButton;
    currentShapeSizesButton = shapeSizesNormalButton;

    currentButtonsArr[0] = currentLayoutButton;
    currentButtonsArr[1] = currentTintButton;
    currentButtonsArr[2] = currentMusicButton;
    currentButtonsArr[3] = currentKeyboardControlsButton;
    currentButtonsArr[4] = currentShapeQuantitiesButton;
    currentButtonsArr[5] = currentShapeSizesButton;

    super.buttonsArr.add(currentLayoutButton);
    super.buttonsArr.add(currentTintButton);
    super.buttonsArr.add(currentMusicButton);
    super.buttonsArr.add(currentKeyboardControlsButton);
    super.buttonsArr.add(currentShapeQuantitiesButton);
    super.buttonsArr.add(currentShapeSizesButton);

    super.runPage();
    background(color(random(255), random(255), random(255)));
  }

  public void setRecentCanvasImage(File f) {
    if (f != null) {
      PImage bg = loadImage(f.toString());
      bg.resize(width, height);
      background(bg);
    }
  }

  public void saveImage() {
    File dir = new File(sketchPath("Saved Images"));
    String savedFileName = "Aimless_Artist_Sketch";
    String[] list = dir.list();
    PImage savedImage = get(150, 0, width-500, height);

    if (list == null) {
      println("Folder does not exist or cannot be accessed.");
    } else {
      for (int i = 0; i < list.length; i++) {
        if (list[i].equals(savedFileName+".png")) {
          savedFileName += "1";
        }
      }
    } 
    savedImage.save("Saved Images/" + savedFileName+".png");
  }



  public void createSquareEffect() {
    PImage square = get(-width/2, -height/2, width/2, height/2);
    //  pushMatrix();
    // scale(-1.0, -1.0);
    image(square, width/2, height/2);
    //  popMatrix();
  }

  public void createSmileEffect() {
    PImage leftEye = get(width/3, height/3, width/8, height/8);
    PImage rightEye = get((int)(width*.75), (int)(height*.75), width/8, height/8);
    PImage mouthMiddle = get(width/4, height*7/8, width/2, height/8);

    pushMatrix();
    scale(-1.0, -1.0);
    image(leftEye, -width/3, (-height/3));
    image(rightEye, (-width/3*2), (-height/3));
    image(mouthMiddle, -width/2, -height/2-height/4);
    popMatrix();
  }

  //Sets the selected button for the cicked menu item
  public Button runButton(Button b, Button[] bArr) {
    Button r = null;
    for (int i = 0; i < currentButtonsArr.length; i++) {
      if (b == currentButtonsArr[i]) {
        r = currentButtonsArr[i] = updateCurrentButton(b, bArr);
      }
    }
    return r;
  }

  //Builds the next button in the array for the clicked menu item
  public Button updateCurrentButton(Button b, Button[] arr) {
    for (int i = 0; i < arr.length; i++) {
      if (b == arr[i]) {
        if (i < arr.length-1) {
          b = arr[i + 1];
        } else {
          b = arr[0];
        }
        b.buildButton(b.getCurrentColor(), b.getButtonLabel(), b.getLabelSize());
        break;
      }
    }
    return b;
  }

  public void configureLayout(Button b) {
    imageMode(CENTER);
    if (b == getLayoutButtonsArr()[0]) {
      //Blank Layout
    } else if (b == getLayoutButtonsArr()[1]) {
      canvasPage.createSquareEffect();
    } else if (b == getLayoutButtonsArr()[2]) {
      canvasPage.createSmileEffect();
    }
  }

  public void configureTint(Button b) {
    imageMode(CORNER);
    if (b == getTintButtonsArr()[0]) {
      //No Tint
    } else if (b == getTintButtonsArr()[1]) {
      PImage tintPic = get(0, 0, 0, 0);
      PImage tintMenu = get(0, 0, 100, 315);
      tintPic = get(0, 0, width, height);
      tint(255, 0, 0);
      image(tintPic, 0, 0);
      image(tintMenu, 0, 0);
    } else if (b == getTintButtonsArr()[2]) {
      PImage tintPic = get(0, 0, 0, 0);
      PImage tintMenu = get(0, 0, 100, 315);
      tintPic = get(0, 0, width, height);
      tint(0, 255, 0);
      image(tintPic, 0, 0);
      image(tintMenu, 0, 0);
    } else if (b == getTintButtonsArr()[3]) {
      PImage tintPic = get(0, 0, 0, 0);
      PImage tintMenu = get(0, 0, 100, 315);
      tintPic = get(0, 0, width, height);
      tint(0, 0, 255);
      image(tintPic, 0, 0);
      image(tintMenu, 0, 0);
    }
  }

  public void configureMusic(Button b) {
    if (b == getMusicButtonsArr()[0]) {
      if (player != null) {
        player.close();
      }
    } else if (b == getMusicButtonsArr()[1]) {
      Music music = new Music();
      selectInput("Select a music file:", "setupMusic", null, music);
    }
  }

  public float configureShapeQuantities(Button b) {
    float q = 0;
    if (b == getShapeQuantitiesButtonsArr()[0]) {
      q = 11;
    } else if (b == getShapeQuantitiesButtonsArr()[1]) {
      q = 50;
    } else if (b == getShapeQuantitiesButtonsArr()[2]) {
      q = 4;
    }
    return q;
  }

  public float configureShapeSizes(Button b, float dimension) {
    float s = 0;
    if (b == getShapeSizesButtonsArr()[0]) {
      s = dimension/2;
    } else if (b == getShapeSizesButtonsArr()[1]) {
      s = dimension;
    } else if (b == getShapeSizesButtonsArr()[2]) {
      s = dimension/8;
    }
    return s;
  }

  public boolean checkButtonCategory(Button b, Button[] arr) {
    boolean bool = false;
    for (int j = 0; j < arr.length; j++) {
      if (b == arr[j]) {
        bool = true;
      }
    }
    return bool;
  }

  public Button[] getButtonCategory(Button b) {
    Button[] r = null;
    for (int j = 0; j < getLayoutButtonsArr ().length; j++) {
      if (b == getLayoutButtonsArr()[j]) {
        r = getLayoutButtonsArr();
      }
    }
    for (int j = 0; j < getTintButtonsArr ().length; j++) {
      if (b == getTintButtonsArr()[j]) {
        r = getTintButtonsArr();
      }
    }
    for (int j = 0; j < getMusicButtonsArr ().length; j++) {
      if (b == getMusicButtonsArr()[j]) {
        r = getMusicButtonsArr();
      }
    }
    for (int j = 0; j < getKeyboardControlsButtonsArr ().length; j++) {
      if (b == getKeyboardControlsButtonsArr()[j]) {
        r = getKeyboardControlsButtonsArr();
      }
    }
    for (int j = 0; j < getShapeQuantitiesButtonsArr ().length; j++) {
      if (b == getShapeQuantitiesButtonsArr()[j]) {
        r = getShapeQuantitiesButtonsArr();
      }
    }
    for (int j = 0; j < getShapeSizesButtonsArr ().length; j++) {
      if (b == getShapeSizesButtonsArr()[j]) {
        r = getShapeSizesButtonsArr();
      }
    }
    return r;
  }

  public void setSavedFile(File f) {
    fileImg = f;
  }

  public File getSavedFile() {
    return fileImg;
  }

  public Button getCurrentLayoutButton() {
    return currentLayoutButton;
  }

  public void setCurrentLayoutButton(Button b) {
    currentLayoutButton = b;
  }

  public Button getCurrentTintButton() {
    return currentTintButton;
  }

  public void setCurrentTintButton(Button b) {
    currentTintButton = b;
  }

  public Button getCurrentMusicButton() {
    return currentMusicButton;
  }

  public void setCurrentMusicButton(Button b) {
    currentMusicButton = b;
  }

  public Button getCurrentKeyboardControlsButton() {
    return currentKeyboardControlsButton;
  }

  public void setCurrentKeyboardControlsButton(Button b) {
    currentKeyboardControlsButton = b;
  }

  public Button getCurrentShapeQuantitiesButton() {
    return currentShapeQuantitiesButton;
  }

  public void setCurrentShapeQuantitiesButton(Button b) {
    currentShapeQuantitiesButton = b;
  }

  public Button getCurrentShapeSizesButton() {
    return currentShapeSizesButton;
  }

  public void setCurrentShapeSizesButton(Button b) {
    currentShapeSizesButton = b;
  }

  public Button[] getLayoutButtonsArr() {
    return super.layoutButtonsArr;
  }

  public Button[] getTintButtonsArr() {
    return super.tintButtonsArr;
  }

  public Button[] getMusicButtonsArr() {
    return super.musicButtonsArr;
  }

  public Button[] getKeyboardControlsButtonsArr() {
    return super.keyboardControlsButtonsArr;
  }

  public Button[] getShapeQuantitiesButtonsArr() {
    return super.shapeQuantitiesButtonsArr;
  }

  public Button[] getShapeSizesButtonsArr() {
    return super.shapeSizesButtonsArr;
  }
}

