package vue;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import traitement.TraitementAvion;

//CTRL + SHIFT + O pour g�n�rer les imports
public class DeleteButtonEditor extends DefaultCellEditor {

    protected JButton            button;
    private DeleteButtonListener bListener = new DeleteButtonListener();

    public DeleteButtonEditor( JCheckBox checkBox ) {
        // Par d�faut, ce type d'objet travaille avec un JCheckBox
        super( checkBox );
        // On cr�e � nouveau notre bouton
        button = new JButton();
        button.setOpaque( true );
        // On lui attribue un listener
        button.addActionListener( bListener );
    }

    public Component getTableCellEditorComponent( JTable table, Object value, boolean isSelected, int row,
            int column ) {
        // On d�finit le num�ro de ligne � notre listener
        bListener.setRow( row );
        // On passe aussi en param�tre le tableau pour des actions potentielles
        bListener.setTable( table );
        // On r�affecte le libell� au bouton
        button.setText( ( value == null ) ? "" : value.toString() );
        // On renvoie le bouton
        return button;
    }

    class DeleteButtonListener implements ActionListener {

        private int    row;
        private JTable table;

        public void setRow( int row ) {
            this.row = row;
        }

        public void setTable( JTable table ) {
            this.table = table;
        }

        public void actionPerformed( ActionEvent event ) {
            if ( table.getRowCount() > 0 ) {

                int order = table.getSelectedRow();
                int id = (int) table.getModel().getValueAt( order, 0 );
                TraitementAvion traitementAvion;
                try {
                    traitementAvion = new TraitementAvion();
                    traitementAvion.suppAvion( id );

                } catch ( ClassNotFoundException | SQLException e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                ( (ZModel) table.getModel() ).removeRow( this.row );

            }
        }
    }
}