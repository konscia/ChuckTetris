package controle;

import java.util.Timer;
import java.util.TimerTask;
import visao.JanelaPrincipal;

public class SecondsController extends TimerTask {

	private JanelaPrincipal janela;
	private Timer timer;

	private long tempo;

	public SecondsController(JanelaPrincipal janela){
		super();
		this.janela = janela;		
		this.tempo = 0;
		this.timer = new Timer();
		this.timer.scheduleAtFixedRate(this, 0, 1000);
	}
	public void run() {
		tempo++;
		janela.mostraTempo(tempo);
	}

	public void setTempo(int tempo){
		this.tempo = tempo;
	}
	
}
