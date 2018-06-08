
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StackTest {
    private Stack testStack;

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
        testStack.push("element");
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
        String testString="test";
        testStack.push(testString);
        String result=testStack.pop();
        assertEquals( testString, result);
    }

    @Test
    public void topReturnsReferenceToPushedInstance() throws Exception {
        String testString="test";
        testStack.push(testString);
        assertSame(testString, testStack.top());
    }

    @Test
    public void pushAddsElements() throws Exception {
        long initSize=testStack.size();
        testStack.push("test");
        assertTrue(initSize<testStack.size());
    }

    @Test
    public void pushAcceptsNull() throws Exception {
        String testString = null;
        testStack.push(testString);
        assertNull(testStack.top());
    }

    @Test
    public void popRemovesTheTopElement() throws Exception {
        String t1 = "t1", t2 = "te2", t3 = "t3", t4 = "t4";
        String tmp1, tmp2, tmp3, tmp4;
        testStack.push(t1); testStack.push(t2); testStack.push(t3); testStack.push(t4);
        tmp4=testStack.pop(); tmp3=testStack.pop(); tmp2=testStack.pop(); tmp1=testStack.pop();
        assertEquals(t1,tmp1);
        assertEquals(t2,tmp2);
        assertEquals(t3,tmp3);
        assertEquals(t4,tmp4);
    }

    @Test
    public void popThrowsExceptionWhenAllElementsAreRemoved() throws Exception {
        String t1 = "t1", t2 = "te2", t3 = "t3", t4 = "t4";
        testStack.push(t1); testStack.push(t2); testStack.push(t3); testStack.push(t4);
        testStack.pop(); testStack.pop(); testStack.pop(); testStack.pop();
        testStack.pop();//no elements to pop, should throw exception

    }

    @Test
    public void popRemainsInValidStateAfterThrowingException() throws Exception {
        String t1 = "t1", t2 = "te2", t3 = "t3", t4 = "t4";
        testStack.push(t1); testStack.push(t2); testStack.push(t3); testStack.push(t4);
        testStack.pop(); testStack.pop(); testStack.pop(); testStack.pop();
        testStack.pop();//no elements to pop, should throw exception
        testStack.push("new element");
        System.out.println(testStack.size());
    }

}