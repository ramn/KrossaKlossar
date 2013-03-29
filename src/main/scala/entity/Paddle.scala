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

class Paddle extends Collidable {
  private val width = 100
  private val height = 20
  private val initX = Width/2-width/2
  private val initY = Height-height-20
  private val position = new Vector2f(initX, initY)

  private val shape = new Rectangle(initX, initY, width, height)

  def update(gc: GameContainer, game: StateBasedGame, delta: Int) {
    updatePosition(gc)
  }

  def render(g: Graphics) {
    g.setColor(Color.white)
    g.fill(shape)
  }

  override def collisionShape = shape

  private def updatePosition(gc: GameContainer) {
    val margin = 10
    val leftStop = margin
    val rightStop = Width-width-margin
    val mouseX = gc.getInput.getMouseX
    if (mouseX > leftStop && mouseX < rightStop) {
      position.x = mouseX
    } else if (mouseX <= leftStop) {
      position.x = leftStop
    } else if (mouseX >= rightStop) {
      position.x = rightStop
    }
    shape.setLocation(position)
  }
}
