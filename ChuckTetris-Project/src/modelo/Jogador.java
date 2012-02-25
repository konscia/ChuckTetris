package modelo;

import java.io.Serializable;

public class Jogador implements Serializable {
	private String nome;
	private long pontuacao;
	
	public Jogador(String n) {
		nome=n;
		pontuacao=0;
	}
	
	public void setNome(String n)
	{
		nome=n;
	}
	public String getNome() {return nome;}	
	public void setPontuacao(long p)
	{
		pontuacao=p;
	}
	public long getPontuacao() {return pontuacao;}
	
	public String toString() {
		return String.format(". %s\nPontuação: %d\n\n",
			getNome(), getPontuacao());
	}
}