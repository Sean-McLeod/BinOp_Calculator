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
    public double Evaluate() {
        if (operation.equals("+")) {
            return 0;
        } else if (operation.equals("-")) {
            return 0;
        }
        else if (operation.equals("/")) {
            return 0;
        }
        else if (operation.equals("*")) {
            return 0;
        }
        else if (operation.equals("^") || operation.equals("$")) {
            return 0;
        }
        else {
            return 0;
        }
    }
}
