package fr.univ_artois.iut_lens.mexican_train.game;

public class Train {
	private boolean _mexicanTrain;
	private Domino[] _dominos = new Domino[Pioche.taille_pioche];
	private Domino _dominoDepart = null;
	private boolean _otherPlayerCanAddDomino = false;
	
	public Train(boolean isMexicanTrain)
	{
		_mexicanTrain = isMexicanTrain;
		if (_mexicanTrain)
			setOtherPlayerCanAddDomino(true);
	}
	
	
	
	
	
	public boolean isMexicanTrain() { return _mexicanTrain; }
	public Domino[] getDominos() { return _dominos; }
	public boolean canOtherPlayerAddDomino() { return _otherPlayerCanAddDomino; }
	
	public void setOtherPlayerCanAddDomino(boolean v)
	{
		if (_mexicanTrain)
			return;
		_otherPlayerCanAddDomino = v;
	}
	
	
	public boolean setDominoDepart(Domino d)
	{
		if (!d.isDouble())
			return false;
		_dominoDepart = d;
		return true;
	}
	
	
	
	public boolean addDomino(Domino d)
	{
		if (_dominoDepart == null)
			return false;
		// on s'assure que le domino de départ soit défini
		
		if (_dominos[0] == null)
		{
			if (d.canBePlacedAfter(_dominoDepart))
			{
				_dominos[0] = d;
				return true;
			}
			return false;
		}
		
		int i = 1;
		while (_dominos[i] != null) { i++; }
		if (i<_dominos.length && d.canBePlacedAfter(_dominos[i-1]))
		{
			_dominos[i] = d;
			return true;
		}
		return false;
	}
	
	
	public Domino getLastDomino()
	{
		for (int i = _dominos.length-1; i>=0; i--)
			if (_dominos[i] != null)
				return _dominos[i];
		return _dominoDepart;
	}
	
	
	
	public int nbDomino()
	{
		int i = 0;
		while (_dominos[i] != null) { i++; }
		return i;
	}
	
	
	
}
