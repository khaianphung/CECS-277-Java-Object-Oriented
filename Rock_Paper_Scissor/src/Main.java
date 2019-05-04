import java.io.*;
import java.util.*;

public class Main {

	/**
	 * @author Aingty Eung
	 * @param args is default
	 * @userScore = the points for the user
	 * @compScore is the computer score
	 * @difficulties is the choice for easy or difficult
	 * @weapon is the user's throw
	 * @patLength is the pattern length that the computer will be looking at
	 * @singleChar is the current user throw
	 * @compThrow is the computer guess after the prediction
	 * @patternConstruct is used to store the user's pattern after @patLength
	 * @playing is the boolean to keep the game going in order for the game to loop until quit
	 * @c is the object Computer
	 * @f is the computer saved file
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		int userScore = 0;
		int compScore = 0;
		int difficulties = 0;
		int patLength = 4;
		
		String singleChar = null;
		String compThrow = null;
		String patternConstruct = "no";
		
		boolean playing = true;
		
		Computer c = null;
		Pattern p = null;
		File f = new File("data.dat");
		
		System.out.println("Welcome to ROCK, PAPER, SCISSOR!");
		if(f.exists())
		{
			difficulties = Functions.input();
			while (difficulties!=1 && difficulties!= 2)
			{
				System.out.println("Your input is invalid.");
				difficulties = Functions.input();
			}
			if (difficulties == 2)
			{
				System.out.println("Loading saved file.....");
				try{ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
					c = (Computer) in.readObject();
					in.close();
				}catch(IOException e)
				{
					System.out.println("NO FILE FOUND!");
				}catch(ClassNotFoundException e)
				{
					System.out.println("Class no found");
				}
				System.out.println("The computer has been loaded.");
			}
			else
			{
				System.out.println("A new computer has been constructed.");
				c = new Computer();
			}
			
		}
		else 
		{
			System.out.println("A previous saved file was not found, so a new computer has been constructed.");
			c = new Computer();
		}
		while (playing)
		{
			Functions.displayScore(userScore, compScore);
			singleChar = Functions.input1();
			while (!(singleChar.equalsIgnoreCase("r") || singleChar.equalsIgnoreCase("p") || singleChar.equalsIgnoreCase("s") || singleChar.equalsIgnoreCase("q")))
			{
				System.out.println("Sorry invalid input.");
				singleChar = Functions.input1();
			}
			if (singleChar.equalsIgnoreCase("q"))
			{
				if(patternConstruct.length()<patLength - 1)
				{
					System.out.println("Thank you for playing, no data was collected. So nothing to save.");
				}
				else
				{
					System.out.println("Thank you for playing, your data has been collected. Computer SAVED.");
					try{ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
						out.writeObject(c);
						out.close();
					}catch(IOException e){System.out.println("No file to write!");}
				}
				System.exit(0);
			}
			if (patternConstruct.equals("no"))
			{
				patternConstruct = singleChar;
			}
			else
			{
				patternConstruct = patternConstruct + singleChar;
			}
			if (patternConstruct.length()< patLength)
			{
				compThrow = c.makePrediction(patternConstruct);
			}
			else
			{
				compThrow = c.makePrediction(patternConstruct.substring(0, patLength-1));
			}
			if (patternConstruct.length() >= patLength)
			{
					p = new Pattern(patternConstruct);
					c.storePattern(p);
					patternConstruct = patternConstruct.substring(1);
			}
			if (compThrow.equalsIgnoreCase("r"))
			{
				System.out.println("The Computer throws a ROCK!");
			}
			else if (compThrow.equalsIgnoreCase("p"))
			{
				System.out.println("The Computer throws a PAPER!");
			}
			else if (compThrow.equalsIgnoreCase("s"))
			{
				System.out.println("The Computer throws a SCISSOR!");
			}
			
			
			//////////////////CALCULATING WINNER!!!!!////////////////////////////////////////////////////////////////////////
			if(singleChar.equalsIgnoreCase("r") && compThrow.equalsIgnoreCase("p"))
			{
				System.out.println("Computer wins this round.");
				compScore = compScore + 1;
			}
			else if(singleChar.equalsIgnoreCase("r") && compThrow.equalsIgnoreCase("s"))
			{
				System.out.println("You win this round.");
				userScore += 1;
			}
			else if(singleChar.equalsIgnoreCase("p") && compThrow.equalsIgnoreCase("r"))
			{
				System.out.println("You win this round.");
				userScore += 1;
			}
			else if(singleChar.equalsIgnoreCase("p") && compThrow.equalsIgnoreCase("s"))
			{
				System.out.println("Computer wins this round.");
				compScore = compScore + 1;
			}
			else if(singleChar.equalsIgnoreCase("s") && compThrow.equalsIgnoreCase("r"))
			{
				System.out.println("Computer wins this round.");
				compScore = compScore + 1;
			}
			else if(singleChar.equalsIgnoreCase("s") && compThrow.equalsIgnoreCase("p"))
			{
				System.out.println("You win this round.");
				userScore += 1;
			}
			else
			{
				System.out.println("This round is a tie.");
			}
		}
	}

}
