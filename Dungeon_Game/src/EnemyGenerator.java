import java.util.*;
import java.io.*;

public class EnemyGenerator {
	private List<Enemy> enemyList = new ArrayList<Enemy>(); //Use for all the lines on the file
	private String l;
	Random rand = new Random();
	private List<String> temp = new ArrayList<String>();
	private ItemGenerator item = new ItemGenerator();
	public EnemyGenerator()
	{
		
		try{
			Scanner read = new Scanner(new File("EnemyList.txt"));
			while(read.hasNextLine())
			{
				String l = read.nextLine();
				temp.add(l);
			}
			
			read.close();
		}catch(FileNotFoundException e){
			System.out.println("Enemy File was not found.");
		}
		
	}
	public Enemy generateEnemy(int level)
	{
		for (int gen = 0; gen<temp.size(); gen++)
		{
			String[] Array = temp.get(gen).split(",");
			Enemy e = new Enemy(Array[0], Array[1], Integer.parseInt(Array[2]), level, 10, item.generateItem());
			enemyList.add(e);
			
		}
		int r = rand.nextInt(7);
		return enemyList.get(r);
	}
}
