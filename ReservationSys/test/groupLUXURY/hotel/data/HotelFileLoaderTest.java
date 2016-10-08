/**
 * 
 */
package groupLUXURY.hotel.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Room;
import groupLUXURY.util.ListUtilities;

/**
 * @author Sebastian
 *
 */
public class HotelFileLoaderTest {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws IOException {
		Room[] roomArrayTest = HotelFileLoader.getRoomListFromSequentialFile(
				"C:\\Users\\Sebastian\\Desktop\\Eclipse\\ReservationSys\\datafiles\\rooms.txt");
		// for (int i = 0; i < roomArrayTest.length; i++) {
		// System.out.println(roomArrayTest[i]);
		// }
		Customer[] customerArrayTest = HotelFileLoader.getCustomerListFromSequentialFile(
				"C:\\Users\\Sebastian\\Desktop\\Eclipse\\ReservationSys\\datafiles\\customers6.txt");
		ListUtilities.sort(customerArrayTest);
		for (int i = 0; i < customerArrayTest.length; i++) {
			System.out.println(customerArrayTest[i]);
		}
	}
}
