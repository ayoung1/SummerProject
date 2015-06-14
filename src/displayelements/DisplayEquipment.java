package displayelements;

import items.Equipment;

import java.awt.event.KeyEvent;

import Display.*;

public class DisplayEquipment implements Focusable{

	private Equipment equipment;
	
	public DisplayEquipment(Equipment equipment)
	{
		this.equipment = equipment;
	}
	
	@Override
	public void open() {
		Display.getInstanceOfDisplay().setFocus(this);
	}

	@Override
	public String displayString() {
		String string = "";
		
		string += "Name:\t" + this.equipment.getName() + "\n";
		string += "Slot:\t" + this.equipment.type.name + "\n";
		string += "Required Level:\t" + this.equipment.getLevel() + "\n";
		
		return string;
	}

	@Override
	public String eventString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(KeyEvent e, boolean focused) {
		// TODO Auto-generated method stub
		return false;
	}

}
