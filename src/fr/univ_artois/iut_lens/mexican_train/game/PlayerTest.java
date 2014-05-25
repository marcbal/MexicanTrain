/**
 * 
 */
package fr.univ_artois.iut_lens.mexican_train.game;

import junit.framework.TestCase;

/**
 * @author Marc Baloup
 *
 */
public class PlayerTest extends TestCase {

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Player#getTrain()}.
	 */
	public void testGetTrain() {
		Player p = new Player(false, "");
		Train t = p.getTrain();
		assertNotNull(t);
		if (t != null)
			assertEquals(false, t.isMexicanTrain());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Player#getReserve()}.
	 */
	public void testGetReserve() {
		assertNotNull((new Player(false, "")).getReserve());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Player#isComputer()}.
	 */
	public void testIsComputer() {
		assertFalse((new Player(false, "")).isComputer());
		assertTrue((new Player(true, "")).isComputer());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Player#getName()}.
	 */
	public void testGetName() {
		assertEquals("test", (new Player(false, "test")).getName());
		assertEquals("bouya", (new Player(false, "bouya")).getName());
		assertEquals("", (new Player(false, "")).getName());
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Player#addDominoOwnTrain(int)}.
	 */
	public void testAddDominoOwnTrain() {
		Player p = new Player(false, "test");
		p.getReserve().addDomino(new Domino(6, 3)); // index 0
		p.getReserve().addDomino(new Domino(7, 3)); // index 1
		p.getReserve().addDomino(new Domino(3, 9)); // index 2
		
		assertFalse(p.addDominoOwnTrain(0)); // domino de départ non défini
		p.getTrain().setDominoDepart(new Domino(6, 6));
		assertTrue(p.addDominoOwnTrain(0));
		assertFalse(p.addDominoOwnTrain(1));
		assertTrue(p.addDominoOwnTrain(2));
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Player#addDominoOtherTrain(fr.univ_artois.iut_lens.mexican_train.game.Player, int)}.
	 */
	public void testAddDominoOtherTrain() {
		Player p = new Player(false, "test");
		Player p2 = new Player(false, "test");
		p.getReserve().addDomino(new Domino(6, 3)); // index 0
		p.getReserve().addDomino(new Domino(7, 3)); // index 1
		p.getReserve().addDomino(new Domino(3, 9)); // index 2
		
		assertFalse(p.addDominoOtherTrain(p2, 0)); // domino de départ non défini
		p2.getTrain().setDominoDepart(new Domino(6, 6));
		assertFalse(p.addDominoOtherTrain(p2, 0));
		p2.getTrain().setOtherPlayerCanAddDomino(true);
		assertTrue(p.addDominoOtherTrain(p2, 0));
		p2.getTrain().setOtherPlayerCanAddDomino(true);
		assertFalse(p.addDominoOtherTrain(p2, 1));
		p2.getTrain().setOtherPlayerCanAddDomino(true);
		assertTrue(p.addDominoOtherTrain(p2, 2));
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Player#addDominoMexicanTrain(fr.univ_artois.iut_lens.mexican_train.game.Train, int)}.
	 */
	public void testAddDominoMexicanTrain() {
		Player p = new Player(false, "test");
		Train tm = new Train(true);
		p.getReserve().addDomino(new Domino(6, 3)); // index 0
		p.getReserve().addDomino(new Domino(7, 3)); // index 1
		p.getReserve().addDomino(new Domino(3, 9)); // index 2
		
		assertFalse(p.addDominoMexicanTrain(tm, 0)); // domino de départ non défini
		tm.setDominoDepart(new Domino(6, 6));
		assertTrue(p.addDominoMexicanTrain(tm, 0));
		assertFalse(p.addDominoMexicanTrain(tm, 1));
		assertTrue(p.addDominoMexicanTrain(tm, 2));
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Player#hasPoseDouble()}.
	 */
	public void testHasPoseDouble() {
		Player p = new Player(false, "");
		Player p2 = new Player(false, "");
		assertFalse(p.hasPoseDouble());
		p.getReserve().addDomino(new Domino(6, 3)); // index 0
		p.getReserve().addDomino(new Domino(3, 3)); // index 1
		p.getReserve().addDomino(new Domino(6, 9)); // index 2
		p.getReserve().addDomino(new Domino(9, 9)); // index 3
		p.getReserve().addDomino(new Domino(6, 11)); // index 4
		p.getReserve().addDomino(new Domino(11, 11)); // index 5
		
		p.getTrain().setDominoDepart(new Domino(6, 6));
		p2.getTrain().setDominoDepart(new Domino(6, 6));
		p2.getTrain().setOtherPlayerCanAddDomino(true);
		
		Train t = new Train(true); // un train mexicain
		t.setDominoDepart(new Domino(6, 6));
		
		// test pour la pose sur son propre train
		p.addDominoOwnTrain(0);	// pose du (6;3)
		assertFalse(p.hasPoseDouble());
		p.addDominoOwnTrain(1);	// pose du (6;6)
		assertTrue(p.hasPoseDouble());
		p.initPoseDouble();
		assertFalse(p.hasPoseDouble());
		
		// test pour la pose sur le train mexicain
		p.addDominoMexicanTrain(t, 2);
		assertFalse(p.hasPoseDouble());
		p.addDominoMexicanTrain(t, 3);
		assertTrue(p.hasPoseDouble());
		p.initPoseDouble();
		assertFalse(p.hasPoseDouble());

		// test pour la pose sur le train d'un autre joueur
		p.addDominoOtherTrain(p2, 4);
		assertFalse(p.hasPoseDouble());
		p.addDominoOtherTrain(p2, 5);
		assertTrue(p.hasPoseDouble());
		p.initPoseDouble();
		assertFalse(p.hasPoseDouble());
		
	}

	/**
	 * Test method for {@link fr.univ_artois.iut_lens.mexican_train.game.Player#initPoseDouble()}.
	 */
	public void testInitPoseDouble() {
		Player p = new Player(false, "test");
		p.initPoseDouble();
		assertFalse(p.hasPoseDouble());
	}

}
