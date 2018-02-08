package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import beans.Vol;
import traitement.gestionVol;

public class FenetreVol extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable            tableau;
    private JButton           change           = new JButton( "Changer la taille" );
    // private String[] comboData = { "Très bien", "Bien", "Mal" };
    private String            supp             = "Supprimer la ligne";
    // private String modif = "Modifier une ligne";
    // private JComboBox combo;

    VolForm                   volForm;

    public FenetreVol() throws ClassNotFoundException, SQLException {
        volForm = new VolForm( this );
        this.setLocationRelativeTo( null );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setTitle( "JTable" );
        this.setSize( 600, 250 );
        this.createContent();
    }

    private void createContent() throws ClassNotFoundException, SQLException {

        gestionVol gestionvol = new gestionVol();
        List<Vol> list = gestionvol.getAllVol();
        Object[][] data = new Object[list.size()][12];

        System.out.println( list.toString() );

        for ( int i = 0; i < list.size(); i++ ) {
            Vol row = list.get( i );
            data[i][0] = row.getIdVol();
            data[i][1] = row.getAvion();
            data[i][2] = row.getDepart();
            data[i][3] = row.getArr();
            data[i][4] = row.getDateDepart();
            data[i][5] = row.getDateArrivee();
            data[i][6] = row.getDateArrivee();
            data[i][7] = row.getHeureDepart();
            data[i][8] = row.getHeureArrivee();
            data[i][9] = row.getPrix();
            data[i][10] = row.getNbplace();
            data[i][11] = supp;

        }

        String title[] = { "ID", "Avion", "Date départ", "Date arrivée", "Heure départ", "Heure arrivéé", "prix",
                "nombre places", "Suppression" };
        ZModel zModel = new ZModel( data, title );

        this.tableau = new JTable( zModel );
        this.tableau.setRowHeight( 30 );
        DefaultTableCellRenderer dcr = new DefaultTableCellRenderer();
        this.tableau.getColumn( "Suppression" ).setCellEditor( new DeleteButtonEditor( new JCheckBox() ) );

        // On ajoute le tableau
        this.getContentPane().add( new JScrollPane( tableau ), BorderLayout.CENTER );

        JButton ajouter = new JButton( "Ajouter une ligne" );
        ajouter.addActionListener( new MoreListener() );
        this.getContentPane().add( ajouter, BorderLayout.SOUTH );
    }

    class MoreListener implements ActionListener {
        public void actionPerformed( ActionEvent event ) {
            volForm.setVisible( true );
            Vol vol = volForm.getVol();
            System.out.println( vol );
            Object[] donnee = new Object[] { vol.getIdVol(), vol.getAvion(),
                    vol.getDateDepart(), vol.getDateArrivee(), vol.getHeureDepart(), vol.getHeureArrivee(),
                    vol.getPrix(), vol.getNbplace(), supp };
            ( (ZModel) tableau.getModel() ).addRow( donnee );
        }
    }

}
