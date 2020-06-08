package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoriaDAO {

	public synchronized void doSave(Categoria categoria) {
		
		
		try (Connection connection= ConPool.getConnection()){
			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO fgmusicstore.categoria (nome, descrizione)"
					+ " VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, categoria.getNome());
			ps.setString(2, categoria.getDescrizione());
					
			if(ps.executeUpdate()!=1)
				throw new RuntimeException("Errore doSave");
			
			ResultSet result = ps.getGeneratedKeys();
			result.next();	
			categoria.setId(result.getInt(1));		
			
		}
		catch(SQLException e) {		
			e.printStackTrace();
			throw new RuntimeException("Errore doSave");
		}
	}

	public synchronized void doSaveOrUpdate(Categoria categoria) {

        try(Connection connection = ConPool.getConnection())
        {
	          PreparedStatement ps = connection.prepareStatement("UPDATE fgmusicstore.categoria SET nome = ? ,descrizione = ?"
	                 + "  WHERE id= ? ;");
	          
	          ps.setString(1, categoria.getNome());
	          ps.setString(2, categoria.getDescrizione());
	          ps.setInt(3, categoria.getId());
	          
	          if(ps.executeUpdate() != 1)
	            throw new RuntimeException("Errore doSaveOrUpdate");
        }
	    catch(SQLException e) 
        {
	    	e.printStackTrace();
	    	throw new RuntimeException("Errore doSaveUPdate");
        }
	}
	
	public synchronized Categoria doRetrieveByName(String nome){
		 
		
		 try (Connection connection= ConPool.getConnection()){
			
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM fgmusicstore.categoria WHERE nome = ?");
			ps.setString(1, nome);
					
			ResultSet res = ps.executeQuery();
			
			if(res.next())
			{
				Categoria cat = new Categoria();
				
				cat.setId(res.getInt(1));
				cat.setNome(res.getString(2));
				cat.setDescrizione(res.getString(3));
				
				return cat;
			}
			return null;
		}
		 catch(SQLException e) {		
			 e.printStackTrace();
			 throw new RuntimeException("Errore doRetriveByName");
		 }
	}
	
	public synchronized Categoria doRetrieveByKey(int id){
		 
		
		 try (Connection connection= ConPool.getConnection()){
			
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM fgmusicstore.categoria WHERE id = ?");
			ps.setInt(1, id);
					
			ResultSet res = ps.executeQuery();
			
			if(res.next())
			{
				Categoria cat = new Categoria();
				
				cat.setId(res.getInt(1));
				cat.setNome(res.getString(2));
				cat.setDescrizione(res.getString(3));
				
				return cat;
			}
			return null;
		}
		 catch(SQLException e) {		
			 e.printStackTrace();
			 throw new RuntimeException("Errore doRetriveByKey");
		 }
	}
	
	public synchronized ArrayList<Categoria>  doRetrieveAll(){
		
		try (Connection connection= ConPool.getConnection()){
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM fgmusicstore.categoria");
					
			ResultSet res = ps.executeQuery();
			ArrayList<Categoria> lista = new ArrayList<>();
			while(res.next())
			{
				Categoria cat = new Categoria();
				
				cat.setId(res.getInt(1));
				cat.setNome(res.getString(2));
				cat.setDescrizione(res.getString(3));
				
				lista.add(cat);
			}
			return lista;
			
		}
		catch(SQLException e) {		
			e.printStackTrace();
			throw new RuntimeException("Errore doRetriveAll");
		}		
	}

	public synchronized void doDelete(int key) {
		
		try (Connection connection= ConPool.getConnection()){
			PreparedStatement ps = connection.prepareStatement("DELETE FROM fgmusicstore.categoria WHERE id = ? ");
			ps.setInt(1, key);
			
			if(ps.executeUpdate()!=1)
				throw new RuntimeException("Errore doDelete");
			
		}
		catch(SQLException e) {		
			e.printStackTrace();
			throw new RuntimeException("Errore doRetriveByKey");
		}
	}
	
}
	
