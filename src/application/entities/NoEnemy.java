package application.entities;

import javafx.scene.Group;

public class NoEnemy extends Enemy {

	public NoEnemy(Group myParent, int x, int y) {
		super(myParent, "no-enemy.png", "noEnemy", x, y, 0);
	}

	public void initDefaultActions() {}
}
