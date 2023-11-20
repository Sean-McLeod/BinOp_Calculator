package Tests.Model;

import Model.Expressions.BinOp;
import Model.Expressions.IExpression;
import Model.Expressions.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBinOp {
    @Test
    void testBasicAddition() {
        Value left = new Value(5.0);
        Value right = new Value(5.0);
        BinOp operation = new BinOp(left, "+", right);
        assertEquals(operation.Evaluate(), 10.0);
    }

    @Test
    void testBasicMultiplication() {
        Value left = new Value(5.0);
        Value right = new Value(5.0);
        BinOp operation = new BinOp(left, "*", right);
        assertEquals(operation.Evaluate(), 25.0);
    }

    @Test
    void testBasicDivision() {
        Value left = new Value(5.0);
        Value right = new Value(5.0);
        BinOp operation = new BinOp(left, "/", right);
        assertEquals(operation.Evaluate(), 1.0);
    }

    @Test
    void testBasicPower() {
        Value left = new Value(5.0);
        Value right = new Value(5.0);
        BinOp operation = new BinOp(left, "^", right);
        assertEquals(operation.Evaluate(), 3125.0);
    }

    @Test
    void testBasicRoot() {
        Value left = new Value(4.0);
        Value right = new Value(2.0);
        BinOp operation = new BinOp(left, "$", right);
        assertEquals(operation.Evaluate(), 2.0);
    }

    @Test
    void testExpressionEvaluation() {
        IExpression expression = new BinOp(new Value(3.0), "+", new BinOp(new Value(2.0), "^", new Value(2.0)));
        assertEquals(expression.Evaluate(), 7.0);
    }

    // Test expception scenerios
}
