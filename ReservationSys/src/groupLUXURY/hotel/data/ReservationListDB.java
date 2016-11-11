package groupLUXURY.hotel.data;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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
		this.factory = null;
	
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
			//check if the reservation overlaps with an existing one.
			if(this.database.get(i).overlap(reserv)) 
				throw new DuplicateReservationException();
		}
		Reservation copyReserv = factory.getReservationInstance(reserv.getCustomer(), reserv.getRoom(), 
		reserv.getCheckInDate().getMonthValue(), reserv.getCheckInDate().getDayOfMonth(), reserv.getCheckInDate().getYear(), 
		reserv.getCheckOutDate().getMonthValue(), reserv.getCheckOutDate().getDayOfMonth(), reserv.getCheckOutDate().getYear());

		database.add(copyReserv);
		// *  Note that the reservation must be added in correct order to keep the database in sorted order.
		// *   Implement a binary search private method to help. 
		// *   In your test class, verify that add works by invoking toString.

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
		String total ="Number of reservations in database: " + database.size();
		
		for (int i=0; i<database.size(); i++)
		{
			total += "\n" + database.get(i);
		}
		
		return total;
		//return "ReservationListDB [database=" + database + ", allRooms=" + allRooms + ", listPersistenceObject="
		//		+ listPersistenceObject + ", factory=" + factory + "]";
	}

}
