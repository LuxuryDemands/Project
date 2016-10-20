/**
 * 
 */
package groupLUXURY.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Scanner;

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

	/*
	 * Efficiently merges two sorted lists of objects in ascending natural
	 * order. If the duplicate objects are in both lists, the object from list1
	 * is merged into the resulting list, and* both objects are written to the
	 * duplicate file.
	 *
	 * Precondition: Assumes that the lists are not null and that both lists
	 * contain objects that can be compared to each other and are filled to
	 * capacity.
	 *
	 *
	 * @param list1 A naturally sorted list of objects. Assumes that the list
	 * contains no duplicates and that its capacity is equal to its size.
	 * 
	 * @param list2 A naturally sorted list of objects. Assumes that the list
	 * contains no duplicates and that its capacity is equal to its size.
	 * 
	 * @param duplicateFileName The name of the file in datafiles\duplicates to
	 * which duplicate pairs will be appended.
	 *
	 * @throws IllegalArgumentException if either parameter is not full to
	 * capacity.
	 *
	 * @throws NullPointerException if the either list is null.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Comparable[] merge(Comparable[] list1, Comparable[] list2) throws FileNotFoundException {
		if (list1 == null || list2 == null) {
			throw new NullPointerException("Neither of the lists can be null");
		}
		if (checkNull(list1) || checkNull(list2)) {
			throw new IllegalArgumentException("One of the lists is not full to capacity");
		}
		Comparable[] listToReturn;
		Comparable[] list3 = (Comparable[]) Array.newInstance(list1.getClass().getComponentType(),
				list1.length + list2.length);
		for (int i = 0, j = 0; i < list3.length; i++, j++) {
			list3[i] = list1[j];
			i++;
			list3[i] = list2[j];
		}
		sort(list3);
		Comparable[] duplicates = arrayDuplicates(list3);
		Comparable[] list4 = (Comparable[]) Array.newInstance(list3.getClass().getComponentType(),
				list3.length - duplicates.length);
		if (duplicates.length > 0) {
			File duplicatesFile = new File("H:\\git\\Project\\ReservationSys\\datafiles\\duplicates.txt");
			PrintWriter duplicatesOut = new PrintWriter(duplicatesFile);
			for (int j2 = 0, i2 = 0; j2 < list3.length; j2++, i2++) {
				for (int k2 = 0; k2 < duplicates.length; k2++) {
					if (list3[j2].compareTo(duplicates[k2]) == 0) {
						duplicatesOut.println(list3[j2] + "(merged)");
						duplicatesOut.println(duplicates[k2]);
						list4[i2] = list3[j2];
						j2++;
					} else {
						list4[i2] = list3[j2];
					}
				}
			}
			duplicatesOut.close();
			listToReturn = list4;
			return listToReturn;
		} else {
			listToReturn = list3;
			return listToReturn;
		}

	}

	@SuppressWarnings("rawtypes")
	private static boolean checkNull(Comparable[] list) {
		for (int i = 0; i < list.length; i++) {
			if (list[i] == null) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public static int countDuplicates(Comparable[] list) {
		int count = 0;
		for (int i = 0; i < list.length; i++) {
			if (i == list.length - 1) {
				break;
			}
			if (list[i].equals(list[i + 1])) {
				count++;
			}
		}
		return count;
	}

	@SuppressWarnings("rawtypes")
	public static Comparable[] arrayDuplicates(Comparable[] list) {
		Comparable[] duplicateList = (Comparable[]) Array.newInstance(list.getClass().getComponentType(),
				countDuplicates(list));
		int count = 0;
		for (int i = 0; i < list.length; i++) {
			if (i == list.length - 1) {
				break;
			}
			if (list[i].equals(list[i + 1])) {
				duplicateList[count] = list[i+1];
				count++;
			}
		}
		return duplicateList;
	}
}

// isaak code vvvvv
// int p=0;
// int q=0;
// int r=0;
//
// make new array
// Comparable[] list3 = (Comparable[])
// Array.newInstance(list1.getClass().getComponentType(),
// list1.length + list2.length);

// while (p<list1.length && q<list2.length)
// {
// if (list1[p].compareTo(list2[q]) == 0 ) // if same
// {
// //save p
// list3[r++] = list1[p];
// p++;
// q++;
// System.out.println("duplicate found:" + list3[r]);
// }
// else if (list1[p].compareTo(list2[q]) < 0 ) //p is smaller
// {
// //save p
// list3[r++] = list1[p];
// p++;
// }
// else if (list1[p].compareTo(list2[q]) > 0 ) //q is smaller
// {
// //save q
// list3[r++] = list2[q];
// q++;
// }
// }
//
// //shrink array by using the total counter r
// Comparable[] listToReturn = Arrays.copyOf(list3, r);
// end isaak code^^^
