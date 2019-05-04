import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/**
 * @author Aingty
 * This is the Panel class used to draw contents to the frame which extends JPanel and implements KeyListener, MouseListener, MouseMotionListener, and Runnable
 */
public class Panel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Runnable{
	/*
	 * hunter is the user's character
	 */
	private Hunter hunter;
	/*
	 * qGen is the quarry generator 
	 */
	private QuarryGenerator qGen;
	/*
	 * obstacles is an array list of all the obstacles being created
	 */
	private ArrayList<Obstacle> obstacles;
	/*
	 * quarries is the arraylist of all the quarries in the game
	 */
	private ArrayList<Quarry> quarries;
	/*
	 * timer is the game's time for user to play
	 */
	private int timer;
	/*
	 * killcount is the user's kills
	 */
	private int killCount;
	/*
	 * bulletCount is how many times the user fired
	 */
	private int bulletCount;
	/*
	 * bulletClip is used to record the current number of round in a clip
	 */
	private int bulletClip;
	/*
	 * bulletAccuracy is used to calculate the user's accuracy
	 */
	private double bulletAccuracy;
	/*
	 * killStat is used to display the user's killcount
	 */
	private JLabel killStat;
	/*
	 * starting is used to calculate the user's time remaining in the game
	 */
	private JLabel starting;
	/*
	 * reloads is used to display the bulletclip of the user
	 */
	private JLabel reloads;
	/*
	 * bulletStat is used to display the number of bullet fired
	 */
	private JLabel bulletStat;
	/*
	 * accuracy is used to display the accuracy
	 */
	private JLabel accuracy; 
	/*
	 * rand is used to do a rand generate number for quarries and obstacle positions
	 */
	Random rand = new Random();
	
	/**
	 * This is the constructor for the JPanel class.
	 */
	public Panel(){
		this.setBackground(Color.BLACK);
		addMouseMotionListener(this);
		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);
		timer = 30;
		killCount = 0;
		/*
		 * 6 in clip because of a revolver.
		 */
		bulletClip = 6;
		starting = new JLabel("Time Left:  "+timer);
		killStat = new JLabel("             Kills: "+killCount);
		reloads = new JLabel("                       Clip: "+bulletClip+"/6");
		bulletStat = new JLabel("                                        Bullet Fires: "+bulletCount);
		accuracy = new JLabel("                                                                   Bullet Accuracy: "+bulletAccuracy);
		accuracy.setForeground(Color.WHITE);
		reloads.setForeground(Color.GREEN);
		bulletStat.setForeground(Color.MAGENTA);
		killStat.setForeground(Color.yellow);
		starting.setForeground(Color.YELLOW);
		hunter = new Hunter(new Point(30,400), 50, 50, 1, 2);
		qGen = new QuarryGenerator();
		quarries = new ArrayList<Quarry>();
		obstacles = new ArrayList<Obstacle>();
		this.add(starting);
		this.add(killStat);
		this.add(reloads);
		this.add(bulletStat);
		this.add(accuracy);
		/*
		 * Creating 10 random location and type obstacles and putting them into an array
		 */
		for (int i = 0; i < 10; i++){
			Point p = new Point(rand.nextInt(1000), rand.nextInt(528));
			Obstacle tempObstacle = new Obstacle(p, rand.nextInt(3)+1);
			obstacles.add(tempObstacle);
		}
		/*
		 * Generating 2 random quarries 
		 */
		for (int i=0; i<2; i++){
			quarries.add(qGen.generateQuarry());
		}
		/*
		 * used to start the message box process
		 */
		Thread p = new Thread(){
			public void run(){
				while(timer > 0){
					try{
						Thread.sleep(100);
						killStat.setText("             Kills: "+killCount);
						reloads.setText("                       Clip: "+bulletClip+"/6");
						bulletStat.setText("                                        Bullet Fires: "+bulletCount);
						accuracy.setText("                                                                   Bullet Accuracy: "+ Math.floor(bulletAccuracy *100));
					} catch(InterruptedException e){
						System.out.println("Error in the Label reading");}
				}
			}
		};
		p.start();
		Thread t = new Thread(){
			public void run(){
				while(timer > 0){
					try{
						Thread.sleep(1000);
						timer--;
						starting.setText("Time Left:  "+timer);
					} catch(InterruptedException e){
						System.out.println("Error in the Label reading");}
					}
			}
		};
		t.start();
		
	}
	
	/**
	 * This method is used to print to the JPanel of the game
	 * @param g is the graphics type tool used to draw
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		testCollisions();
		for (int i = 0; i<obstacles.size();i++){
			obstacles.get(i).update(g);
		}
		hunter.update(g);
		for (int i = 0; i<quarries.size();i++){
			if(!(quarries.get(i).isDead())){
				quarries.get(i).toggleMoving();

			}
			quarries.get(i).update(g);
		}
	}
	
	/**
	 * This method is used to check if any entity have collided with each other
	 */
	public void testCollisions(){
		/*
		 * Testing hunter collision with Obstacles
		 */
		for (int i = 0; i <obstacles.size(); i++){
			if (obstacles.get(i).testCollision(hunter)){
				for(int k = 1; k<=4; k++){
					hunter.spinCW();
				}
				hunter.move();
				hunter.stopMoving();
				for (int k1 =1; k1<=4; k1++){
					hunter.spinCW();
				}
			}
			hunter.testHit(obstacles.get(i));
		}
		/*
		 * Testing hunter collision with quarries
		 */
		for (int i= 0; i < quarries.size(); i++){
			Rectangle q1 = new Rectangle(quarries.get(i).getLocation().x, quarries.get(i).getLocation().y, quarries.get(i).getWidth(), quarries.get(i).getHeight());
			Rectangle q2 = new Rectangle(hunter.getLocation().x, hunter.getLocation().y, hunter.getWidth(), hunter.getHeight());
			if (q1.intersects(q2)){ //If Hunter and quarries collied 
				if(!(quarries.get(i).isDead())){
					for (int k = 1; k<=rand.nextInt(8)+1; k++){
						quarries.get(i).spinCW();
					}
					quarries.get(i).move();
					hunter.stopMoving();
				}
				else
				{
					for (int k1 =1; k1<=4; k1++){
						hunter.spinCW();
					}
					hunter.move();
					hunter.stopMoving();
					for (int k1 =1; k1<=4; k1++){
						hunter.spinCW();
					}
				}
			}
			if (hunter.testHit(quarries.get(i))){
				if (!(quarries.get(i).isDead())){
					quarries.add(qGen.generateQuarry());
					killCount++;
					bulletAccuracy = (double) killCount/ (double) bulletCount;
				}
				
				quarries.get(i).takeHit();
				quarries.get(i).stopMoving();
			}
		}
		/*
		 * Testing obstacles collision with other obstacles
		 */
		for (int i = 0; i<obstacles.size();i++){
			for(int j = 0; i<obstacles.size();j++){
				if (i == j){
					j++;
				}
				if (j >= obstacles.size()){
					break;
				}
				else{
					if (obstacles.get(i).testCollision(obstacles.get(j)) || (obstacles.get(i).testCollision(hunter))){
						obstacles.set(i, new Obstacle(new Point(rand.nextInt(1050), rand.nextInt(528)), rand.nextInt(3)+1));
						testCollisions();
					}
				}
			}
		}
		/*
		 * Testing quarries collision with quarries
		 */
		for (int i = 0; i < quarries.size(); i++){
			for (int j = 0; j < quarries.size(); j++){
				if (i == j){
					j++;
				}
				if (j >= quarries.size()){
					break;
				}
				else{
					Rectangle q1 = new Rectangle(quarries.get(i).getLocation().x, quarries.get(i).getLocation().y, quarries.get(i).getWidth(), quarries.get(i).getHeight());
					Rectangle q2 = new Rectangle(quarries.get(j).getLocation().x, quarries.get(j).getLocation().y, quarries.get(j).getWidth(), quarries.get(j).getHeight());
					if (q1.intersects(q2)){ //If two quarries collied 
						for (int k = 1; k<=rand.nextInt(8)+1; k++){
							quarries.get(i).spinCW();
						}
						quarries.get(i).move();
					}
				}
			}
			/*
			 * Testing quarries collision with obstacles
			 */
			for (int j = 0; j<obstacles.size();j++){
				if (obstacles.get(j).testCollision(quarries.get(i))){
					for (int k = 1; k<=rand.nextInt(8)+1; k++){
						quarries.get(i).spinCCW();
					}
					quarries.get(i).move();
				}
			}
		}
		/*
		 * Testing hunter out of bound 
		 */
		if (hunter.getLocation().x < 0 || hunter.getLocation().x > 1050 || hunter.getLocation().y < 0 || hunter.getLocation().y >528){
			for(int k = 1; k<=4; k++){
				hunter.spinCW();
			}
			hunter.move();
			hunter.stopMoving();
			for(int k = 1; k<=4; k++){
				hunter.spinCW();
			}
		}
		/*
		 * Testing quarries out of bound
		 */
		for (int i = 0; i < quarries.size(); i++){
			if (quarries.get(i).getLocation().x < -70 || quarries.get(i).getLocation().x > 1400 || quarries.get(i).getLocation().y < -70 || quarries.get(i).getLocation().y > 900){
				quarries.remove(i);
				quarries.add(qGen.generateQuarry());
			}
		}
	}
	
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This overridden method is used to track the mouse's movement in order for the hunter to face the correct direction
	 * @param e is the MoseEvent used to track
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		/*
		 * hunterRect is the rectangle made to track the hunter's position
		 */
		Rectangle hunterRect = new Rectangle(hunter.getLocation().x, hunter.getLocation().y, hunter.getWidth(), hunter.getHeight());
		Point mouse = new Point(e.getX(), e.getY());
		if ( (mouse.x > hunterRect.getCenterX()-25 && mouse.x < hunterRect.getCenterX() + 25 && mouse.y < hunterRect.getCenterY())){
			hunter.setDirection(1);
		}
		if ( mouse.x > hunterRect.getCenterX()+25 && mouse.y < hunterRect.getCenterY()-25){
			hunter.setDirection(2);
		}
		if (mouse.x > hunterRect.getCenterX()+25 && mouse.y > hunterRect.getCenterY()-25 && mouse.y < hunterRect.getCenterY()+25){
			hunter.setDirection(3);
		}
		if (mouse.x > hunterRect.getCenterX()+25 && mouse.y > hunterRect.getCenterY()+25){
			hunter.setDirection(4);
		}
		if (mouse.x > hunterRect.getCenterX() -25 && mouse.x < hunterRect.getCenterX()+25 && mouse.y > hunterRect.getCenterY()){
			hunter.setDirection(5);
		}
		if (mouse.x < hunterRect.getCenterX()-25 && mouse.y > hunterRect.getCenterY() + 25){
			hunter.setDirection(6);
		}
		if (mouse.x < hunterRect.getCenterX() -25 && mouse.y > hunterRect.getCenterY()-25 && mouse.y < hunterRect.getCenterY()+25){
			hunter.setDirection(7);
		}
		if (mouse.x < hunterRect.getCenterX() -25 && mouse.y < hunterRect.getCenterY() -25){
			hunter.setDirection(8);
		}
		
	}

	/**
	 * This method is used to record the mouse's click and fire the hunter's gun
	 * @param e is the MouseEvent for the clicking
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (bulletClip > 0){
			bulletCount++;
			hunter.fireBullet();
			play("bullet.wav");
			bulletClip--;
			bulletAccuracy = (double) killCount/ (double) bulletCount;
			if (bulletClip == 0){
				reloads.setForeground(Color.RED);
			}
		}
	}

	/**
	 * This method is used for most clicking
	 * @param arg0 the event of the mouse
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method is used for most clicking
	 * @param arg0 the event of the mouse
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method is used for most clicking
	 * @param arg0 the event of the mouse
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method is used for mouse clicking
	 * @param arg0 the event of the mouse
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This is the method used to track the keyboard button in order to fire the gun, toggle moving, and changing hunter's direction
	 * @param e is the KeyEvent to record the action
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (timer > 0){
			if (e.getKeyCode() == KeyEvent.VK_ENTER){
				hunter.toggleMoving();
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT){
				hunter.spinCCW();
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT){
				hunter.spinCW();
			}
			if (e.getKeyCode() == KeyEvent.VK_A){
				hunter.spinCCW();
			}
			if (e.getKeyCode() == KeyEvent.VK_D){
				hunter.spinCW();
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE){
				if (bulletClip > 0){ // checking to see if reload is needed
					hunter.fireBullet();
					bulletCount++;
					play("bullet.wav");
					bulletClip--;
					bulletAccuracy = (double) killCount/ (double) bulletCount;
					if (bulletClip == 0){
						reloads.setForeground(Color.RED);
					}
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_R){
				bulletClip = 6;
				play("shellFall.wav");
				reloads.setForeground(Color.GREEN);
			}
		}
	}

	/**
	 * This method is used for key clicking
	 * @param arg0 the event of the mouse
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method is used for key clicking
	 * @param arg0 the event of the mouse
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * This method is used for key clicking
	 * @param arg0 the event of the mouse
	 */
	@Override
	public void run() {
		while (timer > 0){
			try{
				repaint();
				Thread.sleep(16);
			} catch(InterruptedException e){System.out.println("Error in the Label reading");}	
		}
	}
	
	/**
	 * This method is used to play any audio 
	 * @param filename the name of the audio that needs to be played
	 */
	public static void play(String filename){
		try {
			/*
			 * the audio name so that the system can retrieve the clip
			 */
			Clip clip = AudioSystem.getClip();
			clip.open( AudioSystem.getAudioInputStream(
			new File(filename)));
			clip.start();
		}catch(LineUnavailableException e){
			System.out.println("Audio Error");
		}catch(IOException e){
			System.out.println("File not found");
		}catch(UnsupportedAudioFileException e){
			System.out.println("Wrong file type");
		}
		}

}
