
public class Medical extends Droid{

	public Medical(String n, int hp, int t) {
		super(n, hp, t);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doTask(Entity e) {
		System.out.println(super.getName()+" healed "+e.getName()+". ");
		useTask();
	}

}
