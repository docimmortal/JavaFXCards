package application.utils;

import application.card.effects.StatType;
import application.entities.Action;
import application.entities.Enemy;

public class EnemyBuffUtil {

	public static void getEnemyBuff(Enemy enemy) {
		Action action = enemy.getCurrentAction();
		switch (action.getStatType()) {
			case ARMOR:
				setArmor(enemy, action.getValue());
				System.out.println("Enemy armor: "+enemy.get(StatType.ARMOR));
				break;
			default:
		}
		enemy.updateScreenText();
		
	}
	public static void setArmor(Enemy enemy, int value) {
		enemy.set(StatType.ARMOR, value);
	}
}
