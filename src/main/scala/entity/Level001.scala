package se.ramn.krossaklossar.entity

import org.newdawn.slick.Graphics


object Level001 extends Level {
	def balls = List(new Ball)
  val paddle = new Paddle
	def bricks: Seq[Brick] = Nil
}
