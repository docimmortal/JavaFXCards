package application.buttons;

import application.card.effects.EffectTarget;
import application.card.effects.StatType;
import application.card.entities.NoCard;
import application.entities.Action;
import application.entities.Enemy;
import application.player.entities.DemoPlayer;
import application.player.entities.Player;
import application.utils.EnemyBuffUtil;
import application.utils.EnemyVsCharacterUtil;
import javafx.scene.Group;

public class EndTurnButton extends ImageButton {

	private Group group;
	
	public EndTurnButton(String filename, int x, int y, Player player, Group group) {
		super(filename, x, y, player);
		this.group=group;
	}
	
	public void doAction() {
		System.out.println("Ended turn. Do stuff.");
		
		// Any ongoing enemy damage/debuffs
		
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
		// Update enemy stats
		enemy.setStatsText();
		group.getChildren().set(8, enemy.getStatsText());
		
		// Player reset
		((DemoPlayer)getPlayer()).getCharacter().resetAll();
		group.getChildren().set(1, ((DemoPlayer)getPlayer()).getCharacter().getSpellpointsText());
		group.getChildren().set(5, ((DemoPlayer)getPlayer()).getCharacter().getStatsText());

		// If Enemy is dead, flag new button to display ("leave") and do not draw cards
		if (enemy.get(StatType.HEALTH)==0) {
			ImageButton leaveButton = new LeaveButton("Leave.png",1200,700, getPlayer(), group);
			group.getChildren().set(2, leaveButton.getImageView());
			getPlayer().clearHand();
		} else {
			// If the game is not over (ie player dead)
			if (!getPlayer().isGameOver()) {
				
				// Get next enemy action
				enemy.getNextAction();
	
				// Draw up cards
				drawCards(5,5);
				
				((DemoPlayer)getPlayer()).addCardsToJavaFxDisplay(group);
			} // else game over
	
			/* Debugging
			System.out.println("Updated points to "+((DemoPlayer)getPlayer()).getCharacter().get(StatType.POINTS));
			System.out.println("Discards:");
			for (Card card: getPlayer().getDiscard()) {
				System.out.println(card);
			}*/
		}
	}
	

	private void drawCards(int cards, int maxHandSize) {
		int count=0;
		int index=0;

		while (getPlayer().handSize() < maxHandSize && count<cards) {
			if (getPlayer().getHand().get(index) instanceof NoCard) {
				getPlayer().replaceACard(index);
				count++;
			}
			index++;
		}
	}

}
