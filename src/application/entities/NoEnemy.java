package application.entities;

public class NoEnemy extends Enemy {

	public NoEnemy(int actionImageIndex, int x, int y) {
		super("no-enemy.png", actionImageIndex, null, x, y, 0);
	}

	public void initDefaultActions() {}
}
