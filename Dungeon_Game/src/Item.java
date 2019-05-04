
public class Item {
	private String name;
	private int goldValue;
	
	public Item(String n, int v)
	{
		this.name = n;
		this.goldValue = v;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getValue()
	{
		return this.goldValue;
	}
}
