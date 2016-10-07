/**
 * 
 */
package groupLUXURY.hotel.business;

/**
 * @author Sebastian
 *
 */
public class DawsonRoomTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DawsonRoom dR1 = new DawsonRoom(808, RoomType.NORMAL);
		DawsonRoom dR2 = new DawsonRoom(701, RoomType.SUITE);
		DawsonRoom dR3 = dR1;
		DawsonRoom dR4 = new DawsonRoom(808, RoomType.NORMAL);
		System.out.println(dR1);
		System.out.println(dR1.compareTo(dR2));
		System.out.println(dR1.getRoomType());
		System.out.println(dR2.getRoomNumber());
		System.out.println(dR1.getNumber());
		System.out.println(dR2.getFloor());
		System.out.println(dR1.hashCode());
		System.out.println(dR3.hashCode());
		System.out.println(dR2.hashCode());
		System.out.println(dR4.hashCode());
		System.out.println(dR1.equals(dR4));
		System.out.println(dR1.equals(dR3));
		System.out.println(dR3.equals(dR4));
		System.out.println(dR2.equals(dR4));
	}

}
