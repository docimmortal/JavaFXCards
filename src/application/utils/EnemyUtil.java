package application.utils;

import java.util.ArrayList;
import java.util.List;

import application.card.effects.Adjustment;
import application.card.effects.Target;
import application.card.effects.StatType;
import application.entities.Action;
import application.entities.Enemy;
import application.fxcomponents.ImageLoader;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class EnemyUtil {

	private static ImageView attackImage = ImageLoader.load("images//enemies//lattack.png",false);
	private static ImageView blockImage = ImageLoader.load("images//enemies//lshield.png",false);
	private static ImageView thinkingImage = ImageLoader.load("images//enemyActions//thinking.png",false);
	
	public static Enemy bunny(Group myParent, int enemyNumber) {
		Enemy bunny = new Enemy(myParent, "images\\enemies\\bunny.png","Enemy"+enemyNumber, 1100, 300, 10);		
		List<Action> actions = new ArrayList<>(); 
		actions.add(new Action("Block",new ImageView(blockImage.getImage()), Target.SELF, Adjustment.INCREMENTS, StatType.ARMOR, 2));
		actions.add(new Action("Bite",new ImageView(attackImage.getImage()), Target.CHARACTER, Adjustment.INCREMENTS, StatType.ATTACK, 3));
		bunny.initDefaultActions(actions);
		return bunny;
	}
	
	public static Enemy easyBunny(Group myParent, int enemyNumber) {
		Enemy ebunny = new Enemy(myParent, "images\\enemies\\bunny.png","Enemy"+enemyNumber, 1100, 300, 10);		
		List<Action> actions = new ArrayList<>(); 
		actions.add(new Action("Thinking",new ImageView(thinkingImage.getImage()), Target.SELF, Adjustment.INCREMENTS, StatType.THINKING, 0));
		actions.add(new Action("Bite",new ImageView(attackImage.getImage()), Target.CHARACTER, Adjustment.INCREMENTS, StatType.ATTACK, 3));
		ebunny.initDefaultActions(actions);
		return ebunny;
	}
	
	public static Enemy yarnBoy(Group myParent, int enemyNumber) {
		Enemy yarnBoy = new Enemy(myParent, "images\\enemies\\Yarnboy.png","Enemy"+enemyNumber, 1100, 350, 15);
		List<Action> actions = new ArrayList<>(); 
		actions.add(new Action("Block",new ImageView(blockImage.getImage()), Target.SELF, Adjustment.INCREMENTS, StatType.ARMOR, 4));
		actions.add(new Action("Bite",new ImageView(attackImage.getImage()), Target.CHARACTER, Adjustment.INCREMENTS, StatType.ATTACK, 5));
		yarnBoy.initDefaultActions(actions);
		return yarnBoy;
	}
	
	public static Enemy gingerbreadMan(Group myParent, int enemyNumber) {
		Enemy gbm = new Enemy(myParent, "images\\enemies\\gingerbreadman.png","Enemy"+enemyNumber, 1100, 150, 25);
		gbm.set(StatType.ARMOR, 5);
		System.out.println("GBM");
		gbm.setStatsText();
		List<Action> actions = new ArrayList<>(); 
		actions.add(new Action("Bite",new ImageView(attackImage.getImage()), Target.CHARACTER, Adjustment.INCREMENTS, StatType.ATTACK, 15));
		actions.add(new Action("Block",new ImageView(blockImage.getImage()), Target.SELF, Adjustment.INCREMENTS, StatType.ARMOR, 8));
		gbm.initDefaultActions(actions);
		return gbm;
	}
	
	public static Enemy redCrab(Group myParent, int enemyNumber) {
		Enemy crab = new Enemy(myParent, "images\\enemies\\crab.png","Enemy"+enemyNumber, 1000, 170, 75);
		List<Action> actions = new ArrayList<>(); 
		actions.add(new Action("Bite",new ImageView(attackImage.getImage()), Target.CHARACTER, Adjustment.INCREMENTS, StatType.ATTACK, 10));
		actions.add(new Action("Bite",new ImageView(attackImage.getImage()), Target.CHARACTER, Adjustment.INCREMENTS, StatType.ATTACK, 8));
		crab.initDefaultActions(actions);
		return crab;
	}
}
