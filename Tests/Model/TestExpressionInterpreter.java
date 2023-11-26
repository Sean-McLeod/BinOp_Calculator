package Tests.Model;

import Model.ExpressionInterpreter;
import Model.Expressions.BinOp;
import Model.Expressions.Value;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestExpressionInterpreter {

    @Test
    void testComplexExpressions() {
        ExpressionInterpreter expressionInterpreter = new ExpressionInterpreter();
        String expression = "1+1*2+1";
        assertEquals(expressionInterpreter.InterpretExpression(expression).Evaluate(), 4);
        expression = "1*1*2*1";
        assertEquals(expressionInterpreter.InterpretExpression(expression).Evaluate(), 2);
        expression = "1-1-2-1";
        assertEquals(expressionInterpreter.InterpretExpression(expression).Evaluate(), -3.0);
        expression = "1+1+2+1";
        assertEquals(expressionInterpreter.InterpretExpression(expression).Evaluate(), 5);
        expression = "1*1/2+1";
        assertEquals(expressionInterpreter.InterpretExpression(expression).Evaluate(), 1.5);
        expression = "2^2^2";
        assertEquals(expressionInterpreter.InterpretExpression(expression).Evaluate(), 16);
        assertEquals(expressionInterpreter.InterpretExpression(expression).StringRepresentation(), "((2.0^2.0)^2.0)");
    }

    @Test
    void testBasicPower() {
        ExpressionInterpreter expressionInterpreter = new ExpressionInterpreter();
        String expression = "3^2";
        assertEquals(expressionInterpreter.InterpretExpression(expression).Evaluate(), 9);
    }

    @Test
    void testBasicAddition() {
        ExpressionInterpreter expressionInterpreter = new ExpressionInterpreter();
        String expression = "1+1";
        assertEquals(expressionInterpreter.InterpretExpression(expression).Evaluate(), 2.0);
    }
    @Test
    void testBasicMultiplication() {
        ExpressionInterpreter expressionInterpreter = new ExpressionInterpreter();
        String expression = "1*1";
        assertEquals(expressionInterpreter.InterpretExpression(expression).Evaluate(), 1);
    }
    @Test
    void testBasicDivision() {
        ExpressionInterpreter expressionInterpreter = new ExpressionInterpreter();
        String expression = "2/1";
        assertEquals(expressionInterpreter.InterpretExpression(expression).Evaluate(), 2);
    }
    @Test
    void testBasicSubstraction() {
        ExpressionInterpreter expressionInterpreter = new ExpressionInterpreter();
        String expression = "1-1";
        assertEquals(expressionInterpreter.InterpretExpression(expression).Evaluate(), 0);
    }
}
