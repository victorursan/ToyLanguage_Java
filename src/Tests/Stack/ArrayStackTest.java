package Tests.Stack;

import com.victorursan.Models.Stack.IStack;
import com.victorursan.Models.Stack.MyLibraryStack;
import junit.framework.TestCase;

/**
 * Created by victor on 10/6/15.
 */
public class ArrayStackTest extends TestCase {
    private IStack<String> s;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        s = new MyLibraryStack<>();
        s.push("ana");
        s.push("123");
        s.push("234");
        s.push("ion");
    }

    public void testPush() throws Exception {
        s.push("maria");
        assertEquals(s.pop(), "maria");
    }

    public void testPop() throws Exception {
        String ion = s.pop();
        assertEquals(ion, "ion");
        assertEquals(s.isEmpty(), false);
    }

    public void testIsEmpty() throws Exception {
        assertEquals(s.isEmpty(), false);
    }

    public void testPeek() throws Exception {
        assertEquals(s.peek(), "ion");
    }

    public  void testSearch() throws Exception {
        assertEquals(s.search("123"), 3);
        assertEquals(s.search("a"), -1);
    }
}