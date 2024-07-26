package application.buttons;

import java.util.List;

import application.card.effects.EffectTarget;
import application.card.effects.StatType;
import application.card.entities.Card;
import application.card.entities.NoCard;
import application.entities.Action;
import application.entities.Enemy;
import application.player.entities.DemoPlayer;
import application.player.entities.Player;
import application.utils.EnemyBuffUtil;
import application.utils.EnemyVsCharacterUtil;
import entities.card.Target;
import javafx.scene.Group;

public class EndTurnButton extends ImageButton {

	private Group group;
	
	public EndTurnButton(String filename, int x, int y, Player player, Group group) {
		super(filename, x, y, player);
		this.group=group;
	}
	
	public void doAction() {
		System.out.println("Ended turn. Do stuff.");
		
		// Enemy does action, can be changed to getEnemies();
		Enemy enemy = ((DemoPlayer)getPlayer()).getEnemy();
		
		// Enemy reset armor
		enemy.resetToZero(StatType.ARMOR);
		
		// Can be changed to loop through enemy list
		Action action = enemy.getCurrentAction();
		if (action.getTarget() == EffectTarget.SELF) {
			EnemyBuffUtil.getEnemyBuff(enemy);
		} else if (action.getTarget() == EffectTarget.CHARACTER) {
			EnemyVsCharacterUtil.getEnemyAction(enemy, ((DemoPlayer)getPlayer()).getCharacter());
		}
		
		// Player reset
		((DemoPlayer)getPlayer()).getCharacter().resetAll();
		group.getChildren().set(1, ((DemoPlayer)getPlayer()).getCharacter().getSpellpointsText());
		group.getChildren().set(5, ((DemoPlayer)getPlayer()).getCharacter().getStatsText());

		// If Enemy is dead, flag new button to display ("hunt") and do not draw cards

		// ELSE
		
		
		// If the game is not over (ie player dead)
		if (!getPlayer().isGameOver()) {
			
			// Get next enemy action
			enemy.getNextAction();
			System.out.println(enemy.getCurrentAction());

			// Draw up cards
			List<Card> hand=getPlayer().getHand();
			for (int i=0; i < getPlayer().handSize(); i++) {
				if (hand.get(i) instanceof NoCard) {
					getPlayer().replaceACard(i);
				}
			}
			((DemoPlayer)getPlayer()).addCardsToJavaFxDisplay(group);
		}

		/* Debugging
		System.out.println("Updated points to "+((DemoPlayer)getPlayer()).getCharacter().get(StatType.POINTS));
		System.out.println("Discards:");
		for (Card card: getPlayer().getDiscard()) {
			System.out.println(card);
		}*/
	}

}
