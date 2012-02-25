package modelo.pecas;

public abstract class Peca
{
	protected int[][] peca;
	protected int altura;
	protected int largura;
	
	public Peca(int altura, int largura)
	{
		peca=new int[altura][largura];
		this.altura = altura;
		this.largura = largura;
	}
	
	public abstract void rotacione();
		
	public int[][] getPeca()
	{
		return peca;
	}
}