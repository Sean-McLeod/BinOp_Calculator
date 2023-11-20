package Model.Expressions;


public class BinOp implements IExpression {
    protected IExpression leftExpression;
    protected IExpression rightExpression;
    private String operation;

    public BinOp(IExpression left, String op, IExpression right) {
        this.leftExpression = left;
        this.operation = op;
        this.rightExpression = right;
    }


    @Override
    public Double Evaluate() {
        if (operation.equals("+")) {
            return Add(leftExpression.Evaluate(), rightExpression.Evaluate());
        } else if (operation.equals("-")) {
            return Subtract(leftExpression.Evaluate(), rightExpression.Evaluate());
        }
        else if (operation.equals("/")) {
            return Divide(leftExpression.Evaluate(), rightExpression.Evaluate());
        }
        else if (operation.equals("*")) {
            return Multiply(leftExpression.Evaluate(), rightExpression.Evaluate());
        }
        else if (operation.equals("^")) {
            return Power(leftExpression.Evaluate(), rightExpression.Evaluate());
        }
        else if (operation.equals("$")) {
            return Root(leftExpression.Evaluate(), rightExpression.Evaluate());
        }
        return null;
    }

    private Double Add(double left, double right) {
        return left + right;
    }
    private Double Subtract(double left, double right) {
        return left - right;
    }

    private Double Divide(Double left, Double right) {
        try {
            return left / right;
        }
        catch (ArithmeticException e) {
            return null;
        }
    }

    private Double Multiply(double left, double right) {
        try {
            return left * right;
        }
        catch (ArithmeticException e) {
            return null;
        }
    }

    private Double Power(double left, double right) {
        try {
            return Math.pow(left, right);
        }
        catch (ArithmeticException e) {
            return null;
        }
    }

    private Double Root(double left, double right) {
        try {
            return Math.pow(left, 1 / right);
        }
        catch (ArithmeticException e) {
            return null;
        }
    }
}
