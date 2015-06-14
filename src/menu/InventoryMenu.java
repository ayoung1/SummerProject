package menu;

import inventory.Inventory;
import Display.*;

public class InventoryMenu extends Menu implements Focusable, MenuOption{

	private Inventory inventory;
	
	public InventoryMenu(Inventory inventory) {
		super("Inventory", inventory);

		this.inventory = inventory;
	}

	@Override
	protected int getSize() {
		return this.inventory.size();
	}

	@Override
	protected MenuOption getIndex(int index) {
		return this.inventory.getItem(index);
	}

	@Override
	public String menuString() {
		return this.getName();
	}

	@Override
	public void display() {
		Display.getInstanceOfDisplay().setFocus(this);		
	}

	
}
