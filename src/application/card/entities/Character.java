package application.card.entities;

import application.fxcomponents.ImageLoader;
import application.utils.TextUtil;
import javafx.scene.image.ImageView;
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

	private ImageView statsImage;
	
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
		
		statsImage = ImageLoader.load("images//characters//stats-images.png",false);
		statsImage.setLayoutX(140);
		statsImage.setLayoutY(getLowerYPlusOffset()-22);
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
		String healthStr=String.format("%3d",health);
		int hpLen=8;
		if (health>0)
			hpLen-=(int)Math.log(health);
		String spacing1=String.format("%"+hpLen+"s", "");
		String attackStr=String.format("%3d",attack);
		int attLen=7;
		if (attack>9)
			attLen-=1;
		if (attack>99)
			attLen-=1;
		System.out.println("--------------->AttLen:"+attLen);
		String spacing2=String.format("%"+attLen+"s", "");
		String armorStr=String.format("%3d",armor);
		String msg=healthStr+spacing1+attackStr+spacing2+armorStr;
		statsText = TextUtil.initText(msg, 140, getLowerYPlusOffset());
	}
	
	private int getLowerYPlusOffset() {
		return (int)getImageView().boundsInParentProperty().get().getMaxY()+25;
	}
	
	/*
	private int getLowerXCenterMinusOffset() {
		int maxX= (int)getImageView().boundsInParentProperty().get().getMaxX();
		int minX= (int)getImageView().boundsInParentProperty().get().getMinX();
		return minX+(maxX-minX)/2;
	}*/
	
	public final Text getStatsText() {
		return statsText;
	}
	
	public ImageView getStatsImage() {
		return statsImage;
	}
}
