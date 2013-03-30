package se.ramn.krossaklossar

import org.newdawn.slick.AppGameContainer
import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.StateBasedGame

import states.GameStates
import states.{Playing, Menu}


object KrossaKlossar extends App {
  val Width = 800
  val Height = 600
  val updateIntervalMs = 16

  val app = new AppGameContainer(new KrossaKlossar)
  app.setDisplayMode(Width, Height, false)
  app.setMinimumLogicUpdateInterval(updateIntervalMs)
  app.setMouseGrabbed(true)
  app.start()
}

class KrossaKlossar extends StateBasedGame("Objects in Space") {
  def initStatesList(gc: GameContainer) {
    this.addState(new Menu(GameStates.Menu))
    this.addState(new Playing(GameStates.Playing))
  }
}
