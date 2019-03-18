public class InstructionsDialog extends Dialog {
  public InstructionsDialog(boolean b, String[] s) {
    super(b, s);
  }

  public void displayInstructions() {
    runDialog(getEnabled(), getStringArr(), width/2 + 100, height/2 - 100);
  }
}
