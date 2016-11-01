/**
 * 
 */
package groupLUXURY.lib.creditcard;

import static java.lang.System.out;

import groupLUXURY.lib.creditcard.MasterCard;

/**
 * @author 1331680
 *
 */
public class MasterCardTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testTheMasterCardClass();
	}

	public static void testTheMasterCardClass() {
		out.println("\nTesting the three parameter constructor.");
		//testTheMasterCardClass("Case 1 - Valid data (5589914945528594)", "5589914945528594", true);
		//testTheMasterCardClass("Case 2 - Invalid data, first 2 digits out of range(below) (5089914945528594)", "5089914945528594", false);
		//testTheMasterCardClass("Case 3 - Invalid data, first 2 digits out of range(above) (5689914945528594)", "5689914945528594", false);
		//testTheMasterCardClass("Case 4 - Invalid data, less than 16 digits (558991494552859)", "558991494552859", false);
		//testTheMasterCardClass("Case 5 - Invalid data, more than 16 digits (55899149455285945)", "55899149455285945", false);
		testTheMasterCardClass("Case 5 - Invalid data, wrong luhn algorithm (5579914945528594)", "5579914945528594", false);
	}

	private static void testTheMasterCardClass(String testCase, String number, boolean expectValid) {

		out.println("   " + testCase);

		MasterCard theCard = new MasterCard(number);
		out.print("\tThe MasterCard instance was created: " + theCard);

		if (!expectValid)
			out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		out.println("\n");
	}

}
