import java.util.Arrays;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.*;
import javax.swing.Icon;

public class Cenario
{
	
	private int[][] grade;
	private Peca pecaAtual;
	private Peca proxima;
	private boolean permissaoDesce = true;
	
	public Cenario()
	{
		
		grade = new int[20][10];
		for(int i=0; i<grade.length; i++)
			for(int j=0; j<grade[0].length; j++)
				grade[i][j]=0;
		proxima = this.geraPeca();
	}
	
	public void setPermissaoDesce(boolean p)
	{
		permissaoDesce = p;
	}
	
	public void imprimeMatriz()
	{
		String matriz = "";
		for(int i = 0; i<grade.length; i++)
		{	for(int j =0; j<grade[0].length; j++)
				matriz += " "+ grade[i][j]+ " ";
			matriz+= "\n";
		}		
		System.out.println(matriz);
	}
	
	public void limpaCenario() {
		for(int i=0; i<grade.length; i++)
			for(int j=0; j<grade[0].length; j++)
			{
				if(grade[i][j] > 5)
					grade[i][j]=0;
			}
	}
	
	public int[][] getCenario()
	{
		return grade;
	}
	
	public void adicionaPeca(Peca p) 
	{
		
		int[][] auxPeca=p.getPeca();
		
		for(int i=0; i<auxPeca.length; i++)
			for(int j=0; j<auxPeca[0].length; j++)
			{
				if(auxPeca[i][j]==1)
					auxPeca[i][j]=5;
				else if(auxPeca[i][j]==2)
					auxPeca[i][j]=6;
				else if(auxPeca[i][j]==3)
					auxPeca[i][j]=7;
				else if(auxPeca[i][j]==4)
					auxPeca[i][j]=8;
			}
			
		for(int i=4; i<(4+auxPeca.length); i++)
			for(int j=0; j<auxPeca[0].length; j++)
			{
				if(auxPeca[i-4][j]!=0)
			    	grade[j][i]=auxPeca[i-4][j];
			}
			
	}
	
	public boolean descePeca()
	{
			boolean podeMovimentar = true;
			boolean controle = false;
			try{
			for(int i=grade.length-1; i>=0; i--)
			{
				for(int j=grade[0].length-1; j>=0; j--)
						if(grade[i][j]==5||grade[i][j]==6||grade[i][j]==7||grade[i][j]==8)
						{
							controle = true;
							//Verifica se pode movimentar
							if(grade[i+1][j] == 0)
							{
								podeMovimentar = true;
							}
							else
							{
								podeMovimentar= false;
								setPermissaoDesce(false);
								break;
							}
							
							if(pecaAtual instanceof Cadeira )
							{
								if(grade[i][j-1] == 0)
									podeMovimentar = true;
								else
									podeMovimentar = false;
							}
						}
						if(controle) break; 
			}
						
							//Movimenta
					for(int i=grade.length-1; i>=0; i--)
						for(int j=grade[0].length-1; j>=0; j--)
							if(grade[i][j]==5||grade[i][j]==6||grade[i][j]==7||grade[i][j]==8)
							{
								if(podeMovimentar)
								{
									grade[i+1][j]=grade[i][j];
									grade[i][j]=0;
								}
								else
								{
									this.fixaPeca();
									return true;
								}
							}
							
					} //Fim Try
					catch(ArrayIndexOutOfBoundsException e)
					{	
						this.fixaPeca();
						return true;				
					}
					return false;

				
		
	}


	

	
	public void paraEsquerda()
	{
		boolean podeMovimentar = true;
			boolean controle = false;
			try{
			for(int j=0; j<=grade[0].length-1; j++)
			{
				for(int i=0; i<=grade.length-1; i++)
						if(grade[i][j]==5||grade[i][j]==6||grade[i][j]==7||grade[i][j]==8)
						{
							controle = true;
							//Verifica se pode movimentar
							if(grade[i][j-1] == 0)
							{
								podeMovimentar = true;
							}
							else
							{
								podeMovimentar= false;
								break;
							}
							
							if(pecaAtual instanceof Cadeira )
							{
								if(grade[i+1][j] == 0)
									podeMovimentar = true;
								else
									podeMovimentar = false;
							}
							
					}
					if(controle) break; 
			}
						
							//Movimenta
					for(int j=0; j<=grade[0].length-1; j++)
						for(int i=0; i<=grade.length-1; i++)
							if(grade[i][j]==5||grade[i][j]==6||grade[i][j]==7||grade[i][j]==8)
							{
								if(podeMovimentar)
								{
									grade[i][j-1]=grade[i][j];
									grade[i][j]=0;
								}
								else
								{
									//		return true;
								}
							}
							
					} //Fim Try
					catch(ArrayIndexOutOfBoundsException e)
					{	
						//	return true;				
					}
				//	return false;
	}
	
	public void paraDireita()
	{
			boolean podeMovimentar = true;
			boolean controle = false;
			try{
			for(int j=grade[0].length-1; j>=0; j--)
			{
				for(int i=grade.length-1; i>=0; i--)
						if(grade[i][j]==5||grade[i][j]==6||grade[i][j]==7||grade[i][j]==8)
						{
							controle = true;
							//Verifica se pode movimentar
							if(grade[i][j+1] == 0)
							{
								podeMovimentar = true;
							}
							else
							{
								podeMovimentar= false;
								break;
							}
							
					}
					if(controle) break; 
			}
						
							//Movimenta
					for(int j=grade[0].length-1; j>=0; j--)
						for(int i=grade.length-1; i>=0; i--)
							if(grade[i][j]==5||grade[i][j]==6||grade[i][j]==7||grade[i][j]==8)
							{
								if(podeMovimentar)
								{
									grade[i][j+1]=grade[i][j];
									grade[i][j]=0;
								}
								else
								{
									//		return true;
								}
							}
							
					} //Fim Try
					catch(ArrayIndexOutOfBoundsException e)
					{	
						//	return true;				
					}
				//	return false;
	}
	

	public void fixaPeca()
	{
		for(int i=0; i<grade.length; i++)
			for(int j=0; j<grade[0].length; j++)
				if(grade[i][j]==5)
					grade[i][j]=1;
				else if(grade[i][j]==6)
					grade[i][j]=2;
				else if(grade[i][j]==7)
					grade[i][j]=3;
				else if(grade[i][j]==8)
					grade[i][j]=4;
	}
	
	public void caiPeca()
	{
		while(!descePeca())
		{
		}
	}
	
	public boolean perdeu()
	{
		//this.perdeu = true;
		for(int j = 0; j < grade[0].length; j++)
			if(grade[0][j] != 0 && grade[0][j] < 5)
				return true;
		return false;
	}
	
/*	public boolean perdeu()
	{
		return perdeu;
	}*/
	
	public int verificaLinhaCompleta()
	{
		boolean teste = false;
		int cont = 0;
		for(int i=grade.length-1; i>=0; i--)
		{
			teste=false;
			for(int j=grade[0].length-1; j>=0; j--)
				if(grade[i][j]!=0 && grade[i][j]<5)
					teste=true;
				else
				{
				
					teste=false;
					break;
				}
			
			if(teste)
			{
				for(int a=i; a>0; a--)
					for(int b=0; b<grade[0].length; b++)
						if(grade[a][b]<5)
							grade[a][b]=grade[a-1][b];
				cont++;
				i++;
			}

		}
		
		if(cont == 1)
			return cont*100;
		else if(cont == 2)
			return cont*150;
		else if(cont > 2)
			return cont*250;
		
		return 0;
	}
	
	public void setPecaAtual()
	{
		pecaAtual = proxima;
	}
	
	public void setProxima(Peca a)
	{
		proxima = a;
	}
	
	public Peca getPecaAtual()
	{
		return pecaAtual;
	}
	
	public Peca getProxima()
	{
		return proxima;
	}
	
	
	public Peca geraPeca()
	{
		int x = (int)(Math.random()*4+1);
		Peca p = null;
		
		switch(x)
		{
			
			case 1:
				p = new Barra();
				break;
			case 2:
				p = new Triangulo();
				break;
			case 3:
				p = new Quadrado();
				break;
			case 4:
				p = new Cadeira();
				break;
		}
		return p;
	}
}