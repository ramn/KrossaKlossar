package se.ramn.krossaklossar.entity

import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.Color
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import org.newdawn.slick.Music
import org.newdawn.slick.Image
import org.newdawn.slick.SpriteSheet
import org.newdawn.slick.geom.{Shape, Rectangle}
import org.newdawn.slick.Animation
import org.newdawn.slick.Input
import org.newdawn.slick.MouseListener
import org.newdawn.slick.geom.Shape
import org.newdawn.slick.geom.Vector2f

import se.ramn.krossaklossar.KrossaKlossar.{Width, Height}
import se.ramn.krossaklossar.util.MouseMovable 

class Paddle extends Collidable with MouseMovable {
  private val width = 100
  private val height = 20
  private val initX = Width/2-width/2
  private val initY = Height-height-20
  private val position = new Vector2f(initX, initY)

  private val shape = new Rectangle(initX, initY, width, height)

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {
    shape.setLocation(position)
  }

  def render(g: Graphics) {
    g.setColor(Color.blue)
    g.draw(shape)
  }

  override def collisionShape = shape

	override def mouseMoved(oldx: Int, oldy: Int, newx: Int, newy: Int) {
		if (newx > 10 && newx < 690) {
			position.x = newx
    }
  }
}
