package View.Buttons;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class CustomButton extends Button {
    public String originalColour; //original colour of button in CSS
    private int fontSize = 15; //font size of button text

    public CustomButton(String text) {
        super(text);
        setDefaultStyle();
        highlightButton();
    }

    public void setDefaultStyle(){
        setPrefWidth(60);
        setPrefHeight(60);
        setTextFill(Color.WHITE);
        setFont(Font.font(fontSize));
        setOriginalColour("#0092ca");
    }

    /**
     * accessibility feature. Highlight buttons with clicked and hovered.
     */
    public void highlightButton(){
        setOnMousePressed(event ->
                setStyle("-fx-font-size: " + fontSize + "; -fx-background-color: orange; -fx-background-radius: 10px"));
        setOnMouseReleased(event ->
                setOriginalColour(originalColour));
        setOnMouseEntered(event -> {
            setOpacity(0.8);
            setStyle("-fx-border-color: orange; -fx-border-width: 2px; -fx-background-color:" + originalColour + ";");});
        setOnMouseExited(event -> {
            setOpacity(1.0);
            setOriginalColour(originalColour);});
    }

    /**
     * increase font size of the label text in increments of 5
     */
    public void increaseFontSize(){
        fontSize = (fontSize < 25) ? fontSize + 5 : 15;
        setStyle("-fx-font-size: " + fontSize + "; -fx-background-color: " + originalColour + "; -fx-background-radius: 10px");
    }

    public void setOriginalColour(String colour){
        this.originalColour = colour;
        setStyle("-fx-font-size: " + fontSize + "; -fx-background-color: " + originalColour + "; -fx-background-radius: 10px");
        highlightButton();
    }

}
