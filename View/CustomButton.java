package View;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class CustomButton extends Button {
    public String originalColour; //original colour of button in CSS

    public CustomButton(String text) {
        super(text);
        setDefaultStyle();
        highlightButton();
    }

    public void setDefaultStyle(){
        setPrefWidth(60);
        setPrefHeight(60);
        setTextFill(Color.WHITE);
        setFont(Font.font(20));
        setOriginalColour("#0092ca");
        }

    public void highlightButton(){
        setOnMousePressed(event ->
                setStyle("-fx-background-color: orange;"));
        setOnMouseReleased(event ->
                setOriginalColour(originalColour));
        setOnMouseEntered(event -> {
            setOpacity(0.8);
            setStyle("-fx-border-color: orange; -fx-border-width: 2px; -fx-background-color:" + originalColour + ";");});
        setOnMouseExited(event -> {
            setOpacity(1.0);
            setOriginalColour(originalColour);});
    }

    public void increaseFontSize(){
        double newSize = getFont().getSize() + 5;
        setFont(new Font(newSize));
    }

    public void setOriginalColour(String colour){
        this.originalColour = colour;
        setStyle("-fx-background-color:"+ colour +";-fx-background-radius: 10px;");
        highlightButton();
    }

    public abstract String setUpEventHandler();

}