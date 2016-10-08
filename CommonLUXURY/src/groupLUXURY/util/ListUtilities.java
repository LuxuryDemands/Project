/**
 * 
 */
package groupLUXURY.util;

import java.lang.reflect.Array;
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Comparable[] merge(Comparable[] list1, Comparable[] list2, String duplicateFileName){
		Comparable[] list3 = (Comparable[]) Array.newInstance(
				list1.getClass().getComponentType(), list1.length + list2.length);
		for(int i=0; i<list1.length+list2.length;i++){
			if (list1[i].compareTo(list2[i])==0){
				if (count(list1[i].toString())>count(list2[i].toString())){
					list3[i] = list1[i];
					list3[i++] = list2[i];
				}
				
			}
		}
	}
	private static int count(String item){
		return item.split("[*]").length;

	}
}
