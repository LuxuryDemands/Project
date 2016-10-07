/**
 * 
 */
package groupLUXURY.hotel.business;

/**
 * @author Sebastian
 *
 */
public class DawsonReservationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DawsonRoom dr1 = new DawsonRoom(801, RoomType.NORMAL);
		DawsonCustomer dc1 = new DawsonCustomer("Juan","Sebastian","abc@hot.com"); 
		DawsonReservation a = new DawsonReservation(dc1, dr1, 2016,10,05,2016,11,06);
		System.out.println(a.getNumberOfDays());

	}

}
