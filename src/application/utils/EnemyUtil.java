package application.utils;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import application.card.effects.Adjustment;
import application.card.effects.EffectTarget;
import application.card.effects.StatType;
import application.entities.Action;
import application.entities.Enemy;
import application.fxcomponents.ImageLoader;
import application.player.entities.Player;
import javafx.scene.image.ImageView;

public class EnemyUtil {

	private static ImageView attackImage = ImageLoader.load("images//enemies//lattack.png",false);
	private static ImageView blockImage = ImageLoader.load("images//enemies//lshield.png",false);
	
	
	public static Enemy yarnBoy(Player player) {
		Enemy yarnBoy = new Enemy("images\\enemies\\Yarnboy.png",Main.ENEMY1_INDEX, player, 1100, 350, 15);
		List<Action> actions = new ArrayList<>(); 
		actions.add(new Action("Block",blockImage, EffectTarget.SELF, Adjustment.INCREMENTS, StatType.ARMOR, 6));
		actions.add(new Action("Bite",attackImage, EffectTarget.CHARACTER, Adjustment.INCREMENTS, StatType.ATTACK, 6));
		yarnBoy.setActions(actions);
		return yarnBoy;
	}
}
