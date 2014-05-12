package fr.univ_artois.iut_lens.mexican_train.game;

public class Reserve {
	private Domino[] _dominos = new Domino[Pioche.taille_pioche];
	
	public Reserve()
	{
		
	}
	
	public Reserve(Domino[] newDominos)
	{
		this();
		for (int i=0; i<newDominos.length; i++)
			if (newDominos[i] != null)
				addDomino(newDominos[i]);
	}
	
	
	public int nbDominos()
	{
		int c = 0;
		for (int i=0; i<_dominos.length; i++)
			if (_dominos[i] != null)
				c++; // lol
		return c;
	}
	
	
	public boolean addDomino(Domino d)
	{
		int i = 0;
		while (i<_dominos.length)
		{
			if (_dominos[i] == null)
			{
				_dominos[i] = d;
				return true;
			}
			i++;
		}
		return false;
	}
	
	public Domino takeDomino(int index)
	{
		if (index >= 0 && index < _dominos.length)
		{
			Domino d = _dominos[index];
			_dominos[index] = null;
			return d;
		}
		return null;
	}
	
	public Domino[] getDominoes()
	{
		return _dominos;
		
	}
	
	public void swap(int i1, int i2)
	{
		if (i1 >= 0 && i1 < _dominos.length && i2 >= 0 && i2 < _dominos.length && i2 != i1)
		{
			Domino tmp = _dominos[i1];
			_dominos[i1] = _dominos[i2];
			_dominos[i2] = tmp;
		}
	}
	
	public int[] getBiggestDoubleDominoIndex()
	{
		int max_val = -1;
		int index = -1;
		for (int i=0; i<_dominos.length; i++)
			if (_dominos[i] != null)
				if (_dominos[i].isDouble() && _dominos[i].getV1() > max_val)
				{
					max_val = _dominos[i].getV1();
					index = i;
				}
		int[] r = new int[2];
		r[0] = index; r[1] = max_val;
		return r;
	}
}
