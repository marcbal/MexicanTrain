package fr.univ_artois.iut_lens.mexican_train.game;

public class GameSettings {
	public static final int maxPlayers = 8;
	
	
	
	private String[] playerNames = new String[maxPlayers];
	private int nbPlayers = 4;
	private int nbHuman = 1;
	
	public GameSettings(int nbP, int nbH, String[] names)
	{
		this(nbP, nbH);
		setPlayerNames(names);
	}
	
	public GameSettings(int nbP, int nbH)
	{
		for (int i=0; i<playerNames.length; i++)
			playerNames[i] = "Joueur "+(i+1);
		
		setNbPlayers(nbP);
		setNbHumans(nbH);
	}
	
	public void setPlayerNames(String[] names)
	{
		for (int i=0; i<playerNames.length && i<names.length; i++)
			playerNames[i] = names[i];
	}
	public void setNbPlayers(int nbP)
	{
		nbPlayers = (nbP > maxPlayers) ? maxPlayers : ((nbP < 2) ? 2 : nbP);
		if (nbPlayers < nbHuman)
			nbHuman = nbPlayers;
	}

	public void setNbHumans(int nbH)
	{
		nbHuman = (nbH > nbPlayers) ? nbPlayers : ((nbH < 0) ? 0 : nbH);
	}
	
	
	
	public String[] getPlayerNames() { return playerNames; }
	public int getNbPlayers() { return nbPlayers; }
	public int getNbHumans() { return nbHuman; }
	
	
	
}
