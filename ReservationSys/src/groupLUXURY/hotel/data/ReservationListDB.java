package groupLUXURY.hotel.data;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dw317.hotel.business.DawsonHotelFactory;
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.hotel.data.interfaces.ReservationDAO;
import groupLUXURY.util.ListUtilities;

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
	@Override
	public void add(Reservation reserv) throws DuplicateReservationException 
	{
		int index = binarySearch(reserv);
		if (index>0){
			throw new DuplicateReservationException("The specified reservation already exists");
		}
		else if(index==-99999){
			throw new DuplicateReservationException("The specified reservation overlaps with another reservation");
		}
		else{
			Reservation copyReserv = factory.getReservationInstance(
					reserv.getCustomer(), 
					reserv.getRoom(), 
					reserv.getCheckInDate().getYear(), 
					reserv.getCheckInDate().getMonthValue(),
					reserv.getCheckInDate().getDayOfMonth(), 
					reserv.getCheckOutDate().getYear(),
					reserv.getCheckOutDate().getMonthValue(),
					reserv.getCheckOutDate().getDayOfMonth() );
			this.database.add(-(index+1),copyReserv);
		}
	}
	private int binarySearch(Reservation key) {

		int low = 0;
		int high = this.database.size() - 1;
		int mid = 0;
				
		while (low <= high) {
			mid = (high+low)/2;
			if (this.database.get(mid).compareTo(key) < 0) {
				if (this.database.get(mid).overlap(key)){
					return -99999;
				}
				low = mid + 1;
			}
			else if (this.database.get(mid).compareTo(key) > 0) {
				if (this.database.get(mid).overlap(key)){
					return -99999;
				}
				high = mid - 1;
			}
			else if (this.database.get(mid).compareTo(key)==0){
				return mid;
			}
			
		}
		return -(low+1);
}

	@Override
	public void disconnect() throws IOException 
	{
		listPersistenceObject.saveReservationDatabase(this.database);
		this.database=null;
	}

	@Override
	public List<Reservation> getReservations(Customer cust) {
		List<Reservation> reservations = new ArrayList<>();
		for (Reservation r: this.database){
			if (r.getCustomer().equals(cust)){
				reservations.add(r);
			}
		}
		return reservations;
	}

	@Override
	public void cancel(Reservation reserv) throws NonExistingReservationException {
		int index = binarySearch(reserv);
		if (index>0){
			this.database.remove(index);
		}
		else{
			throw new NonExistingReservationException("No such reservation was found in the database");
		}
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
	}

}
