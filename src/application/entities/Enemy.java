package application.entities;

import java.util.ArrayList;
import java.util.List;

import application.card.effects.Adjustment;
import application.card.effects.EffectTarget;
import application.card.effects.StatType;
import application.card.entities.Card;
import application.fxcomponents.ImageLoader;
import application.player.entities.DemoPlayer;
import application.player.entities.Player;
import application.utils.TextUtil;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class Enemy extends Entity{

	private Enemy thisEnemy;

	private ImageView actionImage;
	
	private Text actionText;
	
	private List<Action> actions;
	private Action currentAction;
	private int actionIndex;
	
	private int actionImageIndex;
	private int actionX;
	private int actionY;
	
	public Enemy(String filename, int actionImageIndex, Player player, int x, int y, int health) {
		super(filename, player, x, y);
		thisEnemy=this;
		
		initDefaultActions();
		this.actionImageIndex=actionImageIndex;
		actionY=y-(int)actionImage.getImage().getHeight()-2;
		actionX=x+(int)(getImageView().getImage().getWidth()/2)-(int)(actionImage.getImage().getWidth()/2);
		
		set(StatType.HEALTH,health);
		set(StatType.MAX_HEALTH,health);
		
		
		displayFirstAction(x,y);
		
		// Add click event
		getImageView().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	    	@Override
	        public void handle(MouseEvent event) {
	    		if (canTarget()) {
	    			((DemoPlayer)player).setEnemyClicked(thisEnemy);
	    			doTargetAction();
	    		}
	    	}
		});
		
		initText();
		debugAllStats();
		setStatsText();
	}
	
	public void initDefaultActions() {
		actions = new ArrayList<>();
		ImageView attackImage = ImageLoader.load("images//enemies//lattack.png",false);
		ImageView blockImage = ImageLoader.load("images//enemies//lshield.png",false);
		
		//actions.add(String name, ImageView imageView, EffectTarget target, Adjustment adjustment, StatType statType, int value) 		
		actions.add(new Action("Block",blockImage, EffectTarget.SELF, Adjustment.INCREMENTS, StatType.ARMOR, 6));
		actions.add(new Action("Bite",attackImage, EffectTarget.CHARACTER, Adjustment.INCREMENTS, StatType.ATTACK, 6)); 
		currentAction = actions.get(0);
		actionImage=currentAction.getImageView();
	}
	
	public void initDefaultActions(List<Action> actions) {
		this.actions = actions;
		currentAction = actions.get(0);
		actionImage=currentAction.getImageView();
	}
	
	public final void setStatsText() {
		super.setStatsText();
	}
	
	public final Text getStatsText() {
		return super.getStatsText();
	}
	
	public void displayFirstAction(int x, int y) {
		actionImage = actions.get(0).getImageView();
		setActionIndexXY();
	}
	
	private void setActionIndexXY() {
		actionImage.setLayoutX(actionX);
		actionImage.setLayoutY(actionY);
	}
	
	public final Action getNextAction() {
		actionIndex++;
		if (actionIndex==actions.size()) {
			actionIndex=0;
		}
		currentAction=actions.get(actionIndex);
		
		// update actionImage
		actionImage=currentAction.getImageView();
		setActionIndexXY();
		Group group=((DemoPlayer)getPlayer()).getGroup();
		group.getChildren().set(actionImageIndex, actionImage);
		
		return currentAction;
	}
	
	public final Action getCurrentAction() {
		return currentAction;
	}
	
	public final List<Action> getActions() {
		return actions;
	}

	public final void setActions(List<Action> actions) {
		this.actions = actions;
	}
	
	public final ImageView getActionImage() {
		return actionImage;
	}

	public final void setActionImage(ImageView actionImage) {
		this.actionImage.setImage(actionImage.getImage());
	}

	public final int getHealth() {
		return get(StatType.HEALTH);
	}

	public final int getMaxHealth() {
		return get(StatType.MAX_HEALTH);
	}

	// takes into account armor
	public final void decrementHealth(int damage) {
		//System.out.println("=====> Health: "+getHealth()+", Damage: "+damage);
		if (damage>0) {
			int armor = get(StatType.ARMOR);
			int finalDamage=damage-armor;
			decrement(StatType.HEALTH, finalDamage, 0);
			
			// Reduce armor by damage dealt
			if (damage>armor) {
				resetToZero(StatType.ARMOR); // damage exceeds armor. Set character armor to 0.
			}else {
				decrement(StatType.ARMOR, damage, 0); // subtract damage from armor.
			}
		}
	}
	
	public final void incrementHealth(int thisAmount) {
		if (thisAmount>0) {
			increment(StatType.HEALTH, thisAmount, get(StatType.MAX_HEALTH));
		}
	}
	
	public final Text getActionText() {
		return actionText;
	}
	
	public void setActionText() {
		actionText.setText(""+currentAction.getValue());
	}
	
	private void initText() {
		actionText = TextUtil.initText(""+currentAction.getValue(), (int)actionImage.getLayoutX()+10, (int)actionImage.getLayoutY()+25);
	}
	
	@Override
	public void updateScreenText() {
		setStatsText();
		setActionText();
	}

	/*
	 * Methods that can be overridden
	 */
	public boolean canTarget() {
		boolean canTarget=false;
		if (getPlayer().getCardClicked() != null) {
			System.out.println("We can target the enemy.");
			canTarget=true;
		} else {
			System.out.println("Click a valid card.");
		}
		return canTarget;
	}
	
	public void doTargetAction() {
		Card card=getPlayer().getCardClicked();
		if (card!=null) {
			System.out.println("Used the card.");
			card.useTheCard();
		}
		
	}

}
