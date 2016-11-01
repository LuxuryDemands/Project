package dw317.hotel.business.interfaces;

import java.io.Serializable;
import java.util.Optional;

import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.CreditCard;

/**
 * @author Sebastian, Max, isaak
 */
public interface Customer extends Serializable, Comparable<Customer>{
	Name getName();
	Email getEmail();
	Optional<CreditCard> getCreditCard();
	void setCreditCard(Optional<CreditCard> card);
}
