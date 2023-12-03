package Model;

import Model.Expressions.IExpression;
import Model.Memory.MemoryDLL;

import java.lang.reflect.Array;

public class CalculatorModel
{
    private ExpressionInterpreter interpreter = new ExpressionInterpreter();
    private MemoryDLL memory = new MemoryDLL();

    public String ExecuteAction(String userInput) {
        if (userInput.equals("ENTER")) {
            return "" + memory.getCurrent().Evaluate();
        } else if (userInput.equals("DELETE")) {
            memory.delete();
            if (memory.isEmpty()) {
                return null;
            }
            return memory.getCurrent().StringRepresentation() + " = " + memory.getCurrent().Evaluate();
        } else if (userInput.equals("NEXT")) {
            memory.moveForward();
            if (memory.isEmpty()) {
                return null;
            }
            return memory.getCurrent().StringRepresentation() + " = " + memory.getCurrent().Evaluate();
        } else if (userInput.equals("PREV")) {
            memory.moveBackward();
            if (memory.isEmpty()) {
                return null;
            }
            return memory.getCurrent().StringRepresentation() + " = " + memory.getCurrent().Evaluate();
        }
        else {
            IExpression expression  = interpreter.InterpretExpression(userInput);
            memory.addToBack(expression);
            return "" + expression.Evaluate();
        }
    }

}
