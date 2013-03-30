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
import org.newdawn.slick.geom.Vector2f

import se.ramn.krossaklossar.collision.Collidable
import Brick._


class Brick(position: Vector2f) extends Renderable with Collidable {
  val life = 1
  private val shape = new Rectangle(position.x, position.y, width, height)
  private var hits = 0

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {
  }

  def render(g: Graphics) {
    g.setColor(Color.gray)
    g.fill(shape)
  }

  def gotHit() {
    hits += 1
  }

  def isDestroyed: Boolean = hits >= life

  override def collisionShape = shape
}

object Brick {
  val width = 60
  val height = 22
}
