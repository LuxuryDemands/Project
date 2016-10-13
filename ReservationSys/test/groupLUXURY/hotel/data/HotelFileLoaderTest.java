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
//		Room[] roomArrayTest = HotelFileLoader.getRoomListFromSequentialFile(
//				"C:\\Users\\Sebastian\\Desktop\\Eclipse\\ReservationSys\\datafiles\\rooms.txt");
		// for (int i = 0; i < roomArrayTest.length; i++) {
		// System.out.println(roomArrayTest[i]);
		// }
		Customer[] customerArrayTest1 = HotelFileLoader.getCustomerListFromSequentialFile(
				"C:\\Users\\1331680\\Desktop\\datafiles\\customers1.txt");
		Customer[] customerArrayTest2 = HotelFileLoader.getCustomerListFromSequentialFile(
				"C:\\Users\\1331680\\Desktop\\datafiles\\customers2.txt");
//		ListUtilities.sort(customerArrayTest1);
//		ListUtilities.sort(customerArrayTest2);
//		for (int i = 0; i < customerArrayTest1.length; i++) {
//			System.out.println(customerArrayTest1[i]);
//		}
		@SuppressWarnings("rawtypes")
		Comparable[] mergeList = ListUtilities.merge(customerArrayTest1,customerArrayTest2);
		System.out.println(ListUtilities.countDuplicates(mergeList));
		for (int i = 0; i<mergeList.length;i++){
			System.out.println(mergeList[i]);
		}
	}
}
