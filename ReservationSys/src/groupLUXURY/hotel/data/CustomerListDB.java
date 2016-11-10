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

/**
 * @author 1331680
 *
 */
public class CustomerListDB implements CustomerDAO {
	private List<Customer> database;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;

	public CustomerListDB(ListPersistenceObject listPersistenceObject) {
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getCustomerDatabase();
	}

	public CustomerListDB(ListPersistenceObject listPersistenceObject, HotelFactory factory) {
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getCustomerDatabase();
		this.factory = factory;
	}

	@Override
	public void add(Customer cust) throws DuplicateCustomerException {
		Customer copy = DawsonHotelFactory.DAWSON.getCustomerInstance(cust.getName().getFirstName(), cust.getName().getLastName(),
				cust.getEmail().toString());
		

	}

	@Override
	public void disconnect() throws IOException {
		// TODO Auto-generated method stub

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

}
