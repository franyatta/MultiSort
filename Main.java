/*
 * Francine Vo
 * CS 251 Assignment 5A
 * 02/28/2024
 */
public class Main {

	public static void main(String[] args) {
		SortDataStructures a = new SortDataStructures();
		String[] b = a.readFile();

		// Outputs runtime and uses all 3 sorting methods
		a.runtimeCalculator(b);

		// Writes one of the sorted arrays with ASCII values to the file, “sorted.txt”
		for (String c : a.insertionSort(b)) {
			a.createFile(c);
		}
	}

}
