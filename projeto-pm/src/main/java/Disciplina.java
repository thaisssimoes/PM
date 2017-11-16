import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Classe para reconhecimento de informa��es referentes �s disciplinas no Historico Escolar. 
 * 
 * @author grupoPM
 * 
 * */

public class Disciplina {

	
	/**
	 * Atributos da classe
	 * 
	 * */	
	
//	private int initialSize = 16; //Tamanho Inicial da Lista
//	private double loadFactor = 0.75; //Valor do Load Factor	
//	private double sizeToRehash = initialSize * loadFactor;	
	private static Map<String,String> informacaoesDeDisciplinas = new HashMap<String,String>();
	private static Scanner leitorDeCodigo;
	private static Scanner leitorDeHistorico;
	
	/**
	 * 
	 * Este m�todo l� e armazena em hashmap uma lista .txt de disciplinas e c�digos que ser� usada posteriormente. 
	 * 
	 * @param caminhoLista(String) : endere�o da lista na m�quina
	 * 
	 * */
	
	public static void importarListaDisciplinas(String caminhoLista) throws IOException{
		String codigoDisciplina;
		String nomeDisciplina;
		String disciplinaRetiradaDaLista;
		
		File listaDisciplina = new File(caminhoLista);
		String[] arrayDeCodigoENome; 
		
		try {
		        Scanner leitorDisciplinas = new Scanner(listaDisciplina);
		        while (leitorDisciplinas.hasNextLine()) {		            
		        	disciplinaRetiradaDaLista = leitorDisciplinas.nextLine();		        	
		        	arrayDeCodigoENome = disciplinaRetiradaDaLista.split(":");		           		        	
		        	codigoDisciplina = arrayDeCodigoENome[0];
		            nomeDisciplina = arrayDeCodigoENome[1];	            		            
		            informacaoesDeDisciplinas.put(codigoDisciplina, new String(nomeDisciplina));		        	
		        }
		        leitorDisciplinas.close();	        	
		    } 
		    catch (IOException e) {
		        e.printStackTrace();
		  }
	}
	
	
	
	/**
	 * Este m�todo procura encontrar o status de aprova��o do aluno em uma disciplina e armazen�-lo no hashmap
	 * 
	 * @param historicoRefinado (String) : um bloco menor [apenas com as disciplinas] do historico escolar
	 * 
	 * */
		
	public static void encontrarStatusDeAprovacao(String historicoRefinado){
        
		leitorDeHistorico = new Scanner(historicoRefinado);
		String codigo;
		
		while (leitorDeHistorico.hasNextLine()) {            
			String linhaAtual = leitorDeHistorico.nextLine();
			leitorDeCodigo = new Scanner(linhaAtual);
			codigo = leitorDeCodigo.next();		
			for (String codigoChave : informacaoesDeDisciplinas.keySet()) {
				if(codigo.equals(codigoChave) && !codigo.equals("HTD0058")){
					String[] separadorDeStatus = linhaAtual.split("-");
					if(codigo.equals("TIN0110")){
			            informacaoesDeDisciplinas.put(codigoChave, new String(separadorDeStatus[2]));		        	
					}
					else{
						informacaoesDeDisciplinas.put(codigoChave, new String(separadorDeStatus[1]));
					}								
				}
				else if(codigo.equals(codigoChave) && codigo.equals("HTD0058")){
					linhaAtual = leitorDeHistorico.nextLine();
					linhaAtual = leitorDeHistorico.nextLine();
					String[] separadorDeStatus = linhaAtual.split("-");
					informacaoesDeDisciplinas.put(codigoChave, new String(separadorDeStatus[1]));
					
				}
			
			}
	
		}

	}

}


	

