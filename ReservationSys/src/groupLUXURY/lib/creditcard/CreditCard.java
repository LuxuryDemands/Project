package groupLUXURY.lib.creditcard;

import java.io.Serializable;
/**
 * 
 * @author Max, Sebastian, isaak
 */


public interface CreditCard extends Serializable {
	String getNumber();

	CardType getType();

	
	/**
	 * getInstance method checks and creates a CreditCard whether it is MasterCard/Visa/AMEX
	 * @param type The credit card type
	 * @param number The credit card number
	 * @return CreditCard Returning the full credit card number and type.
	 */
	
	
	// Credit card factory method based on the type
	public static CreditCard getInstance(CardType type, String number) {
		CreditCard card = null;
		switch (type) {
		case MASTERCARD:
			card = new MasterCard(number);
			break;
		case VISA:
			card = new Visa(number);
			break;
		case AMEX:
			card = new Amex(number);
		}
		return card;
	}

	/**
	 * enum CardType to define whether a card is MASTERCARD, VISA OR AMEX
	 *
	 */
	
	public enum CardType {
		MASTERCARD, VISA, AMEX;

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		
		@Override
		public String toString() {
			return this.name().toLowerCase();
		}
	}
}
