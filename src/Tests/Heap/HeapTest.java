package Tests.Heap;

import junit.framework.TestCase;
import com.victorursan.Models.Heap.*;

/**
 * Created by victor on 11/22/15.
 */
public class HeapTest extends TestCase{
    private MyLibraryHeap<Integer> myHeap;

    public void setUp() throws Exception {
        super.setUp();
        myHeap = new MyLibraryHeap<>();
        myHeap.add(10);
        myHeap.add(20);
        myHeap.add(30);

    }

    public void testAdd() {
        assertEquals(3, myHeap.add(40));
        assertEquals(4, myHeap.add(50));
        assertEquals(5, myHeap.add(60));
    }

    public void testGet() {
        assert (50 == myHeap.get(4));
        assert (60 == myHeap.get(5));
    }

    public void testUpdate() {
        myHeap.update(4, 34);
        assert (34 == myHeap.get(4));
    }

}
