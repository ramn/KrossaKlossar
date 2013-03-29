package se.ramn.krossaklossar.util

import org.newdawn.slick.MouseListener


trait MouseMovable extends MouseListener {
	def mouseMoved(oldx: Int, oldy: Int, newx: Int, newy: Int): Unit

  def inputEnded(): Unit = {}
  def inputStarted(): Unit = {}
  def isAcceptingInput(): Boolean = true
  def setInput(x$1: org.newdawn.slick.Input): Unit = {}
  def mouseClicked(x$1: Int,x$2: Int,x$3: Int,x$4: Int): Unit = {}
  def mouseDragged(x$1: Int,x$2: Int,x$3: Int,x$4: Int): Unit = {}
  def mousePressed(x$1: Int,x$2: Int,x$3: Int): Unit = {}
  def mouseReleased(x$1: Int,x$2: Int,x$3: Int): Unit = {}
  def mouseWheelMoved(x$1: Int): Unit = {}
}
