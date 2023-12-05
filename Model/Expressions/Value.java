package Model.Expressions;

/**
 * Class Value
 * Implements the IExpression Interface
 */
public class Value implements IExpression
{
    private double value;

    /**
     * Value constructor
     * Initializes attribute
     */
    public Value(double value) {
        this.value = value;
    }

    /**
     * Evaluates value
     */
    @Override
    public Double Evaluate() {
        return this.value;
    }

    /**
     * returns a string representation of the value
     */
    @Override
    public String StringRepresentation() {
        return "" + value;
    }

    /**
     * returns a string representation of the value
     */
    @Override
    public String toString() {
        return "" + this.value;
    }
}
