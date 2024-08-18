package application.buttons;

import application.fxcomponents.ScreenUtil;
import application.utils.EndTurnUtil;
import javafx.scene.Group;


public class EndTurnButton extends ImageButton {

	public EndTurnButton(Group myParent, String filename, int x, int y) {
		super(myParent, filename, x, y);
	}

	public void doAction() {
		EndTurnUtil endTurn = (EndTurnUtil)ScreenUtil.getNodeOfId(getMyParent(),"#EndTurnUtil");
		endTurn.doAction();
	}

}
