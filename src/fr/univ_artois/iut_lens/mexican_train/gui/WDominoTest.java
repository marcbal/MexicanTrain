/**
 * 
 */
package fr.univ_artois.iut_lens.mexican_train.gui;

import org.jsfml.system.Vector2f;

import fr.univ_artois.iut_lens.mexican_train.game.Domino;
import junit.framework.TestCase;

/**
 * @author Marc Baloup
 *
 */
public class WDominoTest extends TestCase {

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.gui.WDomino#isClicked()}.
	 * Il s'agit d'une méthode en rapport avec
	 */
	public void testIsClicked() {
		WDomino d = new WDomino(new Vector2f(0, 0), new Vector2f(0, 0), true, new Domino(0, 0), false, true);
		assertFalse(d.isClicked());
		d.unclick();
		assertFalse(d.isClicked());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.gui.WDomino#getDomino()}.
	 */
	public void testGetDomino() {
		Domino d = new Domino(6, 9);
		WDomino wd = new WDomino(new Vector2f(0, 0), new Vector2f(0, 0), true, d, false, true);
		assertEquals(d, wd.getDomino());
		Domino d2 = new Domino(1, 8);
		wd.setDomino(d2);
		assertEquals(d2, wd.getDomino());
	}

}
