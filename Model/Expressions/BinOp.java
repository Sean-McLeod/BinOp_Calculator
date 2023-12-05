package Model.Expressions;

/**
 * Class BinOp
 * Implements the IExpression Interface
 * This class represents the binary operation between two expressions,
 * in a binary tree-like structure
 */
public class BinOp implements IExpression {
    protected IExpression leftExpression;
    protected IExpression rightExpression;
    private String operation;

    /**
     * BinOp constructor
     * Initializes attributes
     */
    public BinOp(String op) {
        this.leftExpression = null;
        this.operation = op;
        this.rightExpression = null;
    }

    /**
     * Alternative BinOp constructor
     * Initializes attributes
     */
    public BinOp(IExpression left, String op, IExpression right) {
        this.leftExpression = left;
        this.operation = op;
        this.rightExpression = right;
    }

    /**
     * Checks if the current BinOp has leaves
     * returns true when there are no leaves
     */
    public boolean hasLeafs() {
        return (this.rightExpression == null && this.leftExpression == null);
    }

    /**
     * returns the String version of the result of the binary operation
     */
    @Override
    public String toString() {
        if (this.hasLeafs()) {return operation;} else {return "" + this.Evaluate();}
    }

    /**
     * Evaluates the binary operation
     * operations include: + (addition), - (subtraction), * (multiplication), / (division), ^ (power)
     */
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

        return null;
    }

    /**
     * returns the String version of the binary operation
     */
    @Override
    public String StringRepresentation() {
        return "(" + leftExpression.StringRepresentation() + operation + rightExpression.StringRepresentation() + ")";
    }

    /**
     * Addition
     */
    private Double Add(double left, double right) {
        return left + right;
    }

    /**
     * Subtraction
     */
    private Double Subtract(double left, double right) {
        return left - right;
    }

    /**
     * Division
     * Catches Exception with invalid input
     */
    private Double Divide(Double left, Double right) {
        try {
            return left / right;
        }
        catch (ArithmeticException e) {
            return null;
        }
    }

    /**
     * Multiplication
     * Catches Exception with invalid input
     */
    private Double Multiply(double left, double right) {
        try {
            return left * right;
        }
        catch (ArithmeticException e) {
            return null;
        }
    }

    /**
     * Power
     * Catches Exception with invalid input
     */
    private Double Power(double left, double right) {
        try {
            return Math.pow(left, right);
        }
        catch (ArithmeticException e) {
            return null;
        }
    }
}
