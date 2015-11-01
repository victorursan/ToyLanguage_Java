package Tests.Map;

import com.victorursan.Models.Map.ArrayDictionary;
import junit.framework.TestCase;

/**
 * Created by victor on 10/12/15.
 */
public class ArrayDictionaryTest extends TestCase {

    private ArrayDictionary myDictionary;

    public void setUp() throws Exception {
        super.setUp();
        myDictionary = new ArrayDictionary();
        myDictionary.put("a", 1);
        myDictionary.put("b", 2);
    }

    public void testClear() throws Exception {

    }

    public void testContainsKey() throws Exception {

    }

    public void testContainsValue() throws Exception {

    }

    public void testGet() throws Exception {

    }

    public void testIsEmpty() throws Exception {

    }

    public void testPut() throws Exception {

    }

    public void testRemove() throws Exception {
        assertEquals(myDictionary.size(), 2);
        myDictionary.remove("a");
        assertEquals(myDictionary.size(), 1);
        assertEquals(myDictionary.containsKey("a"), false);
    }

    public void testSize() throws Exception {
//        assertEquals(myDictionary.size(), 2);
    }

}