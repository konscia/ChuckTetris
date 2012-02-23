import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class ControleTetris extends TimerTask
{
	private Limite limite;
	private Timer timer;
	private int frame = 0;
	private int timer_var = 0;
	private Jogador jogador;
	public Cenario cenario;
	private int nivel;
	private long tempo;
	private boolean proximaPeca;
	private boolean caiPeca = false;
	private boolean controleRun = true;
	private int velocidade;
	private int loops = 0;
	private boolean gameOver;


	public ControleTetris()
	{
				
		cenario = new Cenario();
		
		timer = new Timer();
		
		limite=new Limite(this);
		
		frame = 0;
		
		proximaPeca = true;
		
	}
	
	public void atualizaTempo()
	{
		tempo++;
	}
	
	public long getTempo()
	{
		return tempo;
	} 
	
	public void novoJogo()
	{
		jogador = new Jogador(JOptionPane.showInputDialog(null, "Qual sua graça?", "Chuck Tetris", JOptionPane.PLAIN_MESSAGE));
		cenario = new Cenario();
		frame = 0;
		proximaPeca = true;
		tempo = 0;
		controleRun = true;
		velocidade = 1000/(nivel+1);
		gameOver = false;
		try{
			timer.scheduleAtFixedRate(this,100,200/*(getNivel()+1)*/);
		}
		catch(IllegalStateException e)
		{
		}
	}
	
	public void setNivel(int n)
	{
		nivel=n;
		this.setVel();
	}
	
	public void setVel()
	{
		velocidade = 1000/(nivel+1);
	}
	
	public int getNivel(){return nivel;}
	
	public Jogador getJogador() {return jogador;}
	
	public void caiPeca()
	{
		cenario.caiPeca();
		caiPeca = true;
	}

			
		public void run() 
        {
        	
        	timer_var++;
        	if(loops >= velocidade && !gameOver)
        	{
	        	loops = 0;
	        	if(frame == 0)
	        	{   
	        		limite.showName(jogador.getNome());
	        		cenario.setPecaAtual();
		           	cenario.setProxima(cenario.geraPeca());
	        	}
			    frame++;
			    
			    
			    if(controleRun)
			    {
				    if(proximaPeca)
				    {
				       	cenario.setPermissaoDesce(true);
				    	cenario.adicionaPeca(cenario.getPecaAtual());
				    	proximaPeca=false;
				    }
					limite.repintar();
			  	 	controleRun = false;
			    }
			    
			   else {
			  	 if(frame>0)
			  	 {
					cenario.imprimeMatriz();
					limite.showTime(this.tempo);
					
					if(cenario.descePeca() || caiPeca)
						{
							if(!cenario.perdeu())
							{
								caiPeca = false;
								this.computaPontos(cenario.verificaLinhaCompleta());
								frame = 0;
								proximaPeca = true;
								controleRun = true;
							}
							else
							{
								//timer.cancel();
								gameOver = true;
								JOptionPane.showMessageDialog(null,"Game Over");
									
							}
						}
						
					limite.repintar();
					
			  			 }
			    }
        	}
        	
        	
			limite.showScore(jogador.getPontuacao());
			loops += 200;
			if(timer_var == 5)
		    {
		    	timer_var = 0;
		    	this.atualizaTempo();
		   	}		    		
		    	
	       
           	
           
        }          
               
        public void computaPontos(int quant)
        {
        	jogador.setPontuacao(jogador.getPontuacao()+quant);
        }
    
	
}