package PT2018.demo.Project1;

import junit.framework.TestCase;

public class MainTest extends TestCase {

	public void testMain() {
//		fail("Not yet implemented");
		
		Polinom pol1 = new Polinom();
		Polinom pol2 = new Polinom();
		Float fl1 = new Float(2);
		Float fl2 = new Float(5);
		Operatii op = new Operatii();
		
		pol1.addElement(new Monom(fl1, 3));
		pol2.addElement(new Monom(fl2, 3));
		
		assertEquals(7, op.getSum(pol1, pol2));
	}

}
