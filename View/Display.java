package View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Display {
    public Stage stage; // this is the window
    public Scene scene; // need to specify root node
    public  FlowPane flowPane; // to hold buttons
    public GridPane gridPane;
    public Label userInput; // to display the user's input
    public ArrayList<Button> numberButtons;
    public ArrayList<Button> operationButtons;
    public ArrayList<Button> funcButtons; // prev button, next button, change mode button

    public Display(Stage stage){
        this.gridPane = new GridPane();
        this.scene = new Scene(this.gridPane, 500, 1000);
        this.stage = stage;
        this.stage.setScene(this.scene);
        this.stage.show();

        ArrayList<Button> numberButtons = new ArrayList<>(); // list to hold number buttons
        ArrayList<Button> operationButtons = new ArrayList<>(); // list to hold operation buttons
        ArrayList<Button> funcButtons = new ArrayList<>(); // list to hold functionality buttons
    }
}
