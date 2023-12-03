package Tests.View;

import org.junit.jupiter.api.Test;

/**
 * Test that commands in History button work accordingly, and that FileNotFound
 * Exceptions are handled
 */
public class TestHistory {

    /**
     * Test that text box is empty when the file is empty
     */
    @Test
    public void testHistoryEmpty(){
        // pass
    }

    /**
     * Test that saved inputs file is updates when a new userInput is saved
     */
    @Test
    public void testHistoryPopulated(){
    }

    /**
     * Test that next button updates the text box to the next item in the file
     * Also test that an error doesnt occur when end of file is reached
     */
    @Test
    public void testNext(){
        // pass
    }

    /**
     * Test that prev button updates the text box to the next item in the file
     * Also test that an error doesnt occur when beginning of file is reached
     */
    @Test
    public void testPrev(){
        // pass
    }

    /**
     * Test that Delete command deletes the shown input
     * Also test that an error doesnt occur when the file is now empty
     */
    @Test
    public void testDel(){
        // pass
    }

    /**
     * Test that "Select" button takes the user back to the calculator
     * and shows the result for the select input
     *
     * Also test that the user cannot select an "empty" input
     */
    @Test
    public void testSelect(){
        // pass
    }

}
