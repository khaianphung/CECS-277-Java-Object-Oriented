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
	 */
	public Frame(){
		setBounds(100, 100, 1100, 600);
		panel = new Panel();
		getContentPane().add(panel);
		Thread t = new Thread(panel);
		t.start();
	}
	
	/**
	 * This is the main where the game will be run after the Frame class calls it.
	 * @param args NOT really sure what this is.....
	 */
	public static void main(String [] args){
//		System.out.println("Welcome to the Hunting Trail game. The game will start in 3 seconds....");
//		for(int i = 2; i>0; i--){
//			try{
//				Thread.sleep(1000);
//				System.out.println(i);
//			} catch(InterruptedException e){
//				System.out.println("Error in the Label reading");
//			}
//		}
		/*
		 * f is the new frame constructed for the game
		 */
		Frame f = new Frame();
		f.setTitle("Hunting Trail");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
