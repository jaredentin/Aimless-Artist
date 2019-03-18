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
