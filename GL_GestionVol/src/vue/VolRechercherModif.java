package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import beans.Reservation;
import beans.Vol;
import traitement.TraitementAeroport;
import traitement.TraitementReservation;
import traitement.gestionVol;

public class VolRechercherModif extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TraitementAeroport trAe;
	private JPanel contentPane;
	private gestionVol vols;
	private JTable table;
	private String date;
	private int idDep;
	private int idDes;
	private List<Vol> volsList;
	private JButton btnAcheter;
	private int idVol=0;
	private TraitementReservation res;
	private int idRes;
	private int nbPlace;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
	
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public VolRechercherModif(String date,int idDep,int idDes,int attributRe, int nbPlace) throws ClassNotFoundException, SQLException {
		
		res = new TraitementReservation();
		trAe = new TraitementAeroport();
		vols=new gestionVol();
		volsList= new ArrayList<Vol>();
//		rechVol=new TestAcceuil();
		this.date=date;
		this.idDep=idDep;
		this.idDes=idDes;
		this.idRes=attributRe;
		this.nbPlace=nbPlace;

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		initComponents();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVolsDisponibles = new JLabel("Vols disponibles");
		lblVolsDisponibles.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 40));
		lblVolsDisponibles.setBounds(149, 37, 393, 46);
		contentPane.add(lblVolsDisponibles);
		

		
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Hajar\\Documents\\GI_2\\S3\\IHM\\ProjetGL\\flight-sky-clouds-fly (2).jpg"));
		label.setBounds(0, 0, 692, 404);
		contentPane.add(label);
		
		
	}

	
	public void initComponents()
	{
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 94, 611, 224);
		contentPane.add(scrollPane);
		
		volsList=vols.rechercheVol(date, idDep, idDes);
        System.out.println(volsList.size());

        Object[][] data = new Object[volsList.size()][9];
        System.out.println( volsList.toString() );

        for ( int i = 0; i < volsList.size(); i++ ) 
        {
            Vol row = volsList.get( i );
            data[i][0] = volsList.get( i ).getIdVol();
            data[i][1] = volsList.get( i ).getAvion();
            data[i][2] = trAe.getAeroportbyId(volsList.get( i ).getDepart()).getNom();
            data[i][3] = volsList.get( i ).getDateDepart();
            data[i][4] = volsList.get( i ).getHeureDepart();
            data[i][5] = trAe.getAeroportbyId(volsList.get( i ).getArr()).getNom();
            data[i][6] = volsList.get( i ).getDateArrivee();
            data[i][7] = volsList.get( i ).getHeureArrivee();
            data[i][8] = volsList.get( i ).getPrix();
     }
       

        String title[] = { "Id Vol", "Id Avion", "Départ", "Date", "Heure", "Destination", "Date", "Heure", "Prix" };
        ZModel zModel = new ZModel( data, title );
		
		table = new JTable(zModel);
		scrollPane.setViewportView(table);
		
		ListSelectionModel model=table.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(! model.isSelectionEmpty())
				{

					int index=table.getSelectedRow();
					TableModel mod=table.getModel();
					idVol=(int) mod.getValueAt(index, 0);

					 
				}
				
			}
		});
		
		btnAcheter = new JButton("Acheter");
		btnAcheter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Reservation R=new Reservation(idRes,nbPlace,idVol);
				res.ModifierReservation(R);
				int selectedRow=model.getMinSelectionIndex();
				try {
					try {
						JOptionPane.showMessageDialog(null, "Achat effectuer  "+res.AfficherParVolPlace(R));
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		btnAcheter.setBackground(Color.WHITE);
		btnAcheter.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 25));
		btnAcheter.setBounds(457, 351, 156, 30);
		contentPane.add(btnAcheter);
	}
}
