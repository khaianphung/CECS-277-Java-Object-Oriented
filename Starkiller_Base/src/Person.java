/**
 * @author Aingty Eung
 * This is the person class as an entity
 */
public abstract class Person extends Entity{
	private String weapon;
	private String quip;
	
	public Person(String n, int h, String w, String q) {
		super(n, h);
		this.weapon = w;
		this.quip = q;
	}
	/**
	 * Say the catch phrase of the person.
	 */
	public void sayCatchPhrase(){
		System.out.print(this.quip);
	}
	
	public abstract void attack(Entity e);
	
	public String getWeapon(){
		return weapon;
	}
	
}
