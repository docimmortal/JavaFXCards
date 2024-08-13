package application.entities;

import application.card.effects.Adjustment;
import application.card.effects.Effect;
import application.card.effects.Target;
import application.card.effects.StatType;
import javafx.scene.image.ImageView;

public class Action extends Effect {
	
	private ImageView imageView;

	public Action(String name, ImageView imageView, Target target, Adjustment adjustment, StatType statType, int value) {
		super(name, target, adjustment, statType, value);
		this.imageView=imageView;
	}

	public final ImageView getImageView() {
		return imageView;
	}
}
