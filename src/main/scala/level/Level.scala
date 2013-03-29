package se.ramn.krossaklossar.level

import org.newdawn.slick.Graphics
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.GameContainer

import se.ramn.krossaklossar.entity.Ball
import se.ramn.krossaklossar.entity.Paddle
import se.ramn.krossaklossar.entity.Brick


trait Level {
  def balls: Seq[Ball]

  def paddle: Paddle

  def bricks: Seq[Brick]

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {
    bricks foreach (_ update (gc, game, delta))

    balls foreach (_ update (gc, game, delta))

    paddle update (gc, game, delta)
  }

  def render(g: Graphics) {
    bricks foreach (_ render g)
    balls foreach (_ render g)
    paddle render g
  }
}
