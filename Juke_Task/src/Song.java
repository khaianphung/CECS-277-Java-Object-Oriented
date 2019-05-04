/**
 * @author Aingty Eung
 * This is the song class for the jukebox
 */
public class Song implements Comparable<Song>{
	/**
	 * @title is the name of the song
	 * @artist is the author of the song
	 * @album is the album
	 * @rating is the user's rating of the song from 1 to 5
	 */
	private String title;
	private String artist;
	private String album;
	private int rating;
	
	/**
	 * CONSTRUCTOR For the class SONG
	 * @param t is the title of the song
	 * @param ar is the artist of the song
	 * @param al is the album of the song
	 * @param r is the rating of the song
	 */
	public Song(String t, String ar, String al, int r){
		this.title = t;
		this.artist = ar;
		this.album = al;
		this.rating = r;
	}
	
	/**
	 * This method will return the artist of the song
	 * @return artist of song
	 */
	public String getArtist(){
		return this.artist;
	}
	
	/**
	 * This method will return the album of the song
	 * @return album of the song
	 */
	public String getAlbum(){
		return this.album;
	}
	
	/**
	 * This method returns the rating of the song
	 * @param s is the song that the computer wants the rating of
	 * @return the rating of that song
	 */
	public int getRating(){
		return this.rating; 
	}
	
	/**
	 * This method returns the title of the song for the compareTo method
	 * @param s is the song to get the title
	 * @return the title of that song
	 */
	public String getTitle(){
		return this.title;
	}
	
	/**
	 * @param Song s is the song passed to compare. 
	 * @return the integer value of compareTo --> -1 = < and +1 = >
	 */
	@Override
	public int compareTo(Song s) {
		/**
		 * @value is the integer value to be returned
		 * @var is the value of the compared song's title
		 */
		if (this.rating == s.getRating()){
			int var = (title.compareTo(s.getTitle()));
			return var * -1;
		}else{
			return this.rating - s.getRating();
		}
	}
	
	/**
	 * This method will return the song's full name
	 * @return song to be able to print the song's full name
	 */
	@Override
	public String toString(){
		/**
		 * @song is the string that has the song's name, artist, album, and rating
		 */
		String song = this.title + ", " + this.artist + ", " + this.album + ", " + this.rating;
		return song;
		
	}
}
