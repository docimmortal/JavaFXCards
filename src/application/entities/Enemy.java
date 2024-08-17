package application.entities;

import java.util.List;

import application.card.effects.StatType;
import application.card.effects.Target;
import application.card.entities.RPGCard;
import application.card.entities.Card;
import application.fxcomponents.TextUtil;
import application.player.entities.DemoPlayer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class Enemy extends Entity{

	private Enemy thisEnemy;

	private String filename;
	private String enemyName;
	
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
		
		//initDefaultActions();
		
		set(StatType.HEALTH,health);
		set(StatType.MAX_HEALTH,health);
		setStatsText();
		
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
		
		//initText();
		//debugAllStats();
		
		// Add enemies FX components
		getEntityImage().setId(enemyName+"-entityimage");
		this.getChildren().add(getEntityImage());
		
		getStatsImage().setId(enemyName+"-statsimage");
		this.getChildren().add(getStatsImage());
		
		getStatsText().setId(enemyName+"-statsText");
		System.out.println("STATS TEXT:"+getStatsText().getText());
		this.getChildren().add(getStatsText());
		
		//NEW!!!!
		myParent.getChildren().add(this);
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
		getActionImage().setLayoutX(actionX);
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
	
	public void initDefaultActions(List<Action> actions) {
		this.actions = actions;
		currentAction = actions.get(0);
		getActionImage().setId(enemyName+"-actionImage");
		actionY=y-(int)getActionImage().getImage().getHeight()-2;
		actionX=x+(int)(getEntityImage().getImage().getWidth()/2)-(int)(getActionImage().getImage().getWidth()/2);
		setActionImageIndexXY();
		
		//NEW
		initText();
		getActionText().setId(enemyName+"-actionText");
		this.getChildren().add(getActionImage());
		this.getChildren().add(getActionText());

		displayFirstAction(x,y);
	}
	
	public final void setStatsText() {
		super.setStatsText();
		if (this.getChildren().size()==3) {
			this.getChildren().set(2, getStatsText());
		} else {
			System.out.println(this.getChildren().size());
		}
	}
	
	public final Text getStatsText() {
		Text text=super.getStatsText();
		text.setId(this.getId()+"-statsText");
		return super.getStatsText();
	}
	
	public void displayFirstAction(int x, int y) {
		getActionImage().setId(enemyName+"-actionImage");
		setActionImageIndexXY();
	}
	
	private void setActionImageIndexXY() {
		getActionImage().setLayoutX(actionX);
		getActionImage().setLayoutY(actionY);
	}
	
	public final Action getNextAction() {
		System.out.println("==============[getNextAction]=========");
		System.out.println(this.getId());
		for(Node node:this.getChildren()) {
			System.out.println(node.getId());
		}
		//int index=ScreenUtil.getIndexOfId(this,"#"+enemyName+"-actionImage");
		actionIndex++;
		if (actionIndex==actions.size()) {
			actionIndex=0;
		}
		currentAction=actions.get(actionIndex);
		setActionText();
		
		// update actionImage
		getActionImage().setId(enemyName+"-actionImage");
		setActionImageIndexXY();

		//this.getChildren().set(index, getActionImage());
		this.getChildren().set(3, getActionImage());
		
		//index=ScreenUtil.getIndexOfId(this,"#"+enemyName+"-actionText");
		//this.getChildren().set(index, actionText);
		this.getChildren().set(4, actionText);
		
		return currentAction;
	}
	
	public final Action getCurrentAction() {
		return currentAction;
	}
	
	public final List<Action> getActions() {
		return actions;
	}

	public final ImageView getActionImage() {
		return currentAction.getImageView();
	}

	public final void setActionImage(ImageView actionImage) {
		getActionImage().setImage(actionImage.getImage());
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
		actionText.setId(enemyName+"-actionText");
		return actionText;
	}
	
	public void setActionText() {
		int value=currentAction.getValue();
		if (value>0) {
			actionText.setText(""+currentAction.getValue());
		} else {
			actionText.setText("");
		}
		actionText.setId(enemyName+"-actionText");
	}
	
	private void initText() {
		actionText = TextUtil.initText(""+currentAction.getValue(), (int)getActionImage().getLayoutX()+10, (int)getActionImage().getLayoutY()+25);
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
