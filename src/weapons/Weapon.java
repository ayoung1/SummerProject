package weapons;

import weaponeffects.WeaponEffect;
import items.EquipSlot;
import items.Equipment;
import character.Figure;
import damage.DamageDice;

public abstract class Weapon extends Equipment{

	private DamageDice damageDice;
	private WeaponEffect effect;
		
	public Weapon(String name, WeaponEffect effect, DamageDice damageDice, int cost, int level) {
		super(EquipSlot.WEAPON, name, cost, level);
		
		this.damageDice = damageDice;
		this.effect = effect;
	}
	
	public int attack(Figure target)
	{
		this.effect.onHitEffect(target);
		return this.damageDice.getDamage();
	}
	
	@Override
	public String getDetails()
	{
		return this.getName() + " : " + this.damageDice.toString() + " : " + this.effect.toString();
	}
}
