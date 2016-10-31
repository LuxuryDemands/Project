/**
 * 
 */
package group.util;

import java.io.FileNotFoundException;
import java.io.IOException;

import groupLUXURY.hotel.data.HotelFileLoader;
import groupLUXURY.util.ListUtilities;

/**
 * @author Sebastian
 *
 */
public class ListUtilitiesTest {

	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		testTheMergeMethod();
	}

	@SuppressWarnings("rawtypes")
	public static void testTheMergeMethod() throws FileNotFoundException, IOException {
		Comparable[] customerList1 = HotelFileLoader
				.getCustomerListFromSequentialFile("../ReservationSys/datafiles/unsorted/customers1.txt");
		Comparable[] customerList2 = HotelFileLoader
				.getCustomerListFromSequentialFile("../ReservationSys/datafiles/unsorted/customers2.txt");
		System.out.println("\nTesting the three parameter constructor.");
		testTheMergeMethod("Case 1A - Valid data (datafiles/unsorted/rooms.txt)", customerList1, customerList2,
				"../ReservationSys/datafiles/duplicate/duplicates.txt", true);
	}

	@SuppressWarnings("rawtypes")
	private static void testTheMergeMethod(String testCase, Comparable[] list1, Comparable[] list2,
			String duplicateFilename, boolean expectValid) throws FileNotFoundException, IOException {
		System.out.println("   " + testCase);
		ListUtilities.merge(list1, list2, duplicateFilename);
		System.out.print("\tThe Room list instance was created: ");
		if (!expectValid)
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		System.out.println("\n");
	}
}
