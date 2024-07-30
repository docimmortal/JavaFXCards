package application;
	
import java.util.ArrayList;
import java.util.List;

import application.entities.Enemy;
import application.buttons.StartButton;
import application.player.entities.DemoPlayer;
import application.screens.SplashScreen;
import application.utils.EnemyUtil;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Main extends Application {
	
	DemoPlayer player;
	Text pointsText;
	Text statsText;
	Enemy enemy ;
	List<HBox> panes;
	
	public static final int ENEMY1_INDEX=6;
	
	public static final int FIRST_CARD_INDEX=9;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		try {
			// Horizontal panes array
			panes = new ArrayList<>();
			Group mainGroup = new Group();
			player = new DemoPlayer(mainGroup, stage); // new character created
			
			SplashScreen spl = new SplashScreen("map.jpg");
			StartButton start = new StartButton("Button-start.jpg",1200,700, player, mainGroup);
			player.setStartButton(start);
			
			List<Enemy> enemies = new ArrayList<>();			
			enemies.add(EnemyUtil.yarnBoy(player));
			enemies.add(new Enemy("images\\enemies\\bunny.png",ENEMY1_INDEX, player, 1100, 300, 10));
			start.setEnemies(enemies);
			
			spl.addButton(start);
			VBox vbox = spl.getSplashScreen();
			
			// Final steps to render the scene
			Scene scene = new Scene(new VBox(), 1500, 900);
			scene = new Scene(vbox, 1500, 900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void splashScreen(Group group) {
		SplashScreen splash = new SplashScreen("map.jpg");
		panes.set(0, new HBox(splash.getSplashScreen()));
	}

}
