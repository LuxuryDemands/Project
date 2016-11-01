package groupLUXURY.hotel.business;

import java.util.Comparator;

import groupLUXURY.hotel.business.interfaces.Reservation;

/**
 * @author Kajal, Sebastian, Maxime, Isaak
 *
 */

public class ReservationByCustSorted implements Comparator<Reservation> {

	/**
	 * Compares two reservation objects, first by their customers, then by the
	 * default (check in date).
	 * 
	 * @param r1
	 *            A reservation object.
	 * 
	 * @param r2
	 *            A reservation object.
	 * 
	 * @return int 0 if they are equal, 1 if r1 is greater than r2, -1 if r2 is
	 *         greater than r1.
	 */
	@Override
	public int compare(Reservation r1, Reservation r2) {

		if (r1.equals(r2))
			return 0;
		if (!r1.getCustomer().equals(r2.getCustomer())) {
			return r1.getCustomer().compareTo(r2.getCustomer());
		}
		// if same customer, order by check in (i.e., default)
		return r1.compareTo(r2);
	}
}