package Classes;

public class Pessoa {
	
	private String nome;
	private String sobrenome;
	
	public Pessoa() {
		this.nome = "";
		this.sobrenome = "";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
}
