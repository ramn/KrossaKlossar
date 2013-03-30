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
import se.ramn.krossaklossar.level.Level


class Ball extends Renderable with Collidable {
  private val radius = 4
  private val initX = Width/2-radius/2
  private val initY = Height/2
  private val initialSpeed = 0.35f
  private val speed = initialSpeed
  private val position = new Vector2f(initX, initY)
  private val shape: Shape = new Circle(initX, initY, radius)
  private val direction = new Vector2f(-1.0f, 1.0f)

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {}
  def update(gc: GameContainer, game: StateBasedGame, delta: Int, level: Level) {
    val movement = direction.copy.scale(speed * delta)
    position.add(movement)
    shape.setLocation(position)

    handleCollisionsWith(level.paddle, movement)
    handleCollisionsWith(level.leftWall, movement)
    handleCollisionsWith(level.rightWall, movement)
    handleCollisionsWith(level.topWall, movement)
    level.bricks foreach { brick => handleCollisionsWith(brick, movement) }
  }

  def render(g: Graphics) {
    g.setColor(Color.white)
    g.fill(shape)
  }

  override def collisionShape = shape

  def isDropped: Boolean = position.y >= Height


  // @return true if collided
  private def handleCollisionsWith(collidable: Collidable, movement: Vector2f): Unit =
    if (collidable collidesWith this) {
      val moveDirBack = movementBackwards(movement)
      var timesMovedBack = 0
      do {
        timesMovedBack += 1
        position.add(moveDirBack)
        shape.setLocation(position)
      } while (collidable collidesWith this)

      val currTheta = direction.getTheta

      collidable match {
        case c: RightWall =>
          if (currTheta < 90)
            bounceClockWise()
          else
            bounceCounterClockWise()
        case c: TopWall =>
          if (currTheta >= 270)
            bounceClockWise()
          else
            bounceCounterClockWise()
        case c: LeftWall =>
          if (currTheta < 180)
            bounceCounterClockWise()
          else
            bounceClockWise()
        case paddle: Paddle =>
          val width = radius*2
          val onLeftEdge = position.x <= paddle.collisionShape.getMinX-width
          val onRightEdge = position.x > paddle.collisionShape.getMaxX
          if (onLeftEdge)
            bounceClockWise()
          else if (onRightEdge)
            bounceCounterClockWise()
          else if (currTheta > 90)
              bounceClockWise()
          else
              bounceCounterClockWise()
        case brick: Brick =>
          if (currTheta >= 270)
            bounceClockWise()
          else
            bounceCounterClockWise()
          brick.gotHit()
      }
      val moveForwardInNewDir = movementBackwards(moveDirBack)
      for (i <- 0 until timesMovedBack) {
        position.add(moveForwardInNewDir)
        shape.setLocation(position)
      }
    }

  private def bounce(clockWise: Boolean) {
    val operator: (Double, Double) => Double =
      if (clockWise) (a, b) => a + b
      else (a, b) => a - b
    direction.setTheta(operator(direction.getTheta, (direction.getTheta*2)%180))
  }

  private def bounceClockWise() {
    bounce(clockWise=true)
  }

  private def bounceCounterClockWise() {
    bounce(clockWise=false)
  }

  private def movementBackwards(movement: Vector2f): Vector2f = {
    val moveDirBack = movement.copy
    moveDirBack.setTheta(moveDirBack.getTheta + 180)
    moveDirBack.scale(0.01f)
  }
}
