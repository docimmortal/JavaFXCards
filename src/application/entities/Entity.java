package application.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import application.card.effects.StatType;
import application.fxcomponents.ImageLoader;
import application.fxcomponents.TextUtil;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Entity extends Group{

	private ImageView entityImage;
	protected Map<StatType,Integer> statMap;
	
	private Text statsText;
	private ImageView statsImage;
	private int statsX;
	private int statsY;
	
	protected Group myParent;
	
	public Entity(Group myParent, String filename, int x, int y) {
		entityImage = ImageLoader.load(filename,false);
		entityImage.setLayoutX(x);
		entityImage.setLayoutY(y);
		statMap = new HashMap<>();
		this.myParent=myParent;
		
		initStatsImage(x);
		setStatsText();
	}
	
	private final void initStatsImage(int x) {
		statsImage = ImageLoader.load("images//characters//stats-images.png",false);
		statsX=x+(int)(getEntityImage().getImage().getWidth()/2)-(int)(statsImage.getImage().getWidth()/2);
		statsY=getLowerYPlusOffset()-12;
		statsImage.setLayoutX(statsX);
		statsImage.setLayoutY(statsY);
	}
	
	public final int get(StatType statType) {
		Integer value= statMap.get(statType);
		if (value==null) {
			value = Integer.valueOf(0);
		}
		return value;
	}
	
	public Group getMyParent() {
		return myParent;
	}
	
	public final void set(StatType statType, int value) {
		statMap.put(statType, value);
	}
	
	public final void debugAllStats() {
		Set<StatType> keys = statMap.keySet();
		for (StatType key:keys) {
			System.out.println(key+": "+statMap.get(key));
		}
	}
	
	// set minAmount to -1 if there is no minAmount.
	public final void decrement(StatType statType, int amount, int minAmount) {
		if (amount>0) {
			int initValue=get(statType);
			int value=initValue-amount;
			if (minAmount>=0 && value<minAmount) {
				value=minAmount;
			}
			if (value>initValue) { //underflow
				value=Integer.MIN_VALUE;
			}
			statMap.put(statType, value);
			updateScreenText();
		}
	}

	// set maxAmount to -1 if there is no maxAmount.
	public final void increment(StatType statType, int amount, int maxAmount) {
		if (amount>0) {
			int value=get(statType);
			value+=amount;
			if (maxAmount>0 && amount>maxAmount) {
				value=maxAmount;
			}
			if (value<0) {// overflow
				value=Integer.MAX_VALUE;
			}
			statMap.put(statType, value);
			updateScreenText();
		}
	}
	
	public final void resetToZero(StatType statType) {
		set(statType, 0);
		updateScreenText();
	}
	
	public final void resetTo(StatType statType, StatType newValue) {
		set(statType, get(newValue));
		updateScreenText();
	}
	
	public final ImageView getStatsImage() {
		return statsImage;
	}
	
	public Text getStatsText() {
		return statsText;
	}
	
	public void setStatsText() {
		int lHealth=get(StatType.HEALTH);
		String healthStr=String.format("%3d",lHealth);
		int hpLen=8;
		if (lHealth>0)
			hpLen-=(int)Math.log(lHealth);

		String spacing1=String.format("%"+hpLen+"s", "");

		int lArmor=get(StatType.ARMOR);
		String armorStr=String.format(" %3d",lArmor);

		String msg=healthStr+spacing1+armorStr;
		statsText = TextUtil.initText(msg, statsX, statsY+25);
	}
	
	public final int getLowerYPlusOffset() {
		return (int)getEntityImage().boundsInParentProperty().get().getMaxY()+25;
	}
	
	public final int getLowerXCenter() {
		int maxX= (int)getEntityImage().boundsInParentProperty().get().getMaxX();
		int minX= (int)getEntityImage().boundsInParentProperty().get().getMinX();
		return minX+(maxX-minX)/2;
	}
	
	public final ImageView getEntityImage() {
		return entityImage;
	}
	
	// methods that can be overridden
	public void updateScreenText() {}

}
