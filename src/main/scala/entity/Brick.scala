package se.ramn.krossaklossar.entity

import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.Color
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import org.newdawn.slick.Sound
import org.newdawn.slick.geom.{Shape, Rectangle}
import org.newdawn.slick.geom.Vector2f

import se.ramn.krossaklossar.collision.Collidable
import Brick._


class Brick(position: Vector2f) extends Renderable with Collidable {
  val life = 2
  val pointsWorth = 50

  private val shape = new Rectangle(position.x, position.y, width, height)
  private var damageTaken = 0

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {
  }

  def render(g: Graphics) {
    g.setColor(Color.gray)
    g.fill(shape)
  }

  def hit(damage: Int) {
    damageTaken += damage

    if (isDestroyed) {
      playDestroySound()
    } else {
      playHitSound()
    }
  }

  def isDestroyed: Boolean = damageTaken >= life

  override def collisionShape = shape
}

object Brick {
  val width = 60
  val height = 22

  private val hitSound = new Sound("sound/hitBrick.wav")
  private val destroySound = new Sound("sound/destroyBrick.wav")

  def playHitSound() {
    hitSound.play()
  }

  def playDestroySound() {
    destroySound.play()
  }
}

