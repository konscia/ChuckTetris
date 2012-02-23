import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Limite
{
	private FramePrincipal framePrincipal;
	private ControleTetris controle;
	
	public Limite(ControleTetris ctr)
	{
		controle=ctr;
		framePrincipal = new FramePrincipal(ctr);
		framePrincipal.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		framePrincipal.setSize( 330, 360 );
		framePrincipal.setLocationRelativeTo(null);
        framePrincipal.setVisible( true ); 
       
	}
	

	private class FramePrincipal extends JFrame implements ActionListener
	{
		
		private JMenuBar menu;
		private JMenuItem item;
		private JRadioButtonMenuItem itensNiveis[];
		private ButtonGroup grupoNiveis;
		private JLabel pontuacao, nome, tempo, campo_nivel;
		private Container container;
		private GridLayout layout2;
		private GridBagLayout layout;
		private Cenario cenario;
		private JPanel esquerdo;
		private Box box;
		private JTextField pontuacaoTF, nomeTF, tempoTF, campo_nivelTF;
		private JButton pause, left, down, right;
		private JPanel direito;
		private ControleTetris ctr;
		private String[] n;
		
		public FramePrincipal(ControleTetris c)
		{
			super("Chuck Tetris");
			ctr=c;
			container = getContentPane();
			container.setLayout(new BorderLayout(20,20));
			
			
			//JMenus
			JMenu arquivo = new JMenu("Arquivo");
			arquivo.setMnemonic('A');
			JMenu opcoes = new JMenu("Opções");
			opcoes.setMnemonic('O');
			JMenu sobre = new JMenu("Sobre");
			sobre.setMnemonic('S');
			
			// Novo jogo
			JMenuItem novoJogo = new JMenuItem("Novo Jogo");
			novoJogo.setMnemonic('N');
			arquivo.add(novoJogo);
			novoJogo.addActionListener( 
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
								controle.novoJogo();
					}
				}
			);
			
			//Recordes
			JMenuItem recordes = new JMenuItem("Recordes");
			recordes.setMnemonic('R');
			arquivo.add(recordes);
//			recordes.addActionListener(  )
			
			//Sair
			JMenuItem sair = new JMenuItem("Sair");
			sair.setMnemonic('S');
			sair.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.exit(0);
					}
				}
			);
			arquivo.add(sair);
			
			//Nível
			JMenu nivel = new JMenu("Nível");
			nivel.setMnemonic('V');
			opcoes.add(nivel);
			
			
			String[] niveis = {"Mané", "Tosco", "Marreco", "Mais ou Menos", "Chuck Norris"};
			n=niveis;
			grupoNiveis = new ButtonGroup();
			itensNiveis = new JRadioButtonMenuItem[niveis.length];
			for(int i=0; i<niveis.length; i++)
			{
				itensNiveis[i] = new JRadioButtonMenuItem(niveis[i]);
				nivel.add(itensNiveis[i]);
				grupoNiveis.add(itensNiveis[i]);
				
				itensNiveis[i].addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event)
						{
							for(int i=0; i<itensNiveis.length; i++)
								if(itensNiveis[i].isSelected())
									setNivel(i);
						}
					}
					);
			}
			itensNiveis[0].setSelected(true);
			
			
			
			JMenuBar bar = new JMenuBar();
	      	setJMenuBar( bar );
	      	bar.add(arquivo);
	      	bar.add(opcoes);
	      	bar.add(sobre);
	      	
	      	//Sobre/O Chuck Tetris
	      	JMenuItem chuckTetris = new JMenuItem("O Chuck Tetris");
	      	chuckTetris.setMnemonic('C');
			sobre.add(chuckTetris);
			chuckTetris.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						JFrame sobreChuck=new Sobre();
						}
				}
			);
			
			//Equipe
			JMenuItem equipe = new JMenuItem("Equipe");
	      	equipe.setMnemonic('C');
			sobre.add(equipe);
			equipe.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						JOptionPane.showMessageDialog(null, "Kaléu Caminha\nRobinson Zimmermann\nMurilo Soares Laghi", "Chuck Tetris", JOptionPane.PLAIN_MESSAGE);
						}
				}
			);
			
			
			//Espaço de jogo
			esquerdo = new PainelEsquerdo();
			add(esquerdo, BorderLayout.CENTER);
				
			criaBoxDados();		
			     	
	     	setSize(320, 360);
			setVisible(true);
			
			
		}
		
		public void setNivel(int nivel)
		{
			ctr.setNivel(nivel);
			box.removeAll();
			criaBoxDados();
			box.revalidate();
		}
		
	
		
	
		public void criaBoxDados() {
			
			//Nome do jogador
			box = Box.createVerticalBox();
	      	nome = new JLabel("Nome");
	      	box.add(nome);
	      	if(ctr.getJogador()==null)
	      		nomeTF = new JTextField("",10);
	      	else
	      		nomeTF = new JTextField(ctr.getJogador().getNome(),10);
	      	nomeTF.setEditable(false);
	      	box.add(nomeTF);
	      	
	      	
	      	//Nível
	      	campo_nivel = new JLabel("Nível");
			box.add(campo_nivel);
			campo_nivelTF = new JTextField(n[ctr.getNivel()],10);
			campo_nivelTF.setEditable(false);
			box.add(campo_nivelTF);
			
			//Pontuação
			pontuacao = new JLabel("Iskore");
			box.add(pontuacao);
			if(ctr.getJogador()==null)
				pontuacaoTF = new JTextField("0",10);
			else
				pontuacaoTF = new JTextField(""+ctr.getJogador().getPontuacao(),10);
			pontuacaoTF.setEditable(false);
			box.add(pontuacaoTF);
	
			//Tempo da partida
			tempo = new JLabel("Tempo");
	     	box.add(tempo);
	     	tempoTF = new JTextField("0",10);
	     	tempoTF.setEditable(false);
			box.add(tempoTF);
	     	
	     	/*pause = new JButton ("Pause");
	     	box.add(pause);
	     	pause.addActionListener(this);*/
	     	     	
	     	//newPeca = new JButton ("Nova Peca");
	     	//box.add(newPeca);
	     	//newPeca.addActionListener(this);
	     	left = new JButton("Esquerda");
	     	box.add(left);
	     	left.addActionListener(this);
	     	right = new JButton("Direita");
	     	box.add(right);
	     	right.addActionListener(this);
	     	down = new JButton("Baixo");
	     	box.add(down);
	     	down.addActionListener(this);
	     	
	     	add(box, BorderLayout.EAST);

	     	box.revalidate();
		}
		
					
		public void actionPerformed( ActionEvent event ) {
			
			if (event.getSource() == left) 
			{
				controle.cenario.paraEsquerda();	
			}
			else if(event.getSource() == right)
			{
					controle.cenario.paraDireita();
			}
			else if(event.getSource() == down)
			{
					controle.caiPeca();
			}
		
		
			
        }
        
        public void mostraTempo(long tempo)
		{
			this.tempoTF.setText(""+tempo);
		}
		
		public void mostraNome(String nom)
		{
			this.nomeTF.setText(nom);
		}
		
		public void mostraScore(long score)
		{
			this.pontuacaoTF.setText(""+score);
		}
			   
	  }
		
		public void showTime(long tempo)
		{
			framePrincipal.mostraTempo(tempo);
		}
		
		public void showName(String name)
		{
			framePrincipal.mostraNome(name);			
		}
		
		public void showScore(long s)
		{
			framePrincipal.mostraScore(s);
		}
		
	//	private JPanel esquerdo;
	
/*	private class AbreRecords implements ActionListener {
		
		
		
	}*/
	
	
	
		private class PainelEsquerdo extends JPanel
		{
		
			
				public PainelEsquerdo()
				{
					super();
					setSize(300, 150);
					setVisible(true);
									
				}
				
				public void paint(Graphics g)
				{
					super.paint(g);
									
					
					int[][] grade = new int[20][10];
					grade = controle.cenario.getCenario();
					
					for(int i=0; i<grade.length; i++)
						for(int j=0; j<grade[0].length; j++)
						{
							if(grade[i][j]==0)
								g.setColor(Color.black);
							else if(grade[i][j]==1||grade[i][j]==5)
								g.setColor(Color.red);
							else if(grade[i][j]==2||grade[i][j]==6)
								g.setColor(Color.blue);
							else if(grade[i][j]==3||grade[i][j]==7)
								g.setColor(Color.green);
							else if(grade[i][j]==4||grade[i][j]==8)
								g.setColor(Color.yellow);
								
							g.fillRect(15*j+1, 15*i+1, 15, 15);
						}
											
				}
				
				
				
			
					
		}
		
		
		
			private class Sobre extends JFrame {
				public Sobre() {
				super("O Chuck Tetris");
				Icon chuck = new ImageIcon("chuck.jpg");
				JLabel fotoChuck = new JLabel(chuck);
				setLayout(new FlowLayout());
				add(fotoChuck);
				add(new JLabel("Uma história de sucesso..."));
				add(new JLabel("Esse jogo mudou minha vida!"));
				setSize(300,460);
				setVisible(true);
				setResizable(false);
				setLocationRelativeTo(null);
			}
		}
		
		
	public void repintar()
	{
		this.framePrincipal.esquerdo.repaint();
	}	
		
}