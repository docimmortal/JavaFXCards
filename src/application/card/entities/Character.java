package application.card.entities;

import application.utils.TextUtil;
import javafx.scene.text.Text;

public class Character extends Entity {

	private int points;
	private int health;
	private int maxHealth;
	private int attack;
	private int armor;
	private Text pointsText;
	private Text statsText;
	private int maxPoints;
	
	public Character(String filename, Player player, int health, int attack, int armor, int points, int x, int y) {
		super(filename, player, x, y);
		this.points=points;
		pointsText = TextUtil.initText("Spell points: "+points, 70, 70);
		this.health=health;
		maxHealth=health;
		maxPoints=points;
		this.attack=attack;
		this.armor=armor;
		points=maxPoints;
		setStatsText();
	}
	
	public final int getHealth() {
		return health;
	}
	
	public final void decrementHealth(int amount) {
		if (amount>0) {
			health-=amount;
			if (health<0) {
				health=0;
			}
			setStatsText();
		}
	}
	
	public final void incrementHealth(int amount) {
		if (amount>0) {
			health+=amount;
			if (health>maxHealth) {
				health=maxHealth;
			}
			setStatsText();
		}
	}
	
	public final int getMaxHealth() {
		return maxHealth;
	}
	
	public final void setMaxHealth(int maxHealth) {
		this.maxHealth=maxHealth;
	}
	
	public final void healToFullHealth() {
		health=maxHealth;
		setStatsText();
	}
	
	public final int getArmor() {
		return armor;
	}
	
	public final void incrementArmor(int thisValue) {
		if (thisValue>0) {
			armor+=thisValue;
			setStatsText();
		}
	}
	
	public final void decrementArmor(int thisValue) {
		if (thisValue>0) {
			armor-=thisValue;
			setStatsText();
		}
	}
	
	public final void resetArmor() {
		armor=0;
		setStatsText();
	}
	

	public final int getAttack() {
		return attack;
	}
	
	public final void incrementAttack(int thisValue) {
		if (thisValue>0) {
			attack+=thisValue;
			setStatsText();
		}
	}
	
	public final void decrementAttack(int thisValue) {
		if (thisValue>0) {
			attack-=thisValue;
			setStatsText();
		}
	}
	
	public final void resetAttack() {
		attack=0;
		setStatsText();
	}

	public final int getPoints() {
		return points;
	}

	public final void setPoints(int points) {
		this.points = points;
		setPointsText();
	}
	
	public final void decrementPoints(int thesePoints) {
		if (thesePoints <= points) {
			points-=thesePoints;
			setPointsText();
		}
	}
	
	public final void incrementPoints(int thesePoints) {
		if (thesePoints > 0) {
			points+=thesePoints;
			setPointsText();
		}
	}
	
	public final void resetPoints() {
		points=maxPoints;
		setPointsText();
	}
	
	public final void resetAll() {
		points=maxPoints;
		armor=0;
		attack=0;
		setPointsText();
		setStatsText();
	}
	
	public final int getMaxPoints() {
		return maxPoints;
	}

	public final Text getSpellpointsText() {
		return pointsText;
	}

	public void setPointsText() {
		pointsText.setText("Spell points: "+points);
	}
	
	private void setStatsText() {
		String msg=health+"      "+armor+"       "+attack;
		statsText = TextUtil.initText(msg, getLowerXCenterMinusOffset(msg.length()), getLowerYPlusOffset());
	}
	
	private int getLowerYPlusOffset() {
		return (int)getImageView().boundsInParentProperty().get().getMaxY()+25;
	}
	
	private int getLowerXCenterMinusOffset(int strLen) {
		int maxX= (int)getImageView().boundsInParentProperty().get().getMaxX();
		int minX= (int)getImageView().boundsInParentProperty().get().getMinX()-(strLen*8);
		return minX+(maxX-minX)/2;
	}
	
	public final Text getStatsText() {
		return statsText;
	}

}
