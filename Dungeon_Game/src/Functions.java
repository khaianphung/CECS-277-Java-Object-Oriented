import java.awt.Point;
import java.util.*;
public class Functions {
	static int direction;
	static int toDo;
	static boolean test = true;
	static Scanner input = new Scanner(System.in);
	public static int checkInput()
	{
		test = true;
		while (test) //Starting Testing
		{
			try{
				System.out.println("Choose a Direction:");
				System.out.println("    8. North");
				System.out.println("    6. East");
				System.out.println("    2. South");
				System.out.println("    4. West");
				direction = input.nextInt();
				test = false;
			}catch(java.util.InputMismatchException e){
				System.out.println("Your input is invalid, please try again.");
				input.next();
			}
		}
	
		while((direction != 8 && direction != 6 && direction != 2 && direction != 4))
		{
			System.out.println("Your input is invalid, please try again.");
			System.out.println("Choose a Direction:");
			System.out.println("    8. North");
			System.out.println("    6. East");
			System.out.println("    2. South");
			System.out.println("    4. West");
			test = true;
			while (test)
			{
				try{
					direction = input.nextInt();
					test = false;
				}catch(java.util.InputMismatchException e){
					System.out.println("Your input is invalid, please try again.");
					System.out.println("Choose a Direction:");
					System.out.println("    8. North");
					System.out.println("    6. East");
					System.out.println("    2. South");
					System.out.println("    4. West");
					input.next();
				}
			}
		}									//Ending Testing
		return direction;
	}
	public static boolean checkDirection(int d, Hero h)
	{
		Point j = h.getLocation();
		if (d == 8)
		{
			if (j.getX()-1 < 0)
			{
				return true;
			}
		}
		else if (d == 2)
		{
			if (j.getX()+1 > 3)
			{
				return true;
			}
		}
		else if (d == 4)
		{
			if (j.getY()-1 < 0)
			{
				return true;
			}
		}
		else if (d == 6)
		{
			if (j.getY()+1 > 3)
			{
				return true;
			}
		}
		return false;
		
	}
	public static int checkInput1(Hero h) // to check for "What to do?"
	{
		int healAble = 0;
		for (int i=0; i<h.getItem().size(); i++)
		{
			if (h.getItem().get(i).getValue() == 25)
				healAble++;
		}
		test = true;
		while (test) //Starting Testing
		{
			try{
				System.out.println("What do you do?");
				System.out.println("    1. Run Away");
				System.out.println("    2. Attack");
				if (healAble > 0)
				{
					System.out.println("    3. Heal ("+healAble+" Potion left)");
				}
				toDo = input.nextInt();
				test = false;
			}catch(java.util.InputMismatchException e){
				System.out.println("Your input is invalid, please try again.");
				input.next();
			}
		}
		while((toDo != 1 && toDo != 2 && toDo !=3))
		{
			System.out.println("Your input is invalid, please try again.");
			System.out.println("What do you do?");
			System.out.println("    1. Run Away");
			System.out.println("    2. Attack");
			if (healAble > 0)
			{
				System.out.println("    3. Heal ("+healAble+" Potion left)");
			}
			test = true;
			while (test)
			{
				try{
					toDo = input.nextInt();
					test = false;
				}catch(java.util.InputMismatchException e){
					System.out.println("Your input is invalid, please try again.");
					System.out.println("What do you do?");
					System.out.println("    1. Run Away");
					System.out.println("    2. Attack");
					if (healAble > 0)
					{
						System.out.println("    3. Heal ("+healAble+" Potion left)");
					}
					input.next();
				}
			}
		}									//Ending Testing
		return toDo;	
	}
	public static int checkInput2(Hero h)
	{
		int invalid = 0;
		int testSize = h.getItem().size();
		if (testSize >= 5)
		{
			invalid = 1;
		}
		test = true;
		while (test) //Starting Testing
		{
			try{
				System.out.println("What do you do?");
				System.out.println("    1. Sell it");
				System.out.println("    2. Store it");
				toDo = input.nextInt();
				test = false;
			}catch(java.util.InputMismatchException e){
				System.out.println("Your input is invalid, please try again.");
				input.next();
			}
		}
		while((toDo != 1 && toDo != 2 || (invalid == 1 && toDo == 2)))
		{
			if (invalid == 1)
			{
				System.out.println("Looks like your inventory is full, you can only sell the item.");
				System.out.println("Your input is invalid, please try again.");
				System.out.println("What do you do?");
				System.out.println("    1. Sell it");
				System.out.println("    2. Store it");
			}
			else{
				System.out.println("Your input is invalid, please try again.");
				System.out.println("What do you do?");
				System.out.println("    1. Sell it");
				System.out.println("    2. Store it");
			}
			
			test = true;
			while (test)
			{
				try{
					toDo = input.nextInt();
					test = false;
				}catch(java.util.InputMismatchException e){
					System.out.println("Your input is invalid, please try again.");
					System.out.println("What do you do?");
					System.out.println("    1. Sell it");
					System.out.println("    2. Store it");
					input.next();
				}
			}
		}
		return toDo;		
	}
	public static int checkInput3()// for returning back to starting position.
	{
		test = true;
		while (test) //Starting Testing
		{
			try{
				System.out.println("Sell all your items beside potions? (if you have any)");
				System.out.println("     1. Yes");
				System.out.println("     2. No");
				toDo = input.nextInt();
				test = false;
			}catch(java.util.InputMismatchException e){
				System.out.println("Your input is invalid, please try again.");
				input.next();
			}
			while((toDo != 1 && toDo != 2))
			{
				System.out.println("Your input is invalid, please try again.");
				System.out.println("Sell all your items beside potions? (if you have any)");
				System.out.println("     1. Yes");
				System.out.println("     2. No");
				test = true;
				while (test)
				{
					try{
						toDo = input.nextInt();
						test = false;
					}catch(java.util.InputMismatchException e){
						System.out.println("Your input is invalid, please try again.");
						input.next();
					}
				}
			}
		}
		return toDo;
	}
}
