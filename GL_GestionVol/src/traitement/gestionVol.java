package traitement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import beans.Vol;

public class gestionVol {
    private Connexion cnx;

    public gestionVol() throws ClassNotFoundException, SQLException {

        cnx = new Connexion();

    }

    /**
     * M�thode qui permet l'ajout d'un vol
     * 
     * @param vol
     * @retun boolean
     */
    public boolean ajouter( Vol v ) throws SQLException {
        try {
            java.util.Date allee = null;
            java.util.Date retour = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "dd-MM-yyyy" );
            allee = simpleDateFormat.parse( v.getDateDepart() );
            retour = simpleDateFormat.parse( v.getDateArrivee() );

            try {
                PreparedStatement pr = (PreparedStatement) cnx.getCn().prepareStatement(
                        "INSERT INTO `vol` ( `idVol`,`Aeroport_depart`, `Aeroport_arrive`,`dateDepart`,`dateArrivee`,`heureDepart`,`heureArrivee` ,`prix`,`nbplace`,`idAvion`) values(null,?,?,?,?,?,?,?,?,?)" );
                pr.setInt( 1, v.getArr() );
                pr.setInt( 2, v.getDepart() );
                pr.setDate( 3, new java.sql.Date( allee.getTime() ) );
                pr.setDate( 4, new java.sql.Date( retour.getTime() ) );
                pr.setString( 5, v.getHeureDepart() );
                pr.setString( 6, v.getHeureArrivee() );
                pr.setDouble( 7, v.getPrix() );
                pr.setInt( 8, v.getNbplace() );
                pr.setInt( 9, v.getAvion() );
                pr.execute();
                return true;

            } catch ( Exception e ) {
                System.out.println( e.getMessage() );
                return false;
            }

        } catch ( ParseException e ) {
            System.out.println( "type de date incorrect" );
            return false;
        }
    }

    /**
     * 
     * @return list des avion
     */
    public List<Vol> getAllVol() {
        List<Vol> list = new ArrayList<Vol>();
        try {
            PreparedStatement pr = (PreparedStatement) cnx.getCn().prepareStatement( "select * from vol" );
            ResultSet rs = pr.executeQuery();
            while ( rs.next() ) {
                Vol v = new Vol();
                v.setIdVol( rs.getInt( "idVol" ) );
                v.setDepart( rs.getInt( "Aeroport_depart" ) );
                v.setArr( rs.getInt( "Aeroport_arrive" ) );
                v.setDateDepart( rs.getString( "dateDepart" ) );
                v.setDateArrivee( rs.getString( "dateArrivee" ) );
                v.setHeureDepart( rs.getString( "heureDepart" ) );
                v.setHeureArrivee( rs.getString( "heureArrivee" ) );
                v.setPrix( rs.getDouble( "prix" ) );
                v.setNbplace( rs.getInt( "nbplace" ) );
                v.setAvion( rs.getInt( "idAvion" ) );
                list.add( v );

            }
        } catch ( Exception e ) {

        }
        return list;
    }

    /**
     * modifier un vol
     * 
     * @param vol
     * 
     */
    public boolean modifierVol( Vol v ) {
        try {

            PreparedStatement pr = (PreparedStatement) cnx.getCn().prepareStatement(
                    "update vol set Aeroport_depart=?,Aeroport_arrive=?,dateDepart=?,dateArrivee=?,heureDepart=?,heureArrivee=?,prix=?,nbplace=?,idAvion=? WHERE  idVol=?" );
            pr.setInt( 1, v.getDepart() );
            pr.setInt( 2, v.getArr() );
            pr.setString( 3, v.getDateDepart() );
            pr.setString( 4, v.getDateArrivee() );
            pr.setString( 5, v.getHeureDepart() );
            pr.setString( 6, v.getHeureArrivee() );
            pr.setDouble( 7, v.getPrix() );
            pr.setInt( 8, v.getNbplace() );
            pr.setInt( 9, v.getAvion() );
            pr.setInt( 10, v.getIdVol() );
            pr.executeUpdate();
            pr.close();
            return true;

        } catch ( Exception e ) {
            return false;
        }

    }

    /**
     * Supprimer un vol
     * 
     * @param v
     * @return
     */
    public boolean supprimerVol( Vol v ) {

        try {

            PreparedStatement pr = (PreparedStatement) cnx.getCn().prepareStatement( "delete from vol  WHERE idVol=?" );
            pr.setInt( 1, v.getIdVol() );
            pr.execute();
            pr.close();
            return true;

        } catch ( Exception e ) {
            return true;
        }
    }

    /**
     * rechercher un element
     * 
     * @param id
     * @return
     */
    public Vol getVolbyId( int id ) {
        Vol v = new Vol();

        try {
            PreparedStatement pr = (PreparedStatement) cnx.getCn()
                    .prepareStatement( "select * from vol where idVol=?" );
            pr.setInt( 1, id );
            ResultSet rs = pr.executeQuery();
            while ( rs.next() ) {
                v.setIdVol( rs.getInt( "idVol" ) );
                v.setDepart( rs.getInt( "Aeroport_depart" ) );
                v.setArr( rs.getInt( "Aeroport_arrive" ) );
                v.setDateDepart( rs.getString( "dateDepart" ) );
                v.setDateArrivee( rs.getString( "dateArrivee" ) );
                v.setHeureDepart( rs.getString( "heureDepart" ) );
                v.setHeureArrivee( rs.getString( "heureArrivee" ) );
                v.setPrix( rs.getDouble( "prix" ) );
                v.setNbplace( rs.getInt( "nbplace" ) );
                v.setAvion( rs.getInt( "idAvion" ) );
            }
        } catch ( Exception e ) {

        }
        return v;
    }

    public Vol getVolbyIdAvion( int idAvion ) {
        Vol v = new Vol();

        try {
            PreparedStatement pr = (PreparedStatement) cnx.getCn()
                    .prepareStatement( "select * from vol where idAvion=?" );
            pr.setInt( 1, idAvion );
            ResultSet rs = pr.executeQuery();
            while ( rs.next() ) {
                v.setIdVol( rs.getInt( "idVol" ) );
                v.setDepart( rs.getInt( "Aeroport_depart" ) );
                v.setArr( rs.getInt( "Aeroport_arrive" ) );
                v.setDateDepart( rs.getString( "dateDepart" ) );
                v.setDateArrivee( rs.getString( "dateArrivee" ) );
                v.setPrix( rs.getDouble( "prix" ) );
                v.setNbplace( rs.getInt( "nbplace" ) );
                v.setAvion( rs.getInt( "idAvion" ) );
            }
        } catch ( Exception e ) {

        }
        return v;
    }

    /**
     * recherche la liste des vol par date ,aeroport de départ ,aeroport
     * d'arrivée
     * 
     * @param d
     * @param dep
     * @param arr
     * @return
     */
    public List<Vol> rechercheVol( String d, int dep, int arr ) {
        List<Vol> list = null;
        try {
            java.util.Date aujourdhui = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
            aujourdhui = simpleDateFormat.parse( d );

            try {
                PreparedStatement pr = (PreparedStatement) cnx.getCn().prepareStatement(
                        "select * from vol where dateDepart=? and Aeroport_depart=? and Aeroport_arrive=?" );
                pr.setString( 1, d );
                pr.setInt( 2, dep );
                pr.setInt( 3, arr );
                ResultSet rs = pr.executeQuery();
       
                list = new ArrayList<Vol>();
                while ( rs.next() ) {
                    Vol v = new Vol();
                    v.setIdVol( rs.getInt( "idVol" ) );
                    v.setDepart( rs.getInt( "Aeroport_depart" ) );
                    v.setArr( rs.getInt( "Aeroport_arrive" ) );
                    v.setDateDepart( rs.getString( "dateDepart" ) );
                    v.setDateArrivee( rs.getString( "dateArrivee" ) );
                    v.setHeureDepart( rs.getString( "heureDepart" ) );
                    v.setHeureArrivee( rs.getString( "heureArrivee" ) );
                    v.setPrix( rs.getDouble( "prix" ) );
                    v.setNbplace( rs.getInt( "nbplace" ) );
                    list.add(v);
                }

            } catch ( Exception e ) {

            }
        } catch ( ParseException e ) {
            System.out.println( "type de date incorrect" );
        }

        return list;
    }

}
