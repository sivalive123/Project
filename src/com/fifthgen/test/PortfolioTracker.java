package com.fifthgen.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PortfolioTracker {

	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("C:\\Users\\542294\\Desktop\\PortfolioTracker portfolio.txt");

		// reading the text file using scanner class
		Scanner readFile = new Scanner(file);

		// storing each line in a list dynamically
		ArrayList<String> portFolio = new ArrayList<String>();
		while (readFile.hasNextLine()) {
			portFolio.add(readFile.nextLine());
		}

		/*
		 * replacing commas present between two stocks in each line with empty
		 * space and converting the list to string array
		 */

		String[] portFolioInArray = new String[portFolio.size()];
		for (int i = 0; i < portFolio.size(); i++) {
			portFolioInArray[i] = portFolio.get(i).replace(",", " ");
		}

		int[] totalStockValue = new int[portFolioInArray.length];

		// this for loop indicates each line
		/*
		 * example:- for j=0, first line will be processed and for j=1, second
		 * line will be processed and so on...
		 */

		for (int j = 0; j < portFolioInArray.length; j++) {

			/*
			 * Separating stock symbol and values from each line with delimiter
			 * '-' and adding it in list
			 */

			ArrayList<String> al1 = new ArrayList<String>();
			StringTokenizer st = new StringTokenizer(portFolioInArray[j], "-");
			while (st.hasMoreElements()) {
				StringTokenizer st1 = new StringTokenizer(st.nextToken().trim(), " ");
				while (st1.hasMoreElements()) {
					al1.add(st1.nextToken());
				}
			}

			// converting the above list to string array
			String[] s1 = new String[al1.size()];
			for (int i = 0; i < al1.size(); i++) {
				s1[i] = al1.get(i);
			}

			/*
			 * taking only the stock values which will be present at the odd
			 * position index and converting the data type from string to
			 * integers
			 */
			int[] num = new int[s1.length / 2];
			int count1 = 0;
			for (int i = 0; i < s1.length; i++) {
				if (i % 2 != 0) {
					num[count1] = Integer.parseInt(s1[i]);
					count1++;
				}
			}

			/*
			 * adding the stock values from each line and storing in
			 * totalStockValue
			 */
			totalStockValue[j] = 0;
			for (int i = 0; i < num.length; i++) {
				totalStockValue[j] = totalStockValue[j] + num[i];

			}

			// clearing the list to repeat the same process for other lines
			al1.clear();

		}

		// converting the array to list for sorting
		ArrayList<Integer> al3 = new ArrayList<Integer>();
		for (int i = 0; i < totalStockValue.length; i++) {
			al3.add(totalStockValue[i]);
		}

		/*
		 * sorting the list and moving it in reverse for sorting in descending
		 * order
		 */
		for (int i = 0; i < totalStockValue.length; i++) {
			Collections.sort(al3, Collections.reverseOrder());
		}

		// converting the list to array
		int[] totalStockValueDesc = new int[al3.size()];
		for (int i = 0; i < al3.size(); i++) {
			totalStockValueDesc[i] = al3.get(i);
		}

		/*
		 * below code will compare total stock value before and after
		 * rearranging in descending order
		 */
		int j = 0;
		int i = 0;
		String[] finalOrder = new String[totalStockValueDesc.length];
		ArrayList<Integer> al4 = new ArrayList<Integer>();

		do {
			boolean emptySpaceFound = false;
			if (totalStockValue[i] != totalStockValueDesc[j]) {
				j++;
			} else {

				/*
				 * this do while is used to print two lines with same total
				 * values of stocks
				 */
				do {
					if (al4.contains(j)) {
						j++;

					} else {
						emptySpaceFound = true;

					}
				} while (j < totalStockValue.length && emptySpaceFound == false);

				finalOrder[j] = portFolio.get(i);
				al4.add(j);
				i++;
				j = 0;
			}

		} while (i < totalStockValue.length);

		// printing all the lines after sorting in descending order
		for (int k = 0; k < finalOrder.length; k++) {
			System.out.println(finalOrder[k]);
		}

	}
}
