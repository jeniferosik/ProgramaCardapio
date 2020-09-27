package br.edu.up.domain;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RelacaoPedidos 
{
	private List<Pedido> pedidos = new ArrayList<Pedido>();
		
	public void incluirPedido(Pedido pedido) throws IOException 
	{
        this.pedidos.add(pedido);
        
        SimpleDateFormat formatted_date = new SimpleDateFormat("yyyy-mm-dd-HH-mm");
        String date = formatted_date.format(new Date()).replace(' ','-');
        
        FileWriter gerarPedido = new FileWriter("C:\\Users\\Jenifer\\OneDrive\\Faculdade\\1º Ano\\2º Semestre\\Desenvolvimento Software I\\Atividades\\Arquivos\\Mesa-"+pedido.getNumeroMesa()+"-"+date+".csv", true);
        PrintWriter gravadorPedido = new PrintWriter(gerarPedido);
        gravadorPedido.println("Comanda: ");        
        gravadorPedido.println("Cliente: " + pedido.getNomeCliente());        
        gravadorPedido.println("Mesa: " + pedido.getNumeroMesa());        
        gravadorPedido.println("Pedido: ");
        
        List<Item> items = pedido.getItems();
        
        for(int i = 0; i < items.size(); i++)
        {
            Item itemPedido = items.get(i);
            String nomeItem = itemPedido.getNome();
            double precoItem = itemPedido.getPreco();
            gravadorPedido.println(nomeItem + ' ' + precoItem);
            
            if(itemPedido.getObservacao() != " ")
            {
            	gravadorPedido.println("Observação: " + itemPedido.getObservacao());
            }
            	
        }
        
        gravadorPedido.println("---------------------------------------");
        gravadorPedido.println("Total: R$ " + pedido.getTotalPedido());
        gravadorPedido.close();
    }
	
	public void removerPedido(int numeroMesa) 
	{
        for(int i = 0; i < pedidos.size(); i++) 
        {
            Pedido pedido = pedidos.get(i);
            
            if(pedido.getNumeroMesa() == numeroMesa) 
            {
                 pedidos.remove(i);                 
            }
        }
    }
	
	public void imprimirPedido(int numeroMesa) 
	{
        for(int i = 0; i < pedidos.size(); i++) 
        {
            Pedido pedido = pedidos.get(i);
            
            if(pedido.getNumeroMesa() == numeroMesa) 
            {
                 pedido.imprimir();                 
            }
        }
    }
	
	public void imprimirTodos() 
	{
        for(int i = 0; i < pedidos.size(); i++) 
        {
            Pedido pedido = pedidos.get(i);
            pedido.imprimir();
        }
        
        if(pedidos.size() == 0)
        {
            System.out.println("\n--------  Nenhum pedido cadastrado.  --------\n");
        }
    }	
}
