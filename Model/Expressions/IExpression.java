package Model.Expressions;

/**
 * Interface IExpression
 * Represents an expression, which can be either a Value or BinOp
 */
public interface IExpression {
    /**
     * Evaluates IExpression
     */
    Double Evaluate();

    /**
     * returns a string representation
     */
    String StringRepresentation();
}
