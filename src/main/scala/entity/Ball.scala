package se.ramn.krossaklossar.entity

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


class Ball extends Collidable {

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {
  }

  def render(g: Graphics) {
  }

  override def collisionShape = ???
}
