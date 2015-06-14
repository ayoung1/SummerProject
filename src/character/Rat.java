package character;

import armor.FuzzyHide;
import weapons.Claws;

public class Rat extends EnemyFigure{

	private final static int level = 1;
	private final static int health = 125;
	private final static int strength = 9;
	private final static int agility = 12;
	private final static int intelligence = 3;
	private final static int exp = 7;
	
	public Rat() {
		super("Rat", "Monster", level, health, strength, agility, intelligence, exp);

		this.addWeapon(new Claws());
		this.addArmor(new FuzzyHide());
	}
}
