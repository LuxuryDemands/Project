package groupLUXURY.lib;
import java.io.Serializable;

/**
 * @author Sebastian, Max, isaak, Kajal
 */
public class Name implements Serializable {
	private static final long serialVersionUID = 42031768871L;
	protected String firstName = "";
	protected String lastName = "";
	
	/**
	 * Name constructor with 1 param
	 * Sets the new instance of name using the 2 param Name constructor
	 * @param name The full name
	 */
	
	public Name(Name name) {
		this(name.getFirstName(), name.getLastName());
	}
	/**
	 * Name constructor with 2 params
	 * Sets the new instance of the firstName and lastName variables.
	 * @param firstName The first name
	 * @param lastName The last name
	 */
	public Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	/**
	 * getFirstName method which returns the firstName
	 * @return String Returns The first name
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * getLastName method which returns the lastName
	 * @return String Returns the last name
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * getFullname method which returns an empty string
	 * @return String Returns the full name
	 */
	public String getFullName() {
		return this.firstName +  this.lastName;
	}
	/**
	 * setFirstName method which sets the new instance of firstName with the one in the param
	 * @param firstName The First Name to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * setLastName method which sets the new instance of lastName with the one in the param
	 * @param lastName The lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	@Override
	public String toString() {
		return (firstName.toLowerCase() + "*" + lastName.toLowerCase());
	}
	

}
