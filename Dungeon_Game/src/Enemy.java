import java.util.Random;

public class Enemy extends Character{
	private Item item;
	private int[] damage = {0, 1, 2, 3, 1, 1, 2, 1, 2, 2, 2}; 
	Random rand = new Random();
	public Enemy(String n, String q, int h, int l, int g, Item i) {
		super(n, q, h*l, l, g);
		this.item = i;
	}

	public Item getItem()
	{
		return this.item;
	}
	@Override
	public void attack(Character c) {
		int gen = rand.nextInt(10)+1;
		c.takeDamage(damage[gen]);
		System.out.println(super.getName()+" hits the "+c.getName()+" for "+damage[gen]+" damage.");
		if (c.getHp()<= 0)
		{
			System.out.println(super.getName()+" has defeated "+c.getName());
		}
	}

}
