package groupLUXURY.hotel.data;

import java.util.ArrayList;
import java.util.List;

import dw317.hotel.business.DawsonCustomer;
import dw317.hotel.business.DawsonHotelFactory;
import dw317.hotel.business.DawsonReservation;
import dw317.hotel.business.DawsonRoom;
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import groupLUXURY.lib.creditcard.CreditCard;

public class ReservationListDBTest {

	public static void main (String[] args) throws DuplicateReservationException
	{
		
		

		
		testReservationListDB();
		System.out.println("\n\n\n");
		testBinarySearch();
		
		
		
	}
		
	public static void testReservationListDB() // constructor and toString test
	{
		String roomFilename="datafiles/database/rooms.txt";
		String customerFilename="datafiles/database/customers.txt";
		String reservationFilename="datafiles/database/reservations.txt";
		
		SequentialTextFileList myList = 
				new SequentialTextFileList(roomFilename,customerFilename,reservationFilename);
		
		ReservationListDB myDB = new ReservationListDB(myList);

		System.out.println(myDB.toString());
	}
	
	public static void testBinarySearch() throws DuplicateReservationException // binary search test
	{
		String roomFilename="datafiles/database/rooms.txt";
		String customerFilename="datafiles/database/customers.txt";
		String reservationFilename="datafiles/database/reservations.txt";
		
		SequentialTextFileList myList = 
				new SequentialTextFileList(roomFilename,customerFilename,reservationFilename);
		
		ReservationListDB myDB = new ReservationListDB(myList);

		
		
		
		
		

		DawsonRoom room = new DawsonRoom(801, RoomType.NORMAL);
		DawsonCustomer cust = new DawsonCustomer("fnam","lnam","ADDEDNEW@hot.com"); 
		DawsonReservation reserv = new DawsonReservation(cust, room, 2017,10,05,2017,11,06);

		System.out.println("adding new reservation: " + reserv);
				
		myDB.add(reserv);
	
		System.out.println(myDB.toString());	
		
	}
}
