package weapons;

import weaponeffects.WeaponNoEffect;
import damage.DamageDice;

public class Claws extends Weapon{

	public Claws() {
		super("Claws", new WeaponNoEffect(), new DamageDice(3, 7), 1, 1);
		// TODO Auto-generated constructor stub
	}

}
