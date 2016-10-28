/**
 * 
 */
package groupLUXURY.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author Sebastian
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
	 *
	 * @throws IllegalArgumentException
	 *             if either parameter is not full to capacity.
	 *
	 * @throws NullPointerException
	 *             if the either list is null.
	 */
	@SuppressWarnings({ "rawtypes" })
	public static Comparable[] merge(Comparable[] list1, Comparable[] list2) throws FileNotFoundException {
		//checks if any of the list is null and throws exception
		if (list1 == null || list2 == null) {
			throw new NullPointerException("Neither of the lists can be null");
		}
		//checks if any element in the list is null and throw exception
		if (checkNull(list1) || checkNull(list2)) {
			throw new NullPointerException("One of the lists is not full to capacity");
		}
		//creates new comparable array
		Comparable[] list3 = (Comparable[]) Array.newInstance(list1.getClass().getComponentType(),
				(list1.length + list2.length) - countDuplicates(list1, list2));
		ArrayList<String> duplicates = duplicatesList(list1, list2);
		saveListToTextFile(duplicates);
		for (int i = 0, j = 0; i < list3.length; i++, j++) {
			list3[i] = list1[j];
			i++;
			if (!(list2[j] == null)) {
				list3[i] = list2[j];
			} else {
				i--;
			}
		}
		sort(list3);
		return list3;
	}
	
	//note from kajal
	// i need this method to accept a String for the path. Thank you~!
	public static <E> void saveListToTextFile(ArrayList<E> list) throws FileNotFoundException{
		File file = new File("C:\\Users\\Sebastian\\Desktop\\Project\\ReservationSys\\datafiles\\duplicates.txt");
		PrintWriter print = new PrintWriter(file);
		for (int i=0;i<list.size();i++){
			print.println(list.get(i));
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
	

	@SuppressWarnings("rawtypes")
	private static ArrayList<String> duplicatesList(Comparable[] list1, Comparable[] list2) {
		ArrayList<String> hold = new ArrayList<>();
		for (int i = 0; i < list1.length; i++) {
			for (int j = 0; j < list2.length; j++) {
				if (list1[i].equals(list2[j])) {
					hold.add(list1[i].toString() + "(merged)");
					hold.add(list2[j].toString());
					list2[j] = null;
				}
			}
		}
		return hold;
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
	private static int countDuplicates(Comparable[] list1, Comparable[] list2) {
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
}
