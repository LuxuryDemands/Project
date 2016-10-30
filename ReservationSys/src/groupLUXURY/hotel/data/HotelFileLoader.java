package groupLUXURY.hotel.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;
import groupLUXURY.hotel.business.DawsonHotelFactory;
/**
 * This class compasses methods to get object lists from sequential text files.
 * 
 * @author Sebastian, Maxime, Isaak, Kajal
 *
 */
public class HotelFileLoader {

	private HotelFileLoader() {

	}

	/**
	 * Creates a new Room array from a sequential text file.
	 *
	 * @param filename
	 *            The path of the sequential text file.
	 * 
	 * @throws FileNotFoundException
	 *             if the file is not found.
	 * 
	 * @throws IOException
	 *             if there has been an input/output error.
	 *
	 * @return roomListScanner The Room array.
	 */
	public static Room[] getRoomListFromSequentialFile(String filename) throws IOException, FileNotFoundException {
		Scanner roomListScanner = new Scanner(new File(filename));
		Room[] roomListArray = new Room[numberOfItems(filename)];
		for (int i = 0; i <= numberOfItems(filename); i++) {
			String roomString = roomListScanner.next();
			roomListArray[i] = DawsonHotelFactory.DAWSON.getRoomInstance(Integer.parseInt(getRoomNumber(roomString)),
					getRoomType(roomString));
			if (roomListScanner.hasNext() == false) {
				break;
			}
		}
		roomListScanner.close();
		return roomListArray;
	}

	/**
	 * Creates a new Customer array from a sequential text file.
	 *
	 * @param filename
	 *            The path of the sequential text file.
	 * 
	 * @throws FileNotFoundException
	 *             if the file is not found.
	 * 
	 * @throws IOException
	 *             if there has been an input/output error.
	 *
	 * @return customerListArray The Room array.
	 */
	public static Customer[] getCustomerListFromSequentialFile(String filename)
			throws IOException, FileNotFoundException {
		Scanner customerListScanner = new Scanner(new File(filename));
		Customer[] customerListArray = new Customer[numberOfItems(filename)];
		for (int i = 0; i <= numberOfItems(filename); i++) {
			String customerString = customerListScanner.next();
			String[] separatedItems = separateItems(customerString);
			if (separatedItems.length == 3) {
				customerListArray[i] = DawsonHotelFactory.DAWSON.getCustomerInstance(separatedItems[1],
						separatedItems[2], separatedItems[0]);
			} else {
				customerListArray[i] = DawsonHotelFactory.DAWSON.getCustomerInstance(separatedItems[1],
						separatedItems[2], separatedItems[0]);
				CreditCard customerCard = CreditCard
						.getInstance(CreditCard.CardType.valueOf(separatedItems[3].toUpperCase()), separatedItems[4]);
				customerListArray[i].setCreditCard(Optional.of(customerCard));
			}
			if (customerListScanner.hasNext() == false) {
				break;
			}
		}
		customerListScanner.close();
		return customerListArray;
	}

	/**
	 * Creates a new Reservation array from a sequential text file, a Customer
	 * list and a Room list.
	 *
	 * @param filename
	 *            The path of the sequential text file.
	 * 
	 * @param customerList
	 *            A list of Customer objects.
	 * 
	 * @param roomList
	 *            A list of Room objects.
	 * 
	 * @throws IllegalArgumentException
	 *             if the Customer or the Room are not found.
	 * 
	 * @throws IOException
	 *             if there has been an input/output error.
	 *
	 * @return reservationListArray The Room list.
	 */
	public static Reservation[] getReservationListFromSequentialFile(String filename, Customer[] customerList,
			Room[] roomList) throws IOException, IllegalArgumentException {
		Scanner reservationListScanner = new Scanner(new File(filename));
		Reservation[] reservationListArray = new Reservation[numberOfItems(filename)];
		for (int i = 0; i < reservationListArray.length; i++) {
			Customer customerToAdd=null;
			Room roomToAdd=null;
			String reservationString = reservationListScanner.next();
			String[] separatedItems = separateItems(reservationString);
			Email reservationAtIEmail = new Email(separatedItems[0]);
			int reservationAtIRoom = Integer.parseInt(separatedItems[7]);
			for (int j=0;j<customerList.length;j++){
				if (reservationAtIEmail.equals(customerList[j].getEmail())){
					customerToAdd = customerList[j];
				}
			}
			if (customerToAdd==null){
				reservationListScanner.close();
				throw new IllegalArgumentException("Email not found: "+reservationAtIEmail);
			}
			for (int k=0;k<roomList.length;k++){
				if (reservationAtIRoom==roomList[k].getRoomNumber()){
					roomToAdd = roomList[k];
				}			
			}
			if (roomToAdd==null){
				reservationListScanner.close();
				throw new IllegalArgumentException("Room not found: "+reservationAtIRoom+" in: "+separatedItems[0].toString());
			}
			reservationListArray[i] = DawsonHotelFactory.DAWSON.getReservationInstance(customerToAdd, 
					roomToAdd, Integer.parseInt(separatedItems[1]), Integer.parseInt(separatedItems[2]), 
					Integer.parseInt(separatedItems[3]), Integer.parseInt(separatedItems[4]), 
					Integer.parseInt(separatedItems[5]), Integer.parseInt(separatedItems[6]));
		}
		reservationListScanner.close();
		return reservationListArray;
	}
	/**
	 * Gets the string representation of the room number from a string
	 * representation of a Room object.
	 *
	 * @param roomString
	 *            A string representation of a Room object.
	 * 
	 * @return the string representation of the room number.
	 */
	private static String getRoomNumber(String roomString) {
		return roomString.substring(0, roomString.indexOf('*'));
	}

	/**
	 * Gets the string representation of the room type from a string
	 * representation of a Room object.
	 *
	 * @param roomString
	 *            A string representation of a Room object.
	 * 
	 * @return the string representation of the room type.
	 */
	private static String getRoomType(String roomString) {
		return roomString.substring(roomString.indexOf('*') + 1);
	}

	/**
	 * Separates the string representation of a customer into separate string
	 * representations of all its data members.
	 *
	 * @param customerString
	 *            A string representation of a Customer object.
	 * 
	 * @return an Array of strings containing the string representations of all
	 *         the Customer data members.
	 */
	private static String[] separateItems(String customerString) {
		return customerString.split("[*]");
	}

	/**
	 * Returns the total number of items in a sequential text file.
	 *
	 * @param fileName
	 *            The path of the sequential text file.
	 * 
	 * @throws FileNotFoundException
	 *             if the file is not found.
	 * 
	 * @return numberOfItems a number representing the number of items in the
	 *         sequential text file.
	 */
	private static int numberOfItems(String fileName) throws FileNotFoundException {
		int numberOfItems = 0;
		Scanner whileLoopScanner = new Scanner(new File(fileName));
		while (whileLoopScanner.hasNext()) {
			numberOfItems++;
			whileLoopScanner.next();
			if (whileLoopScanner.hasNext() == false) {
				break;
			}
		}
		whileLoopScanner.close();
		return numberOfItems;
	}
}
