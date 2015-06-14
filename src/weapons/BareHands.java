package weapons;

import weaponeffects.WeaponNoEffect;
import damage.DamageDice;

public class BareHands extends Weapon{

	public BareHands() {
		super("Bare Hands", new WeaponNoEffect(), new DamageDice(1, 5), 1, 1);
	}
}
