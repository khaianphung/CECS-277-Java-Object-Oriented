/**
 * 
 * @author Aingty Eung
 *
 */
public abstract class Entity {
	private String name;
	private int hp;
	private boolean active;
	private String task;
	/**
	 * Constructor for Entity 
	 */
	public Entity(String n, int h){
		this.name = n;
		this.hp = h;
		if (this.hp <= 0){
			this.active = false;
		}else{
			this.active = true;
		}
	}
	/**
	 * 
	 * @param e
	 */
	public abstract void doTask(Entity e);
	/**
	 * returns the name
	 */
	public String getName(){
		return name;
	}
	
	public int getHP(){
		return hp;
	}
	
	public boolean getActive(){
		return active;
	}
	
	public void setActive(boolean a){
		this.active = a;
	}
	
	public void modifyHP(int h){
		this.hp = this.hp - h;
		if (this.hp <= 0){
			this.active = false;
		}
	}
	
	public String getTask(){
		return task;
	}
	
	
	public void setTask(String t){
		this.task = t;
	}
	
	
	
}
