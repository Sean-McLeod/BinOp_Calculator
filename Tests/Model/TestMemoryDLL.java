package Tests.Model;

import org.junit.jupiter.api.Test;
import Model.Memory.MemoryDLL;
import Model.Memory.MemoryNode;
import Model.Expressions.BinOp;
import Model.Expressions.Value;
import Model.CalculatorModel;

import static org.junit.jupiter.api.Assertions.*;

public class TestMemoryDLL {
    @Test
    void testDelete() {
        CalculatorModel model = new CalculatorModel();
        assertNull(model.ExecuteAction("DELETE"));  // case where there are no nodes
        String myExpr1 = "1+1";
        String myExpr2 = "1+2";
        String myExpr3 = "1+3";

        assertEquals("2.0", model.ExecuteAction(myExpr1));  // add one node
        assertNull(model.ExecuteAction("DELETE"));  // case where there is only one node

        assertEquals("2.0", model.ExecuteAction(myExpr1));  // add one node
        assertEquals("3.0", model.ExecuteAction(myExpr2));  // add one node
        assertEquals("4.0", model.ExecuteAction(myExpr3));  // add one node

        assertEquals("(1.0+2.0) = 3.0", model.ExecuteAction("NEXT"));
        assertEquals("(1.0+3.0) = 4.0", model.ExecuteAction("DELETE"));  // case where selected node is in between

        assertEquals("(1.0+1.0) = 2.0", model.ExecuteAction("DELETE"));  // case where this node is the last node

        assertNull(model.ExecuteAction("DELETE"));  // case where this node is the last node
    }

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
