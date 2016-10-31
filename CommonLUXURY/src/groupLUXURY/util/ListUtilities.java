/**
 * 
 */
package groupLUXURY.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * This class compasses ListUtilities like sort and merge and other private
 * methods.
 * 
 * @author Sebastian, Kajal, Maxime, Isaak
 *
 */
public class ListUtilities {

	private ListUtilities() {

	}

	/**
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
	 * @param list1
	 *            A naturally sorted list of objects. Assumes that the list
	 *            contains no duplicates and that its capacity is equal to its
	 *            size.
	 * 
	 * @param list2
	 *            A naturally sorted list of objects. Assumes that the list
	 *            contains no duplicates and that its capacity is equal to its
	 *            size.
	 * 
	 * @param duplicateFileName
	 *            The name of the file in datafiles\duplicates to which
	 *            duplicate pairs will be appended.
	 * @throws IOException
	 *
	 * @throws IllegalArgumentException
	 *             if either parameter are not full to capacity.
	 *
	 * @throws NullPointerException
	 *             if either lists are null.
	 */
	@SuppressWarnings({ "rawtypes" })
	public static Comparable[] merge(Comparable[] list1, Comparable[] list2, String duplicateFilename)
			throws IOException {
		if (list1 == null || list2 == null) {
			throw new NullPointerException("Neither of the lists can be null");
		}
		if (checkNull(list1) || checkNull(list2)) {
			throw new IllegalArgumentException("One of the lists is not full to capacity");
		}
		FileWriter fw = new FileWriter(duplicateFilename, true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter b = new PrintWriter(bw);
		Comparable[] list3 = (Comparable[]) Array.newInstance(list1.getClass().getComponentType(),
				(list1.length + list2.length) - countDuplicates(list1, list2));
		int indexOfList3 = 0;
		for (int i = 0; i < list1.length; i++) {
			for (int j = 0; j < list2.length; j++) {
				if (list1[i].equals(list2[j])) {
					b.println(list1[i].toString() + "(merged)");
					b.println(list2[j].toString());
					b.println("PREVIOUS MERGE BEFORE");
					list2[j] = null;
				}
			}
			list3[i] = list1[i];
			indexOfList3 = i;
		}
		indexOfList3++;
		for (int k = 0; k < list2.length; k++) {
			if (!(list2[k] == null)) {
				list3[indexOfList3] = list2[k];
				indexOfList3++;
			}
		}
		b.close();
		Comparable[] toReturn = list3;
		return toReturn;
	}

	/**
	 * General method to save the string representation of an array containing
	 * any type of object into a sequential text file.
	 *
	 * Precondition: Assumes that the list is not null and that the list's
	 * capacity is equal to the list's size.
	 *
	 *
	 * @param list
	 *            A list of objects. Assumes that the list's capacity is equal
	 *            to the list's size.
	 * @param path
	 *            A string that specifies the target path for the file.
	 *
	 * @throws IllegalArgumentException
	 *             if the parameter is * not full to capacity.
	 *
	 * @throws NullPointerException
	 *             if the list is null.
	 */
	public static void saveListToTextFile(Object[] list, String path) throws FileNotFoundException {
		if (list == null) {
			throw new NullPointerException("The list cannot be null");
		}
		if (checkNull(list)) {
			throw new IllegalArgumentException("The list is not full to capacity");
		}
		File file = new File(path);
		PrintWriter print = new PrintWriter(file);
		for (int i = 0; i < list.length; i++) {
			print.println(list[i]);
		}
		print.close();
	}

	/**
	 * Sorts a list of objects in ascending natural order using * selection
	 * sort.
	 *
	 * Precondition: Assumes that the list is not null and that the list's
	 * capacity is equal to the list's size.
	 *
	 *
	 * @param list
	 *            A list of objects. Assumes that the list's capacity is equal
	 *            to the list's size.
	 *
	 * @throws IllegalArgumentException
	 *             if the parameter is * not full to capacity.
	 *
	 * @throws NullPointerException
	 *             if the list is null.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] list) throws IllegalArgumentException, NullPointerException {
		if (list == null) {
			throw new NullPointerException("The list cannot be null");
		}
		if (checkNull(list)) {
			throw new IllegalArgumentException("The list is not full to capacity");
		}
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

	/**
	 * Checks if a list contains any null object(the list is not full to
	 * capacity)
	 * 
	 * @param list
	 *            A list of objects. Assumes that the list's capacity is equal
	 *            to the list's size.
	 * @return True if there is a null object, false otherwise
	 * 
	 */
	private static boolean checkNull(Object[] list) {
		for (int i = 0; i < list.length; i++) {
			if (list[i] == null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Counts the number of duplicates between two lists.
	 * 
	 * @param list1
	 *            A list of objects. Assumes that the list's capacity is equal
	 *            to the list's size.
	 * @param list2
	 *            A list of objects. Assumes that the list's capacity is equal
	 *            to the list's size.
	 * @return count The number of duplicates.
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public static int countDuplicates(Comparable[] list1, Comparable[] list2) {
		int count = 0;
		for (int i = 0; i < list1.length; i++) {
			for (int j = 0; j < list2.length; j++) {
				if (list1[i].equals(list2[j])) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Sorts a list of objects in the given order.
	 *
	 * Precondition: Assumes that the list is not null and that the list's
	 * capacity is equal to the list's size.
	 *
	 * @param list
	 *            A list of objects. Assumes that the list's capacity is equal
	 *            to the list's size.
	 * 
	 * @param sortOrder
	 *            A Comparator object that defines the sort order
	 * 
	 * @throws IllegalArgumentException
	 *             if the parameter is not full to capacity. *
	 * 
	 * @throws NullPointerException
	 *             if the list or sortOrder * are null.
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] list, Comparator sortOrder)
			throws IllegalArgumentException, NullPointerException {
		if (list == null) {
			throw new NullPointerException("The list cannot be null");
		}
		if (checkNull(list)) {
			throw new IllegalArgumentException("The list is not full to capacity");
		}
		Arrays.sort(list, sortOrder);
	}
}
