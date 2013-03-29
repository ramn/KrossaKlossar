package se.ramn.krossaklossar.util

import java.awt.{Font => JFont}
import org.newdawn.slick.UnicodeFont
import org.newdawn.slick.font.effects.{Effect, ColorEffect}

object Font {
  lazy val default = build

  def build: UnicodeFont = {
    val font = new JFont("Courier", JFont.PLAIN, 14)
    val uFont = new UnicodeFont(font)
    uFont.addAsciiGlyphs()
    val fontEffects = uFont.getEffects.asInstanceOf[java.util.List[Effect]]
    val fontEffect = new ColorEffect(java.awt.Color.WHITE)
    fontEffects.add(fontEffect)
    uFont.loadGlyphs()
    uFont
  }
}

