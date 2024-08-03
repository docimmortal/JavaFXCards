package application.buttons;

import application.Main;
import application.card.effects.EffectTarget;
import application.card.effects.StatType;
import application.card.entities.NoCard;
import application.entities.Action;
import application.entities.Enemy;
import application.fxcomponents.EraseUtil;
import application.fxcomponents.ImageLoader;
import application.player.entities.DemoPlayer;
import application.player.entities.Player;
import application.utils.EnemyBuffUtil;
import application.utils.EnemyVsCharacterUtil;
import application.utils.ScreenUtil;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class EndTurnButton extends ImageButton {

	private Group group;
	
	public EndTurnButton(String filename, int x, int y, Player player, Group group) {
		super(filename, x, y, player);
		this.group=group;
	}
	
	public void doAction() {
		System.out.println("Ended turn. Do stuff.");
		
		// Enemy does action, can be changed to getEnemies();
		Enemy enemy = ((DemoPlayer)getPlayer()).getEnemy(0);
		
		// Any ongoing enemy damage/debuffs
		
		
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

		// Update enemy stats
		if (enemy.get(StatType.HEALTH)!=0) {
			enemy.setStatsText();
			//group.getChildren().set(8, enemy.getStatsText());
			// Hard coded to only works for one enemy
			int no=enemy.getEnemyNumber();
			Group enemyGroup = (Group) group.getChildren().get(Main.ENEMY1_INDEX+no-1);
			enemyGroup.getChildren().set(2, enemy.getStatsText());
			// If the game is not over (ie player dead)
			if (!getPlayer().isGameOver()) {

				// Get next enemy action
				enemy.getNextAction();

				// Draw up cards
				drawCards(5,5);

				((DemoPlayer)getPlayer()).addCardsToJavaFxDisplay(group);
			} else {
				// game over
				ImageView iv=ScreenUtil.GameOver();
				for (int i=group.getChildren().size()-1; i>0; i--) {
					group.getChildren().remove(i);
				}
				group.getChildren().add(iv);
				
			}

		} else {
			EraseUtil.eraseEnemy(enemy, group, 0);
			getPlayer().clearHand();
			((DemoPlayer)getPlayer()).addCardsToJavaFxDisplay(group);
			EraseUtil.eraseDiscard(7,group);
			ImageButton leaveButton = new LeaveButton("Leave.png",1200,700, getPlayer());
			group.getChildren().set(2, leaveButton.getImageView());
			EraseUtil.redraw(group, getPlayer());
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
