package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import traitement.TraitementUser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class LoginFirst extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private boolean succeeded;
	TraitementUser user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFirst frame = new LoginFirst();
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
	public LoginFirst() throws ClassNotFoundException, SQLException  {
		
		
		 user= new TraitementUser();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		initeComponent();

		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Hajar\\Documents\\GI_2\\S3\\IHM\\ProjetGL\\flight-sky-clouds-fly (2).jpg"));
		label.setBounds(0, 0, 535, 328);
		contentPane.add(label);
	}
	
	
	public void initeComponent()
	{
		tfUsername = new JTextField();
		tfUsername.setBounds(206, 51, 149, 20);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel lblLogin = new JLabel("Login :");
		lblLogin.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 20));
		lblLogin.setBounds(28, 40, 105, 38);
		contentPane.add(lblLogin);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 20));
		lblMotDePasse.setBounds(28, 96, 168, 20);
		contentPane.add(lblMotDePasse);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(206, 98, 149, 20);
		contentPane.add(pfPassword);
		
		JButton btnLogin = new JButton("Connexion");
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 25));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ( user.Login( getUsername(), getPassword() ) ) {
                    JOptionPane.showMessageDialog( LoginFirst.this,
                            "Bonjour " + getUsername() + "! Bienvenue",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE );
                    succeeded = true;
                    try {
						TestAcceuil acceuil=new TestAcceuil();
						acceuil.setVisible(true);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    dispose();
                } else {
                    JOptionPane.showMessageDialog( LoginFirst.this,
                            "Nom d'utilisateur ou mot de passe invalide",
                            "Login",
                            JOptionPane.ERROR_MESSAGE );
                    // reset username and password
                    tfUsername.setText( "" );
                    pfPassword.setText( "" );
                    succeeded = false;

                }
			}
		});
		btnLogin.setBounds(308, 258, 174, 38);
		contentPane.add(btnLogin);
	}
	public String getUsername() {
        return tfUsername.getText().trim();
    }

    public String getPassword() {
        return new String( pfPassword.getPassword() );
    }

    public boolean isSucceeded() {
        return succeeded;
    }
}
