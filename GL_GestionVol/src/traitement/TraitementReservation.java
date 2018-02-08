package traitement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import beans.Reservation;

public class TraitementReservation 
{
   private Connexion cnx ;
   private int place[] ;
   
	public TraitementReservation() throws ClassNotFoundException, SQLException
	{
		cnx = new Connexion(); 
	}
	
	/**
	 * la lise des places desponible  dans le vol
	 * @param vol
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void Place(int vol) throws ClassNotFoundException, SQLException
	{
		gestionVol VolT = new gestionVol();
		place = new int[VolT.getVolbyId(vol).getNbplace()];
		for(int i=1;i<place.length;i++)
		{
			place[i]=1;
			for(int j=0;j<getReservationVol(vol).size();j++)
			{
				if(i==getReservationVol(vol).get(j).getNbPlace())
				{
					place[i]=-1;
				}
			}
		}
		
	}
	
	public int nbPlace()
	{
		for(int i=1 ;i<place.length ;i++)
		{
			if(place[i]!=-1)
				return i;
		}
		return 0;
	}
	/**
	 * Méthode permet d'ajouter une Reservation  
	 * @param avion
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean ajouterReservation(Reservation  reservation) throws ClassNotFoundException, SQLException
	{
		Place(reservation.getVol());
		System.out.println(nbPlace());
		try{
		
			if(nbPlace()!=0)
			{
				int nb=nbPlace();
				
			PreparedStatement pr =  cnx.getCn().prepareStatement("INSERT INTO `reservation` (`attributRe`,`idVol`, `nbPlace`) values(null,?,?)");
			pr.setInt(1, reservation.getVol());
			pr.setInt(2, nb);
			reservation.setNbPlace(nb);
			pr.execute();
			return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			return false;
	    }
     }
	/**
	 * Methode permet de modificatier une reservation
	 * @param avion
	 */
	public boolean ModifierReservation(Reservation  reservation)
	{
		try{
			Place(reservation.getVol());
			if(nbPlace()!=0)
			{
				int nb=nbPlace();
				
			PreparedStatement pr =  cnx.getCn().prepareStatement("update reservation set idVol=?,nbPlace=? WHERE attributRe=?");
			reservation.setNbPlace(nb);
			pr.setInt(1, reservation.getVol());
			pr.setInt(2,nb);
			pr.setInt(3,reservation.getAttributRe());
			pr.executeUpdate();
		    pr.close();
		    return true;
			}
			else
			{
				return false;
			}
	
		}
		catch(Exception e)
		{
			return false;
	    }
	}
	public Reservation getReservationbyId(int id)
	{
		Reservation  a = new Reservation();
		try 
		{
			PreparedStatement pr =  cnx.getCn().prepareStatement("select * from reservation where attributRe=?");
			pr.setInt(1, id);
			ResultSet res = pr.executeQuery();
			while(res.next())
			{
				a.setVol(res.getInt("idVol"));
				a.setAttributRe(res.getInt("attributRe"));
				a.setNbPlace(res.getInt("nbPlace"));
			}
		}
		catch(Exception e)
		{
		}
		return a;
	}
	
	public List<Reservation> getReservationVol(int vol)
	{
		List<Reservation> liste =new  ArrayList<Reservation>();
		try 
		{
			PreparedStatement pr =  cnx.getCn().prepareStatement("select * from reservation where idVol=?");
			pr.setInt(1, vol);
			ResultSet res = pr.executeQuery();
			while(res.next())
			{
				Reservation  a = new Reservation();
				a.setVol(res.getInt("idVol"));
				a.setAttributRe(res.getInt("attributRe"));
				a.setNbPlace(res.getInt("nbPlace"));
				liste.add(a);
			}
		}
		catch(Exception e)
		{
		}
		return liste;
	}
	
	public int AfficherParVolPlace(Reservation  reservation) throws ClassNotFoundException, SQLException
	{
		try{
			PreparedStatement pr =  cnx.getCn().prepareStatement("select attributRe from reservation where idVol=? and  nbPlace=?");
			pr.setInt(1, reservation.getVol());
			pr.setInt(2, reservation.getNbPlace());
			ResultSet res = pr.executeQuery();
			Reservation  a = new Reservation();
			while(res.next())
			{
			
				a.setAttributRe(res.getInt("attributRe"));
				
			}
			return a.getAttributRe();
		}
		catch(Exception e)
		{
			return 0;
	    }
     }
	
	public List<Reservation> getAllReservation()
	{
		List<Reservation> liste =new  ArrayList<Reservation>();
		try 
		{
			PreparedStatement pr =  cnx.getCn().prepareStatement("select * from reservation");
			ResultSet res = pr.executeQuery();
			while(res.next())
			{
				Reservation  a = new Reservation();
				a.setVol(res.getInt("idVol"));
				a.setAttributRe(res.getInt("attributRe"));
				a.setNbPlace(res.getInt("nbPlace"));
				liste.add(a);
			}
		}
		catch(Exception e)
		{
		}
		return liste;
	}
}
