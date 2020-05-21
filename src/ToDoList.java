import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ToDoList {
	public static HashMap<String, String> myList = new HashMap<String, String>(); // Creates a Hasmap that stores two string values
	public static void main(String[] args) { 
		promptUser(); // Calls the method that prompts the user to enter the five tasks
		taskStatus(); // Prints the status of each task to the user after the variables have been assigned
	}

	private static void promptUser() { // Prompts user to enter their five tasks
		System.out.println("Welcome to ToDoList, a command line task manager."); 
		System.out.println("Please enter 5 tasks");
		int n = 1; // n is created as a stopper for the do-while loop
		do {
			System.out.println("Task " + n + ": ");
			Scanner inputTask = new Scanner(System.in); // Scanner is created to parse primitive types
			String taskName = inputTask.nextLine(); // The value of the String input is stored in taskName
			myList.put(taskName, "INCOMPLETE"); // The status of each task is INCOMPLETE by default
			n++;
		} while(n < 6); // Stops user from inputting more than 5 tasks
	}

	private static void checkStatus() { // Prints the key-value pairs 
		System.out.println("\nCurrent Status...\n"); 
		Set set = myList.entrySet(); // Creates and initializes a hash set for iteration
		Iterator iterator = set.iterator(); // Iterator allows iteration through the values in the hashmap list
		while(iterator.hasNext()) { // Continues to print out each key-value pair if the iterator has another token 
			Map.Entry mentry = (Map.Entry)iterator.next(); // Obtains reference to map entry from the iterator 
			System.out.println("Task: " + "\"" + mentry.getKey() + "\"" + "; Status: " + "\"" + mentry.getValue() + "\""); // Keys and values are printed from the map
		}
	}

	private static void taskStatus() { // Prompts user to choose between selecting a task or exiting the program
		checkStatus(); // Presents user with their tasks and corresponding status
		Scanner taskStatusInput = new Scanner(System.in);
		System.out.println("\nInput number:\n[1] Select a task\n[2] Exit program"); // User is asked to input number 1 or 2
		int taskStatus = taskStatusInput.nextInt(); // taskStatus is initialized. 
		do { 
			if (taskStatus == 1) {
				taskSelect(); // Calls the taskSelect function if input is 1
				break;
			} else if (taskStatus == 2) {
				exitProgram(); // Calls the exitProgram function if input is 2
			} else {
				System.out.println("Invalid input.\n" // Asks user to try again if the input is not 1 or 2
				+ "\nInput number:\n[1] Select a task\n[2] Exit program"); 
				taskStatus = taskStatusInput.nextInt();	// Reassigns taskStatus if input is incorrect
			}
		} while (taskStatus != 1 || taskStatus != 2); // Loop stops if equal to 1 or 2
	}

	private static void taskSelect() { // Allows the user to target a certain task and change its status
		checkStatus(); // The key-value pairs are printed to the user
		Scanner taskSelectInput = new Scanner(System.in);
		System.out.println("\nEnter the name of the task you would like to select: "); // User is prompted to enter the exact name of the task
		String taskSelect = taskSelectInput.nextLine(); 
		System.out.println("\nTask: " + "\"" + taskSelect + "\"" + " Status: " + myList.get(taskSelect)); // .get is used to obtain the value of the given task
		Scanner updateStatusInput = new Scanner(System.in);
		System.out.println("Input number:\n[1] Mark complete\n[2] Mark incomplete\n[3] Return to List\n[4] Exit Program"); // User is asked to input numbers between 1-4
		int updateStatus = updateStatusInput.nextInt(); // The input value is initialized in updateStatus
		do {			
			switch(updateStatus) { // A switch case is used for the various number choices
			case 1: 
				myList.replace(taskSelect, "COMPLETE"); // If user inputs 1 the item is marked as complete
				System.out.println("\nTask: " + "\"" + taskSelect + "\"" + "; Status: " + "\"" + myList.get(taskSelect) + "\"");
				taskStatus(); // The user is returned to the previous menu
				break; // Prevents the loop from calling the same case
			case 2:
				myList.replace(taskSelect, "INCOMPLETE"); // If user inputs 2 the item is marked as incomplete.
				System.out.println("\nTask: " + "\"" + taskSelect + "\"" + "; Status: " + "\"" + myList.get(taskSelect) + "\"");
				taskStatus(); 
				break;
			case 3:
				taskStatus(); // If user inputs 3, they are returned to the previous menu
				break;
			case 4:
				exitProgram(); // If user inputs 4, then the program will exit
			default:
				System.out.println("Invalid input.\nInput number:\n[1] Mark complete\n[2] Mark incomplete\n[3] View List\n[4] Exit Program");
				updateStatus = updateStatusInput.nextInt(); // default case prompts the user to try again, and updateStatus is reassigned
				break; 
			}
		} while(updateStatus != 4); // The loop continues to run until the user chooses to exit the program
	}

	private static void exitProgram() { // Allows the user to exit the program
		System.out.println("\nThanks for using ToDoList!\n"
				+ "Press any key to exit..."); // A good-bye message is displayed
		Scanner exit = new Scanner(System.in);
		if(exit instanceof Scanner) {  // The system exits when the user presses a key
			System.exit(0);
		}
	}
}
