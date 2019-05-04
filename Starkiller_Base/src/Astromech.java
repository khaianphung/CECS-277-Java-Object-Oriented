
public class Astromech extends Droid{
	/**
	 * @param n is the name of the Astromech
	 * @param t is the number of tasks for the Astromech
	 */
	public Astromech(String n, int hp, int t) {
		super(n, hp, t);
	}

	@Override
	public void doTask(Entity e) {
		getNumTasks();
	}
	
}
