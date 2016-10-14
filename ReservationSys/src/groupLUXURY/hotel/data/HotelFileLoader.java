package groupLUXURY.hotel.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Room;
import dw317.lib.creditcard.CreditCard;
import groupLUXURY.hotel.business.DawsonHotelFactory;

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
			if (customerListScanner.hasNext()==false){
				break;
			}
		}
		customerListScanner.close();
		return customerListArray;
	}
	//public static Reservation[] getReservationListFromSequentialFile  (String filename,  Customer[] customerList,  Room[] roomList) throws IOException, IllegalArgumentException{
		
	//}

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
