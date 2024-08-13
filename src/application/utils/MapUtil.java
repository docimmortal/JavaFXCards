package application.utils;

import java.util.ArrayList;
import java.util.List;

import application.buttons.MapLocation;
import application.entities.Enemy;
import application.fxcomponents.ScreenUtil;
import application.player.entities.DemoPlayer;
import application.screens.MapScreen;
import javafx.scene.Group;
import javafx.scene.Node;

public class MapUtil extends Group{

	private Group myParent;
	private MapScreen map;
	
	public MapUtil(Group myParent) {
		this.myParent=myParent;
		setId("MapUtil");
	}
	
	public void setMapScreen(MapScreen map) {
		this.map=map;
		map.setId("Map");
		Node lookup = myParent.lookup("#Map");
		if (lookup != null) {
			myParent.getChildren().remove(lookup);
		}
		myParent.getChildren().add(map);
	}
	
	public MapScreen getMapScreen() {
		return map;
	}
	
	public void setDefaultLocations() {
		DemoPlayer player = (DemoPlayer)ScreenUtil.getNodeOfId(myParent,"#Player");
		List<Enemy> enemies = new ArrayList<>();
		enemies.add(EnemyUtil.yarnBoy(player,1));
		enemies.add(EnemyUtil.yarnBoy(player,2));
		enemies.add(EnemyUtil.bunny(player,3));

		initMapLocation("s-woods.png","Woods1",770,670, enemies);
		
		enemies = new ArrayList<>();
		enemies.add(EnemyUtil.bunny(player,1));
		initMapLocation("s-woods.png","Woods2",700,650, enemies);
		
		enemies = new ArrayList<>();
		enemies.add(EnemyUtil.bunny(player,1));
		enemies.add(EnemyUtil.bunny(player,2));
		initMapLocation("s-woods.png","Woods3",620,650, enemies);
		
		enemies = new ArrayList<>();
		enemies.add(EnemyUtil.redCrab(player,1));
		initMapLocation("s-water.png","Ocean1",280,680, enemies);
		
		enemies = new ArrayList<>();
		enemies.add(EnemyUtil.bunny(player,1));
		initMapLocation("s-mount.png","Mount1",500,380, enemies);
	}
	
	public void initMapLocation(String imageFilename, String mapLoc, int x, int y, List<Enemy> enemies) {
		MapLocation mlb = new MapLocation(myParent, mapLoc,imageFilename,x,y, enemies);
		map.addMapLocation(mlb);
	}
	
}
