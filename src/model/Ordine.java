package model;

import java.sql.Date;

public class Ordine {

	private int numOrd;
	private int idUtente;
	private int idProdotto;
	private int quantita;
	private long totaleP;
	private Date data;
	
	
	public int getNumOrd() {
		return numOrd;
	}
	public void setNumOrd(int numOrd) {
		this.numOrd = numOrd;
	}
	public int getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	public int getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public long getTotaleP() {
		return totaleP;
	}
	public void setTotaleP(long totaleP) {
		this.totaleP = totaleP;
	}
	public Date getData() { 
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}	
}
