package View;

import View.Buttons.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Objects;

import java.util.ArrayList;

public class Display {
    public Stage stage; // this is the window
    public Scene calcScene; // scene for calculator page
    public Scene themeScene; // scene of themes page
    private GridPane gridPane; // gridpane holding calculator buttons
    public InputLabel userInput; // to display the user's input
    public ArrayList<NumOpButton> numberButtons;
    public ArrayList<NumOpButton> operationButtons;
    public ArrayList<FuncButton> funcButtons; // prev button, next button, change mode button
    public ArrayList<CustomButton> allButtons; // list containing all button objects;

    public Display(Stage stage){
        this.stage = stage;
        stage.setHeight(600);
        stage.setWidth(500);
        // set user label -- will be empty at first
        this.userInput = new InputLabel();

        this.numberButtons = new ArrayList<NumOpButton>(); // list to hold number buttons
        this.operationButtons = new ArrayList<NumOpButton>(); // list to hold operation buttons
        this.funcButtons = new ArrayList<FuncButton>(); // list to hold functionality buttons
        allButtons = new ArrayList<CustomButton>(); // list to hold functionality buttons

        initUI(); // show the gaphics
    }

    /**
     * build the GUI!
     */

    public void initUI(){
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(40);
        root.setStyle("-fx-background-color: #eeeeee");

        this.userInput.setText("");
        root.getChildren().add(userInput); // add userInput Label to root node

        this.gridPane = new GridPane(); // GRIDPANE to hold items

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
        NumOpButton num1 = new NumOpButton("1"); allButtons.add(num1); numberButtons.add(num1);
        NumOpButton num2 = new NumOpButton("2"); allButtons.add(num2); numberButtons.add(num2);
        NumOpButton num3 = new NumOpButton("3"); allButtons.add(num3); numberButtons.add(num3);
        NumOpButton num4 = new NumOpButton("4"); allButtons.add(num4); numberButtons.add(num4);
        NumOpButton num5 = new NumOpButton("5"); allButtons.add(num5); numberButtons.add(num5);
        NumOpButton num6 = new NumOpButton("6"); allButtons.add(num6); numberButtons.add(num6);
        NumOpButton num7 = new NumOpButton("7"); allButtons.add(num7); numberButtons.add(num7);
        NumOpButton num8 = new NumOpButton("8"); allButtons.add(num8); numberButtons.add(num8);
        NumOpButton num9 = new NumOpButton("9"); allButtons.add(num9); numberButtons.add(num9);
        NumOpButton num0 = new NumOpButton("0"); allButtons.add(num0); numberButtons.add(num0);
// add an event to all the buttons (define what they do when they're clicked)
        for (Button button: numberButtons){
            button.setOnAction(mouseEvent -> updateUserText(button.getText()));
        }
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
        gridPane.add(num0, 1, 4);

        // operation buttons
        // operation buttons
        NumOpButton add = new NumOpButton("+"); allButtons.add(add); operationButtons.add(add);
        NumOpButton sub = new NumOpButton("-"); allButtons.add(sub); operationButtons.add(sub);
        NumOpButton mul = new NumOpButton("x"); allButtons.add(mul); operationButtons.add(mul);
        NumOpButton div = new NumOpButton("/"); allButtons.add(div); operationButtons.add(div);
        NumOpButton pow = new NumOpButton("^"); allButtons.add(pow); operationButtons.add(pow);
        NumOpButton dec = new NumOpButton("."); allButtons.add(dec); operationButtons.add(dec);
        NumOpButton parOpen = new NumOpButton("("); allButtons.add(parOpen); operationButtons.add(parOpen);
        NumOpButton parClose = new NumOpButton(")"); allButtons.add(parClose); operationButtons.add(parClose);

        // function buttons
        NumOpButton del = new NumOpButton("⌫"); allButtons.add(del); operationButtons.add(del);
        NumOpButton equal = new NumOpButton("="); allButtons.add(equal); operationButtons.add(equal);
    // add an event to all the buttons (define what they do when they're clicked)
        for (Button button: operationButtons){
            button.setOnAction(mouseEvent -> updateUserText(button.getText()));
        }
    // handle for deleting text
        del.setOnAction(mouseEvent -> updateUserText("DEL"));

        gridPane.add(parOpen, 3, 1);
        gridPane.add(sub, 3, 2);
        gridPane.add(mul, 3, 3);
        gridPane.add(add, 4, 2);
        gridPane.add(parClose, 4, 1);
        gridPane.add(div, 4, 3);
        gridPane.add(del, 0, 4);
        gridPane.add(pow, 2, 4);
        gridPane.add(dec, 3, 4);
        gridPane.add(equal, 4, 4);

        // for debugging:
        //gridPane.setGridLinesVisible(true);
        root.getChildren().add(this.gridPane); // add buttons gridPane to root node

        // HBox node for theme, font-size, and history buttons
        HBox funcPane = new HBox();
        funcPane.setSpacing(15);
        funcPane.setAlignment(Pos.CENTER);
        root.getChildren().add(funcPane);
         // to clear the userInput
        FuncButton clear = new FuncButton("Clear"); allButtons.add(clear);
        clear.setPrefWidth(110);
        clear.setPrefHeight(50);
        // handle for clearing the userInput
        clear.setOnAction(mouseEvent -> updateUserText("CLEAR"));
        funcPane.getChildren().add(clear);

        //change theme button
        createThemesScene(); // create themes scene in advance
        FuncButton changeTheme = new FuncButton("Theme"); allButtons.add(changeTheme); funcButtons.add(changeTheme);
        changeTheme.setPrefWidth(110);
        changeTheme.setPrefHeight(50);
        funcPane.getChildren().add(changeTheme);
        //changeThemes button handler
        changeTheme.setOnAction(event -> stage.setScene(themeScene));

        //history traversal button
        FuncButton historyButton = new FuncButton("History"); allButtons.add(historyButton); funcButtons.add(historyButton);
        historyButton.setPrefWidth(110);
        historyButton.setPrefHeight(50);
        funcPane.getChildren().add(historyButton);

        //font size button
        FuncButton fontSizeButton = new FuncButton("Size"); allButtons.add(fontSizeButton); funcButtons.add(fontSizeButton);
        fontSizeButton.setPrefWidth(110);
        fontSizeButton.setPrefHeight(50);
        funcPane.getChildren().add(fontSizeButton);
        //font size button handler
        fontSizeButton.setOnAction(event -> {
            for (CustomButton b: allButtons) {b.increaseFontSize();}
            userInput.increaseFontSize();});

        this.calcScene = new Scene(root, 500, 1000);
        this.stage.setScene(this.calcScene);
        this.stage.show();
    }

    /**
     * Create themes scene
     */
    public void createThemesScene(){
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(30);

        // Various theme buttons
        ThemeButton darkTheme = new ThemeButton(this, "Dark", "black", "grey", "white"); allButtons.add(darkTheme);
        ThemeButton lightTheme = new ThemeButton(this, "Light", "white", "grey", "#c1c1c1"); allButtons.add(lightTheme);
        ThemeButton forestTheme = new ThemeButton(this, "Forest", "#388e3c", "#8bc34a", "#c5f0a4"); allButtons.add(forestTheme);
        ThemeButton earthTheme = new ThemeButton(this, "Earth", "#d3bd9a", "#674f04", "#402a23"); allButtons.add(earthTheme);
        ThemeButton oceanTheme = new ThemeButton(this, "Ocean", "#eeeeee", "#0092ca", "#393e46"); allButtons.add(oceanTheme);

        //back button to change scene back to calculator
        FuncButton backButton = new FuncButton("←"); allButtons.add(backButton); funcButtons.add(backButton);
        backButton.setOnAction(event2 -> stage.setScene(calcScene));

        //add buttons to screen
        root.getChildren().add(darkTheme);
        root.getChildren().add(lightTheme);
        root.getChildren().add(forestTheme);
        root.getChildren().add(earthTheme);
        root.getChildren().add(oceanTheme);
        root.getChildren().add(backButton);

        this.themeScene = new Scene(root);
    }

    /**
     * update and display the user text when given a new input!
     * if the input is an empty string, delete one line
     * if the input is one space, clear the userinput
     */
    public void updateUserText(String input){
        // if string = DEL, means delete button was called
        if (Objects.equals(input, "DEL")){
            // check if string is non empty
            if (!userInput.getText().isEmpty()){
                String text = userInput.getText().substring(0, userInput.getText().length() - 1);
                userInput.setText(text);
            }
        } else if (Objects.equals(input, "CLEAR")) { // clear was clicked
            userInput.setText("");

        } else{ // input defined = number or operation was clicked
            String text = userInput.getText();
            userInput.setText(text + input);
        }
    }

    /**
     * Handle for equal button
     * send the userInput text to the controller
     */
    public void sendText() {
        // pass
    }
}