package view;

import java.io.IOException;
import java.util.Scanner;

import Controller.SteamController;

public class Principal {

	private static Scanner input;

	public static void main(String[] args) throws IOException {
		
		input = new Scanner(System.in);
		SteamController sc = new SteamController();
		
		System.out.println("Digite um symbol (use letras maiúsculas): ");
		String symbol = input.next();
		String arquivo = "dados.csv";
		String diretorio = "C:\\TEMP";
		
		try {
			sc.geraArquivo(symbol, arquivo, diretorio);
			sc.busca(symbol);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	
	}
	

}