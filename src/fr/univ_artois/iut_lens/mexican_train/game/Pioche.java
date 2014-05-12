package fr.univ_artois.iut_lens.mexican_train.game;

import java.util.Random;

public class Pioche {
	public static int taille_pioche = ((Domino.max_val+1)*(Domino.max_val+1)+(Domino.max_val+1))/2;
	private Domino[] _dominos = new Domino[taille_pioche];
	
	public Pioche (boolean melanger)
	{
		int k = 0;
		for (int i=0; i<=Domino.max_val; i++)
			for (int j=0; j<=i; j++)
			{
				_dominos[k] = new Domino(i, j);
				k++;
			}
		
		if (melanger)
		{
			Random r = new Random();
			for (int i=0; i<2*_dominos.length; i++)
			{
				int r1 = r.nextInt(_dominos.length);
				int r2 = r.nextInt(_dominos.length);
				if (r.nextBoolean())
					_dominos[r1].swap();
				if (r1 != r2)
				{
					Domino tmp = _dominos[r1];
					_dominos[r1] = _dominos[r2];
					_dominos[r2] = tmp;
				}
				
			}
		}
		
		
		
	}
	
	
	public int nbDomino()
	{
		int c = 0;
		for (int i=0; i<_dominos.length; i++)
			if (_dominos[i] != null)
				c++; // lol
		return c;
	}
	
	public Domino pickDomino()
	{

		for (int i=_dominos.length-1; i>=0; i--)
			if (_dominos[i] != null)
			{
				Domino d = _dominos[i];
				_dominos[i] = null;
				return d;
			}
		return null;
	}
	

}
