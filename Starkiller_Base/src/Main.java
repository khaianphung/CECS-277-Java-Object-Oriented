import java.util.*;
public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);
		Random rand = new Random();
		
		String JediName;
		String response;
		
		int Mission;
		int i;
		int f; //use to determine the force attack
		int toDo = 1; // pick what type of move to make
		int toAttack; //pick who to attack
		int toHeal;
		
		boolean playing = true; //At the end, will ask if want to continue
		boolean keepgoing = true;
		System.out.println("Welcome to the Star Wars Galaxy!");
		System.out.println("You are about to embark on a time long long ago in a galaxy far far away....");
		System.out.println("Please choose a name for your Jedi's name:");
		JediName = input.nextLine();
		
		while (keepgoing)
		{
			f = 3; //reset force attack
			System.out.println("");
			System.out.println("Please choose a Mission:");
			System.out.println("    1. Hunt the Sith Lord");
			System.out.println("    2. Capture Starkiller Base");
			Mission = input.nextInt();
			playing = true; 
			while (Mission!=1 && Mission!=2)
			{
				System.out.println("The number corresponding to the Mission is invalid Please choose a Mission:");
				System.out.println("    1. Hunt the Sith Lord");
				System.out.println("    2. Capture Starkiller Base");
				Mission = input.nextInt();
			}
			while (playing)
			{
				Person Sith = new SithLord("Kylo Ren", "I will Finish.... What you started.");
				Person Stormtrooper1 = new Stormtrooper("Stormtrooper  1", "Feeeel the BURN!");
				Person Stormtrooper2 = new Stormtrooper("Stormtrooper  2", "Holy Fk**! YEAHH!");
				Person Stormtrooper3 = new Stormtrooper("Stormtrooper  3", "I don't miss.");
				Person Stormtrooper4 = new Stormtrooper("Stormtrooper  4", "These are the Droids we are looking for!!");
				Person Stormtrooper5 = new Stormtrooper("Stormtrooper  5", "For the First Order!!!");
				Entity Door1 = new Door("Seal-Door #1"); 
				Entity Door2 = new Door("Seal-Door #2");
				Entity Computer = new Computer("Base Control Panel");
			
				Person Jedi = new Jedi(JediName, "Take that!", "Green");
				Person Rebel1 = new Rebel("RebelTrooper  1", "Straight on the Helmet!");
				Person Rebel2 = new Rebel("RebelTrooper  2", "Yeeee HAWWWW!!!");
				Person Rebel3 = new Rebel("RebelTrooper  3", "A Small Green guy once said, 'DO or DO NOT...' I forget the rest");
				Person Rebel4 = new Rebel("RebelTrooper  4", "That's gonna leave a burnt mark!");
				Person Rebel5 = new Rebel("RebelTrooper  5", "Cover Me! Reloading!!");
				Entity Healer = new Medical("BB-8", 30, 5);
			
				if (Mission == 1)
				{
					Entity[] FirstOrder = {null, Sith, Stormtrooper1, Stormtrooper2, Stormtrooper3, Stormtrooper4, Stormtrooper5}; //Bad Guys
					Entity[] Resistance = {null, Jedi, Rebel1, Rebel2, Rebel3, Rebel4, Rebel5, Healer}; //Good Guys
					System.out.println("You run across the Sith Lord, he has troops with him. ATTACK!!");
					System.out.println("");
					while ((toDo==1 || toDo==2 || toDo==3) && playing)
					{
						System.out.println("Good Guys");
						System.out.println("----------");
						for (i = 1; i<=Resistance.length - 1; i++)
						{
							while (Resistance[i].getActive() == false)
							{
								i++;
								if (i >Resistance.length - 1)
								{
									break;
								}
							}
							if (i >Resistance.length - 1)
							{
								break;
							}
							System.out.print(Resistance[i].getName());
							for (int k = Resistance[i].getName().length(); k <= 20; k++)
							{
								System.out.print(" ");
							}
							System.out.println(Resistance[i].getHP());
						}
						System.out.println(" ");
						System.out.println("Bad Guys");
						System.out.println("----------");
						for (i = 1; i<=FirstOrder.length - 1; i++)
						{
							while (FirstOrder[i].getActive() == false)
							{
								i++;
								if (i >FirstOrder.length - 1)
								{
									break;
								}
							}
							if (i >FirstOrder.length - 1)
							{
								break;
							}
							System.out.print(FirstOrder[i].getName());
							for (int k = FirstOrder[i].getName().length(); k <= 20; k++)
							{
								System.out.print(" ");
							}
							System.out.println(FirstOrder[i].getHP());
						}
						System.out.println("");
						System.out.println("What do you want to do?");
						System.out.println("  1. Attack with Lightsaber.");
						System.out.print("  2. Attack using the Force. ("+f+" left)");
						System.out.println("");
						System.out.println("  3. Have the droid to heal someone.");
						Medical m = (Medical) Resistance[7];
						int t = m.getNumTasks();
						System.out.println("             You have a total of "+t+" medpack.");
						toDo = input.nextInt();
						while ((toDo!=1 && toDo!=2 && toDo!=3) || (toDo==2 && f<=0) || (toDo==3 && t<=0))
						{
							System.out.println("The input you press is invalid.");
							System.out.println("What do you want to do?");
							System.out.println("  1. Attack with Lightsaber.");
							System.out.print("  2. Attack using the Force. ("+f+" left)");
							System.out.println("");
							System.out.println("  3. Have the droid to heal someone.");
							System.out.println("             You have a total of "+t+" medpack.");
							toDo = input.nextInt();
						}
						if (toDo==1)///////////////////////////////////////////////////////////////////////////////////////////////////////////////To DO 1
						{
							System.out.println("Choose someone to attack:");
							int j = 1;
							int y = 1; // use for marking K 
							int[] Kmarker = new int[10];
							for (int k = 1; k <= FirstOrder.length - 1; k++)
							{
								if (FirstOrder[k].getActive())
								{
									System.out.println(j+".  "+FirstOrder[k].getName());
									Kmarker[y] = k;
									y++;
									j++;
								
								}
							}
							toAttack = input.nextInt(); // GONNA NEED INPUT CHECK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
							while (toAttack > j-1 || toAttack <= 0)
							{
								System.out.println("The input value is invalid, please try again.");
								System.out.println("Choose someone to attack:");
								j = 1;
								y = 1; // use for marking K
								for (int k = 1; k <= FirstOrder.length - 1; k++)
								{
									if (FirstOrder[k].getActive())
									{
										System.out.println(j+".  "+FirstOrder[k].getName());
										Kmarker[y] = k;
										y++;
										j++;
									
									}
								}
								toAttack = input.nextInt();
							}
							for (int l = 1; l <= Resistance.length-3; l++)
							{
								while (Resistance[l].getActive() == false)
								{
									l++;
									if (l > Resistance.length-3)
									{
										break;
									}
								}
								if (l > Resistance.length-3)
								{
									break;
								}
								if (l == 1) //Special Jedi vs. FirstOrder CASE Making Jedi has a better chance to hit first order team.
								{
									if (toAttack == 1) //if SithLord is selected
									{
										int p = rand.nextInt(10)+1;
										if (p >= 5) // 50% chance of hitting
										{
											System.out.println(Resistance[l].getName()+" yelled, \"Hey "+FirstOrder[Kmarker[toAttack]].getName()+ "!!!\"");
											System.out.print(Resistance[l].getName()+" successfully swings his Green "+Jedi.getWeapon()+" at "+FirstOrder[Kmarker[toAttack]].getName()+". ");
											Resistance[l].doTask(FirstOrder[Kmarker[toAttack]]);
											if (FirstOrder[Kmarker[toAttack]].getActive() == false)
											{
												System.out.println(FirstOrder[Kmarker[toAttack]].getName()+" was destroyed!!.");
												System.out.println(Resistance[l].getName()+" shouted in excitment, \"You have failed!! The First Order is NO MORE!!\"");
												System.out.println("CONGRATULATION! You have DEFEATED THE FIRST ORDER!");
												System.out.println("      Would you like to continue? Y or N");
												response = input2.nextLine();
												while (!(response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n")))
												{
													System.out.println("Your input could not be read, play again? Y or N");
													response = input.nextLine();
												}
												if (response.equalsIgnoreCase("y"))
												{
													playing = false;
													break;
												}
												else
												{
													System.exit(0);
												}
											}
										}
										else
										{
											System.out.println(Resistance[l].getName()+" tried to hit "+FirstOrder[Kmarker[toAttack]].getName()+", but swings a missed.");
											int s = rand.nextInt(5)+1;// SAYING DIFFERENT THINGS!!!!!!!!!!!!!!!!!!
											System.out.println(Resistance[l].getName()+" then provoked "+FirstOrder[Kmarker[toAttack]].getName()+", \"Your Lightsaber looks lame!!\"");
										}
									}
									else
									{
										int p = rand.nextInt(10)+1;
										if (p > 3) //70% chance of hitting
										{
											System.out.print(Resistance[l].getName()+" successfully swings his Green "+Jedi.getWeapon()+" at "+FirstOrder[Kmarker[toAttack]].getName()+". ");
											Resistance[l].doTask(FirstOrder[Kmarker[toAttack]]);
											if (FirstOrder[Kmarker[toAttack]].getActive()==false)
											{
												System.out.println(FirstOrder[Kmarker[toAttack]].getName()+" was killed.");
											}
										}
										else
										{
											System.out.println(Resistance[l].getName()+" tried to throw his Green "+Jedi.getWeapon()+" at "+FirstOrder[Kmarker[toAttack]].getName()+", but the aim was off.");
											System.out.println(FirstOrder[Kmarker[toAttack]].getName()+" taunted "+Resistance[l].getName()+", \"Jedi Scum!!\"");
										}
									}
								}
								else
								{
									toAttack = rand.nextInt(6)+1;
									while (FirstOrder[toAttack].getActive() == false)
									{
										toAttack = rand.nextInt(6)+1;
									}
									int p = rand.nextInt(10)+1;
									if (toAttack == 1)
									{
										if (p <= 8)
										{
											System.out.println(Resistance[l].getName()+" tried to fire his Blaster Rifle at "+FirstOrder[toAttack].getName()+", but his shot was block by his Lightsaber.");
											System.out.println(FirstOrder[toAttack].getName()+" spoke confidently,\"Are we done?\"");
										}
										else
										{
											System.out.print(Resistance[l].getName()+" successfully blasted "+FirstOrder[toAttack].getName()+" with his Blaster Rifle. ");
											Resistance[l].doTask(FirstOrder[toAttack]);
											if (FirstOrder[1].getActive() == false)
											{
												System.out.println(FirstOrder[toAttack].getName()+" was killed.");
												System.out.println(FirstOrder[toAttack].getName()+" screamed,\"NOOOOOOOOOOOOO!!\"");
												System.out.println("CONGRATULATION! You have DEFEATED THE FIRST ORDER!");
												System.out.println("      Would you like to continue? Y or N");
												response = input2.nextLine();
												while (!(response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n")))
												{
													System.out.println("Your input could not be read, play again? Y or N");
													response = input.nextLine();
												}
												if (response.equalsIgnoreCase("y"))
												{
													playing = false;
													break;
												}
												else
												{
													System.exit(0);
												}
											}
										}
									}
									else if (p >= 5)
									{
										System.out.print(Resistance[l].getName()+" successfully blasted "+FirstOrder[toAttack].getName()+" with his Blaster Rifle. ");
										Resistance[l].doTask(FirstOrder[toAttack]);
										if (FirstOrder[toAttack].getActive() == false)
										{
											System.out.println(FirstOrder[toAttack].getName()+" was killed.");
										}
									}
									else 
									{
										System.out.println(Resistance[l].getName()+" failed to get his shot on. No damage was done to "+FirstOrder[toAttack].getName());
										
									}
								}
							}
							for (int l = 1; l <= FirstOrder.length-1; l++)// FOR EMPIRE TO HIT!
							{
								while (FirstOrder[l].getActive() == false)
								{
									System.out.println("Testing");
									l++;
									if (l > FirstOrder.length-1)
									{
										System.out.println("Testing");
										break;
									}
								}
								if (l > FirstOrder.length-1)
								{
									break;
								}
								int e = rand.nextInt(7)+1;
								while (FirstOrder[toAttack].getActive() == false)
								{
									e = rand.nextInt(7)+1;
								}
								int p = rand.nextInt(10)+1;
								if (l == 1)
								{
									if (p >= 3)
									{
										System.out.print(FirstOrder[l].getName()+" Striked "+Resistance[e].getName()+". ");
										FirstOrder[l].doTask(Resistance[e]);
										if (Resistance[e].getActive()==false)
										{
											System.out.println(Resistance[e].getName()+" was killed by "+FirstOrder[l].getName());
										}
										if (Resistance[1].getActive()==false)
										{
											System.out.println("Your Jedi, "+Resistance[1].getName()+", has been defeated. You have FAIL to defeat the Sith Lord. GAME OVER!!!");
											playing = false;
											break;
										}
									}
									else
									{
										System.out.print(FirstOrder[l].getName()+" force choked "+Resistance[1].getName()+" but his unstable personality failed him. ");
										System.out.println("No damage was done to "+Resistance[1].getName());
									}
								}
								else
								{
									if (p >= 6)
									{
										System.out.print(FirstOrder[l].getName()+" successfully blasted "+Resistance[e].getName()+" with his Blaster Rifle. ");
										FirstOrder[l].doTask(Resistance[e]);
										if (Resistance[e].getActive() == false)
										{
											System.out.println(Resistance[e].getName()+" was killed.");
										}
									}
									else 
									{
										System.out.println(FirstOrder[l].getName()+" failed to get his shot on. No damage was done to "+Resistance[e].getName());
										
									}
								}
							}
						}
						else if (toDo == 2)///////////////////////////////////////////////////////////////////////////////////////To DO 2
						{
							f--;
							System.out.println("Choose someone to attack:");
							int j = 1;
							int y = 1; // use for marking K 
							int[] Kmarker = new int[10];
							for (int k = 1; k <= FirstOrder.length - 1; k++)
							{
								if (FirstOrder[k].getActive())
								{
									System.out.println(j+".  "+FirstOrder[k].getName());
									Kmarker[y] = k;
									y++;
									j++;
								
								}
							}
							toAttack = input.nextInt(); // GONNA NEED INPUT CHECK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
							while (toAttack > j-1 || toAttack <= 0)
							{
								System.out.println("The input value is invalid, please try again.");
								System.out.println("Choose someone to attack:");
								j = 1;
								y = 1; // use for marking K
								for (int k = 1; k <= FirstOrder.length - 1; k++)
								{
									if (FirstOrder[k].getActive())
									{
										System.out.println(j+".  "+FirstOrder[k].getName());
										Kmarker[y] = k;
										y++;
										j++;
									
									}
								}
								toAttack = input.nextInt();
							}
							for (int l = 1; l <= Resistance.length-3; l++)
							{
								while (Resistance[l].getActive() == false)
								{
									l++;
									if (l > Resistance.length-3)
									{
										break;
									}
								}
								if (l > Resistance.length-3)
								{
									break;
								}
								if (l == 1) //Special Jedi vs. FirstOrder CASE Making Jedi has a better chance to hit first order team.
								{
									if (toAttack == 1) //if SithLord is selected
									{
										int p = rand.nextInt(10)+1;
										if (p >= 5) // 50% chance of hitting
										{
											System.out.println(Resistance[l].getName()+" yelled, \"Hey "+FirstOrder[Kmarker[toAttack]].getName()+ "!!!\"");
											System.out.print(Resistance[l].getName()+" successfully swings his Green "+Jedi.getWeapon()+" at "+FirstOrder[Kmarker[toAttack]].getName()+". ");
											((Jedi) Resistance[l]).attackForce(FirstOrder[Kmarker[toAttack]]);
											if (FirstOrder[Kmarker[toAttack]].getActive() == false)
											{
												System.out.println(FirstOrder[Kmarker[toAttack]].getName()+" was destroyed!!.");
												System.out.println(Resistance[l].getName()+" shouted in excitment, \"You have failed!! The First Order is NO MORE!!\"");
												System.out.println("CONGRATULATION! You have DEFEATED THE FIRST ORDER!");
												System.out.println("      Would you like to continue? Y or N");
												response = input2.nextLine();
												while (!(response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n")))
												{
													System.out.println("Your input could not be read, play again? Y or N");
													response = input.nextLine();
												}
												if (response.equalsIgnoreCase("y"))
												{
													playing = false;
													break;
												}
												else
												{
													System.exit(0);
												}
											}
										}
										else
										{
											System.out.println(Resistance[l].getName()+" tried to hit "+FirstOrder[Kmarker[toAttack]].getName()+", but swings a missed.");
											int s = rand.nextInt(5)+1;// SAYING DIFFERENT THINGS!!!!!!!!!!!!!!!!!!
											System.out.println(Resistance[l].getName()+" then provoked "+FirstOrder[Kmarker[toAttack]].getName()+", \"Your Lightsaber looks lame!!\"");
										}
									}
									else
									{
										int p = rand.nextInt(10)+1;
										if (p > 3) //70% chance of hitting
										{
											System.out.print(Resistance[l].getName()+" successfully swings his Green "+Jedi.getWeapon()+" at "+FirstOrder[Kmarker[toAttack]].getName()+". ");
											Resistance[l].doTask(FirstOrder[Kmarker[toAttack]]);
											if (FirstOrder[Kmarker[toAttack]].getActive()==false)
											{
												System.out.println(FirstOrder[Kmarker[toAttack]].getName()+" was killed.");
											}
										}
										else
										{
											System.out.println(Resistance[l].getName()+" tried to throw his Green "+Jedi.getWeapon()+" at "+FirstOrder[Kmarker[toAttack]].getName()+", but the aim was off.");
											System.out.println(FirstOrder[Kmarker[toAttack]].getName()+" taunted "+Resistance[l].getName()+", \"Jedi Scum!!\"");
										}
									}
								}
								else
								{
									toAttack = rand.nextInt(6)+1;
									while (FirstOrder[toAttack].getActive() == false)
									{
										toAttack = rand.nextInt(6)+1;
									}
									int p = rand.nextInt(10)+1;
									if (toAttack == 1)
									{
										if (p <= 8)
										{
											System.out.println(Resistance[l].getName()+" tried to fire his Blaster Rifle at "+FirstOrder[toAttack].getName()+", but his shot was block by his Lightsaber.");
											System.out.println(FirstOrder[toAttack].getName()+" spoke confidently,\"Are we done?\"");
										}
										else
										{
											System.out.print(Resistance[l].getName()+" successfully blasted "+FirstOrder[toAttack].getName()+" with his Blaster Rifle. ");
											Resistance[l].doTask(FirstOrder[toAttack]);
											if (FirstOrder[toAttack].getActive() == false)
											{
												System.out.println(FirstOrder[toAttack].getName()+" was killed.");
												System.out.println(FirstOrder[toAttack].getName()+" screamed,\"NOOOOOOOOOOOOO!!\"");
												System.out.println("CONGRATULATION! You have DEFEATED THE FIRST ORDER!");
												System.out.println("      Would you like to continue? Y or N");
												response = input2.nextLine();
												while (!(response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n")))
												{
													System.out.println("Your input could not be read, play again? Y or N");
													response = input.nextLine();
												}
												if (response.equalsIgnoreCase("y"))
												{
													playing = false;
													break;
												}
												else
												{
													System.exit(0);
												}
											}
										}
									}
									else if (p >= 5)
									{
										System.out.print(Resistance[l].getName()+" successfully blasted "+FirstOrder[toAttack].getName()+" with his Blaster Rifle. ");
										Resistance[l].doTask(FirstOrder[toAttack]);
										if (FirstOrder[toAttack].getActive() == false)
										{
											System.out.println(FirstOrder[toAttack].getName()+" was killed.");
										}
									}
									else 
									{
										System.out.println(Resistance[l].getName()+" failed to get his shot on. No damage was done to "+FirstOrder[toAttack].getName());
										
									}
								}
							}
							for (int l = 1; l <= FirstOrder.length-1; l++)// FOR EMPIRE TO HIT!
							{
								
								while (FirstOrder[l].getActive() == false)
								{
									System.out.println("Testing");
									l++;
									if (l > FirstOrder.length-1)
									{
										break;
									}
								}
								if (l > FirstOrder.length-1)
								{
									break;
								}
								int e = rand.nextInt(7)+1;
								while (FirstOrder[toAttack].getActive() == false)
								{
									e = rand.nextInt(7)+1;
								}
								int p = rand.nextInt(10)+1;
								if (l == 1)
								{
									if (p >= 3)
									{
										System.out.print(FirstOrder[l].getName()+" Striked "+Resistance[e].getName()+". ");
										FirstOrder[l].doTask(Resistance[e]);
										if (Resistance[e].getActive()==false)
										{
											System.out.println(Resistance[e].getName()+" was killed by "+FirstOrder[l].getName());
										}
										if (Resistance[1].getActive()==false)
										{
											System.out.println("Your Jedi, "+Resistance[1].getName()+", has been defeated. You have FAIL to defeat the Sith Lord. GAME OVER!!!");
											playing = false;
											break;
										}
									}
									else
									{
										System.out.print(FirstOrder[l].getName()+" force choked "+Resistance[1].getName()+" but his unstable personality failed him. ");
										System.out.println("No damage was done to "+Resistance[1].getName());
									}
								}
								else
								{
									if (p >= 6)
									{
										System.out.print(FirstOrder[l].getName()+" successfully blasted "+Resistance[e].getName()+" with his Blaster Rifle. ");
										FirstOrder[l].doTask(Resistance[e]);
										if (Resistance[e].getActive() == false)
										{
											System.out.println(Resistance[e].getName()+" was killed.");
										}
									}
									else 
									{
										System.out.println(FirstOrder[l].getName()+" failed to get his shot on. No damage was done to "+Resistance[e].getName());
										
									}
								}
							}
						}
						else if (toDo == 3)
						{
							System.out.println("Please choose a target to heal.");
							int j = 1;
							int y = 1; // use for marking K 
							int[] Kmarker = new int[10];
							for (int k = 1; k <= Resistance.length - 2; k++)
							{
								if (Resistance[k].getActive())
								{
									System.out.println(j+".  "+Resistance[k].getName());
									Kmarker[y] = k;
									y++;
									j++;
								}
							}
							toHeal = input.nextInt();
							m.doTask(Resistance[toHeal]);
							if (toHeal == 1)
							{
								((Jedi) Resistance[toHeal]).heal(25);
							}
							else
							{
								((Rebel) Resistance[toHeal]).heal(30);
							}
						}
					}
				}
				if (Mission == 2)
				{
					System.out.println("This mode is simple. Just progress through 2 stages to reach the final stage.");
					System.out.println("A team of Resistance Trooper, Jedi, and Droids have landed on StarKiller Base of Operation.");
					System.out.println("Reach the Control Panel, which is the main computer for this base. Destroy it and victory will be yours!");
					Entity[] FirstOrder = {null, Stormtrooper1, Door1, Stormtrooper2, Door2, Sith, Computer}; //Bad Guys
					Entity R2D2 = new Astromech("R2D2", 50, 3);
					Healer = new Medical("BB-8", 45, 5);
					Entity[] Resistance = {null, Jedi, Rebel1, R2D2, Healer}; //Good Guys
					System.out.println("");
					System.out.println("As your team runs across the base, you arrived at the first Seal-Door. Use R2D2 to open the door.");
					System.out.println("   HINT: R2D2 can only open the door if the guard is down. Keep R2D2 ALIVE!");
					toAttack = 1;
					int soldier = 2;
					int w = 1;
					while ((toDo==1 || toDo==2 || toDo==3) && playing)
					{
						System.out.println("Good Guys");
						System.out.println("----------");
						for (i = 1; i<=Resistance.length - 1; i++)
						{
							while (Resistance[i].getActive() == false)
							{
								i++;
								if (i >Resistance.length - 1)
								{
									break;
								}
							}
							if (i >Resistance.length - 1)
							{
								break;
							}
							System.out.print(Resistance[i].getName());
							for (int k = Resistance[i].getName().length(); k <= 20; k++)
							{
								System.out.print(" ");
							}
							System.out.println(Resistance[i].getHP());
						}
						System.out.println(" ");
						System.out.println("Bad Guys");
						System.out.println("----------");
						for (i = w; i<=soldier; i++)
						{
							while (FirstOrder[i].getActive() == false)
							{
								i++;
								if (i >FirstOrder.length - 1)
								{
									break;
								}
							}
							if (i >FirstOrder.length - 1)
							{
								break;
							}
							System.out.print(FirstOrder[i].getName());
							for (int k = FirstOrder[i].getName().length(); k <= 20; k++)
							{
								System.out.print(" ");
							}
							System.out.println(FirstOrder[i].getHP());
						}
						System.out.println("");
						System.out.println("What do you want to do?");
						System.out.println("  1. Attack.");
						System.out.println("  2. Open Door");
						System.out.println("  3. Have the droid to heal someone.");
						Medical m = (Medical) Resistance[4];
						int t = m.getNumTasks();
						System.out.println("             You have a total of "+t+" medpack.");
						toDo = input.nextInt();
						while ((toDo!=1 && toDo!=2 && toDo!=3) || (toDo==2 && FirstOrder[1].getActive()) || (toDo==3 && t<=0))
						{
							if(toDo==2 && FirstOrder[1].getActive())
							{
								System.out.println("You can't open the door while the guard is still alive.");
							}
							if(toDo==3 && t<=0)
							{
								System.out.println("You can't heal, because you are out of medpack.");
							}
							System.out.println("The input you press is invalid.");
							System.out.println("What do you want to do?");
							System.out.println("  1. Attack.");
							System.out.println("  2. Open Door");
							System.out.println("  3. Have the droid to heal someone.");
							System.out.println("             You have a total of "+t+" medpack.");
							toDo = input.nextInt();
						}
						
						if (toDo==1)
						{
							System.out.println("Your troop has begun firing upon the enemy.");
							
							for (int l = 1; l <= Resistance.length-3; l++)
							{
								while (Resistance[l].getActive() == false)
								{
									l++;
									if (l > Resistance.length-3)
									{
										break;
									}
								}
								if (l > Resistance.length-3)
								{
									break;
								}
								if (l == 1) //Special Jedi vs. FirstOrder CASE Making Jedi has a better chance to hit first order team.
								{
									if (toAttack == 5) //if SithLord is selected
									{
										int p = rand.nextInt(10)+1;
										if (p >= 5) // 50% chance of hitting
										{
											System.out.println(Resistance[l].getName()+" yelled, \"Hey "+FirstOrder[toAttack].getName()+ "!!!\"");
											System.out.print(Resistance[l].getName()+" successfully swings his Green "+Jedi.getWeapon()+" at "+FirstOrder[toAttack].getName()+". ");
											Resistance[l].doTask(FirstOrder[toAttack]);
											if (FirstOrder[toAttack].getActive() == false)
											{
												System.out.println(FirstOrder[toAttack].getName()+" was destroyed!!.");
												System.out.println(Resistance[l].getName()+" shouted in excitment, \"You have failed!! The First Order is NO MORE!!\"");
												System.out.println("CONGRATULATION! You have DEFEATED "+FirstOrder[toAttack].getName()+" The Control Panel is also destroyed.");
												System.out.println("You have captured Starkiller Base!!! Victory is Yours!!");
												System.out.println("      Would you like to continue? Y or N");
												response = input2.nextLine();
												while (!(response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n")))
												{
													System.out.println("Your input could not be read, play again? Y or N");
													response = input.nextLine();
												}
												if (response.equalsIgnoreCase("y"))
												{
													playing = false;
													break;
												}
												else
												{
													System.exit(0);
												}
											}
										}
										else
										{
											System.out.println(Resistance[l].getName()+" tried to hit "+FirstOrder[toAttack].getName()+", but swings a missed.");
											int s = rand.nextInt(5)+1;// SAYING DIFFERENT THINGS!!!!!!!!!!!!!!!!!!
											System.out.println(Resistance[l].getName()+" then provoked "+FirstOrder[toAttack].getName()+", \"Your Lightsaber looks lame!!\"");
										}
									}
									else
									{
										int p = rand.nextInt(10)+1;
										if (p > 3) //70% chance of hitting
										{
											System.out.print(Resistance[l].getName()+" successfully swings his Green "+Jedi.getWeapon()+" at "+FirstOrder[toAttack].getName()+". ");
											Resistance[l].doTask(FirstOrder[toAttack]);
											if (FirstOrder[toAttack].getActive()==false)
											{
												System.out.println(FirstOrder[toAttack].getName()+" was killed.");
												break;
											}
										}
										else
										{
											System.out.println(Resistance[l].getName()+" tried to throw his Green "+Jedi.getWeapon()+" at "+FirstOrder[toAttack].getName()+", but the aim was off.");
											System.out.println(FirstOrder[toAttack].getName()+" taunted "+Resistance[l].getName()+", \"Jedi Scum!!\"");
										}
									}
								}
								else
								{
									int p = rand.nextInt(10)+1;
									if (toAttack == 5)
									{
										if (p <= 8)
										{
											System.out.println(Resistance[l].getName()+" tried to fire his Blaster Rifle at "+FirstOrder[toAttack].getName()+", but his shot was block by his Lightsaber.");
											System.out.println(FirstOrder[toAttack].getName()+" spoke confidently,\"Are we done?\"");
										}
										else
										{
											System.out.print(Resistance[l].getName()+" successfully blasted "+FirstOrder[toAttack].getName()+" with his Blaster Rifle. ");
											Resistance[l].doTask(FirstOrder[toAttack]);
											if (FirstOrder[1].getActive() == false)
											{
												System.out.println(FirstOrder[toAttack].getName()+" was killed.");
												System.out.println(FirstOrder[toAttack].getName()+" screamed,\"NOOOOOOOOOOOOO!!\"");
												System.out.println("CONGRATULATION! You have DEFEATED THE FIRST ORDER! Starkiller Base is Yours!! Now go have some Pizza. :)");
												System.out.println("      Would you like to continue? Y or N");
												response = input2.nextLine();
												while (!(response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n")))
												{
													System.out.println("Your input could not be read, play again? Y or N");
													response = input.nextLine();
												}
												if (response.equalsIgnoreCase("y"))
												{
													playing = false;
													break;
												}
												else
												{
													System.exit(0);
												}
											}
										}
									}
									else if (p >= 5)
									{
										System.out.print(Resistance[l].getName()+" successfully blasted "+FirstOrder[toAttack].getName()+" with his Blaster Rifle. ");
										Resistance[l].doTask(FirstOrder[toAttack]);
										if (FirstOrder[toAttack].getActive() == false)
										{
											System.out.println(FirstOrder[toAttack].getName()+" was killed.");
											break;
										}
									}
									else 
									{
										System.out.println(Resistance[l].getName()+" failed to get his shot on. No damage was done to "+FirstOrder[toAttack].getName());
										
									}
									if (FirstOrder[toAttack].getActive())
									{
										int e = rand.nextInt(4)+1;
										while (Resistance[e].getActive() == false)
										{
											e = rand.nextInt(4)+1;
										}
										p = rand.nextInt(10)+1;
										if (toAttack == 5)
										{
											if (p >= 3)
											{
												System.out.print(FirstOrder[toAttack].getName()+" Striked "+Resistance[e].getName()+". ");
												FirstOrder[toAttack].doTask(Resistance[e]);
												if (Resistance[e].getActive()==false)
												{
													System.out.println(Resistance[e].getName()+" was killed by "+FirstOrder[toAttack].getName());
												}
												if (Resistance[4].getActive()==false)
												{
													System.out.println("Your "+Resistance[1].getName()+", has been defeated. You have FAIL to capture the base. GAME OVER!!!");
													System.out.println("      Would you like to continue? Y or N");
													response = input2.nextLine();
													while (!(response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n")))
													{
														System.out.println("Your input could not be read, play again? Y or N");
														response = input.nextLine();
													}
													if (response.equalsIgnoreCase("y"))
													{
														playing = false;
														break;
													}
													else
													{
														System.exit(0);
													}
												}
											}
											else
											{
												System.out.print(FirstOrder[toAttack].getName()+" force choked "+Resistance[e].getName()+" but his unstable personality failed him. ");
												System.out.println("No damage was done to "+Resistance[e].getName());
											}
										}
											
										else
										{
											if (p >= 6)
											{
												System.out.print(FirstOrder[toAttack].getName()+" successfully blasted "+Resistance[e].getName()+" with his Blaster Rifle. ");
												FirstOrder[toAttack].doTask(Resistance[e]);
												if (Resistance[e].getActive() == false)
												{
													System.out.println(Resistance[e].getName()+" was killed.");
													if (Resistance[4].getActive()==false)
													{
														System.out.println("It looks like R2D2 Was Killed!! Now you cannot access the control panel!!");
														System.out.println("GAME OVER!!!! You FAIL to capture the BASE!");
														System.out.println("      Would you like to continue? Y or N");
														response = input2.nextLine();
														while (!(response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n")))
														{
															System.out.println("Your input could not be read, play again? Y or N");
															response = input.nextLine();
														}
														if (response.equalsIgnoreCase("y"))
														{
															playing = false;
															break;
														}
														else
														{
															System.exit(0);
														}
													}	
												}
											}
											else 
											{
												System.out.println(FirstOrder[toAttack].getName()+" failed to get his shot on. No damage was done to "+Resistance[e].getName());
												
											}
										}
									}
								}
							}
							
						}
						if (toDo==2)
						{
							System.out.println("R2D2 has opened the door.");
							System.out.println("Prepare for the next wave!");
							toAttack = toAttack +2;
							w = w+2;
							soldier = soldier+2;	
						}
					}
				}
			}
		}
	}
}

