import java.util.*;
import java.io.*;
public class ItemGenerator {
	private List<Item> itemList = new ArrayList<Item>();
	private List<String> temp = new ArrayList<String>();
	Random rand = new Random();
	private String l;
	
	public ItemGenerator(){
		try{
			Scanner read = new Scanner(new File("ItemList.txt"));
			while(read.hasNextLine())
			{
				String l = read.nextLine();
				temp.add(l);
			}
			
			read.close();
		}catch(FileNotFoundException e){
			System.out.println("Item File was not found.");
		}
	}
	public Item generateItem()
	{
		for (int gen = 0; gen<temp.size(); gen++)
		{
			String[] Array = temp.get(gen).split(",");
			Item e = new Item(Array[0], Integer.parseInt(Array[1]));
			itemList.add(e);
			
		}
		int r = rand.nextInt(7);
		return itemList.get(r);
	}
}
