package traitement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import beans.Utilisateur;

public class TraitementUser 
{

private Connexion cnx ;
	
	public TraitementUser() throws ClassNotFoundException, SQLException
	{
		cnx = new Connexion(); 
	}
	
	public boolean Login(String login ,String password)
	{
		Utilisateur user = new Utilisateur();
		try{
			
			PreparedStatement pr =  cnx.getCn().prepareStatement("select * from utilisateur where login=? and motPasse=?");
			pr.setString(1,login);
			pr.setString(2, password);

			ResultSet res = pr.executeQuery();
			while(res.next())
			{	
				user.setIdUser(res.getInt("idUser"));	
			}
	
		}
		catch(Exception e)
		{

	    }
		if(user.getIdUser()!=0)
			return true;
		return false;
	}
}
