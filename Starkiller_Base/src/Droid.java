
public abstract class Droid extends Entity{
	private int numTasks; //amount of medpack
	/**
	 * 
	 * @param n is the name of the Droid
	 * @param h is the health point of the Droid
	 * @param t is the number of healthpack for the Droid
	 */
	public Droid(String n, int h, int t) {
		super(n, h);
		this.numTasks = t;
	}
	
	public int getNumTasks(){
		return numTasks;
	}
	
	public void useTask(){
		numTasks--;
	}

}
