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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class Enemy extends Entity{

	private Enemy thisEnemy;
	private int health;
	private int maxHealth;
	
	private ImageView healthImage;
	private ImageView actionImage;
	
	private Text actionText;
	
	private List<Action> actions;
	private Action currentAction;
	private int actionIndex;
	
	public Enemy(String filename, Player player, int x, int y, int health) {
		super(filename, player, x, y);
		thisEnemy=this;
		this.health=health;
		this.maxHealth=health;
		
		/*
		healthImage = ImageLoader.load("images//enemies//lheart.png",false);
		int healthY=y+(int)getImageView().getImage().getHeight()+2;
		int healthX=x+(int)(getImageView().getImage().getWidth()/2)-(int)(healthImage.getImage().getWidth()/2);
		healthImage.setLayoutX(healthX);
		healthImage.setLayoutY(healthY);*/
		
		actionImage = ImageLoader.load("images//enemies//lattack.png",false);
		int actionY=y-(int)actionImage.getImage().getHeight()-2;
		int actionX=x+(int)(getImageView().getImage().getWidth()/2)-(int)(actionImage.getImage().getWidth()/2);
		actionImage.setLayoutX(actionX);
		actionImage.setLayoutY(actionY);
		
		getImageView().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	    	@Override
	        public void handle(MouseEvent event) {
	    		if (canTarget()) {
	    			System.out.println("Clicked monster");
	    			((DemoPlayer)player).setEnemyClicked(thisEnemy);
	    			doTargetAction();
	    		}
	    	}
		});
		actions = new ArrayList<>();
		
		//(String name, ImageView imageView, EffectTarget target, Adjustment adjustment, StatType statType, int value) 
		actions.add(new Action("Bite",actionImage, EffectTarget.CHARACTER, Adjustment.INCREMENTS, StatType.ATTACK, 6)); 
		ImageView blockImage = ImageLoader.load("images//enemies//lshield.png",false);
		actions.add(new Action("Block",blockImage, EffectTarget.CHARACTER, Adjustment.INCREMENTS, StatType.ARMOR, 6));
		currentAction = actions.get(actionIndex);
		initText();
	}
	
	public final Action getNextAction() {
		if (actionIndex==actions.size()) {
			actionIndex=0;
		}
		currentAction=actions.get(actionIndex);
		actionImage=currentAction.getImageView();
		actionIndex++;
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
		return health;
	}

	public final int getMaxHealth() {
		return maxHealth;
	}

	public final void decrementHealth(int thisAmount) {
		System.out.println("=====> Health: "+health+", Damage: "+thisAmount);
		if (thisAmount>0) {
			health-=thisAmount;
			if (health<0) {
				health=0;
			}
		}
		System.out.println("=====> Health: "+health);
	}
	
	public final void incrementHealth(int thisAmount) {
		if (thisAmount>0) {
			health-=thisAmount;
			if (health>maxHealth) {
				health=maxHealth;
			}
		}
	}
	
	public final Text getActionText() {
		return actionText;
	}
	
	public void setActionText() {
		actionText.setText(""+currentAction.getValue());
	}
	
	private void initText() {
		//healthText = TextUtil.initText(""+health, (int)healthImage.getLayoutX()+10, (int)healthImage.getLayoutY()+25);
		actionText = TextUtil.initText(""+currentAction.getValue(), (int)actionImage.getLayoutX()+10, (int)actionImage.getLayoutY()+25);
	}
	
	@Override
	public void updateScreenText() {
		super.setStatsText();
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
