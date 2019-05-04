import java.util.*;
import java.awt.Point;
import java.io.*;
public class Hero extends Character implements Serializable{
	/**
	 * Author Aingty Eung
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Item> items = new ArrayList<Item>();
	private Point location;
	private int[] damage = {0, 1, 2, 3, 1, 1, 2, 3, 3, 2, 6}; 
	Random rand = new Random();
	public Hero(String n, String q, int h, int l, int g, Point start) {
		super(n, q, h*l, l, g);
		this.location = start;
	}
	
	public ArrayList<Item> getItem()
	{
		return items;
	}
	
	public int getItemIndex(ArrayList<Item> u, int itemValue){
		int j;
		for (j = 0; j<=u.size();j++)
		{
			if (u.get(j).getValue()==itemValue)
			{
				break;
			}
		}
		return j;
	}
	public boolean pickUpItem(Item i)
	{
		int testSize = this.items.size();
		if(testSize < 5)
		{
			this.items.add(i);
			System.out.println("Item "+i.getName()+" has been stored. Backpack size is "+items.size());
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public void removeItem(Item i)
	{
		for (int j = 0; j<=items.size();j++)
		{
			if (items.get(j).getValue()==i.getValue())
			{
				items.remove(j);
				System.out.println("Item "+i.getName()+" has been removed from your inventory.");
				break;
			}
		}
	}
	public void removeItem(int index)
	{
		removeItem(this.items.get(index));
	}
	
	public Point getLocation()
	{
		return this.location;
	}
	
	public void setLocation(Point p)
	{
		this.location = p;
	}
	
	public char goNorth(Level l)
	{
		int x = (int) location.getX();
		int y = (int) location.getY();
		x -= 1;
		location.setLocation(x, y);
		return l.getRoom(location);
	}
	
	public char goSouth(Level l)
	{
		int x = (int) location.getX();
		int y = (int) location.getY();
		x += 1;
		location.setLocation(x, y);
		return l.getRoom(location);
	}
	
	public char goEast(Level l)
	{
		int x = (int) location.getX();
		int y = (int) location.getY();
		y += 1;
		location.setLocation(x, y);
		return l.getRoom(location);
	}
	
	public char goWest(Level l)
	{
		int x = (int) location.getX();
		int y = (int) location.getY();
		y -= 1;
		location.setLocation(x, y);
		return l.getRoom(location);
	}
	
	@Override
	public void attack(Character c) {
		int gen = rand.nextInt(10)+1;
		c.takeDamage(damage[gen]);
		System.out.println(super.getName()+" hits the "+c.getName()+" for "+damage[gen]+" damage.");
		if (c.getHp()<= 0)
		{
			System.out.println(super.getName()+" has defeated "+c.getName());
			System.out.println(super.getName()+" shouted, \""+super.getQuip()+"!!!\"");
		}
	}
}
