package Tests.Model;
import Model.CalculatorModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCalculatorModel {

    @Test
    void testEnterAction() {
        CalculatorModel myModel = new CalculatorModel();
        myModel.ExecuteAction("1+1");
        String x = myModel.ExecuteAction("ENTER");
        double xDouble = Double.parseDouble(x);
        assertEquals(2.0, xDouble);

        myModel.ExecuteAction("ENTER");
        myModel.ExecuteAction("ENTER");
        x = myModel.ExecuteAction("ENTER");
        xDouble = Double.parseDouble(x);
        assertEquals(2.0, xDouble);
    }

    @Test
    void testDeleteAction() {
        CalculatorModel myModel = new CalculatorModel();
        myModel.ExecuteAction("2+2");

        myModel.ExecuteAction("3+3");
        String x = myModel.ExecuteAction("DELETE");
        assertEquals(x, "(3.0+3.0)");
        myModel.ExecuteAction("4+4");
        x = myModel.ExecuteAction("DELETE");
        assertEquals(x, "(4.0+4.0)");
    }

    @Test
    void testNextBackAction() {
        CalculatorModel myModel = new CalculatorModel();
        myModel.ExecuteAction("2+2");
        myModel.ExecuteAction("3+3");
        myModel.ExecuteAction("4+4");
        myModel.ExecuteAction("2^7");
        String x = myModel.ExecuteAction("NEXT");
        assertEquals(x, "(3.0+3.0)");
        x = myModel.ExecuteAction("BACK");
        assertEquals(x, "(2.0+2.0)");
        myModel.ExecuteAction("NEXT");
        myModel.ExecuteAction("NEXT");
        x = myModel.ExecuteAction("NEXT");
        assertEquals(x, "(2.0^7.0)");

        myModel.ExecuteAction("BACK");
        x = myModel.ExecuteAction("BACK");
        assertEquals(x, "(3.0+3.0)");
    }
}
