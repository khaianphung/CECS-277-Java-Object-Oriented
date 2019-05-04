import java.io.Serializable;
import java.util.*;

/**
 * 
 * @author Aingty Eung
 *  @map = is the hashmap for the patterns
 *  @rand is used to throw the random guess
 *  @rock is the pattern with a rock at the end
 *  @paper is the pattern with a paper at the end
 *  @scissor is the pattern with a paper at the end
 *  @tempR is the occurrence of pattern with rock at the end
 *  @tempP is the occurrence of pattern with paper at the end
 *  @tempS is the occurrence of pattern with scissor at the end
 */
public class Computer implements Serializable {
	private HashMap<Object, Integer> map = new HashMap();
	private Random rand = new Random();
	private String rock;
	private String paper;
	private String scissor;
	private int tempR;
	private int tempP;
	private int tempS;
	
	/**
	 * Construct a new hashmap with size 702.
	 */
	public Computer()
	{
		this.map = new HashMap(702);
	}
	
	/**
	 * 
	 * @param p passes in the one less pattern in order to predict the next outcome.
	 * @return the string value of the next computer's guess.
	 */
	public String makePrediction(String p)
	{
		int random = rand.nextInt(3)+1;
		rock = p + "r";
		paper = p + "p";
		scissor = p + "s";
		tempR = 0;
		tempP = 0;
		tempS = 0;
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
			System.out.println(tempP + " " + tempR + " " + tempS);
			if (tempR == tempP && tempR == tempS)// if they are all equal occurrences
			{
				System.out.println("It seems that I can't predict what is next, due to equal or no occurrences. I will guess randomly.");
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
					System.out.println("It seems that I found a pattern where you will either throw Rock or Scissor next. I guessed randomly.");
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
					System.out.println("I have predicted that you will throw Rock next. So I threw Paper.");
					return "p";
				}
			}
			else if (tempP >= tempR && tempP > tempS)// potential paper being the biggest
			{
				if (tempP == tempR)
				{
					System.out.println("It seems that I found a pattern where you will either throw Rock or Paper next. I guessed randomly.");
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
					System.out.println("I have predicted that you will throw Paper next. So I threw Scissor.");
					return "s";
				}
			}
			else if (tempS > tempR && tempS >= tempP) // potential scissor being the biggest
			{
				if (tempS == tempP)
				{
					System.out.println("It seems that I found a pattern where you will either throw Scissor or Paper next. I will guess randomly.");
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
					System.out.println("I have predicted that you will throw Scissor next. So I threw Rock.");
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
		if (this.map.containsKey(p.getPattern()))
		{
			map.put(p.getPattern(), this.map.get(p.getPattern())+1);
		}
		else
		{
			map.put(p.getPattern(), 1);
		}
	}
}
