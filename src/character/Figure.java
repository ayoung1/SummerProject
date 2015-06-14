package character;

import items.Equipment;

import java.util.ArrayList;
import java.util.Random;

import menu.MenuOption;
import displayelements.DisplayFigure;
import weapons.Weapon;
import armor.Armor;

public abstract class Figure implements MenuOption, Comparable<Figure>{
	private String name;
	private String className;
	private int health;
	private int maxhealth;
	private int strength;
	private int agility;
	private int intelligence;
	private int level;
	
	private Random random;
	
	private Weapon mainhand;
	private Armor armor;
	
	public Figure(String name, String className, int level, int health, int strength, int agility, int intelligence)
	{
		this.name = name;
		this.className = className;
		this.health = health > 0 ? health : 0;
		this.maxhealth = health > 0 ? health : 0;
		this.strength = strength > 0 ? strength : 0;
		this.agility = agility > 0 ? agility : 0;
		this.intelligence = intelligence > 0 ? intelligence : 0;
		this.level = level;
		
		this.random = new Random(System.currentTimeMillis() * 1000);
	}
	
	public int getLevel()
	{
		return this.level;
	}
	
	protected void incrementLevel()
	{
		this.level++;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getClassName()
	{
		return this.className;
	}
	
	public int getMaxHealth()
	{
		return this.maxhealth;
	}
	
	public int getHealth()
	{
		return this.health;
	}
	
	public int getStrength()
	{
		return this.strength;
	}
	
	public int getAgility()
	{
		return this.agility;
	}
	
	public int getIntelligence()
	{
		return this.intelligence;
	}
	
	public boolean isAlive()
	{
		return this.health > 0;
	}
	
	protected void takeDamage(int amount)
	{   
        double reduction = ( 0.01 * this.armor.getArmor() ) / ( 0.01 * this.armor.getArmor() + 1 );
		int damage = (int) ((1 - reduction) * amount);
        
		this.health -= damage;
	}
	
	protected void recoverHealth(int amount)
	{
		this.health -= amount;
		
		if(this.health > this.maxhealth)
			this.health = this.maxhealth;
	}
	
	public double chanceToHit(Figure attacked)
	{
		double hitchance = Math.pow(0.9, ((double)attacked.getAgility() / (double)this.getAgility()));
		int rounded = (int)(hitchance * 10);
		double displayed = (double)rounded / 10;
		
		return displayed;
	}
	
	protected boolean attackHits(Figure attacked)
	{
		double hitchance = Math.pow(0.9, ((double)attacked.getAgility() / (double)this.getAgility()));
		
		return (this.random.nextDouble() <= hitchance);
	}
	
	protected void addWeapon(Weapon weapon)
	{
		this.mainhand = weapon;
	}
	
	protected void addArmor(Armor armor)
	{
		this.armor = armor;
	}
	
	public Armor getArmor()
	{
		return this.armor;
	}
	
	public Weapon getMainHand()
	{
		return this.mainhand;
	}
	
	public ArrayList<Equipment> getEquipment()
	{
		ArrayList<Equipment> list = new ArrayList<Equipment>();
		
		list.add(this.mainhand);
		list.add(this.armor);
		
		return list;
	}
	
	public void display()
	{
		new DisplayFigure(this).open();
	}
	
	public String menuString()
	{
		return this.name + " : " + this.className;
	}
	
	public int compareTo(Figure o)
	{
		if(this.agility > o.agility)
			return -1;
		
		return 1;
	}
	
	public abstract boolean damageTarget(Figure target);
}
