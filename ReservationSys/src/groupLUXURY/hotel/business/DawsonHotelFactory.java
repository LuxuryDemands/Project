package groupLUXURY.hotel.business;

import groupLUXURY.hotel.business.interfaces.Customer;
import groupLUXURY.hotel.business.interfaces.HotelFactory;
import groupLUXURY.hotel.business.interfaces.Reservation;
import groupLUXURY.hotel.business.interfaces.Room;
import groupLUXURY.lib.creditcard.CreditCard;

/**
 * @author Sebastian, Max, Isaak, Kajal
 */
public enum DawsonHotelFactory implements HotelFactory {
	DAWSON;
	
	
	/**
	 * Overrides abstract method getCustomerInstance from HotelFactory class
	 * Sets new instances of Customer
	 * @param firstName The first name of the customer
	 * @param lastName The last name of the customer
	 * @param email the email of the customer
	 * @return DawsonCustomer Object of DawsonCustomer with params firstName, lastName, email.
	 */
	
	
	@Override
	public Customer getCustomerInstance(String firstName, String lastName, String email) {
		return new DawsonCustomer(firstName, lastName, email);
	}

	/**
	 * Overrides abstract method getCard from HotelFactory class
	 * @param cardtype Credit card type
	 * @param number Credit card number
	 * @return CreditCard Returns a CreditCard object with the card type and credit card number.
	 */
	
	
	@Override
	public CreditCard getCard(String cardtype, String number) {
		return CreditCard.getInstance(CreditCard.CardType.valueOf(cardtype.toUpperCase()), number);
	}

	/**
	 * Overrides abstract method getRoomInstance from HotelFactory class
	 * @param roomNumber The room number
	 * @param roomtype the room type 
	 * @return DawsonRoom Returns an room object with roomNumber and roomtype
	 */
	
	
	@Override
	public Room getRoomInstance(int roomNumber, String roomtype) {
		return new DawsonRoom(roomNumber, RoomType.valueOf(roomtype.toUpperCase()));
	}

	/**
	 * Overrides abstract method getReservationInstance from HotelFactory class
	 * @param aCustomer The customer
	 * @param aRoom The room
	 * @param inYear Check in date: year
	 * @param inMonth Check in date: month
	 * @param inDay Check in date: day
	 * @param outYear Check out date: year
	 * @param outMonth Check out date: month
	 * @param outDay Check out date: day
	 * @return DawsonReservation Returns a Reservation object with params: aCustomer, aRoom, inYear, inMonth, inDay, outYear, outMonth, outDay
	 */
	
	
	@Override
	public Reservation getReservationInstance(Customer aCustomer, Room aRoom, int inYear, int inMonth, int inDay,
			int outYear, int outMonth, int outDay) {
		return new DawsonReservation(aCustomer, aRoom, inYear, inMonth, inDay, outYear, outMonth, outDay);
	}
}