import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Records {
			
		private Vector<Jogador> osJogadores;
		private Vector<Jogador> osJogadores2;
		private ObjectOutputStream saida;
		private ObjectInputStream entrada;
		private static final String NOMEARQUIVO = "records.txt";
		private Jogador jogador;
		
		public Records() {
			osJogadores = new Vector<Jogador>();
			abreArquivo();
		}
			
		public  void abreArquivo() {
	   		try
	   		{
	   		   	entrada = new ObjectInputStream( new FileInputStream( NOMEARQUIVO ) );
	      		lerDados(entrada);
	      	}
	      	catch ( FileNotFoundException fileNotFoundException )
	      	{
	         	JOptionPane.showMessageDialog(null, "Arquivo vazio", "Chuck Tetris", JOptionPane.ERROR_MESSAGE);
	         	criarArquivo();
	      	}
	      	catch (IOException ioException) 
	      	{
	      	//	JOptionPane.showMessageDialog(null, "Erro na leitura", "Chuck Tetris", JOptionPane.ERROR_MESSAGE);
	      		criarArquivo();
	      	}
	   	}
	   	public  void criarArquivo()
	   {
	      try
	      {
	         saida = new ObjectOutputStream( new FileOutputStream( NOMEARQUIVO ) );
	      }
	      catch ( IOException e )
	      {
	         JOptionPane.showMessageDialog(null, "Erro no arquivo", "Chuck Tetris", JOptionPane.ERROR_MESSAGE);
	      }
	   } 
	   
	   	public  void lerDados(ObjectInputStream entrada) {
	   		Jogador jogador;
	   		try 
	  		{
	         	while ( true ) {
	         		jogador = (Jogador)entrada.readObject();
	            	osJogadores.add(jogador);
	         }
	      }
		  catch ( EOFException endOfFileException )
	      {
	         return;
	      }
	      catch ( ClassNotFoundException classNotFoundException )
	      {
	      	JOptionPane.showMessageDialog(null, "Nao foi possivel criar o objeto", "Chuck Tetris", JOptionPane.ERROR_MESSAGE);
	      }
	      catch ( IOException ioException )
	      {
	      	JOptionPane.showMessageDialog(null, "Erro durante a leitura do arquivo", "Chuck Tetris", JOptionPane.ERROR_MESSAGE);
	      }
	   }
	
	   	
		public  void fecharArquivo() {
			try {
	   			if ( entrada != null )
	        		entrada.close();
	   			if ( saida != null)
	   				saida.close();
			}
			catch (IOException e ) {
				JOptionPane.showMessageDialog(null, "Erro no fechamento do arquivo", "Chuck Tetris", JOptionPane.ERROR_MESSAGE);
			}
	   	}
	   	public  void salvarJogador(Jogador jogador) {
	   		osJogadores.add(jogador);
	   		JButton salvar=new JButton("Salvar");
	   		JFrame salvarRecord = new JFrame("Salvar recorde");
	   		salvarRecord.setLayout(new GridLayout(2,1));
			salvarRecord.add(new JLabel("Sua pontuação foi: "+jogador.getPontuacao()));
			salvarRecord.add(salvar);
			salvar.addActionListener( 
				new ActionListener() {
					public void actionPerformed (ActionEvent event) {
						try {
							abreArquivo();
   							criarArquivo();
   							for (Jogador jogador: osJogadores) 
   								saida.writeObject( jogador );
        					fecharArquivo();  
   						}
   						catch (IOException e) {
   							JOptionPane.showMessageDialog(null, "Erro ao salvar os dados", "Chuck Tetris", JOptionPane.ERROR_MESSAGE);
   						}                     
					}
				}
			 );
			salvarRecord.setSize(250,150);
			salvarRecord.setResizable(false);
			salvarRecord.setVisible(true);
	   		//osJogadores.add(jogador);
   		
   	}
   	
/*   	public void cadastrarRecord() {
   		
   		salvarJogador();
   	}*/
   	
   	public  void listarRecordes() {
   		if (!osJogadores.isEmpty()) {
   			Vector <Jogador> osJogadores2=new Vector<Jogador>();
   			long maior=osJogadores.elementAt(0).getPontuacao();
			for(int i=0; i<osJogadores.size(); i++)
   			{
   				for(int j=i+1; j<osJogadores.size(); j++)
   					if(osJogadores.elementAt(i).getPontuacao()<=osJogadores.elementAt(j).getPontuacao())
   					{
   						Jogador aux=osJogadores.elementAt(i);
   						osJogadores.set(i, osJogadores.elementAt(j));
   						osJogadores.set(j, aux);
   					}
   			}
   			 			
   			String rank="";
   		   	for (int i=0; i<osJogadores.size(); i++){
   		   		if(i>4)
   		   			break;
   				rank=rank +(i+1)+ "º "+osJogadores.elementAt(i);
   		   	}
   			JOptionPane.showMessageDialog(null, "Ranking: \n\n"+rank, "Chuck Tetris", JOptionPane.PLAIN_MESSAGE);
   			} else
   			JOptionPane.showMessageDialog(null, "Não há dados ", "Chuck Tetris", JOptionPane.ERROR_MESSAGE);
   	}   	
}
