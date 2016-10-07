/**
 * 
 */
package groupLUXURY.hotel.business;

/**
 * @author Sebastian, Max, Isaak, Kajal
 */
public enum RoomType{
	NORMAL, SUITE, PENTHOUSE;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	@Override
	public String toString(){
		switch(this){
		case SUITE:
			return "suite";
		case PENTHOUSE:
			return "penthouse";
		default:
			return "normal";
		}
	}
}



