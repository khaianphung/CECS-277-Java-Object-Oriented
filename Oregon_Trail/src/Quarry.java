import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * @author Aingty
 * This is the Quarry class used to represent an animal as an Entity
 */
public class Quarry extends Entity{
	/*
	 * name is the name of the quarries
	 */
	private String name;
	/*
	 * weight is the weight aka speed determine the quarries
	 */
	private int weight;
	
	/**
	 * This is the constructor for the animal, aka quarry class
	 * @param p is the point location of the quarry
	 * @param w is the width of the quarry
	 * @param h is the height of the quarry
	 * @param hp is the hit point of the quarry 
	 * @param sp is the speed of the quarry
	 * @param n is the name of the quarry
	 * @param wt is the weight of the quarry
	 */
	public Quarry(Point p, int w, int h, int hp, int sp, String n, int wt) {
		super(p, w, h, hp, sp, 3);
		this.name = n;
		this.weight = wt;
	}

	/**
	 * This is the method to get the name of the quarry
	 * @return the string value of the quarry's name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * This is the method to get the weight of the quarry to determine its speed
	 * @return the int value of the quarry's weight
	 */
	public int getWeight(){
		return this.weight;
	}
	
	/**
	 * This is the overridden method used to draw the quarry for the game
	 * @param g is the graphic passed for the quarries to be drawned.
	 * @param p is the point of the quarries
	 * @param w is the width of the quarries
	 * @param h is the height of the quarries
	 * @param dir is the direction of the quarries
	 */
	@Override
	public void draw(Graphics g, Point p, int w, int h, int dir) {
		if (this.isDead()){ //Checking to see if the quarry is dead, then print it as a red quarry
			g.setColor(Color.RED);
			g.fillOval(p.x, p.y, w, h);
		}else{
			g.setColor(Color.cyan);
			g.fillOval(p.x, p.y, w, h);
		}
	}

}
