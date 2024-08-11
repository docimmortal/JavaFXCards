package application.fxcomponents;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class UpdateUtil {

	public static void updateGroupText(Group myParent, String id, Text text) {
		int index=ScreenUtil.getIndexOfId(myParent, id);
		System.out.println("===================[START of updateGroupText]=========");
		System.out.println("updateGroupText for "+id+" to '"+text.getText()+'"');
		myParent.getChildren().set(index, text);
		for (Node node:myParent.getChildren()) {
			System.out.println(node.getId());
		}
		System.out.println("Index: "+index);
		System.out.println("===================[End of updateGroupText]=========\n\n");
	}
	
}
