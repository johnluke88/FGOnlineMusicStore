package model;

import java.util.Collection;
import java.util.LinkedHashMap;

public class Carrello {

	public static class CartItem {
		
		private Prodotto prodotto;
		private int quantita;
	
		private CartItem(Prodotto prodotto, int quantita) {
			this.prodotto = prodotto;
			this.quantita = quantita;
		}
		public Prodotto getProdotto() {
			return prodotto;
		}
		public void setProdotto(Prodotto prodotto) {
			this.prodotto = prodotto;
		}
		public int getQuantita() {
			return quantita;
		}
		public void setQuantita(int quantita) {
			this.quantita = quantita;
		}	
		public long getPrezzoTotCent() {
			return prodotto.getPrezzoCent() * quantita;
		}
		public String getPrezzoTotEuro() {
			return String.format("%.2f",quantita * prodotto.getPrezzoCent() /100.);
		}
	}
	private LinkedHashMap<Integer, CartItem> lista = new LinkedHashMap<>();

	public Collection<CartItem> getLista() {
		return lista.values();
	}
	public void addItem(Prodotto prodotto, int quantita) {
		lista.put(prodotto.getId(), new CartItem(prodotto, quantita));
	}

	public CartItem removeItem(int prodId) {
		return lista.remove(prodId);
	}

	public long getPrezzoTotCentItem() {
		return lista.values().stream().mapToLong(p -> p.getPrezzoTotCent()).sum();
	}

	public String getPrezzoTotEuroItems() {
		return String.format("%.2f", getPrezzoTotCentItem() / 100.);
	}

	public CartItem get(int prodId) {
		return lista.get(prodId);
	}

	@Override
	public String toString() {
		return "Carrello [lista=" + lista + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrello other = (Carrello) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		return true;
	}
	
}
