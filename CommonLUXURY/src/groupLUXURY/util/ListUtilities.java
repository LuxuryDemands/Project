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
	 * Sorts a list of objects in ascending natural order using * selection
	 * sort.
	 *
	 * Precondition: Assumes that the list is not null and that the list's
	 * capacity is equal to the list's size.
	 *
	 *
	 * @param list A list of objects. Assumes that the list's capacity is equal
	 * to the list's size.
	 *
	 * @throws IllegalArgumentException if the parameter is * not full to
	 * capacity.
	 *
	 * @throws NullPointerException if the list is null.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] list) throws IllegalArgumentException, NullPointerException {
		for (int i = 0; i < list.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < list.length; j++)
				if (list[j].compareTo(list[index]) < 0)
					index = j;
			Comparable smallest = list[index];
			list[index] = list[i];
			list[i] = smallest;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Comparable[] merge(Comparable[] list1, Comparable[] list2) {
		if (list1==null || list2 == null){
			throw new NullPointerException("Neither of the lists can be null");
		}
		if (checkNull(list1) || checkNull(list2)){
			throw new IllegalArgumentException("One of the lists is not full to capacity");
		}
		Comparable[] list3 = (Comparable[]) Array.newInstance(list1.getClass().getComponentType(),
				list1.length + list2.length);
		int i = 0;
		int j = 0;
		while (i < list1.length) {
			if (list1[i].compareTo(list2[i]) < 0) {
				list3[j] = list1[i];
				j++;
				list3[j] = list2[i];
				j++;
				i++;
			} else if (list1[i].compareTo(list2[i]) > 0) {
				list3[j] = list2[i];
				j++;
				list3[j] = list1[i];
				j++;
				i++;
			} else {
				if (count(list1[i].toString()) > count(list2[i].toString())) {
					list3[j] = list1[i];
					j++;
					i++;
				} else {
					list3[j] = list2[i];
					j++;
					i++;
				}
			}
		}
		sort(list3);
		Comparable [] listToReturn = list3;
		return listToReturn;
	}

	private static int count(String item) {
		return item.split("[*]").length;

	}
	@SuppressWarnings("rawtypes")
	private static boolean checkNull(Comparable[] list){
		for (int i = 0; i<list.length; i++){
			if (list[i] == null){
				return true;
			}
		}
		return false;
	}
}
