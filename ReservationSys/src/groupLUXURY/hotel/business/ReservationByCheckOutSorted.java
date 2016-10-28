package groupLUXURY.hotel.business;

import java.util.Comparator;

import dw317.hotel.business.interfaces.Reservation;

/**
 * @author kajal
 *
 */
public class ReservationByCheckOutSorted implements Comparator<Reservation> {

	@Override
	public int compare(Reservation r1, Reservation r2) {

		if (r1.getCheckOutDate().equals(r2.getCheckOutDate())) {
			return 0;
		}

		if (r1.getCheckOutDate().isAfter(r2.getCheckOutDate())) {
			return 1;
		}

		// // if same customer, order by check out (i.e., default)
		return -1;
	}

}
