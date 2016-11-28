/**
 * 
 */
package groupLUXURY.hotel.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import groupLUXURY.util.Utilities;

/**
 * @author 1331680
 *
 */
public class SerializedFileLoaderApp {
	/**
	 * @param args
	 */
	static SequentialTextFileList stfl = new SequentialTextFileList("datafiles/database/rooms.txt",
			"datafiles/database/customers.txt", "datafiles/database/reservations.txt");
	public static void main(String[] args) {
		
		createDatabaseDirectory();
		serializeRoomList();
		serializeCustomerList();
		serializeReservationList();
	}

	private static void createDatabaseDirectory() {
		Path folderPath = Paths.get("datafiles/database");
		if (Files.notExists(folderPath)) {
			File databaseDirectory = new File("datafiles/database");
			databaseDirectory.mkdir();
		}

	}
	private static void serializeRoomList(){
		try {
			Utilities.serializeObject(loadRooms(), "datafiles/database/rooms.ser");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void serializeCustomerList(){
		try {
			Utilities.serializeObject(loadCustomers(), "datafiles/database/customers.ser");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void serializeReservationList(){
		try {
			Utilities.serializeObject(loadReservations(), "datafiles/database/reservations.ser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static List<Room> loadRooms(){
		return stfl.getRoomDatabase();
	}
	private static List<Customer> loadCustomers(){
		return stfl.getCustomerDatabase();
	}
	private static List<Reservation> loadReservations(){
		return stfl.getReservationDatabase();
	}
	
}