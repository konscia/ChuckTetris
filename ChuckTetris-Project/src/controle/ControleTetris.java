package controle;

import modelo.Jogador;
import modelo.Cenario;
import visao.JanelaPrincipal;
import javax.swing.*;

public class ControleTetris
{
	private JanelaPrincipal janela;

	private SecondsController secondsController;

	private int frame = 0;
	private Jogador jogador;
	public Cenario cenario;
	private int nivel;
	
	private boolean temProximaPeca;
	private boolean caiPeca = false;	
	private int velocidade;	
	private boolean gameOver;


	public ControleTetris(){				
		cenario = new Cenario();

		janela = new JanelaPrincipal(this);
		secondsController = new SecondsController(janela);
		
		frame = 0;		
		temProximaPeca = true;
		gameOver = true;

		while(true){
			try{ Thread.sleep((long)velocidade); } catch(Exception e){}
			gameLoop();			
		}
		
	}
	
	public void novoJogo() {
		jogador = new Jogador(JOptionPane.showInputDialog(null, "Qual sua graça?", "Chuck Tetris", JOptionPane.PLAIN_MESSAGE));
		cenario = new Cenario();
		frame = 0;
		temProximaPeca = true;
		this.secondsController.setTempo(0);		
		janela.mostraNome(jogador.getNome());
		cenario.setPecaAtual();
		this.setNivel(1);
		gameOver = false;		
	}
	
	public void setNivel(int n) {
		nivel = n;
		velocidade = 1000 / (nivel*3);
	}	
		
	
	public void caiPeca() {
		cenario.caiPeca();
		caiPeca = true;
	}
		
	private void gameLoop() {
		if(gameOver) { return; }		

		//Se tem peça pra descer, calcula
		if(cenario.descePeca() || caiPeca){
			caiPeca = false;
			this.computaPontos(cenario.verificaLinhaCompleta());
			temProximaPeca = true;
			cenario.setPecaAtual();
		}

		if(cenario.perdeu()){
			gameOver = true;
			Records r = new Records();
			r.salvarJogador(jogador);
			JOptionPane.showMessageDialog(null,"Game Over");
		}

		if(temProximaPeca)	{
			this.adicionaPecaAoCenario();
		}

		janela.repintar();
		janela.mostraScore(jogador.getPontuacao());
		
	}

	private void adicionaPecaAoCenario(){
		cenario.setPermissaoDesce(true);
		cenario.adicionaPeca(cenario.getPecaAtual());
		temProximaPeca = false;
	}
	
	public void computaPontos(int quant) {
		jogador.setPontuacao(jogador.getPontuacao()+quant);
	}

	// Métodos de Acesso

	public int getNivel(){return nivel;}	
	public Jogador getJogador() {return jogador;}
}