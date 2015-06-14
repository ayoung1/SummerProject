package displayelements;

import items.Equipment;

import java.awt.event.KeyEvent;

import character.Figure;
import Display.*;

public class DisplayFigure implements Focusable{

	private Figure figure;
	
	public DisplayFigure(Figure figure)
	{
		this.figure = figure;
	}
	
	@Override
	public void open() {
		Display.getInstanceOfDisplay().setFocus(this);
	}

	@Override
	public String displayString() {
		String string = "";
		
		string += "Name: " + this.figure.getName() + '\n';
		string += "Class: " + this.figure.getClassName() + '\n';
		
		string += "\tHealth: " + this.figure.getHealth() + "/" + this.figure.getMaxHealth() + '\n';
		string += "\tStrength: " + this.figure.getStrength() + '\n';
		string += "\tAgility: " + this.figure.getAgility() + '\n';
		string += "\tIntelligence: " + this.figure.getIntelligence() + "\n\n";
		
		for(Equipment equipment : this.figure.getEquipment())
		{
			string += equipment.getDetails() + '\n';
		}
		
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
		if(focused)
		{
			if(Keys.keyEscape(e))
				return true;
		}
		return false;
	}

}
