package application.entities;

import application.player.entities.Player;
import application.utils.TextUtil;
import entities.card.Target;

import application.card.effects.StatType;
import application.card.entities.AnExtendedCard;
import javafx.scene.Group;
import javafx.scene.text.Text;

public class Character extends Entity {

	private Text pointsText;
	private Group group;
	
	public Character(String filename, Player player, Group group, int health, int attack, int armor, int points, int x, int y) {
		super(filename, player, x, y);
		this.group=group;
		set(StatType.POINTS, points);
		set(StatType.MAX_POINTS, points);
		pointsText = TextUtil.initText("Spell points: "+points, 70, 70);
		set(StatType.HEALTH, health);
		set(StatType.MAX_HEALTH, health);
		set(StatType.ARMOR, armor);
	}

	public final Text getSpellpointsText() {
		return pointsText;
	}
	
	public final void setStatsText() {
		super.setStatsText();
	}
	
	public final Text getStatsText() {
		return super.getStatsText();
	}
	
	/*
	 * Methods that can be overridden
	 */
	
	@Override
	public void updateScreenText() {
		setStatsText();
		setPointsText();
	}
	
	public void setInitialDeck(){
		System.out.println("===========[ DUMMY INITIAL DECK ]===========");
		AnExtendedCard block = new AnExtendedCard("images\\cards\\block.jpg", Target.SELF, getPlayer(), group, "Block", 1);
		block.set(StatType.ARMOR,5);
		AnExtendedCard punch = new AnExtendedCard("images\\cards\\punch.jpg",Target.ENEMY, getPlayer(), group, "Punch", 1);
		punch.set(StatType.ATTACK,5);
		
		for(int i=0; i<10; i++) {
			AnExtendedCard newCard=null;
			// create only 2 cards (for now)
			if (i%2==0) {
				newCard=new AnExtendedCard(block);
				
			} else {
				newCard=new AnExtendedCard(punch);
			}
			getPlayer().addCardToDeck(newCard);
		}
	}
	
	public void setPointsText() {
		pointsText.setText("Spell points: "+get(StatType.POINTS));
	}
	
	public void resetAll() {
		resetTo(StatType.POINTS, StatType.MAX_POINTS);
		resetToZero(StatType.ARMOR);
	}
}
