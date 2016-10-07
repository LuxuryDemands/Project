/**
 * 
 */
package dw317.lib.creditcard;

/**
 * @author Sebastian, Max, Isaak
 */
public final class Visa extends AbstractCreditCard{
	private static final long serialVersionUID = 42031768871L;

	/**
	 * Visa constructor with 1 param
	 * Uses super to recieve number from AbstractCreditCard class.
	 * @param number The Credit Card number 
	 */
	
	public Visa(String number) {
		super(CardType.VISA, validateNumber(number));
	}
	
	/**
	 * validateNumber method that checks wheather the number's length is in the range of 4-16
	 * @throws IllegalArgumentException If the card does not follow the restrictions of a Visa card.
	 * @param number The Credit Card number
	 * @return String Returning the Credit Card number
	 */
	
	private static String validateNumber(String number){
		if (Integer.parseInt(number.substring(0, 1))!=4 || number.length()!=16){
			throw new IllegalArgumentException("INVALID NUMBER (for Visa)"); 
		}
		return number;
	}
}