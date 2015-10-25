package Tests.Expressions;

import com.victorursan.Models.Expressions.ArithExp;
import com.victorursan.Models.Expressions.ConstExp;
import com.victorursan.Models.Expressions.Exp;
import com.victorursan.Models.Map.ArrayDictionary;
import junit.framework.TestCase;

/**
 * Created by victor on 10/25/15.
 */
public class ArithExpTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void testEval() throws Exception {
        Exp ex = new ArithExp(new ConstExp(5), "+", new ArithExp(new ConstExp(4), "*", new ConstExp(5)));
        assertEquals(ex.eval(new ArrayDictionary()).intValue(), 25);
        System.out.print(ex.eval(new ArrayDictionary()));
    }



    public void testToStr() throws Exception {

    }

    public void testEval1() throws Exception {

    }

    public void testToStr1() throws Exception {

    }
}