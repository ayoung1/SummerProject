package items;

import menu.MenuOption;
import displayelements.DisplayEquipment;

public abstract class Equipment implements MenuOption{

	public final EquipSlot type;
	
	private String name;
	private int cost;
	private int level;
	
	public Equipment(EquipSlot type, String name, int cost, int level)
	{
		this.type = type;
		this.name = name;
		this.cost = cost > 1 ? cost : 1;
		this.level = level > 1 ? level : 1;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getCost()
	{
		return this.cost;
	}
	
	public int getLevel()
	{
		return this.level;
	}
	
	public void display()
	{
		new DisplayEquipment(this).open();
	}
	
	public String menuString()
	{
		return this.name + " : " + this.type.name;
	}
	
	public abstract String getDetails();
}
