package se.ramn.krossaklossar.level

import org.newdawn.slick.Graphics

import se.ramn.krossaklossar.entity._


class Level001 extends Level {
  var balls = Seq(new Ball)
  val paddle = new Paddle
  val bricks: Seq[Brick] = Nil
  val leftWall = new LeftWall
  val rightWall = new RightWall
  val topWall = new TopWall
}
