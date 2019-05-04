
public abstract class Character {
	private String name;
	private String quip;
	private int level;
	private int hp;
	private int gold;
	
	public Character(String n, String q, int h, int l, int g)
	{
		this.name = n;
		this.quip = q;
		this.level = l;
		this.hp = h;
		this.gold = g;
	}
	public abstract void attack(Character c);
	
	public String getName()
	{
		return this.name;
	}
	
	public String getQuip()
	{
		return this.quip;
	}
	
	public int getHp()
	{
		return this.hp;
	}
	
	public int getLevel()
	{
		return this.level;
	}
	
	public int getGold()
	{
		return this.gold;
	}
	
	public void increaseLevel()
	{
		this.level += 1;
		System.out.println("Your hero has leveled up!! "+this.name+" is not level "+this.level);
	}
	
	public void heal(int h)
	{
		this.hp = h;
		System.out.println("Your hero was healed to the max.");
	}
	
	public void takeDamage(int h)
	{
		this.hp -= h;
	}
	
	public void collectGold(int g)
	{
		this.gold += g;
		System.out.println(g+" amount of golds were collected.");
	}
	
	public void display()
	{
		
	}
}
