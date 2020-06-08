package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdineDAO {
	
	public synchronized void doSave(ArrayList<Ordine> ordini) {
		
		try (Connection connection= ConPool.getConnection()){

			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO fgmusicstore.ordine (numOrd,idUtente, idProdotto,quantita,totaleP,data)"
					+ " VALUES (?,?, ?, ?,?,?);");

			for(Ordine o : ordini) {
				ps.setInt(1,o.getNumOrd());
				ps.setInt(2, o.getIdUtente());
				ps.setInt(3, o.getIdProdotto());
				ps.setInt(4, o.getQuantita());
				ps.setLong(5, o.getTotaleP());
				ps.setDate(6, o.getData());
				ps.addBatch();	
			}
			if(ps.executeBatch() == null)
				new RuntimeException("errore do save ordini");
			
//			ResultSet result = ps.getGeneratedKeys();
//			while(result.next())
//			{
//				result.getInt(1);
//			}
//					
//			prodotto.setId(result.getInt(1));
			
			
			
//			ps.setInt(1, ordine.getIdUtente());
//			ps.setInt(2, ordine.getIdProdotto());
//			ps.setInt(3, ordine.getQuantita());
//			ps.setLong(4, ordine.getTotaleP());
//			ps.setDate(5, new Date(ordine.getData().getTimeInMillis()));


//			if(ps.executeUpdate()!=1)
//				throw new RuntimeException("Errore doSave");
			
//			ResultSet result = ps.getGeneratedKeys();
//			result.next();	
//			ordine.setNumOrd(result.getInt(1));
			
			
		}
		catch(SQLException e) {		
			e.printStackTrace();
			throw new RuntimeException("Errore doSave");
		}
	}
	
	public synchronized ArrayList<Ordine> doRetrieveById(int key) {
		
		try (Connection connection= ConPool.getConnection()){
		
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM fgmusicstore.ordine WHERE idUtente = ? ");
			ps.setInt(1, key);
					
			ResultSet res = ps.executeQuery();
			ArrayList<Ordine> lista = new ArrayList<>();
			while(res.next())
			{
				Ordine ordine = new Ordine();
				
				ordine.setNumOrd(res.getInt(1));
				ordine.setIdUtente(res.getInt(2));
				ordine.setIdProdotto(res.getInt(3));
				ordine.setQuantita(res.getInt(4));
				ordine.setTotaleP(res.getLong(5));
				ordine.setData(res.getDate(6));
					
				lista.add(ordine);
			}
			return lista;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Errore doRetriveById");
		}
	}
	public synchronized int doRetrieveLastNumOrd() {
		
		try (Connection connection= ConPool.getConnection()){
			
			PreparedStatement pskey = connection.prepareStatement("SELECT * FROM fgmusicstore.ordine ORDER BY numOrd DESC LIMIT 1;");
			int key=1;
			ResultSet resultkey = pskey.executeQuery();
			if(resultkey.next())			
				key = resultkey.getInt(1)+1;
			
			return key;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Errore doRetrieveLastNumOrd");
		}
		
		
	}
	
}
