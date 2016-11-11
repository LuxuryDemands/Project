/**
 * 
 */
package groupLUXURY.hotel.data;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.DawsonHotelFactory;
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Room;
import groupLUXURY.lib.creditcard.CreditCard;
import groupLUXURY.util.ListUtilities;

/**
 * @author 1331680
 *
 */
public class CustomerListDBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testGetCustomers();
	}
	//joe.mancini@mail.me*Joe*Mancini**"
	private static void setup() {
		String[] custs = new String[8];
		custs[0] = "raj@aing.ru*Raj*Wong*visa*4556737586899855";
		custs[1] = "joe.mancini@mail.me*Joe*Mancini**";
		custs[2] = "Jack_Bauer24@gmail.ca*Jack*Bauer*visa*4539663276313217";
		custs[3] = "chrissytocool404@hitmall.com*Chrissy*Adams*mastercard*5209371001459604";
		custs[4] = "G8772375@hotmail.com*gabrielle*montcalm**";
		custs[5] = "PhillipFry_3940@outlook.ca*Phillip*Fry*amex*345780742674865";
		custs[6] = "the_blur@live.ca*Barry*Allen*visa*4929042939787133";
		custs[7] = "d@zzz.com*Da*Ja*amex*345780742674865";

		File dir = new File("testfiles");
		try {
			if (!dir.exists()) {
				dir.mkdirs();
			}
			ListUtilities.saveListToTextFile(custs, "testfiles/testCustomers.txt");
		} catch (IOException io) {
			System.out.println("Error creating file in setUp()");
		}
	}

	private static void teardown() {
		File theFile = new File("testfiles/testRooms.txt");
		if (theFile.exists()) {
			theFile.delete();
		}
		theFile = new File("testfiles/testCustomers.txt");
		if (theFile.exists()) {
			theFile.delete();
		}
		theFile = new File("testfiles/testReservations.txt");
		if (theFile.exists()) {
			theFile.delete();
		}
	}
	private static void testAddCustomer(){
		setup();
		SequentialTextFileList file = new SequentialTextFileList
				("testfiles/testRooms.txt", "testfiles/testCustomers.txt",
						"testfiles/testReservations.txt");
		CustomerListDB db = new CustomerListDB(file);
		//"raj@aing.ru*Raj*Wong*visa*4556737586899855"
		Customer dup = DawsonHotelFactory.DAWSON.getCustomerInstance("Raj", "Wong","raj@aing.ru");
		CreditCard card = DawsonHotelFactory.DAWSON.getCard("visa", "4556737586899855");
		dup.setCreditCard(Optional.of(card));
		//db.add(cust);
	}

	private static void testGetCustomers() {
		setup();
		SequentialTextFileList file = new SequentialTextFileList
				("testfiles/testRooms.txt", "testfiles/testCustomers.txt",
						"testfiles/testReservations.txt");
		CustomerListDB db = new CustomerListDB(file);
		for (Customer c: db.database){
			System.out.println(c);
		}
//		System.out.println("Normal rooms:");
//		Customer<Room> normal = db.getRooms(RoomType.NORMAL);
//		for (Room r : normal)
//			System.out.println(r.toString());			
//
//		System.out.println("Suites:");
//		List<Room> suite = db.getRooms(RoomType.SUITE);
//		for (Room r : suite)
//			System.out.println(r.toString());
//		
//		System.out.println("Penthouse rooms:");
//		List<Room> ph = db.getRooms(RoomType.PENTHOUSE);
//		for (Room r : ph)
//			System.out.println(r.toString());
		
		teardown();
	}

}
