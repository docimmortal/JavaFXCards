package application;
	
import java.util.ArrayList;
import java.util.List;

import application.entities.Enemy;
import application.buttons.EndTurnButton;
import application.buttons.ImageButton;
import application.card.effects.StatType;
import application.card.entities.AnExtendedCard;
import application.fxcomponents.ImageLoader;
import application.player.entities.DemoPlayer;
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
	
	public static final int FIRST_CARD_INDEX=11;
	private static final int ENEMY1_ACTION_IMAGE_INDEX=9;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		try {
			// Horizontal panes array
			List<HBox> panes = new ArrayList<>();
			Group group = new Group();
			
			// initialize player and enemy
			player = new DemoPlayer(group);
			enemy = new Enemy("images\\enemies\\bunny.png",ENEMY1_ACTION_IMAGE_INDEX, player, 1100, 300, 15);
			player.setEnemy(enemy);
			
			// display everything except cards;
			initScreen(group);

			
			// Cards displayed should be the last thing since max hand size might not be the same as initial hand size
			
			// initialize deck and add the card images to the screen
			initDeck(group);
			drawCards(5);
			
			// display cards
			// group: indexes 11-15 (change FIRST_CARD_INDEX if this changes).
			player.addCardsToJavaFxDisplay(group);
			
			// Add everything to the panes
			panes.add(new HBox(group));
			
			// Final steps to render the scene
			VBox pane = new VBox(panes.size(), panes.toArray(new HBox[0]));
			Scene scene = new Scene(pane, 1500, 900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initScreen(Group group) {
		// Set background
		// group: index 0
		group.getChildren().add(ImageLoader.load("images\\backgrounds\\woods2.jpg", false)); 

		// Add spell points text
		// group: index 1
		pointsText = player.getCharacter().getSpellpointsText();
		group.getChildren().add(pointsText);

		// Add End Turn button - group: index 2
		ImageButton endTurnButton = new EndTurnButton("Button-EndTurn",1200,700, player, group);
		group.getChildren().add(endTurnButton.getImageView());

		// Add character image - group: index 3
		group.getChildren().add(player.getCharacter().getImageView());

		// Add character stats image - group: index 4
		group.getChildren().add(player.getCharacter().getStatsImage());

		// Add character text - group: index 5
		statsText=player.getCharacter().getStatsText();
		group.getChildren().add(statsText);

		// Add enemies - group: index 6
		group.getChildren().add(enemy.getImageView());

		// Add enemy health - group: index 7,8
		group.getChildren().add(enemy.getStatsImage());
		group.getChildren().add(enemy.getStatsText());

		// Add enemy action - group: index 9,10
		group.getChildren().add(enemy.getActionImage());
		group.getChildren().add(enemy.getActionText());
	}
	
	private void initDeck(Group group) {
		AnExtendedCard block = new AnExtendedCard("images\\cards\\block.jpg", Target.SELF, player, group, "Block", 1);
		block.set(StatType.ARMOR,5);
		AnExtendedCard punch = new AnExtendedCard("images\\cards\\punch.jpg",Target.ENEMY, player, group, "Punch", 1);
		punch.set(StatType.ATTACK,5);
		
		for(int i=0; i<10; i++) {
			AnExtendedCard newCard=null;
			// create only 2 cards (for now)
			if (i%2==0) {
				newCard=new AnExtendedCard(block);
				
			} else {
				newCard=new AnExtendedCard(punch);
			}
			player.addCardToDeck(newCard);
		}
	}
	
	private void drawCards(int handSize) {
		drawCards(handSize,handSize);
	}
	
	private void drawCards(int cards, int maxHandSize) {
		int count=0;
		while (player.handSize() < maxHandSize && count<cards) {
			player.drawACard();
			count++;
		}
	}

}
