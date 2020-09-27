package br.edu.up.util;

import java.io.IOException;
import java.util.Scanner;

import br.edu.up.domain.Cardapio;
import br.edu.up.domain.Pedido;
import br.edu.up.domain.RelacaoPedidos;

public class Menu
{
	private int opcaoEscolhida;	
	
	Cardapio cardapio = new Cardapio();
	RelacaoPedidos pedidos = new RelacaoPedidos();
	
	Scanner leitorMenu = new Scanner(System.in);
	
	
	public void imprimirMenuPrincipal() throws IOException
	{
		System.out.println("\nPor gentileza, selecione a opção desejada:");
        System.out.println("[1] Mostrar cardápio");
        System.out.println("[2] Cadastrar ou Remover itens");
        System.out.println("[3] Cadastrar novo pedido");
        System.out.println("[4] GERENCIAL: Mostrar pedidos");
        System.out.println("[0] Sair\n");
       
        opcaoMenuEscolhida(opcaoEscolhida);
	}
	
	public void opcaoMenuEscolhida (int opcaoEscolhida) throws IOException
	{
		opcaoEscolhida = leitorMenu.nextInt();
		leitorMenu.nextLine();
		
		switch(opcaoEscolhida) 
		{
	        case 0:
	            System.out.println("\nFinalizando o sistema gerencial.");          
	        break;
	        
	        case 1:
	        	cardapio.atualizarCardapio();
	        	cardapio.imprimirCardapio();           
	            
	            System.out.println("\nPara retornar aperte [0].");
	            int opcaoRetornar = leitorMenu.nextInt();	
	            imprimirMenuPrincipal();
	            
	            
	        break;
	        
	        case 2:
	            System.out.println("Digite a opção desejada:\n");
	            System.out.println("[1] Cadastrar prato");
	            System.out.println("[2] Cadastrar bebida");
	            System.out.println("[3] Cadastrar vinho");
	            System.out.println("[4] Remover prato");
	            System.out.println("[5] Remover bebida");
	            System.out.println("[6] Remover vinho");
	            System.out.println("[0] Voltar");
	            
	            opcaoMenuSecundarioEscolhida();	            
			break;
			
	        case 3:
	            System.out.println("Informe o nome do cliente:");	                       
	            String nomeCliente = leitorMenu.nextLine();
	            
	            System.out.println("Informe o número da mesa:");
	            int numeroMesa = leitorMenu.nextInt();
	                     
	            Pedido pedido = new Pedido(nomeCliente, numeroMesa, cardapio); 
	            
	            int opcaoItemEscolhida = 0;
	            
                do 
                {
                    System.out.println("Selecione uma das opções:");
                    System.out.println("[1] Incluir Item");
                    System.out.println("[2] Excluir Item");
                    System.out.println("[3] Mostrar Cardápio");
                    System.out.println("[4] Finalizar Pedido");
                    System.out.println("[0] Sair"); 
                    
                    opcaoItemEscolhida = leitorMenu.nextInt();
                    
                    gerenciarItemCardapio(pedido, opcaoItemEscolhida);
                    
                }
                while (opcaoItemEscolhida != 0);                  
	        break;
	        
	        case 4:
	            System.out.println("Digite a opção:");
	            System.out.println("[1] Imprimir pedido");
	            System.out.println("[0] Sair");
	            
	            int opcaoImprimirPedido = leitorMenu.nextInt();
	            
	            switch(opcaoImprimirPedido)
	            {
	                case 0:                            
	                break;
	                
	                case 1:
	                    System.out.println("Digite o número da mesa:");
	                    int idMesaPedido = leitorMenu.nextInt();	                    
	                    pedidos.imprimirPedido(idMesaPedido);
	                    
	                    System.out.println("\nPara retornar aperte [0].\n");
	                    leitorMenu.nextLine();
	                break; 
	                
	                default:
	                    System.out.println("\nOpção inválida.\n");
	                break;
	            }        
	       break;
	       
	       default:
	            System.out.println("\nOpção inválida.\n");
	       break;    
		} 
		while (opcaoEscolhida != 0);
	
		leitorMenu.close();
	}
	

	public void opcaoMenuSecundarioEscolhida() throws IOException 
	{
		int opcaoEscolhidaMenuSecundario = -1;
		opcaoEscolhidaMenuSecundario = leitorMenu.nextInt();
        
        switch(opcaoEscolhidaMenuSecundario) 
        {
	        case 0:
	        break;
	        
	        case 1:
	            System.out.println("\nInforme o nome do prato:\n");
	            leitorMenu.nextLine();
	            String nomePratoIncluir = leitorMenu.nextLine();
	            
	            System.out.println("\nInforme o preço do prato:\n");
	            String precoPratoIncluir = leitorMenu.nextLine();
	            leitorMenu.nextLine();
	            
	            cardapio.adicionarPrato(nomePratoIncluir, precoPratoIncluir);
	            imprimirMenuPrincipal();
	        break;
	        
	        case 2:
	            System.out.println("\nInforme o nome da bebida:\n");
	            leitorMenu.nextLine();
	            String nomeBebidaIncluir = leitorMenu.nextLine();
	            
	            System.out.println("\nInforme o preço da bebida:\n");
	            String precoBebidaIncluir = leitorMenu.nextLine();
	            leitorMenu.nextLine();
	            
	            cardapio.adicionarBebida(precoBebidaIncluir, nomeBebidaIncluir);
	            imprimirMenuPrincipal();
	        break;
	        
	        case 3:
	            System.out.println("\nInforme o nome do vinho:\n");
	            leitorMenu.nextLine();
	            String nomeVinhoIncluir = leitorMenu.nextLine();
	            
	            System.out.println("Informe o preço do vinho:");
	            String precoVinhoIncluir = leitorMenu.nextLine();
	            leitorMenu.nextLine();
	            
	            cardapio.adicionarVinho(precoVinhoIncluir, nomeVinhoIncluir);
	            imprimirMenuPrincipal();
	        break;
	        
	        case 4:
	            System.out.println("\nInforme o nome do prato a ser removido:\n");
	            leitorMenu.nextLine();
	            String nomePratoExcluir = leitorMenu.nextLine();
	            
	            cardapio.removerPrato(nomePratoExcluir);
	            imprimirMenuPrincipal();
	        break;
	        
	        case 5:
	            System.out.println("\nInforme o nome da bebida a ser removida:\n");
	            leitorMenu.nextLine();
	            String nomeBebidaExcluir = leitorMenu.nextLine();
	            
	            cardapio.removerBebida(nomeBebidaExcluir);
	            imprimirMenuPrincipal();
	        break;
	        
	        case 6:
	            System.out.println("\nDigite o nome do vinho a ser removido:\n");
	            leitorMenu.nextLine();
	            String nomeVinhoExcluir = leitorMenu.nextLine();
	            
	            cardapio.removerVinho(nomeVinhoExcluir);
	            imprimirMenuPrincipal();
	        break;
	        
	        default:
	            System.out.println("----  Opção inválida.  ----");	
	        break;
        }
        
        cardapio.atualizarCardapio();
	}
	
	public void gerenciarItemCardapio(Pedido pedido, int opcaoItemEscolhida) throws IOException
	{		
		String observacao = " ";
		int identificacao = 0;
		int quantidade = 0;
		
        switch(opcaoItemEscolhida) 
        {
            case 0:
            break;
            
            case 1:
                System.out.println("Digite o identificador do item a ser adicionado:");                
                identificacao = leitorMenu.nextInt();
                
                System.out.println("Informe a quantidade:");
                quantidade = leitorMenu.nextInt();
                
                System.out.println("Deseja fazer alguma observação? \n[1] SIM [2] NÃO");
                int opcaoObservacaoEscolhida = leitorMenu.nextInt();
                
                if(opcaoObservacaoEscolhida == 1) 
                {
                    System.out.println("Digite a observação:");
                    observacao = leitorMenu.nextLine();
                }
                
                pedido.incluirItem(identificacao, quantidade, observacao);
                break;
            case 2:
                System.out.println("Digite o identificador do item a ser removido:");                
                identificacao = leitorMenu.nextInt();
                
                System.out.println("Informe a quantidade:");
                quantidade = leitorMenu.nextInt();
                
                pedido.removerItem(identificacao, quantidade);
            break;
            
            case 3:
                cardapio.imprimirCardapio();
                System.out.println("\nPara retornar aperte [0].\n");
                leitorMenu.nextLine();
                imprimirMenuPrincipal();
            break;
            
            case 4:
            	pedidos.incluirPedido(pedido);               
            break;
            
            default:
                System.out.println("Opção inválida.");
            break;
        }
	}
	
	public int getOpcao() 
	{
		return opcaoEscolhida;
	}

	public void setOpcao(int opcaoEscolhida) 
	{
		this.opcaoEscolhida = opcaoEscolhida;
	}
	
	
	
	
	
	
	
	
}
