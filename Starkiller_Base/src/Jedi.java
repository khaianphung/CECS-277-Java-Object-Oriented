import java.util.*;
/**
 * 
 * @author Aingty
 * This is the Jedi class extends to person and has the force and healable interface
 */
public class Jedi extends Person implements hasForce, Healable{
	private int [] damage = {0, 10, 10, 10, 20, 20, 20, 30, 35, 40, 50};
	private int [] damageForce = {0, 45, 40, 40, 40, 55, 60, 70, 70, 80, 99};
	private String saberColor;
	
	Random rand = new Random();
	
	int i; // for random probability damage.
	
	/**
	 * @param n is the name of the Jedi
	 * @param q is the battle cry of the Jedi
	 * @param c is the saber color of the Jedi
	 */
	public Jedi(String n, String q, String c) {
		super(n, 100, "LightSaber", q);
		this.saberColor = c;
	}
	/**
	 * 
	 * @return the color of the light saber.
	 */
	public String getSaberColor() {
		return this.saberColor;
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
		System.out.print(super.getName()+" screamed \"");
		sayCatchPhrase();
		System.out.println("!!\"");
	}

	@Override
	public void attackForce(Entity e) {
		i = rand.nextInt(10)+1;
		e.modifyHP(damageForce[i]);
		System.out.println(damageForce[i]+ " points of damage taken. ");
	}

	@Override
	public void heal(int hp) {
		int pp = hp * (-1);
		modifyHP(pp);
		System.out.println(hp+" points of health was restored to the target.");
	}
}
