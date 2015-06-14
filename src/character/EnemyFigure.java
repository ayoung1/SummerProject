package character;

public abstract class EnemyFigure extends Figure{

	private final int expGiven;
	
	public EnemyFigure(String name, String className, int level, int health, int strength, int agility, int intelligence, int exp) {
		super(name, className, level, health, strength, agility, intelligence);
		this.expGiven = exp;
	}
	
	public int expGiven()
	{
		return this.expGiven;
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
}
