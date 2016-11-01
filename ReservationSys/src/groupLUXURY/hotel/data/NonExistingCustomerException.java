/**
 * 
 */
package groupLUXURY.hotel.data;

/**
 * This exception signals that the provided customer does not exist.
 * @author Sebastian
 *
 */
public class NonExistingCustomerException extends Exception {
	private static final long  serialVersionUID = 42031768871L;
	
	public NonExistingCustomerException(){
		super("The provided customer does not exist.");
	}

	public NonExistingCustomerException(String message) {
		super(message);
	}

}
