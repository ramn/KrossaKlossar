package se.ramn.krossaklossar.states

import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.Color
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input

import se.ramn.krossaklossar.util
import se.ramn.krossaklossar.level.Level
import se.ramn.krossaklossar.level.Level001
import se.ramn.krossaklossar.KrossaKlossar.Height


class Playing(val gameState: GameStates.Value) extends BasicGameState {
  private var level: Level = _

  def getID: Int = gameState.id

  def init(gc: GameContainer, game: StateBasedGame) {
  }

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {
    level.update(gc, game, delta)
    if (level.balls.isEmpty) {
      game.enterState(GameStates.GameOver.id)
    } else if (level.bricks.isEmpty) {
      game.enterState(GameStates.Victory.id)
    }
  }

  def render(gc: GameContainer, game: StateBasedGame, g: Graphics) {
    level.render(g)
    g.setFont(util.Font.default)
    g.drawString(s"Score: ${level.points}", 15, Height-15)
  }

  override def enter(gc: GameContainer, game: StateBasedGame) {
    super.enter(gc, game)
    level = new Level001
  }
}
