/**
 * 
 */
package groupLUXURY.hotel.data;

import java.io.FileNotFoundException;
import java.io.IOException;
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
				"H:\\git\\Project\\ReservationSys\\datafiles\\unsorted\\rooms.txt");
		 for (int i = 0; i < roomArrayTest.length; i++) {
		System.out.println(roomArrayTest[i]);
	 }
		 ListUtilities.sort(roomArrayTest);
		 for (int i = 0; i < roomArrayTest.length; i++) {
				System.out.println(roomArrayTest[i]);
			 }
		Customer[] customerArrayTest1 = HotelFileLoader.getCustomerListFromSequentialFile(
				"datafiles/unsorted/customers1.txt");
		for (Customer c: customerArrayTest1){
			System.out.println(c);
		}

		Customer[] customerArrayTest2 = HotelFileLoader.getCustomerListFromSequentialFile(
				"datafiles/unsorted/customers2.txt");
		for (Customer c: customerArrayTest2){
			System.out.println(c);
		}
		ListUtilities.sort(customerArrayTest1);
		ListUtilities.sort(customerArrayTest2);
	}
}
