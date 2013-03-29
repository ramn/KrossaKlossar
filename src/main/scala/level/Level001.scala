package se.ramn.krossaklossar.level

import org.newdawn.slick.Graphics

import se.ramn.krossaklossar.entity.Ball
import se.ramn.krossaklossar.entity.Paddle
import se.ramn.krossaklossar.entity.Brick


object Level001 extends Level {
  val balls = List(new Ball)
  val paddle = new Paddle
  val bricks: Seq[Brick] = Nil
}
