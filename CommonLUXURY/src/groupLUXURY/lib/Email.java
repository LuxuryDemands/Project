package groupLUXURY.lib;

import java.io.Serializable;

/**
 * @author Sebastian, Max, isaak, Kajal
 */
public class Email implements Serializable, Comparable<Email> {
	private static final long serialVersionUID = 42031768871L;
	protected final String address;

	/**
	 * Email constructor with 1 param. Set the email instance using
	 * validateEmail method for validating that the email is syntactically
	 * correct.
	 * 
	 * @param address
	 *            A string representing the email address.
	 */

	public Email(String address) {
		this.address = new String(validateEmail(address));
	}

	/**
	 * Returns a String representation of the email.
	 * 
	 * @return String Returning the email address.
	 */

	public String getAddress() {
		return this.address;
	}

	/**
	 * Returns the user id.
	 * 
	 * @return String The user id.
	 */

	public String getUserID() {
		return address.substring(0, address.indexOf('@'));
	}

	/**
	 * Returns the user host.
	 * 
	 * @return String The user host.
	 */

	public String getHost() {
		return address.substring(address.indexOf('@'));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((address == null) ? 0 : this.getHost().hashCode() + this.getUserID().toLowerCase().hashCode());
		return result;
	}

	/**
	 * OverRiding the equals class
	 * 
	 * @param Object
	 *            The object to be compared.
	 * @return True or False depending on whether or not the two objects are the
	 *         same The two objects are the same if - this.obj is equal to the
	 *         object in the param - object in the param is not null - object in
	 *         the param is an instance of the class Email - if the address of
	 *         the object in the param is not null - if the address of the
	 *         object comparing to is not null - if the hostName are the same
	 *         for both the objects - if the userID are the same for both the
	 *         objects
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Email))
			return false;
		Email other = (Email) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!this.getHost().equals(other.getHost())) {
			return false;
		} else if (!this.getUserID().equalsIgnoreCase(other.getUserID())) {
			return false;
		}
		return true;
	}

	/**
	 * /** Overrides abstract method compareTo from Comparable Class
	 * 
	 * @param other
	 *            Customer object to compare
	 * @return int Returns the comparison of this.email and other.email
	 */

	@Override
	public int compareTo(Email otherEmail) {
		if (this.getHost().equals(otherEmail.getHost())) {
			return this.getUserID().compareToIgnoreCase(otherEmail.getUserID());
		} else {
			return this.getHost().compareTo(otherEmail.getHost());
		}
	}

	/**
	 * validateEmail method validates the hostName and userID to make sure they
	 * satisfy the restrictions.
	 * 
	 * @param emailAddress
	 *            The Email Address
	 * @throws IllegalArgumentException
	 *             if there are no characters in the email
	 * @throws IllegalArgumentException
	 *             if the hostName and userID do not satisfy the
	 *             validateHostName/validateUserID method restrictions.
	 * @return String Returns the emailAddress
	 */

	private static String validateEmail(String emailAddress) {
		if (emailAddress.indexOf('@') == -1) {
			throw new IllegalArgumentException("Invalid Email");
		}
		boolean host = validateHostName(emailAddress.substring(emailAddress.indexOf('@')));
		boolean userID = validateUserID(emailAddress.substring(0, emailAddress.indexOf('@')));
		if (!host || !userID) {
			throw new IllegalArgumentException("Invalid Email: "+emailAddress);
		} else {
			return emailAddress;
		}
	}

	/**
	 * validateUserID method validates the userID depending on the restrictions;
	 * Restrictions being: - Cannot start with a '.' or end with a '.' - Cannot
	 * contain '..' anywhere - Has to match with any of the following
	 * characters: ' a-z A-Z 0-9 . _ - '
	 * 
	 * @param userID
	 *            The UserID
	 * @return boolean Returning true or false whether it is satisfied or not.
	 */

	private static boolean validateUserID(String userID) {
		if (userID.length() < 1 || userID.length() > 32) {
			return false;
		} else if (userID.startsWith(".") || userID.endsWith(".")) {
			return false;
		} else if (userID.contains("..")) {
			return false;
		} else if (!userID.matches("^[a-zA-Z0-9._-]+$")) {
			return false;
		}
		return true;
	}

	/**
	 * validateHostName method validates the hostName depending on the
	 * restrictions; Restrictions being: - Cannot start with a '.' or end with a
	 * '.' - Cannot contain '..' anywhere - Has to match with any of the
	 * following characters -->' a-z A-Z 0-9 . _ - '
	 * 
	 * @param hostName
	 *            The Host Name
	 * @return boolean Returning true or false whether it is satisfied or not.
	 */

	private static boolean validateHostName(String hostName) {
		if (hostName.startsWith(".") || hostName.endsWith(".") || hostName.startsWith("-")) {
			return false;
		} else if (hostName.contains("..")) {
			return false;
		} else if (!hostName.matches("^[a-zA-Z0-9.-@]+$")) {
			return false;
		} else {
			return true;
		}

	}

}
