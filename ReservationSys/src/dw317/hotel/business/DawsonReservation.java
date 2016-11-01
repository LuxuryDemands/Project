/**
 * 
 */
package dw317.hotel.business;

import java.time.LocalDate;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;

/**
 * @author Sebastian, Max, Isaak
 */
public class DawsonReservation implements Reservation {
	private static final long serialVersionUID = 42031768871L;
	protected final Customer customer;
	protected final Room room;
	protected LocalDate checkInDate;
	protected LocalDate checkOutDate;

	/**
	 * DawsonReservation constructor with 8 params. sets new instances of
	 * checkInDate using LocalDate.of method with params: inYear, inMonth, inDay
	 * sets new instances of checkOutDate using LocalDate.of method with params:
	 * outYear, outMonth, outDay sets new instances of customer sets new
	 * instances of room
	 * 
	 * @throws IllegalArgumentException
	 *             if the in day is BEFORE the out day.
	 * @throws IllegalArgumentException
	 *             if the in day is BEFORE the current day
	 * @param customer
	 *            The customer
	 * @param room
	 *            The room
	 * @param inYear
	 *            The check in date: year
	 * @param inMonth
	 *            The check in date: month
	 * @param inDay
	 *            The check in date: day
	 * @param outYear
	 *            The check out date: year
	 * @param outMonth
	 *            The check out date: month
	 * @param outDay
	 *            The check out date: day
	 */

	public DawsonReservation(Customer customer, Room room, int inYear, int inMonth, int inDay, int outYear,
			int outMonth, int outDay) {
		this.checkInDate = LocalDate.of(inYear, inMonth, inDay);
		this.checkOutDate = LocalDate.of(outYear, outMonth, outDay);
		if (this.checkInDate.isAfter(checkOutDate)) {
			throw new IllegalArgumentException(
					"The check in date must be before or in the same date as the check out day: "
							+ customer.getEmail().toString());
		}
		// if (this.checkInDate.isBefore(LocalDate.now())) {
		// throw new IllegalArgumentException("You cannot make a reservation for
		// a day in the past");
		// }
		this.customer = customer;
		this.room = room;
	}

	/**
	 * overRiding compareTo method
	 * 
	 * @param other
	 *            Reservation to be compared to.
	 * @return room compared with other room OR checkInDate compared with other
	 *         getCheckOutDate
	 */
	@Override
	public int compareTo(Reservation other) {
		if (this.checkInDate == other.getCheckOutDate()) {
			return this.room.compareTo(other.getRoom());
		} else {
			return this.checkInDate.compareTo(other.getCheckInDate());
		}
	}

	/**
	 * Overrides abstract method getCustomer from Reservation class Sets new
	 * instances of firstName with customer's getName and getFirstName method
	 * Sets new instances of lastName with customer's getName and getLastName
	 * method Sets new instances of email with customer's getEmail and
	 * getAddress
	 * 
	 * @return Customer Returns a DawsonCustomer object with params:firstName,
	 *         lastName, email
	 */
	@Override
	public Customer getCustomer() {
		// TODO Auto-generated method stub
		String firstName = customer.getName().getFirstName();
		String lastName = customer.getName().getLastName();
		String email = customer.getEmail().getAddress();
		return new DawsonCustomer(firstName, lastName, email);
	}

	/**
	 * overRiding getRoom method
	 * 
	 * @return room Returning the room
	 */

	@Override
	public Room getRoom() {
		// TODO Auto-generated method stub
		return this.room;
	}

	/**
	 * Overrides abstract method getCheckInDate from Reservation class
	 * 
	 * @return LocalDate Returning a LocalDate object of the check in date with
	 *         the result: YYYY*MM*DD
	 */

	@Override
	public LocalDate getCheckInDate() {
		return LocalDate.of(checkInDate.getYear(), checkInDate.getMonthValue(), checkInDate.getDayOfMonth());
	}

	/**
	 * Overrides abstract method getCheckOutDate from Reservation class
	 * 
	 * @return LocalDate Returning a LocalDate object of the check out date with
	 *         the result: YYYY*MM*DD
	 */

	@Override
	public LocalDate getCheckOutDate() {
		return LocalDate.of(checkOutDate.getYear(), checkOutDate.getMonthValue(), checkOutDate.getDayOfMonth());
	}

	/**
	 * Overrides abstract method getNumberOfDays from Reservation class
	 * 
	 * @return int Returning the days between the check in dates and the check
	 *         out dates
	 */

	@Override
	public int getNumberOfDays() {
		return this.checkInDate.until(checkOutDate).getDays();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checkInDate == null) ? 0 : checkInDate.hashCode());
		result = prime * result + ((checkOutDate == null) ? 0 : checkOutDate.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DawsonReservation))
			return false;
		DawsonReservation other = (DawsonReservation) obj;
		if (checkInDate == null) {
			if (other.checkInDate != null)
				return false;
		} else if (!checkInDate.equals(other.checkInDate))
			return false;
		if (checkOutDate == null) {
			if (other.checkOutDate != null)
				return false;
		} else if (!checkOutDate.equals(other.checkOutDate))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
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
		return this.customer.getEmail().toString() + "*" + formatDate(this.checkInDate) + "*"
				+ formatDate(this.checkOutDate) + "*" + this.getRoom().getRoomNumber();
	}

	/**
	 * formatDate method that formats a date structure
	 * 
	 * @param LocalDate
	 *            The date to be formatted
	 * @return String Returning results: YYYY*MM*DD
	 */

	private String formatDate(LocalDate date) {
		return date.getYear() + "*" + date.getMonthValue() + "*" + date.getDayOfMonth();
	}

}
