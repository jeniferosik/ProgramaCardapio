package br.edu.up.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cardapio 
{
	private List<Item> listaPratos = new ArrayList<Item>();
	private List<Item> listaBebidas = new ArrayList<Item>();
	private List<Item> listaVinhos = new ArrayList<Item>();
	
	public static java.text.DecimalFormat formato = new java.text.DecimalFormat("#,##.00"); 
	
	File arquivoPratos = new File("C:\\Users\\Jenifer\\OneDrive\\Faculdade\\1º Ano\\2º Semestre\\Desenvolvimento Software I\\Atividades\\Arquivos\\pratos-tabulados.csv");
	File arquivoBebidas = new File("C:\\Users\\Jenifer\\OneDrive\\Faculdade\\1º Ano\\2º Semestre\\Desenvolvimento Software I\\Atividades\\Arquivos\\bebidas-tabuladas.txt");
	File arquivoVinhos = new File("C:\\Users\\Jenifer\\OneDrive\\Faculdade\\1º Ano\\2º Semestre\\Desenvolvimento Software I\\Atividades\\Arquivos\\vinhos-tabulados.txt");

	
	public List<Item> retornarPratos() 
	{
        return this.listaPratos;
    }
    
	public List<Item> retornarBebidas() 
	{
        return this.listaBebidas;
    }
	
	public List<Item> retornarVinhos()
	{
        return this.listaVinhos;
    }
	
	public void atualizarCardapio() throws FileNotFoundException 
	{
		int identificacao = 0;
		
		listaPratos.clear();
		listaBebidas.clear();
		listaVinhos.clear();
		
		//PRATOS:
		
		Scanner leitorPratos = new Scanner(arquivoPratos, "UTF-8");
		leitorPratos.nextLine();
		
        while (leitorPratos.hasNext()) 
        {
            identificacao++;
            
            String linhaArquivoPrato = leitorPratos.nextLine();
            String[] partesArquivoPrato = linhaArquivoPrato.split(";");
            listaPratos.add(new Item(identificacao, partesArquivoPrato[0], Double.parseDouble(partesArquivoPrato[1].replaceAll(",","."))));
        
        }
            
        //BEBIDAS:
            
        Scanner leitorBebidas = new Scanner(new FileInputStream(arquivoBebidas), "UTF-8");
        leitorBebidas.nextLine();
        
        while (leitorBebidas.hasNext()) 
        {
            identificacao++;
            
            String linhaArquivoBebida = leitorBebidas.nextLine();
            String[] partesArquivoBebida = linhaArquivoBebida.split("\\t+");        
            listaBebidas.add(new Item(identificacao, partesArquivoBebida[1], Double.parseDouble(partesArquivoBebida[0].replaceAll(",","."))));
        }
        
        
        //VINHOS
        
        Scanner leitorVinhos = new Scanner(new FileInputStream(arquivoVinhos), "UTF-8");
        leitorVinhos.nextLine();
        
        while (leitorVinhos.hasNext()) 
        {
            identificacao++;
            
            String linhaArquivoVinho = leitorVinhos.nextLine();
            String[] partesArquivoVinho = linhaArquivoVinho.split("\\t+");            
            listaVinhos.add(new Item(identificacao, partesArquivoVinho[1], Double.parseDouble(partesArquivoVinho[0])));           
        }
        
        
        leitorPratos.close();
        leitorBebidas.close();
        leitorVinhos.close();        
	}
	
	public void imprimirCardapio()
	{
		   
		
		System.out.println("\n-----------------PRATOS--------------------\n");
		
		for (int i = 0; i < listaPratos.size(); i++) 
		{
			Item prato = listaPratos.get(i);
			
            System.out.println(prato.getIdentificacao() + ") " + prato.getNome() + " - " + "R$" + prato.getPreco());
		}
		
		
		System.out.println("\n----------------BEBIDAS--------------------\n");
		
		for (int i = 0; i < listaBebidas.size(); i++) 
		{
			Item bebida = listaBebidas.get(i);
			
            System.out.println(bebida.getIdentificacao() + ") " + bebida.getNome() + " - " + "R$" + bebida.getPreco());
		}
		
		System.out.println("\n-----------------VINHOS--------------------\n");
		
		for (int i = 0; i < listaVinhos.size(); i++) 
		{
			Item vinho = listaVinhos.get(i);
						
            System.out.println(vinho.getIdentificacao() + ") " + vinho.getNome() + " - " + "R$" + vinho.getPreco());
		}
	}
	
	public void adicionarPrato(String nomePratoIncluir, String precoPratoIncluir) throws IOException
	{
		FileWriter arquivoIncluirPrato = new FileWriter(arquivoPratos, true);	
		PrintWriter gravadorPrato = new PrintWriter(arquivoIncluirPrato);
		
		gravadorPrato.println(nomePratoIncluir + ';' + precoPratoIncluir);
		gravadorPrato.close();
		
		atualizarCardapio();
		System.out.println("\n------  Cardápio atualizado com sucesso.    -----\n");
        
        imprimirCardapio();
	}
	
	public void adicionarBebida(String precoBebidaIncluir, String nomeBebidaIncluir) throws IOException
	{
		FileWriter arquivoIncluirBebida = new FileWriter(arquivoBebidas, true);
		
		PrintWriter gravadorBebida = new PrintWriter(arquivoIncluirBebida);		
		gravadorBebida.println(precoBebidaIncluir + "	" + nomeBebidaIncluir );
		gravadorBebida.close();
		
		atualizarCardapio();
		System.out.println("\n------  Cardápio atualizado com sucesso.    -----\n");
        
        imprimirCardapio();
	}
	
	public void adicionarVinho(String precoVinhoIncluir, String nomeVinho) throws IOException
	{
		FileWriter arquivoIncluirVinho = new FileWriter(arquivoVinhos, true);
		
		PrintWriter gravadorVinho = new PrintWriter(arquivoIncluirVinho);		
		gravadorVinho.println(precoVinhoIncluir + "	" + nomeVinho);
		gravadorVinho.close();
		
		atualizarCardapio();
		System.out.println("\n------  Cardápio atualizado com sucesso.    -----\n");
        
        imprimirCardapio();
	}
	
	public void removerPrato(String pratoExcluir) throws IOException
	{
		File arquivoPratosTemporario = new File("C:\\Users\\Jenifer\\OneDrive\\Faculdade\\1º Ano\\2º Semestre\\Desenvolvimento Software I\\Atividades\\Arquivos\\pratos-temporario.csv");
		
		Scanner leitorPratos = new Scanner(new FileInputStream(arquivoPratos));
		FileWriter arquivoPratosTemporarioAtualizado = new FileWriter(arquivoPratosTemporario, true);
		
		PrintWriter gravadorPrato = new PrintWriter(arquivoPratosTemporarioAtualizado);
        
        while (leitorPratos.hasNext()) 
        {
            String linhaPratos = leitorPratos.nextLine();
            String[] partesPratos = linhaPratos.split(";");
            
            if(!partesPratos[0].equals(pratoExcluir)) 
            {
            	gravadorPrato.println(linhaPratos);
            }
        }    
        
        gravadorPrato.close();        
        leitorPratos.close();
        arquivoPratosTemporarioAtualizado.close();
        
        arquivoPratos.delete();
        arquivoPratosTemporario.renameTo(arquivoPratos);
        
        atualizarCardapio();        
        System.out.println("\n-----   O prato foi removido com sucesso! Seu cardápio agora está atualizado.     -----\n");
        
        imprimirCardapio();
	}
	
	public void removerBebida(String bebidaExcluir) throws IOException
	{
		File arquivoBebidasTemporario = new File("C:\\Users\\Jenifer\\OneDrive\\Faculdade\\1º Ano\\2º Semestre\\Desenvolvimento Software I\\Atividades\\Arquivos\\bebidas-tabuladas-temporario.txt");
		
		Scanner leitorBebidas = new Scanner(new FileInputStream(arquivoBebidas));
		FileWriter arquivoBebidasTemporarioAtualizado = new FileWriter(arquivoBebidasTemporario, true);
		
		PrintWriter gravadorBebida = new PrintWriter(arquivoBebidasTemporarioAtualizado);
        
        while (leitorBebidas.hasNext()) 
        {
            String linhaBebidas = leitorBebidas.nextLine();
            String[] partesBebidas = linhaBebidas.split("\\t+");
            
            if(!partesBebidas[1].equals(bebidaExcluir)) 
            {
            	gravadorBebida.println(linhaBebidas);
            }
        }    
        
        gravadorBebida.close();        
        leitorBebidas.close();
        arquivoBebidasTemporarioAtualizado.close();
        
        arquivoBebidas.delete();
        arquivoBebidasTemporario.renameTo(arquivoBebidas);
        
        atualizarCardapio();        
        System.out.println("\n-----   A sua bebida foi removido com sucesso! Seu cardápio agora está atualizado.     -----\n");
        
        imprimirCardapio();
	}
	
	public void removerVinho(String vinhoExcluir) throws IOException
	{
		File arquivoVinhosTemporario = new File("C:\\Users\\Jenifer\\OneDrive\\Faculdade\\1º Ano\\2º Semestre\\Desenvolvimento Software I\\Atividades\\Arquivos\\vinhos-tabulados-temporario.txt");
		
		Scanner leitorVinhos = new Scanner(new FileInputStream(arquivoVinhos));
		FileWriter arquivoVinhosTemporarioAtualizado = new FileWriter(arquivoVinhosTemporario, true);
		
		PrintWriter gravadorVinho = new PrintWriter(arquivoVinhosTemporarioAtualizado);
        
        while (leitorVinhos.hasNext()) 
        {
            String linhaVinhos = leitorVinhos.nextLine();
            String[] partesVinhos = linhaVinhos.split("\\t+");
            
            if(!partesVinhos[1].equals(vinhoExcluir)) 
            {
            	gravadorVinho.println(linhaVinhos);
            }
        }    
        
        gravadorVinho.close();        
        leitorVinhos.close();
        arquivoVinhosTemporarioAtualizado.close();
        
        arquivoVinhos.delete();
        arquivoVinhosTemporario.renameTo(arquivoVinhos);
        
        atualizarCardapio();        
        System.out.println("\n-----   O seu vinho foi removido com sucesso! Seu cardápio agora está atualizado.     -----\n");
        
        imprimirCardapio();
	}
}
