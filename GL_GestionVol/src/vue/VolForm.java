package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import beans.Aeroport;
import beans.Avion;
import beans.Vol;
import traitement.TraitementAeroport;
import traitement.TraitementAvion;
import traitement.gestionVol;

public class VolForm extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JTextField        tfheureDepart;
    private JTextField        tfheureArrivee;
    private JTextField        tfprix;
    private JTextField        tfnbplace;

    private JComboBox<String> cmdepart;
    private JComboBox<String> cmarrive;
    private JComboBox<String> cmavion;

    private JDateChooser      cldateDepart;         // jar JCalendar
    private JDateChooser      cldateArrivee;

    private JLabel            lbheureDepart;
    private JLabel            lbheureArrivee;
    private JLabel            lbdateDepart;
    private JLabel            lbdateArrivee;
    private JLabel            lbprix;
    private JLabel            lbnbplace;
    private JLabel            lbdepart;
    private JLabel            lbarrive;
    private JLabel            lbavion;
    private JButton           btnValider;
    private JButton           btnFermer;
    private boolean           succeeded;
    private Vol               vol;

    public VolForm( Frame parent ) {
        super( parent, "Vol", true );
        JPanel panel = new JPanel( new FlowLayout() );

        this.setPreferredSize( new Dimension( 270, 700 ) );

        lbdateDepart = new JLabel( "La date de départ: " );
        panel.add( lbdateDepart );

        cldateDepart = new JDateChooser();

        cldateDepart.setLocale( Locale.FRANCE );
        panel.add( cldateDepart );

        lbdateArrivee = new JLabel( "La date d'arivée: " );

        panel.add( lbdateArrivee );

        cldateArrivee = new JDateChooser();
        panel.add( cldateArrivee );

        lbheureDepart = new JLabel( "L'heure de départ: " );

        panel.add( lbheureDepart );

        tfheureDepart = new JTextField( 20 );

        panel.add( tfheureDepart );
        lbheureArrivee = new JLabel( "L'heure d'arrivée: " );

        panel.add( lbheureArrivee );

        tfheureArrivee = new JTextField( 20 );

        panel.add( tfheureArrivee );
        lbprix = new JLabel( "Prix: " );

        panel.add( lbprix );

        tfprix = new JTextField( 20 );

        panel.add( tfprix );
        lbnbplace = new JLabel( "Nombre de places: " );

        panel.add( lbnbplace );

        tfnbplace = new JTextField( 20 );

        panel.add( tfnbplace );
        lbavion = new JLabel( "Avion: " );

        panel.add( lbavion );

        cmavion = new JComboBox<String>();
        TraitementAvion traitementAvion = null;

        try {

            traitementAvion = new TraitementAvion();

        } catch ( ClassNotFoundException | SQLException e2 ) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        final List<Avion> listavions = traitementAvion.getAllAvion();

        for ( Avion avion : listavions ) {
            cmavion.addItem( avion.getNomAvion() );
        }

        lbdepart = new JLabel( "L'Aéropot de départ: " );

        panel.add( lbdepart );

        cmdepart = new JComboBox<String>();
        TraitementAeroport traitementAeroport = null;

        try {
            traitementAeroport = new TraitementAeroport();
        } catch ( ClassNotFoundException | SQLException e2 ) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        final List<Aeroport> listaeo = traitementAeroport.getAllAeroport();
        for ( Aeroport aeroport : listaeo ) {
            cmdepart.addItem( aeroport.getNom() );
        }

        panel.add( cmdepart );
        lbarrive = new JLabel( "L'Aéropot d'arrivé: " );
        panel.add( lbarrive );

        cmarrive = new JComboBox<String>();

        for ( Aeroport aeroport : listaeo ) {
            cmarrive.addItem( aeroport.getNom() );
        }

        panel.add( cmarrive );

        btnValider = new JButton( "Valider" );
        panel.add( lbdateDepart );
        panel.add( cldateDepart );
        panel.add( lbdateArrivee );
        panel.add( cldateArrivee );
        panel.add( lbheureDepart );
        panel.add( tfheureDepart );
        panel.add( lbheureArrivee );
        panel.add( tfheureArrivee );
        panel.add( lbprix );
        panel.add( tfprix );
        panel.add( lbnbplace );
        panel.add( tfnbplace );
        panel.add( lbavion );
        panel.add( cmavion );
        panel.add( lbdepart );
        panel.add( cmdepart );
        panel.add( lbarrive );
        panel.add( cmarrive );
        panel.setBorder( new LineBorder( Color.GRAY ) );
        btnValider = new JButton( "Validers" );

        btnValider.addActionListener( new ActionListener() {

            public void actionPerformed( ActionEvent e ) {

                int idAvion = 0;

                for ( Avion av : listavions ) {
                    if ( av.getNomAvion().equals( getavion() ) ) {
                        idAvion = av.getId();
                    }
                }

                int idAeop1 = 0;
                for ( Aeroport aeroport : listaeo ) {
                    if ( aeroport.getNom().equals( getdepart() ) ) {
                        idAeop1 = aeroport.getIdAeroport();
                    }
                }

                int idAeop2 = 0;
                for ( Aeroport aeroport : listaeo ) {
                    if ( aeroport.getNom().equals( getarrive() ) ) {
                        idAeop2 = aeroport.getIdAeroport();
                    }
                }

                vol = new Vol( getdateDepart(), getdateArrive(), getheureDepart(), getheureArrive(),
                        Double.parseDouble( getprix() ),
                        Integer.parseInt( getnbplace() ), idAvion,
                        idAeop1,
                        idAeop2 );

                gestionVol gv = null;
                try {
                    gv = new gestionVol();
                } catch ( ClassNotFoundException | SQLException e1 ) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    if ( gv.ajouter( vol ) ) {

                        JOptionPane.showMessageDialog( VolForm.this,
                                "La création d'une vol est faite avec succès!",
                                "gestionVol",
                                JOptionPane.INFORMATION_MESSAGE );
                        succeeded = true;
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog( VolForm.this,
                                "Une erreur est survenue",
                                "gestionVol",
                                JOptionPane.ERROR_MESSAGE );
                        // reset username and password
                        cldateDepart.setDate( new Date() );
                        cldateArrivee.setDate( new Date() );
                        tfheureDepart.setText( "" );
                        tfheureArrivee.setText( "" );
                        tfprix.setText( "" );
                        tfnbplace.setText( "" );

                        succeeded = false;

                    }
                } catch ( HeadlessException e1 ) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch ( SQLException e1 ) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        } );
        btnFermer = new JButton( "Fermer" );
        btnFermer.addActionListener( new ActionListener() {

            public void actionPerformed( ActionEvent e ) {
                dispose();
            }
        } );
        JPanel bp = new JPanel();
        bp.add( btnValider );
        bp.add( btnFermer );

        getContentPane().add( panel, BorderLayout.CENTER );
        getContentPane().add( bp, BorderLayout.PAGE_END );

        pack();
        setResizable( false );
        setLocationRelativeTo( parent );
    }

    public String getdateDepart() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "dd-MM-yyyy" );
        String date = simpleDateFormat.format( cldateDepart.getDate() );
        return date;
    }

    public String getdateArrive() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "dd-MM-yyyy" );
        String date = simpleDateFormat.format( cldateArrivee.getDate() );
        return date;
    }

    public String getheureDepart() {
        return tfheureDepart.getText().trim();
    }

    public String getheureArrive() {
        return tfheureArrivee.getText().trim();
    }

    public String getprix() {
        return tfprix.getText().trim();
    }

    public String getnbplace() {
        return tfnbplace.getText().trim();
    }

    public String getavion() {
        return cmavion.getSelectedItem().toString();
    }

    public String getdepart() {
        return cmdepart.getSelectedItem().toString();
    }

    public String getarrive() {
        return cmarrive.getSelectedItem().toString();
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public Vol getVol() {
        if ( vol == null || succeeded == false )
            return null;
        return vol;
    }

}