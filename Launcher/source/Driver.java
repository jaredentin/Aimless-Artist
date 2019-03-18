import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Driver extends PApplet {

Page[] pageArr;
Page currentPage;
MainPage mainPage;
InstructionsPage instructionsPage;
CanvasPage canvasPage;
About aboutDialog;
InstructionsDialog instructDialog;

//Minim minim = new Minim(this);
//AudioPlayer player;

PImage scrShot = null;

public void setup() {
  
  colorMode(RGB);

  mainPage = new MainPage(loadImage("Aimless_Artist.jpg"));
  canvasPage = new CanvasPage(color(random(255), random(255), random(255)));
  instructionsPage = new InstructionsPage(loadImage("Aimless_Artist.jpg"));

  String[] aboutTextArr = new String[3];
  aboutTextArr[0] = "Developed by Jared Entin";
  aboutTextArr[1] = "Created: Fall, 2013\nModified: Summer, 2015";
  aboutTextArr[2] = "Thanks for playing!";

  String[] instructTextArr = new String[3];
  instructTextArr[0] = instructionsPage.getText(instructionsPage.getTextArr(), 0);
  instructTextArr[1] = instructionsPage.getText(instructionsPage.getTextArr(), 1);
  instructTextArr[2] = instructionsPage.getText(instructionsPage.getTextArr(), 2);

  aboutDialog = new About(false, aboutTextArr);
  instructDialog = new InstructionsDialog(false, instructTextArr);

  pageArr = new Page[5];
  pageArr[0] = mainPage;
  pageArr[1] = instructionsPage;
  pageArr[2] = canvasPage;
  pageArr[3] = aboutDialog;
  pageArr[4] = instructDialog;

  mainPage.runPage();
}

public void mouseClicked() {
  for (int i = 0; i < pageArr.length; i++) {
    if (currentPage.equals(pageArr[i])) {
      for (int j = 0; j < pageArr[i].getButtonsArr ().size(); j++) {
        if (pageArr[i].getButtonsArr().get(j).checkMouseHover()) {
          Button button = pageArr[i].getButtonsArr().get(j);

          //Main Page buttons
          if (button.getButtonLabel().equals("Start")) {
            canvasPage.runPage();
            return;
          } else if (button.getButtonLabel().equals("Open Recent")) {
            selectInput("Select an image:", "setRecentCanvasImage", null, canvasPage);
            canvasPage.runPage();
            return;
          } else if (button.getButtonLabel().equals("Instructions")) {
            instructionsPage.runPage();
            return;
          } else if (button.getButtonLabel().equals("Exit")) {
            exit();
          }

          //Back button for Instructions and Canvas Pages
          else if (button.getButtonLabel().equals("Back")) {
            if (pageArr[i] == canvasPage || pageArr[i] == instructionsPage) {
              mainPage.runPage();
            } else {
              if (pageArr[i] == aboutDialog) {
                aboutDialog.setEnabled(false);
              } else if (pageArr[i] == instructDialog) {
                instructDialog.setEnabled(false);
              }
              currentPage = canvasPage;
              set(0, 0, scrShot);
            }
          }

          //Canvas Page Left buttons
          else if (button.getButtonLabel().equals("New")) {
            canvasPage.runPage();
            return;
          } else if (button.getButtonLabel().equals("Open")) {
            selectInput("Select an image:", "setRecentCanvasImage", null, canvasPage);
            return;
          } else if (button.getButtonLabel().equals("Save")) {
            canvasPage.saveImage();
            return;
          } else if (button.getButtonLabel().equals("About")) {
            println("About");
            scrShot = get(0, 0, width, height);
            aboutDialog.setEnabled(true);
            aboutDialog.displayAbout();
            return;
          } else if (button.getButtonLabel().equals("Help")) {
            println("Halp");
            scrShot = get(0, 0, width, height);
            instructDialog.setEnabled(true);
            instructDialog.displayInstructions();
            return;
          }

          //Canvas Page Right Buttons
          else if (pageArr[i] == canvasPage && !aboutDialog.getEnabled() && !instructDialog.getEnabled()) {
            if (canvasPage.checkButtonCategory(button, canvasPage.getLayoutButtonsArr())) {
              canvasPage.setCurrentLayoutButton(canvasPage.runButton(canvasPage.getCurrentLayoutButton(), canvasPage.getLayoutButtonsArr ()));
              return;
            } else if (canvasPage.checkButtonCategory(button, canvasPage.getTintButtonsArr())) {
              canvasPage.setCurrentTintButton(canvasPage.runButton(canvasPage.getCurrentTintButton(), canvasPage.getTintButtonsArr ()));
              return;
            } else if (canvasPage.checkButtonCategory(button, canvasPage.getMusicButtonsArr())) {
              canvasPage.setCurrentMusicButton(canvasPage.runButton(canvasPage.getCurrentMusicButton(), canvasPage.getMusicButtonsArr ()));
              canvasPage.configureMusic(canvasPage.currentMusicButton);
              return;
            } else if (canvasPage.checkButtonCategory(button, canvasPage.getKeyboardControlsButtonsArr())) {
              canvasPage.setCurrentKeyboardControlsButton(canvasPage.runButton(canvasPage.getCurrentKeyboardControlsButton(), canvasPage.getKeyboardControlsButtonsArr ()));
              return;
            } else if (canvasPage.checkButtonCategory(button, canvasPage.getShapeQuantitiesButtonsArr())) {
              canvasPage.setCurrentShapeQuantitiesButton(canvasPage.runButton(canvasPage.getCurrentShapeQuantitiesButton(), canvasPage.getShapeQuantitiesButtonsArr ()));
              return;
            } else if (canvasPage.checkButtonCategory(button, canvasPage.getShapeSizesButtonsArr())) {
              canvasPage.setCurrentShapeSizesButton(canvasPage.runButton(canvasPage.getCurrentShapeSizesButton(), canvasPage.getShapeSizesButtonsArr ()));
              return;
            }
          }
        }
      }
    }
  }
}

public void keyReleased() {
  if (currentPage.equals(canvasPage)) {
    char n = key;

    if (canvasPage.currentKeyboardControlsButton == canvasPage.getKeyboardControlsButtonsArr()[1]) {
      n = (char)random(97, 104);
    }

    switch(n) {
    case 'a':
      Circle circleShape = new Circle(50, 50, 50, 50, 5, 255, 0);
      circleShape.constructShape();
      break;

    case 'b':
      Triangle triangleShape = new Triangle(0, 0, 0, 0, 0, 0, 0, 0, 0);
      triangleShape.constructShape();
      break;

    case 'c':
      Quad quadShape = new Quad(50, 50, 50, 50, 5, 255, 0);
      quadShape.constructShape();
      break;

    case 'd':
      Dot dotShape = new Dot(0, 0, 0, 0, 0, 0, 0);
      dotShape.constructShape();
      break;

    case 'e':
      Segment segmentShape = new Segment(0, 0, 0, 0, 0, 0, 0);
      segmentShape.constructShape();
      break;

    case 'f':
      Curve curveShape = new Curve(0, 0, 0, 0, 0, 0, 0, 0);
      curveShape.constructShape();
      break;

    case 'g':
      Streak streakShape = new Streak(0, 0, 0, 0, 0, 0, 0);
      streakShape.constructShape();
      break;
    }
    canvasPage.configureLayout(canvasPage.currentLayoutButton);
    canvasPage.configureTint(canvasPage.currentTintButton);
  }
}

public void draw() {
  for (int i = 0; i < pageArr.length; i++) {
    if (currentPage.equals(pageArr[i])) {
      pageArr[i].checkButtons(pageArr[i], pageArr[i].getButtonsArr().toArray(new Button[ pageArr[i].getButtonsArr().size()]));
      if (pageArr[i] == canvasPage) {
        pageArr[i].checkButtons(pageArr[i], canvasPage.currentButtonsArr);
      } else if (pageArr[i] == aboutDialog) {
        if (aboutDialog.getEnabled()) {
          aboutDialog.checkButtons(aboutDialog, pageArr[i].getButtonsArr().toArray(new Button[ pageArr[i].getButtonsArr().size()]));
        }
      } else if (pageArr[i] == instructDialog) {
        if (instructDialog.getEnabled()) {
          instructDialog.checkButtons(instructDialog, pageArr[i].getButtonsArr().toArray(new Button[ pageArr[i].getButtonsArr().size()]));
        }
      }
    }
  }
}
public class About extends Dialog {
  public About(boolean b, String[] s) {
    super(b, s);
  }

  public void displayAbout() {
    runDialog(getEnabled(), getStringArr(), 300, height/2 - 100);
  }
}
public class Button
{
  private int currentColor;
  private float rectX, rectY, rectWidth, rectHeight;
  private String buttonLabel;
  private float labelSize;
  private float mouseXMin;
  private float mouseXMax;
  private float mouseYMin;
  private float mouseYMax;

  public Button(float x, float y, float w, float h, int c, String l, int size) {
    rectX = x;
    rectY = y;
    rectWidth = w;
    rectHeight = h;
    currentColor = c;
    buttonLabel = l;
    labelSize = size + rectHeight/size;
    mouseXMin = rectX - (rectWidth/2);
    mouseXMax = rectX + (rectWidth/2);
    mouseYMin = rectY - (rectHeight/2);
    mouseYMax = rectY + (rectHeight/2);
  }

  public void buildButton(int c, String s, float si) {
    stroke(0);
    strokeWeight(1);
    rectMode(CENTER);
    fill(c);
    rect(rectX, rectY, rectWidth, rectHeight);
    Text text = new Text(s, si, 0, 0, rectX, rectY);
    text.buildText();
  }

  public boolean checkMouseHover() {
    if (mouseX >= mouseXMin && mouseX <= mouseXMax && mouseY >= mouseYMin && mouseY <= mouseYMax) {
      this.setCurrentColor(0);
      return true;
    } else {
      return false;
    }
  }

  public float getRectX() {
    return rectX;
  }

  public void setRectX(float x) {
    rectX = x;
  }

  public float getRectY() {
    return rectY;
  }

  public void setRectY(float y) {
    rectY = y;
  }

  public float getRectWidth() {
    return rectWidth;
  }

  public void setRectWidth(float w) {
    rectWidth = w;
  }

  public float getRectHeight() {
    return rectHeight;
  }

  public void setRectHeight(float h) {
    rectHeight = h;
  }

  public int getCurrentColor() {
    return currentColor;
  }

  public void setCurrentColor(int c) {
    currentColor = c;
  }

  public float getMouseXMin() {
    return mouseXMin;
  }

  public float getMouseXMax() {
    return mouseXMax;
  }

  public float getMouseYMin() {
    return mouseYMin;
  }

  public float getMouseYMax() {
    return mouseYMax;
  }

  public void setMouseXMin(float x) {
    mouseXMin = x;
  }

  public void setMouseXMax(float x) {
    mouseXMax = x;
  }

  public void setMouseYMin(float y) {
    mouseYMin = y;
  }

  public void setMouseYMax(float y) {
    mouseYMax = y;
  }

  public String getButtonLabel() {
    return buttonLabel;
  }

  public void setButtonlabel(String s) {
    buttonLabel = s;
  }

  public float getLabelSize() {
    return labelSize;
  }

  public void setLabelSize(float s) {
    labelSize = s;
  }
}
public class CanvasPage extends Page {
  private Button currentLayoutButton;
  private Button currentTintButton;
  private Button currentMusicButton;
  private Button currentKeyboardControlsButton;
  private Button currentShapeQuantitiesButton;
  private Button currentShapeSizesButton;

  private Button[] currentButtonsArr = new Button[6];

  private File fileImg = null;
  private int col;

  public CanvasPage() {
  }

  public CanvasPage(PImage img) {
    super(img);
  }

  public CanvasPage(int bg) {
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
    PImage rightEye = get((int)(width*.75f), (int)(height*.75f), width/8, height/8);
    PImage mouthMiddle = get(width/4, height*7/8, width/2, height/8);

    pushMatrix();
    scale(-1.0f, -1.0f);
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
    //if (b == getMusicButtonsArr()[0]) {
    //  if (player != null) {
    //    player.close();
    //  }
    //} else if (b == getMusicButtonsArr()[1]) {
    //  Music music = new Music();
    //  selectInput("Select a music file:", "setupMusic", null, music);
    //}
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
public class Circle extends Shape {
  Circle(float x, float y, float w, float h, float sw, int s, int c) {
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
public class Curve extends Shape {
  Curve(float x, float y, float w, float h, float b, float sw, int s, int c) {
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
public class Dialog extends Page {
  private boolean enabled; 
  private String[] stringArr;

  public Dialog(boolean b, String[] s) {
    enabled = b;
    stringArr = s;
  }

  public void runDialog(boolean b, String[] s, float w, float h) {
    if (b) {
      fill(200, 220);
      rect(width/2, height/2, w, h);

      for (int i = 0; i < s.length; i++) {
        Text text = new Text(s[i], 20, 0, 0, width/2, height/2 - 150+(75*i));
        text.buildText(width/2, 200);
      }

      Button backButton = new Button(width/2, height/2 + h/4, 200, 50, 200, "Back", 25);
      super.buttonsArr = new ArrayList<Button>(1);
      super.buttonsArr.add(backButton);
      super.runPage();
    }
  }

  public void setEnabled(boolean b) {
    enabled = b;
  }

  public boolean getEnabled() {
    return enabled;
  }

  public String[] getStringArr() {
    return stringArr;
  }

  public void setStringArr(String[] s) {
    stringArr = s;
  }
}
public class Dot extends Shape {
  Dot (float x, float y, float w, float h, float sw, int s, int c) {
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
public class InstructionsDialog extends Dialog {
  public InstructionsDialog(boolean b, String[] s) {
    super(b, s);
  }

  public void displayInstructions() {
    runDialog(getEnabled(), getStringArr(), width/2 + 100, height/2 - 100);
  }
}
public class InstructionsPage extends Page {
  private String[] textArr = new String[3];


  public InstructionsPage(PImage bg) {
    super(bg);
    textArr[0] = "Aimless Artist is a program designed to make the creation of abstract portraits simpler and easier. From the start screen, click NEW to open a blank canvas and start creating.";
    textArr[1] = "The program's controls aren't your average controls, though. Each key alphabetically from A to G creates a random effect at a random location on the canvas.";
    textArr[2] = "Eventually, these effects intertwine to create an incredible portrait that can be saved and opened later.";
  }

  public void runPage() {    
    Button backButton = new Button(width/2, height/2 + 100, 200, 50, 200, "Back", 25);

    super.buttonsArr = new ArrayList<Button>(1);
    super.buttonsArr.add(backButton);
    super.runPage();

    fill(200, 200);
    rect(width/2, height/2, width/2 + 100, height/2 - 100);

    buildInstructions();
  }

  public void buildInstructions() {
    textArr[0] = "Aimless Artist is a program designed to make the creation of abstract portraits simpler and easier. From the start screen, click NEW to open a blank canvas and start creating.";
    textArr[1] = "The program's controls aren't your average controls, though. Each key alphabetically from A to G creates a random effect at a random location on the canvas.";
    textArr[2] = "Eventually, these effects intertwine to create an incredible portrait that can be saved and opened later.";

    Text instructText1 = new Text(textArr[0], 20, 0, 0, width/2, height/2 - 150);
    Text instructText2 = new Text(textArr[1], 20, 0, 0, width/2, height/2 - 75);
    Text instructText3 = new Text(textArr[2], 20, 0, 0, width/2, height/2);

    instructText1.buildText(width/2, 200);
    instructText2.buildText(width/2, 200);
    instructText3.buildText(width/2, 200);
  }

  public String getText(String[] arr, int i) {
    return arr[i];
  }

  public String[] getTextArr() {
    return textArr;
  }
}
public class MainPage extends Page {
  public MainPage(PImage bg) {
    super(bg);
  }

  public void runPage() {
    super.buttonsArr = new ArrayList<Button>(4);

    Button startButton = new Button(300, 300, 300, 100, 200, "Start", 28);
    Button openButton = new Button(300, 450, 300, 100, 200, "Open Recent", 28);
    Button helpButton = new Button(300, 600, 300, 100, 200, "Instructions", 28);
    Button exitButton = new Button(300, 750, 300, 100, 200, "Exit", 28);

    super.buttonsArr.add(startButton);
    super.buttonsArr.add(openButton);
    super.buttonsArr.add(helpButton);
    super.buttonsArr.add(exitButton);

    //    for (int i = 0; i < super.buttonsArr.size(); i++){
    //       buttonsArr.get(i). 
    //    }

    super.runPage();
  }
}
//import ddf.minim.*;

//public class Music {
//  public Music() {
//  }

//  public void setupMusic(File f) {
//    if (f.toString().endsWith(".mp3") || f.toString().endsWith(".wav")) {
//      player = minim.loadFile(f.toString());
//      player.play();
//    } else {
//      println("Please enter a valid music file.");
//    }
//  }

//  public AudioPlayer getMusicPlayer() {
//    return player;
//  }

//  public void setPlayer(AudioPlayer p) {
//    player = p;
//  }
//}
public class Page {
  private PImage bgImg;
  private int bgCol;
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

  public Page(int c) {
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

  public int getBackgroundColor() { 
    return bgCol;
  }

  public void setBackgroundColor(int c) {
    bgCol = c;
  }
}
public class Quad extends Shape {
  Quad(float x, float y, float w, float h, float sw, int s, int c) {
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
public class Segment extends Shape {
  Segment(float x, float y, float w, float h, float sw, int s, int c) {
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
public class Shape {
  private float xPos, yPos, shapeWidth, shapeHeight, bezier, triX1, triY1, triX2, triY2, strokeW, quantity;
  private int shapeCol, stroke, col1, col2, col3;

  public Shape(float x, float y, float w, float h, float sw, int s, int c) {
    xPos = x;
    yPos = y;
    shapeWidth = w;
    shapeHeight = h;
    strokeW = sw;
    stroke = s;
    shapeCol = c;
  }

  public Shape(float x, float y, float w, float h, float b, float sw, int s, int c) {
    xPos = x;
    yPos = y;
    shapeWidth = w;
    shapeHeight = h;
    bezier = b;
    strokeW = sw;
    stroke = s;
    shapeCol = c;
  }

  public Shape(float x, float y, float tx1, float ty1, float tx2, float ty2, float sw, int s, int c) {
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

  public int getStroke() {
    return stroke;
  }

  public int getShapeColor() {
    return shapeCol;
  }
}
public class Streak extends Shape {
  Streak (float x, float y, float w, float h, float sw, int s, int c) {
    super(x, y, w, h, sw, s, c);
  }

  public void constructShape() { 
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
    return random(1) > .5f;
  }

  public boolean inverseColorDirection(int col, boolean b) {
    if (col >= 255 || col <+ 0) {
      b = !b;
    }
    return b;
  }
}
public class Text {
  private String text;
  private float size, stroke, xPos, yPos;
  private int col;

  public Text(String t, float si, float st, int c, float x, float y) {
    text = t;
    size = si;
    stroke = st;
    col = c;
    xPos = x;
    yPos = y;
  }

  public void buildText() {
    textAlign(CENTER, CENTER);
    textSize(size);
    stroke(stroke);
    fill(col);
    text(text, xPos, yPos);
  }

  public void buildText(float w, float h) {
    textAlign(CENTER, CENTER);
    textSize(size);
    stroke(stroke);
    fill(col);
    text(text, xPos, yPos, w, h);
  }
}
public class Triangle extends Shape {
  Triangle(float x, float y, float tx1, float ty1, float tx2, float ty2, float sw, int s, int c) {
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
  public void settings() {  size(displayWidth, displayHeight); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "Driver" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
