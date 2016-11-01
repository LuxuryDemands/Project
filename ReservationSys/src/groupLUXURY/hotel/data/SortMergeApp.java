package groupLUXURY.hotel.data;

import groupLUXURY.util.ListUtilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;

/**
 * Loads and sort the room text file. Loads, merges and sort the Customer text
 * file. Loads. merges and sorts the Reservation text file.
 * 
 * @author Sebastian, Maxime, Kajal, Isaak
 *
 */
public class SortMergeApp {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		createDatabaseDirectory();
		createSortedDirectory();
		sortMergeReservationList(sortMergeCustomerList(), loadSortRoomList());
	}

	/**
	 * Creates a new database folder if the directory does not exist.
	 *
	 */
	private static void createDatabaseDirectory() {
		Path folderPath = Paths.get("datafiles/database");
		if (Files.notExists(folderPath)) {
			File databaseDirectory = new File("datafiles/database");
			databaseDirectory.mkdir();
		}
	}

	/**
	 * Creates a new sorted folder if the directory does not exist.
	 *
	 */
	private static void createSortedDirectory() {
		Path folderPath = Paths.get("datafiles/sorted");
		if (Files.notExists(folderPath)) {
			File databaseDirectory = new File("datafiles/sorted");
			databaseDirectory.mkdir();
		}
	}

	/**
	 * Loads and sorts a Room[] from a sequential text file, saves the string
	 * representation of the new Room[] into a sequential text file.
	 *
	 * @throws IOException
	 * 
	 * @throws FileNotFoundException
	 *             if the file is not found.
	 * 
	 * @return roomList the Room list.
	 */
	private static Room[] loadSortRoomList() throws FileNotFoundException, IOException {
		Room[] roomList = HotelFileLoader.getRoomListFromSequentialFile("datafiles/unsorted/rooms.txt");
		ListUtilities.sort(roomList);
		ListUtilities.saveListToTextFile(roomList, "datafiles/database/rooms.txt");
		return roomList;
	}

	/**
	 * Loads, merges and sorts all the customer sequential text files into a
	 * single Customer[], saves the string representation of the resulting
	 * Customer[] into a sequential text file.
	 *
	 * @throws IOException
	 * 
	 * @throws FileNotFoundException
	 *             if the file is not found.
	 * 
	 * @return customerList the Customer list.
	 */
	@SuppressWarnings({ "rawtypes" })
	private static Customer[] sortMergeCustomerList() throws IOException {
		Customer[] customerList;
		for (int i = 1; i < 11; i++) {
			customerList = loadCustomerList("datafiles/unsorted/customers" + i + ".txt");
			ListUtilities.sort(customerList);
			ListUtilities.saveListToTextFile(customerList, "datafiles/sorted/sortedCustomers" + i + ".txt");
		}
		Comparable[] customerList2 = loadCustomerList("datafiles/unsorted/customers1.txt");
		for (int i = 2; i < 11; i++) {
			customerList2 = ListUtilities.merge(customerList2,
					loadCustomerList("datafiles/unsorted/customers" + i + ".txt"),
					"datafiles/duplicate/duplicates.txt");
		}
		ListUtilities.sort(customerList2);
		ListUtilities.saveListToTextFile(customerList2, "datafiles/database/customers.txt");
		return (Customer[]) customerList2;
	}

	/**
	 * Loads, merges and sorts all the reservation sequential text files into a
	 * single Reservation[], saves the string representation of the resulting
	 * Reservation[] into a sequential text file.
	 *
	 * @throws IOException
	 * 
	 * @throws FileNotFoundException
	 *             if the file is not found.
	 * 
	 * @return reservationList the Reservation list.
	 */
	@SuppressWarnings({ "rawtypes" })
	private static void sortMergeReservationList(Customer[] customerList, Room[] roomList) throws IOException, FileNotFoundException {
		Reservation[] reservationList;
		for (int i = 1; i < 11; i++) {
			reservationList = loadReservationList("datafiles/unsorted/reservations" + i + ".txt", customerList, roomList);
			ListUtilities.sort(reservationList);
			ListUtilities.saveListToTextFile(reservationList, "datafiles/sorted/sortedReservations" + i + ".txt");
		}
		Comparable[] reservationList2 = loadReservationList("datafiles/unsorted/reservations1.txt", customerList, roomList);
		for (int i = 2; i < 11; i++) {
			reservationList2 = ListUtilities.merge(reservationList2,
					loadReservationList(("datafiles/unsorted/reservations" + i + ".txt"), customerList, roomList),
					"datafiles/duplicate/duplicateReservations.txt");
		}
		ListUtilities.sort(reservationList2);
		ListUtilities.saveListToTextFile(reservationList2, "datafiles/database/reservations.txt");
	}
	/**
	 * Loads a single Customer[] from a single sequential text file.
	 *
	 * @throws IOException
	 * 
	 * @throws FileNotFoundException
	 *             if the file is not found.
	 * 
	 * @return customerList the Room list.
	 */
	private static Customer[] loadCustomerList(String path) throws FileNotFoundException, IOException {
		Customer[] customerList = HotelFileLoader.getCustomerListFromSequentialFile(path);
		return customerList;
	}
	/**
	 * Loads a single Reservation[] from a single sequential text file.
	 *
	 * @throws IOException
	 * 
	 * @throws FileNotFoundException
	 *             if the file is not found.
	 * 
	 * @return reservationList the Room list.
	 */
	private static Reservation[] loadReservationList(String path, Customer[] customerList, Room[] roomList)
			throws FileNotFoundException, IOException {
		Reservation[] reservationList = HotelFileLoader.getReservationListFromSequentialFile(path, customerList,
				roomList);
		return reservationList;
	}

}
