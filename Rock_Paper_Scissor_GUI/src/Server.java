import java.net.*;
import java.io.*;

/**
 * This is the server class used to compute the guess of the computer in the game
 * @author Aingty
 */
public class Server extends Thread{
	/**
	 * sock is the port connection between sever and the client
	 */
	private static Socket sock;
	/**
	 * patLength is the pattern of which the server will check the client
	 */
	private static int patLength = 4;
	/**
	 * singleChar is used to assign the user's input so that the server can look for the pattern and makde prediction
	 */
	private static String singleChar;
	/**
	 * compThrow is used to sent the server's prediction back to the client
	 */
	private static String compThrow;
	/**
	 * patternContruct is the string of concatenated characters used to look at any pattern that the user makes
	 */
	private static ServerSocket server;
	/**
	 * patternConstruct is the string used to find the pattern of the user input
	 */
	private static String patternConstruct = "no";
	/**
	 * c is the computer used to create and store the pattern
	 */
	private static Computer c = null;
	/**
	 * out is used to write to the client 
	 */
	private static PrintStream out;
	/**
	 * Server creates server sockets and process input in the main
	 * @param args ..
	 */
	public static void main(String [] args) {
		
		/*
		 * creating the socket for the client with port number 1235
		 */
		try {
				server = new ServerSocket(1235);
				System.out.print("Waiting... ");
				sock = server.accept();
				System.out.println("Connected.");
				out = new PrintStream(sock.getOutputStream());
		}catch( IOException e ) { System.out.println(e);}
		File f = new File("data.dat");
		if(f.exists()){
			try{
				ObjectInputStream comp = new ObjectInputStream(new FileInputStream(f));
				c = (Computer) comp.readObject();
				comp.close();
			}catch(IOException e){System.out.println("NO FILE FOUND!");}catch(ClassNotFoundException e){System.out.println("Class no found");}
		}else{
			c = new Computer();
		}
		
		Thread t = new Thread(){
			public void run(){
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
					while(true) {
						singleChar = in.readLine();
						if (patternConstruct.equals("no"))
						{
							patternConstruct = singleChar;
						}
						else
						{
							patternConstruct = patternConstruct + singleChar;
						}
						if (patternConstruct.length()< patLength)
						{
							compThrow = c.makePrediction(patternConstruct);
						}
						else
						{
							compThrow = c.makePrediction(patternConstruct.substring(0, patLength-1));
						}
						if (patternConstruct.length() >= patLength)
						{
								c.storePattern(new Pattern(patternConstruct));
								patternConstruct = patternConstruct.substring(1);
						}
						if (singleChar.equalsIgnoreCase("q")){
							try{ObjectOutputStream out1 = new ObjectOutputStream(new FileOutputStream(f));
							out1.writeObject(c);
							out1.close();
							System.out.println("Client Disconnected, writing to file..");
							System.out.println("Server shutting down.");
							server.close();
							break;
						}catch(IOException e){System.out.println("No file to write!");}
							System.exit(0);
						}
						out.println(compThrow);
					}
				}catch( IOException e ) { System.out.println(e);}
				
			}
		};
		/*
		 * t 
		 */
		t.start();
	}
	
}