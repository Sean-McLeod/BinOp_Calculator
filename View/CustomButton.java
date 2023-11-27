package View;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class CustomButton extends Button {

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
        setStyle("-fx-background-color: green; -fx-background-radius: 5px;");    }

    public void highlightButton(){
        setOnMousePressed(event ->
                setStyle("-fx-background-color: orange;"));
        setOnMouseReleased(event ->
                setStyle("-fx-background-color: green;"));
        setOnMouseEntered(event -> {
            setOpacity(0.8);
            setStyle("-fx-border-color: orange; -fx-border-width: 2px; -fx-background-color: green;");});
        setOnMouseExited(event -> {
            setOpacity(1.0);
            setStyle("-fx-background-color: green;");});
    }

    public void increaseFontSize(){
        double newSize = getFont().getSize() + 5;
        setFont(new Font(newSize));
    }

    public abstract String setUpEventHandler();

}