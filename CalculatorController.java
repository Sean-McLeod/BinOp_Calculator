import Model.CalculatorModel;
import View.CalculatorView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Objects;

public class CalculatorController extends Application
{
    public static void main(String[] args) {
        launch(args);
    }

    /***
     * Run program fucntion which intilizes and the model and view of the program
     * @param stage A valid stage object
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        CalculatorModel model = new CalculatorModel();
        CalculatorView display = new CalculatorView(stage, model);
    }
}
