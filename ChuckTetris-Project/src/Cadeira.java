public class Cadeira extends Peca
{
	
	private char direcao;
	public Cadeira()
	{
		super(3, 2);
		peca[0][0]=2;
		peca[1][0]=2;
		peca[1][1]=2;
		peca[2][1]=2;
		peca[0][1]=0;
		peca[2][0]=0;
	}
	
	public void rotacione()
	{
		
	}
}