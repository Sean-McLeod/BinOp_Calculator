package Model.Memory;

import Model.Expressions.IExpression;

public class MemoryNode {
    private IExpression expression;
    public MemoryNode next;
    public MemoryNode prev;

    public MemoryNode(IExpression expression) {
        this.expression = expression;
        this.next = null;
        this.prev = null;
    }

    public IExpression getExpression() {return this.expression;}
}
