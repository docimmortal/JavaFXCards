package application.utils;

import java.util.List;
import java.util.ArrayList;

import application.entities.Enemy;

public class EnemyList {

	private static List<Enemy> enemies;
	private static int enemyIndex;
	
	public static void setEnemies(List<Enemy> newEnemies) {
		enemies=newEnemies;
	}
	
	public static List<Enemy> getEnemies() {
		return enemies;
	}
	
	public static void addEnemy(Enemy enemy) {
		if (enemies == null) {
			enemies = new ArrayList<>();
		}
		enemies.add(enemy);
	}
	
	public static int size() {
		if (enemies==null) {
			enemies = new ArrayList<>();
		}
		return enemies.size();
	}
	
	public static Enemy getEnemy(int index) {
		Enemy enemy = null;
		if (enemies!=null && enemies.size()<index) {
			enemy=enemies.get(index);
		}
		return enemy;
	}
	
	public static Enemy getNextEnemy() {
		Enemy enemy = null;
		if (enemies!=null) {
			if (enemyIndex==enemies.size()) {
				enemyIndex=0;
			}
			enemy=enemies.get(enemyIndex++);
		}
		return enemy;
	}
	
	public static Enemy removeEnemy() {
		Enemy enemy = null;
		if (enemies!=null && enemies.size()>0) {
			enemy=enemies.remove(0);
		}
		return enemy;
	}
}
