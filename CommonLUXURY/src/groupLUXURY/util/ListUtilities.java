/**
 * 
 */
package groupLUXURY.util;

/**
 * @author Sebastian
 *
 */
public class ListUtilities {
	
	@SuppressWarnings({"rawtypes","unchecked"})
	public static void sort(Comparable[] list) {
		int length = list.length;
		int j, first, temp;
		for (int i = 0; ) {
			first = 0; // initialize to subscript of first element
			for (j = 1; j <= i; j++) // locate smallest element between
										// positions 1 and i.
			{
				if (num[j] < num[first])
					first = j;
			}
			temp = num[first]; // swap smallest found with element in position
								// i.
			num[first] = num[i];
			num[i] = temp;
		}
	}

}
