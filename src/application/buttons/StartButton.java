package application.buttons;

import application.entities.Enemy;
import application.fxcomponents.ImageLoader;
import application.player.entities.Player;
import application.player.entities.DemoPlayer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StartButton extends ImageButton {

	private DemoPlayer player;
	private Text pointsText;
	private Text statsText;
	private Enemy enemy;
	private Group group;
	
	private static final int ENEMY1_ACTION_IMAGE_INDEX=9;

	public StartButton(String filename, int x, int y, Player player, Group group) {
		super(filename, x, y, player);
		this.player=(DemoPlayer)player;
		this.group=group;
	}

	@Override
	public void doAction() {
		
		enemy = new Enemy("images\\enemies\\bunny.png",ENEMY1_ACTION_IMAGE_INDEX, player, 1100, 300, 15);
		player.setEnemy(enemy);
		
		// display everything except cards;
		initScreen(group);

		// Cards displayed should be the last thing since max hand size might not be the same as initial hand size

		// initialize deck and add the card images to the screen
		//initDeck(group);
		player.getCharacter().setInitialDeck();
		player.resetDeckHandDiscard();
		drawCards(5);

		// display cards
		// group: indexes 11-15 (change FIRST_CARD_INDEX if this changes).
		player.addCardsToJavaFxDisplay(group);
		VBox pane = new VBox(1, new HBox(group));
		Scene scene = new Scene(pane, 1500, 900);
		player.getStage().setScene(scene);
		player.getStage().show();
	}


	private void initScreen(Group group) {
		// Set background
		// group: index 0
		//group.getChildren().add(ImageLoader.load("images\\backgrounds\\woods2.jpg", false)); 
		putInGroup(0, ImageLoader.load("images\\backgrounds\\woods2.jpg", false),group);
		
		// Add spell points text
		// group: index 1
		pointsText = getPlayer().getCharacter().getSpellpointsText();
		//group.getChildren().add(pointsText);
		putInGroup(1,pointsText, group);

		// Add End Turn button - group: index 2
		ImageButton endTurnButton = new EndTurnButton("Button-EndTurn.jpg",1200,700, player, group);
		//group.getChildren().add(endTurnButton.getImageView());
		putInGroup(2,endTurnButton.getImageView(), group);

		// Add character image - group: index 3
		//group.getChildren().add(getPlayer().getCharacter().getImageView());
		putInGroup(3,getPlayer().getCharacter().getImageView(), group);

		// Add character stats image - group: index 4
		//group.getChildren().add(player.getCharacter().getStatsImage());
		putInGroup(4,player.getCharacter().getStatsImage(),group);

		// Add character text - group: index 5
		statsText=player.getCharacter().getStatsText();
		//group.getChildren().add(statsText);
		putInGroup(5,statsText,group);

		// Add enemies - group: index 6
		//group.getChildren().add(enemy.getImageView());
		putInGroup(6,enemy.getImageView(),group);

		// Add enemy health - group: index 7,8
		//group.getChildren().add(enemy.getStatsImage());
		putInGroup(7,enemy.getStatsImage(),group);
		//group.getChildren().add(enemy.getStatsText());
		putInGroup(8,enemy.getStatsText(),group);

		// Add enemy action - group: index 9,10
		//group.getChildren().add(enemy.getActionImage());
		putInGroup(9,enemy.getActionImage(),group);
		//group.getChildren().add(enemy.getActionText());
		putInGroup(10,enemy.getActionText(),group);
	}

	private void putInGroup(int index, Node node, Group group) {
	
		if (group.getChildren().size()<index+1) {
			group.getChildren().add(node);
		} else {
			group.getChildren().set(index, node);
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
