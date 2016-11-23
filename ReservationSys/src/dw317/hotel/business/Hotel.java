/**
 * 
 */
package dw317.hotel.business;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.HotelManager;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ReservationDAO;
import dw317.hotel.data.interfaces.RoomDAO;
import groupLUXURY.hotel.data.DuplicateCustomerException;
import groupLUXURY.hotel.data.DuplicateReservationException;
import groupLUXURY.hotel.data.NonExistingCustomerException;
import groupLUXURY.hotel.data.NonExistingReservationException;
import groupLUXURY.lib.Email;
import groupLUXURY.lib.creditcard.CreditCard;
import groupLUXURY.lib.creditcard.CreditCard.CardType;

/**
 * @author 1331680
 *
 */
public class Hotel extends Observable implements HotelManager {
	private final HotelFactory factory;
	private final CustomerDAO customers;
	private final ReservationDAO reservations;
	private static final long serialVersionUID = 42031768871L;

	public Hotel(HotelFactory factory, RoomDAO rooms, CustomerDAO customers, ReservationDAO reservations) {
		this.factory = factory;
		this.customers = customers;
		this.reservations = reservations;
	}

	@Override
	public void cancelReservation(Reservation reservation) throws NonExistingReservationException {
		this.reservations.cancel(reservation);
	}

	@Override
	public void closeHotel() throws IOException {
		this.customers.disconnect();
		this.reservations.disconnect();
	}

	@Override
	public Optional<Reservation> createReservation(Customer customer, LocalDate checkin, LocalDate checkout,
			RoomType roomType){
		DawsonHotelAllocationPolicy policy = new DawsonHotelAllocationPolicy(this.reservations);
		Room room = policy.getAvailableRoom(checkin, checkout, roomType).get();
		Reservation reserv = factory.getReservationInstance(customer, room, checkin.getYear(), checkin.getMonthValue(),
				checkin.getDayOfMonth(), checkout.getYear(), checkout.getMonthValue(), checkout.getDayOfMonth());
		try {
			reservations.add(reserv);
		} catch (DuplicateReservationException e) {
			e.printStackTrace();
		}
		return Optional.of(reserv);

	}

	@Override
	public Customer findCustomer(String email) throws NonExistingCustomerException {
		return this.customers.getCustomer(new Email(email));
	}

	@Override
	public List<Reservation> findReservations(Customer customer) {
		return this.reservations.getReservations(customer);
	}

	@Override
	public Customer registerCustomer(String firstName, String lastName, String email)
			throws DuplicateCustomerException {
		Customer cust = factory.getCustomerInstance(firstName, lastName, email);
		customers.add(cust);
		return cust;
	}

	@Override
	public Customer updateCreditCard(String email, String cardType, String cardnumber)
			throws NonExistingCustomerException {
		CreditCard card = CreditCard.getInstance(CardType.valueOf(cardType), cardnumber);
		this.customers.update(new Email(email), card);
		return findCustomer(email);
	}

}
