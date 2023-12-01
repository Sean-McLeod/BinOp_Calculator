package View.Buttons;

import View.Display;

public class ThemeButton extends CustomButton{
    final private Display display; // display of the calculator
    final private String a; // background colour
    final private String b; // button colour
    final private String c; // userInputLabel colour
    public ThemeButton(Display display, String text, String a, String b, String c){
        super(text);
        setPrefWidth(200);

        this.display = display;
        this.a = a;
        this.b = b;
        this.c = c;
        setUpEventHandler();
    }

    /**
     * set event handler for when theme buttons are clicked
     */
    public void setUpEventHandler() {
        setOnAction(event -> {
            //change button colours
            for (CustomButton button: display.allButtons){
                button.setStyle("-fx-text-fill:" +a + ";");
                button.setOriginalColour(b);
            }
            //change background colour of all scenes
            display.themeScene.getRoot().setStyle("-fx-background-color:" +a + ";");
            display.calcScene.getRoot().setStyle("-fx-background-color:" +a + ";");
            display.historyScene.getRoot().setStyle("-fx-background-color:" +a + ";");

            //change background and text colour of user input and history labels
            display.userInput.setLabelStyle(c, display.userInput.fontSize,a);
            display.historyLabel.setLabelStyle(c, display.userInput.fontSize,a);
        });
    }
}