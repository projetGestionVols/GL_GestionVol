package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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

import beans.Avion;
import traitement.TraitementAvion;

public class Fenetre extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable            tableau;
    private JButton           change           = new JButton( "Changer la taille" );
    // private String[] comboData = { "Très bien", "Bien", "Mal" };
    private String            supp             = "Supprimer la ligne";
    private String            modif            = "Modifier une ligne";
    // private JComboBox combo;

    AvionForm                 avionForm;
    
    
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fenetre frame = new Fenetre();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    public Fenetre() throws ClassNotFoundException, SQLException {
        avionForm = new AvionForm( this );
        this.setLocationRelativeTo( null );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setTitle( "JTable" );
        this.setSize( 600, 250 );
        this.createContent();
    }

    private void createContent() throws ClassNotFoundException, SQLException {

        TraitementAvion traitementAvion = new TraitementAvion();
        List<Avion> list = traitementAvion.getAllAvion();
        Object[][] data = new Object[list.size()][5];

        System.out.println( list.toString() );

        for ( int i = 0; i < list.size(); i++ ) {
            Avion row = list.get( i );
            data[i][0] = row.getId();
            data[i][1] = row.getReference();
            data[i][2] = row.getNomAvion();
            data[i][3] = row.getNbplace();
            data[i][4] = supp;

        }

        String title[] = { "ID", "Reference", "Nom", "Nombre de places", "Suppression" };
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
            avionForm.setVisible( true );
            Avion avion = avionForm.getAvion();
            System.out.println( avion );
            Object[] donnee = new Object[] { avion.getId(), avion.getReference(),
                    avion.getNomAvion(), avion.getNbplace(), supp };
            ( (ZModel) tableau.getModel() ).addRow( donnee );
        }
    }

    // public static void main( String[] args ) throws ClassNotFoundException,
    // SQLException {
    // Fenetre fen = new Fenetre();
    // fen.setVisible( true );
    // }
}
