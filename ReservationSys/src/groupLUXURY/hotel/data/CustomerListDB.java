/**
 * 
 */
package groupLUXURY.hotel.data;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import dw317.hotel.business.DawsonHotelFactory;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import groupLUXURY.lib.Email;
import groupLUXURY.lib.creditcard.CreditCard;

/**
 * Concrete class that provides CustomerDAO functionality
 * 
 * @author Sebastian, Max, Isaak, Kajal
 *
 */
public class CustomerListDB implements CustomerDAO {
	public List<Customer> database;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;

	/**
	 * Constructor requires a ListPersistenceObject to read from file.
	 * 
	 * @param listPersistenceObject
	 */

	public CustomerListDB(ListPersistenceObject listPersistenceObject) {
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getCustomerDatabase();
		this.factory = DawsonHotelFactory.DAWSON;
	}

	/**
	 * Constructor requires a ListPersistenceObject to read from file and a
	 * factory object.
	 * 
	 * @param listPersistenceObject
	 * 
	 * @param factory
	 */

	public CustomerListDB(ListPersistenceObject listPersistenceObject, HotelFactory factory) {
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getCustomerDatabase();
		this.factory = factory;
	}

	@Override
	public void add(Customer cust) throws DuplicateCustomerException {
		int index = binarySearch(cust);
		if (index > 0) {
			throw new DuplicateCustomerException("The specified email is already in the database.");
		} else {
			Customer copy = this.factory.getCustomerInstance(cust.getName().getFirstName(),
					cust.getName().getLastName(), cust.getEmail().toString());
			if (!(cust.getCreditCard() == null)) {
				copy.setCreditCard(cust.getCreditCard());
			}
			this.database.add(-(index + 1), copy);
		}

	}

	/**
	 * Searches for a customer in the customer database according to a given
	 * customer(key).
	 * 
	 * @param key
	 *            a customer
	 *            
	 * @return int mid if the key is found, -(low)
	 */
	private int binarySearch(Customer key) {

		int low = 0;
		int high = this.database.size() - 1;
		int mid = 0;

		while (low <= high) {
			mid = (high + low) / 2;
			if (this.database.get(mid).compareTo(key) < 0) {
				low = mid + 1;
			} else if (this.database.get(mid).compareTo(key) > 0) {
				high = mid - 1;
			} else if (this.database.get(mid).compareTo(key) == 0) {
				return mid;
			}

		}
		return -(low + 1);
	}

	@Override
	public void disconnect() throws IOException {
		listPersistenceObject.saveCustomerDatabase(this.database);
		this.database = null;

	}

	@Override
	public Customer getCustomer(Email email) throws NonExistingCustomerException {
		Customer cust = this.factory.getCustomerInstance("", "", email.toString());
		int index = binarySearch(cust);
		if (index < 0) {
			throw new NonExistingCustomerException("The specified customer does not exist.");
		} else {
			cust = this.database.get(index);
		}
		return cust;
	}

	@Override
	public void update(Email email, CreditCard card) throws NonExistingCustomerException {
		Customer cust = this.factory.getCustomerInstance("", "", email.toString());
		int index = binarySearch(cust);
		if (index < 0) {
			throw new NonExistingCustomerException("The specified customer does not exist");
		} else {
			this.database.get(index).setCreditCard(Optional.of(card));
		}

	}

	public String toString() {
		StringBuilder total = new StringBuilder("Number of reservations in database: " + this.database.size());

		for (int i = 0; i < this.database.size(); i++) {
			total.append("\n" + database.get(i));
		}
		return total.toString();
	}
}
