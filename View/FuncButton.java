package View;

public class FuncButton extends CustomButton{
    public FuncButton(String text) {
        super(text);
    }

    @Override
    public String setUpEventHandler() {
        // when clicked, return the value of the button
        // will change later!
        return this.getText();
    }
}
