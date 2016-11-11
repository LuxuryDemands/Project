package groupLUXURY.hotel.data;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import dw317.hotel.business.DawsonHotelFactory;
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.hotel.data.interfaces.ReservationDAO;

public class ReservationListDB implements ReservationDAO {

	private List<Reservation> database;
	private List<Room> allRooms;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;
	
	public ReservationListDB (ListPersistenceObject listPersistenceObject)
	{
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getReservationDatabase();
		this.allRooms = listPersistenceObject.getRoomDatabase();
		this.factory = DawsonHotelFactory.DAWSON;
	
	}
	
	public ReservationListDB(ListPersistenceObject listPersistenceObject, HotelFactory factory) {
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getReservationDatabase();
		this.allRooms = listPersistenceObject.getRoomDatabase();
		this.factory = factory;
	}
	
	
	
	
	
	/***
	 * 
	 * Checks if the reservation overlaps with an existing reservation.
	 * If the reservation does not overlap, 
	 *  adds a reference to a copy of the object referenced by the reserv 
	 *   
	 *  
	
	 */
	
	@Override
	public void add(Reservation reserv) throws DuplicateReservationException 
	{
		for (int i=0; i<database.size(); i++)
		{
			if(this.database.get(i).overlap(reserv)) 	//check if the reservation overlaps with an existing one.
				throw new DuplicateReservationException();
		}
		Reservation copyReserv = factory.getReservationInstance(
				reserv.getCustomer(), 
				reserv.getRoom(), 
				reserv.getCheckInDate().getYear(), 
				reserv.getCheckInDate().getMonthValue(),
				reserv.getCheckInDate().getDayOfMonth(), 
				reserv.getCheckOutDate().getYear(),
				reserv.getCheckOutDate().getMonthValue(),
				reserv.getCheckOutDate().getDayOfMonth() );

		int index = binarySearch(copyReserv);
		database.add(index, copyReserv);
	}
	
	private int binarySearch(Reservation keyReserv)
	{
		int low = 0;
		int high = database.size()-1;
		int mid = 0;
		while (low <= high)
		{
			mid = (low+high)/2;
			if (database.get(mid).compareTo(keyReserv) >0)// copy is smaller
			{
				high = mid-1;
			}
			else if (database.get(mid).compareTo(keyReserv) <0) //copy is bigger
			{
				low = mid+1;
			}
		}
		return low;
	}

	@Override
	public void disconnect() throws IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Reservation> getReservations(Customer cust) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancel(Reservation reserv) throws NonExistingReservationException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Room> getReservedRooms(LocalDate checkin, LocalDate checkout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout, RoomType roomType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearAllPast() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		StringBuilder total = new StringBuilder("Number of reservations in database: " + database.size());
		
		for (int i=0; i<database.size(); i++)
		{
			total.append( "\n" + database.get(i));
		}
		
		return total.toString();
		//return "ReservationListDB [database=" + database + ", allRooms=" + allRooms + ", listPersistenceObject="
		//		+ listPersistenceObject + ", factory=" + factory + "]";
	}

}
