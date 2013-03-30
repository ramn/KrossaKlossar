package se.ramn.krossaklossar.level

import org.newdawn.slick.Graphics
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Input

import se.ramn.krossaklossar.entity._


trait Level {
  private var _points = 0

  def balls: Seq[Ball]

  protected def balls_=(value: Seq[Ball])

  def paddle: Paddle

  def bricks: Seq[Brick]

  protected def bricks_=(value: Seq[Brick])

  def leftWall: LeftWall

  def rightWall: RightWall

  def topWall: TopWall

  def points: Int = _points

  def costOfNewBall: Int

  def completed: Boolean = bricks.isEmpty

  protected def points_=(value: Int) {
    _points = value
  }

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {
    balls foreach (_ update(gc, game, delta, this))
    paddle update (gc, game, delta)
    bricks foreach (_ update (gc, game, delta))

    balls = balls filterNot (_.isDropped)
    points += (bricks filter (_.isDestroyed) map (_.pointsWorth)).sum
    bricks = bricks filterNot (_.isDestroyed)

    if (gc.getInput.isKeyPressed(Input.KEY_B) && points >= costOfNewBall) {
      addBall()
      points -= costOfNewBall
    }
  }

  def render(g: Graphics) {
    balls foreach (_ render g)
    entitiesNoBalls foreach (_ render g)
  }

  def addBall() {
    balls = new Ball +: balls
  }

  private def entitiesNoBalls: Seq[Renderable] =
    bricks ++ Seq(paddle, leftWall, rightWall, topWall)
}
