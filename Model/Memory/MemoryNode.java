package Model.Memory;

import Model.Expressions.IExpression;

public class MemoryNode {
    private IExpression expression;
    public MemoryNode next;
    public MemoryNode prev;

    /***
     * Intilize a node object which stores that IExpression that was inputed into the
     * memory (DLL)
     * @param expression A valid IExpression the ExpressionInterpter had intrepreted
     */
    public MemoryNode(IExpression expression) {
        this.expression = expression;
        this.next = null;
        this.prev = null;
    }

    public IExpression getExpression() {return this.expression;}
}
