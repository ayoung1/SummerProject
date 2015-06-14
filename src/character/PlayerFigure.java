package character;

import weapons.*;
import armor.*;

public abstract class PlayerFigure extends Figure{

	private int experience;
	
	public PlayerFigure(String name, String className, int health, int strength, int agility, int intelligence) {
		super(name, className, 1, health, strength, agility, intelligence);
		this.addWeapon(new BareHands());
		this.addArmor(new ClothArmor());
		this.experience = 0;
	}
	
	public void addExperience(int amount)
	{
		amount = amount > 0 ? amount : 0;
		
		this.experience += amount;
		
		if(this.experience >= 100)
		{
			this.experience -= 100;
			this.incrementLevel();
			this.levelUp();
		}
	}
	
	@Override
	public boolean damageTarget(Figure target) {
		int damage = this.getStrength();
		boolean hits = this.attackHits(target);
		
		if(!hits)
			return false;
		
		damage += this.getMainHand().attack(target);
		target.takeDamage(damage);
		
		return true;
	}
	
	protected abstract void levelUp();
}