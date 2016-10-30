/**
 * 
 */
package groupLUXURY.hotel.business;

import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.CreditCard;

/**
 * @author Sebastian, Max, Isaak
 */
public class DawsonCustomer implements Customer {

	/**
	 * Varriables
	 */
	private static final long serialVersionUID = 42031768871L;
	protected Name name;
	protected final Email email;
	protected CreditCard creditCard;

	/**
	 * DawsonCustomer constructor with 3 params. Sets all the new instances of
	 * name,email,creditCard with the ones given in the param
	 * 
	 * @param firstName
	 *            The first name of the customer
	 * @param lastName
	 *            The last name of the customer
	 * @param email
	 *            The email of the customer
	 */

	public DawsonCustomer(String firstName, String lastName, String email) {
		this.name = new Name(firstName, lastName);
		this.email = new Email(email);
		this.creditCard = null;
	}

	/**
	 * Overrides abstract method compareTo from Comparable Class
	 * 
	 * @param other
	 *            Customer object to compare
	 * @return int Returns the comparision of this.email and other.email
	 */

	@Override
	public int compareTo(Customer other) {
		return this.email.compareTo(other.getEmail());

	}

	/**
	 * Overrides abstract method getName from Customer Class
	 * 
	 * @return Name object's name defensive copying.
	 */

	@Override
	public Name getName() {
		return new Name(this.name);
	}

	/**
	 * Overrides abstract method getEmail from Customer Class
	 * 
	 * @return Email object's address defensive copying
	 */

	@Override
	public Email getEmail() {
		return new Email(this.email.getAddress());
	}

	/**
	 * Overrides abstract method getCreditCard from Customer Class
	 * 
	 * @return CreditCard Returns a CreditCard object if it exists and it is not
	 *         null.
	 */

	@Override
	public Optional<CreditCard> getCreditCard() {
		return Optional.ofNullable(creditCard);
	}

	/**
	 * Overrides abstract method setCreditCard from Customer Class Sets a new
	 * instance of creditCard
	 * 
	 * @param card
	 *            Optional CreditCard card
	 */

	@Override
	public void setCreditCard(Optional<CreditCard> card) {
		this.creditCard = card.orElse(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DawsonCustomer))
			return false;
		DawsonCustomer other = (DawsonCustomer) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String creditCardToString;
		if (this.creditCard == null) {
			creditCardToString = "*";
		} else {
			creditCardToString = this.creditCard.toString();
		}
		return this.email.toString() + "*" + this.name.toString() + "*" + creditCardToString;
	}
}
