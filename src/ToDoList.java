import java.util.HashMap;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ToDoList {
		public static HashMap<String, String> myList = new HashMap<String, String>();
		public static void main(String[] args) {
			promptUser();
			taskStatus();
		}
		
		private static void checkStatus() {
			System.out.println("\nCurrent Status...\n");
			Set set = myList.entrySet();
			Iterator iterator = set.iterator();
			while(iterator.hasNext()) {
				Map.Entry mentry = (Map.Entry)iterator.next();
				System.out.println("Task: " + "\"" + mentry.getKey() + "\"" + "; Status: " + "\"" + mentry.getValue() + "\"");
			}
		}

		private static void promptUser() {
			System.out.println("Welcome to ToDoList, a command line task manager.");
			System.out.println("Please enter 5 tasks.");
			int n = 1;
				do {
					System.out.println("Task " + n + ": ");
					Scanner inputTask = new Scanner(System.in);
					String taskName = inputTask.nextLine();
					myList.put(taskName, "INCOMPLETE");
					n++;
				} while(n < 6);
			}		
		
		private static void taskStatus() {
			checkStatus();
			Scanner taskStatusInput = new Scanner(System.in);
			System.out.println("\nInput number:\n[1] Select a task\n[2] Exit program");
			int taskStatus = taskStatusInput.nextInt();
			
			do {
				if (taskStatus == 1) {
					taskSelect();
					break;
				} else if (taskStatus == 2) {
					exitProgram();
				} else {
					System.out.println("Invalid input.\n"
					+ "\nInput number:\n[1] Select a task\n[2] Exit program");
					taskStatus = taskStatusInput.nextInt();	
				}
			} while (taskStatus != 1 || taskStatus != 2);
		}

		private static void exitProgram() {
			System.out.println("\nThanks for using ToDoList!\n"
					+ "Press any key to exit...");
			Scanner exit = new Scanner(System.in);
			if(exit instanceof Scanner) {
				System.exit(0);
			}
		}

		private static void taskSelect() {
			checkStatus();
			Scanner taskSelectInput = new Scanner(System.in);
			System.out.println("\nEnter the exact name of the task you would like to select (case sensitive): ");
			String taskSelect = taskSelectInput.nextLine(); 
			String value = myList.get(taskSelect); //stores the methods output
			do {
				if(value != null) {
					System.out.println("\nTask: " + "\"" + taskSelect + "\"" + " Status: " + myList.get(taskSelect));
					break;
				} else {
					System.out.println("\nThe task name does not exist. Enter the exact name of the task you would like to select (case sensitive): ");
					checkStatus();
					taskSelect = taskSelectInput.nextLine();
				}
			} while(value == null);
			
			Scanner updateStatusInput = new Scanner(System.in);
			System.out.println("Input number:\n[1] Mark complete\n[2] Mark incomplete\n[3] Return to List\n[4] Exit Program");
			int updateStatus = updateStatusInput.nextInt();
			do {			
				switch(updateStatus) {
				case 1:
					myList.replace(taskSelect, "COMPLETE");
					System.out.println("\nTask: " + "\"" + taskSelect + "\"" + "; Status: " + "\"" + myList.get(taskSelect) + "\"");
					taskStatus();
					break;
				case 2:
					myList.replace(taskSelect, "INCOMPLETE");
					System.out.println("\nTask: " + "\"" + taskSelect + "\"" + "; Status: " + "\"" + myList.get(taskSelect) + "\"");
					taskStatus();
					break;
				case 3:
					taskStatus();
					break;
				case 4:
					exitProgram();
				default:
					System.out.println("Invalid input.\nInput number:\n[1] Mark complete\n[2] Mark incomplete\n[3] View List\n[4] Exit Program");
					updateStatus = updateStatusInput.nextInt();
					break;
				}
			} while(updateStatus != 4);
		}
	}