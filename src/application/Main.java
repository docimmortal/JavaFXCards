package application;
	
import java.util.ArrayList;
import java.util.List;

import application.entities.Enemy;
import application.buttons.EndTurnButton;
import application.buttons.ImageButton;
import application.buttons.StartButton;
import application.card.effects.StatType;
import application.card.entities.AnExtendedCard;
import application.fxcomponents.ImageLoader;
import application.player.entities.DemoPlayer;
import application.screens.SplashScreen;
import entities.card.Target;
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
	
	public static final int FIRST_CARD_INDEX=11;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		try {
			// Horizontal panes array
			panes = new ArrayList<>();
			Group group = new Group();
			player = new DemoPlayer(group); // new character created
			
			SplashScreen spl = new SplashScreen("map.jpg");
			
			spl.addButton(new StartButton("Button-start",1200,700, player, group, stage));
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
