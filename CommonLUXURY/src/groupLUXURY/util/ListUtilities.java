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
