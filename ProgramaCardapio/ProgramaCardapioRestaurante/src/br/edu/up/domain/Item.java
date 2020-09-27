package br.edu.up.domain;

public class Item 
{
	private int identificacao;
	private Double preco;
	private String nome;
	private String observacao;
	
	
	public Item(int id, String nome, Double preco) 
	{
		this.identificacao = id;
        this.nome = nome;
        this.preco = preco;
	}

	public int getIdentificacao() 
	{
		return identificacao;
	}
	
	public void setIdentificacao(int identificacao) 
	{
		this.identificacao = identificacao;
	}
	
	public Double getPreco() 
	{		
		return preco;
	}
	
	public void setPreco(Double preco) 
	{
		this.preco = preco;
	}
	
	public String getNome() 
	{
		return nome;
	}
	
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	
	public String getObservacao() 
	{
		return observacao;
	}
	
	public void setObservacao(String observacao) 
	{
		this.observacao = observacao;
	}
	
	
}
