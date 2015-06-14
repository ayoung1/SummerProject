package battle;

import java.util.Random;

import character.*;

public class EnemyFactory {
	
	private int averageLevel;
	private Random random;
	
	public EnemyFactory(int averageLevel)
	{
		this.averageLevel = averageLevel;
		this.random = new Random(System.currentTimeMillis()*1000);
	}
	
	public Figure generateEnemy()
	{
		if(this.averageLevel > 0)
			return lowlevelEnemies();
		
		return lowlevelEnemies();
	}
	
	private Figure lowlevelEnemies()
	{
		int rand = this.random.nextInt();
		
		return new Rat();
	}
}
