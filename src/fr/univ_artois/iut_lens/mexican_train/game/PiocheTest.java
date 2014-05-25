/**
 * 
 */
package fr.univ_artois.iut_lens.mexican_train.game;

import junit.framework.TestCase;

/**
 * @author Marc Baloup
 *
 */
public class PiocheTest extends TestCase {

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Pioche#nbDomino()}.
	 */
	public void testNbDomino() {
		Pioche p = new Pioche(false);
		assertEquals(Pioche.taille_pioche, p.nbDomino());
		p.pickDomino();
		p.pickDomino();
		p.pickDomino();
		p.pickDomino();
		p.pickDomino();
		assertEquals(Pioche.taille_pioche - 5, p.nbDomino());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Pioche#pickDomino()}.
	 */
	public void testPickDomino() {
		Pioche p = new Pioche(true);
		while (p.nbDomino()>0)
			assertNotNull(p.pickDomino());
		assertNull(p.pickDomino());
	}

}
