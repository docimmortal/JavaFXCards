package application.buttons;

import java.util.List;

import application.entities.Enemy;
import application.fxcomponents.ImageLoader;
import application.fxcomponents.ScreenUtil;
import application.player.entities.DemoPlayer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MapLocation extends ImageButton {

	private Text pointsText;
	private Text statsText;
	private List<Enemy> enemies;
	private String mapLocId;

	public MapLocation(Group myParent, String mapLocId, String filename, int x, int y, List<Enemy> enemies) {
		super(myParent, "images//mapLocs//",filename, x, y);
		setId("StartButton");
		this.enemies=enemies;
		this.mapLocId=mapLocId;
		setId(mapLocId);
		getImageView().setId(mapLocId+"-image");
	}
	
	public String getMapLocId() {
		return mapLocId;
	}
	
	public DemoPlayer getPlayer() {
		return (DemoPlayer)getMyParent().lookup("#Player");
	}

	@Override
	public void doAction(){
		if (enemies==null || enemies.size()==0) {
			System.out.println("NO ENEMIES DEFINED! Setting default enemy.");
		}
	
		DemoPlayer dp=getPlayer();

		dp.getCharacter().resetAll();
		
		// display everything except cards;
		initScreen(this, dp);//, enemy);

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
	
	private void initScreen(Group group, DemoPlayer player) {//, Enemy enemy) {
		putInGroup("Background",ImageLoader.load("images\\backgrounds\\woods2.jpg", false));
		
		pointsText = player.getCharacter().getSpellpointsText();
		putInGroup("PointsText", pointsText);


		ImageButton endTurnButton = new EndTurnButton(getMyParent(),"Button-EndTurn.jpg",1200,750);
		putInGroup("EndTurnButton", endTurnButton.getImageView());

		putInGroup("PlayerImage", player.getCharacter().getEntityImage());

		putInGroup("PlayerStatsImage", player.getCharacter().getStatsImage());

		statsText=player.getCharacter().getStatsText();
		putInGroup("PlayerStatsText", statsText);

		/* START ENEMY*/
		for (int i=0; i<enemies.size();i++) {
			Enemy enemy=enemies.get(i);
			int x=(int) enemy.getX();
			x=x-(i*200);
			enemy.resetXs(x);
			putInGroup("Enemy"+(i+1), enemy);
		}
		
		/* END ENEMY*/
		
		ImageButton discard = new DiscardButton(getMyParent(),1200,600);
		putInGroup("DiscardButton", discard.getImageView());
	}
	
	public void debugGroup() {
		int size=getMyParent().getChildren().size();
		for (int i=0; i<size;i++) {
			System.out.println("==>"+getMyParent().getChildren().get(i).getId());
		}
	}

	private void putInGroup(String id, Node node) {
		node.setId(id);
		int index=ScreenUtil.getIndexOfId(myParent, "#"+id);
		if (index<0) {
			System.out.println("*******ERROR MapLocation putInGroup "+id+" not found");
			getMyParent().getChildren().add(node);
		} else {
			getMyParent().getChildren().set(index, node);
		}
	}

}
