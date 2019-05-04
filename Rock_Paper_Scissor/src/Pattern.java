import java.io.Serializable;

/**
 * 
 * @author Aingty Eung
 * @pattern is the 3, 4, or 5 length strings of the user pattern
 */
public class Pattern implements Serializable{
	private String pattern;
	
	public Pattern(String p){
		this.pattern = p; 
	}
	
	public String getPattern(){
		return this.pattern;
	}
	
	/**
	 * the override method for the Pattern class in order to get the hashcode of the string pattern
	 */
	@Override 
	public int hashCode(){
		return this.pattern.hashCode();
		
	}
	
	/**
	 * @param o is the object being passed in in order to compare with the inclusive value
	 * @return a boolean value of whether the object is in order or out of order.
	 */
	@Override
	public boolean equals(Object o){
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Pattern))
		{
			return false;
		}
		Pattern T = (Pattern) o;
		return T.equals(this);
	}
}
