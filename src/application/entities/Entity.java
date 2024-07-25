package application.entities;

import java.util.HashMap;
import java.util.Map;

import application.card.effects.StatType;
import application.fxcomponents.ImageLoader;
import application.player.entities.Player;
import application.utils.TextUtil;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Entity {

	private ImageView imageView;
	private Player player;
	private Map<StatType,Integer> statMap;
	
	private Text statsText;
	private ImageView statsImage;
	private int statsX;
	private int statsY;
	
	public Entity(String filename, Player player, int x, int y) {
		imageView = ImageLoader.load(filename,false);
		imageView.setLayoutX(x);
		imageView.setLayoutY(y);
		this.player=player;
		statMap = new HashMap<>();
		
		statsImage = ImageLoader.load("images//characters//stats-images.png",false);
		//statsImage.setLayoutX(140);
		statsX=x+(int)(getImageView().getImage().getWidth()/2)-(int)(statsImage.getImage().getWidth()/2);
		statsY=getLowerYPlusOffset()-12;
		statsImage.setLayoutX(statsX);
		statsImage.setLayoutY(statsY);
		setStatsText();
	}
	
	public final int get(StatType statType) {
		Integer value= statMap.get(statType);
		if (value==null) {
			value = Integer.valueOf(0);
		}
		//System.out.println("Entity get "+statType+" = "+value);
		return value;
	}
	
	public final void set(StatType statType, int value) {
		statMap.put(statType, value);
	}
	
	// set minAmount to -1 if there is no minAmount.
	public final void decrement(StatType statType, int amount, int minAmount) {
		if (amount>0) {
			int value=get(statType);
			value-=amount;
			if (minAmount>=0 && value<minAmount) {
				value=minAmount;
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
		/*int lAttack=get(StatType.ATTACK);
		String attackStr=String.format("%3d",lAttack);
		int attLen=7;
		if (lAttack>9)
			attLen-=1;
		if (lAttack>99)
			attLen-=1;
		String spacing2=String.format("%"+attLen+"s", "");*/
		int lArmor=get(StatType.ARMOR);
		String armorStr=String.format("%3d",lArmor);
		//String msg=healthStr+spacing1+attackStr+spacing2+armorStr;
		String msg=healthStr+spacing1+armorStr;
		statsText = TextUtil.initText(msg, statsX, statsY+25);
	}
	
	public final int getLowerYPlusOffset() {
		return (int)getImageView().boundsInParentProperty().get().getMaxY()+25;
	}
	
	public final int getLowerXCenter() {
		int maxX= (int)getImageView().boundsInParentProperty().get().getMaxX();
		int minX= (int)getImageView().boundsInParentProperty().get().getMinX();
		return minX+(maxX-minX)/2;
	}
	
	public final ImageView getImageView() {
		return imageView;
	}
	
	public final Player getPlayer() {
		return player;
	}
	
	// methods that can be overridden
	public void updateScreenText() {}

}
