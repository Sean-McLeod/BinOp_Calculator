package View.Buttons;

public class NumOpButton extends CustomButton {

    public NumOpButton(String text) {
        super(text);
        setUpEventHandler();
    }

    public String setUpEventHandler() {
        // when clicked, return the value of the button
        // will change later
        return this.getText();
    }
}
