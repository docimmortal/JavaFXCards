package application.entities;

import application.player.entities.Player;
import application.utils.TextUtil;
import entities.card.Target;

import java.util.ArrayList;
import java.util.List;

import application.card.effects.StatType;
import application.card.entities.AnExtendedCard;
import javafx.scene.Group;
import javafx.scene.text.Text;

public class Character extends Entity {

	private Text pointsText;
	private Group group;
	private String filename;
	private int x,y;
	
	public Character(String filename, Player player, Group group, int health, int attack, int armor, int points, int x, int y) {
		super(filename, player, x, y);
		this.filename=filename;
		this.group=group;
		this.x=x;
		this.y=y;
		set(StatType.POINTS, points);
		set(StatType.MAX_POINTS, points);
		pointsText = TextUtil.initText("Spell points: "+points, 70, 70);
		set(StatType.HEALTH, health);
		set(StatType.MAX_HEALTH, health);
		set(StatType.ARMOR, armor);
	}
	
	public Character(Character model) {
		super(model.filename, model.getPlayer(), model.x, model.y);
		this.group=model.group;
		set(StatType.POINTS, model.get(StatType.POINTS));
		set(StatType.MAX_POINTS, model.get(StatType.MAX_POINTS));
		pointsText = TextUtil.initText("Spell points: "+model.get(StatType.POINTS), 70, 70);
		set(StatType.HEALTH, model.get(StatType.HEALTH));
		set(StatType.MAX_HEALTH, model.get(StatType.MAX_HEALTH));
		set(StatType.ARMOR, model.get(StatType.ARMOR));
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
		getPlayer().addCardToDeck(block);
		
		List<AnExtendedCard> eCards = new ArrayList<>();
		AnExtendedCard punch = new AnExtendedCard("images\\cards\\lg-punch.jpg",Target.ENEMY, getPlayer(), group, "Punch", 1);
		punch.set(StatType.ATTACK,5);
		eCards.add(punch);
		getPlayer().addCardToDeck(punch);
		
		AnExtendedCard puncha = new AnExtendedCard("images\\cards\\puncha.jpg",Target.ENEMY, getPlayer(), group, "Punch", 1);
		puncha.set(StatType.ATTACK,5);
		eCards.add(puncha);
		getPlayer().addCardToDeck(puncha);
		
		AnExtendedCard punchb = new AnExtendedCard("images\\cards\\punchb.jpg",Target.ENEMY, getPlayer(), group, "Punch", 1);
		punchb.set(StatType.ATTACK,5);
		eCards.add(punchb);
		getPlayer().addCardToDeck(punchb);
		
		AnExtendedCard punchc = new AnExtendedCard("images\\cards\\punchc.jpg",Target.ENEMY, getPlayer(), group, "Punch", 1);
		punchc.set(StatType.ATTACK,5);
		eCards.add(punchc);
		getPlayer().addCardToDeck(punchc);
		
		AnExtendedCard punchd = new AnExtendedCard("images\\cards\\punchd.jpg",Target.ENEMY, getPlayer(), group, "Punch", 1);
		punchd.set(StatType.ATTACK,5);
		eCards.add(punchd);
		getPlayer().addCardToDeck(punchd);
		
		AnExtendedCard punche = new AnExtendedCard("images\\cards\\punche.jpg",Target.ENEMY, getPlayer(), group, "Punch", 1);
		punche.set(StatType.ATTACK,5);
		eCards.add(punche);
		getPlayer().addCardToDeck(punche);
		
	}
	
	public void setPointsText() {
		pointsText.setText("Spell points: "+get(StatType.POINTS));
	}
	
	public void resetAll() {
		resetTo(StatType.POINTS, StatType.MAX_POINTS);
		resetToZero(StatType.ARMOR);
	}
}
