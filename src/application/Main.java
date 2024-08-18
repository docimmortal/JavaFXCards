package application;
	
import java.util.ArrayList;
import java.util.List;

import application.buttons.StartButton;
import application.card.entities.Card;
import application.entities.Character;
import application.player.entities.DemoPlayer;
import application.screens.SplashScreen;
import application.utils.DeckUtil;
import application.utils.EndTurnUtil;
import application.utils.MapUtil;
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
			List<Card> initialDeck = DeckUtil.getInitialDeck(mainGroup);
			Character character = new Character(mainGroup,"images\\characters\\wizard-point-tp.png",initialDeck,20,0,0,3,100,100);
			player = new DemoPlayer(mainGroup,stage); // new character created
			player.setCharacter(character);
			mainGroup.getChildren().add(player);
			
			MapUtil mu = new MapUtil(mainGroup);
			mainGroup.getChildren().add(mu);
			
			// We externalized the end turn logic
			EndTurnUtil endTurn = new EndTurnUtil(mainGroup);
			mainGroup.getChildren().add(endTurn);
			
			SplashScreen spl = new SplashScreen("TwoWizards.jpg");
			StartButton startButton = new StartButton(mainGroup,"Button-start.png",1200,700);		
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
