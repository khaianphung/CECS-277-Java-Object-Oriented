import java.util.*;

public class Functions {
	static Scanner input = new Scanner(System.in);
	static Scanner input1 = new Scanner(System.in);
	
	public static int input()
	{
		int x = 0;
		boolean test = true;
		while(test)
		{
			System.out.println("	Please choose a difficulty ");
			System.out.println("            1. Easy");
			System.out.println("            2. A challenge");
			try{ 
				x = input.nextInt(); 
				test = false;
			}catch(InputMismatchException e){System.out.println("Sorry invalid input. Please Choose 1 or 2."); input.next();}
		}
		return x;
	}
	
	public static String input1()
	{
		String x = null;
		boolean test = true;
		while(test)
		{
			System.out.println("    Please choose what to do");
			System.out.println(" 			r. Rock");
			System.out.println(" 			p. Paper");
			System.out.println(" 			s. Scissor");
			System.out.println(" 			q. Quit");
			try{
				x = input1.nextLine();
				test = false;
			}catch(InputMismatchException e){System.out.println("Sorry invalid input."); input.next();}
		}
		return x;
	}
	
	public static void displayScore(int x, int y)
	{
		System.out.println("Your Score:				"+x);
		System.out.println("Computer's Score:			"+y);
	}
}
