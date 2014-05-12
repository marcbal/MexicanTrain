package fr.univ_artois.iut_lens.mexican_train.game;

public class Game {
	private Domino _dominoDepart = null;
	private Pioche _pioche = new Pioche(true);
	private Train _trainMexicain = new Train(true);
	private Player[] _joueurs;
	
	private int _activPlayer;
	private boolean _activPlayerAPioche = false;
	private boolean _isFinished = false;
	
	
	
	public Game(GameSettings settings)
	{
		_joueurs = new Player[settings.getNbPlayers()];
		int valueBiggestDoubleDomino = -1;
		do
		{
			for (int i=0; i<_joueurs.length; i++)
			{
				_joueurs[i] = new Player((i >= settings.getNbHumans()), settings.getPlayerNames()[i]);
			}
			
			// on distribue les dominos aux joueurs
			/* 2 à 4 joueurs = 15 wagons
				5 à 6 joueurs = 11 wagons
				7 à 8 joueurs = 8 wagons */
			int nbDominoPerPlayer = (settings.getNbPlayers() >= 2 && settings.getNbPlayers() <= 4) ? 15 :
								(settings.getNbPlayers() == 5 || settings.getNbPlayers() == 6) ? 11 : 8;
			for (int i=0; i<_joueurs.length; i++)
			{
				for (int j=0; j<nbDominoPerPlayer; j++)
				{
					_joueurs[i].getReserve().addDomino(_pioche.pickDomino());
				}
			}
			
			
			
			// recherche du domino de départ
			// il s'agit du double domino le plus grand se trouvant parmis les réserves des joueurs
			int indexPlayerBiggestDoubleDomino = -1;
			int indexBiggestDoubleDomino = -1;
			valueBiggestDoubleDomino = -1;
			for (int i=0; i<_joueurs.length; i++)
			{
				int[] dom = _joueurs[i].getReserve().getBiggestDoubleDominoIndex();
				if (dom[1] > valueBiggestDoubleDomino)
				{
					indexPlayerBiggestDoubleDomino = i;
					indexBiggestDoubleDomino = dom[0];
					valueBiggestDoubleDomino = dom[1];
					
				}
			}
			if (valueBiggestDoubleDomino >= 0)
			{
				setDominoDepart(_joueurs[indexPlayerBiggestDoubleDomino].getReserve().takeDomino(indexBiggestDoubleDomino));
				// le joueur auquel on a pris le domino devient le joueur actif
				_activPlayer = indexPlayerBiggestDoubleDomino;
			}
		}
		while (valueBiggestDoubleDomino < 0);
		
		System.out.println("Démarrage d'une nouvelle partie : "+getActivPlayer().getName()+" commence à jouer");
		
		
		
	}
	
	
	public Player getActivPlayer()
	{
		return getPlayer(_activPlayer);
	}
	public Player getPlayer(int i)
	{
		if (i >= 0 && i < _joueurs.length)
			return _joueurs[i];
		return null;
	}
	public Player[] getPlayers() { return _joueurs; }
	
	
	public boolean isFinished() { return _isFinished; }
	public Train getMexicanTrain() { return _trainMexicain; }
	public Pioche getPioche() { return _pioche; }
	public Domino getDominoDepart() { return _dominoDepart; }
	public int getActivPlayerIndex() { return _activPlayer; }
	
	
	public void nextPlayer()
	{
		
		if (_pioche.nbDomino() == 0)
			_isFinished = true;
		for (int i=0; i<_joueurs.length; i++)
			if (_joueurs[i].getReserve().nbDominos() == 0)
				_isFinished = true;
		
		if (_isFinished)
		{
			System.out.println("Le jeu est terminé");
			return;
		}
		_activPlayerAPioche = false;
		
		if (getActivPlayer().hasPoseDouble())
		{
			getActivPlayer().initPoseDouble();
			return;
		}
		
		_activPlayer++;
		if (_activPlayer >= _joueurs.length)
			_activPlayer -= _joueurs.length;
		
		System.out.println("Au tour de "+((getActivPlayer().isComputer())?"(ordinateur) ":" ")+getActivPlayer().getName()+" de jouer");
		
	}
	
	
	
	
	public void setDominoDepart(Domino d)
	{
		if (!d.isDouble())
			return;
		_dominoDepart = d;
		_trainMexicain.setDominoDepart(d);
		for (int i=0; i<_joueurs.length; i++)
		{
			_joueurs[i].getTrain().setDominoDepart(d);
		}
	}
	
	
	
	
	
	public void playComputer()
	{
		if (_isFinished)
			return;
		if (getActivPlayer().isComputer())
		{
			Player p = getActivPlayer();
			boolean finishPlayer = false;
			
			while (!finishPlayer)
			{
				Domino[] doms = p.getReserve().getDominoes();
				
				// on parcours pour placer un domino sur le train du joueur
				for (int i = 0; i < doms.length && !finishPlayer; i++)
				{
					Domino d = p.getTrain().getLastDomino();
					if (doms[i] == null)
						continue;
					if (doms[i].canBePlacedAfter(d))
					{
						System.out.println(p.getName()+" place un "+doms[i]+" sur son train");
						p.addDominoOwnTrain(i);
						finishPlayer = true;
						break;
					}
					else if (doms[i].canBePlacedSwappedAfter(d))
					{
						doms[i].swap();
						System.out.println(p.getName()+" place un "+doms[i]+" sur son train");
						p.addDominoOwnTrain(i);
						finishPlayer = true;
						break;
					}
					if (finishPlayer)
						p.getTrain().setOtherPlayerCanAddDomino(false);
					
				}
				
				if (!finishPlayer)
				{
					// on parcours pour placer un domino sur le train mexicain
					for (int i = 0; i < doms.length && !finishPlayer; i++)
					{
						if (doms[i] == null)
							continue;
						if (doms[i].canBePlacedAfter(_trainMexicain.getLastDomino()))
						{
							System.out.println(p.getName()+" place un "+doms[i]+" sur le train mexicain");
							p.addDominoMexicanTrain(_trainMexicain, i);
							finishPlayer = true;
							break;
						}
						else if (doms[i].canBePlacedSwappedAfter(_trainMexicain.getLastDomino()))
						{
							doms[i].swap();
							System.out.println(p.getName()+" place un "+doms[i]+" sur le train mexicain");
							p.addDominoMexicanTrain(_trainMexicain, i);
							finishPlayer = true;
							break;
						}
						
					}
				}
				

				
				// if (!finishPlayer)
				if (true)
				{
					// on parcours pour placer un domino sur le train d'un autre joueur
					for (int j=0; j<_joueurs.length && !finishPlayer; j++)
					{
						if (!_joueurs[j].getTrain().canOtherPlayerAddDomino())
							continue;
						
						Train domAutreJoueur = _joueurs[j].getTrain();
						
						for (int i = 0; i < doms.length && !finishPlayer; i++)
						{
							if (doms[i] == null)
								continue;
							if (doms[i].canBePlacedAfter(domAutreJoueur.getLastDomino()))
							{
								System.out.println(p.getName()+" place un "+doms[i]+" sur le train de "+_joueurs[j].getName());
								p.addDominoOtherTrain(_joueurs[j], i);
								finishPlayer = true;
								break;
							}
							else if (doms[i].canBePlacedSwappedAfter(domAutreJoueur.getLastDomino()))
							{
								doms[i].swap();
								System.out.println(p.getName()+" place un "+doms[i]+" sur le train de "+_joueurs[j].getName());
								p.addDominoOtherTrain(_joueurs[j], i);
								finishPlayer = true;
								break;
							}
							
						}
						if (finishPlayer)
							_joueurs[j].getTrain().setOtherPlayerCanAddDomino(false);
					}
					
					
				}
				
				if (!hasActivPlayerPioche() && !finishPlayer)
				{
					activPlayerPioche();
					System.out.println(p.getName()+" pioche un domino");
				}
				else if (!finishPlayer)
				{
					p.getTrain().setOtherPlayerCanAddDomino(true);
					System.out.println(p.getName()+" passe son tour");
					finishPlayer = true;
				}
				
			}
			
			nextPlayer();
			
				
		}
	}


	public boolean hasActivPlayerPioche()
	{
		return _activPlayerAPioche;
	}
	
	public void activPlayerPioche()
	{
		getActivPlayer().getReserve().addDomino(getPioche().pickDomino());
		_activPlayerAPioche = true;
	}

	
	
	
	
	
	
}
