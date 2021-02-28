package Classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Arquivo {
	
	public static File AbrirArquivo(String nomeArquivo) throws IOException {
		
		 File arquivo = new File( nomeArquivo + ".txt" );
		 
		 if(!arquivo.exists()) {
			 arquivo.createNewFile();
		 }
		 
		 return arquivo;
		
	}
	
	public static ArrayList<String> lerArquivo(File arquivo) throws IOException {
		
		FileReader fr = new FileReader(arquivo);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> conteudo = new ArrayList<String>();
		
		while(br.ready()) {
			conteudo.add(br.readLine());
		}
		
		br.close();
		fr.close();

		return conteudo;
		
	}
	
	public static void escreverArquivo(File arquivo, String texto) throws IOException {
		
		FileWriter fw = new FileWriter(arquivo, true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(texto);
		bw.newLine();
		bw.close();
		fw.close();

		
	}
	
	public static void escreveNovaLinha(File file, String valor1, String valor2) throws IOException {
		String linha = valor1 + ";" + valor2;
		escreverArquivo(file, linha );
	}
	
	

}