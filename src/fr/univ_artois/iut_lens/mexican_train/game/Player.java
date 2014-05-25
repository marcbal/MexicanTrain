package fr.univ_artois.iut_lens.mexican_train.game;

public class Player {
	private boolean _isComputer = false;
	private boolean _aPoseDouble = false;
	private String _name = "";
	
	private Train _train = new Train(false);
	private Reserve _reserve = new Reserve();
	
	
	public Player (boolean com, String name)
	{
		_isComputer = com;
		_name = name;
	}
	
	
	
	public Train getTrain() { return _train; }
	public Reserve getReserve() { return _reserve; }
	public boolean isComputer() { return _isComputer; }
	public String getName() { return _name; }
	
	
	
	public boolean addDominoOwnTrain(int dominoIndexReserve)
	{
		Domino d = _reserve.getDominoes()[dominoIndexReserve];
		if (d == null)
			return false;
		if(_train.addDomino(d))
		{
			_aPoseDouble = d.isDouble();
			_reserve.takeDomino(dominoIndexReserve);
			return true;
		}
		return false;
	}
	
	public boolean addDominoOtherTrain(Player otherPlayer, int dominoIndexReserve)
	{
		Domino d = _reserve.getDominoes()[dominoIndexReserve];
		if (d == null || otherPlayer.getTrain().canOtherPlayerAddDomino() == false)
			return false;

		if (otherPlayer.getTrain().addDomino(d))
		{
			_aPoseDouble = d.isDouble();
			_reserve.takeDomino(dominoIndexReserve);
			return true;
		}
		return false;
	}
	
	public boolean addDominoMexicanTrain(Train mexicanTrain, int dominoIndexReserve)
	{
		Domino d = _reserve.getDominoes()[dominoIndexReserve];
		// Domino d = 
		if ((d == null || mexicanTrain.isMexicanTrain() == false))
			return false;
		if (mexicanTrain.addDomino(d))
		{
			_aPoseDouble = d.isDouble();
			_reserve.takeDomino(dominoIndexReserve);
			return true;
		}
		return false;
	}
	
	
	public boolean hasPoseDouble() { return _aPoseDouble; }
	public void initPoseDouble() { _aPoseDouble = false; }


}
