/**
 * 
 */
package fr.univ_artois.iut_lens.mexican_train.gui;

import org.jsfml.system.Vector2f;

import junit.framework.TestCase;

/**
 * @author Marc Baloup
 *
 */
public class WidgetTest extends TestCase {

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.gui.Widget#getThemeFont()}.
	 */
	public void testGetThemeFont() {
		assertNotNull(Widget.getThemeFont());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.gui.Widget#isEnabled()}.
	 */
	public void testIsEnabled() {
		Widget w1 = new Widget(new Vector2f(0, 0), new Vector2f(0, 0), true);
		Widget w2 = new Widget(new Vector2f(0, 0), new Vector2f(0, 0), false);
		assertTrue(w1.isEnabled());
		assertFalse(w2.isEnabled());
		w2.setEnabled(true);
		w1.setEnabled(false);
		assertTrue(w2.isEnabled());
		assertFalse(w1.isEnabled());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.gui.Widget#getPosition()}.
	 */
	public void testGetPosition() {
		Vector2f v = new Vector2f(6, 17.3f);
		Vector2f v2 = new Vector2f(12.78f, 16);
		Widget w = new Widget(v, new Vector2f(0, 0), true);
		assertEquals(v, w.getPosition());
		w.setPosition(v2);
		assertEquals(v2, w.getPosition());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.gui.Widget#getSize()}.
	 */
	public void testGetSize() {
		Vector2f v = new Vector2f(6, 17.3f);
		Vector2f v2 = new Vector2f(12.78f, 16);
		Widget w = new Widget(new Vector2f(0, 0), v, true);
		assertEquals(v, w.getSize());
		w.setSize(v2);;
		assertEquals(v2, w.getSize());
	}

}
