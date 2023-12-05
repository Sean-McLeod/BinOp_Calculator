package Model;

import Model.Expressions.IExpression;
import Model.Memory.MemoryDLL;

import java.lang.reflect.Array;

/**
 * Class CalculatorModel
 * Responsible for the core processing of a calculator
 */
public class CalculatorModel
{
    private ExpressionInterpreter interpreter = new ExpressionInterpreter();
    private MemoryDLL memory = new MemoryDLL();


    /**
     * Handle/Execute user requests by taking the request string defined as userInput
     * and returning the corresponding output
     * Ex: userInput = '5+5' ==> '10'
     * */
    public String ExecuteAction(String userInput) {
        // Execute Enter request
        if (userInput.equals("ENTER")) {
            return "" + memory.getCurrent().Evaluate();
        }
        else if (userInput.equals("DELETE")) // Execute delete current expression of the memeory request
        {
            memory.delete();
            if (memory.isEmpty()) {
                return null;
            }
            return memory.getCurrent().StringRepresentation() + " = " + memory.getCurrent().Evaluate();
        }
        else if (userInput.equals("NEXT")) // Execute traverse to next expression in the memory request
        {
            memory.moveForward();
            if (memory.isEmpty()) {
                return null;
            }
            return memory.getCurrent().StringRepresentation() + " = " + memory.getCurrent().Evaluate();
        }
        else if (userInput.equals("PREV")) // Execute traverse to previous expression in the memory request
        {
            memory.moveBackward();
            if (memory.isEmpty()) {
                return null;
            }
            return memory.getCurrent().StringRepresentation() + " = " + memory.getCurrent().Evaluate();
        }
        else // Execute evaluate expression provided in userInput
        {
            IExpression expression  = interpreter.InterpretExpression(userInput);
            memory.addToBack(expression);
            return "" + expression.Evaluate();
        }
    }

}
