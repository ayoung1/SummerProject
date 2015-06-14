package armoreffects;

import character.Figure;

public class ArmorNoEffect implements ArmorEffect{

	@Override
	public void action(Figure target) {}
	
	@Override
	public String toString()
	{
		return "No Effects";
	}

}
