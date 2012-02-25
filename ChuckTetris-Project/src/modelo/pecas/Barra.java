package modelo.pecas;


import modelo.pecas.Peca;

public class Barra extends Peca
{
	private char direcao;
	public Barra()
	{
		super(1, 4);
		for(int i=0; i<4; i++)
			peca[0][i]=1;
	}
	
	public void rotacione()
	{

	}
}