package Model.Memory;

import Model.Memory.MemoryNode;
import Model.Expressions.IExpression;

import java.beans.Expression;


public class MemoryDLL {

    private MemoryNode head;
    private MemoryNode tail;

    public MemoryDLL() {
        this.head = null;
        this.tail = null;
    }

    public void InsertAtFront(IExpression expression) {
        MemoryNode newNode = new MemoryNode(expression);

        if (head == null) {
            // List is empty, set both head and tail to the new node
            head = newNode;
            tail = newNode;
        } else {
            // Add the new node to the front of the list
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public IExpression traverseToIndex(int index) {
        if (head == null) {
            throw new IndexOutOfBoundsException("");
        }

        MemoryNode current = head;
        int count = 0;

        while (current != null && count < index) {
            current = current.next;
            count++;
        }

        if (current == null) {
            throw new IndexOutOfBoundsException("");
        } else {
            return current.getExpression();
        }
    }
}
