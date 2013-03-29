package se.ramn.krossaklossar

import org.newdawn.slick.AppGameContainer
import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.StateBasedGame

import states.GameStates
import states.Playing


object KrossaKlossar extends App {
  val Width = 1440
  val Height = 832
  val updateIntervalMs = 16

  val app = new AppGameContainer(new KrossaKlossar)
  app.setDisplayMode(Width, Height, false)
  app.setMinimumLogicUpdateInterval(updateIntervalMs)
  app.start()
}

class KrossaKlossar extends StateBasedGame("Objects in Space") {
  def initStatesList(gc: GameContainer) {
    this.addState(new Playing(GameStates.Playing))
  }
}
