package traitement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Aeroport;



public class TraitementAeroport {
private Connexion cnx ;
	
	public TraitementAeroport() throws ClassNotFoundException, SQLException
	{
		cnx = new Connexion(); 
	}
	/**
	 * 
	 * @param id
	 * @return 
	 */
	public Aeroport getAeroportbyId(int id)
	{
		Aeroport a = new Aeroport();
		
		try 
		{
			PreparedStatement pr =  cnx.getCn().prepareStatement("select * from airports where id=?");
			pr.setInt(1, id);
			ResultSet res = pr.executeQuery();
			while(res.next())
			{
				a.setIdAeroport(res.getInt("id"));
				a.setNom(res.getString("name"));
				a.setVille(res.getString("cityName"));
			}
		}
		catch(Exception e)
		{
			
		}
		return a;
	}
	
	/**
	 * 
	 * @return liste des Aeroports
	 */
	public List<Aeroport> getAllAeroport()
	{
		List<Aeroport> listeAvion = new ArrayList<Aeroport>();
		try 
		{
			PreparedStatement pr =  cnx.getCn().prepareStatement("select * from airports");
			ResultSet res = pr.executeQuery();
			while(res.next())
			{
				Aeroport a = new Aeroport();
				a.setIdAeroport(res.getInt("id"));
				a.setNom(res.getString("name"));
				a.setVille(res.getString("cityName"));
				listeAvion.add(a);
			}
		}
		catch(Exception e)
		{
			
		}
		return listeAvion;
	}
	
	public List<Aeroport> getAeroportByName(String name)
	{
		List<Aeroport> listeAeroport = new ArrayList<Aeroport>();
		try 
		{
			PreparedStatement pr =  cnx.getCn().prepareStatement("select * from airports where name=?");
			pr.setString(1, name);
			ResultSet res = pr.executeQuery();
			while(res.next())
			{
				Aeroport a = new Aeroport();
				a.setIdAeroport(res.getInt("id"));
				a.setNom(res.getString("name"));
				a.setVille(res.getString("cityName"));
				listeAeroport.add(a);
			}
		}
		catch(Exception e)
		{
			
		}
		return listeAeroport;
	}
	

	public int getAeroportByNameId(String name)
	{
		int id =0;
		try 
		{
			PreparedStatement pr =  cnx.getCn().prepareStatement("select * from airports where name=?");
			pr.setString(1, name);
			ResultSet res = pr.executeQuery();
			while(res.next())
			{
				id=res.getInt("id");
				
			}
		}
		catch(Exception e)
		{
			
		}
		return id;
	}
	
}
