package se.ramn.krossaklossar.entity

import org.newdawn.slick.Graphics


object Level001 extends Level {
  val balls = List(new Ball)
  val paddle = new Paddle
  val bricks: Seq[Brick] = Nil
}
