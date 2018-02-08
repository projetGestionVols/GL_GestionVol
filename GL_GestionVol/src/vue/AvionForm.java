package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import beans.Avion;
import traitement.TraitementAvion;

public class AvionForm extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTextField        tfReference;
    private JTextField        tfNom;
    private JTextField        tfNombrePlace;
    private JLabel            lbReference;
    private JLabel            lbNom;
    private JLabel            lbNombrePlace;
    private JButton           btnValider;
    private JButton           btnFermer;
    private boolean           succeeded;
    private Avion             avion            = null;

    public AvionForm( Frame parent ) {
        super( parent, "Avion", true );
        JPanel panel = new JPanel( new FlowLayout() );

        this.setPreferredSize( new Dimension( 250, 550 ) );

        lbReference = new JLabel( "Reference: " );

        panel.add( lbReference );

        tfReference = new JTextField( 20 );

        panel.add( tfReference );

        lbNom = new JLabel( "Nom: " );

        panel.add( lbNom );

        tfNom = new JTextField( 20 );

        panel.add( tfNom );

        lbNombrePlace = new JLabel( "Nombre de places: " );
        panel.add( lbNombrePlace );

        tfNombrePlace = new JTextField( 20 );

        panel.add( tfNombrePlace );

        btnValider = new JButton( "Valider" );
        panel.add( lbReference );
        panel.add( tfReference );
        panel.add( lbNom );
        panel.add( tfNom );
        panel.add( lbNombrePlace );
        panel.add( tfNombrePlace );
        panel.setBorder( new LineBorder( Color.GRAY ) );
        btnValider = new JButton( "Valider" );

        btnValider.addActionListener( new ActionListener() {

            public void actionPerformed( ActionEvent e ) {
                Avion a = new Avion( getReference(), getNom(), Integer.parseInt( getNombrePlace() ) );
                TraitementAvion ta = null;
                try {
                    ta = new TraitementAvion();
                } catch ( ClassNotFoundException | SQLException e1 ) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if ( ta.ajouterAvion( a ) ) {
                    avion = ta.getAvionbyRef( getReference() );
                    JOptionPane.showMessageDialog( AvionForm.this,
                            "La création d'une avion est faite avec succès!",
                            "TraitementAvion",
                            JOptionPane.INFORMATION_MESSAGE );
                    succeeded = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog( AvionForm.this,
                            "Une erreur est survenue",
                            "TraitementAvion",
                            JOptionPane.ERROR_MESSAGE );
                    // reset username and password
                    tfReference.setText( "" );
                    tfNom.setText( "" );
                    tfNombrePlace.setText( "" );

                    succeeded = false;

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

    public String getReference() {
        return tfReference.getText().trim();
    }

    public String getNom() {
        return tfNom.getText().trim();
    }

    public String getNombrePlace() {
        return tfNombrePlace.getText().trim();

    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public Avion getAvion() {
        if ( avion == null )
            return null;
        return avion;
    }
}