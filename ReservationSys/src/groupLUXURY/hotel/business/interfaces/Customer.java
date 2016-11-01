package groupLUXURY.hotel.business.interfaces;

import java.io.Serializable;
import java.util.Optional;

import groupLUXURY.lib.Email;
import groupLUXURY.lib.Name;
import groupLUXURY.lib.creditcard.CreditCard;

/**
 * @author Sebastian, Max, isaak
 */
public interface Customer extends Serializable, Comparable<Customer>{
	Name getName();
	Email getEmail();
	Optional<CreditCard> getCreditCard();
	void setCreditCard(Optional<CreditCard> card);
}
