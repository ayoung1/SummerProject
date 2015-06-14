package battle;

public class BetweenTurns implements BattleInterface {

	private BattleHandler handler;
	@Override
	public void startBattle() {
		this.handler = BattleHandler.instanceOf();
	}

	@Override
	public void betweenTurns() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerTurn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enemyTurn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void endBattle() {
		// TODO Auto-generated method stub

	}

}
