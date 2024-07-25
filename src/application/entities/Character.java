package application.entities;

import application.player.entities.Player;
import application.utils.TextUtil;

import java.util.ArrayList;
import java.util.List;

import application.card.effects.StatType;
import application.card.entities.Card;
import javafx.scene.text.Text;

public class Character extends Entity {

	private Text pointsText;
	
	public Character(String filename, Player player, int health, int attack, int armor, int points, int x, int y) {
		super(filename, player, x, y);
		set(StatType.POINTS, points);
		set(StatType.MAX_POINTS, points);
		pointsText = TextUtil.initText("Spell points: "+points, 70, 70);
		set(StatType.HEALTH, health);
		set(StatType.MAX_HEALTH, health);
		//set(StatType.ATTACK, attack);
		set(StatType.ARMOR, armor);
		
		//statsImage = ImageLoader.load("images//characters//stats-images.png",false);
		//statsImage.setLayoutX(140);
		//statsImage.setLayoutY(getLowerYPlusOffset()-22);
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
	
	public List<Card> getInitialDeck(){
		System.out.println("===========[ NO INITIAL DECK ]===========");
		return new ArrayList<>();
	}
	
	public void setPointsText() {
		pointsText.setText("Spell points: "+get(StatType.POINTS));
	}
	
	public void resetAll() {
		resetTo(StatType.POINTS, StatType.MAX_POINTS);
		//resetToZero(StatType.ATTACK);
		resetToZero(StatType.ARMOR);
	}
}
