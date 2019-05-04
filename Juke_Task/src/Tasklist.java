import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Aingty Eung
 * This is the main program for the Task management.
 */
public class Tasklist {

	public static void main(String[] args) {
		/**
		 * This is used mainly for the new task section. For user's input
		 */
		Scanner input = new Scanner(System.in);
		/**
		 * tree is a HeapTree for the class Job
		 */
		HeapTree <Job> tree = new HeapTree <Job> ();
		/**
		 * keepGoing is used to loop the functionalities menu
		 */
		boolean keepGoing = true;
		/**
		 * response is used to get user's input
		 */
		int response;
		
		System.out.println("Welcome to the Task manager Program.");
		try{
			Scanner read = new Scanner(new File("taskList.txt"));
			while(read.hasNextLine()){
				/**
				 * arrayTemp is used to read and split the task from the taskList.txt file
				 */
				String[] arrayTemp = read.nextLine().split(", ");
				/**
				 * This is the construction of a Job class and adds it to the tree right away.
				 */
				tree.addNode(new Job(arrayTemp[0], arrayTemp[1]));
			}
			read.close();
		}catch(FileNotFoundException e){ System.out.println("File not Found!");}
		while (keepGoing){
			/**
			 * The loop of functionalities is called from here as well as doing the user's input check
			 */
			response = Functions.tasklistFunction();
			if(response == 1){
				/**
				 * This if statement is used to check if the tree is empty. In the case that every task is completed.
				 */
				if (tree.isEmpty()){
					System.out.println("No more task to do. Just add or quit.");
				}else{
					System.out.println("Here are all your tasks:");
					tree.printHeap();
					System.out.println("");
				}
			}
			else if(response == 2){
				if (tree.isEmpty()){
					System.out.println("No more task to do. Just add or quit.");
				}else{
					/**
					 * will print out the current task as well as the due date for that task
					 */
					System.out.println("Your current task is: "+tree.getNodeAt(0).getTask()+"      Due: "+tree.getNodeAt(0).getDate());
					System.out.println("");
				}
			}
			else if(response == 3){
				System.out.println("What is the new Task?");
				String newTask = input.nextLine();
				/**
				 * month is the month of the new task
				 * day is the date of the new task
				 * year is the year of the new task
				 * hour is used to get the hours of the new task from 0-24
				 * minute is the minute of the new task
				 */
				String month = Functions.newMonthCheck();
				String day = Functions.newDayCheck();
				String year = Functions.newYearCheck();
				String hour = Functions.newHourCheck();
				String minute = Functions.newMinuteCheck();
				/**
				 * dueDate is the combination of month, day, year, hour, and minute so that the tree can read it.
				 */
				String dueDate = month+"/"+day+"/"+year+" "+hour+":"+minute;
				tree.addNode(new Job(newTask, dueDate));
				System.out.println("The new task: "+newTask+" with Due Date: "+dueDate+" has been added to the tasklist");
				System.out.println(" ");
			}
			else if( response == 4){
				if (tree.isEmpty()){
					System.out.println("No more task to do. Just add or quit.");
				}else
				{
					System.out.println("The task: "+tree.getNodeAt(0).getTask()+" has been marked as completed. Retrieving new task......");
					tree.removeMin();
					/**
					 * Thread.sleep is used here as an illusion that the program is loading up the next task. Paused for 3 seconds.
					 */
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (tree.isEmpty()){
						System.out.println("No more task to do. Just add or quit.");
					}else{
						System.out.println("Your current task is: "+tree.getNodeAt(0).getTask()+"      Due: "+tree.getNodeAt(0).getDate());
						System.out.println("");
					}
				}
			}
			else if (response == 5){
				Job j1 = tree.removeMin();
				if (tree.isEmpty()){
					System.out.println("No more task to postpone. Just add or quit.");
				}
				else{
					Job j2 = tree.removeMin();
					tree.addNode(j1);
					System.out.println("Your next task is: "+j2.getTask()+"        Due: "+j2.getDate()+"  will be postponed.");
					/**
					 * month is the month of the postponed task
					 * day is the date of the postponed task
					 * year is the year of the postponed task
					 * hour is used to get the hours of the postponed task from 0-24
					 * minute is the minute of the postponed task
					 */
					String month = Functions.newMonthCheck();
					String day = Functions.newDayCheck();
					String year = Functions.newYearCheck();
					String hour = Functions.newHourCheck();
					String minute = Functions.newMinuteCheck();
					/**
					 * dueDate is the combination of month, day, year, hour, and minute so that the tree can read it.
					 */
					String dueDate = month+"/"+day+"/"+year+" "+hour+":"+minute;
					tree.addNode(new Job(j2.getTask(), dueDate));
					System.out.println("The task: "+j2.getTask()+"        Due: "+j2.getDate()+" has been postponed to "+dueDate);
				}
			}
			else if (response == 6){
				System.out.println("The task manager is shutting down.......");
				/**
				 * Thread.sleep is used here as an illusion that the program is shutting down. Paused for 2 seconds.
				 */
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("     GOODBYE!");
				keepGoing = false;
			}
		}
	}

}
