/**
 * 
 */
package groupLUXURY.hotel.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;

/**
 * Test for the HotelFileLoader methods.
 * 
 * @author Sebastian, Kajal, Isaak, Maxime
 *
 */
public class HotelFileLoaderTest {
	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		testGetRoomFromSequentialFile();
	}

	public static void testGetRoomFromSequentialFile() throws FileNotFoundException, IOException {
		System.out.println("\nTesting the three parameter constructor.");
		testGetRoomFromSequentialFile("Case 1A - Valid data (datafiles/unsorted/rooms.txt)",
				"datafiles/unsorted/rooms.txt", true);
		// testGetRoomFromSequentialFile("Case 2A - Invalid data
		// (datafiles/unsorted/rooms.txt - no such file exists)",
		// "datafiles/unsorted/room.txt", false);
		// testGetRoomFromSequentialFile("Case 3A - Invalid data
		// (datafiles/unsorted/customers1.txt - not a room file)",
		// "datafiles/unsorted/customers1.txt", false);
		testGetCustomerFromSequentialFile("Case 1B - Valid data (datafiles/unsorted/customers1.txt)",
				"datafiles/unsorted/customers1.txt", true);
		// testGetCustomerFromSequentialFile("Case 2B - Invalid data
		// (datafiles/unsorted/customer1.txt - no such file exists)",
		// "datafiles/unsorted/customer1.txt", false);
		// testGetCustomerFromSequentialFile(
		// "Case 3B - Invalid data (datafiles/unsorted/rooms.txt - not a
		// customers file)",
		// "datafiles/unsorted/rooms.txt", false);
		testGetReservationFromSequentialFile("Case 1C - Valid data (datafiles/unsorted/reservations1.txt)",
				"datafiles/unsorted/reservations1.txt", "datafiles/unsorted/customers1.txt",
				"datafiles/unsorted/rooms.txt", true);
		// testGetReservationFromSequentialFile(
		// "Case 2C - Invalid data (datafiles/unsorted/reservation1.txt - no
		// such file exists)",
		// "datafiles/unsorted/reservation1.txt",
		// "datafiles/unsorted/customers1.txt",
		// "datafiles/unsorted/rooms.txt", false);
		// testGetReservationFromSequentialFile(
		// "Case 3C - Invalid data (datafiles/unsorted/customers1.txt - not a
		// reservations file)",
		// "datafiles/unsorted/customers1.txt",
		// "datafiles/unsorted/customers1.txt",
		// "datafiles/unsorted/rooms.txt", false);
	}

	@SuppressWarnings("unused")
	private static void testGetRoomFromSequentialFile(String testCase, String path, boolean expectValid)
			throws FileNotFoundException, IOException {
		System.out.println("   " + testCase);
		Room[] roomList = HotelFileLoader.getRoomListFromSequentialFile(path);
		System.out.print("\tThe Room list instance was created: ");
		if (!expectValid)
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		System.out.println("\n");
	}

	@SuppressWarnings("unused")
	private static void testGetCustomerFromSequentialFile(String testCase, String path, boolean expectValid)
			throws FileNotFoundException, IOException {
		System.out.println("   " + testCase);
		Customer[] customerList = HotelFileLoader.getCustomerListFromSequentialFile(path);
		System.out.print("\tThe Customer list instance was created: ");
		if (!expectValid)
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		System.out.println("\n");
	}

	@SuppressWarnings("unused")
	private static void testGetReservationFromSequentialFile(String testCase, String reservationPath,
			String customerPath, String roomPath, boolean expectValid) throws FileNotFoundException, IOException {
		Customer[] customerList = HotelFileLoader.getCustomerListFromSequentialFile(customerPath);
		Room[] roomList = HotelFileLoader.getRoomListFromSequentialFile(roomPath);
		System.out.println("   " + testCase);
		Reservation[] reservationList = HotelFileLoader.getReservationListFromSequentialFile(reservationPath,
				customerList, roomList);
		System.out.print("\tThe Reservation list instance was created: ");
		if (!expectValid)
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		System.out.println("\n");
	}

}
