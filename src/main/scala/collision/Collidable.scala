package se.ramn.krossaklossar.collision

import org.newdawn.slick.Image
import org.newdawn.slick.geom.Shape
import org.newdawn.slick.geom.Transform
import org.newdawn.slick.geom.Vector2f


trait Collidable {
  def collisionShape: Shape

  def collidesWith(collidable: Collidable): Boolean =
    collisionShape intersects collidable.collisionShape
}
