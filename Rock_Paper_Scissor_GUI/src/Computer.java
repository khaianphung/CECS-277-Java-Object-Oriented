import java.io.Serializable;
import java.util.*;

/**
 * 
 * @author Aingty Eung
 *  @map = is the hashmap for the patterns
 */
public class Computer implements Serializable {
	private HashMap<Pattern, Integer> map;
	
	/**
	 * Construct a new hashmap with size 702.
	 */
	public Computer()
	{
		map = new HashMap<Pattern, Integer>(702);
		
	}
	
	/**
	 * 
	 * @param p passes in the one less pattern in order to predict the next outcome.
	 * @return the string value of the next computer's guess.
	 */
	public String makePrediction(String p)
	{
		/*
		 * rand is used to throw a random guess if the computer can't find a pattern
		 */
		Random rand = new Random();
		int random = rand.nextInt(3)+1;
		/*
		 * rock is a pattern to concatenate the next guess that ends in rock
		 */
		Pattern rock = new Pattern(p + "r");
		/*
		 * paper is a pattern to concatenate the next guess that ends in paper
		 */
		Pattern paper = new Pattern(p + "p");
		/*
		 * scissor is a pattern to concatenate the next guess that ends in scissor
		 */
		Pattern scissor = new Pattern(p + "s");
		/*
		 * tempR is used to find the occurence of the pattern with rock in the end
		 */
		int tempR = 0;
		/*
		 * tempP is used to find the occurrences of the pattern with paper in the end
		 */
		int tempP = 0;
		/*
		 * tempS is used to find the occurrences of the pattern with the 
		 */
		int tempS = 0;
		/*
		 * if statement used to check if the hastmap of the computer is empty.
		 */
		if (this.map.isEmpty())
		{
			if (random == 1)
			{
				return "r";
			}
			else if (random == 2)
			{
				return "p";
			}
			else 
			{
				return "s";
			}
		}
		else
		{
			if (this.map.containsKey(rock))
			{
				tempR = this.map.get(rock);
			}
			if (this.map.containsKey(paper))
			{
				tempP = this.map.get(paper);
			}
			if (this.map.containsKey(scissor)) 
			{
				tempS = this.map.get(scissor);
			}
			if (tempR == tempP && tempR == tempS)// if they are all equal occurrences
			{
//				System.out.println("It seems that I can't predict what is next, due to equal or no occurrences. I will guess randomly.");
				if (random == 1)
				{
					return "r";
				}
				else if (random == 2)
				{
					return "p";
				}
				else 
				{
					return "s";
				}
			}
			else if (tempR > tempP && tempR >= tempS) // potential Rock being the biggest
			{
			
				if (tempR == tempS)// if rock occurrences equals scissor
				{
//					System.out.println("It seems that I found a pattern where you will either throw Rock or Scissor next. I guessed randomly.");
					if (rand.nextInt(2)+1 == 1)
					{
						return "p";
					}
					else
					{
						return "r";
					}
				}
				else
				{
//					System.out.println("I have predicted that you will throw Rock next. So I threw Paper.");
					return "p";
				}
			}
			else if (tempP >= tempR && tempP > tempS)// potential paper being the biggest
			{
				if (tempP == tempR)
				{
//					System.out.println("It seems that I found a pattern where you will either throw Rock or Paper next. I guessed randomly.");
					if (rand.nextInt(2)+1 == 1)
					{
						return "p";
					}
					else
					{
						return "s";
					}			
				}
				else
				{
//					System.out.println("I have predicted that you will throw Paper next. So I threw Scissor.");
					return "s";
				}
			}
			else if (tempS > tempR && tempS >= tempP) // potential scissor being the biggest
			{
				if (tempS == tempP)
				{
//					System.out.println("It seems that I found a pattern where you will either throw Scissor or Paper next. I will guess randomly.");
					if (rand.nextInt(2)+1 == 1)
					{
						return "r";
					}
					else
					{
						return "s";
					}			
				}
				else
				{
//					System.out.println("I have predicted that you will throw Scissor next. So I threw Rock.");
					return "r";
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param p passes in the pattern in order to store it as a key in the hashmap
	 */
	public void storePattern(Pattern p)
	{
		if (this.map.containsKey(p))
		{
			map.put(p, this.map.get(p)+1);
		}
		else
		{
			map.put(p, 1);
		}
	}
}
