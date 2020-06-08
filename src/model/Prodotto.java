package model;

import java.util.ArrayList;

public class Prodotto {

	private int id;
	private String nome;
	private String descrizione;
	private long prezzoCent;
	private ArrayList<Categoria> categorie;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String name) {
		this.nome = name;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public long getPrezzoCent() {
		return prezzoCent;
	}
	public void setPrezzoCent(long prezzo) {
		this.prezzoCent = prezzo;
	}
	public String getPrezzoEuro() {
		//return String.valueOf(prezzoCent/100.);
		return String.format("%.2f",prezzoCent/100.);
	}
	public ArrayList<Categoria> getCategorie() {
		return categorie;
	}
	public void setCategorie(ArrayList<Categoria> categorie) {
		this.categorie = categorie;
	}
	@Override
	public String toString() {
		return "Prodotto [id=" + id + ", name=" + nome + ", descrizione=" + descrizione + ", prezzoCent=" + prezzoCent
				+ ", categorie=" + categorie + "]";
	}
	
	
}
