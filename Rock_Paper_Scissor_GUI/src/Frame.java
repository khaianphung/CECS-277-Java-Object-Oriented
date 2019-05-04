import java.net.Socket;

import javax.swing.*;

/**
 * @author Aingty
 * This class is used to contruct the background for the game.
 */
public class Frame extends JFrame{
 /*
  * panel is the Java Panel for the game
  */
 private Panel panel;
 
 /**
  * This is the constructor for the Frame class in order to out put a GUI
  * @param s is the socket passed from the client
  */
 public Frame(Socket s){
  setBounds(600, 300, 600, 600);
  /*
   * panel is used to construct a new panel to output to the frame
   */
  panel = new Panel(s);
  getContentPane().add(panel);
  setTitle("Rock Paper Scissior Game");
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setVisible(true);
  setResizable(false);
  Thread t = new Thread(panel);
  t.start();
 }
 
 /**
  * This is the main where the game will be run after the Frame class calls it.
  * @param args NOT really sure what this is.....
  */
 public static void main(String [] args){
  
 }
}
