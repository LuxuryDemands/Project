/**
 * 
 */
package group.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import groupLUXURY.util.ListUtilities;

/**
 * @author Sebastian
 *
 */
public class ListUtilitiesTest {

	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		testTheMergeMethod();
	}

	@SuppressWarnings({ "unused" })
	public static void testTheMergeMethod() throws FileNotFoundException, IOException {
		String[] stringArr1 = { "nonduplicate", "duplicate" };
		String[] stringArr2 = { "nonduplicate1", "duplicate" };
		String[] stringArr3 = { "nonduplicate", "duplicate" };
		String[] stringArr4 = { "nonduplicate1", "duplicates" };
		Integer[] intArr1 = { 1, 2, 3 };
		Integer[] intArr2 = { 2, 3, 4 };
		testTheMergeMethod("Case 1 - Valid data (2 String arrays with a duplicate in both)", stringArr1, stringArr2,
				"../ReservationSys/datafiles/testCase1.txt", true);
		testTheMergeMethod("Case 2 - Valid data (2 String arrays with no duplicates in both)", stringArr3, stringArr4,
				"../ReservationSys/datafiles/testCase2.txt", true);
//		testTheMergeMethod("Case 3 - Invalid data (2 different type of arrays)", stringArr3, intArr1,
//				"../ReservationSys/datafiles/testCase3.txt", false);
	}

	@SuppressWarnings("rawtypes")
	private static void testTheMergeMethod(String testCase, Comparable[] list1, Comparable[] list2,
			String duplicateFilename, boolean expectValid) throws FileNotFoundException, IOException {
		System.out.println("   " + testCase);
		ListUtilities.merge(list1, list2, duplicateFilename);
		System.out.print("\tThe Room list instance was created: ");
		if (!expectValid)
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		System.out.println("\n");
	}
}
