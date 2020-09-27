package br.edu.up.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Pedido 
{	
	Cardapio cardapio;
	
	private String nomeCliente;
	private int numeroMesa;
	private double totalPedido;
	
	private List<Item> listaCombinada = new ArrayList<Item>();
	private List<Item> pedido = new ArrayList<Item>();
	private List<Pedido> relatorioPedidos = new ArrayList<Pedido>();
	
	
	public Pedido(String nomeCliente, int numeroMesa, Cardapio cardapio) throws IOException 
	{
        this.setNomeCliente(nomeCliente);
        this.setNumeroMesa(numeroMesa);
        this.cardapio = cardapio;
        this.setTotalPedido(0.00);       
        this.listaCombinada.addAll(cardapio.retornarPratos());
        this.listaCombinada.addAll(cardapio.retornarBebidas());
        this.listaCombinada.addAll(cardapio.retornarVinhos());
    }

	public void imprimir() 
	{        
        System.out.println("Comanda: ");        
        System.out.println("Cliente: " + this.nomeCliente);        
        System.out.println("Mesa: " + this.numeroMesa);        
        System.out.println("Pedidos: ");
        
        for(int i = 0; i < pedido.size(); i++) 
        {
            Item item = pedido.get(i);
            String nomeItem = item.getNome();
            Double precoItem = item.getPreco();
            System.out.println(nomeItem + ' ' + precoItem);
            
            if(item.getObservacao() != " ")
            {
            	System.out.println("Observação: " + item.getObservacao());
            }
            
        }
        
        System.out.println("------------------------------------");
        System.out.println("Total: " + totalPedido);
    }
	
	public void incluirItem(int identificador, int quantidade, String obs) 
	{
        for(int i = 0; i < listaCombinada.size(); i++)
        {
            Item item = listaCombinada.get(i);
            if(item.getIdentificacao() == identificador) 
            {
                for(int j = 0; j < quantidade; j++) 
                {
                    item.setObservacao(obs);
                    pedido.add(item);
                    totalPedido += item.getPreco();
                }
                
                item.setObservacao(" ");
            }
        }
        
        imprimir();
    }
	
	public void removerItem (int identificador, int quantidade) 
	{
        for(int i = 0; i < listaCombinada.size(); i++) 
        {
            Item item = listaCombinada.get(i);
            if(item.getIdentificacao() == identificador) 
            {
                for(int j = 0; j < pedido.size(); j++) 
                {
                    Item itemPedido = pedido.get(j);
                    int contador = 0;
                    if(itemPedido == item) 
                    {
                        pedido.remove(j);
                        contador++;
                        totalPedido -= item.getPreco();
                    }
                    
                    if(contador >= quantidade) 
                    {
                        break;
                    }                    
                }
            }
        }
        
        imprimir();
    }

	public String getNomeCliente() 
	{
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) 
	{
		this.nomeCliente = nomeCliente;
	}

	public int getNumeroMesa() 
	{
		return numeroMesa;
	}

	public void setNumeroMesa(int numeroMesa) 
	{
		this.numeroMesa = numeroMesa;
	}

	public double getTotalPedido() 
	{
		return totalPedido;
	}

	public void setTotalPedido(double totalPedido) 
	{
		this.totalPedido = totalPedido;
	}

	public List<Item> getPedido() 
	{
		return pedido;
	}

	public void setPedido(List<Item> pedido) 
	{
		this.pedido = pedido;
	}

	
	public List<Pedido> getRelatorioPedidos() 
	{
		return relatorioPedidos;
	}
	

	public void setRelatorioPedidos(List<Pedido> relatorioPedidos) 
	{
		this.relatorioPedidos = relatorioPedidos;
	}

	
	public List<Item> getItems()
	{
		return this.pedido;
	}

	public void incluirPedido(Pedido pedidoRealizado) 
	{
		this.pedido = (List<Item>) pedidoRealizado;
		
	}
	
}
