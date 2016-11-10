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
	
	
	
	
	
	
	
	@Override
	public void add(Reservation reserv) throws DuplicateReservationException 
	{
		// TODO Auto-generated method stub
		//binary search
		overlap
		
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
