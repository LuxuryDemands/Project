package groupLUXURY.hotel.data;

import java.util.List;

import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.data.interfaces.ListPersistenceObject;

public class ReservationListDBTest {

	public static void main (String[] args)
	{
		testReservationListDB();
		
		
	}
		
	public static void testReservationListDB()
	{
		String roomFilename="datafiles/database/rooms.txt";
		String customerFilename="datafiles/database/customers.txt";
		String reservationFilename="datafiles/database/reservations.txt";
		
		SequentialTextFileList myList = 
				new SequentialTextFileList(roomFilename,customerFilename,reservationFilename);
		
		ReservationListDB myDB = new ReservationListDB(myList);

		System.out.println(myDB.toString());
	}
}
