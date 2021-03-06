package model;

public class Categoria {
	
	private  int id;
	private String nome;
	private  String descrizione;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + "]";
	}
	
}
