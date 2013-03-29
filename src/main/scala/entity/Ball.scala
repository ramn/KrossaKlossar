package se.ramn.krossaklossar.entity

import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.Color
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import org.newdawn.slick.geom.{Shape, Circle}
import org.newdawn.slick.geom.Vector2f

import se.ramn.krossaklossar.KrossaKlossar.{Width, Height}
import se.ramn.krossaklossar.collision.Collidable


class Ball extends Collidable {
  private val radius = 4
  private val initX = Width/2-radius/2
  private val initY = Height/2
  private val initialSpeed = 0.1f
  private val speed = initialSpeed
  private val position = new Vector2f(initX, initY)
  private val shape: Shape = new Circle(initX, initY, radius)
  private val direction = new Vector2f(1.0f, 1.0f)

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {
    //position.x += direction.x * delta * speed
    //position.y -= direction.y * delta * speed
    position.add(direction.copy.scale(speed * delta))
    shape.setLocation(position)
  }

  def render(g: Graphics) {
    g.setColor(Color.red)
    g.fill(shape)
  }

  override def collisionShape = shape
}
