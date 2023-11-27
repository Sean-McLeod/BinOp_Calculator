package View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Display {
    public Stage stage; // this is the window
    public Scene scene; // need to specify root node
    public GridPane gridPane;
    public Label userInput; // to display the user's input
    public ArrayList<Button> numberButtons;
    public ArrayList<Button> operationButtons;
    public ArrayList<Button> funcButtons; // prev button, next button, change mode button

    public Display(Stage stage){
        this.stage = stage;
        stage.setHeight(600);
        // set user label -- will be empty at first
        this.userInput = new Label();

        ArrayList<Button> numberButtons = new ArrayList<>(); // list to hold number buttons
        ArrayList<Button> operationButtons = new ArrayList<>(); // list to hold operation buttons
        ArrayList<Button> funcButtons = new ArrayList<>(); // list to hold functionality buttons

        initUI(); // show the gaphics
    }

    /**
     * build the GUI!
     */

    public void initUI(){
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(40);

        this.userInput.setText("TemporaryText 1234+-/x");
        userInput.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20; -fx-alignment: center-right;" +
                "-fx-padding: 15px;-fx-background-color: black; -fx-background-radius: 5px");
        userInput.setTextFill(Color.WHITE);
        userInput.setPrefSize(340, 70);

        root.getChildren().add(userInput); // add userInput Label to root node
        this.gridPane = new GridPane(); // GRIDPANE to hold items

        // add userInput text to gridPane:
        //gridPane.add(userInput, 0, 0, 5, 1);

        // setting the contrains for a gridpane
        //Three columns, three rows for the GridPane
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        ColumnConstraints column3 = new ColumnConstraints();

        // Row constraints
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getColumnConstraints().addAll( column1 , column2 , column3);
        gridPane.getRowConstraints().addAll( row1 , row2 , row3);

        // create instances of the buttons and add them to the grid
        // num button
        NumOpButton num1 = new NumOpButton("1");
        NumOpButton num2 = new NumOpButton("2");
        NumOpButton num3 = new NumOpButton("3");
        NumOpButton num4 = new NumOpButton("4");
        NumOpButton num5 = new NumOpButton("5");
        NumOpButton num6 = new NumOpButton("6");
        NumOpButton num7 = new NumOpButton("7");
        NumOpButton num8 = new NumOpButton("8");
        NumOpButton num9 = new NumOpButton("9");
        NumOpButton num0 = new NumOpButton("0");
        // col, row
        gridPane.add(num1, 0, 1);
        gridPane.add(num2, 1, 1);
        gridPane.add(num3, 2, 1);
        gridPane.add(num4, 0, 2);
        gridPane.add(num5, 1, 2);
        gridPane.add(num6, 2, 2);
        gridPane.add(num7, 0, 3);
        gridPane.add(num8, 1, 3);
        gridPane.add(num9, 2, 3);

        // operation buttons
        NumOpButton add = new NumOpButton("+");
        NumOpButton sub = new NumOpButton("-");
        NumOpButton mul = new NumOpButton("x");
        NumOpButton div = new NumOpButton("/");
        FuncButton equal = new FuncButton("=");
        FuncButton parOpen = new FuncButton("(");
        FuncButton parClose = new FuncButton(")");

        gridPane.add(parOpen, 3, 1);
        gridPane.add(sub, 3, 2);
        gridPane.add(mul, 3, 3);
        gridPane.add(add, 4, 2);
        gridPane.add(parClose, 4, 1);
        gridPane.add(div, 4, 3);
        gridPane.add(equal, 4, 5);

        // for debugging:
        gridPane.setGridLinesVisible(true);
        root.getChildren().add(this.gridPane); // add buttons gridPane to root node


        this.scene = new Scene(root, 500, 1000);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    /**
     * update the user text when given a new input
     */
    public void updateUserText(String input){
        String text = userInput.getText();
        userInput.setText(text + input);
    }
}