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
		loadSortRoomList();
		sortMergeCustomerList();
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

	private static void loadSortRoomList() throws FileNotFoundException, IOException {
		Room[] roomList = HotelFileLoader.getRoomListFromSequentialFile("datafiles/unsorted/rooms.txt");
		ListUtilities.sort(roomList);
		ListUtilities.saveListToTextFile(roomList, "datafiles/database/rooms.txt");
	}

	@SuppressWarnings("rawtypes")
	private static void sortMergeCustomerList() throws IOException {
		try {
			Comparable[] test = loadCustomerList("datafiles/unsorted/customers1.txt");
			for (int i = 2; i < 11; i++) {
				test = ListUtilities.merge(test, loadCustomerList("datafiles/unsorted/customers" + i + ".txt"));
			}
			ListUtilities.saveListToTextFile(test, "datafiles/sorted/customers.txt");
		} catch (FileNotFoundException e) {

		}
	}

	private static Customer[] loadCustomerList(String path) throws FileNotFoundException, IOException {
		Customer[] customerList = HotelFileLoader.getCustomerListFromSequentialFile(path);
		return customerList;
	}

}
