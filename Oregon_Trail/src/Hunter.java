import java.awt.*;
import java.util.ArrayList;

/**
 * 
 * @author Aingty
 * This class is used to resemble the hunter that is an Entity in the game
 */
public class Hunter extends Entity {
	/*
	 * bulletList is the arraylist of bullet used to allow multiple bullet at once
	 */
	private ArrayList <Bullet> bulletList = new ArrayList<Bullet>();
	/*
	 * bullet is the type Bullet used for hunter to fire
	 */
	private Bullet bullet;
	/**
	 * This is the constructor for Bullet class that calls the super constructor
	 * @param p is the x and y coordinate 
	 * @param w is the width 
	 * @param h is the height of bullet
	 * @param hp is the hit point of the bullet which should be zero here
	 * @param sp is the speed
	 * @param dir is the direction of the bullet
	 */
	public Hunter(Point p, int w, int h, int hp, int sp) {
		super(p, w, h, hp, sp, 1);
	}
	
	/**
	 * This is the method used to construct the bullet of the hunter and sent it flying
	 */
	public void fireBullet(){
		/*
		 * halfWidth is the center width of the hunter
		 */
		int halfWidth = super.getLocation().x + super.getWidth()/2;
		/*
		 * halfHeight is the center height of the hunter
		 */
		int halfHeight = super.getLocation().y + super.getHeight()/2;
		Point P = new Point(halfWidth, halfHeight);
		bullet = new Bullet(P, 5, 2, 1, 50, super.getDirection());
		bulletList.add(bullet);
		bulletList.get(bulletList.size()-1).toggleMoving();
	}
	
	/**
	 * This method is used to test to see if the bullet fired by hunter hits anything
	 * @param e is the entity used to test the bullet collision
	 * @return the boolean value of whether or not bullet is hit
	 */
	public boolean testHit(Entity e){
		/*
		 * removeB is the arrayList of bullet so that bullet can be removed when it hits something
		 */
		ArrayList<Bullet> removeB = new ArrayList<Bullet>();
		/*
		 * bool is the boolean value of whether or not the bullet hit something
		 */
		boolean bool = false;
		if(bulletList.size()>0){
			for (Bullet b: bulletList){
				if(b.testCollision(e)){
					removeB.add(b);
					bool = true;
				}
			}
		}
		for (Bullet b:removeB){
			bulletList.remove(b);
		}
		return bool;
	}

	/**
	 * This is the overrided method to draw the bullet
	 */
	@Override
	public void draw(Graphics g, Point p, int w, int h, int dir) {
		g.setColor(Color.green);
		g.fillOval(p.getLocation().x, p.getLocation().y, w, h);
		this.setDirection(dir);
		g.setColor(Color.ORANGE);
		/*
		 * Switch is used to get the drawing of the gun.
		 */
		switch(this.getDirection())
		{
		case 1: //N
			g.drawLine(this.getLocation().x + this.getWidth()/2, this.getLocation().y + this.getHeight()/2, this.getLocation().x + this.getWidth()/2, this.getLocation().y - this.getHeight()/2);
			break;
		case 2: //NE
			g.drawLine(this.getLocation().x + this.getWidth()/2, this.getLocation().y + this.getHeight()/2, this.getLocation().x + this.getWidth()/2 +this.getWidth() - 15, this.getLocation().y + this.getHeight()/2 - this.getHeight()+15);
			break;
		case 3: //E
			g.drawLine(this.getLocation().x + this.getWidth()/2, this.getLocation().y + this.getHeight()/2, this.getLocation().x + this.getWidth()/2 + this.getWidth(), this.getLocation().y + this.getHeight()/2);
			break;
		case 4: //SE
			g.drawLine(this.getLocation().x + this.getWidth()/2, this.getLocation().y + this.getHeight()/2, this.getLocation().x + this.getWidth()/2 +this.getWidth() - 15, this.getLocation().y + this.getHeight()/2 + this.getHeight()-15);
			break;
		case 5: //S
			g.drawLine(this.getLocation().x + this.getWidth()/2, this.getLocation().y + this.getHeight()/2, this.getLocation().x + this.getWidth()/2, this.getLocation().y + this.getHeight()/2 + this.getHeight());
			break;
		case 6: //SW
			g.drawLine(this.getLocation().x + this.getWidth()/2, this.getLocation().y + this.getHeight()/2, this.getLocation().x + this.getWidth()/2 - this.getWidth() +15, this.getLocation().y + this.getHeight()/2 + this.getHeight()-15);
			break;
		case 7: //W
			g.drawLine(this.getLocation().x + this.getWidth()/2, this.getLocation().y + this.getHeight()/2, this.getLocation().x + this.getWidth()/2 - this.getWidth(), this.getLocation().y + this.getHeight()/2);
			break;
		case 8: //NW
			g.drawLine(this.getLocation().x + this.getWidth()/2, this.getLocation().y + this.getHeight()/2, this.getLocation().x + this.getWidth()/2 - this.getWidth() +15, this.getLocation().y + this.getHeight()/2 - this.getHeight()+15);
			break;
		}
		if(bulletList.size()>0){
			for (int i =0; i<bulletList.size(); i++){
				bulletList.get(i).update(g);
			}
		}
	}
}