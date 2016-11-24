/**
 * 
 */
package groupLUXURY.hotel.data;

import java.io.IOException;
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
		List<Room> rooms;
			try {
				rooms = (List<Room>) Utilities.deserializeObject(roomFilename);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		

	}

	@Override
	public List<Customer> getCustomerDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getReservationDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCustomerDatabase(List<Customer> custs) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveReservationDatabase(List<Reservation> reservs) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
