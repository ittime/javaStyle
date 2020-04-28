package lottery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class RgularNumbers implements LotoNumbers {

	private int min = 1;
	private int max = 37;
	private int total = 6;
	private int startWith = 0;
	private ArrayList<Integer> alRegularNumberList = new ArrayList<Integer>();

	/*
	 * generate() - RgularNumbers main method.
	 * 
	 * @see jsm.utility.lotto.LotoNumbers#generate()
	 */
	@Override
	public void generate() {
		multipleGenerateRandomNumber();
		sortArrayList();
		printArrayList();
	}

	/*
	 * generate() - Generate 6 regular numbers, using a loop and
	 * generateRandomNumber() method.
	 */
	private void multipleGenerateRandomNumber() {

		int temp = 0;

		for (int i = startWith; i < total;) {
			temp = generateRandomNumber();

			/***
			 *  if number not exist in array
			 *  write the the new number to the array , 
			 *  continue to next number.
			 */
			if (alRegularNumberList.contains(temp) == false) {
				alRegularNumberList.add(temp);
				temp = 0;
				i++;
			}
			
			// -!!- if number exist in array rerun current index loop -!!- //
		}

	}

	/*
	 * generateRandomNumber() - generate Random Number (one number).
	 */
	
	
	/**
	 * generateRandomNumber()
	 * @return number // a random number
	 */
	private int generateRandomNumber() {
		int number = 0;

		Random r = new Random();
		number = (r.nextInt((max - min) + 1) + min);

		return number;
	}

	/*
	 * sortArrayList() - sort ArrayList (
	 */
	public void sortArrayList() {
        // Ascending //
		Collections.sort(alRegularNumberList);

        // Descending 
        //   Collections.sort(alRegularNumberList, Collections.reverseOrder());	
	}

	/*
	 * printArrayList() - print ArrayList
	 */
	public void printArrayList() {
		for (int i = startWith; i < total; i++)
			System.out.printf("[%3d], ", alRegularNumberList.get(i));

	}

} // class//
