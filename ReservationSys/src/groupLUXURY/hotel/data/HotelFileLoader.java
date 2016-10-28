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
import groupLUXURY.hotel.business.DawsonReservation;

/**
 * @author 1331680
 *
 */
public class HotelFileLoader {

	private HotelFileLoader() {

	}

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

	/*
	 * uses the email field to find customer and roomnumber to find room returns
	 * a reservation array
	 * 
	 */
	public static Reservation[] getReservationListFromSequentialFile(String filename, Customer[] customerList,
			Room[] roomList) throws IOException, IllegalArgumentException {

		Scanner reservationListScanner = new Scanner(new File(filename));
		Reservation[] reservationListArray = new Reservation[numberOfItems(filename)];
		Customer cust1;
		Room room1;
		for (int i = 0; i <= numberOfItems(filename); i++) {

			String reservationString = reservationListScanner.next();
			String[] separatedItems = separateItems(reservationString);
			for (Customer c : customerList) {
				if (!(c.getEmail().equals(new Email(separatedItems[i])))) // email
																			// found
					throw new IllegalArgumentException("Email not found: " + separatedItems[i]);
				else {
					cust1 = c;
					for (Room r : roomList) {
						if (!(r.getRoomNumber() == Integer.parseInt(separatedItems[7])))
							throw new IllegalArgumentException("Room not found");
						else {
							room1 = r;
							reservationListArray[i] = new DawsonReservation(cust1, room1,
									Integer.parseInt(separatedItems[1]), Integer.parseInt(separatedItems[2]),
									Integer.parseInt(separatedItems[3]), Integer.parseInt(separatedItems[4]),
									Integer.parseInt(separatedItems[5]), Integer.parseInt(separatedItems[6]));
						}
					}

				}
			}

			if (reservationListScanner.hasNext() == false) {
				break;
			}
			reservationListScanner.close();
		}
		return reservationListArray;

	}
	private static String getRoomNumber(String roomString) {
		return roomString.substring(0, roomString.indexOf('*'));
	}

	private static String getRoomType(String roomString) {
		return roomString.substring(roomString.indexOf('*') + 1);
	}

	private static String[] separateItems(String customerString) {
		return customerString.split("[*]");
	}

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
