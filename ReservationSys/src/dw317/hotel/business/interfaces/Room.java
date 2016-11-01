/**
 * 
 */
package dw317.hotel.business.interfaces;

import java.io.Serializable;

import dw317.hotel.business.RoomType;

/**
 * @author Sebastian, Max, Isaak, Kajal
 */
public interface Room extends Serializable, Comparable<Room> {
	RoomType getRoomType();
	int getRoomNumber();
	int getFloor();
	int getNumber();
}
