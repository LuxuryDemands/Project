package groupLUXURY.hotel.data;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.DawsonCustomer;
import dw317.hotel.business.DawsonHotelAllocationPolicy;
import dw317.hotel.business.DawsonReservation;
import dw317.hotel.business.DawsonRoom;
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Room;
import groupLUXURY.util.ListUtilities;

public class ReservationListDBTest {

	public static void main(String[] args) throws DuplicateReservationException, IOException {

		// //test 1
		// testToString();
		// //test 2
		// testBinarySearch();
		// //test 3
		// testDisconnect();
		// //test 4
		// testGetReservedRooms();
		// //test 5
		double startTime = System.nanoTime();
		testGetFreeRooms();
		double endTime = System.nanoTime();
		double duration = (endTime - startTime)/1000000.0; 
		System.out.println(duration);
		// //test 6
		//testGetFreeRooms2();
		// //test 7
		// testClearAllPast();
		// testDAP();
	}

	private static void setup() {
		String[] rooms = new String[10];
		rooms[0] = "101*normal";
		rooms[1] = "102*normal";
		rooms[2] = "301*suite";
		rooms[3] = "401*penthouse";
		rooms[4] = "305*normal";
		rooms[5] = "701*suite";
		rooms[6] = "505*normal";
		rooms[7] = "801*penthouse";
		rooms[8] = "704*suite";
		rooms[9] = "306*normal";

		String[] custs = new String[8];
		custs[0] = "raj@aing.ru*Raj*Wong*visa*4556737586899855";
		custs[1] = "joe.mancini@mail.me*Joe*Mancini**";
		custs[2] = "Jack_Bauer24@gmail.ca*Jack*Bauer*visa*4539663276313217";
		custs[3] = "chrissytocool404@hitmall.com*Chrissy*Adams*mastercard*5209371001459604";
		custs[4] = "G8772375@hotmail.com*gabrielle*montcalm**";
		custs[5] = "PhillipFry_3940@outlook.ca*Phillip*Fry*amex*345780742674865";
		custs[6] = "the_blur@live.ca*Barry*Allen*visa*4929042939787133";
		custs[7] = "d@zzz.com*Da*Ja*amex*345780742674865";

		String[] reservs = new String[8];
		reservs[0] = "raj@aing.ru*2016*12*10*2016*12*15*101";
		reservs[1] = "joe.mancini@mail.me*2016*10*10*2016*10*20*401";
		reservs[2] = "the_blur@live.ca*2012*6*14*2012*6*21*305";
		reservs[3] = "Jack_Bauer24@gmail.ca*2015*11*5*2015*11*10*701";
		reservs[4] = "chrissytocool404@hitmall.com*2016*2*22*2016*3*17*505";
		reservs[5] = "G8772375@hotmail.com*2016*9*18*2016*9*19*801";
		reservs[6] = "d@zzz.com*2016*10*11*2016*11*2*704";
		reservs[7] = "d@zzz.com*2016*10*12*2016*10*15*101";

		File dir = new File("testfiles");
		try {
			if (!dir.exists()) {
				dir.mkdirs();
			}
			ListUtilities.saveListToTextFile(reservs, "testfiles/testReservations.txt");
			ListUtilities.saveListToTextFile(rooms, "testfiles/testRooms.txt");
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

	public static void testToString() // toString test
	{
		setup();
		SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB reservDB = new ReservationListDB(file);

		System.out.println(reservDB.toString());

		teardown();
	}

	public static void testBinarySearch() // binary search
											// test
	{

		setup();
		SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB reservDB = new ReservationListDB(file);

		System.out.println("\n" + reservDB.toString());

		DawsonRoom room = new DawsonRoom(801, RoomType.PENTHOUSE);
		DawsonCustomer cust = new DawsonCustomer("fnam", "lnam", "AllenWilson123@gmail.com");
		DawsonReservation reserv = new DawsonReservation(cust, room, 2017, 10, 05, 2017, 11, 06);

		System.out.println("adding new reservation: " + reserv);

		try {

			reservDB.add(reserv);
		} catch (DuplicateReservationException d) {
			System.out.println("Room duplicate!");
		}

		System.out.println(reservDB.toString());

		teardown();

	}

	public static void testDisconnect() throws IOException {
		setup();

		SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB reservDB = new ReservationListDB(file);
		System.out.println(reservDB.toString());
		DawsonRoom room = new DawsonRoom(801, RoomType.PENTHOUSE);
		DawsonCustomer cust = new DawsonCustomer("fnam", "lnam", "raj@aing.ru");
		DawsonReservation reserv = new DawsonReservation(cust, room, 2017, 10, 05, 2017, 11, 06);

		try {
			reservDB.add(reserv);
		} catch (DuplicateReservationException e) {
		}

		reservDB.disconnect();
		System.out.println("\n>>>disconnected!");

		ReservationListDB newList = new ReservationListDB(file);

		System.out.println("\n>>>reconnected!\n");
		System.out.println(newList);
	}

	public static void testGetReservedRooms() {
		setup();

		SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB reservDB = new ReservationListDB(file);

		LocalDate checkIn = LocalDate.of(2016, 1, 1);
		LocalDate checkOut = LocalDate.of(2016, 12, 31);
		List<Room> rooms = reservDB.getReservedRooms(checkIn, checkOut);

		System.out.println("start");
		for (Room r : rooms) {
			System.out.println(r);
		}
		teardown();

		System.out.println("end size=" + rooms.size());

	}

	public static void testGetFreeRooms() {
		setup();

		SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB reservDB = new ReservationListDB(file);

		LocalDate checkIn = LocalDate.of(2016, 1, 1);
		LocalDate checkOut = LocalDate.of(2016, 12, 31);
		List<Room> rooms = reservDB.getFreeRooms(checkIn, checkOut);
		System.out.println("start");
		for (Room r : rooms) {
			System.out.println(r);
		}
		teardown();

		System.out.println("end size=" + rooms.size());

	}

	public static void testGetFreeRooms2() {
		setup();

		SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB reservDB = new ReservationListDB(file);

		LocalDate checkIn = LocalDate.of(2016, 1, 1);
		LocalDate checkOut = LocalDate.of(2016, 12, 31);
		List<Room> rooms = reservDB.getFreeRooms(checkIn, checkOut, RoomType.NORMAL);
		System.out.println("start");
		for (Room r : rooms) {
			System.out.println(r);
		}
		teardown();

		System.out.println("end size=" + rooms.size());

	}

	public static void testClearAllPast() {
		setup();

		SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB reservDB = new ReservationListDB(file);
		reservDB.clearAllPast();
		System.out.println(reservDB.toString());
		teardown();

	}

	public static void testDAP() {
		setup();

		SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB reservDB = new ReservationListDB(file);
		DawsonHotelAllocationPolicy dap = new DawsonHotelAllocationPolicy(reservDB);
		LocalDate checkIn = LocalDate.of(2016, 1, 1);
		LocalDate checkOut = LocalDate.of(2016, 12, 31);
		Optional<Room> freeRoom = dap.getAvailableRoom(checkIn, checkOut, RoomType.NORMAL);
		System.out.println(freeRoom);
		teardown();

	}
}
