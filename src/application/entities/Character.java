package application.entities;

import application.player.entities.DemoPlayer;
import application.player.entities.Player;
import application.utils.TextUtil;
import entities.card.Target;

import java.util.ArrayList;
import java.util.List;

import application.card.effects.StatType;
import application.card.entities.AnExtendedCard;
import application.card.entities.Card;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class Character extends Entity {

	private Text pointsText;
	private Group group;
	private String filename;
	private int x,y;
	private Character character;
	
	public Character(String filename, Player player, Group group, int health, int attack, int armor, int points, int x, int y) {
		super(filename, player, x, y);
		character=this;
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
		getEntityImage().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	    	@Override
	        public void handle(MouseEvent event) {
	    		if (canTarget()) {
	    			((DemoPlayer)player).setCharacterClicked(character);
	    			doTargetAction();
	    		}
	    	}
		});
	}
	
	public Character(Character model) {
		this(model.filename, model.getPlayer(), model.group, 0,0,0,0, model.x, model.y);
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
		System.out.println("Group size: "+group.getChildren().size());
		Group group2 = new Group();
		group2.getChildren().add(new ImageView());
		group2.getChildren().add(new ImageView());
		group2.getChildren().add(new ImageView());
		group2.getChildren().add(new ImageView());
		System.out.println("Group2 size: "+group2.getChildren().size());
		group.getChildren().addAll(group2);
		System.out.println("Group size: "+group.getChildren().size());
		
		System.out.println("===========[ DUMMY INITIAL DECK ]===========");
		AnExtendedCard block = new AnExtendedCard("images\\cards\\block.jpg", Target.SELF, getPlayer(), group, "Block", 1);
		block.set(StatType.ARMOR,5);
		getPlayer().addCardToDeck(block);
		
		List<AnExtendedCard> eCards = new ArrayList<>();
		AnExtendedCard punch = new AnExtendedCard("images\\cards\\lg-punch.jpg",Target.ENEMY, getPlayer(), group, "Punch", 1);
		punch.set(StatType.ATTACK,5);
		eCards.add(punch);
		getPlayer().addCardToDeck(punch);
		
		AnExtendedCard punchPlus = new AnExtendedCard("images\\cards\\punch-plus.jpg",Target.ENEMY, getPlayer(), group, "Punch+", 1);
		punchPlus.set(StatType.ATTACK,5);
		punchPlus.set(StatType.CYCLE, 1);
		eCards.add(punchPlus);
		getPlayer().addCardToDeck(punchPlus);
		
		AnExtendedCard blockPlus = new AnExtendedCard("images\\cards\\block-plus.jpg",Target.ENEMY, getPlayer(), group, "Block+", 1);
		blockPlus.set(StatType.ARMOR,5);
		blockPlus.set(StatType.CYCLE, 1);
		eCards.add(blockPlus);
		getPlayer().addCardToDeck(blockPlus);
		
		AnExtendedCard puncha = new AnExtendedCard("images\\cards\\puncha.jpg",Target.ENEMY, getPlayer(), group, "Punch A", 1);
		puncha.set(StatType.ATTACK,5);
		eCards.add(puncha);
		getPlayer().addCardToDeck(puncha);
		
		getPlayer().addCardToDeck(new AnExtendedCard(block));
		
		AnExtendedCard punchb = new AnExtendedCard("images\\cards\\punchb.jpg",Target.ENEMY, getPlayer(), group, "Punch B", 1);
		punchb.set(StatType.ATTACK,5);
		eCards.add(punchb);
		getPlayer().addCardToDeck(punchb);
		
		AnExtendedCard punchc = new AnExtendedCard("images\\cards\\punchc.jpg",Target.ENEMY, getPlayer(), group, "Punch C", 1);
		punchc.set(StatType.ATTACK,5);
		eCards.add(punchc);
		getPlayer().addCardToDeck(punchc);
		
		getPlayer().addCardToDeck(new AnExtendedCard(block));
		getPlayer().addCardToDeck(new AnExtendedCard(block));
		getPlayer().addCardToDeck(new AnExtendedCard(block));
		
		AnExtendedCard punchd = new AnExtendedCard("images\\cards\\punchd.jpg",Target.ENEMY, getPlayer(), group, "Punch D", 1);
		punchd.set(StatType.ATTACK,5);
		eCards.add(punchd);
		getPlayer().addCardToDeck(punchd);
		
		AnExtendedCard punche = new AnExtendedCard("images\\cards\\punche.jpg",Target.ENEMY, getPlayer(), group, "Punch E", 1);
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
		if (getPlayer().getCardClicked() != null && ((AnExtendedCard)getPlayer().getCardClicked()).getTarget()==Target.SELF) {
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
