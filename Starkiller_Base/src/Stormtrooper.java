import java.util.*;
public class Stormtrooper extends Person{
	private int[] damage = {0, 10, 10, 15, 20, 25, 20, 20, 30, 35, 30}; 
	
	Random rand = new Random();
	private int i;
	/**
	 * @param n is the name of the Stormtrooper
	 * @param q is the battle cry of the Trooper
	 */
	public Stormtrooper(String n, String q) {
		super(n, 50, "Blaster Rifle", q);
	}

	@Override
	public void attack(Entity e) {
		i = rand.nextInt(10)+1;
		e.modifyHP(damage[i]);
		System.out.println(damage[i]+ " points of damage taken. ");
	}

	@Override
	public void doTask(Entity e) {
		attack(e);
		System.out.print(super.getName()+" Cheered, \"");
		sayCatchPhrase();
		System.out.println("\"");
		
	}

}
