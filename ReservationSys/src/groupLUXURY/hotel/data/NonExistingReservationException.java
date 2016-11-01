/**
 * 
 */
package groupLUXURY.hotel.data;

/**
 * This exception signals that the provided reservation does not exist.
 * @author Sebastian
 *
 */
public class NonExistingReservationException extends Exception {
private static final long  serialVersionUID = 42031768871L;
	
	public NonExistingReservationException(){
		super("The reservation provided does not exist.");
	}

	public NonExistingReservationException(String message) {
		super(message);
	}

}
