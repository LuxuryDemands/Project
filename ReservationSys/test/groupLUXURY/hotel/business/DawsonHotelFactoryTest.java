/**
 * 
 */
package groupLUXURY.hotel.business;

import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.lib.creditcard.CreditCard;
/**
 * @author 1331680
 *
 */
public class DawsonHotelFactoryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Customer c1 = DawsonHotelFactory.DAWSON.getCustomerInstance("Juan","Ramirez","abc@hot.com");
		Customer c2 = DawsonHotelFactory.DAWSON.getCustomerInstance("Juan","Ramirez","abc@hoT.com");
		Customer c3 = DawsonHotelFactory.DAWSON.getCustomerInstance("Juan","Ramirez","ABC@hot.com");
		CreditCard cc1 = DawsonHotelFactory.DAWSON.getCard("amex","379558683553028");
		Room r1 = DawsonHotelFactory.DAWSON.getRoomInstance(801,"PENTHOUSE");
		c1.setCreditCard(Optional.of(cc1));
		Reservation rv1 = DawsonHotelFactory.DAWSON.getReservationInstance(c1, r1, 2016, 10, 06, 2016, 10, 16);
		System.out.println(cc1.toString());
		System.out.println(c1.toString());
		System.out.println(c1.equals(c2));
		System.out.println(c3.toString());
		System.out.println(r1.toString());
		System.out.println(rv1.toString());
		
	}

}
