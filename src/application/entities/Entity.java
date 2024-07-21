package application.entities;

import java.util.HashMap;
import java.util.Map;

import application.card.effects.StatType;
import application.fxcomponents.ImageLoader;
import application.player.entities.Player;
import javafx.scene.image.ImageView;

public class Entity {

	private ImageView imageView;
	private Player player;
	private Map<StatType,Integer> statMap;
	
	public Entity(String filename, Player player, int x, int y) {
		imageView = ImageLoader.load(filename,false);
		imageView.setLayoutX(x);
		imageView.setLayoutY(y);
		this.player=player;
		statMap = new HashMap<>();
	}
	
	public final int get(StatType statType) {
		return statMap.get(statType);
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
	
	public final ImageView getImageView() {
		return imageView;
	}
	
	public final Player getPlayer() {
		return player;
	}
	
	// methods that can be overridden
	public void updateScreenText() {}

}
