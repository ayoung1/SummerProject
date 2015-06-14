package weaponeffects;

import character.Figure;

public class WeaponNoEffect implements WeaponEffect{

	@Override
	public void onHitEffect(Figure target) {}
	
	@Override
	public String toString()
	{
		return "No Effects";
	}

}
