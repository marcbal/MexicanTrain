/**
 * 
 */
package fr.univ_artois.iut_lens.mexican_train.game;

import junit.framework.TestCase;

/**
 * @author Marc Baloup
 *
 */
public class TrainTest extends TestCase {

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Train#isMexicanTrain()}.
	 */
	public void testIsMexicanTrain() {
		assertFalse((new Train(false)).isMexicanTrain());
		assertTrue((new Train(true)).isMexicanTrain());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Train#getDominos()}.
	 */
	public void testGetDominos() {
		Train t = new Train(false);
		assertNotNull(t.getDominos());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Train#canOtherPlayerAddDomino()}.
	 */
	public void testCanOtherPlayerAddDomino() {
		Train t = new Train(false);
		assertFalse(t.canOtherPlayerAddDomino());
		t.setOtherPlayerCanAddDomino(true);
		assertTrue(t.canOtherPlayerAddDomino());
		t.setOtherPlayerCanAddDomino(false);
		assertFalse(t.canOtherPlayerAddDomino());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Train#setDominoDepart()}.
	 */
	public void testSetDominoDepart() {
		Train t = new Train(false);
		t.addDomino(new Domino(6, 4));
		assertEquals(0, t.nbDomino());
		t.setDominoDepart(new Domino(6, 6));
		t.addDomino(new Domino(6, 4));
		assertEquals(1, t.nbDomino());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Train#getLastDomino()}.
	 */
	public void testGetLastDomino() {
		Train t = new Train(false);
		t.setDominoDepart(new Domino(6, 6));
		Domino d = new Domino(6, 12);
		t.addDomino(d);
		assertEquals(d, t.getLastDomino());
		
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Train#nbDomino()}.
	 */
	public void testNbDomino() {
		Train t = new Train(false);
		t.setDominoDepart(new Domino(6, 6));
		t.addDomino(new Domino(6, 4));
		t.addDomino(new Domino(4, 7));
		assertEquals(2, t.nbDomino());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Train#addDomino()}.
	 */
	public void testAddDomino() {
		Train t = new Train(false);
		t.setDominoDepart(new Domino(6, 6));
		t.addDomino(new Domino(6, 4));
		assertEquals(1, t.nbDomino());
		t.addDomino(new Domino(5, 7));
		assertEquals(1, t.nbDomino());
	}

}
