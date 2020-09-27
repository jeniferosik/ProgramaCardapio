package br.edu.up.programa;

import java.io.IOException;
import br.edu.up.util.Menu;

public class ProgramaPrincipal 
{

	public static void main(String[] args) throws IOException 
	{		
				
		Menu menuPrincipal = new Menu();
		
		System.out.println("Olá, seja bem-vindo ao nosso restaurante!");
		menuPrincipal.imprimirMenuPrincipal();
		
		
		
	}
	
}
