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
        assertEquals(4, myHeap.add(40));
        assertEquals(5, myHeap.add(50));
        assertEquals(6, myHeap.add(60));
    }

    public void testGet() {
        try {
            assert (50 == myHeap.get(5));
            assert (60 == myHeap.get(6));
        } catch (HashIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public void testUpdate() {
        try {
            myHeap.update(3, 34);
            assert (34 == myHeap.get(3));
        } catch (HashIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

}
