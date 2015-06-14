package inventory;

import items.Equipment;

import java.util.ArrayList;
import java.util.Iterator;
import menu.EmptyOption;
import menu.MenuOption;

public class Inventory implements Iterable<MenuOption>{

	public static final int MAX_ITEMS = 15;
	
	private ArrayList<Equipment> inventory;
	
	private class InventoryIterator implements Iterator<MenuOption>
	{
		private int position = 0;
		private final int end = inventory.size();
		
		@Override
		public boolean hasNext() {
			return position < end;
		}

		@Override
		public MenuOption next() {
			if(!this.hasNext())
			{
				return new EmptyOption();
			}
			Equipment current = inventory.get(position);
			position++;
			return current;
		}
		
	}
	
	public Inventory(Equipment ... party)
	{
		this.inventory = new ArrayList<Equipment>();
		
		for(Equipment equipment : inventory)
			this.addItem(equipment);
	} 
	
	public boolean addItem(Equipment equipment)
	{
		if(this.inventory.size() < MAX_ITEMS)
		{
			this.inventory.add(equipment);
			return true;
		}
		return false;
	}
	
	public Equipment removeItem(int index)
	{
		Equipment equipment = this.inventory.get(index);
		
		this.inventory.remove(index);
		
		return equipment;
	}
	
	public int size()
	{
		return this.inventory.size();
	}
	
	public Equipment getItem(int index)
	{
		return this.inventory.get(index);
	}
	
	@Override
	public Iterator<MenuOption> iterator() {
		return new InventoryIterator();
	}
}
