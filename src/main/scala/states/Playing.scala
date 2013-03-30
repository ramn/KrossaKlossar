package se.ramn.krossaklossar.states

import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.Color
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import org.newdawn.slick.Music

import se.ramn.krossaklossar.util
import se.ramn.krossaklossar.level.Level
import se.ramn.krossaklossar.level.Level001
import se.ramn.krossaklossar.level.Level002
import se.ramn.krossaklossar.KrossaKlossar.Height


class Playing(val gameState: GameStates.Value) extends BasicGameState {
  private val music = new Music("music/explore_your_mind.mod")

  private var level: Level = _
  private var remainingLevels: Seq[Level] = _

  def getID: Int = gameState.id

  def init(gc: GameContainer, game: StateBasedGame) {
    music.loop()
  }

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {
    level.update(gc, game, delta)
    if (level.balls.isEmpty) {
      game.enterState(GameStates.GameOver.id)
    } else if (level.completed || gc.getInput.isKeyPressed(Input.KEY_N)) {
      remainingLevels match {
        case next :: rest =>
          level = next
          remainingLevels = rest
        case Nil =>
          game.enterState(GameStates.Victory.id)
      }
    }
  }

  def render(gc: GameContainer, game: StateBasedGame, g: Graphics) {
    level.render(g)
    g.setFont(util.Font.default)
    val textOffsetX = 15
    val textOffsetY = Height-20
    val scoreText = s"Score: ${level.points}"
    g.drawString(scoreText, textOffsetX, textOffsetY)
    g.drawString(s"Ball price: ${level.costOfNewBall}",
      textOffsetX + scoreText.length * 8 + textOffsetX,
      textOffsetY)
  }

  override def enter(gc: GameContainer, game: StateBasedGame) {
    super.enter(gc, game)
    val (first :: rest) = levels
    level = first
    remainingLevels = rest
  }

  private def levels = List(new Level001, new Level002)
}
