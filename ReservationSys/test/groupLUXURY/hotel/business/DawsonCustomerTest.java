/**
 * 
 */
package groupLUXURY.hotel.business;

import java.util.Optional;

import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.CreditCard.CardType;

/**
 * @author Sebastian
 *
 */
public class DawsonCustomerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreditCard card1 = CreditCard.getInstance(CardType.AMEX, "341681901408265");
		DawsonCustomer dc1 = new DawsonCustomer("juan","sebastian","Abc@abc.com");
		DawsonCustomer dc2 = new DawsonCustomer("juan","sebastian","ABC@Abc.com");
		DawsonCustomer dc3 = new DawsonCustomer("john","sebastin","ABC@Abc.com");
		dc1.setCreditCard(Optional.of(card1));
		System.out.println(dc1);
		System.out.println(dc1.getCreditCard());
		System.out.println(dc2);
		System.out.println(dc2.getCreditCard());
		System.out.println(dc1.getName()+"*"+dc1.getEmail());
		System.out.println(dc1.hashCode());
		System.out.println(dc2.hashCode());
		System.out.println(dc3.hashCode());
		System.out.println(dc1.equals(dc3));
	}

}
