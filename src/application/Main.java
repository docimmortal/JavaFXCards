package application;
	
import java.util.ArrayList;
import java.util.List;

import application.entities.Enemy;
import application.buttons.StartButton;
import application.player.entities.DemoPlayer;
import application.screens.SplashScreen;
import application.utils.EnemyList;
import application.utils.EnemyUtil;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	DemoPlayer player;
	List<HBox> panes;
	StartButton startButton;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		try {
			Group mainGroup = new Group();
			mainGroup.setId("Main");
			player = new DemoPlayer(mainGroup,stage); // new character created
			mainGroup.getChildren().add(player);
			
			EnemyList.addEnemy(EnemyUtil.yarnBoy(player,1));
			EnemyList.addEnemy(new Enemy(mainGroup,"images\\enemies\\bunny.png","Enemy1", 1100, 300, 10));

			SplashScreen spl = new SplashScreen("map.jpg");
			StartButton startButton = new StartButton(mainGroup,"Button-start.jpg",1200,700);		
			spl.addButton(startButton);
			mainGroup.getChildren().add(startButton);
			mainGroup.getChildren().add(spl);
			
			// Final steps to render the scene
			Scene scene = new Scene(new VBox(mainGroup), 1500, 900);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public StartButton getStartButton() {
		return startButton;
	}
	
	public void splashScreen(Group group) {
		SplashScreen splash = new SplashScreen("map.jpg");
		panes = new ArrayList<>();
		panes.set(0, new HBox(splash.getSplashScreen()));
	}

}
