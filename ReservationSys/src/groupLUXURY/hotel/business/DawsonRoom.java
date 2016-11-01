/**
 * 
 */
package groupLUXURY.hotel.business;


import groupLUXURY.hotel.business.interfaces.Room;

/**
 * @author Sebastian, Max, Isaak
 */
public class DawsonRoom implements Room {
	private static final long serialVersionUID = 42031768871L;
	private final int roomNumber;
	private final RoomType roomType;

	/**
	 * DawsonRoom constructor with 2 params
	 * Checking if floor number is in the range of 1-8 and room number is in the range of *00-*08
	 * Sets new instances of roomNumber with the param given
	 * Sets new instances of roomType with the param given
	 * @throws IllegalArgumentException if the floor number is not in the range of 1-8
	 * @throws IllegalArgumentException if the room number is not in the range of 1-8
	 * @param roomNumber The room number
	 * @param roomType The room type
	 */
	
	public DawsonRoom(int roomNumber, RoomType roomType) {
		if ((roomNumber / 100) < 1 || (roomNumber / 100) > 8) {
			throw new IllegalArgumentException("Invalid Floor Number");
		}
		if ((roomNumber - ((roomNumber / 100) * 100)) < 1 || (roomNumber - ((roomNumber / 100) * 100)) > 8) {
			throw new IllegalArgumentException("Invalid Room Number");
		}
		this.roomNumber = roomNumber;
		this.roomType = roomType;
	}

	/**
	 * Overrides abstract method compareTo from Comparable Class 
	 * @param room The Room object to compare it to. 
	 * @return int Returns the comparison of this.room and other.room
	 */
	
	
	@Override
	public int compareTo(Room room) {
		// TODO Auto-generated method stub
		Integer thisInt = this.roomNumber;
		Integer otherInt = room.getRoomNumber();
		return thisInt.compareTo(otherInt);
	}

	/**
	 * Overrides the abstract method getRoomType from Room class
	 * @return RoomType Returning the RoomType
	 */
	
	@Override
	public RoomType getRoomType() {
		return this.roomType;
	}

	/**
	 * Overrides the abstract method getRoomNumber from Room class
	 * @return int Returning the room number
	 */
	
	@Override
	public int getRoomNumber() {
		return this.roomNumber;
	}

	/**
	 * Overrides the abstract method getFloor from Room class
	 * @return int Returning the floor number
	 */
	
	@Override
	public int getFloor() {
		return (this.roomNumber / 100);
	}

	/**
	 * Overrides the abstract method getNumber from Room class
	 * @return int Returning the room number
	 */
	
	@Override
	public int getNumber() {
		return (this.roomNumber - (this.roomNumber / 100) * 100);
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	@Override
	public String toString() {
		return this.roomNumber + "*" + this.roomType.toString();
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	
	
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roomNumber;
		result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
		return result;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	
	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DawsonRoom))
			return false;
		DawsonRoom other = (DawsonRoom) obj;
		if (roomNumber != other.roomNumber)
			return false;
		if (roomType != other.roomType)
			return false;
		return true;
	}

}
