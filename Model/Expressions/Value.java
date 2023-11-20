package Model.Expressions;

public class Value implements IExpression
{
    private double value;

    public Value(double value) {
        this.value = value;
    }

    @Override
    public Double Evaluate() {
        return this.value;
    }

    @Override
    public String toString() {
        return "" + this.value;
    }
}
