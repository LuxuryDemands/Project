/**
 * 
 */
package groupLUXURY.hotel.data.interfaces;

import java.util.List;

import groupLUXURY.hotel.business.RoomType;
import groupLUXURY.hotel.business.interfaces.Room;

public interface RoomDAO {

	/**
	 * Returns a list of all rooms with a given RoomType
	 * 
	 * @param roomType
	 * @return list of rooms
	 */
	List<Room> getRooms(RoomType roomType);

}
