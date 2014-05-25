package fr.univ_artois.iut_lens.mexican_train.game;

public class Domino {
	private int v1;
	private int v2;
	private boolean hiddenFace = false;
	
	public static final int max_val = 12; // valeurs possible de 1 à 12
	
	
	public Domino (int vi1, int vi2)
	{
		setV1(vi1);
		setV2(vi2);
	}
	
	public Domino (int vi1, int vi2, boolean hidden)
	{
		this(vi1, vi2);
		
	}
	
	
	public void setV1(int v)
	{
		if (v<0)
			v=0;
		if (v>max_val)
			v=max_val;
		v1 = v;
	}
	public void setV2(int v)
	{
		if (v<0)
			v=0;
		if (v>max_val)
			v=max_val;
		v2 = v;
	}
	public void setHidden(boolean hidden)
	{
		hiddenFace = hidden;
	}
	
	public int getV1() { return v1; }
	public int getV2() { return v2; }
	public int getVal() { return v1+v2; }
	public boolean isHidden() { return hiddenFace; }
	
	
	public void swap() { int tmp = v1; v1 = v2; v2 = tmp; }
	
	public String toString() { return "("+v1+";"+v2+")"; }
	
	public boolean isDouble() { return (v2 == v1); }
	
	public boolean canBePlacedAfter(Domino d) { return (v1 == d.v2); }
	public boolean canBePlacedSwappedAfter(Domino d) { return (v2 == d.v2); }
	
	
	
}
