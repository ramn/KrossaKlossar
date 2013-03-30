package se.ramn.krossaklossar.level

import org.newdawn.slick.Graphics

import se.ramn.krossaklossar.entity._


object Level001 extends Level {
  val balls = List(new Ball)
  val paddle = new Paddle
  val bricks: Seq[Brick] = Nil
  val leftWall = new LeftWall
  val rightWall = new RightWall
  val topWall = new TopWall
}
