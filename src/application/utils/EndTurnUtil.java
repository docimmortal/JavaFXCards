package application.utils;

import application.buttons.ImageButton;
import application.buttons.LeaveButton;
import application.buttons.RestartButton;
import application.card.effects.StatType;
import application.card.effects.Target;
import application.card.entities.NoCard;
import application.entities.Action;
import application.entities.Enemy;
import application.fxcomponents.EraseUtil;
import application.fxcomponents.ScreenUtil;
import application.fxcomponents.UpdateUtil;
import application.player.entities.DemoPlayer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class EndTurnUtil extends Group {

	private Group myParent;
	
	public EndTurnUtil(Group myParent) {
		this.myParent=myParent;
		setId("EndTurnUtil");
	}
	
	public DemoPlayer getPlayer() {
		return (DemoPlayer)myParent.lookup("#Player");
	}
	
	public void doAction() {
		//System.out.println("Ended turn. Do stuff.");

		int totalEnemyHealth=0;
		// Enemy does action, can be changed to getEnemies();
		for (Node node: myParent.getChildren()) {
			
			if (node instanceof Enemy) {
				Enemy enemy = (Enemy) node;
				//System.out.println("==============[EndTurn doAction]=========");
				// Any ongoing enemy damage/debuffs

				// Enemy reset armor
				enemy.resetToZero(StatType.ARMOR);

				// Can be changed to loop through enemy list
				Action action = enemy.getCurrentAction();
				if (action.getTarget() == Target.SELF) {
					EnemyBuffUtil.getEnemyBuff(enemy);
				} else if (action.getTarget() == Target.CHARACTER) {
					EnemyVsCharacterUtil.getEnemyAction(enemy, ((DemoPlayer)getPlayer()).getCharacter());
				}

				// Update enemy stats
				totalEnemyHealth+=enemy.get(StatType.HEALTH);
				if (enemy.get(StatType.HEALTH)!=0) {
					enemy.setStatsText();
					enemy.getChildren().set(2, enemy.getStatsText());
					enemy.getNextAction();
				}else {
					EraseUtil.eraseEnemy(enemy);
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
				ImageButton leaveButton = new RestartButton(myParent,"Leave.png",1200,700);
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
