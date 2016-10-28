package groupLUXURY.hotel.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author kajal
 *
 */
public class ReservationByCheckOutSortedTEST {

	public static void main(String[] args) {

		RoomType rt1 = RoomType.PENTHOUSE;
		DawsonRoom rom1 = new DawsonRoom(101, rt1);
		DawsonCustomer cus1 = new DawsonCustomer("Bruce", "Wayne", "Bruce@live.com");
		DawsonReservation res1 = new DawsonReservation(cus1, rom1, 2017, 2, 3, 2024, 4, 7);

		RoomType rt2 = RoomType.NORMAL;
		DawsonRoom rom2 = new DawsonRoom(102, rt2);
		DawsonCustomer cus2 = new DawsonCustomer("Call", "Saul", "Call@hotmail.ca");
		DawsonReservation res2 = new DawsonReservation(cus2, rom2, 2018, 9, 4, 2019, 6, 8);

		RoomType rt3 = RoomType.NORMAL;
		DawsonRoom rom3 = new DawsonRoom(103, rt3);
		DawsonCustomer cus3 = new DawsonCustomer("Harison", "Wells", "Harison@gmail.com");
		DawsonReservation res3 = new DawsonReservation(cus3, rom3, 2017, 7, 1, 2019, 2, 2);

		RoomType rt4 = RoomType.PENTHOUSE;
		;
		DawsonRoom rom4 = new DawsonRoom(104, rt4);
		DawsonCustomer cus4 = new DawsonCustomer("Berry", "Allen", "Berry@yahoo.com");
		DawsonReservation res4 = new DawsonReservation(cus4, rom4, 2019, 9, 5, 2022, 2, 8);

		List<DawsonReservation> list = new ArrayList<DawsonReservation>();
		list.add(res1);
		list.add(res2);
		list.add(res3);
		list.add(res4);
        Collections.sort(list,new ReservationByCheckOutSorted());
	
		for (DawsonReservation r : list) {
			System.out.println("This is the Check out Date: " + r.getCheckOutDate());

		}


	}

}
