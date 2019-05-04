import java.net.*;

import java.io.*;

/**
 * This is the client class used to construct a socket
 */
public class Client {
	public static void main(String [] args){

		/**
		 * s is a socket for the client to connect to the server
		 */
		Socket s;
		
		/*
		 * Constructing a new socket for the client to connect to the server in port (1235) with localhost ip
		 */
		try {
			s = new Socket("localhost", 1235);
			Frame start = new Frame(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}