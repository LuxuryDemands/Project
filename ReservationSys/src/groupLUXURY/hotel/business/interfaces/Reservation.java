/**
 * 
 */
package groupLUXURY.hotel.business.interfaces;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Sebastian, Max, Isaak, Kajal
 */
public interface Reservation extends Serializable, Comparable<Reservation>  {
	Customer getCustomer();
	Room getRoom();
	LocalDate getCheckInDate();
	LocalDate getCheckOutDate();
	int getNumberOfDays();
}
