package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import beans.Vol;
import traitement.TraitementAeroport;
import traitement.gestionVol;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ModifierRes extends JFrame {

	private JPanel contentPane;
	private JComboBox comboDep;
	private JComboBox comboDes;
	private JDateChooser dateChooser;
	private int idDep=0;
	private int idDes=0;
	private TraitementAeroport trar;
	private gestionVol vols;
	private List<Vol> volsList;
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
	public ModifierRes(int attributRe, int nbPlace) throws ClassNotFoundException, SQLException {
		
		
		
		volsList= new ArrayList<Vol>();
		trar = new TraitementAeroport();
		vols=new gestionVol();
		
		this.idRes=attributRe;
		this.nbPlace=nbPlace;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		initComponent();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblModifierLaRservation = new JLabel("Modifier la r\u00E9servation");
		lblModifierLaRservation.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 40));
		lblModifierLaRservation.setBounds(49, 0, 589, 53);
		contentPane.add(lblModifierLaRservation);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Hajar\\Documents\\GI_2\\S3\\IHM\\ProjetGL\\flight-sky-clouds-fly (2).jpg"));
		label.setBounds(0, 0, 688, 391);
		contentPane.add(label);
	}
	
	public void initComponent()
	{		
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
				
				VolRechercherModif rech;
				try {
					rech = new VolRechercherModif(dateChoose, idDep, idDes, idRes, nbPlace);
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
}
