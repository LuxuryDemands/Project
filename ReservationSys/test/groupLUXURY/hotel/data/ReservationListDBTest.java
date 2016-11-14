package groupLUXURY.hotel.data;

import java.io.IOException;
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
		
		
		String roomFilename="datafiles/database/roomsTest.txt";
		String customerFilename="datafiles/database/customersTest.txt";
		String reservationFilename="datafiles/database/reservationsTest.txt";		
		SequentialTextFileList myList = new SequentialTextFileList(roomFilename,customerFilename,reservationFilename);
		ReservationListDB myDB = new ReservationListDB(myList);

		//test 1
		testToString(myDB);
		System.out.println("\n\n\n");
	
		//test2
		testBinarySearch(myDB);
		
		
		
		
		//test3
		
		   //disconnect
			try {
				testDisconnect(myDB);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\n>>>disconnected!");
			
		  //reconnect:
			myDB = new ReservationListDB(new SequentialTextFileList(roomFilename,customerFilename, reservationFilename));

			System.out.println("\n>>>reconnected!\n");
			testToString(myDB);
			
		
	}
		
	public static void testToString(ReservationListDB myDB) // constructor and toString test
	{
		
		System.out.println(myDB.toString());
	}
	
	public static void testBinarySearch(ReservationListDB myDB) // binary search test
	{
		DawsonRoom room = new DawsonRoom(801, RoomType.PENTHOUSE);
		DawsonCustomer cust = new DawsonCustomer("fnam","lnam","homerSimpson@gmail.com"); 
		DawsonReservation reserv = new DawsonReservation(cust, room, 2018,10,05,2018,11,06);

		System.out.println("adding new reservation: " + reserv);
			
		try{

			myDB.add(reserv);
		}
		catch( DuplicateReservationException d)
		{
			System.out.println("Reservation duplicate!");
		}
	
		System.out.println(myDB.toString());	
		
	}
	

	public static void testDisconnect(ReservationListDB myDB) throws IOException
	{
		myDB.disconnect();
	}
}
