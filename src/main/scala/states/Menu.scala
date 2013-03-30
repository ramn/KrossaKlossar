package se.ramn.krossaklossar.states

import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.Color
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import org.newdawn.slick.Music
import org.newdawn.slick.Image
import org.newdawn.slick.SpriteSheet
import org.newdawn.slick.geom.{Shape, Rectangle}

import se.ramn.krossaklossar.util
import se.ramn.krossaklossar.Highscore


class Menu(val gameState: GameStates.Value) extends BasicGameState {
  lazy val font = util.Font.build

  def getID: Int = gameState.id

  def init(gc: GameContainer, game: StateBasedGame) {
  }

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {
    if (gc.getInput.isKeyPressed(Input.KEY_P)) {
      game.enterState(GameStates.Playing.id)
    } else if (gc.getInput.isKeyPressed(Input.KEY_Q)) {
      sys.exit
    }
  }

  def render(gc: GameContainer, game: StateBasedGame, g: Graphics) {
    g.setFont(font)
    val menu = s"""
    |Welcome to Krossa Klossar!
    |
    |Press p to play
    |Press q to quit
    |
    |
    |Gameplay
    |--------
    |Buy more balls by pressing b. This costs points!
    |Press z while the ball is hitting the paddle to increase ball speed
    |Press x while the ball is hitting the paddle to decrease ball speed
    |
    |
    |Highscore
    |---------
    |${Highscore.toString}
    |""".stripMargin.trim

    g.drawString(menu, 100, 100)
  }
}
