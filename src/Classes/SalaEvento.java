package Classes;

import java.util.ArrayList;

public class SalaEvento {
	private String nome;
	private int lotacao;
	private ArrayList<Pessoa> participantesHorario1;
	private ArrayList<Pessoa> participantesHorario2;
	
	public SalaEvento() {
		this.nome = "";
		this.lotacao = 0;
		this.participantesHorario1 = new ArrayList<Pessoa>();
		this.participantesHorario2 = new ArrayList<Pessoa>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getLotacao() {
		return lotacao;
	}

	public void setLotacao(int lotacao) {
		this.lotacao = lotacao;
	}

	public ArrayList<Pessoa> getParticipantesHorario1() {
		return participantesHorario1;
	}

	public void setParticipantesHorario1(ArrayList<Pessoa> participantes) {
		this.participantesHorario1 = participantes;
	}
	
	public ArrayList<Pessoa> getParticipantesHorario2() {
		return participantesHorario2;
	}

	public void setParticipantesHorario2(ArrayList<Pessoa> participantesHorario2) {
		this.participantesHorario2 = participantesHorario2;
	}

	public void addParticipanteHorario1(Pessoa participante) {
		this.participantesHorario1.add(participante);
	}
	
	public void addParticipanteHorario2(Pessoa participante) {
		this.participantesHorario2.add(participante);
	}
	
}
