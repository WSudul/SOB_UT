
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {
    private Stack testStack;
    private String t1 = "t1";
    private String t2 = "t2";
    private String t3 = "t3";
    private String t4 = "t4";

    @Before
    public void setUp() throws Exception {
        testStack = new Stack();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isEmptyReturnsTrue() throws Exception {
        assertTrue(testStack.isEmpty());
    }

    @Test
    public void isEmptyReturnsFalseWhenHasElements() throws Exception {
        testStack.push(t1);
        assertFalse(testStack.isEmpty());
    }

    @Test(expected = IllegalStateException.class)
    public void topThrowsExceptionWhenEmpty() throws Exception {
        testStack.top();
    }

    @Test(expected = IllegalStateException.class)
    public void popThrowsExceptionWhenEmpty() throws Exception {
        testStack.pop();
    }

    @Test
    public void topReturnsValidTopString() throws Exception {
        testStack.push(t1);
        testStack.push(t2);
        String result = testStack.pop();
        assertEquals(t2, result);
    }

    @Test
    public void topReturnsReferenceToPushedInstance() throws Exception {
        testStack.push(t1);
        assertSame(t1, testStack.top());
    }

    @Test
    public void pushAddsElements() throws Exception {
        long expectedSize = 1;
        testStack.push(t1);
        assertEquals(expectedSize, testStack.size());
    }

    @Test
    public void pushAcceptsNull() throws Exception {
        String testString = null;
        testStack.push(testString);
        assertNull(testStack.top());
        assertNull(testStack.pop());
    }

    @Test
    public void popRemovesTheTopElement() throws Exception {
        String tmp1, tmp2, tmp3, tmp4;
        testStack.push(t1);
        testStack.push(t2);
        testStack.push(t3);
        testStack.push(t4);
        tmp4 = testStack.pop();
        tmp3 = testStack.pop();
        tmp2 = testStack.pop();
        tmp1 = testStack.pop();
        assertEquals(t1, tmp1);
        assertEquals(t2, tmp2);
        assertEquals(t3, tmp3);
        assertEquals(t4, tmp4);
    }

    @Test(expected = IllegalStateException.class)
    public void popThrowsExceptionWhenAllElementsAreRemoved() throws Exception {
        testStack.push(t1);
        testStack.push(t2);
        testStack.push(t3);
        testStack.push(t4);
        testStack.pop();
        testStack.pop();
        testStack.pop();
        testStack.pop();
        testStack.pop();//no elements to pop, should throw exception

    }

    @Test
    public void popRemainsInValidStateAfterThrowingException() throws Exception {
        boolean exception_thrown = false;
        testStack.push(t1);
        testStack.pop();
        try {
            testStack.pop();

        } catch (IllegalStateException e) {
            exception_thrown = true;
        }
        assertTrue(exception_thrown);
        assertTrue(testStack.isEmpty());

        assertSame(t2, testStack.push(t2));
        assertFalse(testStack.isEmpty());
        assertSame(t2, testStack.top());
        assertSame(t2, testStack.pop());
        assertTrue(testStack.isEmpty());
    }

}