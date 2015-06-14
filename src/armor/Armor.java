package armor;

import items.EquipSlot;
import items.Equipment;
import armoreffects.*;
import character.Figure;

public abstract class Armor extends Equipment{

	private int protection;
	private ArmorEffect effect;
	
	public Armor(String name, ArmorEffect effect, int protection, int cost, int level) {
		super(EquipSlot.ARMOR, name, cost, level);

		this.protection = protection > 0 ? protection : 0;
		this.effect = effect;
	}

	public int getArmor()
	{
		return this.protection;
	}

	public void onHitEffect(Figure target)
	{
		effect.action(target);
	}
	
	@Override
	public String getDetails()
	{
		return this.getName() + " : " + this.protection + " Armor : " + this.effect.toString();
	}

}
