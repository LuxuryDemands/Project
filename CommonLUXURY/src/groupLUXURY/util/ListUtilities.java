/**
 * 
 */
package groupLUXURY.util;

import java.util.Arrays;

/**
 * @author Sebastian
 *
 */
public class ListUtilities {
	
	/*
	* Sorts a list of objects in ascending natural order using * selection sort.
	*
	* Precondition: Assumes that the list is not null and that the
	* list's capacity is equal to the list's size.
	*
	*
	* @param list A list of objects. Assumes that the
	* list's capacity is equal to the list's size.
	*
	* @throws IllegalArgumentException if the parameter is * not full to capacity.
	*
	* @throws NullPointerException if the list is null.
	*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] list) throws IllegalArgumentException,NullPointerException {
		for (int i = 0; i < list.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < list.length; j++)
                if (list[j].compareTo(list[index])<0) 
                    index = j;
            Comparable smallest = list[index];  
            list[index] = list[i];
            list[i] = smallest;
        }
    }
}
