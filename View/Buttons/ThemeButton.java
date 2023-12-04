package View.Buttons;

import View.CalculatorView;

public class ThemeButton extends CustomButton{
    final private CalculatorView display; // display of the calculator
    final private String backColour; // background colour
    final private String buttonColour; // button colour
    final private String labelColour; // userInputLabel colour

    /**
     * ThemeButton Constructor
     * __________________________
     * Initializes attributes
     */
    public ThemeButton(CalculatorView display, String text, String backColour, String buttonColour, String labelColour){
        super(text);
        setPrefWidth(200);

        this.display = display;
        this.backColour = backColour;
        this.buttonColour = buttonColour;
        this.labelColour = labelColour;
        setUpEventHandler();
    }

    /**
     * set event handler for when theme buttons are clicked
     */
    public void setUpEventHandler() {
        setOnAction(event -> {
            //change button colours
            for (CustomButton button: display.allButtons){
                button.setStyle("-fx-text-fill:" +backColour + ";");
                button.setOriginalColour(buttonColour);
            }
            //change background colour of all scenes
            display.themeScene.getRoot().setStyle("-fx-background-color:" +backColour + ";");
            display.calcScene.getRoot().setStyle("-fx-background-color:" +backColour + ";");
            display.historyScene.getRoot().setStyle("-fx-background-color:" +backColour + ";");

            //change background and text colour of user input and history labels
            display.userInput.setLabelStyle(labelColour, display.userInput.fontSize,backColour);
            display.historyLabel.setLabelStyle(labelColour, display.userInput.fontSize,backColour);
        });
    }
}