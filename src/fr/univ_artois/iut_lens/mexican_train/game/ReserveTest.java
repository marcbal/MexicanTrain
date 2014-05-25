/**
 * 
 */
package fr.univ_artois.iut_lens.mexican_train.game;

import junit.framework.TestCase;

/**
 * @author Marc Baloup
 *
 */
public class ReserveTest extends TestCase {

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Reserve#nbDominos()}.
	 */
	public void testNbDominos() {
		Reserve r = new Reserve();
		assertEquals(0, r.nbDominos());
		r.addDomino(new Domino(10, 5));
		assertEquals(1, r.nbDominos());
		r.addDomino(new Domino(10, 7));
		r.addDomino(new Domino(1, 5));
		assertEquals(3, r.nbDominos());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Reserve#takeDomino(int)}.
	 */
	public void testTakeDomino() {
		Reserve r = new Reserve();
		r.addDomino(new Domino(0, 1)); // 0
		r.addDomino(new Domino(1, 1)); // 1
		assertNotNull(r.takeDomino(0));
		assertEquals(1, r.nbDominos());
		assertNull(r.takeDomino(0));
		assertNotNull(r.takeDomino(1));
		assertEquals(0, r.nbDominos());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Reserve#getDominoes()}.
	 */
	public void testGetDominoes() {
		Reserve r = new Reserve();
		assertNotNull(r.getDominoes());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Reserve#swap(int, int)}.
	 */
	public void testSwap() {
		Reserve r = new Reserve();
		r.addDomino(new Domino(0, 1)); // 0
		r.addDomino(new Domino(1, 1)); // 1
		r.addDomino(new Domino(3, 7)); // 2
		r.addDomino(new Domino(1, 2)); // 3
		r.addDomino(new Domino(7, 7)); // 4
		r.addDomino(new Domino(2, 2)); // 5
		r.swap(1, 4);
		Domino d1 = r.takeDomino(1), d2 = r.takeDomino(4);
		assertEquals(7, d1.getV1());
		assertEquals(7, d1.getV2());
		assertEquals(1, d2.getV1());
		assertEquals(1, d2.getV2());
		
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Reserve#getBiggestDoubleDominoIndex()}.
	 */
	public void testGetBiggestDoubleDominoIndex() {
		Reserve r = new Reserve();
		r.addDomino(new Domino(0, 1)); // 0
		r.addDomino(new Domino(1, 1)); // 1
		r.addDomino(new Domino(3, 7)); // 2
		r.addDomino(new Domino(1, 2)); // 3
		r.addDomino(new Domino(7, 7)); // 4
		r.addDomino(new Domino(2, 2)); // 5
		int[] res = r.getBiggestDoubleDominoIndex();
		assertEquals(4, res[0]);
		assertEquals(7, res[1]);
	}

}
