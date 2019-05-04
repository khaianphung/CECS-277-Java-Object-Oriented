import java.util.Random;

/**
 * @author Aingty Eung
 * This is the Sith class as a person
 */
public class SithLord extends Person implements hasForce{
	private String saberColor;
	private int [] damage = {0, 10, 10, 10, 20, 20, 20, 30, 35, 40, 50};
	Random rand = new Random();
	
	int i; // for random probability damage.
	
	public SithLord(String n, String q) {
		super(n, 100, "LightSaber", q);
		this.saberColor = "red";
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
		e.modifyHP(damage[i]);
		System.out.println(damage[i]+ " points of damage taken. ");
	}
	

}
