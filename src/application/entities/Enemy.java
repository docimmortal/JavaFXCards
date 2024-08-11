package application.entities;

import java.util.ArrayList;
import java.util.List;

import application.card.effects.Adjustment;
import application.card.effects.EffectTarget;
import application.card.effects.StatType;
import application.card.entities.RPGCard;
import application.card.entities.Card;
import application.fxcomponents.ImageLoader;
import application.fxcomponents.ScreenUtil;
import application.fxcomponents.TextUtil;
import application.player.entities.DemoPlayer;
import entities.card.Target;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class Enemy extends Entity{

	private Enemy thisEnemy;

	private String filename;
	private String enemyName;
	private ImageView actionImage;
	
	private Text actionText;
	
	private List<Action> actions;
	private Action currentAction;
	private int actionIndex;

	private int x;
	private int y;
	private int actionX;
	private int actionY;
	
	private int enemyNumber;
	
	public Enemy(Enemy enemy) {
		this(enemy.myParent,enemy.filename, enemy.enemyName, enemy.x, enemy.y, enemy.get(StatType.HEALTH));
	}
	
	public Enemy(Group myParent, String filename, String enemyName, int x, int y, int health) {
		super(myParent, filename, x, y);
		this.enemyName=enemyName;
		setId(enemyName);
		enemyNumber=1;
		this.x=x;
		this.y=y;
		thisEnemy=this;
		this.filename=filename;
		
		initDefaultActions();
		actionY=y-(int)actionImage.getImage().getHeight()-2;
		actionX=x+(int)(getEntityImage().getImage().getWidth()/2)-(int)(actionImage.getImage().getWidth()/2);
		
		set(StatType.HEALTH,health);
		set(StatType.MAX_HEALTH,health);
		
		
		displayFirstAction(x,y);
		
		// Add click event
		getEntityImage().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	    	@Override
	        public void handle(MouseEvent event) {
	    		if (canTarget()) {
	    			DemoPlayer dp = ((DemoPlayer)getMyParent().lookup("#Player"));
	    			System.out.println("Targetted "+thisEnemy.getId());
	    			dp.setEnemyClicked(thisEnemy);
	    			doTargetAction();
	    		}
	    	}
		});
		
		initText();
		debugAllStats();
		setStatsText();
		
		// Add enemies FX components
		getEntityImage().setId(enemyName+"-entityimage");
		this.getChildren().add(getEntityImage());
		
		getStatsImage().setId(enemyName+"-statsimage");
		this.getChildren().add(getStatsImage());
		
		getStatsText().setId(enemyName+"-statsText");
		this.getChildren().add(getStatsText());

		getActionImage().setId(enemyName+"-actionImage");
		this.getChildren().add(getActionImage());
		
		getActionText().setId(enemyName+"-actionText");
		this.getChildren().add(getActionText());
	}
	
	public int getEnemyNumber() {
		return enemyNumber;
	}
	
	public final int getX() {
		return x;
	}

	public final void resetXs(int x) {
		int diff=Math.abs(x-this.x);
		actionX=actionX-diff;
		this.x = x;
		getStatsImage().setLayoutX(getStatsImage().getLayoutX()-diff);
		actionImage.setLayoutX(actionX);
		getEntityImage().setLayoutX(getEntityImage().getLayoutX()-diff);
		getStatsText().setLayoutX(getStatsText().getLayoutX()-diff);
		getActionText().setLayoutX(getActionText().getLayoutX()-diff);
		setStatsX(getStatsX()-diff);
	}

	public final int getY() {
		return y;
	}

	public final void setY(int y) {
		this.y = y;
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
		actionImage.setId(enemyName+"-actionImage");
	}
	
	public void initDefaultActions(List<Action> actions) {
		this.actions = actions;
		currentAction = actions.get(0);
		actionImage=currentAction.getImageView();
		actionImage.setId(enemyName+"-actionImage");
	}
	
	public final void setStatsText() {
		super.setStatsText();
	}
	
	public final Text getStatsText() {
		return super.getStatsText();
	}
	
	public void displayFirstAction(int x, int y) {
		actionImage = actions.get(0).getImageView();
		actionImage.setId(enemyName+"-actionImage");
		setActionIndexXY();
		
		setActionIndexXY();
	}
	
	private void setActionIndexXY() {
		actionImage.setLayoutX(actionX);
		actionImage.setLayoutY(actionY);
	}
	
	public final Action getNextAction() {
		int index=ScreenUtil.getIndexOfId(this,"#"+enemyName+"-actionImage");
		actionIndex++;
		if (actionIndex==actions.size()) {
			actionIndex=0;
		}
		currentAction=actions.get(actionIndex);
		
		// update actionImage
		actionImage=currentAction.getImageView();
		actionImage.setId(enemyName+"-actionImage");
		setActionIndexXY();

		this.getChildren().set(index, actionImage);
		//Group group=((DemoPlayer)getPlayer()).getGroup();
		//group.getChildren().set(actionImageIndex, actionImage);		
		//this.getChildren().set(3, actionImage);
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
		actionImage.setId(enemyName+"-actionImage");
	}

	public final int getHealth() {
		return get(StatType.HEALTH);
	}

	public final int getMaxHealth() {
		return get(StatType.MAX_HEALTH);
	}

	// takes into account armor
	public final void decrementHealth(int damage) {
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
		actionText.setId(enemyName+"-actionText");
	}
	
	private void initText() {
		actionText = TextUtil.initText(""+currentAction.getValue(), (int)actionImage.getLayoutX()+10, (int)actionImage.getLayoutY()+25);
		actionText.setId(enemyName+"-actionText");
	}
	
	@Override
	public void updateScreenText() {
		setStatsText();
		setActionText();
	}

	private DemoPlayer getPlayer() {
		return (DemoPlayer)myParent.lookup("#Player");
	}
	/*
	 * Methods that can be overridden
	 */
	public boolean canTarget() {
		boolean canTarget=false;
		if (getPlayer().getCardClicked() != null && ((RPGCard)getPlayer().getCardClicked()).getTarget()==Target.ENEMY) {
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

	@Override
	public String toString() {
		return filename+" "+get(StatType.HEALTH);
	}
	
}
