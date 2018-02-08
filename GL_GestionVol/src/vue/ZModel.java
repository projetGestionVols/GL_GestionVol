package vue;

import javax.swing.table.AbstractTableModel;

class ZModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private Object[][]        data;
    private String[]          title;

    // Constructeur
    public ZModel( Object[][] data, String[] title ) {
        this.data = data;
        this.title = title;
    }

    // Retourne le titre de la colonne � l'indice sp�cifi�
    public String getColumnName( int col ) {
        return this.title[col];
    }

    // Retourne le nombre de colonnes
    public int getColumnCount() {
        return this.title.length;
    }

    // Retourne le nombre de lignes
    public int getRowCount() {
        return this.data.length;
    }

    // Retourne la valeur � l'emplacement sp�cifi�
    public Object getValueAt( int row, int col ) {
        return this.data[row][col];
    }

    // D�finit la valeur � l'emplacement sp�cifi�
    public void setValueAt( Object value, int row, int col ) {
        // On interdit la modification sur certaines colonnes !
        if ( !this.getColumnName( col ).equals( "Age" )
                && !this.getColumnName( col ).equals( "Suppression" ) )
            this.data[row][col] = value;
    }

    // Retourne la classe de la donn�e de la colonne
    public Class getColumnClass( int col ) {
        // On retourne le type de la cellule � la colonne demand�e
        // On se moque de la ligne puisque les donn�es sont les m�mes
        // On choisit donc la premi�re ligne
        return this.data[0][col].getClass();
    }

    // M�thode permettant de retirer une ligne du tableau
    public void removeRow( int position ) {

        int indice = 0, indice2 = 0;
        int nbRow = this.getRowCount() - 1;
        int nbCol = this.getColumnCount();
        Object temp[][] = new Object[nbRow][nbCol];

        for ( Object[] value : this.data ) {
            if ( indice != position ) {
                temp[indice2++] = value;
            }
            System.out.println( "Indice = " + indice );
            indice++;
        }
        this.data = temp;
        temp = null;
        // Cette m�thode permet d'avertir le tableau que les donn�es
        // ont �t� modifi�es, ce qui permet une mise � jour compl�te du tableau
        this.fireTableDataChanged();
    }

    // Permet d'ajouter une ligne dans le tableau
    public void addRow( Object[] data ) {
        int indice = 0, nbRow = this.getRowCount(), nbCol = this.getColumnCount();

        Object temp[][] = this.data;
        this.data = new Object[nbRow + 1][nbCol];

        for ( Object[] value : temp )
            this.data[indice++] = value;

        this.data[indice] = data;
        temp = null;
        // Cette m�thode permet d'avertir le tableau que les donn�es
        // ont �t� modifi�es, ce qui permet une mise � jour compl�te du tableau
        this.fireTableDataChanged();
    }

    public boolean isCellEditable( int row, int col ) {
        return true;
    }
}