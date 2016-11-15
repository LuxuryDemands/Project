package dw317.hotel.business;

import java.util.Comparator;

import dw317.hotel.business.interfaces.Reservation;

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
public class ReservationByCheckOutSorted implements Comparator<Reservation> {

	@Override
	public int compare(Reservation r1, Reservation r2) {
		if (r1.getCheckOutDate().equals(r2.getCheckOutDate())) {
			return r1.compareTo(r2);
		}
		if (r1.getCheckOutDate().isAfter(r2.getCheckOutDate())) {
			return 1;
		}
		// // if same customer, order by check out (i.e., default)
		return -1;
	}

}
