package application;
	
import java.util.ArrayList;
import java.util.List;

import application.card.entities.EndTurnButton;
import application.card.entities.Enemy;
import application.card.entities.ImageButton;
import application.card.entities.AnExtendedCard;
import application.card.entities.DemoPlayer;
import application.fxcomponents.ImageLoader;
import entities.card.Target;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application {
	
	DemoPlayer player = new DemoPlayer();
	Text pointsText;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		try {
			player.setPoints(3);
			// Horizontal panes array
			List<HBox> panes = new ArrayList<>();
			Group group = new Group();
			group.getChildren().add(ImageLoader.load("images\\woods2.jpg", false)); 
			
			// Add any text
			pointsText = new Text("Points:"+((DemoPlayer)player).getPoints());
			pointsText.setLayoutX(70);
			pointsText.setLayoutY(70);
			pointsText.setFont(new Font(20));
			pointsText.setFill(Color.WHITE);
			group.getChildren().add(pointsText);
			
			// initialize deck and add the card images to the screen
			initDeck(group);
			drawCards(5);
			addItems(group);
			
			// Add buttons
			ImageButton ib = new EndTurnButton("Button-EndTurn",1200,700, player, group);
			group.getChildren().add(ib.getImageView());
			
			// Add enemies
			Enemy enemy = new Enemy("images\\bunny.png", player, 1100, 300);
			group.getChildren().add(enemy.getImageView());
			
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
	
	private void initDeck(Group group) {
		for(int i=0; i<10; i++) {
			AnExtendedCard newCard=null;
			// create only 2 cards (for now)
			if (i%2==0) {
				newCard=new AnExtendedCard("images\\block.jpg", Target.SELF, player, group, "Block", 1);
				newCard.setBlock(5);
				
			} else {
				newCard=new AnExtendedCard("images\\punch.jpg",Target.ENEMY, player, group, "Punch", 1);
				newCard.setDamage(5);
			}
			player.addCardToDeck(newCard);
		}
	}
	
	private void drawCards(int handSize) {
		while (player.handSize() < handSize) {
			player.drawACard();
		}
	}
	private void addItems(Group group) {
		for(int i=0; i<player.handSize(); i++) {
			ImageView imageView = player.viewCardInHand(i).getImageView();
			imageView.setLayoutX(50+200*i);
			imageView.setLayoutY(600);
			group.getChildren().add(imageView);
		}
	}

}
