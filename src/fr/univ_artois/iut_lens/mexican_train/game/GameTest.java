/**
 * 
 */
package fr.univ_artois.iut_lens.mexican_train.game;

import junit.framework.TestCase;

/**
 * @author Marc Baloup
 *
 */
public class GameTest extends TestCase {

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Game#getActivPlayer()}.
	 */
	public void testGetActivPlayer() {
		Game g = new Game(new GameSettings(4, 1));
		assertEquals(g.getPlayer(g.getActivPlayerIndex()), g.getActivPlayer());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Game#getPlayer(int)}.
	 */
	public void testGetPlayer() {
		int nbP = 4;
		Game g = new Game(new GameSettings(nbP, 1));
		for (int i=0; i<nbP; i++)
			assertNotNull(g.getPlayer(i));
		assertNull(g.getPlayer(-3));
		assertNull(g.getPlayer(nbP));
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Game#getPlayers()}.
	 */
	public void testGetPlayers() {
		int nbP = 4;
		Game g = new Game(new GameSettings(nbP, 1));
		assertNotNull(g.getPlayers());
		assertEquals(nbP, g.getPlayers().length);
		for (int i=0; i<nbP; i++)
			assertNotNull(g.getPlayers()[i]);
	}
	


	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Game#getActivPlayerIndex()}.
	 */
	public void testGetActivPlayerIndex() {
		Game g = new Game(new GameSettings(4, 1));
		assertEquals(g.getActivPlayer(), g.getPlayers()[g.getActivPlayerIndex()]);
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Game#isFinished()}.
	 */
	public void testIsFinished() {
		Game g = new Game(new GameSettings(4, 1));
		assertFalse(g.isFinished());
		while (g.getPioche().pickDomino() != null);
		g.nextPlayer();
		assertTrue(g.isFinished());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Game#getMexicanTrain()}.
	 */
	public void testGetMexicanTrain() {
		Game g = new Game(new GameSettings(4, 1));
		assertNotNull(g.getMexicanTrain());
		assertTrue(g.getMexicanTrain().isMexicanTrain());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Game#getPioche()}.
	 */
	public void testGetPioche() {
		Game g = new Game(new GameSettings(4, 1));
		assertNotNull(g.getPioche());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Game#getDominoDepart()}.
	 */
	public void testGetDominoDepart() {
		Game g = new Game(new GameSettings(4, 1));
		assertNotNull(g.getDominoDepart());
		assertTrue(g.getDominoDepart().isDouble());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Game#hasActivPlayerPioche()}.
	 */
	public void testHasActivPlayerPioche() {
		Game g = new Game(new GameSettings(4, 1));
		assertFalse(g.hasActivPlayerPioche());
		g.activPlayerPioche();
		assertTrue(g.hasActivPlayerPioche());
		g.nextPlayer();
		assertFalse(g.hasActivPlayerPioche());
	}

}
