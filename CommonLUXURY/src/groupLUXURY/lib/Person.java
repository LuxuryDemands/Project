/**
 * 
 */
package groupLUXURY.lib;

import java.util.Optional;

/**
 * @author Sebastian, Max, isaak
 */
public class Person {
	protected Name name;
	protected Address address;

	/**
	 * Person constructor with 2 params which sets the new instance of name with the firstname and lastName params
	 * then creates a new address.
	 * @param firstName The first name
	 * @param lastName The last name
	 */
	public Person(String firstName, String lastName) {
		this.name = new Name(firstName,lastName);
		this.address = new Address();
	}

	/**
	 * Person constructor with 3 params which sets the new instance of name with the firstName and lastName params.
	 * then creates a new address with the address param which contains civicNumber,city,province,code
	 * @param firstName The first name
	 * @param lastName The last name
	 * @param address The address
	 */
	
	
	public Person(String firstName, String lastName, Address address) {
		this.name = new Name(firstName,lastName);
		this.address = new Address(address.getCivicNumber(),address.getStreetName(),address.getCity(),Optional.of(address.getProvince()),Optional.of(address.getCode()));
	}

	/**
	 * getName method
	 * @return Name Returns a new object of Name
	 */
	public Name getName() {
		return new Name(this.name.getFirstName(),this.name.getLastName());
	}
	/**
	 * getAddress method
	 * @return Address Returns a new Address object
	 */
	public Address getAddress() {
		return new Address(address.getCivicNumber(),address.getCivicNumber(),address.getCity(),Optional.of(address.getProvince()),Optional.of(address.getCode()));
	}

	/**
	 * setName method which sets the new instance of firstName and lastName with the ones in the params
	 * @param firstName The first name
	 * @param lastName The last name
	 */
	public void setName(String firstName, String lastName) {
		this.name.firstName = firstName;
		this.name.lastName = lastName;
	}

	/**
	 * setAddress which sets the new instance of address with the one in the param
	 * @param address the Address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return name.toString() + "*" + (address == null ? "" : address.toString());
	}

}
