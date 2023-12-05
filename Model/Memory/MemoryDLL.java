package Model.Memory;

import Model.Expressions.IExpression;


/**
 * Class MemoryDLL
 * This class represents a doubly linked list where it's nodes represent memories of user input or expressions
 */
public class MemoryDLL {
    private MemoryNode first;
    private MemoryNode last;
    private MemoryNode current;

    /**
     * MemoryDLL constructor
     * Initializes attributes
     */
    public MemoryDLL() {
        this.first = null;
        this.last = null;
        this.current = null;
    }

    /**
     * getter
     * returns current expression
     */
    public IExpression getCurrent() {
        return current.getExpression();
    }

    /**
     * traverses forward in memory
     */
    public void moveForward() {
        if (current != null && current.next != null) {
            current = current.next;
        }
    }

    /**
     * traverses backwards in memory
     */
    public void moveBackward() {
        if (current != null && current.prev != null) {
            current = current.prev;
        }
    }

    /**
     * add IExpression to the end of the MemoryDLL
     */
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

    /**
     * deletes the current expression
     */
    public void delete() {
        if (!isEmpty()) {
            if ((current.next == null) && (current.prev == null)) {
                // when the current node is the only node in the memoryDLL
                first = null;
                last = null;
                current = null;
            } else if (current.next == null) {
                // when the current node is the last node in the memoryDLL
                current.prev.next = current.next;
                current = current.prev;
                last = current;
            } else if (current.prev == null) {
                // when the current node is the first node in the memoryDLL
                current.next.prev = current.prev;
                current = current.next;
                first = current;
            } else {
                // when the current node is somewhere in the middle of the memoryDLL
                current.prev.next = current.next;
                current.next.prev = current.prev;
                current = current.next;
            }
        }
    }

    /**
     * checks if the MemoryDLL is empty or not
     */
    public boolean isEmpty() {
        return first == null;
    }
}
