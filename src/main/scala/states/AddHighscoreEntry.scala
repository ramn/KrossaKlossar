package se.ramn.krossaklossar.states

import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import org.newdawn.slick.gui.TextField

import se.ramn.krossaklossar.util


class AddHighscoreEntry(val gameState: GameStates.Value) extends BasicGameState {
  lazy val font = util.Font.build

  private var textField: TextField = _
  private var textCollected = false
  private var text = ""

  def getID: Int = gameState.id

  def init(gc: GameContainer, game: StateBasedGame) {
  }

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {
    if (gc.getInput.isKeyPressed(Input.KEY_ENTER)) {
      game.enterState(GameStates.GameOver.id)
    }
  }

  def render(gc: GameContainer, game: StateBasedGame, g: Graphics) {
    textField.render(gc, g)
  }

  override def enter(gc: GameContainer, game: StateBasedGame) {
    super.enter(gc, game)
    textCollected = false
    textField = buildTextField(gc)
  }

  override def leave(gc: GameContainer, game: StateBasedGame) {
    super.leave(gc, game)
    text = textField.getText
  }

  def collectText(): String =
    if (!textCollected) {
      textCollected = true
      text
    } else
      ""

  private def buildTextField(gc: GameContainer): TextField = {
    val textField = new TextField(gc, font, 200, 200, 300, 50)
    textField.setFocus(true)
    textField.setConsumeEvents(true)
    textField
  }
}
