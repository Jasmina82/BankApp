package bank.main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Inputs {
	
	public static Scanner input = new Scanner(System.in);

	//metod returns input of a string
	public static String getUserInput(String message) {

		System.out.println(message);

		return input.nextLine();
	}

	//metod returns input of a integer
	public static int getUserIntegerInput(String message) {

		System.out.println(message);
		int userInput = 0;
		boolean wrong = true;

		do {
			try {
				userInput = input.nextInt();
				wrong = false;
				input.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Wrong input!");
				input.nextLine();
			}
		} while (wrong);
		return userInput;

	}
	
	//metod returns input of a double value
		public static double getUserDoubleInput(String message) {

			System.out.println(message);
			double userInput = 0;
			boolean wrong = true;

			do {
				try {
					userInput = input.nextDouble();
					wrong = false;
					input.nextLine();
					if(userInput<1 || userInput>1000000000) {
						System.out.println("For this operation alowed amount is from 1 to 1000.000.000 $!Try again:");
						wrong=true;
					}
					
				} catch (InputMismatchException e) {
					System.out.println("Wrong input!");
					input.nextLine();
				}
			} while (wrong);
			return userInput;

		}
}
