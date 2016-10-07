/**
 * 
 */
package dw317.lib.creditcard;

/**
 * @author Sebastian, Max, isaak
 */
public final class MasterCard extends AbstractCreditCard{
	private static final long serialVersionUID = 42031768871L;

	/**
	 * MasterCard constructor with 1 param
	 * Uses super to recieve number from AbstractCreditCard class.
	 * @param number
	 */
	public MasterCard(String number) {
		super(CardType.MASTERCARD, validateNumber(number));
	}
	
	/**
	 * validateNumber method that checks the number's length whether it corresponds with the restrictions or not.
	 * @throws IllegalArgumentException if the credit card number is invalid depending on the restrictions.
	 * @param number The credit card number
	 * @return String Returning the credit card number
	 */
	
	
	private static String validateNumber(String number){
		if (checkStartingDigits(number) || number.length()!=16){
			throw new IllegalArgumentException("INVALID NUMBER (for MasterCard)"); 
		}
		return number;
	}
	
	/**
	 * checkStartingDigits method checks the first two digits of the number to see if the creditcard is a MasterCard or not
	 * @param number The credit card number
	 * @return boolean Returning true or false whether or not the two first digits corresponds with the MasterCard restrictions
	 */
	
	
	private static boolean checkStartingDigits(String number){
		boolean condition = true;
		String firstTwoDigits = number.substring(0, 2);
		switch (firstTwoDigits){
		case "51":
			condition = false;
			break;
		case "52":
			condition = false;
			break;
		case "53":
			condition = false;
			break;
		case "54":
			condition = false;
			break;
		case "55":
			condition = false;
			break;
		default:
			condition = true;
		}
		return condition;
	}
}
