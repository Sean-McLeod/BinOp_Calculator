package View;

import View.Buttons.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class CalculatorView {
    public Stage stage; // this is the window
    public Scene calcScene; // scene for calculator page
    public Scene themeScene; // scene of themes page
    public Scene historyScene; // scene of history traversal page
    private GridPane gridPane; // gridpane holding calculator buttons
    public InputLabel userInput; // to display the user's input
    public InputLabel historyLabel; //to display history of expressions
    public ArrayList<NumOpButton> numberButtons;
    public ArrayList<NumOpButton> operationButtons;
    public ArrayList<FuncButton> funcButtons; // prev button, next button, change mode button
    public ArrayList<CustomButton> allButtons; // list containing all button objects;
    private String currentMarker;

    public CalculatorView(Stage stage) throws IOException {
        this.stage = stage;
        stage.setHeight(600);
        stage.setWidth(500);
        // set user label -- will be empty at first
        this.userInput = new InputLabel();
        this.historyLabel = new InputLabel();

        this.numberButtons = new ArrayList<NumOpButton>(); // list to hold number buttons
        this.operationButtons = new ArrayList<NumOpButton>(); // list to hold operation buttons
        this.funcButtons = new ArrayList<FuncButton>(); // list to hold functionality buttons
        allButtons = new ArrayList<CustomButton>(); // list to hold functionality buttons

        this.currentMarker = "";  // our current place in the saved.txt file, that is to be created

        initUI(); // show the gaphics
    }

    /**
     * build the GUI!
     */

    public void initUI() throws IOException {
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
        NumOpButton equal = new NumOpButton("="); allButtons.add(equal);
        // add an event to all the buttons (define what they do when they're clicked)
        for (Button button: operationButtons){
            button.setOnAction(mouseEvent -> updateUserText(button.getText()));
        }
        // handle for deleting text
        del.setOnAction(mouseEvent -> updateUserText("DEL"));
        // handle for eq button
        equal.setOnAction(mouseEvent -> {
            try {
                equalButtonHandle();
            } catch (FileNotFoundException e) { // in case the file doesnt exist
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

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
        createHistoryScene(); // create history scene in advance
        FuncButton historyButton = new FuncButton("History"); allButtons.add(historyButton); funcButtons.add(historyButton);
        historyButton.setPrefWidth(110);
        historyButton.setPrefHeight(50);
        funcPane.getChildren().add(historyButton);
        //historyButton handler
        historyButton.setOnAction(event -> {
            try {
                historyHandle();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //font size button
        FuncButton fontSizeButton = new FuncButton("Size"); allButtons.add(fontSizeButton); funcButtons.add(fontSizeButton);
        fontSizeButton.setPrefWidth(110);
        fontSizeButton.setPrefHeight(50);
        funcPane.getChildren().add(fontSizeButton);
        //font size button handler
        fontSizeButton.setOnAction(event -> {
            for (CustomButton b: allButtons) {b.increaseFontSize();}
            userInput.increaseFontSize();
            historyLabel.increaseFontSize();});

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
        backButton.setOnAction(event -> stage.setScene(calcScene));

        //add buttons to screen
        root.getChildren().add(darkTheme);
        root.getChildren().add(lightTheme);
        root.getChildren().add(forestTheme);
        root.getChildren().add(earthTheme);
        root.getChildren().add(oceanTheme);
        root.getChildren().add(backButton);

        this.themeScene = new Scene(root);
    }

    // BUTTON HANDLERS:

    /** Handle for the "NumOp", "del" and "clear" buttons.
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
     * save the input to a file that can be accessed through history?
     */
    public void equalButtonHandle() throws IOException{
        // write to file
        if (!new File("View/saved.txt").exists()){
            new File("View/saved.txt").createNewFile(); // creates the file if it does not exist
        }
        try{
            File file = new File("View/saved.txt");
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            if (file.length() == 0){
                bufferedWriter.write(userInput.getText()); // write directly
            }
            else{
                bufferedWriter.write("\n"); // new line
                bufferedWriter.write(userInput.getText()); // write userInput to file
            }
            bufferedWriter.close(); // close file
        } catch (IOException e) { // buffered reader exception
            throw new RuntimeException(e);
        }
        // send to MODEL
    }

    public void historyHandle() throws IOException {
        stage.setScene(historyScene); // switch to History page
        // if there is an input saved, display the first input from "saved.txt"
        if (new File("View/saved.txt").exists() && new File("View/saved.txt").length() > 0) {
            try {
                FileReader reader = new FileReader("View/saved.txt");
                BufferedReader bufferedReader = new BufferedReader(reader);
                bufferedReader.mark(0);
                // read input from the file
                // catch exceptions incase the file or directory doesnt exist!!!
                bufferedReader.reset();
                String newLabel = bufferedReader.readLine();
                currentMarker = newLabel;
                this.historyLabel.setText(newLabel);
                reader.close();
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        else{ // create new file and display empty textbox
            new File("View/saved.txt").createNewFile();
            this.historyLabel.setText("");
        }
    }

    /**
     * Handle for del button in "History" menu
     * delete the current input from the file
     */
    public void delHandle() throws FileNotFoundException {
        // pass
    }

    /**
     * Handle for prev button in "History" menu
     * go to the prev line in the file
     */
    public void prevHandle() throws IOException {
        // if there is an input saved, go through the file until marker is reached
        if (new File("View/saved.txt").exists() && new File("View/saved.txt").length() > 0) {
            try{
                File f = new File("View/saved.txt"); // create or find saved input file
                // read input from the file
                // catch exceptions incase the file or directory doesnt exist!!!
                FileReader reader = new FileReader(f.getPath());
                BufferedReader bufferedReader = new BufferedReader(reader);
                // skip until before the current marker reached, then display the current line
                String curr = bufferedReader.readLine(); // current string
                String next = bufferedReader.readLine();;  // the next line after current string
                if (!Objects.equals(curr, currentMarker)){ // curr = currentMarker means there is no previous
                    while (!Objects.equals(next, currentMarker)){
                        curr = next;
                        next = bufferedReader.readLine();
                    }
                }
                currentMarker = curr;
                if (currentMarker != null){ // input saved is valid, and not the end of the file
                    this.historyLabel.setText(currentMarker);
                } // we dont wanna display an empty string so we keep the input as it is
                bufferedReader.close(); // close file
            } catch (IOException e){
                throw new RuntimeException(e);
            }

        }
    }

    /**
     * Handle for next button in "History" menu
     * go to the next input in the file
     */
    public void nextHandle() throws IOException {
        // if there is an input saved, display the first input from "saved.txt"
        if (new File("View/saved.txt").exists() && new File("View/saved.txt").length() > 0) {
            try{
                File f = new File("View/saved.txt"); // create or find saved input file
                // read input from the file
                // catch exceptions incase the file or directory doesnt exist!!!
                FileReader reader = new FileReader(f.getPath());
                BufferedReader bufferedReader = new BufferedReader(reader);
                // skip until current marker reached, then display the next line
                String next = bufferedReader.readLine();
                while (!Objects.equals(next, currentMarker)){
                    next = bufferedReader.readLine();
                }
                currentMarker = bufferedReader.readLine();
                if (currentMarker != null){ // input saved is valid, and not the end of the file
                    this.historyLabel.setText(currentMarker);
                } // we dont wanna display an empty string so we keep the input as it is
                bufferedReader.close(); // close file
            } catch (IOException e){
                throw new RuntimeException(e);
            }

        }
    }

    // TODO: Create a "select" button that selects a saved input and sees its result
    public void selectHandle() throws FileNotFoundException {
        // pass
    }

    /**
     * Create scene for history traversal
     */
    public void createHistoryScene() throws IOException {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(30);

        //label to view history of expressions

        historyHandle();

        root.getChildren().add(historyLabel);

        //history traversal buttons to check out previous and next nodes, delete a node
        FuncButton prevButton = new FuncButton("Prev"); allButtons.add(prevButton); funcButtons.add(prevButton);
        prevButton.setPrefWidth(110);
        prevButton.setPrefHeight(50);
        prevButton.setOnAction(event -> { // set prev button handler, may throw exception
            try {
                prevHandle();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        FuncButton nextButton = new FuncButton("Next"); allButtons.add(nextButton); funcButtons.add(nextButton);
        nextButton.setPrefWidth(110);
        nextButton.setPrefHeight(50);
        nextButton.setOnAction(event -> { // set next button handler, may throw exception
            try {
                nextHandle();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        FuncButton delButton = new FuncButton("Del."); allButtons.add(delButton); funcButtons.add(delButton);
        delButton.setPrefWidth(110);
        delButton.setPrefHeight(50);

        //back to calculator button
        FuncButton backButton = new FuncButton("←"); allButtons.add(backButton); funcButtons.add(backButton);
        backButton.setOnAction(event -> stage.setScene(calcScene));
        backButton.setPrefWidth(110);
        backButton.setPrefHeight(50);

        root.getChildren().add(prevButton);
        root.getChildren().add(nextButton);
        root.getChildren().add(delButton);
        root.getChildren().add(backButton);

        this.historyScene = new Scene(root);
    }
}