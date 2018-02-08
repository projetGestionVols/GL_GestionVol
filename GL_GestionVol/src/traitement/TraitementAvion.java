package traitement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Avion;

public class TraitementAvion {
    private Connexion cnx;

    public TraitementAvion() throws ClassNotFoundException, SQLException {
        cnx = new Connexion();
    }

    /**
     * Méthode permet d'ajouter une avion
     * 
     * @param avion
     */
    public boolean ajouterAvion( Avion avion ) {
        try {
            PreparedStatement pr = cnx.getCn().prepareStatement(
                    "INSERT INTO `avion` (`idAv`, `reference`, `nomAvion`, `nbplace`) values(null,?,?,?)" );
            pr.setString( 1, avion.getReference() );
            pr.setString( 2, avion.getNomAvion() );
            pr.setInt( 3, avion.getNbplace() );
            pr.execute();
            return true;
        } catch ( Exception e ) {
            return false;
        }

    }

    /**
     * Methode permet de modificatier une avion
     * 
     * @param avion
     */
    public boolean ModifierAvion( Avion avion ) {
        try {

            PreparedStatement pr = cnx.getCn()
                    .prepareStatement( "update avion set reference=?,nomAvion=?,nbplace=? WHERE idAv=?" );
            pr.setString( 1, avion.getReference() );
            pr.setString( 2, avion.getNomAvion() );
            pr.setInt( 3, avion.getNbplace() );
            pr.setInt( 4, avion.getId() );
            pr.executeUpdate();
            pr.close();
            return true;

        } catch ( Exception e ) {
            return false;
        }
    }

    /**
     * methode permet de selectionner une avion par id
     * 
     * @param id
     * @return Avion
     */
    public Avion getAvionbyId( int id ) {
        Avion a = new Avion();

        try {
            PreparedStatement pr = cnx.getCn().prepareStatement( "select * from avion where idAv=?" );
            pr.setInt( 1, id );
            ResultSet res = pr.executeQuery();
            while ( res.next() ) {
                a.setId( res.getInt( "idAv" ) );
                a.setReference( res.getString( "reference" ) );
                a.setNomAvion( res.getString( "nomAvion" ) );
                a.setNbplace( res.getInt( "nbplace" ) );
            }
        } catch ( Exception e ) {

        }
        return a;
    }

    public Avion getAvionbyRef( String rf ) {
        Avion a = new Avion();

        try {
            PreparedStatement pr = cnx.getCn().prepareStatement( "select * from avion where reference=?" );
            pr.setString( 1, rf );
            ResultSet res = pr.executeQuery();
            while ( res.next() ) {
                a.setId( res.getInt( "idAv" ) );
                a.setReference( res.getString( "reference" ) );
                a.setNomAvion( res.getString( "nomAvion" ) );
                a.setNbplace( res.getInt( "nbplace" ) );
            }
        } catch ( Exception e ) {

        }
        return a;
    }

    /**
     * la suppression d'une avion par id
     * 
     * @param avion
     */
    public void suppAvion( Avion avion ) {
        try {

            PreparedStatement pr = cnx.getCn().prepareStatement( "delete from avion  WHERE idAv=?" );
            pr.setInt( 1, avion.getId() );
            pr.execute();
            pr.close();

        } catch ( Exception e ) {

        }
    }

    /**
     * la suppression d'une avion par id
     * 
     * @param int
     */
    public void suppAvion( int idavion ) {
        try {

            PreparedStatement pr = cnx.getCn().prepareStatement( "delete from avion  WHERE idAv=?" );
            pr.setInt( 1, idavion );
            pr.execute();
            pr.close();

        } catch ( Exception e ) {

        }
    }

    /**
     * 
     * @return liste des Avions
     */
    public List<Avion> getAllAvion() {
        List<Avion> listeAvion = new ArrayList<Avion>();
        try {
            PreparedStatement pr = cnx.getCn().prepareStatement( "select * from avion" );
            ResultSet res = pr.executeQuery();
            while ( res.next() ) {
                Avion a = new Avion();
                a.setId( res.getInt( "idAv" ) );
                a.setReference( res.getString( "reference" ) );
                a.setNomAvion( res.getString( "nomAvion" ) );
                a.setNbplace( res.getInt( "nbplace" ) );
                listeAvion.add( a );
            }
        } catch ( Exception e ) {

        }
        return listeAvion;
    }
}
