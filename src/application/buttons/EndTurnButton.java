package application.buttons;

import application.card.effects.EffectTarget;
import application.card.effects.StatType;
import application.card.entities.NoCard;
import application.entities.Action;
import application.entities.Enemy;
import application.fxcomponents.EraseUtil;
import application.fxcomponents.ScreenUtil;
import application.fxcomponents.UpdateUtil;
import application.player.entities.DemoPlayer;
import application.utils.EnemyBuffUtil;
import application.utils.EnemyVsCharacterUtil;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class EndTurnButton extends ImageButton {

	public EndTurnButton(Group myParent, String filename, int x, int y) {
		super(myParent, filename, x, y);
	}

	public void doAction() {
		System.out.println("Ended turn. Do stuff.");

		int totalEnemyHealth=0;
		// Enemy does action, can be changed to getEnemies();
		for (int i=0; i<4; i++) {
			Node node = ScreenUtil.getNodeOfIndex(myParent, "#Enemy"+i);
			if (node!= null) {
				Enemy enemy = (Enemy) node;


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

				// Update enemy stats
				totalEnemyHealth+=enemy.get(StatType.HEALTH);
				if (enemy.get(StatType.HEALTH)!=0) {
					enemy.setStatsText();
					//group.getChildren().set(8, enemy.getStatsText());
					// Hard coded to only works for one enemy
					int no=enemy.getEnemyNumber();
					int enemyIndex=ScreenUtil.getIndexOfId(myParent,"#Enemy"+i);
					Group enemyGroup = (Group) myParent.getChildren().get(enemyIndex+no-1);
					enemyGroup.getChildren().set(2, enemy.getStatsText());
					enemy.getNextAction();
				}else {
					EraseUtil.eraseEnemy(enemy, myParent, 0);
				}
			}
		}

		// Player reset
		((DemoPlayer)getPlayer()).getCharacter().resetAll();
		//getMyParent().getChildren().set(1, ((DemoPlayer)getPlayer()).getCharacter().getSpellpointsText());
		Text text=((DemoPlayer)getPlayer()).getCharacter().getSpellpointsText();
		UpdateUtil.updateGroupText(myParent, "#PointsText", text);
		//getMyParent().getChildren().set(5, ((DemoPlayer)getPlayer()).getCharacter().getStatsText());
		text=((DemoPlayer)getPlayer()).getCharacter().getStatsText();
		UpdateUtil.updateGroupText(myParent, "#PlayerStatsText", text);

		// Update enemy stats
		if (totalEnemyHealth!=0) {
			if (!getPlayer().isGameOver()) {
				// Draw up cards
				drawCards(5,5);
				((DemoPlayer)getPlayer()).addCardsToJavaFxDisplay(myParent);
			} else {
				// game over
				ImageView iv=ScreenUtil.GameOver();
				clearHandAndButtons();
				myParent.getChildren().add(iv);
				ImageButton leaveButton = new RestartButton(getMyParent(),"Leave.png",1200,700);
				leaveButton.setId("LeaveButton");
				myParent.getChildren().add(leaveButton.getImageView());
			}

		}
	}


	private void clearHandAndButtons() {
		getPlayer().clearHand();
		((DemoPlayer)getPlayer()).addCardsToJavaFxDisplay(myParent);
		EraseUtil.erase("#DiscardButton",myParent);
		EraseUtil.erase("#EndTurnButton",myParent);
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
