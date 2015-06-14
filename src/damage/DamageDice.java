package damage;

import java.util.Random;

public class DamageDice {
	
	private int min;
	private int max;
	private Random random;
	
	public DamageDice(int min, int max)
	{
		this.max = max > 0 ? max : 0;
		this.min = min > 0 ? min : 0;
		this.random = new Random(System.currentTimeMillis() % 1000);
	}
	
	public int getDamage()
	{
		int randomNum = this.random.nextInt((this.max - this.min) + 1) + this.min;
		
		return randomNum;
	}
	
	public String toString()
	{
		return this.min + " - " + this.max + " Damage";
	}
}
