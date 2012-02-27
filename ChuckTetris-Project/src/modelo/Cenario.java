package modelo;
import modelo.pecas.Cadeira;
import modelo.pecas.Barra;
import modelo.pecas.Triangulo;
import modelo.pecas.Peca;
import modelo.pecas.Quadrado;

public class Cenario
{
	private int[][] grade;
	private Peca pecaAtual;
	private Peca proxima;
	private boolean permissaoDesce = true;
	
	public Cenario()
	{
		this.inicializaCenario();
		//Sorteia próxima peça
		proxima = this.geraPeca();
	}

	private void inicializaCenario(){
		grade = new int[20][10];
		for(int i=0; i<grade.length; i++) {
			for(int j=0; j<grade[0].length; j++) {
				grade[i][j]=0;
			}
		}
	}
	
	public void setPermissaoDesce(boolean p){
		permissaoDesce = p;
	}

	/**
	 * Usado para debug,
	 * Imprime o cenário no console da IDE
	 */
	public void imprimeMatriz() {
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
			for(int j=0; j<grade[0].length; j++) {
				if(grade[i][j] > 5)
					grade[i][j] = 0;
			}
	}
	
	public int[][] getCenario()	{
		return grade;
	}
	
	public void adicionaPeca(Peca p) {
		
		int[][] auxPeca = p.getPeca();
		
		for(int i=0; i<auxPeca.length; i++)
			for(int j=0; j<auxPeca[0].length; j++)	{
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
			for(int j=0; j<auxPeca[0].length; j++)	{
				if(auxPeca[i-4][j]!=0)
			    	grade[j][i]=auxPeca[i-4][j];
			}
			
	}
	
	public boolean descePeca() {			

		try {
			if(this.podeMovimentar("baixo")){
				this.movimentaPecaBaixo();
			} else {
				this.setPermissaoDesce(false);
				this.fixaPeca();
				return true;
			}		
		} catch(ArrayIndexOutOfBoundsException e){
			this.fixaPeca();
			return true;
		}
		
		return false;
	}

	private boolean podeMovimentar(String direcao){
		boolean podeMovimentar = true;

		for(int i=grade.length-1; i>=0; i--) {
			for(int j=grade[0].length-1; j>=0; j--) {
				if(!this.ehPeca(i, j)){
					continue; //pula para o próximo loop
				}

				//Pega a casa correspondente ao lado para o qual quer movimentar
				int proximaCasa = 0;
				if(direcao.equals("esquerda")){
					proximaCasa = grade[i][j-1];
				} else if(direcao.equals("direita")){
					proximaCasa = grade[i][j+1];
				} else { //baixo
					proximaCasa = grade[i+1][j];
				}

				podeMovimentar = ( proximaCasa == 0 || proximaCasa >= 5);
				if(!podeMovimentar){
					return false; //Se um bloco não puder se movimentar, não movimenta nenhum
				}
			}
		}
		return true;
	}

	private void movimentaPecaBaixo(){
		for(int i=grade.length-2; i>=0; i--) {
			for(int j=grade[0].length-1; j>=0; j--) {
				if(!this.ehPeca(i, j)){
					continue; //pula para o próximo loop
				}

				grade[i+1][j] = grade[i][j];
				grade[i][j]=0;
			}
		}
	}

	private void movimentaPecaDireita(){
		for(int i=grade.length-1; i>=0; i--) {
			for(int j=grade[0].length-1; j>=0; j--) {
				if(!this.ehPeca(i, j)){
					continue; //pula para o próximo loop
				}

				grade[i][j+1] = grade[i][j];
				grade[i][j]=0;
			}
		}
	}

	private void movimentaPecaEsquerda(){
		for(int i=grade.length-1; i>=0; i--) {
			for(int j=0; j<grade[0].length; j++) {
				if(!this.ehPeca(i, j)){
					continue; //pula para o próximo loop
				}
				
				grade[i][j-1] = grade[i][j];
				grade[i][j]=0;
			}
		}
	}

	private boolean ehPeca(int i, int j){

		return (grade[i][j]==5 || //Barra
				grade[i][j]==6 || //Cadeira
				grade[i][j]==7 || //Triangulo
				grade[i][j]==8    //Quadrado
				);
	}
	
	private boolean ehPecaFixa(int i, int j){
		
		return (grade[i][j]==1 || //Barra
				grade[i][j]==2 || //Cadeira
				grade[i][j]==3 || //Triangulo
				grade[i][j]==4    //Quadrado
				);
	}
	
	public void paraEsquerda(){

		try {
			if(this.podeMovimentar("esquerda")){
				this.movimentaPecaEsquerda();
			}
		} catch(ArrayIndexOutOfBoundsException e){

		}	
	}
	
	public void paraDireita() {
		try {
			if(this.podeMovimentar("direita")){
				this.movimentaPecaDireita();
			}
		} catch(ArrayIndexOutOfBoundsException e){

		}
	}
	

	public void fixaPeca() {
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
	
	public void caiPeca() {
		while(!descePeca()){}
	}
	
	public boolean perdeu() {
		for(int j = 0; j < grade[0].length; j++)
			if(grade[0][j] != 0 && grade[0][j] < 5)
				return true;
		return false;
	}
	
	public int verificaLinhaCompleta(){		
		int numLinhasCompletas= 0;
		boolean ehLinhaCompleta = false;
		//De baixo para cima, percorre todas as linhas
		for(int i=grade.length-1; i>=0; i--) {

			//Considera que a linha e completa, se não for, o próximo laço irá marcar como falsa
			ehLinhaCompleta = true;

			//Percorre todos os blocos de uma linha,
			//se pelo menos um não for peça fixa,
			//pula para a próxima linha
			for(int j=grade[0].length-1; j>=0; j--) {
				if(!this.ehPecaFixa(i, j)) {
					ehLinhaCompleta = false;
					break;
				}
			}

			//Se chegou aqui é porque todos são peça fixa.
			if(ehLinhaCompleta){
				//Nesse caso, move todos os blocos de cima deste,
				//uma casa para baixo.
				for(int a=i; a>0; a--)
					for(int b=0; b<grade[0].length; b++)
						if(this.ehPecaFixa(a, b)) {
							grade[a][b]=grade[a-1][b];
						}
				numLinhasCompletas++;
				i++;
			}					
		}
		
		if(numLinhasCompletas == 1)
			return numLinhasCompletas*100;
		else if(numLinhasCompletas == 2)
			return numLinhasCompletas*150;
		else if(numLinhasCompletas > 2)
			return numLinhasCompletas*250;
		
		return 0;
	}
	
	public void setPecaAtual() {
		pecaAtual = proxima;
		this.setProxima(this.geraPeca());
	}
	
	public void setProxima(Peca a) { proxima = a; }
	public Peca getPecaAtual() {  return pecaAtual; }
	public Peca getProxima() {  return proxima; }
	
	public Peca geraPeca() {
		//@todo ROTEIRO: o jogo está muito difícil, altere este método para aumentar a probabilidade de gerar barras e quadrados
		int x = (int)(Math.random()*4+1);				
		switch(x) {
			case 1: return new Barra();
			case 2: return new Triangulo();
			case 3: return new Quadrado();
			case 4:	return new Cadeira();
		}
		return null;
	}
}