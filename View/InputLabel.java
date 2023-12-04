package View;

import javafx.scene.control.Label;

public class InputLabel extends Label {
    public int fontSize; //font size of label text
    public String fontColour; //font colour of label text
    public String backColour; //background colour of label

    public InputLabel(){
        super();
        setLabelStyle("#393e46", 15, "white");
    }
    public InputLabel(String text){
        super(text);
        setLabelStyle("#393e46", 15, "white");
    }

    /**
     * set font size, font colour, and background colour of the input label
     */
    public void setLabelStyle(String backColour, int fontSize, String fontColour){
        this.fontColour = fontColour;
        this.fontSize = fontSize;
        this.backColour = backColour;
        setPrefSize(340, 70);
        setStyle("-fx-font-family: 'Monaco'; -fx-font-size:"+fontSize+"; -fx-alignment: center-right; -fx-padding: 15px;" +
                "-fx-background-color:"+backColour+"; -fx-background-radius: 10px; -fx-text-fill:"+fontColour+";");
    }

    /**
     * increase font size of the label text in increments of 5
     */
    public void increaseFontSize(){
        fontSize = (fontSize < 25) ? fontSize + 5 : 15;
        setStyle("-fx-font-family: 'Monaco'; -fx-font-size:"+fontSize+"; -fx-alignment: center-right; -fx-padding: 15px;" +
                "-fx-background-color:"+backColour+"; -fx-background-radius: 10px; -fx-text-fill:"+fontColour+";");
    }
}
