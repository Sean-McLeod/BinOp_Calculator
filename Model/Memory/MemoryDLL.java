package Model.Memory;

import Model.Expressions.IExpression;


public class MemoryDLL {
    private MemoryNode first;
    private MemoryNode last;
    private MemoryNode current;

    public MemoryDLL() {
        this.first = null;
        this.last = null;
        this.current = null;
    }

    public IExpression getCurrent() {
        return current.getExpression();
    }

    public void moveForward() {
        if (current != null && current.next != null) {
            current = current.next;
        }
    }

    public void moveBackward() {
        if (current != null && current.prev != null) {
            current = current.prev;
        }
    }

    public void addToBack(IExpression data) {
        MemoryNode newNode = new MemoryNode(data);
        if (isEmpty()) {
            first = newNode;
            current = newNode;
            last = newNode;
        } else {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }
}
