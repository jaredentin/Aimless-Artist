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

