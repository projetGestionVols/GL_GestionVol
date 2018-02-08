package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import beans.Vol;
import traitement.TraitementAeroport;
import traitement.gestionVol;

public class TestAcceuil extends JFrame {

	private JPanel contentPane;
	private JComboBox comboDep;
	private JComboBox comboDes;
	private TraitementAeroport trar;
	private gestionVol vols;
	private List<Vol> volsList;
	private JDateChooser dateChooser;
	private int idDep=0;
	private int idDes=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestAcceuil frame = new TestAcceuil();
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
	public TestAcceuil() throws ClassNotFoundException, SQLException {
		
		volsList= new ArrayList<Vol>();
		trar = new TraitementAeroport();
		vols=new gestionVol();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		initComponent();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Hajar\\Documents\\GI_2\\S3\\IHM\\ProjetGL\\flight-sky-clouds-fly (2).jpg"));
		label.setBounds(0, 0, 715, 419);
		contentPane.add(label);
		
	}

	public void initComponent()
	{
		JButton btnRservation = new JButton("Gestion des r\u00E9servations");
		btnRservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ReservationGest gestRes=new ReservationGest();
					gestRes.setVisible(true);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}
		});
		btnRservation.setBackground(Color.WHITE);
		btnRservation.setBounds(86, 22, 184, 35);
		contentPane.add(btnRservation);
		
		String[] gestTab=new String[2];
		gestTab[0]="Gestion de vol";
		gestTab[1]="Gestion d'avions";
		JComboBox comboBox = new JComboBox(gestTab);
		comboBox.setBackground(Color.WHITE);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomComb=(String)comboBox.getSelectedItem();
				if(gestTab[0].equals(nomComb))
				{
					try {
						FenetreVol gestVol=new FenetreVol();
						gestVol.setVisible(true);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					try {
						Fenetre gestAvion=new Fenetre();
						gestAvion.setVisible(true);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dispose();
				}
				
			}
		});
		comboBox.setBounds(426, 22, 146, 35);
		contentPane.add(comboBox);
		
		comboDep = new JComboBox();
		comboDep.setBackground(Color.WHITE);
		comboDep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nomDep=(String)comboDep.getSelectedItem();
				idDep=trar.getAeroportByNameId(nomDep);
				System.out.println(""+idDep);
			}
		});
		comboDep.setBounds(109, 155, 174, 28);
		contentPane.add(comboDep);
		
		comboDes = new JComboBox();
		comboDes.setBackground(Color.WHITE);
		comboDes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nomDes=(String)comboDes.getSelectedItem();
				idDes=trar.getAeroportByNameId(nomDes);
				System.out.println(""+idDes);
			}
		});
		comboDes.setBounds(427, 155, 174, 28);
		contentPane.add(comboDes);
		remplirCombo();
		
		dateChooser = new JDateChooser();
		dateChooser.setBackground(Color.WHITE);
		dateChooser.setBounds(109, 277, 174, 28);
		contentPane.add(dateChooser);
		
		JButton btnNewButton = new JButton("Recherche");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String dateChoose;
				DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				dateChoose=df.format(dateChooser.getDate());
				System.out.println(dateChoose);

				
				volsList=vols.rechercheVol(dateChoose, idDep, idDes);
				//volsList=vols.rechercheVol("2018-02-15", 4545, 4541);
				System.out.println(volsList.size());
				
				VolRechercher rech;
				try {
					rech = new VolRechercher(dateChoose,idDep,idDes);
					rech.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
				dispose();
			}
		});
		btnNewButton.setBounds(444, 277, 120, 28);
		contentPane.add(btnNewButton);
	}
	public void remplirCombo()
	{
		for(int i=0;i<10;i++)
		{
			comboDep.addItem(trar.getAllAeroport().get(i).getNom());
			comboDes.addItem(trar.getAllAeroport().get(i).getNom());
		}
	}
	public List<Vol> getVolsList() {
		return volsList;
	}
}
