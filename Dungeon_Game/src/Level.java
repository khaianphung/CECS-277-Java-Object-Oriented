import java.util.*;
import java.awt.Point;
import java.io.*;

public class Level {
	private char[][] level = new char[4][4];
	private List<String> temp = new ArrayList<String>();
	private int k = 0;
	private Point start = new Point(3,0);
	public Level()
	{
		//nothing here!!!!
	}
	
	public void generateLevel(int levelNum)
	{
		if(levelNum == 1)
		{
			try{
				Scanner read = new Scanner(new File("Level1.txt"));
				while(read.hasNext())
				{
					String l = read.next();
					temp.add(l);
				}
				
				read.close();
			}catch(FileNotFoundException e){
				System.out.println("Map1 File was not found.");
			}
		}
		if(levelNum == 2)
		{
			try{
				Scanner read = new Scanner(new File("Level2.txt"));
				while(read.hasNext())
				{
					String l = read.next();
					temp.add(l);
				}
				
				read.close();
			}catch(FileNotFoundException e){
				System.out.println("Map2 File was not found.");
			}
		}
		if(levelNum == 3)
		{
			try{
				Scanner read = new Scanner(new File("Level3.txt"));
				while(read.hasNext())
				{
					String l = read.next();
					temp.add(l);
				}
				
				read.close();
			}catch(FileNotFoundException e){
				System.out.println("Map3 File was not found.");
			}
		}
		for(int row = 0; row<=3; row++){
			for(int col = 0; col<=3; col++){
				level[row][col] = temp.get(k).charAt(0); 
				k++;
			}
		}
	}
	
	public char getRoom(Point p)
	{
		return level[p.x][p.y];
	}
	
	public void displayMap(Point p)
	{
		System.out.println(" _________");
		System.out.print("| ");
		for(int row = 0; row<=3; row++){
			for(int col = 0; col<=3; col++){
				if (p.getX()==row && p.getY()==col)
				{
					System.out.print("* ");
				}
				else{
					System.out.print(level[row][col]+" ");
				}
			}
			System.out.print("|");
			System.out.println();
			if (row !=3)
			{
				System.out.print("| ");
			}
			else{
				System.out.println(" ---------");
			}
		}
	}
	
	public Point findStartLocation()
	{
		start.x = 3;
		start.y = 0;
		return start;
	}
}
