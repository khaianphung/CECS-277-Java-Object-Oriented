/**
 * 
 * @author Aingty Eung
 * This class is used to do user's input check. For extra purposes only.
 */
import java.util.*;
public class Functions {
	/**
	 * This is the scanner used to get user's input
	 */
	static Scanner input = new Scanner(System.in);
	/**
	 * This is used to do the input check on the functionalities for the jukebox
	 * @return the integer value which is the selections
	 */
	public static int jukboxFunction(){
		boolean test = false;
		int userInput = -1;
		while(!test){
			System.out.println("     Here are the functionalities of this Jukebox:");
			System.out.println("          1. Display the list of songs");
			System.out.println("          2. Display current song");
			System.out.println("          3. Add a new song to the list");
			System.out.println("          4. Play next song");
			System.out.println("          5. Re-rate next song (will prompt for new rating)");
			System.out.println("          6. Quit");
			try{
				userInput = input.nextInt();
				if (!(userInput==1 || userInput==2 || userInput==3 || userInput==4 || userInput==5 || userInput==6)){
					System.out.println("The option you've selected is not a valid one. Please try again.");
					
				}else{
					test = true;
				}
			}catch(InputMismatchException e){System.out.println("Sorry, but that was an invalid input. Try again."); input.next();}
		}
		return userInput;
	}
	
	public static int newRating(){
		boolean test = false;
		int userInput = -1;
		while(!test){
			System.out.println("Please input the rating of the song (1-5)");
			try{
				userInput = input.nextInt();
				if (!(userInput==1 || userInput==2 || userInput==3 || userInput==4 || userInput==5)){
					System.out.println("The rating that you've selected is invalid. Please try again.");
					
				}else{
					test = true;
				}
			}catch(InputMismatchException e){System.out.println("Sorry, but that was an invalid input. Try again."); input.next();}
		}
		return userInput;
	}
	
	public static int tasklistFunction(){
		boolean test = false;
		int userInput = -1;
		while(!test){
			System.out.println("     Here are the functionalities of this manager:");
			System.out.println("          1. Display the list of tasks");
			System.out.println("          2. Display current task");
			System.out.println("          3. Add a new item to the task list");
			System.out.println("          4. Mark Current task completed");
			System.out.println("          5. Postpone next task (will prompt for new due date)");
			System.out.println("          6. Quit");
			try{
				userInput = input.nextInt();
				if (!(userInput==1 || userInput==2 || userInput==3 || userInput==4 || userInput==5 || userInput==6)){
					System.out.println("The option you've selected is not a valid one. Please try again.");
					
				}else{
					test = true;
				}
			}catch(InputMismatchException e){System.out.println("Sorry, but that was an invalid input. Try again."); input.next();}
		}
		return userInput;
	}
	/**
	 * This method is used to check the month of the new task
	 * @return month
	 */
	public static String newMonthCheck(){
		boolean test = false;
		int month = -1;
		while(!test){
			System.out.println("Enter the month");
			try{
				month = input.nextInt();
				if (month <= 0 || month>= 13){
					System.out.println("The month is invalid. (1-12)");
				}else{
					test = true;
				}
			}catch(InputMismatchException e){System.out.println("Sorry, but that was an invalid input. Try again."); input.next();}
		}
		return Integer.toString(month);
	}
	/**
	 * This method is used to check the day of the new task
	 * @return day
	 */
	public static String newDayCheck(){
		boolean test = false;
		int day = -1;
		while(!test){
			System.out.println("Enter the day");
			try{
				day = input.nextInt();
				if (day <= 0 || day>= 32){
					System.out.println("The day is invalid. (1-31)");
				}else{
					test = true;
				}
			}catch(InputMismatchException e){System.out.println("Sorry, but that was an invalid input. Try again."); input.next();}
		}
		return Integer.toString(day);
	}
	/**
	 * This method is used to Year the minute of the new task
	 * @return year
	 */
	public static String newYearCheck(){
		boolean test = false;
		int year = -1;
		while(!test){
			System.out.println("Enter the year");
			try{
				year = input.nextInt();
				if (year <= 2015){
					System.out.println("The year is invalid. (Greater than or equal to 2016)");
				}else{
					test = true;
				}
			}catch(InputMismatchException e){System.out.println("Sorry, but that was an invalid input. Try again."); input.next();}
		}
		return Integer.toString(year);
	}
	/**
	 * This method is used to check the hour of the new task
	 * @return hour
	 */
	public static String newHourCheck(){
		boolean test = false;
		int hour = -1;
		while(!test){
			System.out.println("Enter the hour");
			try{
				hour = input.nextInt();
				if (hour < 0 || hour>24){
					System.out.println("The hour is invalid. (0-24)");
				}else{
					test = true;
				}
			}catch(InputMismatchException e){System.out.println("Sorry, but that was an invalid input. Try again."); input.next();}
		}
		return Integer.toString(hour);
	}
	/**
	 * This method is used to check the minute of the new task
	 * @return minutes
	 */
	public static String newMinuteCheck(){
		boolean test = false;
		int minute = -1;
		while(!test){
			System.out.println("Enter the minute");
			try{
				minute = input.nextInt();
				if (minute < 0 || minute>59){
					System.out.println("The minute is invalid. (0-59)");
				}else{
					test = true;
				}
			}catch(InputMismatchException e){System.out.println("Sorry, but that was an invalid input. Try again."); input.next();}
		}
		/**
		 * This if statement is used incase the minute is equal to zero, if it is the case, then it just add another zero to the end of minute to follow the xx:xx format.
		 */
		if (minute == 0){
			String minute1 = Integer.toString(minute)+"0";
			return minute1;
		}else{
			return Integer.toString(minute);
		}
	}
}
