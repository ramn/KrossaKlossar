package se.ramn.krossaklossar.entity

import org.newdawn.slick.geom.Vector2f
import org.newdawn.slick.Graphics
import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.StateBasedGame


trait LevelObject {
  var position: Vector2f

  def name: String

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {
  }

  def render(gc: GameContainer, game: StateBasedGame, g: Graphics) {
  }
}
