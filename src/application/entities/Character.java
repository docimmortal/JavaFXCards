package application.entities;

import application.player.entities.DemoPlayer;

import java.util.ArrayList;
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
	
	public Character(Group myParent, String filename, int health, int attack, int armor, int points, int x, int y) {
		super(myParent, filename, x, y);
		character=this;
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
	
	public DemoPlayer getPlayer() {
		return (DemoPlayer)myParent.lookup("#Player");
	}
	
	public Character(Character model) {
		this(model.myParent,model.filename,0,0,0,0, model.x, model.y);
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
		
		System.out.println("===========[ DUMMY INITIAL DECK ]===========");
		RPGCard block = new RPGCard(myParent, "images\\cards\\block.jpg", Target.SELF, "Block", 1);
		block.set(StatType.ARMOR,5);
		getPlayer().addCardToDeck(block);
		
		List<RPGCard> eCards = new ArrayList<>();
		RPGCard punch = new RPGCard(myParent,"images\\cards\\lg-punch.jpg",Target.ENEMY, "Punch", 1);
		punch.set(StatType.ATTACK,5);
		eCards.add(punch);
		getPlayer().addCardToDeck(punch);
	
		RPGCard punchPlus = new RPGCard(myParent,"images\\cards\\punch-plus.jpg",Target.ENEMY, "Punch+", 1);
		punchPlus.set(StatType.ATTACK,5);
		punchPlus.set(StatType.CYCLE, 1);
		eCards.add(punchPlus);
		getPlayer().addCardToDeck(punchPlus);
		
		RPGCard blockPlus = new RPGCard(myParent,"images\\cards\\block-plus.jpg",Target.SELF, "Block+", 1);
		blockPlus.set(StatType.ARMOR,6);
		blockPlus.set(StatType.CYCLE, 1);
		eCards.add(blockPlus);
		getPlayer().addCardToDeck(blockPlus);
		
		RPGCard puncha = new RPGCard(myParent,"images\\cards\\puncha.jpg",Target.ENEMY, "Punch A", 1);
		puncha.set(StatType.ATTACK,5);
		eCards.add(puncha);
		getPlayer().addCardToDeck(puncha);
		
		getPlayer().addCardToDeck(new RPGCard(block));
		
		RPGCard punchb = new RPGCard(myParent,"images\\cards\\punchb.jpg",Target.ENEMY, "Punch B", 1);
		punchb.set(StatType.ATTACK,5);
		eCards.add(punchb);
		getPlayer().addCardToDeck(punchb);
		
		RPGCard punchc = new RPGCard(myParent,"images\\cards\\punchc.jpg",Target.ENEMY, "Punch C", 1);
		punchc.set(StatType.ATTACK,5);
		eCards.add(punchc);
		getPlayer().addCardToDeck(punchc);
		
		getPlayer().addCardToDeck(new RPGCard(block));
		
		RPGCard punchd = new RPGCard(myParent,"images\\cards\\punchd.jpg",Target.ENEMY, "Punch D", 1);
		punchd.set(StatType.ATTACK,5);
		eCards.add(punchd);
		getPlayer().addCardToDeck(punchd);
		
		RPGCard punche = new RPGCard(myParent,"images\\cards\\punche.jpg",Target.ENEMY, "Punch E", 1);
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
