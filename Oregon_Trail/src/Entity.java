/**
 * 
 * @author Aingty Eung
 * This class is used as the super class so that other classes can inherit from it.
 */
import java.awt.*;
public abstract class Entity {
	/*
	 * location is a Rectangle class used to describe the location of an entity
	 */
	private Rectangle location;
	/*
	 * hp is the health point of the entity
	 */
	private int hp;
	/*
	 * speed is the pace at which the entity travels 
	 */
	private int speed;
	/*
	 * moving is the boolean value of whether the entity is moving at this time
	 */
	private boolean moving;
	/*
	 * direction is the cardinal directions of the entity (N, NE, E, SE, S, SW, W, NW) {1-8}
	 */
	private int direction;
	
	/**
	 * This is the constructor for the Entity class
	 * @param p is the point so that it can be placed into rectangle location with its x and y
	 * @param w is the width of the Rectangle location
	 * @param h is the height of the Rectangle location
	 * @param hp is the health point
	 * @param sp is the speed of that Entity
	 * @param dir is the direction of that entity
	 */
	public Entity(Point p, int w, int h, int hp, int sp, int dir){
		this.location = new Rectangle((int) p.getX(), (int) p.getY(), w, h);
		this.hp = hp;
		this.speed = sp;
		this.direction = dir;
		this.moving = false;
	}
	
	/**
	 * This is the method to return location of the Entity as a Point
	 * @return the Point location of the Entity
	 */
	public Point getLocation(){
		/*
		 * p is a temporary point, used to return the point location.
		 */
		Point p = this.location.getLocation();
		return p;
	}
	
	/**
	 * This is the method to return the width of entity
	 * @return width location of Entity
	 */
	public int getWidth(){
		return this.location.width;
	}
	
	/**
	 * This is the method to return the height of the entity
	 * @return the int type of height of entity
	 */
	public int getHeight(){
		return this.location.height;
	}
	
	/**
	 * This is the method to return the hp of an entity
	 * @return int value of entity's hp
	 */
	public int getHp(){
		return this.hp;
	}
	
	/**
	 * This is the method to return the speed of the Entity
	 * @return the int value of speed of Entity.
	 */
	public int getSpeed(){
		return this.speed;
	}
	
	/**
	 * This is the method to get the direction of the Entity
	 * @return the int value of the 8 directions of Entity
	 */
	public int getDirection(){
		return this.direction;
	}
	
	/**
	 * This is the method to check if the Entity is alive by using the hp variable
	 * @return the boolean value of the Entity's hp
	 */
	public boolean isDead(){
		if (this.hp <= 0 ){
			return true;
		}
		else 
			return false;
	}
	
	/**
	 * This is the method to take away the hp of the Entity.
	 */
	public void takeHit(){
		this.hp = 0;
	}
	
	/**
	 * This is the method to change the direction clock wise for the Entity
	 */
	public void spinCW(){
		if (this.direction == 8){
			this.direction = 1;
		}
		else
			this.direction++; 
	}
	
	/**
	 * This method is used to spin the entity counter clock wise in a loop
	 */
	public void spinCCW(){
		if(this.direction == 1){
			this.direction = 8;
		}
		else 
			this.direction--;
	}
	
	/**
	 * This method is used to assign a new direction to the Entity
	 * @param d is the new direction of the Entity
	 */
	public void setDirection(int d){
		this.direction = d;
	}
	
	/**
	 * This method is used to move the Entity based on their speed
	 */
	public void move(){
		/* 
		 * x is the temp location of Entity on X's axis
		 */
		int x = this.location.x;
		/*
		 * y is the temp location of Entity on Y's axis
		 */
		int y = this.location.y;
		switch(this.direction)
		{
		case 1: //N
			y -= this.speed;
			this.location.setLocation(x, y);
			break;
		case 2: //NE
			y-= this.speed;
			x+= this.speed;
			this.location.setLocation(x, y);
			break;
		case 3: //E
			x+= this.speed;
			this.location.setLocation(x, y);
			break;
		case 4: //SE
			y+= this.speed;
			x+= this.speed;
			this.location.setLocation(x, y);
			break;
		case 5: //S
			y += this.speed;
			this.location.setLocation(x,y);
			break;
		case 6: //SW
			y+= this.speed;
			x-= this.speed;
			this.location.setLocation(x,y);
			break;
		case 7: //W
			x-= this.speed;
			this.location.setLocation(x, y);
			break;
		case 8: //NW
			x-= this.speed;
			y-= this.speed;
			this.location.setLocation(x, y);
			break;
		}
	}
	
	/**
	 * This method is used to toggle move or stop for Entity
	 */
	public void toggleMoving(){
		if (this.moving == false)
			this.moving = true;
		else
			this.moving = false;
	}
	
	/**
	 * This method is used to make the Entity stop moving
	 */
	public void stopMoving(){
		this.moving = false;
	}
	
	/**
	 * This is the abstract method that update will call in order to redraw every Entity
	 * @param g is the graphic type of an entity
	 * @param p is the x and y coordinate of an entity
	 * @param w is the width of an entity
	 * @param h is the height of an entity
	 * @param dir is the directon of an entity
	 */
	public abstract void draw(Graphics g, Point p, int w, int h, int dir);
	
	/**
	 * This is the method used to draw and redraw an entity to the Jpanel
	 * @param g is the type graphic data used to redraw
	 */
	public void update(Graphics g){
		if (this.moving && !this.isDead()){
			move();
		}
		draw(g, this.getLocation(), this.getWidth(), this.getHeight(), this.getDirection());
	}
}
