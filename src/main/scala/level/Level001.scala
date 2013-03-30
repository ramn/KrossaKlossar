package se.ramn.krossaklossar.level

import org.newdawn.slick.Graphics
import org.newdawn.slick.geom.Vector2f

import se.ramn.krossaklossar.entity._


class Level001 extends Level {
  var balls = Seq(new Ball)
  val paddle = new Paddle
  val leftWall = new LeftWall
  val rightWall = new RightWall
  val topWall = new TopWall
  val costOfNewBall = 500
  var bricks: Seq[Brick] = {
    val offset = 10
    val padding = 5
    for {
      row <- 1 to 6
      col <- 1 to 10
    } yield new Brick(
      new Vector2f(
        offset + col * (Brick.width + padding),
        offset + row * (Brick.height + padding)))
  }
}
