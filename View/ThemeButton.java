package View;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class ThemeButton extends CustomButton{
    final private String a; // background colour
    final private String b; // button colour
    final private String c; // userInputLabel colour
    public ThemeButton(String text, String a, String b, String c){
        super(text);
        setPrefWidth(200);
        this.a = a;
        this.b = b;
        this.c = c;
        setUpEventHandler();
    }

    @Override
    public String setUpEventHandler() {
        setOnAction(event -> {
            for (CustomButton button: Display.allButtons){
                button.setStyle("-fx-text-fill:" +a + ";");
                button.setOriginalColour(b);
            }
            Display.stage.getScene().getRoot().setStyle("-fx-background-color:" +a + ";");
            //Display.stage.setScene(Display.scene);
            Display.scene.getRoot().setStyle("-fx-background-color:" +a + ";");
            Display.userInput.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20; -fx-alignment: center-right;" +
                    "-fx-padding: 15px;-fx-background-color:" + c + "; -fx-background-radius: 5px; -fx-text-fill:" +a + ";");
        });
        return null;
    }
}