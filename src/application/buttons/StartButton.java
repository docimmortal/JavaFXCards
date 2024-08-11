package application.buttons;

import application.entities.Enemy;
import application.fxcomponents.EraseUtil;
import application.fxcomponents.ImageLoader;
import application.player.entities.DemoPlayer;
import application.utils.EnemyList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StartButton extends ImageButton {

	private Text pointsText;
	private Text statsText;

	public StartButton(Group myParent, String filename, int x, int y) {
		super(myParent, filename, x, y);
		setId("StartButton");
	}
	
	public DemoPlayer getPlayer() {
		System.out.println("StartButton "+this.getParent().getChildrenUnmodifiable().size());
		return (DemoPlayer)getMyParent().lookup("#Player");
	}

	@Override
	public void doAction(){
		System.out.println("StartButton doAction");
		System.out.println(getMyParent().getId());
		int size=getMyParent().getChildren().size();
		for (int i=0; i<size;i++) {
			System.out.println(getMyParent().getChildren().get(i).getId());
		}
		if (EnemyList.size()==0) {
			System.out.println("NO ENEMIES DEFINED! Setting default enemy.");
			EnemyList.addEnemy(new Enemy(getMyParent(),"images\\enemies\\bunny.png","Enemy1", 1100, 300, 35));
		}
	
		Enemy enemy = new Enemy(EnemyList.getNextEnemy()); // remove first enemy from the list.
		DemoPlayer dp = getPlayer();
		System.out.println("StartButton DemoPlayer:"+dp);
		/* This works!
		Node node=myParent.lookup("#StartButton");
		if (node==null) {
			System.out.println("ERROR!");
		}
		myParent.getChildren().remove(node);
		dp.setEnemy(0,enemy);*/
		dp.getCharacter().resetAll();
		
		// display everything except cards;
		initScreen(this, dp, enemy);

		// Cards displayed should be the last thing since max hand size might not be the same as initial hand size

		// initialize deck and add the card images to the screen
		//initDeck(group);
		dp.getCharacter().setInitialDeck();
		dp.resetDeckHandDiscard();
		dp.drawAnInitialHand();

		// display cards
		// group: indexes 11-15 (change FIRST_CARD_INDEX if this changes).
		dp.addCardsToJavaFxDisplay(getMyParent());
		VBox pane = new VBox(1, new HBox(getMyParent()));
		Scene scene = new Scene(pane, 1500, 900);
		dp.getStage().setScene(scene);
		dp.getStage().show();
	}
	
	private void initScreen(Group group, DemoPlayer player, Enemy enemy) {
		System.out.println("StartButton initScreen: "+getMyParent().getId());
		EraseUtil.erase("#StartButton", getMyParent());
		EraseUtil.erase("#SplashScreen", getMyParent());
		System.out.println("\nAFTER");
		// Set background
		// group: index 0
		//group.getChildren().add(ImageLoader.load("images\\backgrounds\\woods2.jpg", false)); 
		putInGroup("Background",ImageLoader.load("images\\backgrounds\\woods2.jpg", false),group);
		
		// Add spell points text
		// group: index 1
		pointsText = player.getCharacter().getSpellpointsText();
		//group.getChildren().add(pointsText);
		putInGroup("PointsText", pointsText, group);

		// Add End Turn button - group: index 2
		ImageButton endTurnButton = new EndTurnButton(getMyParent(),"Button-EndTurn.jpg",1200,750);
		//group.getChildren().add(endTurnButton.getImageView());
		putInGroup("EndTurnButton", endTurnButton.getImageView(), group);

		// Add character image - group: index 3
		//group.getChildren().add(getPlayer().getCharacter().getImageView());
		putInGroup("PlayerImage", player.getCharacter().getEntityImage(), group);

		// Add character stats image - group: index 4
		//group.getChildren().add(player.getCharacter().getStatsImage());
		putInGroup("PlayerStatsImage", player.getCharacter().getStatsImage(),group);

		// Add character text - group: index 5
		statsText=player.getCharacter().getStatsText();
		//group.getChildren().add(statsText);
		putInGroup("PlayerStatsText", statsText,group);

		/* START ENEMY*/
		
		// Add enemies - group: index 6
		putInGroup("Enemy1", enemy,group);
		
		/* END ENEMY*/
		
		ImageButton discard = new DiscardButton(getMyParent(),1200,600);
		putInGroup("DiscardButton", discard.getImageView(),group);
	}
	
	public void debugGroup() {
		int size=getMyParent().getChildren().size();
		for (int i=0; i<size;i++) {
			System.out.println("==>"+getMyParent().getChildren().get(i).getId());
		}
	}

	private void putInGroup(String id, Node node, Group group) {
	
		node.setId(id);
		boolean found = (getMyParent().lookup("#"+id)==null)?false:true;
		//if (getMyParent().getChildren().size()<index+1) {
		if (!found) {
			getMyParent().getChildren().add(node);
		} else {
			Node g = getMyParent().lookup("#"+id);
			int index=getMyParent().getChildren().indexOf(g);
			getMyParent().getChildren().set(index, node);
		}
	}

}
