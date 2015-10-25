Page[] pageArr;
Page currentPage;
MainPage mainPage;
InstructionsPage instructionsPage;
CanvasPage canvasPage;
About aboutDialog;
InstructionsDialog instructDialog;

Minim minim = new Minim(this);
AudioPlayer player;

PImage scrShot = null;

void setup() {
  size(displayWidth, displayHeight);
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

void draw() {
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

