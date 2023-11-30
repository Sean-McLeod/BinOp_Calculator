package Tests.Model;

import org.junit.jupiter.api.Test;
import Model.Memory.MemoryDLL;
import Model.Memory.MemoryNode;
import Model.Expressions.BinOp;
import Model.Expressions.Value;

import static org.junit.jupiter.api.Assertions.*;

public class TestMemoryDLL {

    @Test
    void testTrackCurrent() {
        MemoryDLL myMemory = new MemoryDLL();
        BinOp myExpr = new BinOp(new Value(3), "+", new Value(3));
        myMemory.addToBack(myExpr);

        assertEquals(new BinOp(new Value(3), "+", new Value(3)).StringRepresentation(), myMemory.getCurrent().StringRepresentation());
        assertFalse(myMemory.isEmpty());

        myExpr = new BinOp(new Value(3), "*", new Value(4));
        myMemory.addToBack(myExpr);
        myExpr = new BinOp(new Value(3), "/", new Value(5));
        myMemory.addToBack(myExpr);
        myExpr = new BinOp(new Value(3), "*", new Value(6));
        myMemory.addToBack(myExpr);
        myExpr = new BinOp(new Value(3), "/", new Value(7));
        myMemory.addToBack(myExpr);

        assertEquals(new BinOp(new Value(3), "+", new Value(3)).StringRepresentation(), myMemory.getCurrent().StringRepresentation());
        assertFalse(myMemory.isEmpty());
    }

    @Test
    void testAddToBack() {
        MemoryDLL myMemory = new MemoryDLL();
        BinOp myExpr = new BinOp(new BinOp(new Value(1), "/", new Value(5)), "+", new Value(7));
        myMemory.addToBack(myExpr);

        assertEquals(new BinOp(new BinOp(new Value(1), "/", new Value(5)), "+", new Value(7)).StringRepresentation(), myMemory.getCurrent().StringRepresentation());

        myExpr = new BinOp(new Value(3), "*", new Value(4));
        myMemory.addToBack(myExpr);
        myMemory.moveForward();

        assertEquals(new BinOp(new Value(3), "*", new Value(4)).StringRepresentation(), myMemory.getCurrent().StringRepresentation());

        myMemory.moveBackward();

        assertEquals(new BinOp(new BinOp(new Value(1), "/", new Value(5)), "+", new Value(7)).StringRepresentation(), myMemory.getCurrent().StringRepresentation());
    }
}
