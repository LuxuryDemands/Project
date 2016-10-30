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
 * @author Sebastian, Maxime, Kajal, Isaak
 *
 */
public class SortMergeApp {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		createDatabaseDirectory();
		createSortedDirectory();
		sortMergeReservationList(sortMergeCustomerList(),loadSortRoomList());
	}

	private static void createDatabaseDirectory() {
		Path folderPath = Paths.get("datafiles/database");
		if (Files.notExists(folderPath)) {
			File databaseDirectory = new File("datafiles/database");
			databaseDirectory.mkdir();
		}
	}

	private static void createSortedDirectory() {
		Path folderPath = Paths.get("datafiles/sorted");
		if (Files.notExists(folderPath)) {
			File databaseDirectory = new File("datafiles/sorted");
			databaseDirectory.mkdir();
		}
	}

	private static Room[] loadSortRoomList() throws FileNotFoundException, IOException {
		Room[] roomList = HotelFileLoader.getRoomListFromSequentialFile("datafiles/unsorted/rooms.txt");
		ListUtilities.sort(roomList);
		ListUtilities.saveListToTextFile(roomList, "datafiles/database/rooms.txt");
		return roomList;
	}

	@SuppressWarnings({ "rawtypes" })
	private static Customer[] sortMergeCustomerList() throws IOException {
		Customer[] holder;
		for (int i = 1; i < 11; i++) {
			holder = loadCustomerList("datafiles/unsorted/customers" + i + ".txt");
			ListUtilities.sort(holder);
			ListUtilities.saveListToTextFile(holder, "datafiles/sorted/sortedCustomers" + i + ".txt");
		}
		Comparable[] holder2 = loadCustomerList("datafiles/unsorted/customers1.txt");
		for (int i = 2; i < 11; i++) {
			holder2 = ListUtilities.merge(holder2, loadCustomerList("datafiles/unsorted/customers" + i + ".txt"),
					"datafiles/duplicate/duplicates.txt");
		}
		ListUtilities.sort(holder2);
		ListUtilities.saveListToTextFile(holder2, "datafiles/database/customers.txt");
		return (Customer[]) holder2;
	}

	@SuppressWarnings({ "rawtypes" })
	private static void sortMergeReservationList(Customer[] customerList, Room[] roomList) throws IOException {
		Reservation[] holder;
		for (int i = 1; i < 11; i++) {
			holder = loadReservationList("datafiles/unsorted/reservations" + i + ".txt", customerList, roomList);
			ListUtilities.sort(holder);
			ListUtilities.saveListToTextFile(holder, "datafiles/sorted/sortedReservations" + i + ".txt");
		}
		Comparable[] holder2 = loadReservationList("datafiles/unsorted/reservations1.txt", customerList, roomList);
		for (int i = 2; i < 11; i++) {
			holder2 = ListUtilities.merge(holder2,
					loadReservationList(("datafiles/unsorted/reservations"+i+".txt"), customerList, roomList),
					"datafiles/duplicate/duplicateReservations.txt");
		}
		for (Comparable c: holder2){
			System.out.println(c);
		}
		ListUtilities.sort(holder2);
		ListUtilities.saveListToTextFile(holder2, "datafiles/database/reservations.txt");
	}

	private static Customer[] loadCustomerList(String path) throws FileNotFoundException, IOException {
		Customer[] customerList = HotelFileLoader.getCustomerListFromSequentialFile(path);
		return customerList;
	}

	private static Reservation[] loadReservationList(String path, Customer[] customerList, Room[] roomList)
			throws FileNotFoundException, IOException {
		Reservation[] reservationList = HotelFileLoader.getReservationListFromSequentialFile(path, customerList,
				roomList);
		return reservationList;
	}

}
