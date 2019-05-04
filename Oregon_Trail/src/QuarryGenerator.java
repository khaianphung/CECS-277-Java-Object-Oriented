import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/**
 * @author Aingty
 * This is the class used to generate quarry from a text file
 */
public class QuarryGenerator {
	/*
	 * quarry is the arraylist used to store all the animals
	 */
	private ArrayList <Quarry> quarry;
	/*
	 * arrayTemp is the array list used to store all the strings from the text file
	 */
	private List<String> arrayTemp = new ArrayList<String>();
	/*
	 * rand is the random generator to return a random quarry and random spwan points
	 */
	Random rand = new Random();
	
	/**
	 * This is the constructor for the quarry generator
	 */
	public QuarryGenerator(){
		try{
			Scanner read = new Scanner(new File("QuarryList.txt"));
			while(read.hasNextLine()){
				/*
				 * Temp is the string variable used to store one line from the text file with commas
				 */
				String Temp = read.nextLine();
				arrayTemp.add(Temp);
			}
			read.close();
			
		}catch(FileNotFoundException e){ System.out.println("File not Found!");}
	}
	
	/**
	 * This is the method used to generate a random quarry
	 * @return the type quarry
	 */
	public Quarry generateQuarry(){
		quarry = new ArrayList<Quarry>();
		
		/*
		 * rand is the random generator to return a random quarry and random spwan points
		 */
		Random rand = new Random();
		/*
		 * for loop used to add all the quarry to the quarry arraylist
		 */
		for (int ran = 0; ran < arrayTemp.size(); ran++){
			String[] quarryArrayTemp = arrayTemp.get(ran).split(",");
			Point randPoint = getRandPoint();
			/*
			 * if statements are used here to increase rectangle size based on weight
			 */
			if(Integer.parseInt(quarryArrayTemp[1])>2 && Integer.parseInt(quarryArrayTemp[1])<=75){ //Bear and Deer
				quarry.add(new Quarry(randPoint, 55, 55, Integer.parseInt(quarryArrayTemp[2]), 3, quarryArrayTemp[0], Integer.parseInt(quarryArrayTemp[1])));
				quarry.get(quarry.size()-1).setDirection(correctDirection(randPoint));
			}
			if(Integer.parseInt(quarryArrayTemp[1])>76){// Bison
				quarry.add(new Quarry(randPoint,70, 70, Integer.parseInt(quarryArrayTemp[2]), 1, quarryArrayTemp[0], Integer.parseInt(quarryArrayTemp[1])));
				quarry.get(quarry.size()-1).setDirection(correctDirection(randPoint));
			}
			else 
			{
				quarry.add(new Quarry(randPoint, 45, 45, Integer.parseInt(quarryArrayTemp[2]), 4, quarryArrayTemp[0], Integer.parseInt(quarryArrayTemp[1])));
				quarry.get(quarry.size()-1).setDirection(correctDirection(randPoint));
			}
		}
		return quarry.get(rand.nextInt(7));
	}
	
	/**
	 * This method is used to get a random fixed spwan location for the quarry so that they don't run off the map. 
	 * @return Point location for the quarry
	 */
	public Point getRandPoint(){
		/*
		 * spwan1 is the Point at 250, 30
		 */
		Point spwan1 = new Point(250,-70);
		/*
		 * spwan2 is the Point at 850, 30
		 */
		Point spwan2 = new Point(850,-70);
		/*
		 * spwan3 is the Point at 1400, 100
		 */
		Point spwan3 = new Point(1400,100);
		/*
		 * spwan4 is the Point at 1400, 700
		 */
		Point spwan4 = new Point(1400,700);
		/*
		 * spwan5 is the Point at 1400, 900
		 */
		Point spwan5 = new Point(1400,900);
		/*
		 * spwan6 is the Point at 900, 900
		 */
		Point spwan6 = new Point(900,900);
		/*
		 * spwan7 is the Point at 750, 900
		 */
		Point spwan7 = new Point(750,900);
		/*
		 * spwan8 is the Point at 100, 900
		 */
		Point spwan8 = new Point(100,900);
		/*
		 * spwan9 is the Point at 30, 500
		 */
		Point spwan9 = new Point(-70,500);
		/*
		 * spwanPoint is the Point array for all the spwans location
		 */
		Point[] spwanPoint = {spwan1, spwan2, spwan3, spwan4, spwan5, spwan6, spwan7, spwan8, spwan9};
		return spwanPoint[rand.nextInt(9)];
	}
	
	/**
	 * This method is used to recalibrate the direction of the quarries
	 * @param p is the spawn point of the quarries
	 * @return the correct int direction
	 */
	public int correctDirection(Point p){
		if ( (p.y == -70 && (p.x == 250 || p.x == 850)) ){ // testing for the top spwan point 
			int[] direction = {6, 5, 4};
			return direction[rand.nextInt(3)];
		}
		else if(p.x == 1400 && p.y == 100){
			return 6;
		}
		else if (p.x ==1400 && p.y == 700){
			int [] direction = {8 ,7, 6};
			return direction[rand.nextInt(3)];
		}
		else if(p.x == 1400 && p.y == 900){
			return 8;
		}
		else if ( (p.x == 900 || p.x == 750 )&& p.y == 900){
			int [] direction = {8 , 1, 2};
			return direction[rand.nextInt(3)];
		}
		else if (p.x ==100 && p.y == 900){
			return 2;
		}
		else 
		{
			int [] direction = {2 , 3, 4};
			return direction[rand.nextInt(3)];
		}
	}
}
