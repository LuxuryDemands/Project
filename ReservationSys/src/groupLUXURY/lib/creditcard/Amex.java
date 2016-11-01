/**
 * 
 */
package groupLUXURY.lib.creditcard;
/**
 * @author Sebastian, Max, isaak, Kajal
 *
 */
public final class Amex extends AbstractCreditCard{
	private static final long serialVersionUID = 42031768871L;

	/**
	 * Amex constructor with 1 param
	 * Uses super to recieve number from AbstractCreditCard class.
	 * @param number
	 * @result
	 */
	
	
	public Amex(String number) {
		
		super(CardType.AMEX, validateNumber(number));
	}
	
	
	/**
	 * validateNumber method that checks if the number's length corresponds with the restrictions
	 * @throws IllegalArgumentException("INVALID NUMBER (For Amex)") --> if ever the number's length does not satisfy with the restrictions.
	 * @param number
	 * @return number
	 */
	
	private static String validateNumber(String number){
		if (checkStartingDigits(number) || number.length()!=15){
			throw new IllegalArgumentException("INVALID NUMBER (for Amex)"); 
		}
		return number;
	}
	
	
	/**
	 * checkStartingDigits method checks if the first two numbers are either 34 or 37, which determines whether or not the number is a AMEX credit card
	 * @param number
	 * @return condition
	 */
	
	
	private static boolean checkStartingDigits(String number){
		boolean condition = true;
		String firstTwoDigits = number.substring(0, 2);
		switch (firstTwoDigits){
		case "34":
			condition = false;
			break;
		case "37":
			condition = false;
			break;
		default:
			condition = true;
		}
		return condition;
	}
}