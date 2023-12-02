import Model.CalculatorModel;
import View.CalculatorView;
import javafx.application.Application;
import javafx.stage.Stage;

public class CalculatorController extends Application
{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        CalculatorView display = new CalculatorView(stage);
        CalculatorModel model = new CalculatorModel();
        // request = display.MakeARequest
        // output = model.ExectuteRequest(request)
        // view.execture(output)
    }
}
