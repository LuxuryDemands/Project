/**
 * 
 */
package groupLUXURY.hotel.data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import groupLUXURY.util.Utilities;

/**
 * @author Sebastian
 *
 */
public class ObjectSerializedList implements ListPersistenceObject {
	private final String roomFilename;
	private final String customerFilename;
	private final String reservationFilename;
	
	public ObjectSerializedList(String roomFilename, String customerFilename, String reservationFilename){
		this.roomFilename = roomFilename;
		this.customerFilename = customerFilename;
		this.reservationFilename = reservationFilename;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> getRoomDatabase() {
		List<Room> rooms = null;
			try {
				rooms = (List<Room>) Utilities.deserializeObject(roomFilename);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return rooms;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomerDatabase() {
		List<Customer> cust = null;
		try {
			cust = (List<Customer>) Utilities.deserializeObject(customerFilename);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cust;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getReservationDatabase() {
		List<Reservation> reserv = null;
		try {
			reserv = (List<Reservation>) Utilities.deserializeObject(reservationFilename);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reserv;
	}

	@Override
	public void saveCustomerDatabase(List<Customer> custs) throws IOException {
		ObjectOutputStream oos = null;
		FileOutputStream fout = null;
		try{
		    fout = new FileOutputStream(this.customerFilename, true);
		    oos = new ObjectOutputStream(fout);
		    oos.writeObject(custs);
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    if(oos  != null){
		        oos.close();
		    } 
		}
	}

	@Override
	public void saveReservationDatabase(List<Reservation> reservs) throws IOException {
		ObjectOutputStream oos = null;
		FileOutputStream fout = null;
		try{
		    fout = new FileOutputStream(this.reservationFilename, true);
		    oos = new ObjectOutputStream(fout);
		    oos.writeObject(reservs);
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    if(oos  != null){
		        oos.close();
		    } 
		}
	}

}
