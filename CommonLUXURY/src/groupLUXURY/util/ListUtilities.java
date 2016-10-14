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
		
		/*
		 * Comparable[] list3 = (Comparable[]) Array.newInstance(list1.getClass().getComponentType(),
		 
				list1.length + list2.length);
		int i = 0;
		int j = 0;
		while (i < list1.length) {
				list3[j] = list1[i];
				j++;
				list3[j] = list2[i];
				j++;
				i++;
		}
		sort(list3);
<<<<<<< HEAD
//		Comparable[] list3copy = Arrays.copyOf(list3, list3.length);
//		Comparable
		
		Comparable [] listToReturn = list3;
=======
		*/
		
		
		
		
		
		//*
		//*isaak
		//*
		//*
		int p=0;
		int q=0;
		int r=0;
//
		//make new array 
		Comparable[] list3 = (Comparable[]) Array.newInstance(list1.getClass().getComponentType(),
				list1.length + list2.length);
		
		while (p<list1.length && q<list2.length)
		{
			if (list1[p].compareTo(list2[q]) == 0 ) // if same
			{
				//save p
				list3[r++] = list1[p];
				p++;
				q++;
			}
			else if (list1[p].compareTo(list2[q]) < 0 ) //p is smaller
			{
				//save p
				list3[r++] = list1[p];
				p++;
			}
			else if (list1[p].compareTo(list2[q]) > 0 ) //q is smaller
			{
				//save q
				list3[r++] = list2[q];
				q++;
			}
		}
		
		//shrink array by using the total counter r
		Comparable[] listToReturn = Arrays.copyOf(list3, r+1);
			

		
		
		//Comparable [] listToReturn = list3;
>>>>>>> branch 'master' of https://github.com/LuxuryDemands/Project.git
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
	
	public static int countDuplicates(Comparable[] list){
		int count = 0;
		for (int i =0;i<list.length;i++){
			if (i==list.length-1){
				break;
			}
			if (list[i].equals(list[i+1])){
				count++;
			}
		}
		return count;
	}
}
