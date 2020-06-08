package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.UserBean;


public class UserBeanDAO {
	
	public synchronized void doSave(UserBean user) {
		
		
		try (Connection connection= ConPool.getConnection()){
			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO fgmusicstore.utente (username, passwordhash,nome,cognome,"
					+ " telefono, via, citta, cap, mail, admin)"
					+ " VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, user.getUsr());
			ps.setString(2, user.getPsw());
			ps.setString(3, user.getNome());
			ps.setString(4, user.getCognome());
			ps.setString(5, user.getTelefono());
			ps.setString(6, user.getVia());
			ps.setString(7, user.getCitta());
			ps.setString(8, user.getCap());
			ps.setString(9, user.getMail());
			ps.setBoolean(10, user.isAdmin());
					
			if(ps.executeUpdate()!=1)
				throw new RuntimeException("Errore doSave");
			
			ResultSet result = ps.getGeneratedKeys();
			result.next();	
			user.setId(result.getInt(1));		
			
		}
		catch(SQLException e) {		
			e.printStackTrace();
			throw new RuntimeException("Errore doSave");
		}
	}
	
	public synchronized void doSaveOrUpdate(UserBean user) 
	{ 
       try(Connection connection = ConPool.getConnection())
       {
	        PreparedStatement ps = connection.prepareStatement("UPDATE fgmusicstore.utente SET username = ?, passwordhash = ?,"
	               + " nome = ?, cognome = ?, telefono = ?, via = ?, citta = ?, cap = ?, mail = ?, admin = ?"
	               + " WHERE id = ?;");
	          
	        ps.setString(1, user.getUsr());
	        ps.setString(2, user.getPsw());
	        ps.setString(3, user.getNome());
	        ps.setString(4, user.getCognome());
	        ps.setString(5, user.getTelefono());
	        ps.setString(6, user.getVia());
	        ps.setString(7, user.getCitta());
	        ps.setString(8, user.getCap());
	        ps.setString(9, user.getMail());
	        ps.setBoolean(10, user.isAdmin());
	        ps.setInt(11, user.getId());
	          
	        if(ps.executeUpdate() != 1)
	          throw new RuntimeException("Errore doSaveOrUpdate");
      }
      catch(SQLException e) 
      {
    	  e.printStackTrace();
    	  throw new RuntimeException("Errore doSaveOrUpdate");
      }
	}
	
	public synchronized UserBean doRetrieveByUsername(String username){
		 
		
		 try (Connection connection= ConPool.getConnection()){
			
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM fgmusicstore.utente WHERE username = ? ");
			ps.setString(1, username);
					
			ResultSet res = ps.executeQuery();
			
			if(res.next())
			{
				UserBean ub = new UserBean();
				ub.setId(res.getInt(1));
				ub.setUsr(res.getString(2));
				ub.setPsw(res.getString(3));
				ub.setNome(res.getString(4));
				ub.setCognome(res.getString(5));
				ub.setTelefono(res.getString(6));
				ub.setVia(res.getString(7));
				ub.setCitta(res.getString(8));
				ub.setCap(res.getString(9));
				ub.setMail(res.getString(10));
				ub.setAdmin(res.getBoolean(11));
				
				return ub;
			}
			return null;
		}
	catch(SQLException e) {		
		e.printStackTrace();
		throw new RuntimeException("Errore doRetriveByUsername");
	}
}
	
	public synchronized UserBean doRetrieveByKey(int key) {
		
		try (Connection connection= ConPool.getConnection()){
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM fgmusicstore.utente WHERE id = ? ");
			ps.setInt(1, key);
			
			ResultSet res = ps.executeQuery();
			
			if(res.next())
			{
				UserBean ub = new UserBean();
				ub.setId(res.getInt(1));
				ub.setUsr(res.getString(2));
				ub.setPsw(res.getString(3));
				ub.setNome(res.getString(4));
				ub.setCognome(res.getString(5));
				ub.setTelefono(res.getString(6));
				ub.setVia(res.getString(7));
				ub.setCitta(res.getString(8));
				ub.setCap(res.getString(9));
				ub.setMail(res.getString(10));
				ub.setAdmin(res.getBoolean(11));
				
				return ub;
			}
			return null;
			
		}
		catch(SQLException e) {		
			e.printStackTrace();
			throw new RuntimeException("Errore doRetriveByKey");
		}
	}
	
	public synchronized ArrayList<UserBean>  doRetrieveAll(){
		
		try (Connection connection= ConPool.getConnection()){
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM fgmusicstore.utente");
					
			ResultSet res = ps.executeQuery();
			ArrayList<UserBean> lista = new ArrayList<>();
			while(res.next())
			{
				UserBean ub = new UserBean();
				ub.setId(res.getInt(1));
				ub.setUsr(res.getString(2));
				ub.setPsw(res.getString(3));
				ub.setNome(res.getString(4));
				ub.setCognome(res.getString(5));
				ub.setTelefono(res.getString(6));
				ub.setVia(res.getString(7));
				ub.setCitta(res.getString(8));
				ub.setCap(res.getString(9));
				ub.setMail(res.getString(10));
				ub.setAdmin(res.getBoolean(11));
				lista.add(ub);
			}
			return lista;
			
		}
		catch(SQLException e) {		
			e.printStackTrace();
			throw new RuntimeException("Errore doRetriveAll");
		}		
	}

	public synchronized UserBean doRetrieveByUsernameAndPassWord(String username, String password){
		 
		
		 try (Connection connection= ConPool.getConnection()){
			
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM fgmusicstore.utente WHERE username = ? AND passwordhash = SHA1(?)");
			ps.setString(1, username);
			ps.setString(2, password);
					
			ResultSet res = ps.executeQuery();
			
			if(res.next())
			{
				UserBean ub = new UserBean();
				ub.setId(res.getInt(1));
				ub.setUsr(res.getString(2));
				ub.setPsw(res.getString(3));
				ub.setNome(res.getString(4));
				ub.setCognome(res.getString(5));
				ub.setTelefono(res.getString(6));
				ub.setVia(res.getString(7));
				ub.setCitta(res.getString(8));
				ub.setCap(res.getString(9));
				ub.setMail(res.getString(10));
				ub.setAdmin(res.getBoolean(11));
				
				return ub;
			}
			return null;
		}
		 catch(SQLException e) {		
			 e.printStackTrace();
			 throw new RuntimeException("Errore doRetriveByUsernameAndPassword");
		 }
	}
	
	public synchronized UserBean doRetrieveByMail(String mail){
		 
		
		 try (Connection connection= ConPool.getConnection()){
			
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM fgmusicstore.utente WHERE mail = ? ");
			ps.setString(1, mail);
					
			ResultSet res = ps.executeQuery();
			
			if(res.next())
			{
				UserBean ub = new UserBean();
				ub.setId(res.getInt(1));
				ub.setUsr(res.getString(2));
				ub.setPsw(res.getString(3));
				ub.setNome(res.getString(4));
				ub.setCognome(res.getString(5));
				ub.setTelefono(res.getString(6));
				ub.setVia(res.getString(7));
				ub.setCitta(res.getString(8));
				ub.setCap(res.getString(9));
				ub.setMail(res.getString(10));
				ub.setAdmin(res.getBoolean(11));
				
				return ub;
			}
			return null;
		}
	catch(SQLException e) {		
		e.printStackTrace();
		throw new RuntimeException("Errore doRetriveByMail");
	}
}
	
	public synchronized void doDelete(int key) {
		
		try (Connection connection= ConPool.getConnection()){
			PreparedStatement ps = connection.prepareStatement("DELETE FROM fgmusicstore.utente WHERE id = ? ");
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
