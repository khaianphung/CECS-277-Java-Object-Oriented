import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Hero hero = null;
		String name;
		Point p;
		ItemGenerator n = new ItemGenerator();
		int floorLevel = 1;
		int direction = 0;
		int toDo;
		int toDo1;
		int toDo2;
		int startingHealth = 20;
		boolean playing = true;
		boolean test = true;
		boolean run = true;
		Level l = new Level();
		p = l.findStartLocation();
		File f = new File("hero.dat");
		if (f.exists())
		{
			try{
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
				System.out.println("It seems that you have a hero already. Welcome back!");
				hero = (Hero) in.readObject();
				System.out.println(hero.getName());
				in.close();
			}catch(IOException e){System.out.println("File not found.");} catch (ClassNotFoundException e) {System.out.println("Class not found");}
			
		}else{
			System.out.println("What is your name, Dungeoneer?");
			name = input.nextLine();
			hero = new Hero(name, "HA!", startingHealth, 1, 10, p);
			
		}
		while (playing)
		{
			l.generateLevel(floorLevel);
			p = l.findStartLocation();
			System.out.println(hero.getName()+" enters the Dungeon of ECS.");
			run = true;
			while (run)
			{
				System.out.println("Dungeon Map: ");
				l.displayMap(p);
				direction = Functions.checkInput();
				while (Functions.checkDirection(direction, hero))
				{
					System.out.println("Cannot move that direction");
					System.out.println("Dungeon Map: ");
					l.displayMap(p);
					direction = Functions.checkInput();		
				}
				if (direction == 2)
				{
					char d = hero.goSouth(l);
					if (d == 'm')
					{
						EnemyGenerator o = new EnemyGenerator();
						Enemy E = o.generateEnemy(hero.getLevel());
						System.out.println(hero.getName()+" has "+hero.getHp()+" health.");
						System.out.println(hero.getName()+" has encountered a "+E.getName()+". It has "+E.getHp()+" health.");
						toDo = Functions.checkInput1(hero);
						if(toDo == 1)// to run away
						{
							System.out.print("While running away, ");
							E.attack(hero);
							System.out.println("Which way do you want to run?");
							run = true;
						}
						else if (toDo == 2)
						{
							hero.attack(E);
							if(E.getHp()>0)
							{
								E.attack(hero);
							}
							while (toDo == 2)
							{
								if (E.getHp()<= 0)
								{
									boolean canPickUp = hero.pickUpItem(E.getItem());
									hero.collectGold(E.getGold());
									if (!canPickUp)
									{
										System.out.println("You cannot pick up anymore item. Thus the item was sold for "+E.getItem().getValue()+" gold.");
										hero.collectGold(E.getItem().getValue());
									}
									break;
								}
								if(hero.getHp()<=0)
								{
									System.out.println("Your HERO has been DEFEATED! GAME OVER!!");
									System.exit(0);
								}
								System.out.println(hero.getName()+" has "+hero.getHp()+" health.");
								System.out.println(hero.getName()+" has encountered a "+E.getName()+". It has "+E.getHp()+" health.");
								toDo = Functions.checkInput1(hero);
								hero.attack(E);
								if(E.getHp()>0)
								{
									E.attack(hero);
								}
							}
						}else if (toDo == 3)
						{
							hero.heal(startingHealth*hero.getLevel());
							int index = hero.getItemIndex(hero.getItem(), 25);
							hero.removeItem(index);
							while (toDo == 3)
							{
								System.out.println(hero.getName()+" has "+hero.getHp()+" health.");
								System.out.println(hero.getName()+" has encountered a "+E.getName()+". It has "+E.getHp()+" health.");
								toDo = Functions.checkInput1(hero);
							}
						}
					}else if (d == 'i')
					{
						Item m = n.generateItem();
						System.out.println(hero.getName()+" found a treasure: "+m.getName());
						toDo1 = Functions.checkInput2(hero);
						if (toDo1 == 1)
						{
							hero.collectGold(m.getValue());
						}
						else if (toDo1 == 2)
						{
							hero.pickUpItem(m);
						}
					}else if (d == 's')
					{
						toDo2 = Functions.checkInput3();
						if (toDo2 == 1)
						{
							for (int w = 0; w<=hero.getItem().size();w++)
							{
								while(hero.getItem().get(w).getValue()==25)
								{
									w++;
								}
								hero.collectGold(hero.getItem().get(w).getValue());
								hero.removeItem(w);
							}
						}
					}else if (d == 'f')
					{
						System.out.println("Congratulations! You found the exit.");
						hero.increaseLevel();
						hero.heal(startingHealth*hero.getLevel());
						System.out.println(hero.getName()+" now has "+hero.getHp()+" health.");
						System.out.println("Saving Progress......");
						floorLevel++;
						try{
							ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
							out.writeObject(hero);
							out.close();
						}catch(IOException e){ System.out.println("Can't Save For Some Reason.");}
						run = false;
					}
				}
				if (direction == 8)
				{
					char d = hero.goNorth(l);
					if (d == 'm')
					{
						EnemyGenerator o = new EnemyGenerator();
						Enemy E = o.generateEnemy(hero.getLevel());
						System.out.println(hero.getName()+" has "+hero.getHp()+" health.");
						System.out.println(hero.getName()+" has encountered a "+E.getName()+". It has "+E.getHp()+" health.");
						toDo = Functions.checkInput1(hero);
						if(toDo == 1)// to run away
						{
							System.out.print("While running away, ");
							E.attack(hero);
							System.out.println("Which way do you want to run?");
							run = true;
						}
						else if (toDo == 2)
						{
							hero.attack(E);
							if(E.getHp()>0)
							{
								E.attack(hero);
							}
							while (toDo == 2)
							{
								if (E.getHp()<= 0)
								{
									boolean canPickUp = hero.pickUpItem(E.getItem());
									hero.collectGold(E.getGold());
									if (canPickUp == false)
									{
										System.out.println("You cannot pick up anymore item. Thus the item was sold for "+E.getItem().getValue()+" gold.");
										hero.collectGold(E.getItem().getValue());
									}
									break;
								}
								if(hero.getHp()<=0)
								{
									System.out.println("Your HERO has been DEFEATED! GAME OVER!!");
									System.exit(0);
								}
								System.out.println(hero.getName()+" has "+hero.getHp()+" health.");
								System.out.println(hero.getName()+" has encountered a "+E.getName()+". It has "+E.getHp()+" health.");
								toDo = Functions.checkInput1(hero);
								hero.attack(E);
								if(E.getHp()>0)
								{
									E.attack(hero);
								}
							}
						}else if (toDo == 3)
						{
							hero.heal(startingHealth*hero.getLevel());
							int index = hero.getItemIndex(hero.getItem(), 25);
							hero.removeItem(index);
							while (toDo == 3)
							{
								System.out.println(hero.getName()+" has "+hero.getHp()+" health.");
								System.out.println(hero.getName()+" has encountered a "+E.getName()+". It has "+E.getHp()+" health.");
								toDo = Functions.checkInput1(hero);
							}
						}
					}else if (d == 'i')
					{
						Item m = n.generateItem();
						System.out.println(hero.getName()+" found a treasure: "+m.getName());
						toDo1 = Functions.checkInput2(hero);
						if (toDo1 == 1)
						{
							hero.collectGold(m.getValue());
						}
						else
						{
							hero.pickUpItem(m);
						}
					}else if (d == 's')
					{
						toDo2 = Functions.checkInput3();
						if (toDo2 == 1)
						{
							for (int w = 0; w<=hero.getItem().size();w++)
							{
								while(hero.getItem().get(w).getValue()==25)
								{
									w++;
								}
								hero.collectGold(hero.getItem().get(w).getValue());
								hero.removeItem(w);
							}
						}
					}else if (d == 'f')
					{
						System.out.println("Congratulations! You found the exit.");
						hero.increaseLevel();
						hero.heal(startingHealth*hero.getLevel());
						System.out.println(hero.getName()+" now has "+hero.getHp()+" health.");
						System.out.println("Saving Progress......");
						floorLevel++;
						try{
							ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
							out.writeObject(hero);
							out.close();
						}catch(IOException e){ System.out.println("Can't Save For Some Reason.");}
						run = false;
					}
				}
				if (direction == 6)
				{
					char d = hero.goEast(l);
					if (d == 'm')
					{
						EnemyGenerator o = new EnemyGenerator();
						Enemy E = o.generateEnemy(hero.getLevel());
						System.out.println(hero.getName()+" has "+hero.getHp()+" health.");
						System.out.println(hero.getName()+" has encountered a "+E.getName()+". It has "+E.getHp()+" health.");
						toDo = Functions.checkInput1(hero);
						if(toDo == 1)// to run away
						{
							System.out.print("While running away, ");
							E.attack(hero);
							System.out.println("Which way do you want to run?");
							run = true;
						}
						else if (toDo == 2)
						{
							hero.attack(E);
							if(E.getHp()>0)
							{
								E.attack(hero);
							}
							while (toDo == 2)
							{
								if (E.getHp()<= 0)
								{
									boolean canPickUp = hero.pickUpItem(E.getItem());
									hero.collectGold(E.getGold());
									if (canPickUp == false)
									{
										System.out.println("You cannot pick up anymore item. Thus the item was sold for "+E.getItem().getValue()+" gold.");
										hero.collectGold(E.getItem().getValue());
									}
									break;
								}
								if(hero.getHp()<=0)
								{
									System.out.println("Your HERO has been DEFEATED! GAME OVER!!");
									System.exit(0);
								}
								System.out.println(hero.getName()+" has "+hero.getHp()+" health.");
								System.out.println(hero.getName()+" has encountered a "+E.getName()+". It has "+E.getHp()+" health.");
								toDo = Functions.checkInput1(hero);
								hero.attack(E);
								if(E.getHp()>0)
								{
									E.attack(hero);
								}
							}
						}else if (toDo == 3)
						{
							hero.heal(startingHealth*hero.getLevel());
							int index = hero.getItemIndex(hero.getItem(), 25);
							hero.removeItem(index);
							while (toDo == 3)
							{
								System.out.println(hero.getName()+" has "+hero.getHp()+" health.");
								System.out.println(hero.getName()+" has encountered a "+E.getName()+". It has "+E.getHp()+" health.");
								toDo = Functions.checkInput1(hero);
							}
						}
					}else if (d == 'i')
					{
						Item m = n.generateItem();
						System.out.println(hero.getName()+" found a treasure: "+m.getName());
						toDo1 = Functions.checkInput2(hero);
						if (toDo1 == 1)
						{
							hero.collectGold(m.getValue());
						}
						else
						{
							hero.pickUpItem(m);
						}
					}else if (d == 's')
					{
						toDo2 = Functions.checkInput3();
						if (toDo2 == 1)
						{
							for (int w = 0; w<=hero.getItem().size();w++)
							{
								while(hero.getItem().get(w).getValue()==25)
								{
									w++;
								}
								hero.collectGold(hero.getItem().get(w).getValue());
								hero.removeItem(w);
							}
						}
					}else if (d == 'f')
					{
						System.out.println("Congratulations! You found the exit.");
						hero.increaseLevel();
						hero.heal(startingHealth*hero.getLevel());
						System.out.println(hero.getName()+" now has "+hero.getHp()+" health.");
						System.out.println("Saving Progress......");
						floorLevel++;
						try{
							ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
							out.writeObject(hero);
							out.close();
						}catch(IOException e){ System.out.println("Can't Save For Some Reason.");}
						run = false;
					}
				}
				if(direction == 4)
				{
					char d = hero.goWest(l);
					if (d == 'm')
					{
						EnemyGenerator o = new EnemyGenerator();
						Enemy E = o.generateEnemy(hero.getLevel());
						System.out.println(hero.getName()+" has "+hero.getHp()+" health.");
						System.out.println(hero.getName()+" has encountered a "+E.getName()+". It has "+E.getHp()+" health.");
						toDo = Functions.checkInput1(hero);
						if(toDo == 1)// to run away
						{
							System.out.print("While running away, ");
							E.attack(hero);
							System.out.println("Which way do you want to run?");
							run = true;
						}
						else if (toDo == 2)
						{
							hero.attack(E);
							if(E.getHp()>0)
							{
								E.attack(hero);
							}
							while (toDo == 2)
							{
								if (E.getHp()<= 0)
								{
									boolean canPickUp = hero.pickUpItem(E.getItem());
									hero.collectGold(E.getGold());
									if (canPickUp == false)
									{
										System.out.println("You cannot pick up anymore item. Thus the item was sold for "+E.getItem().getValue()+" gold.");
										hero.collectGold(E.getItem().getValue());
									}
									break;
								}
								if(hero.getHp()<=0)
								{
									System.out.println("Your HERO has been DEFEATED! GAME OVER!!");
									System.exit(0);
								}
								System.out.println(hero.getName()+" has "+hero.getHp()+" health.");
								System.out.println(hero.getName()+" has encountered a "+E.getName()+". It has "+E.getHp()+" health.");
								toDo = Functions.checkInput1(hero);
								hero.attack(E);
								if(E.getHp()>0)
								{
									E.attack(hero);
								}
							}
						}else if (toDo == 3)
						{
							hero.heal(startingHealth*hero.getLevel());
							int index = hero.getItemIndex(hero.getItem(), 25);
							hero.removeItem(index);
							while (toDo == 3)
							{
								System.out.println(hero.getName()+" has "+hero.getHp()+" health.");
								System.out.println(hero.getName()+" has encountered a "+E.getName()+". It has "+E.getHp()+" health.");
								toDo = Functions.checkInput1(hero);
							}
						}
					}else if (d == 'i')
					{
						Item m = n.generateItem();
						System.out.println(hero.getName()+" found a treasure: "+m.getName());
						toDo1 = Functions.checkInput2(hero);
						if (toDo1 == 1)
						{
							hero.collectGold(m.getValue());
						}
						else
						{
							hero.pickUpItem(m);
						}
					}else if (d == 's')
					{
						toDo2 = Functions.checkInput3();
						if (toDo2 == 1)
						{
							for (int w = 0; w<=hero.getItem().size();w++)
							{
								while(hero.getItem().get(w).getValue()==25)
								{
									w++;
								}
								hero.collectGold(hero.getItem().get(w).getValue());
								hero.removeItem(w);
							}
						}
					}else if (d == 'f')
					{
						System.out.println("Congratulations! You found the exit.");
						hero.increaseLevel();
						hero.heal(startingHealth*hero.getLevel());
						System.out.println(hero.getName()+" now has "+hero.getHp()+" health.");
						System.out.println("Saving Progress......");
						floorLevel++;
						try{
							ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
							out.writeObject(hero);
							out.close();
						}catch(IOException e){ System.out.println("Can't Save For Some Reason.");}
						run = false;
					}
				}
			}
			if (floorLevel == 4)
			{
				System.out.println("You have completed this game, ALIVE!! Thank you for playing. See you next time, "+hero.getName()+".");
				System.exit(0);
			}
		}
		
	}

}
