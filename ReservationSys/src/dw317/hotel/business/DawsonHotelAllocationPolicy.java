/**
 * 
 */
package dw317.hotel.business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import dw317.hotel.business.interfaces.AllocationPolicy;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ReservationDAO;

/**
 * @author 1331680
 *
 */
public class DawsonHotelAllocationPolicy implements Serializable, AllocationPolicy {

	protected ReservationDAO reservDAO;
	private static final long serialVersionUID = 42031768871L;

	public DawsonHotelAllocationPolicy(ReservationDAO reservDAO) {
		this.reservDAO = reservDAO;
	}

	@Override
	public Optional<Room> getAvailableRoom(LocalDate checkin, LocalDate checkout, RoomType type) {
		Integer mostFrequent = 0;
		ArrayList<Integer> floors = new ArrayList<>();
		List<Room> availableRooms = reservDAO.getFreeRooms(checkin, checkout, type);
		for (Room r : availableRooms) {
			floors.add(r.getFloor());
		}
		for (int floor = 0; floor < 9; floor++) {
			if (Collections.frequency(floors, floor) > mostFrequent) {
				mostFrequent = floor;
			}
		}
		for (int index = 0; index < availableRooms.size(); index++) {
			if (!((Integer) availableRooms.get(index).getFloor()).equals(mostFrequent)) {
				availableRooms.remove(index);
				index--;
			}
		}
		if (availableRooms.size() == 0) {
			return Optional.ofNullable(null);
		} else {
			Random randomizer = new Random();
			Room random = availableRooms.get(randomizer.nextInt(availableRooms.size()));
			return Optional.ofNullable(random);
		}
	}

}
