/**
 * Defines an Address type
 */
package dw317.lib;

import java.util.Optional;

/**
 * @author Sebastian, Max, Isaak, Kajal
 *
 */

//Address varriables

public class Address {
	private String city;
	private String civicNumber;
	private String streetName;
	private String province = "";
	private String code = "";
	private int num;



	/**
	 * Address default constructor with no params: does nothing.
	 * Simply overrides the java default constructor with no params.
	 * 
	 */
	
	public Address() {

	}

	/**
	 * Address constructor with 3 params.
	 * Sets all of the new instances of civicNumber, streetName and city 
	 * whilst using validateExistence method for validating the existance of the params.
	 * @param civicNumber The Civic Number
	 * @param streetName The street name
	 * @param city The city
	 */
	
	
	public Address(String civicNumber, String streetName, String city) {
		this.civicNumber = validateExistence("civic number", civicNumber);
		this.streetName = validateExistence("street name", streetName);
		this.city = validateExistence("city", city);
	}
	
	/**
	 * Address constructor with 5 params: 2 of them being optional; province and code
	 * Sets all of the new instances of civicNumber, streetName, city, province and code
	 * whilst using validateExistence method for validating the existance of the params.
	 * @param civicNumber The civic number
	 * @param streetName The street name
	 * @param city The city
	 * @param province The province
	 * @param code The code
	 * 
	 */
	
	public Address(String civicNumber, String streetName, String city, Optional<String> province, Optional<String> code) {
		this.civicNumber = validateExistence("civic number", civicNumber);
		this.streetName = validateExistence("street name", streetName);
		this.city = validateExistence("city", city);
		this.province = province.orElse("");
		this.code = code.orElse("");
	}

	/**
	 * Returns a String representation of the address.
	 * @return String Returning the address
	 */
	public String getAddress() {
		String address = civicNumber + " " + streetName + "\n" + city;
		address += (province.equals("") ? "" : (", " + province)) + (code.equals("") ? "" : ("\n" + code));

		return address;
	}

	/**
	 * Returns the city
	 * @return String The city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Returns the civicNumber
	 * @return String the civic number
	 */
	public String getCivicNumber() {
		return civicNumber;
	}

	/**
	 * Returns the streetName
	 * @return String Returns the street name
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * Returns the province
	 * @return String Returns the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * Returns the code
	 * @return String Returns the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the city to a new value
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Sets the civicNumber to a new value
	 * @param civicNumber
	 *            the civicNumber to set
	 */
	public void setCivicNumber(String civicNumber) {
		this.civicNumber = civicNumber;
	}

	/**
	 * Sets the streetName to a new value
	 * @param streetName
	 *            the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * Sets the province to a new value
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * Sets the code to a new value
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	@Override
	public String toString() {
		return (civicNumber + "*" + streetName + "*" + city + "*" + province + "*" + code);
	}
	/**
	 * Validates the existence of the fieldName and fieldValue.
	 * @throws IllegalArgumentException if the fieldName is null.
	 * @throws IllegalArgumentException if the fieldValue is null.
	 * @param fieldName The field name
	 * @param fieldValue The field value
	 * @return String Returns the trimmedString of the field name and field value
	 */
	private String validateExistence(String fieldName, String fieldValue) {
		if (fieldValue == null)
			throw new IllegalArgumentException("Address Error - " + fieldName + " must exist. Invalid value = " + fieldValue);
		String trimmedString = fieldValue.trim();
		if (trimmedString.trim().isEmpty())
			throw new IllegalArgumentException(
					"Address Error - " + fieldName + " must exist. Invalid value = " + fieldValue);
		return trimmedString;
	}
}
