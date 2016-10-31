/**
 * 
 */
package groupLUXURY.hotel.business;

/**
 * @author kajal
 *
 */
public class ReservationByCustSortedTEST {

	public static void main(String[] args) {

		testCompareByCust();
	}

	public static void testCompareByCust() {
		
		DawsonRoom rom1 = new DawsonRoom(101, RoomType.PENTHOUSE);
		DawsonCustomer cus1 = new DawsonCustomer("Bruce", "Wayne", "Bruce@live.com");
		DawsonReservation res1 = new DawsonReservation(cus1, rom1, 2017, 2, 3, 2022, 4, 7);

		DawsonRoom rom2 = new DawsonRoom(101, RoomType.PENTHOUSE);
		DawsonCustomer cus2 = new DawsonCustomer("Bruce", "Wayne", "Bruce@live.com");
		DawsonReservation res2 = new DawsonReservation(cus2, rom2, 2017, 2, 3, 2022, 4, 7);

		DawsonRoom rom3 = new DawsonRoom(103, RoomType.NORMAL);
		DawsonCustomer cus3 = new DawsonCustomer("Berry", "Allen", "Berry@yahoo.com");
		DawsonReservation res3 = new DawsonReservation(cus3, rom3, 2017, 2, 3, 2022, 4, 7);

		DawsonRoom rom4 = new DawsonRoom(104, RoomType.PENTHOUSE);
		DawsonCustomer cus4 = new DawsonCustomer("Berry", "Allen", "Berry@yahoo.com");
		DawsonReservation res4 = new DawsonReservation(cus4, rom4, 2017, 2, 3, 2022, 4, 7);
         
		DawsonReservation[] list={res1,res2,res3,res4};
		testCompareByCust(list);
	}
	
	public static void testCompareByCust(DawsonReservation[]  list){
		ReservationByCustSorted reservationByCustSorted = new ReservationByCustSorted();
		
		System.out.println("Those are the test for ReservationByCustSorted class \n");
		
		System.out.println("Exactly the same details (expect 0)");
		System.out.println(reservationByCustSorted.compare(list[0],list[1]));
	    
		System.out.println("R1>R2 (expect a positive number)");
		System.out.println(reservationByCustSorted.compare(list[2],list[1]));
		
		System.out.println("R1<R2 (expect a negative number)");
		System.out.println(reservationByCustSorted.compare(list[2],list[3]));
		
		
	}
}
