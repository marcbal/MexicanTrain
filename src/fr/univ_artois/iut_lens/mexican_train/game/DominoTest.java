/**
 * 
 */
package fr.univ_artois.iut_lens.mexican_train.game;

import junit.framework.TestCase;

/**
 * @author Marc Baloup
 *
 */
public class DominoTest extends TestCase {

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Domino#getV1()}.
	 */
	public void testGetV1() {
		Domino d = new Domino(1, 0);
		assertEquals(1, d.getV1());
		d.setV1(4);
		assertEquals(4, d.getV1());
		d.setV1(-1);
		assertEquals(0, d.getV1());
		d.setV1(30);
		assertEquals(Domino.max_val, d.getV1());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Domino#getV2()}.
	 */
	public void testGetV2() {
		Domino d = new Domino(0, 9);
		assertEquals(9, d.getV2());
		d.setV2(4);
		assertEquals(4, d.getV2());
		d.setV2(-1);
		assertEquals(0, d.getV2());
		d.setV2(30);
		assertEquals(Domino.max_val, d.getV2());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Domino#getVal()}.
	 */
	public void testGetVal() {
		Domino d = new Domino(6, 9);
		assertEquals(6+9, d.getVal());
		d.setV1(2);
		d.setV2(7);
		assertEquals(2+7, d.getVal());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Domino#isHidden()}.
	 */
	public void testIsHidden() {
		Domino d = new Domino(0, 0, true);
		Domino e = new Domino(0, 0, false);
		assertEquals(true, d.isHidden());
		assertEquals(false, e.isHidden());
		d.setHidden(false);
		assertEquals(false, d.isHidden());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Domino#swap()}.
	 */
	public void testSwap() {
		Domino d = new Domino(4, 7);
		d.swap();
		assertEquals(7, d.getV1());
		assertEquals(4, d.getV2());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Domino#toString()}.
	 */
	public void testToString() {
		Domino d = new Domino(4, 7);
		assertEquals("(4;7)", d.toString());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Domino#isDouble()}.
	 */
	public void testIsDouble() {
		Domino d = new Domino(4, 7);
		Domino e = new Domino(6, 6);
		assertEquals(false, d.isDouble());
		assertEquals(true, e.isDouble());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Domino#canBePlacedAfter(fr.univ_artois.iut_lens.mexican_train.game.Domino)}.
	 */
	public void testCanBePlacedAfter() {
		Domino d = new Domino(4, 7);
		Domino e = new Domino(7, 6);
		assertEquals(true, e.canBePlacedAfter(d));
		e.swap();
		assertEquals(false, e.canBePlacedAfter(d));
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Domino#canBePlacedSwappedAfter(fr.univ_artois.iut_lens.mexican_train.game.Domino)}.
	 */
	public void testCanBePlacedSwappedAfter() {
		Domino d = new Domino(4, 7);
		Domino e = new Domino(7, 6);
		assertEquals(false, e.canBePlacedSwappedAfter(d));
		e.swap();
		assertEquals(true, e.canBePlacedSwappedAfter(d));
	}

}
