package application.utils;

import application.buttons.ImageButton;
import application.buttons.LeaveButton;
import application.card.effects.StatType;
import application.card.effects.Target;
import application.card.entities.RPGCard;
import application.entities.Character;
import application.entities.Enemy;
import application.fxcomponents.EraseUtil;
import application.fxcomponents.UpdateUtil;
import application.player.entities.DemoPlayer;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class UseCardUtil {

	public static void useTheCard(RPGCard card) {
		Enemy enemy=card.getEnemyClicked();
		Character character=card.getCharacterClicked();
		if ((character!=null && card.getTarget()==Target.SELF) || (enemy!=null && card.getTarget()==Target.ENEMY)) {
			int cost=card.get(StatType.COST);
			Integer damage=card.get(StatType.ATTACK);
			Integer block=card.get(StatType.ARMOR);
			
			// spend points on using card
			((DemoPlayer)card.getPlayer()).getCharacter().decrement(StatType.POINTS,cost,0); // lowest point value is 0.
			//this.getChildren().set(1, ((DemoPlayer)getPlayer()).getCharacter().getSpellpointsText());
			Text text=((DemoPlayer)card.getPlayer()).getCharacter().getSpellpointsText();
			UpdateUtil.updateGroupText(card.getMyParent(), "#PointsText", text);
			
			// Discard card
			card.getPlayer().setCardClicked(null);
			card.getPlayer().discardCardFromHand(card);
			
			// Do card actions based on target
			if (card.getTarget()==Target.ENEMY) {
				if (damage != null && damage!=0) {
					enemy.decrementHealth(damage);
					enemy.updateScreenText();
				}
			} else if (card.getTarget()==Target.SELF) {
				if (block != null && block!=0) {
					card.getPlayer().getCharacter().increment(StatType.ARMOR,block,-1); // no max armor amount
				}
			}
			
			// Update character stats.
			// This will change based on index in group for this text
			//this.getChildren().set(5, ((DemoPlayer)getPlayer()).getCharacter().getStatsText());
			card.getPlayer().getCharacter().updateScreenText();
			text=card.getPlayer().getCharacter().getStatsText();
			UpdateUtil.updateGroupText(card.getMyParent(), "#PlayerStatsText", text);
			
			if (enemy != null && enemy.get(StatType.HEALTH)>0) {
				enemy.getChildren().set(2, enemy.getStatsText());
			} else if (enemy!=null){
				EraseUtil.eraseEnemy(enemy);
				int totalHealth=0;
				for (Node n: card.getMyParent().getChildren()) {
					if (n instanceof Enemy) {
						totalHealth+=((Enemy)n).get(StatType.HEALTH);
					}
				}
				if (totalHealth==0) {
					card.getPlayer().clearHand();
					((DemoPlayer)card.getPlayer()).addCardsToJavaFxDisplay(card.getMyParent());
					EraseUtil.erase("#DiscardButton",card.getMyParent());
					EraseUtil.erase("#EndTurnButton",card.getMyParent());
					ImageButton leaveButton = new LeaveButton(card.getMyParent(),"Leave.png",1200,700);
					card.getMyParent().getChildren().add(leaveButton.getImageView());
				}
				EraseUtil.redraw(card.getMyParent(), card.getPlayer());
			}
		}
	}
}
