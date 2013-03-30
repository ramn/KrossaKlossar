package se.ramn.krossaklossar.entity

import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.Color
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import org.newdawn.slick.geom.{Shape, Rectangle}
import org.newdawn.slick.geom.Vector2f
import org.newdawn.slick.geom.GeomUtil

import se.ramn.krossaklossar.KrossaKlossar.{Width, Height}
import se.ramn.krossaklossar.collision.Collidable
import Wall.thickness

trait Wall extends Renderable with Collidable {
  def render(g: Graphics) {
    g.setColor(Color.white)
    g.fill(collisionShape)
  }
}

object Wall {
  val thickness = 10
}

class LeftWall extends Wall {
  override val collisionShape = new Rectangle(0, 0, thickness, Height)
}

class RightWall extends Wall {
  override val collisionShape = new Rectangle(Width-thickness, 0, thickness, Height)
}

class TopWall extends Wall {
  override val collisionShape = new Rectangle(0, 0, Width, thickness)
}
