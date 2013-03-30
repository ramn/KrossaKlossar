package se.ramn.krossaklossar.level

import org.newdawn.slick.Graphics
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.GameContainer

import se.ramn.krossaklossar.entity._


trait Level {
  def balls: Seq[Ball]

  def balls_=(value: Seq[Ball])

  def paddle: Paddle

  def bricks: Seq[Brick]

  def leftWall: LeftWall

  def rightWall: RightWall

  def topWall: TopWall

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {
    balls foreach (_ update(gc, game, delta, this))
    paddle update (gc, game, delta)
    bricks foreach (_ update (gc, game, delta))

    balls = balls filterNot (_.isDropped)
  }

  def render(g: Graphics) {
    balls foreach (_ render g)
    entitiesNoBalls foreach (_ render g)
  }

  private def entitiesNoBalls: Seq[Renderable] =
    bricks ++ Seq(paddle, leftWall, rightWall, topWall)
}
