/**
 * 
 * @author Aingty Eung
 * This is the main program that acts as a Jukebox
 *
 */
import java.util.*;
import java.io.*;
public class Jukebox {

	public static void main(String[] args) {
		/**
		 * input is used here as a scanner for user's input
		 */
		Scanner input = new Scanner(System.in);
		/**
		 * tree is a HeapTree for the class song.
		 */
		HeapTree <Song> tree = new HeapTree <Song> ();
		/**
		 * keepGoing is used to loop the functionalities
		 */
		boolean keepGoing = true;
		/**
		 * response is used to get user's input for the functionalities
		 */
		int response;
		
		System.out.println("Thank you for choosing Jukebox Inc.");
		
		try{
			Scanner read = new Scanner(new File("playlist.txt"));
			while(read.hasNextLine()){
				/**
				 * arrayTemp is used to get the full song's detail from title to rating.
				 */
				String[] arrayTemp = read.nextLine().split(",");
				/**
				 * This is the construction of a new song from the arrayTemp and add it to the tree
				 */
				tree.addNode(new Song(arrayTemp[0], arrayTemp[1], arrayTemp[2], Integer.parseInt(arrayTemp[3])));
			}
			read.close();
			
		}catch(FileNotFoundException e){ System.out.println("File not Found!");}
		
		while (keepGoing){
			response = Functions.jukboxFunction();
			if (response == 1){
				/**
				 * This if statement is used to check if the tree is empty. In the case that every song is played.
				 */
				if (tree.isEmpty()){
					System.out.println("No more song to play. Just add or quit.");
				}else{
					System.out.println("Here are all the songs:");
					tree.printHeap();
					System.out.println("");
				}
			}
			else if (response == 2){
				if (tree.isEmpty()){
					System.out.println("No more song to play. Just add or quit.");
				}else{
					System.out.println("Currently, the song \""+ tree.getNodeAt(0).getTitle()+"\" is playing");
					System.out.println("");
				}
			}
			else if (response == 3){
				System.out.println("Please input the name of the new song.");
				/**
				 * name is the new title for the song 
				 */
				String name = input.nextLine();
				System.out.println("Please input the author of the new song.");
				/**
				 * author is the author for the new song
				 */
				String author = input.nextLine();
				System.out.println("Please input the album of the new song.");
				/**
				 * album is the name of the album for the new song
				 */
				String album = input.nextLine();
				/**
				 * rating is used to set as a rating for the new song
				 */
				int rating = Functions.newRating();
				/**
				 * s is the new song being created
				 */
				Song s = new Song(name, author, album, rating);
				tree.addNode(s);
				System.out.println("The song, \""+name+" by "+author+" in album "+album+" with rating: "+rating+"\", has been added.");
				System.out.println("Currently, the song \""+ tree.getNodeAt(0).getTitle()+"\" is playing");
				System.out.println("");
			}
			else if (response == 4){
				if (tree.isEmpty()){
					System.out.println("No more song to play. Just add or quit.");
				}else{
					System.out.println("Playing the next song, please wait.....");
					/**
					 * Thread.sleep is used here as an illusion that the jukebox is loading up the next songs. Paused for 3 seconds.
					 */
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					tree.removeMin();
					if (tree.isEmpty()){
						System.out.println("No more song to play. Just add or quit.");
					}else{
						System.out.println("Currently, the song \""+ tree.getNodeAt(0).getTitle()+"\" is playing");
						System.out.println("");
					}
				}
			}
			else if (response == 5){
				/**
				 * s1 is the root song in the jukebox
				 */
				Song s1 = tree.removeMin();
				if (tree.isEmpty()){
					System.out.println("No more song to play. Just add or quit.");
				}
				else{
					int rating = Functions.newRating();
					/**
					 * s2 is the next song to be played in the jukebox, so this was removed in order for the user to re-rate
					 */
					Song s2 = tree.removeMin();
					tree.addNode(s1);
					System.out.println("The next song, \""+s2.getTitle()+"\", has been re-rated from "+s2.getRating()+" to "+rating+".");
					tree.addNode(new Song(s2.getTitle(), s2.getArtist(), s2.getAlbum(), rating));
				}
			}
			else if (response == 6){
				System.out.println("Jukebox is powering down...");
				/**
				 * Thread.sleep is used here as an illusion that the jukebox is shutting down. Paused for 2 seconds.
				 */
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("  GoodBye! ");
				keepGoing = false;
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}	
}
