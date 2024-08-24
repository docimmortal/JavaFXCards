package application.entities;

import application.player.entities.RPGPlayer;

import java.util.List;

import application.card.effects.StatType;
import application.card.effects.Target;
import application.card.entities.RPGCard;
import application.fxcomponents.TextUtil;
import application.card.entities.Card;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class Character extends Entity {

	private Text pointsText;
	private String filename;
	private int x,y;
	private Character character;
	
	private List<Card> initialDeck;
	
	public Character(Group myParent, String filename, List<Card> initialDeck, int health, int attack, int armor, int points, int x, int y) {
		super(myParent, filename, x, y);
		character=this;
		this.initialDeck=initialDeck;
		this.filename=filename;
		this.x=x;
		this.y=y;
		set(StatType.POINTS, points);
		set(StatType.MAX_POINTS, points);
		pointsText = TextUtil.initText("Spell points: "+points, 70, 70);
		set(StatType.HEALTH, health);
		set(StatType.MAX_HEALTH, health);
		set(StatType.ARMOR, armor);
		getEntityImage().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	    	@Override
	        public void handle(MouseEvent event) {
	    		if (canTarget()) {
	    			getPlayer().setCharacterClicked(character);
	    			doTargetAction();
	    		}
	    	}
		});
		setId("Character");
	}
	
	public RPGPlayer getPlayer() {
		return (RPGPlayer)myParent.lookup("#Player");
	}
	
	public Character(Character model) {
		this(model.myParent,model.filename, model.initialDeck, 0,0,0,0, model.x, model.y);
		set(StatType.POINTS, model.get(StatType.POINTS));
		set(StatType.MAX_POINTS, model.get(StatType.MAX_POINTS));
		pointsText = TextUtil.initText("Spell points: "+model.get(StatType.POINTS), 70, 70);
		set(StatType.HEALTH, model.get(StatType.HEALTH));
		set(StatType.MAX_HEALTH, model.get(StatType.MAX_HEALTH));
		set(StatType.ARMOR, model.get(StatType.ARMOR));
	}

	public final Text getSpellpointsText() {
		pointsText.setId("PointsText");
		return pointsText;
	}
	
	public final void setStatsText() {
		super.setStatsText();
	}
	
	public final Text getStatsText() {
		Text text=super.getStatsText();
		text.setId("PlayerStatsText");
		return text;
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
		getPlayer().clearDeckHandDiscard();
		for (Card card: initialDeck) {
			getPlayer().addCardToDeck(card);
		}
		getPlayer().shuffleDeck();
	}
	
	public void setPointsText() {
		pointsText.setText("Spell points: "+get(StatType.POINTS));
	}
	
	public void resetAll() {
		resetTo(StatType.POINTS, StatType.MAX_POINTS);
		resetToZero(StatType.ARMOR);
	}
	
	public boolean canTarget() {
		boolean canTarget=false;
		if (getPlayer().getCardClicked() != null && ((RPGCard)getPlayer().getCardClicked()).getTarget()==Target.SELF) {
			canTarget=true;
		} else {
			System.out.println("Click a valid card.");
		}
		return canTarget;
	}
	
	public void doTargetAction() {
		Card card=getPlayer().getCardClicked();
		if (card!=null) {
			card.useTheCard();
		}
		
	}
}
