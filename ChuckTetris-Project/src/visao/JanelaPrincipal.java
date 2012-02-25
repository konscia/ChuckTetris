package visao;

import controle.ControleTetris;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JanelaPrincipal extends JFrame implements ActionListener
{
	//Referência para a classe principal de controle.
	private ControleTetris controle;
	//Níveis possíveis para o jogo
	private String[] niveis = {"Mané", "Tosco", "Marreco", "Mais ou Menos", "Chuck Norris"};

	//Painel que contém o espaço do jogo
	private JPanel esquerdo;

	//Campos de texto que podem ser alterados durante a partida
	private JTextField pontuacaoTF, nomeTF, tempoTF, campo_nivelTF;
	//Botões que Movimentam as peças
	private JButton left, down, right;
	//Ítens do menu principal
	private JMenuItem chuckTetris, equipe, novoJogo, records, sair;	
		
	public JanelaPrincipal(ControleTetris c) {
		super("Chuck Tetris");

		//Configurações básicas janela
		this.controle = c;
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );		
		this.setLocationRelativeTo(null);
		setSize(320, 360);

		//Configura layout da janela
		this.getContentPane().setLayout(new BorderLayout(20,20));

		//Cria sub-painéis
		this.criaMenu();
		this.criaPainelEsquerdo();
		this.criaPainelInformacoes();

		//Exibe Janela
		setVisible(true);
	}

	private void criaMenu() {
		JMenuBar bar = new JMenuBar();
		setJMenuBar( bar );

		JMenu arquivo = new JMenu("Arquivo");
		bar.add(arquivo);

			novoJogo = new JMenuItem("Novo Jogo");
			novoJogo.addActionListener(this);
			arquivo.add(novoJogo);

			records = new JMenuItem("Recordes");
			records.addActionListener(this);
			arquivo.add(records);

			//Sair
			sair = new JMenuItem("Sair");
			sair.addActionListener(this);
			arquivo.add(sair);

		JMenu opcoes = new JMenu("Opções");
		bar.add(opcoes);

			//Nível
			JMenu nivel = new ItemMenuNivel(controle);
			opcoes.add(nivel);

		JMenu sobre = new JMenu("Sobre");
		bar.add(sobre);

			this.chuckTetris = new JMenuItem("O Chuck Tetris");
			chuckTetris.addActionListener(this);
			sobre.add(chuckTetris);

			//Equipe
			this.equipe = new JMenuItem("Equipe");
			this.equipe.addActionListener(this);
			sobre.add(equipe);
	}

	private void criaPainelInformacoes() {
			//Nome do jogador
			Box box = Box.createVerticalBox();

	      	JLabel nome = new JLabel("Nome");
	      	box.add(nome);

			nomeTF = new JTextField("",10);
	      	nomeTF.setEditable(false);
	      	box.add(nomeTF);	      	
	      	
	      	//Nível
	      	JLabel campo_nivel = new JLabel("Nível");
			box.add(campo_nivel);

			campo_nivelTF = new JTextField(niveis[controle.getNivel()],10);
			campo_nivelTF.setEditable(false);
			box.add(campo_nivelTF);
			
			//Pontuação
			JLabel pontuacao = new JLabel("Iskore");
			box.add(pontuacao);

			pontuacaoTF = new JTextField("0",10);
			pontuacaoTF.setEditable(false);
			box.add(pontuacaoTF);
	
			//Tempo da partida
			JLabel tempo = new JLabel("Tempo");
	     	box.add(tempo);

	     	tempoTF = new JTextField("0",10);
	     	tempoTF.setEditable(false);
			box.add(tempoTF);


			//Movimento da peça
	        left = new JButton("Esquerda");
			left.addActionListener(this);
	     	box.add(left);
	     	
	     	right = new JButton("Direita");
			right.addActionListener(this);
	     	box.add(right);
	     	
	     	down = new JButton("Baixo");
			down.addActionListener(this);
	     	box.add(down);
	     		     	
	     	add(box, BorderLayout.EAST);
	     	box.revalidate();
		}

	private void criaPainelEsquerdo(){
		esquerdo = new PainelEsquerdo(this.controle);
		add(esquerdo, BorderLayout.CENTER);
	}

	/**
	 * Manipula ações dos botões e ítens de menu	 
	 */
	public void actionPerformed( ActionEvent event ) {

		Object botaoPressionado = event.getSource();

		//Movimento da Peça
		if (botaoPressionado == this.left) {
			controle.cenario.paraEsquerda();

		} else if(botaoPressionado == this.right) {
			controle.cenario.paraDireita();

		} else if(botaoPressionado == this.down) {
			controle.caiPeca();

		}
		
		//Algum dos ítens de menu foi pressionado
		if( botaoPressionado == this.chuckTetris){
			Sobre sobre = new Sobre();

		} else if(botaoPressionado == this.equipe){
			JOptionPane.showMessageDialog(null, "Kaléu Caminha\nRobinson Zimmermann\nMurilo Soares Laghi", "Chuck Tetris", JOptionPane.PLAIN_MESSAGE);

		} else if(botaoPressionado == this.novoJogo){
			controle.novoJogo();

		} else if(botaoPressionado == records){
			//Exibe Records

		} else if(botaoPressionado == sair){
			System.exit(0);

		}

	}
	

	/**
	 * Altera a informação de tempo de jogo
	 */
	public void mostraTempo(long tempo) {
		this.tempoTF.setText(""+tempo);
	}

	/**
	 * Altera a informação do Nome do jogador
	 */
	public void mostraNome(String nom) {
		this.nomeTF.setText(nom);
	}

	/**
	 * Altera Pontuação do jogador
	 */
	public void mostraScore(long score) {
		this.pontuacaoTF.setText(""+score);
	}

	/**
	 * Redesenha o espaço do jogo
	 */
	public void repintar() {
		this.esquerdo.repaint();
	}


}