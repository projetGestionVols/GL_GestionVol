package traitement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion
{
	
	Connection cn;
	public Connexion() throws SQLException, ClassNotFoundException
	{
		try {
		Class.forName("com.mysql.jdbc.Driver");
  		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_lg","root","");
	   System.out.println("connexion effectuée");
	
	}catch(SQLException e)
	{
		e.printStackTrace();
		
	}
	}
	
	public Connection getCn() {
		return cn;
	}
	public void setCn(Connection cn) {
		this.cn = cn;
	}


}
