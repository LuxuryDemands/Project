package groupLUXURY.hotel.data;

import groupLUXURY.util.ListUtilities;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
		loadSortMergeCustomerList();
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

	private static void loadSortMergeCustomerList() throws IOException {
		try {
			for (int customerIndex = 1; customerIndex < 11; customerIndex++) {
				Customer[] customerList = HotelFileLoader
						.getCustomerListFromSequentialFile("datafiles/unsorted/customers" + customerIndex + ".txt");
			}
		} catch (FileNotFoundException e) {

		}
	}

}
