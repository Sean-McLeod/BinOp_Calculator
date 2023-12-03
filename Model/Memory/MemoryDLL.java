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

    public void delete() {
        if (!isEmpty()) {
            if ((current.next == null) && (current.prev == null)) {
                // when the target node is the only node in the memoryDLL
                first = null;
                last = null;
                current = null;
            } else if (current.next == null) {
                // when the target node is the last node in the memoryDLL
                current.prev.next = current.next;
                current = current.prev;
                last = current;
            } else if (current.prev == null) {
                // when the target node is the first node in the memoryDLL
                current.next.prev = current.prev;
                current = current.next;
                first = current;
            } else {
                // when the target node is somewhere in the middle of the memoryDLL
                current.prev.next = current.next;
                current.next.prev = current.prev;
                current = current.next;
            }
        }
    }

    public boolean isEmpty() {
        return first == null;
    }
}
