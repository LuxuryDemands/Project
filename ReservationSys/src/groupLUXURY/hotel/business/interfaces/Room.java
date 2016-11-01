/**
 * 
 */
package groupLUXURY.hotel.business.interfaces;

import java.io.Serializable;

import groupLUXURY.hotel.business.RoomType;

/**
 * @author Sebastian, Max, Isaak, Kajal
 */
public interface Room extends Serializable, Comparable<Room> {
	RoomType getRoomType();
	int getRoomNumber();
	int getFloor();
	int getNumber();
}
