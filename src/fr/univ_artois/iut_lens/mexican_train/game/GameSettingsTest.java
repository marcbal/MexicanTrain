/**
 * 
 */
package fr.univ_artois.iut_lens.mexican_train.game;

import junit.framework.TestCase;

/**
 * @author Marc Baloup
 *
 */
public class GameSettingsTest extends TestCase {


	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.GameSettings#getPlayerNames()}.
	 */
	public void testGetPlayerNames() {
		String[] s = new String[4];
		s[0] = "test1";
		s[1] = "test6";
		s[2] = "test17";
		s[3] = "test20";
		GameSettings g1 = new GameSettings(2, 2, s);
		assertEquals(s[0], g1.getPlayerNames()[0]);
		assertEquals(s[1], g1.getPlayerNames()[1]);
		assertEquals(s[2], g1.getPlayerNames()[2]);
		assertEquals(s[3], g1.getPlayerNames()[3]);
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.GameSettings#getNbPlayers()}.
	 */
	public void testGetNbPlayers() {
		GameSettings g = new GameSettings(2, 5);
		assertEquals(2, g.getNbPlayers());
		g.setNbPlayers(50);
		assertEquals(GameSettings.maxPlayers, g.getNbPlayers());
		g.setNbPlayers(0);
		assertEquals(2, g.getNbPlayers());
		
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.GameSettings#getNbHumans()}.
	 */
	public void testGetNbHumans() {
		GameSettings g = new GameSettings(2, 5);
		assertEquals(2, g.getNbHumans());
		g.setNbHumans(50);
		assertEquals(g.getNbPlayers(), g.getNbHumans());
		g.setNbHumans(-1);
		assertEquals(0, g.getNbHumans());
	}

}
