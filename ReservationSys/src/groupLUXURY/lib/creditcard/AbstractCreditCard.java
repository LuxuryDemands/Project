/**
 * 
 */
package dw317.lib.creditcard;

/**
 * @author Sebastian, Max, Isaak
 */
public abstract class AbstractCreditCard implements CreditCard {
	private static final long serialVersionUID = 42031768871L;
	private final CardType cardType;
	private final String number;

	/**
	 * AbstractCreditCard constructor with 2 params 
	 * which sets new instances of cardType with the one in the param
	 * then sets the new instances of number with the one in the param depending on if it satisfies the luhnAlgorithm using validateLuhnAlgorithum method
	 * @param cardType Card Type
	 * @param number Card Number
	 */
	public AbstractCreditCard(CardType cardType, String number) {
		this.cardType = cardType;
		if (this.cardType != CardType.AMEX){
			this.number = validateLuhnAlgorithm(new String(number));
		}
		else{
			this.number = validateLuhnAlgorithmAmex(new String(number));
		}

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	@Override
	public String toString() {
		return cardType + "*" + number;
	}

	/**
	 * getType method
	 * @return CardType Returns the card type
	 */
	public CardType getType() {
		return this.cardType;
	}
	/**
	 * getNumber method
	 * @return String Returns the credit card number
	 */
	public String getNumber() {
		return new String(number);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : 
			number.hashCode());
		return result;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AbstractCreditCard))
			return false;
		AbstractCreditCard other = (AbstractCreditCard) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
	
	/**
	 * validateLuhnAlgorithm method validates whether or not the credit card entered (param: number) is legit or not using the Luhn Algorithm: VISA OR MASTERCARD
	 * @throws IllegalArgumentException If the number does not satisfy the Luhn Algorithm. 
	 * @param number The Credit card number
	 * @return String Retuning the credit card number that is validated using the luhn algorithm
	 */
	
	
	private String validateLuhnAlgorithm(String number){
		
		int numbers [] = new int [20];
		int total =0;
			

		for (int i=0; i<number.length() ; i++)
		{
			numbers[i] = Integer.parseInt(number.substring(i, i+1));
				
			if (i % 2 == 0)
				numbers[i] = numbers[i] * 2;
				
			if (numbers[i] >9)
				numbers[i] -= 9;
		}
			
		for (int i=0; i<number.length() ; i++)
				total += numbers[i];
			
		if (total % 10 ==0 )
			return number;
		else
			throw new IllegalArgumentException("INVALID NUMBER (luhn alg)");
				
			
	}
	
	/**
	 * validateLuhnAlgorithmAmex method validates whether or not the credit card (AMEX type) entered (param: number) is legit or not using the Luhn Algorithm
	 * @throws IllegalArgumentException If the number does not satisfy the Luhn Algorithm.
	 * @param number The Credit Card number
	 * @return String Returning the Credit Card number
	 */
	
	
	private String validateLuhnAlgorithmAmex(String number){
		
		int numbers [] = new int [20];
		int total =0;
		number = "0"+number;	

		for (int i=0; i<number.length() ; i++)
		{
			numbers[i] = Integer.parseInt(number.substring(i, i+1));
				
			if (i % 2 == 0)
				numbers[i] = numbers[i] * 2;
				
			if (numbers[i] >9)
				numbers[i] -= 9;
		}
			
		for (int i=0; i<number.length() ; i++)
				total += numbers[i];
			
		if (total % 10 ==0 )
			return number.substring(1);
		else
			throw new IllegalArgumentException("INVALID NUMBER (luhn alg)");
				
	}

}
