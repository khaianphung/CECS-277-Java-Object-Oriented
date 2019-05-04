import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;

/**
 * @author Aingty
 * This is the Panel class used to draw contents to the frame which extends JPanel and implements KeyListener, MouseListener, MouseMotionListener, and Runnable
 */
public class Panel extends JPanel implements ActionListener, Runnable{

	/**
	 * score is used to display the player and computer score
	 * system is used to display who wins
	 */
	private JLabel score, system;
	
	/**
	 * log is used to set the activity
	 */
	private String log;
	
	/**
	 * timer is used to execute the while loop
	 */
	private int timer = 30;
	
	/**
	 * playerScore is used to keep track of player's score
	 */
	private int playerScore = 0;
	
	/**
	 * compScore is used to keep track of the computer's score
	 */
	private int compScore = 0;
	
	/**
	 * type is used to keep track of the user's input, 1 = rock, 2 = paper, 3 = scissor
	 */
	private int type = 0;
	
	/**
	 * type1 is similar to type, but used to draw a rectangle on the panel
	 */
	private int type1 = 0;
	
	/**
	 * compThrow is used to find the computer's prediction
	 */
	private String compThrow = "NONE";
	
	/**
	 * out is used to sent the client's input to the server
	 */
	private PrintStream out;
	
	/**
	 * in is used read the server's output
	 */
	private BufferedReader in;
	
	/**
	 * s is the socket for the client to connect to the server
	 */
	private Socket s;
	
	/**
	 * rock is the button used allow client to press on the image
	 * paper is the button used allow client to press on the image
	 * scissor is the button used allow client to press on the image
	 * quit is the button used to quit the program
	 */
	private JButton rock, paper, scissor, quit;
	
	/**
	 * img is the image of a hand's rock
	 * img1 is the image of a hand's paper
	 * img2 is the image of a hand's scissor
	 */
	private BufferedImage img, img1, img2;
	
	private boolean cont = true;
	/**
	 * This is the constructor for the panel in order for the user to interact with
	 * @param sock is the client's socket with a specific port number (1235)
	 */
	public Panel(Socket sock){
		this.setBackground(Color.BLACK);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		setFocusable(true);
		/*
		 * reading in the image of the rock, paper, and scissor
		 */
		try{
			img = ImageIO.read(new File("rock.png"));
			img1 = ImageIO.read(new File("paper.png"));
			img2 = ImageIO.read(new File("scissor.png"));
		}catch(IOException e){System.out.println("No image found!"); e.printStackTrace();} 
		
		/*
		 * This section is used to re-scale the image to the size of the button
		 */
		ImageIcon icon = new ImageIcon(img);
		Image imgg = icon.getImage().getScaledInstance(110, 110, 0);
		icon = new ImageIcon(imgg);
		ImageIcon icon1 = new ImageIcon(img1);
		Image imgg1 = icon1.getImage().getScaledInstance(110, 110, 0);
		icon1 = new ImageIcon(imgg1);
		ImageIcon icon2 = new ImageIcon(img2);
		Image imgg2 = icon2.getImage().getScaledInstance(110, 110, 0);
		icon2 = new ImageIcon(imgg2);
		/*
		 * This section is used to re-scale the image to the size of the button
		 */
		
		log = "Rock Paper or Scissor?";
		system = new JLabel();
		score = new JLabel("");
		system.setForeground(Color.YELLOW);
		score.setForeground(Color.GREEN);
		
		/*
		 * declaring the buttons on the panel
		 */
		rock = new JButton(icon);
		paper = new JButton(icon1);
		scissor = new JButton(icon2);
		quit = new JButton("Quit");
		
		/*
		 * setting the background color for the buttons
		 */
		rock.setBackground(Color.BLACK);
		paper.setBackground(Color.BLACK);
		scissor.setBackground(Color.black);
		
		/*
		 * allowing the user to make click on the buttons
		 */
		rock.addActionListener(this);
		paper.addActionListener(this);
		scissor.addActionListener(this);
		quit.addActionListener(this);
		
		this.add(system);
		this.add(paper);
		this.add(scissor);
		this.add(rock);
		this.add(quit);
		this.add(score);
		
		
		
		try{
			s = sock;
			//send message to server
			out = new PrintStream(s.getOutputStream());
			//get message from server
			in = new BufferedReader(new InputStreamReader (s.getInputStream()));
		}catch(IOException e){System.out.println(e.getMessage());}
		
		Thread p = new Thread(){
			public void run(){
				while(true){
					try{
						system.setText(getActivity());
						score.setText("Computer: "+compScore+" || You: "+playerScore);
						Thread.sleep(100);
				} catch(InterruptedException e){System.out.println("Error in the Label reading");}	
				}
			}
		};
		p.start();
		Thread t = new Thread(){
			public void run(){
				while(cont){
					try {
						compThrow = in.readLine();
					} catch (IOException e) {e.printStackTrace();}
				}
					
			}
		};
		t.start();
		
	}
	
	/**
	 * This is the method used to draw images to the frame.
	 * @param g is the graphical tools for the panel to draw to frame
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		checkWhoWins();
		/*
		 * Painting the rectangle of a computer's prediction behind the image
		 */
		if (compThrow.equalsIgnoreCase("r")){
			g.setColor(Color.RED);
			g.fillRect(25, 225, 160, 160);
		}
		if (compThrow.equalsIgnoreCase("p")){
			g.setColor(Color.RED);
			g.fillRect(225, 225, 160, 160);
		}
		if (compThrow.equalsIgnoreCase("s")){
			g.setColor(Color.RED);
			g.fillRect(425, 225, 160, 160);
		}
		
		/*
		 * Painting the rectangle of a user's input behind the image
		 */
		if(type1 == 1){
			g.setColor(Color.GREEN);
			g.fillRect(35, 235, 140, 140);
		}
		if(type1 == 2){
			g.setColor(Color.GREEN);
			g.fillRect(235, 235, 140, 140);
		}
		if(type1 == 3){
			g.setColor(Color.GREEN);
			g.fillRect(435, 235, 140, 140);
		}
		
		/*
		 * binding the location of the image and jlabel onto the panel
		 */
		system.setBounds(200, 0, 200, 30);
		score.setBounds(0, 0, 400, 30);
		rock.setBounds(50, 250, 110, 110);
		paper.setBounds(250, 250, 110, 110);
		scissor.setBounds(450, 250, 110, 110);
		quit.setBounds(50, 60, 100, 30);
	}
	
	/**
	 * This method is used to check the winner in that round and output that message to the frame
	 */
	public void checkWhoWins(){
		/*
		 * if statement used to check if the user makes an input
		 */
		if (!(type == 0)){
			if(compThrow.equalsIgnoreCase("p") && type == 1)
			{
				setActivity("Computer Wins!");
				compScore++;
				type = 0;
			}
			else if(type == 1 && compThrow.equalsIgnoreCase("s"))
			{
				setActivity("You won!");
				playerScore++;
				type = 0;
			}
			else if(type == 2 && compThrow.equalsIgnoreCase("r"))
			{
				setActivity("You won!");
				playerScore++;
				type = 0;
			}
			else if(type == 2 && compThrow.equalsIgnoreCase("s"))
			{
				setActivity("Computer Wins!");
				compScore++;
				type = 0;
			}
			else if(type == 3 && compThrow.equalsIgnoreCase("r"))
			{
				setActivity("Computer Wins!");
				compScore++;
				type = 0;
			}
			else if(type == 3 && compThrow.equalsIgnoreCase("p"))
			{
				setActivity("You won!");
				playerScore++;
				type = 0;
			}
			else
			{
				setActivity("It is a Tie!");
				type = 0;
			}
		}
//		try{
//			Thread.sleep(1000);
//		} catch(InterruptedException e){System.out.println("Error in the Label reading");}	
	}
	
	
	/**
	 * This method is used to set the log of the game
	 * @param e is the string used to type out what the activity of the game is.
	 */
	public void setActivity(String e){
		this.log = e;
	}
	
	/**
	 * This method is used to set the message of the game
	 * @return the string messages
	 */
	public String getActivity(){
		return this.log;
	}
	

	 

	 /**
	  * This is the overridden method is used to repaint the panel 
	  */
	 @Override
	 public void run() {
		 while (timer > 0){
				try{
					Thread.sleep(50);
					repaint();
				} catch(InterruptedException e){System.out.println("Error in the Label reading");}	
			}
	 }
	 
	 /**
	  * This method is used to play any audio 
	  * @param filename the name of the audio that needs to be played
	  */
	 public static void play(String filename){
	 
	 }

	 /**
	  * This overridden method is used to record the clicking of the user on the buttons
	  * @param e is the clicking event from the user
	  */
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * This if is when the user click on rock image button
		 */
		if (e.getSource() == rock){	
			out.println("r");
			type = 1;
			type1 = 1;
		}
		
		/*
		 * This if is when the user click on paper image button
		 */
		if (e.getSource() == paper){
			out.println("p");
			type = 2;
			type1 = 2;
		}
		
		/*
		 * This if is when the user click on scissor image button
		 */
		if (e.getSource() == scissor){
			out.println("s");
			type = 3;
			type1 = 3;
		}
		
		/*
		 * This if is when the user click on quit button
		 */
		if (e.getSource() == quit){
			setActivity("BYE!!!");
			out.println("q");
			try {
				s.close();
			} catch (IOException e1) {e1.printStackTrace();}
			cont = false;
			System.exit(0);
		}
		try{
			Thread.sleep(1000);
			repaint();
		} catch(InterruptedException p){System.out.println("Error in the Label reading");}	
		
	}
}
