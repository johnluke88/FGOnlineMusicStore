package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProdottoDAO {

	public synchronized void doSave(Prodotto prodotto) {
		
		try (Connection connection= ConPool.getConnection()){
			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO fgmusicstore.prodotto (nome, descrizione,prezzoCent)"
					+ " VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, prodotto.getNome());
			ps.setString(2, prodotto.getDescrizione());
			ps.setLong(3, prodotto.getPrezzoCent());
					
			if(ps.executeUpdate()!=1)
				throw new RuntimeException("Errore doSave1");
			
			ResultSet result = ps.getGeneratedKeys();
			result.next();	
			prodotto.setId(result.getInt(1));		
			
			
			PreparedStatement psc = connection.prepareStatement("INSERT INTO fgmusicstore.prodotto_categoria (idprodotto, idcategoria)"
					+ " VALUES (?, ?);");
			for(Categoria c : prodotto.getCategorie()) {
				
				psc.setInt(1, prodotto.getId());
				psc.setInt(2, c.getId());
				psc.addBatch();		
			}
			psc.executeBatch();	
			
		}
		catch(SQLException e) {		
			e.printStackTrace();
			throw new RuntimeException("Errore doSave");
		}
	}

	public synchronized void doSaveOrUpdate(Prodotto prodotto) {
	      
        try(Connection connection = ConPool.getConnection())
        {
        	PreparedStatement ps = connection.prepareStatement("UPDATE fgmusicstore.prodotto SET nome = ? , descrizione = ? ,prezzoCent = ?"
                 + "  WHERE id= ? ;");
          
        	ps.setString(1, prodotto.getNome());
        	ps.setString(2, prodotto.getDescrizione());
        	ps.setLong(3, prodotto.getPrezzoCent());
        	ps.setInt(4, prodotto.getId());
          
        	if(ps.executeUpdate() != 1)
        		throw new RuntimeException("Errore Modifica");
        	if(prodotto.getCategorie().isEmpty())
        	{
        
        		PreparedStatement psd = connection.prepareStatement("DELETE  from fgmusicstore.prodotto_categoria where idprodotto = ? ");
        		psd.setInt(1, prodotto.getId());
          
        		psd.executeUpdate();
        	}
        	else
        	{
        		PreparedStatement psd = connection.prepareStatement("DELETE  from fgmusicstore.prodotto_categoria where idprodotto = ? AND NOT idcategoria = ? ");
        		for(Categoria c : prodotto.getCategorie())
        		{
        			psd.setInt(1, prodotto.getId()); 
        			psd.setInt(2, c.getId());
        			psd.addBatch();		
        		}
        		psd.executeBatch();
        	}
        	PreparedStatement psi = connection.prepareStatement("INSERT IGNORE INTO fgmusicstore.prodotto_categoria (idprodotto, idcategoria)"
					+ " VALUES (?, ?);");
        	for(Categoria c : prodotto.getCategorie())
        	{
				
				psi.setInt(1, prodotto.getId());
				psi.setInt(2, c.getId());
				psi.addBatch();		
        	}
        	if(psi.executeBatch() == null)	
        		throw new RuntimeException("Errore INSERT");	          
        }
        catch(SQLException e) 
        {
        	e.printStackTrace();
	        throw new RuntimeException("Errore doSaveUPdate");
        }
	}

	public synchronized Prodotto doRetrieveByKey(int id) {
		
		try (Connection connection= ConPool.getConnection()){
		
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM fgmusicstore.prodotto WHERE id = ? ");
			ps.setInt(1, id);
					
			ResultSet res = ps.executeQuery();
			
			if(res.next())
			{
				Prodotto prod = new Prodotto();
				
				prod.setId(res.getInt(1));
				prod.setNome(res.getString(2));
				prod.setDescrizione(res.getString(3));
				prod.setPrezzoCent(res.getLong(4));
				prod.setCategorie(getCategorie(connection,prod.getId()));
					
				return prod;
			}
			return null;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Errore doRetriveByKey");
		}
	}
	
	
	public synchronized Prodotto doRetrieveByName(String name) {
		
		try (Connection connection= ConPool.getConnection()){
		
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM fgmusicstore.prodotto WHERE nome = ? ");
			ps.setString(1, name);
					
			ResultSet res = ps.executeQuery();
			
			if(res.next())
			{
				Prodotto prod = new Prodotto();
				
				prod.setId(res.getInt(1));
				prod.setNome(res.getString(2));
				prod.setDescrizione(res.getString(3));
				prod.setPrezzoCent(res.getLong(4));
				prod.setCategorie(getCategorie(connection,prod.getId()));
					
				return prod;
			}
			return null;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Errore doRetriveByName");
		}
	}
	
	private static ArrayList<Categoria> getCategorie(Connection con, int idProdotto) throws SQLException {
	    PreparedStatement ps = con.prepareStatement( "SELECT id, nome, descrizione FROM fgmusicstore.categoria LEFT JOIN fgmusicstore.prodotto_categoria ON id=idcategoria WHERE idprodotto=?");
	    ps.setInt(1, idProdotto);
	    ArrayList<Categoria> categorie = new ArrayList<>();
	    ResultSet rs = ps.executeQuery();
	    while (rs.next())
	    {
	    	Categoria c = new Categoria();
	    	c.setId(rs.getInt(1));
	    	c.setNome(rs.getString(2));
	    	c.setDescrizione(rs.getString(3));
	    	categorie.add(c);
	    }
	    return categorie;
	  }

	public synchronized void doDelete(int key) {
			
		try (Connection connection= ConPool.getConnection()){
			PreparedStatement ps = connection.prepareStatement("DELETE FROM fgmusicstore.prodotto WHERE id = ? ");
			ps.setInt(1, key);
				
		if(ps.executeUpdate()!=1)
			throw new RuntimeException("Errore doDelete");
	
		}
		catch(SQLException e) {		
			e.printStackTrace();
			throw new RuntimeException("Errore doDelete");
		}
	}	

	public synchronized ArrayList<Prodotto> doRetrieveByAll(int offset, int limit){
		
		try (Connection connection= ConPool.getConnection())
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM fgmusicstore.prodotto LIMIT ? , ?");
			int start = offset * limit - limit;
			
			
			ps.setInt(1, start);
			ps.setInt(2, limit);   
			ResultSet res = ps.executeQuery();
			
			ArrayList<Prodotto> lista = new ArrayList<>();

			while(res.next()) {
				Prodotto prod = new Prodotto();
				prod.setId(res.getInt(1));
				prod.setNome(res.getString(2));
				prod.setDescrizione(res.getString(3));
				prod.setPrezzoCent(res.getLong(4));
				prod.setCategorie(getCategorie(connection, prod.getId()));
				
				lista.add(prod);
			}
			return lista;
		
		}
		catch(SQLException e) {		
			e.printStackTrace();
			throw new RuntimeException("Errore doRetrieveByAll");
		}
	}
	
	public synchronized int getNumberOfRows()
    {
	      try (Connection connection = ConPool.getConnection())
	    {
	        
	      int numOfRows = 0;
	      PreparedStatement ps = connection.prepareStatement("SELECT COUNT(id) FROM fgmusicstore.prodotto");
	      
	      ResultSet res = ps.executeQuery();
	      res.next();
	      numOfRows = res.getInt(1);
	      
	      return numOfRows;
	    }
	    catch(SQLException e) 
	    {  
	      e.printStackTrace();
	      throw new RuntimeException("getNumberOfRows()");
	    }
    }
	
	public synchronized int getNumberOfRowsCategory(int id)
    {
	      try (Connection connection = ConPool.getConnection())
	    {
	        
	      int numOfRows = 0;
	      PreparedStatement ps = connection.prepareStatement("SELECT COUNT(prodotto_categoria.idprodotto) " + 
	      		"FROM fgmusicstore.prodotto JOIN fgmusicstore.prodotto_categoria " + 
	      		"ON prodotto.id=prodotto_categoria.idprodotto " + 
	      		"WHERE prodotto_categoria.idcategoria = ? ");
	      ps.setInt(1, id);
	      ResultSet res = ps.executeQuery();
	      res.next();
	      numOfRows = res.getInt(1);
	      
	      return numOfRows;
	    }
	    catch(SQLException e) 
	    {  
	      e.printStackTrace();
	      throw new RuntimeException("getNumberOfRowsCategory()");
	    }
    }

	public synchronized ArrayList<Prodotto> searchByName(String name, int offset, int limit){
		
		try (Connection connection= ConPool.getConnection())
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM fgmusicstore.prodotto WHERE MATCH(nome) AGAINST (? IN BOOLEAN MODE) LIMIT ? , ?");
			ps.setString(1, name);
			ps.setInt(2, offset);
			ps.setInt(3, limit);
			ResultSet res = ps.executeQuery();
			
			ArrayList<Prodotto> lista = new ArrayList<>();

			while(res.next()) {
				Prodotto prod = new Prodotto();
				prod.setId(res.getInt(1));
				prod.setNome(res.getString(2));
				prod.setDescrizione(res.getString(3));
				prod.setPrezzoCent(res.getLong(4));
				prod.setCategorie(getCategorie(connection, prod.getId()));
				
				lista.add(prod);
			}
			return lista;
			
		}
		catch(SQLException e) {		
			e.printStackTrace();
			throw new RuntimeException("Errore searchByName");
		}
	}
	
	public synchronized ArrayList<Prodotto> searchByNameOrDescription(String name, int offset, int limit){
		try (Connection connection= ConPool.getConnection())
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM fgmusicstore.prodotto WHERE MATCH(nome,descrizione) AGAINST (? ) LIMIT ? , ?");
			ps.setString(1, name);
			ps.setInt(2, offset);
			ps.setInt(3, limit);
			ResultSet res = ps.executeQuery();
			
			ArrayList<Prodotto> lista = new ArrayList<>();

			while(res.next()) {
				Prodotto prod = new Prodotto();
				prod.setId(res.getInt(1));
				prod.setNome(res.getString(2));
				prod.setDescrizione(res.getString(3));
				prod.setPrezzoCent(res.getLong(4));
				prod.setCategorie(getCategorie(connection, prod.getId()));
				
				lista.add(prod);
			}
			return lista;
			
		}
		catch(SQLException e) {		
			e.printStackTrace();
			throw new RuntimeException("Errore searchByNameOrDescription");
		}		
	}

	public synchronized ArrayList<Prodotto> doRetrieveByCategoria(int key, int offset, int limit){
		try (Connection connection= ConPool.getConnection())
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM fgmusicstore.prodotto LEFT JOIN fgmusicstore.prodotto_categoria ON id = idprodotto WHERE idcategoria = ?  LIMIT ? , ?");

			int start = offset * limit - limit;
			
			ps.setInt(1, key);
			ps.setInt(2, start);
			ps.setInt(3, limit); 
			
			
			ResultSet res = ps.executeQuery();
			
			ArrayList<Prodotto> lista = new ArrayList<>();

			while(res.next()) {
				Prodotto prod = new Prodotto();
				prod.setId(res.getInt(1));
				prod.setNome(res.getString(2));
				prod.setDescrizione(res.getString(3));
				prod.setPrezzoCent(res.getLong(4));
				prod.setCategorie(getCategorie(connection, prod.getId()));
				
				lista.add(prod);
			}
			return lista;
			
		}
		catch(SQLException e) {		
			e.printStackTrace();
			throw new RuntimeException("Errore doRetriveByCategoria");
		}		
	}

}