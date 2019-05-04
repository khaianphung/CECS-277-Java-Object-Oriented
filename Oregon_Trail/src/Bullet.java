import java.awt.*;

/**
 * @author Aingty Eung
 * This class is used to be a bullet from the hunter.
 */
public class Bullet extends Entity{

	/**
	 * This is the constructor for Bullet class that calls the super constructor
	 * @param p is the x and y coordinate 
	 * @param w is the width 
	 * @param h is the height of bullet
	 * @param hp is the hit point of the bullet which should be zero here
	 * @param sp is the speed
	 * @param dir is the direction of the bullet
	 */
	public Bullet(Point p, int w, int h, int hp, int sp, int dir) {
		super(p, w, h, hp, sp, dir);
	}
	
	/**
	 * This is the method used to test whether the bullet collide with an Entity
	 * @param e is the Entity that is being used to test
	 * @return the boolean value of whether the bullet hit the Entity
	 */
	public boolean testCollision(Entity e){
		/*
		 * testBullet is used as a rectangle for the bullet
		 */
		Rectangle testBullet = new Rectangle(super.getLocation().x, super.getLocation().y, super.getWidth(), super.getHeight());
		/*
		 * testEntity is used as the current entity whose placement will be determined if it is hit by the bullet.
		 */
		Rectangle testEntity = new Rectangle(e.getLocation().x, e.getLocation().y, e.getWidth(), e.getHeight());
		return testBullet.intersects(testEntity);
	}
	
	/**
	 * This is the overrided method to draw the bullet
	 */
	@Override
	public void draw(Graphics g, Point p, int w, int h, int dir) {
		g.setColor(Color.orange);
		g.fillRect(p.x, p.y, w, h);
		
		
	}

}
