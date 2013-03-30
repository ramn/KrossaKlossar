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
import se.ramn.krossaklossar.level.Level
import se.ramn.krossaklossar.level.Level001


class Playing(val gameState: GameStates.Value) extends BasicGameState {
  private var level: Level = _

  def getID: Int = gameState.id

  def init(gc: GameContainer, game: StateBasedGame) {
  }

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {
    level.update(gc, game, delta)
  }

  def render(gc: GameContainer, game: StateBasedGame, g: Graphics) {
    level.render(g)
  }

  override def enter(gc: GameContainer, game: StateBasedGame) {
    super.enter(gc, game)
    level = Level001
  }
}
