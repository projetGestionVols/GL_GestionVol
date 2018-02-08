package vue;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.xml.bind.ParseConversionEvent;

import beans.Reservation;
import traitement.TraitementReservation;
import javax.swing.JTextField;
import java.awt.Color;

public class ReservationGest extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private TraitementReservation res;
	private List<Reservation> listeRes;
	private Reservation R;
	private int idRes=0;
	private int idVol=0;
	private int nbPlace=0;
	private JTextField textField;
	private JScrollPane scrollPane;
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationGest frame = new ReservationGest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ReservationGest() throws ClassNotFoundException, SQLException {
		
		res=new TraitementReservation();
		listeRes=new ArrayList<Reservation>();
		R=new Reservation();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		initComponent();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListeDesRservations = new JLabel("Liste des R\u00E9servations");
		lblListeDesRservations.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 40));
		lblListeDesRservations.setBounds(75, 11, 540, 61);
		contentPane.add(lblListeDesRservations);
		
		textField = new JTextField();
		textField.setBounds(256, 83, 40, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Id de R\u00E9servation :");
		lblNewLabel.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(75, 89, 171, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnActualiser = new JButton("Recherche");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateTable();
			}
		});
		btnActualiser.setBackground(Color.WHITE);
		btnActualiser.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 14));
		btnActualiser.setBounds(306, 83, 113, 23);
		contentPane.add(btnActualiser);
		
		
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 40));
		label.setIcon(new ImageIcon("C:\\Users\\Hajar\\Documents\\GI_2\\S3\\IHM\\ProjetGL\\flight-sky-clouds-fly (2).jpg"));
		label.setBounds(0, 0, 690, 411);
		contentPane.add(label);
		
		
	}
	
	public void initComponent()
	{
		scrollPane = new JScrollPane();
		scrollPane.setBounds(75, 114, 496, 234);
		contentPane.add(scrollPane);
		
		listeRes = res.getAllReservation();
		
		 Object[][] data = new Object[listeRes.size()][3];
	        System.out.println( listeRes.toString() );

	        for ( int i = 0; i < listeRes.size(); i++ ) 
	        {
	            Reservation row = listeRes.get( i );
	            data[i][0] = listeRes.get( i ).getAttributRe();
	            data[i][1] = listeRes.get( i ).getVol();
	            data[i][2] = listeRes.get( i ).getNbPlace(); 
	            
	        }
	       
	        String title[] = { "Id de Réservation", "Id Vol", "Place" };
	       
		
		table = new JTable( data, title);
		scrollPane.setViewportView(table);
		
		ListSelectionModel model=table.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(! model.isSelectionEmpty())
				{

					int index=table.getSelectedRow();
					TableModel mod=table.getModel();
					idRes=(int) mod.getValueAt(index, 0);
					idVol=(int) mod.getValueAt(index, 1);
					nbPlace=(int) mod.getValueAt(index, 2);
					
 
				}
				
			}
		});
		
		JButton btnNewButton = new JButton("Modifier\r\n");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ModifierRes modRes = new  ModifierRes(idRes,nbPlace);
					modRes.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 20));
		btnNewButton.setBounds(424, 371, 142, 29);
		contentPane.add(btnNewButton);
	}
	public void updateTable()
	{
		Reservation R=new Reservation();
		int txt=Integer.parseInt(this.getTestField());		
				R=res.getReservationbyId(txt);
				Object[][] data = new Object[1][3];
				System.out.println( R.toString() );

		            Reservation row = R;
		            data[0][0] = R.getAttributRe();
		            data[0][1] = R.getVol();
		            data[0][2] = R.getNbPlace(); 

		        String title[] = { "Id de Réservation", "Id Vol", "Place" };
		       
			
			table = new JTable( data, title);
			scrollPane.setViewportView(table);
	}
	public String getTestField() {
        return textField.getText().trim();
    }
}
