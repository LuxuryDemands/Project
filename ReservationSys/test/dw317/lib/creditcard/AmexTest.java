/**
 * 
 */
package dw317.lib.creditcard;

import static java.lang.System.out;

/**
 * @author Sebastian nop kajal another cxhange hjgfjhgjhgjg
 *
 */
//test
public class AmexTest{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testTheAmexClass();
	}

	public static void testTheAmexClass() {
		out.println("\nTesting the three parameter constructor.");
		testTheAmexClass("Case 1 - Valid data, starting with 34 (341681901408265)", "341681901408265", true);
		testTheAmexClass("Case 1 - Valid data, starting with 37 (371173254180108)", "373386597001264", true);
		//testTheAmexClass("Case 1 - Invalid data, 2 first digits not 34 or 37 (331681901408265)", "331681901408265", false);
		testTheAmexClass("Case 1 - Invalid data, 2 first digits not 37 (331681901408265)", "331681901408265", false);

	}

	private static void testTheAmexClass(String testCase, String number, boolean expectValid) {

		out.println("   " + testCase);

		Amex theCard = new Amex(number);
		out.print("\tThe Amex instance was created: " + theCard);

		if (!expectValid)
			out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		out.println("\n");
	}

}
