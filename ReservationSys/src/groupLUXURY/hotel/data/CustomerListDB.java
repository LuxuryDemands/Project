/**
 * 
 */
package groupLUXURY.hotel.data;

import java.io.IOException;
import java.util.List;

import dw317.hotel.business.DawsonHotelFactory;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import groupLUXURY.lib.Email;
import groupLUXURY.lib.creditcard.CreditCard;
import groupLUXURY.util.ListUtilities;

/**
 * @author 1331680
 *
 */
public class CustomerListDB implements CustomerDAO {
	public List<Customer> database;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;

	public CustomerListDB(ListPersistenceObject listPersistenceObject) {
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getCustomerDatabase();
		this.factory=DawsonHotelFactory.DAWSON;
	}

	public CustomerListDB(ListPersistenceObject listPersistenceObject, HotelFactory factory) {
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getCustomerDatabase();
		this.factory = factory;
	}

	@Override
	public void add(Customer cust) throws DuplicateCustomerException {
		if (!(ListUtilities.binarySearch(this.database, cust)==-1)){
			throw new DuplicateCustomerException("The specified email is already in the database.");
		}
		Customer copy = DawsonHotelFactory.DAWSON.getCustomerInstance(cust.getName().getFirstName(), cust.getName().getLastName(),
				cust.getEmail().toString());
		if (!(cust.getCreditCard()==null)){
			copy.setCreditCard(cust.getCreditCard());
		}
		int index = binarySearch(this.database,cust);
		this.database.add(index,cust);
	}

	private static int binarySearch(List<Customer> list, Customer key) {

		int low = 0;
		int high = list.size() - 1;
		int mid = low + (high - low) / 2;
		while (low <= high) {
			
			if (list.get(mid).compareTo(key) < 0) {
				low = mid + 1;
				mid = (low+high)/2;
			}
			else if (list.get(mid).compareTo(key) > 0) {
				high = mid - 1;
				mid = (low+high)/2;
			}
		}
		return mid;
	}

	@Override
	public void disconnect() throws IOException {
		ListUtilities.saveListToTextFile(database, "datafiles/database/savedToDisk");
		this.database=null;

	}

	@Override
	public Customer getCustomer(Email email) throws NonExistingCustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Email email, CreditCard card) throws NonExistingCustomerException {
		// TODO Auto-generated method stub

	}
	public String toString() {
		StringBuilder total = new StringBuilder("Number of reservations in database: " + database.size());
		
		for (int i=0; i<database.size(); i++)
		{
			total.append( "\n" + database.get(i));
		}
		return total.toString();
	}
}
