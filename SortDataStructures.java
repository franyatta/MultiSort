/*
 * Francine Vo
 * CS 251 Assignment 5A
 * 02/28/2024
 */
import java.io.*;

public class SortDataStructures {

	public String[] bubbleSort(String[] strs) {
		// Bubble sort compares pairs of adjacent strings and the strings are swapped if
		// they are not in order by ASCII value sums
		for (int i = 0; i < strs.length - 1; i++) {
			for (int j = 0; j < strs.length - i - 1; j++) {
				if (calculateTotalAsciiValue(strs[j]) > calculateTotalAsciiValue(strs[j + 1])) {
					String temp = strs[j];
					strs[j] = strs[j + 1];
					strs[j + 1] = temp;
				}
			}
		}
		return strs;
	}

	public String[] insertionSort(String[] strs) {
		// Insertion sort compares elements to the left and shifts to the right to make
		// room to insert a value

		for (int i = 1; i < strs.length; i++) {
			String temp = strs[i];
			int j = i - 1;
			while (j >= 0 && calculateTotalAsciiValue(temp) < calculateTotalAsciiValue(strs[j])) {
				strs[j + 1] = strs[j];
				j--;
			}
			strs[j + 1] = temp;
		}
		return strs;
	}

	public String[] mergeSort(String[] strs) {
		// Merge sort recursively divides array in 2, sorts, then re-combines the
		// sub-arrays
		int length = strs.length;
		if (length <= 1)
			return strs;

		int middle = length / 2;
		String[] leftArray = new String[middle];
		String[] rightArray = new String[length - middle];

		for (int i = 0; i < middle; i++) {
			leftArray[i] = strs[i];
		}
		for (int i = middle; i < length; i++) {
			rightArray[i - middle] = strs[i];
		}

		mergeSort(leftArray);
		mergeSort(rightArray);
		merge(leftArray, rightArray, strs);
		return strs;
	}

	public void merge(String[] leftArr, String[] rightArr, String[] strs) {

		int leftSize = leftArr.length;
		int rightSize = rightArr.length;
		int i = 0, l = 0, r = 0;

		// Check conditions for merging
		while (l < leftSize && r < rightSize) {
			if (calculateTotalAsciiValue(leftArr[l]) < calculateTotalAsciiValue(rightArr[r])) {
				strs[i] = leftArr[l];
				l++;
			} else {
				strs[i] = rightArr[r];
				r++;
			}
			i++;
		}

		// Copies remaining elements to left and right arrays, if any
		while (l < leftSize) {
			strs[i] = leftArr[l];
			i++;
			l++;
		}
		while (r < rightSize) {
			strs[i] = rightArr[r];
			i++;
			r++;
		}
	}

	public String[] readFile() {
		// This method reads in the file and then returns an array of the strings
		StringBuilder strings = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader("input.txt"));
			String line;
			while ((line = in.readLine()) != null) {
				strings.append(line).append("\n");
			}
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(e);
		}
		return strings.toString().split("\n");
	}

	public static int calculateTotalAsciiValue(String str) {
		// This method will calculate and return the total ASCII value of each string.
		int totalAsciiValue = 0;

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			int asciiValue = (int) ch;
			totalAsciiValue += asciiValue;
		}
		return totalAsciiValue;
	}

	public void createFile(String c) {
		// This method outputs a file "sorted.txt" that includes all the words from the
		// imported file and also prints their ASCII values next to the string.
		try {
			File file = new File("sorted.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write(c + " " + calculateTotalAsciiValue(c) + "\n");
			writer.close();
		} catch (IOException e) {
			System.err.print(e);
		}
	}

	public void runtimeCalculator(String[] strings) {
		// This method calculates the run time of each sorting method and prints it out
		// to console
		try {
			// Performs bubble sort and calculates the time
			long startTime = System.nanoTime();
			bubbleSort(strings);
			long endTime = System.nanoTime();
			System.out.println("Bubble sort time: " + (endTime - startTime) + " nano seconds");

			// Performs insertion sort and calculates the time
			startTime = System.nanoTime();
			insertionSort(strings);
			endTime = System.nanoTime();
			System.out.println("Insertion sort time: " + (endTime - startTime) + " nano seconds");

			// Performs merge sort and calculates the time
			startTime = System.nanoTime();
			mergeSort(strings);
			endTime = System.nanoTime();
			System.out.println("Merge sort time: " + (endTime - startTime) + " nano seconds");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
