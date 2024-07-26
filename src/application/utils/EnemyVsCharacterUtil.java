package application.utils;

import application.entities.Enemy;
import application.card.effects.StatType;
import application.entities.Action;
import application.entities.Character;

public class EnemyVsCharacterUtil {

	public static void getEnemyAction(Enemy enemy, Character character) {
		Action action=enemy.getCurrentAction();
		switch (action.getStatType()) {
			case ATTACK:
				attack(character, action.getValue());
				break;
			default:
		}
		character.setStatsText();
	}
	
	public static void attack(Character character, int damage) {
		int armor = character.get(StatType.ARMOR);
		int finalDamage=damage-armor;
		
		// Reduce health by damage that went through the armor.
		character.decrement(StatType.HEALTH, finalDamage, 0);
		
		// Reduce armor by damage dealt
		if (damage>armor) {
			character.resetToZero(StatType.ARMOR); // damage exceeds armor. Set character armor to 0.
		}else {
			character.decrement(StatType.ARMOR, damage, 0); // subtract damage from armor.
		}
	}
}