/**
 * 
 */
package dw317.lib.creditcard;

import static java.lang.System.out;

import dw317.lib.Email;

/**
 * @author Sebastian
 *
 */
public class VisaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testTheVisaClass();
		//String emailAddress1 = "sebastian@hot.com";
		//System.out.println(emailAddress1.substring(emailAddress1.indexOf('@')+1));
		Email emailAddress = new Email("sebastian@ab-c.com");
		System.out.println(emailAddress.toString());

	}
	public static void testTheVisaClass() {
		out.println("\nTesting the three parameter constructor.");
		testTheVisaClass("Case 1 - Valid data (4929266914587172)", "4929266914587172", true);
		//testTheVisaClass("Case 2 - Invalid data, number does not start by 4 (5929266914587172)", "5929266914587172", false);
		//testTheVisaClass("Case 3 - Invalid data, number is below 16 digits (492926691458717)", "492926691458717", false);
		//testTheVisaClass("Case 3 - Invalid data, number is above 16 digits (49292669145871723)", "49292669145871723", false);
		//testTheVisaClass("Case 4 - Invalid data, invalid luhn algorithm (4929266914588172)", "4929266914588172", false);
		Email email = new Email ("juserapa@hotmail.com");
		Email email1 = new Email ("kuserapa@HOTMAIL.com");
		System.out.println(email.compareTo(email1));
		String asd = ".asdasd.";
		System.out.println(asd.contains(".."));
	}

	private static void testTheVisaClass(String testCase, String number, boolean expectValid) {

		out.println("   " + testCase);

		Visa theCard = new Visa(number);
		out.print("\tThe Visa instance was created: " + theCard);

		if (!expectValid)
			out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		out.println("\n");
	}

}


