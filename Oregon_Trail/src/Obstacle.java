import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Aingty
 * This is the obstacle class used to create tree and bush.
 */
public class Obstacle extends Entity implements ImageObserver{
	/**
	 * type is the integer value of what type of obstacle it is. 
	 */
	private int type;
	
	/**
	 * This is the constructor for the Obstacle class 
	 * @param p is the x and y coordinate of the Obstacle
	 * @param t is the integer type of the obstacle
	 */
	public Obstacle(Point p, int t) {
		super(p, 60, 60, 1, 0, 1);
		this.type = t;
	}
	
//	/**
//	 * This is the method used to get the type of the obstacles
//	 * @return the int value of type obstacles: 1 = tree, 2 = bush, 3 = rock.
//	 */
//	public int getType(){
//		return this.type;
//	}

	/**
	 * This is the method used to test if an obstacle collided with something
	 * @param e is the entity used to test if the entity is colliding with the obstacle
	 * @return the boolean value of whether the two rectangles intersect each other. 
	 */
	public boolean testCollision(Entity e){
		/*
		 * testObstacle is used as a rectangle for the obstacle 
		 */
		Rectangle testObstacle = new Rectangle(super.getLocation().x, super.getLocation().y, super.getWidth(), super.getHeight());
		/*
		 * testEntity is used as the current entity whose placement will be determined if it is hit by the obstacle.
		 */
		Rectangle testEntity = new Rectangle(e.getLocation().x, e.getLocation().y, e.getWidth(), e.getHeight());
		
		return testObstacle.intersects(testEntity);
	}
	
	@Override
	public void draw(Graphics g, Point p, int w, int h, int dir) {
		if (this.type==1){
			try{
				BufferedImage img = ImageIO.read(new File("grass_x1.png"));
				g.drawImage(img, this.getLocation().x, this.getLocation().y, this.getWidth(), this.getHeight(), this);
		}catch(IOException e){System.out.println("No image found!"); e.printStackTrace();}
//			g.setColor(Color.RED);
//			g.fillRect(this.getLocation().x, this.getLocation().y, this.getWidth(), this.getHeight());
		}
		else if (this.type==2){
//			g.setColor(Color.BLUE);
//			g.fillRect(this.getLocation().x, this.getLocation().y, this.getWidth(), this.getHeight());
			try{
				BufferedImage img = ImageIO.read(new File("JunglerockAlpha.png"));
				g.drawImage(img, this.getLocation().x, this.getLocation().y, this.getWidth(), this.getHeight(), this);
		}catch(IOException e){System.out.println("No image found!"); e.printStackTrace();}
		}
		else if (this.type== 3){
//			g.setColor(Color.YELLOW);
//			g.fillRect(this.getLocation().x, this.getLocation().y, this.getWidth(), this.getHeight());
			try{
				BufferedImage img = ImageIO.read(new File("n3rGp.png"));
				g.drawImage(img, this.getLocation().x, this.getLocation().y, this.getWidth(), this.getHeight(), this);
		}catch(IOException e){System.out.println("No image found!"); e.printStackTrace();}
		}
		
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
}
