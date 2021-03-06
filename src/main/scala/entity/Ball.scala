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
  private val initialSpeed = 0.25f
  private val speedAdjustDelta = 0.03f
  private val position = new Vector2f(initX, initY)
  private val shape: Shape = new Circle(initX, initY, radius)
  private val direction = new Vector2f(-1.0f, 1.0f)
  private val killsRequiredPerLevel = 4
  private val maxLevel = 4

  private var speed = initialSpeed
  private var killCount = 0

  class CollisionHandler(movement: Vector2f, input: Input) {
    val increaseSpeed = input.isKeyDown(Input.KEY_Z)
    val decreaseSpeed = input.isKeyDown(Input.KEY_X)
    def handle(collidable: Collidable) = {
      handleCollisionsWith(collidable, movement.copy, increaseSpeed, decreaseSpeed)
    }
  }

  def update(gc: GameContainer, game: StateBasedGame, delta: Int, level: Level) {
    val movement = direction.copy.scale(speed * delta)
    position.add(movement)
    shape.setLocation(position)

    val collisionHandler = new CollisionHandler(movement, gc.getInput)
    val collidables = Seq(level.paddle, level.leftWall, level.rightWall, level.topWall) ++ level.bricks
    collidables foreach collisionHandler.handle
  }

  def render(g: Graphics) {
    val color = level match {
      case 1 => Color.white
      case 2 => Color.lightGray
      case 3 => Color.gray
      case 4 => Color.darkGray
      case _ => Color.pink
    }
    g.setColor(color)
    g.fill(shape)
  }

  override def collisionShape = shape

  def isDropped: Boolean = position.y >= Height

  def level = {
    val levelCandidate = 1 + (killCount / killsRequiredPerLevel)
    if (levelCandidate <= maxLevel)
      levelCandidate
    else
      maxLevel
  }

  def damage = level


  private def handleCollisionsWith(
    collidable: Collidable,
    movement: Vector2f,
    increaseSpeed: Boolean,
    decreaseSpeed: Boolean
  ): Unit =
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
          val width = radius * 2
          val onLeftEdge = position.x <= paddle.collisionShape.getMinX - width
          val onRightEdge = position.x > paddle.collisionShape.getMaxX
          if (onLeftEdge)
            bounceClockWise()
          else if (onRightEdge)
            bounceCounterClockWise()
          else if (currTheta > 90)
              bounceClockWise()
          else
              bounceCounterClockWise()
          paddle.ballBounced()
          if (increaseSpeed) {
            speed += speedAdjustDelta
          }
          if (decreaseSpeed && speed > 0) {
            speed -= speedAdjustDelta
          }
        case brick: Brick =>
          if (currTheta >= 270)
            bounceClockWise()
          else
            bounceCounterClockWise()
          if (!brick.isDestroyed) {
            brick.hit(damage)
            if (brick.isDestroyed) {
              killCount += 1
            }
          }
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
    direction.setTheta(
      operator(
        direction.getTheta,
        (direction.getTheta * 2) % 180))
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
