package Inicial;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Classes.*;

public class Principal {

	static ArrayList<EspacoCafe> espacosCafe; 
	static ArrayList<Pessoa> participantes; 
	static ArrayList<SalaEvento> salasEvento; 
	
	static File fileEspacoCafe;
	static File fileParticipantes;
	static File fileSalasEventos;
	
	static Scanner leia;
	static int ultimaSala;
	static int ultimoEspacoCafe;
	
	public static void main(String[] args) {
		
		ultimaSala = -1;
		ultimoEspacoCafe = -1;
		
		leia = new Scanner (System.in);
		
		espacosCafe = new ArrayList<EspacoCafe>();
		participantes = new ArrayList<Pessoa>();
		salasEvento = new ArrayList<SalaEvento>();

		carregaArquivos();
		menu();

	}
	
	static void carregaArquivos() {
		try {
			fileEspacoCafe = Arquivo.AbrirArquivo("espacoCafe");
			fileParticipantes = Arquivo.AbrirArquivo("participantes");
			fileSalasEventos = Arquivo.AbrirArquivo("salasEvento");
			
			ArrayList<String> arqEspacoCafe = Arquivo.lerArquivo(fileEspacoCafe);
			ArrayList<String> arqParticipantes = Arquivo.lerArquivo(fileParticipantes);
			ArrayList<String> arqSalasEventos = Arquivo.lerArquivo(fileSalasEventos);
			
			if(!arqEspacoCafe.isEmpty()) {
				for (String linha : arqEspacoCafe) {
					String[] valores = linha.split(";");
					
					EspacoCafe espacoCafe = new EspacoCafe();
					espacoCafe.setNome(valores[0]);
					espacoCafe.setLotacao( Integer.parseInt(valores[1]) );
					espacosCafe.add(espacoCafe);
					
				}
			}
			
			if(!arqSalasEventos.isEmpty()) {
				for (String linha : arqSalasEventos) {
					String[] valores = linha.split(";");
					
					SalaEvento salaEvento = new SalaEvento(); ;
					salaEvento.setNome(valores[0]);
					salaEvento.setLotacao(Integer.parseInt(valores[1]));					
					salasEvento.add(salaEvento);
					
				}
			}
			
			if(!arqParticipantes.isEmpty()) {
				for (String linha : arqParticipantes) {
					String[] valores = linha.split(";");
					
					Pessoa participante = new Pessoa(); 
					participante.setNome(valores[0]);
					participante.setSobrenome(valores[1]);
					participantes.add(participante);					
				}
			}
			
			if(!arqEspacoCafe.isEmpty() && !arqSalasEventos.isEmpty() && !arqParticipantes.isEmpty()) {
				calculaLocal();
			}
			
		} catch (Exception e) {
			System.out.println("Nao foi possivel carregar os arquivos");
		}
	}
	
	static void menu(){
		String opcao = "0";
		
		do {
			System.out.println("|-------------------------------|");
			System.out.println("|        >>Bem vindo<<          |");
			System.out.println("|-------------------------------|");
			System.out.println("|  [1] Cadastro de espaço café  |");
			System.out.println("|  [2] Cadastro sala de eventos |");
			System.out.println("|  [3] Cadastro de pessoa       |");
			System.out.println("|  [4] Consultar espaço café    |");
			System.out.println("|  [5] Consultar sala Eventos   |");
			System.out.println("|  [6] Consultar pessoas        |");
			System.out.println("|  [0] Para sair                |");
			System.out.println("|-------------------------------|");
			
			opcao = leia.nextLine();
			
			try {
				switch(opcao) {
					case "1":{
						cadastrarCafe();
						break;
					}
					case "2":{
						cadastrarEvento();
						break;
					}
					case "3":{
						cadastrarPessoa();
						break;
					}
					case "4":{
						consultarCafe();
						break;
					}
					case "5":{
						consultarEvento();
						break;
					}
					case "6":{
						consultarPessoas();
						break;
					}
					case "0":{
						System.out.println("Obrigado por usar este programa");
						System.exit(0);
					}
					default:{
						System.out.println("Você digitou um numero invalido");
					}
				}				
			}catch (Exception e) {
				carregaArquivos();
				menu();
			}

			
		}while(opcao != "0");
	}
	
	static void voltarMenu() throws Exception {
		System.out.println("Pressione enter para voltar ao menu");
		leia.nextLine();
		menu();
	}
	
	static ArrayList<Pessoa> subLista(ArrayList<Pessoa> lista, int inicio, int fim) {
		
		ArrayList<Pessoa> subList = new ArrayList<Pessoa>();
		
		for(int i = inicio; i < fim; i++) {
			subList.add(lista.get(i));
		}
		
		return subList;
		
	}
		
	static void cadastrarCafe() throws Exception {
		if (espacosCafe.size()>=2) {
			System.out.println("Os dois espacos café ja estao cadastrados");
			voltarMenu();
		}
		
		EspacoCafe espacoCafe = new EspacoCafe();
		System.out.println("Digite o nome do espaço cafe");
		espacoCafe.setNome(leia.nextLine());
		System.out.println("Digite a lotação do espaço café");
		espacoCafe.setLotacao(leia.nextInt());
		leia.nextLine();
		
		espacosCafe.add(espacoCafe);
		Arquivo.escreveNovaLinha(fileEspacoCafe, espacoCafe.getNome(), String.valueOf(espacoCafe.getLotacao()));
		
		System.out.println("Digite 0 para volta ao Menu");
		System.out.println("Digite 1 Para cadastrar um novo espaço café");	
		int opcao = leia.nextInt();
		leia.nextLine();
		if (opcao==0) {
			menu();
		}
		else {
			cadastrarCafe();
		}
	}
	
	static void cadastrarEvento() throws Exception {
		SalaEvento salaEvento = new SalaEvento(); 
		System.out.println("Digite o nome da sala de eventos");
		salaEvento.setNome(leia.nextLine());
		System.out.println("Digite a lotação");
		salaEvento.setLotacao(leia.nextInt());
		leia.nextLine();
		
		salasEvento.add(salaEvento);
		Arquivo.escreveNovaLinha(fileSalasEventos, salaEvento.getNome(), String.valueOf(salaEvento.getLotacao()));
		
		System.out.println("Digite 0 para volta ao Menu");
		System.out.println("Digite 1 Para cadastrar uma nova sala de evento");		
		int opcao = leia.nextInt();
		leia.nextLine();
		if (opcao==0) {
			menu();
		}
		else {
			cadastrarEvento();
		}
	}
	
	static void cadastrarPessoa() throws Exception {
		
		if (salasEvento.size()==0 || espacosCafe.size()==0) {
			System.out.println("Cadastre salas de eventos e espacos cafe antes de cadastrar pessoa");
			voltarMenu();
		}
		
		ultimaSala = ultimaSala +1;
		if (ultimaSala == salasEvento.size()) {
			ultimaSala = 0;
		}
		SalaEvento salaAtual = salasEvento.get(ultimaSala);
		if (salaAtual.getParticipantesHorario1().size()>= salaAtual.getLotacao()) {
			System.out.println("Não é possivel cadastrar mais participantes! Todas as vagas já foram preenchidas");
			voltarMenu();
		}
		
		ultimoEspacoCafe = ultimoEspacoCafe +1;
		if (ultimoEspacoCafe == espacosCafe.size()) {
			ultimoEspacoCafe = 0;
		}
		EspacoCafe espacoCafeAtual = espacosCafe.get(ultimoEspacoCafe);
		if (espacoCafeAtual.getParticipantesHorario1().size()>= espacoCafeAtual.getLotacao()) {
			System.out.println("Não é possivel cadastrar mais participantes! Todas as vagas foram preenchidas");
			voltarMenu();
		}
		
		Pessoa participante = new Pessoa(); 
		System.out.println("Digite o nome do participante");
		participante.setNome(leia.nextLine());
		System.out.println("Digite o sobrenome do participante");
		participante.setSobrenome(leia.nextLine());
		
		participantes.add(participante);
		Arquivo.escreveNovaLinha(fileParticipantes, participante.getNome(), participante.getSobrenome());
		
		calculaLocal();
		
		System.out.println("Digite 0 para volta ao Menu");
		System.out.println("Digite 1 Para cadastrar um novo participante");	
		int opcao = leia.nextInt();
		leia.nextLine();
		if (opcao==0) {
			menu();
		}
		else {
			cadastrarPessoa();
		}
	}
	
	static void calculaLocal() {
		
		for(int i = 0; i < salasEvento.size(); i++) {
			salasEvento.get(i).setParticipantesHorario1(new ArrayList<Pessoa>());
			salasEvento.get(i).setParticipantesHorario2(new ArrayList<Pessoa>());
		}
		
		for(int i = 0; i < espacosCafe.size(); i++) {
			espacosCafe.get(i).setParticipantesHorario1(new ArrayList<Pessoa>());
			espacosCafe.get(i).setParticipantesHorario2(new ArrayList<Pessoa>());			
		}
		
		ultimaSala = -1;
		ultimoEspacoCafe = -1;
		
		
		for(Pessoa participante: participantes) {
			
			ultimaSala = ultimaSala +1;
			if (ultimaSala == salasEvento.size()) {
				ultimaSala = 0;
			}
			
			ultimoEspacoCafe = ultimoEspacoCafe +1;
			if (ultimoEspacoCafe == espacosCafe.size()) {
				ultimoEspacoCafe = 0;
			}
			
			espacosCafe.get(ultimoEspacoCafe).addParticipanteHorario1(participante);
			salasEvento.get(ultimaSala).addParticipanteHorario1(participante);
		}

		

		
		for(int i = 0; i < salasEvento.size(); i++ ) {
			salasEvento.get(i).setParticipantesHorario2(new ArrayList<Pessoa>());
		}
		
		for(int i = 0; i < salasEvento.size(); i++ ) {
			
			ArrayList<Pessoa> alocados = new ArrayList<Pessoa>();
			ArrayList<Pessoa> realocados = new ArrayList<Pessoa>();
			
			if(salasEvento.get(i).getParticipantesHorario1().size() == 1) {
				realocados.add(salasEvento.get(i).getParticipantesHorario1().get(0));
			}else if(salasEvento.get(i).getParticipantesHorario1().size() > 1){
				alocados = subLista(salasEvento.get(i).getParticipantesHorario1(),0, (int)Math.floor(salasEvento.get(i).getParticipantesHorario1().size()/2));
				realocados = subLista(salasEvento.get(i).getParticipantesHorario1(),(int)Math.floor(salasEvento.get(i).getParticipantesHorario1().size()/2),salasEvento.get(i).getParticipantesHorario1().size());
			}			
			
			if( i + 1 < salasEvento.size()) {
				for(Pessoa pessoa: alocados) {
					salasEvento.get(i).addParticipanteHorario2(pessoa);
				}
				for(Pessoa pessoa: realocados) {
					salasEvento.get(i+1).addParticipanteHorario2(pessoa);
				}
			}else if(i + 1 == salasEvento.size()){
				for(Pessoa pessoa: alocados) {
					salasEvento.get(i).addParticipanteHorario2(pessoa);
				}
				for(Pessoa pessoa: realocados) {
					salasEvento.get(0).addParticipanteHorario2(pessoa);
				}
			}
			
		}
				
		for(int i = 0; i < espacosCafe.size(); i++ ) {
			espacosCafe.get(i).setParticipantesHorario2(new ArrayList<Pessoa>());
		}
		
		for(int i = 0; i < espacosCafe.size(); i++ ) {
			
			ArrayList<Pessoa> alocados = new ArrayList<Pessoa>();
			ArrayList<Pessoa> realocados = new ArrayList<Pessoa>();
			
			if(espacosCafe.get(i).getParticipantesHorario1().size() == 1) {
				realocados.add(espacosCafe.get(i).getParticipantesHorario1().get(0));
			}else if(espacosCafe.get(i).getParticipantesHorario1().size() > 1){
				alocados = subLista(espacosCafe.get(i).getParticipantesHorario1(),0, (int)Math.floor(espacosCafe.get(i).getParticipantesHorario1().size()/2));
				realocados = subLista(espacosCafe.get(i).getParticipantesHorario1(),(int)Math.floor(espacosCafe.get(i).getParticipantesHorario1().size()/2),espacosCafe.get(i).getParticipantesHorario1().size());
			}			
			
			if( i + 1 < espacosCafe.size()) {
				for(Pessoa pessoa: alocados) {
					espacosCafe.get(i).addParticipanteHorario2(pessoa);
				}
				for(Pessoa pessoa: realocados) {
					espacosCafe.get(i+1).addParticipanteHorario2(pessoa);
				}
			}else if(i + 1 == espacosCafe.size()){
				for(Pessoa pessoa: alocados) {
					espacosCafe.get(i).addParticipanteHorario2(pessoa);
				}
				for(Pessoa pessoa: realocados) {
					espacosCafe.get(0).addParticipanteHorario2(pessoa);
				}
			}
			
		}
		
		
	}
	
	static void consultarCafe() throws Exception {
		if(espacosCafe.size() == 0) {
			System.out.println("Nenhuma espaco cafe cadastrado ainda!");
			voltarMenu();
		}
		
		int contador = 0;
		System.out.println("Digite o o numero corespondente ao espaço café");
		for (EspacoCafe espacoCafe: espacosCafe) {
			contador = contador +1;
			System.out.printf("[%d] %s a Lotação é: %d / %d %n",contador,espacoCafe.getNome(),espacoCafe.getParticipantesHorario1().size(),espacoCafe.getLotacao());
		}
		System.out.println("Digite 0 para volta ao menu");
		int opcao = leia.nextInt();
		leia.nextLine();
		if (opcao == 0) {
			menu();
		}
		else if (opcao>espacosCafe.size()) {
			consultarCafe();
		}
		else {
			pessoasNoCafe(opcao-1);
		}
	}
	
	static void pessoasNoCafe(int posicao) throws Exception {
		
		if(participantes.size() == 0) {
			System.out.println("Nenhuma participante cadastrado ainda!");
			voltarMenu();
		}
		
		EspacoCafe cafeAtual = espacosCafe.get(posicao);
		ArrayList<Pessoa> participantesH1 = cafeAtual.getParticipantesHorario1();
		ArrayList<Pessoa> participantesH2 = cafeAtual.getParticipantesHorario2();
		
		System.out.printf("Participantes no espaço café: %s %n",cafeAtual.getNome());
		System.out.println("Primeiro Horario");
		for (Pessoa participante: participantesH1) {
			System.out.printf("Nome: %s %s %n",participante.getNome(),participante.getSobrenome());
		}
		
		System.out.printf("-----------------------------------");
		System.out.println("Segundo Horario");
		for (Pessoa participante: participantesH2) {
			System.out.printf("Nome: %s %s %n",participante.getNome(),participante.getSobrenome());
		}
		
		System.out.println("Digite 1 para consultar um novo espaco cafe");
		System.out.println("Digite 0 para voltar ao menu");
		int opcao = leia.nextInt();
		leia.nextLine();
		if (opcao==1) {
			consultarCafe();
		}
		menu();
	}
	
	static void consultarEvento() throws Exception {
		
		if(salasEvento.size() == 0) {
			System.out.println("Nenhuma sala de evento cadastrada ainda!");
			voltarMenu();
		}
		
		System.out.println("Digite o numero corespondente a sala de eventos");
		int contador = 0;
		for (SalaEvento salaEvento: salasEvento) {
			contador = contador +1;
			System.out.printf("[%d] %s a Lotação é: %d / %d %n",contador,salaEvento.getNome(),salaEvento.getParticipantesHorario1().size(),salaEvento.getLotacao());
		}
		System.out.println("Digite 0 para volta ao menu");
		int opcao = leia.nextInt();
		leia.nextLine();
		if (opcao == 0) {
			menu();
		}
		else if (opcao>salasEvento.size()) {
			consultarEvento();
		}
		else {
			pessoasNaSala(opcao-1);
		}
	}
	
	static void pessoasNaSala(int posicao) throws Exception {
		
		if(participantes.size() == 0) {
			System.out.println("Nenhuma participante cadastrado ainda!");
			voltarMenu();
		}
		
		SalaEvento salaAtual = salasEvento.get(posicao);
		ArrayList<Pessoa> participantesH1 = salaAtual.getParticipantesHorario1();
		ArrayList<Pessoa> participantesH2 = salaAtual.getParticipantesHorario2();
		
		System.out.printf("Participantes na sala %s %n",salaAtual.getNome());
		System.out.println("Primeiro Horario");
		for (Pessoa participante: participantesH1) {
			System.out.printf("Nome: %s %s %n",participante.getNome(),participante.getSobrenome());
		}
		
		System.out.printf("-----------------------------------");
		System.out.println("Segundo Horario");
		for (Pessoa participante: participantesH2) {
			System.out.printf("Nome: %s %s %n",participante.getNome(),participante.getSobrenome());
		}
		
		System.out.println("Digite 1 para consultar um nova sala");
		System.out.println("Digite 0 para voltar ao menu");
		int opcao = leia.nextInt();
		leia.nextLine();
		if (opcao==1) {
			consultarEvento();
		}
		menu();
	}
	
	static void consultarPessoas() throws Exception {
		
		if(participantes.size() == 0) {
			System.out.println("Nenhuma participante cadastrado ainda!");
			voltarMenu();
		}
		
		System.out.println("Digite o numero corespondente ao participante");
		int contador = 0;
		for (Pessoa participante: participantes) {
			contador = contador +1;
			System.out.printf("[%d] - %s %s  %n",contador,participante.getNome(),participante.getSobrenome());
		}
		
		System.out.println("Digite 0 para volta ao menu");
		int opcao = leia.nextInt();
		leia.nextLine();
		if (opcao == 0) {
			menu();
		}
		else if (opcao>participantes.size()) {
			consultarPessoas();
		}
		else {
			consultarPessoa(opcao-1);
		}
		
	}

	static void consultarPessoa(int posicao) throws Exception {
		Pessoa participante = participantes.get(posicao);
		
		System.out.printf("Participante: %s %s %n", participante.getNome(),participante.getSobrenome());
		
		for (SalaEvento salaEvento : salasEvento) {
			for(Pessoa pessoa: salaEvento.getParticipantesHorario1()) {
				if(pessoa.getNome() == participante.getNome() && pessoa.getSobrenome() == participante.getSobrenome()){
					System.out.printf("Sala de Evento no primeiro horario é: %s %n", salaEvento.getNome() );
				}
			}	
			
			for(Pessoa pessoa: salaEvento.getParticipantesHorario2()) {
				if(pessoa.getNome() == participante.getNome() && pessoa.getSobrenome() == participante.getSobrenome()){
					System.out.printf("Sala de Evento no segundo horario é: %s %n", salaEvento.getNome() );
				}
			}
		}
		
		for (EspacoCafe espacoCafe : espacosCafe) {
			for(Pessoa pessoa: espacoCafe.getParticipantesHorario1()) {
				if(pessoa.getNome() == participante.getNome() && pessoa.getSobrenome() == participante.getSobrenome()){
					System.out.printf("Espaco Cafe no primeiro horario é: %s %n", espacoCafe.getNome() );
				}
			}	
			
			for(Pessoa pessoa: espacoCafe.getParticipantesHorario2()) {
				if(pessoa.getNome() == participante.getNome() && pessoa.getSobrenome() == participante.getSobrenome()){
					System.out.printf("Espaco Cafe no segundo horario é: %s %n", espacoCafe.getNome() );
				}
			}	
		}

		System.out.println("Digite 1 para consultar um nova pessoa");
		System.out.println("Digite 0 para voltar ao menu");
		int opcao = leia.nextInt();
		leia.nextLine();
		if (opcao==1) {
			consultarPessoas();
		}
		menu();
	}
	
}
