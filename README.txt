This uses JavaFX. Compiled with JDK19 and JavaFX 19.

Install e(fx)clipse from Eclipse Marketplace.
Add JavaFX and JavaFX SDK libraries and JavaFX libraries to classpath.

Update the .classpath file if JavaFX is somewhere other than C:/Users/Public/Java19.

Running:
Right click Main.java > Run As > Run Configurations
Add configuration for this project's Main.java.
VM arguments: --module-path C:\path-to-javafx\lib --add-modules=javafx.controls,javafx.fxml


Structure:
The Player object contains references to the deck, hand, discard, clicked card. The updateDiscardCardImage and isGameOver should be overwritten (see DemoPlayer). The shuffleTheDeck method can be overridden if you want to actually shuffle the discard pile before creating a new deck. 
The DemoPlayer extends that, adding enemy, clicked enemy (the enemy target if the user clicked it), points (for playing cards) and points text for displaying remaining points to screen.
The Card has the ImageView, whether it can be used, and a circular reference back to player. Methods checkUsability() and useTheCard() can be overwritten (see AnExtendedCard).
Enemy has an ImageView, a circular reference back to the player, and a reference to itself which is needed for the listener. Enemy will eventually have one or more methods than can be overridden.
SpecificEnemy will eventually overwrite methods in Enemy class, so it does specific actions.
ImageButton is for any clickable button. Can be extended for "exit game", "look for monster", "save:, etc. doAction() should be overwritten. See EndTurnButton for example. 

Playing:
Cards that target SELF only needs to be clicked. Other cards must be clicked followed by clicking a target.
Clicking a card makes the card not visible (not doing this will cause an error since the card would still be displayed and if clicked again, would throw an exception).
End turn resets available points.


Not implemented:
Redraw hand.
Shuffling cards.
Cards actually doing something.
Shuffle discards to create a new draw pile. (shuffle should reset canUse and isUsed to false and make cards visible again).
Enemy stats.
Enemy taking damage or doing actions. 
Player health or other stats (outside of points for playing cards).
Enemy or player dying. 
A way to find a new enemy.

Unresolved issues:
Clicking cards, the enemy, and End Turn should check to see if isGameOver is true before doing the click action. 
