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
    static public Stage stage; // this is the window
    static public Scene scene; // need to specify root node
    public Scene themeScene; // scene of themes page
    public GridPane gridPane;
    static public Label userInput; // to display the user's input
    public ArrayList<Button> numberButtons;
    public ArrayList<Button> operationButtons;
    public ArrayList<Button> funcButtons; // prev button, next button, change mode button
    static public ArrayList<CustomButton> allButtons = new ArrayList<>(); // list containing all button objects;

    public Display(Stage stage){
        this.stage = stage;
        stage.setHeight(600);
        stage.setWidth(500);
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
        root.setStyle("-fx-background-color: #eeeeee");

        this.userInput.setText("TemporaryText 1234+-/x");
        userInput.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20; -fx-alignment: center-right;" +
                "-fx-padding: 15px;-fx-background-color: #393e46; -fx-background-radius: 10px");
        userInput.setTextFill(Color.WHITE);
        userInput.setPrefSize(340, 70);

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
        NumOpButton num1 = new NumOpButton("1"); allButtons.add(num1);
        NumOpButton num2 = new NumOpButton("2"); allButtons.add(num2);
        NumOpButton num3 = new NumOpButton("3"); allButtons.add(num3);
        NumOpButton num4 = new NumOpButton("4"); allButtons.add(num4);
        NumOpButton num5 = new NumOpButton("5"); allButtons.add(num5);
        NumOpButton num6 = new NumOpButton("6"); allButtons.add(num6);
        NumOpButton num7 = new NumOpButton("7"); allButtons.add(num7);
        NumOpButton num8 = new NumOpButton("8"); allButtons.add(num8);
        NumOpButton num9 = new NumOpButton("9"); allButtons.add(num9);
        NumOpButton num0 = new NumOpButton("0"); allButtons.add(num0);
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
        NumOpButton add = new NumOpButton("+"); allButtons.add(add);
        NumOpButton sub = new NumOpButton("-"); allButtons.add(sub);
        NumOpButton mul = new NumOpButton("x"); allButtons.add(mul);
        NumOpButton div = new NumOpButton("/"); allButtons.add(div);
        NumOpButton pow = new NumOpButton("^"); allButtons.add(pow);
        NumOpButton dec = new NumOpButton("."); allButtons.add(dec);
        FuncButton del = new FuncButton("Del."); allButtons.add(del);
        FuncButton equal = new FuncButton("="); allButtons.add(equal);
        FuncButton parOpen = new FuncButton("("); allButtons.add(parOpen);
        FuncButton parClose = new FuncButton(")"); allButtons.add(parClose);

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

        // HBox node for theme button and history button
        HBox root_2 = new HBox();
        root_2.setSpacing(30);
        root_2.setAlignment(Pos.CENTER);
        root.getChildren().add(root_2);

        //change theme button
        createThemesScene(); // create scene for themes page in advance
        Button changeTheme = new Button("Theme");
        changeTheme.setPrefWidth(100);
        changeTheme.setPrefHeight(50);
        changeTheme.setStyle("-fx-background-color: #393e46; -fx-background-radius: 5px; -fx-font-size:15; -fx-text-fill: white");
        root_2.getChildren().add(changeTheme);
        changeTheme.setOnAction(event -> stage.setScene(themeScene)); //changeThemes button handler

        this.scene = new Scene(root, 500, 1000);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    /**
     * Create themes page
     */
    public void createThemesScene(){
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(30);

        // Various theme buttons
        ThemeButton darkTheme = new ThemeButton("Dark", "black", "grey", "white"); allButtons.add(darkTheme);
        ThemeButton lightTheme = new ThemeButton("Light", "white", "grey", "#c1c1c1"); allButtons.add(lightTheme);
        ThemeButton forestTheme = new ThemeButton("Forest", "#388e3c", "#8bc34a", "#c5f0a4"); allButtons.add(forestTheme);
        ThemeButton earthTheme = new ThemeButton("Earth", "#d3bd9a", "#674f04", "#402a23"); allButtons.add(earthTheme);
        ThemeButton oceanTheme = new ThemeButton("Ocean", "#eeeeee", "#0092ca", "#393e46"); allButtons.add(oceanTheme);

        //button to change scene back to calculator
        Button backButton = new Button("Back to Calculator");
        backButton.setPrefWidth(200);
        backButton.setPrefHeight(50);
        backButton.setStyle("-fx-background-color: #393e46; -fx-background-radius: 5px; -fx-font-size:15; -fx-text-fill: white");
        backButton.setOnAction(event2 -> stage.setScene(scene));

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
     * update the user text when given a new input
     */
    public void updateUserText(String input){
        String text = userInput.getText();
        userInput.setText(text + input);
    }
}