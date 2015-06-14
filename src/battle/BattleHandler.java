package battle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import party.Party;
import character.Figure;

public class BattleHandler {

	private static BattleHandler battleHandler;
	
	//state
	public final BattleInterface startBattle;
	public final BattleInterface betweenTurn;
	public final BattleInterface playerTurn;
	public final BattleInterface enemyTurn;
	public final BattleInterface endBattle;
	//endstate
	
	private ArrayList<Figure> combatants;
	private ArrayList<Figure> players;
	private ArrayList<Figure> enemies;
	private int averageLevel;
	private Random random;
	
	private BattleInterface state;
	
	private BattleHandler()
	{
		this.startBattle = new StartBattle();
		this.betweenTurn = new BetweenTurns();
		this.playerTurn = new PlayerTurn();
		this.enemyTurn = new EnemyTurn();
		this.endBattle = new EndBattle();
		
		this.random = new Random(System.currentTimeMillis()*1000);
	}
	
	public static BattleHandler instanceOf()
	{
		if(battleHandler == null)
			battleHandler = new BattleHandler();
		return battleHandler;
	}
	
	public void startBattle(Party party)
	{	
		this.combatants = new ArrayList<Figure>();
		this.enemies = new ArrayList<Figure>();
		this.players = new ArrayList<Figure>();
		
		this.setupCombatants(party);
		this.sortCombatants();
		
		this.state = this.startBattle;
	}
	
	public void setState(BattleInterface state)
	{
		this.state = state;
	}
	
	public BattleInterface getState()
	{
		return this.state;
	}
	
	public boolean isAlly(Figure figure)
	{
		return this.players.contains(figure);
	}
	
	private void sortCombatants()
	{
		Collections.sort(this.combatants);
	}
	
	private void setupCombatants(Party party)
	{
		int levels = 0;
		EnemyFactory factory;
		Figure figure;
		
		for(int i = 0; i < party.size(); i++)
		{
			levels += party.getPartyMember(i).getLevel();
			this.combatants.add(party.getPartyMember(i));
			this.players.add(party.getPartyMember(i));
		}
		
		this.averageLevel = levels / party.size()-1;
		factory = new EnemyFactory(this.averageLevel);
		
		for(int i = 0; i < this.random.nextInt(party.size())+1; i++)
		{
			figure = factory.generateEnemy();
			this.enemies.add(figure);
			this.combatants.add(figure);
		}
	}
	
}
