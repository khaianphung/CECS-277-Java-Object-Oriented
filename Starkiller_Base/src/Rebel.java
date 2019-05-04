import java.util.*;
public class Rebel extends Person implements Healable{
	private int[] damage = {0, 10, 10, 15, 20, 25, 20, 20, 30, 35, 30};
	
	Random rand = new Random();
	private int i;
	
	/**
	 * @param n is the name of the Rebel
	 * @param q is the battle cry of the Rebel
	 */
	public Rebel(String n, String q) {
		super(n, 50, "Blaster Pistol", q);
		// TODO Auto-generated constructor stub
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

	@Override
	public void heal(int hp) {
		int pp = hp * (-1);
		modifyHP(pp);
		System.out.println(hp+" points of health was restored to the target.");
	}

}
