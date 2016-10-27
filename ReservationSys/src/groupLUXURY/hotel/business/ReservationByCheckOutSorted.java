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

		if (r1.equals(r2)) {
			return 0;
		}

		if (!r1.getCheckOutDate().equals(r2.getCheckOutDate())) {
			return r1.getCheckOutDate().compareTo(r2.getCheckOutDate());
		}

		// // if same customer, order by check out (i.e., default)
		return r1.compareTo(r2);
	}

}
