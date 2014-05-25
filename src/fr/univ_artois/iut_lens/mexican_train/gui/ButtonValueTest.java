/**
 * 
 */
package fr.univ_artois.iut_lens.mexican_train.gui;

import junit.framework.TestCase;

/**
 * @author Marc Baloup
 *
 */
public class ButtonValueTest extends TestCase {

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.gui.ButtonValue#ButtonValue(int)}.
	 */
	public void testButtonValue() {
		ButtonValue b = new ButtonValue(123);
		assertEquals(123, b.val);
		b.val = 17;
		assertEquals(17, b.val);
	}

}
