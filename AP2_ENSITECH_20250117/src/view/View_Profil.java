package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.mainMVC;
import model.ADHERENT;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.List;
import java.awt.Color;

public class View_Profil {

	private JFrame frame;
	private JTextField textField_numA;
	private JTextField textField_Nom;
	private JTextField textField_Prenom;
	private JTextField textField_Email;

	 
	 
	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public View_Profil() throws ClassNotFoundException, SQLException {
		mainMVC.getM().getall();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 520, 328);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" Veuillez entrez votre numero");
		lblNewLabel.setBounds(33, 40, 135, 13);
		frame.getContentPane().add(lblNewLabel);
		
		textField_numA = new JTextField();
		textField_numA.setBounds(178, 37, 96, 19);
		frame.getContentPane().add(textField_numA);
		textField_numA.setColumns(10);
		 
		
		JPanel panel = new JPanel();
		panel.setBounds(33, 82, 358, 148);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nom :");
		lblNewLabel_1.setBounds(10, 21, 45, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Prenom : ");
		lblNewLabel_2.setBounds(10, 44, 64, 13);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(" Email :");
		lblNewLabel_3.setBounds(10, 67, 45, 13);
		panel.add(lblNewLabel_3);
		
		List list_livre = new List();
		list_livre.setBounds(167, 10, 181, 90);
		panel.add(list_livre);
		
		textField_Nom = new JTextField();
		textField_Nom.setBounds(65, 18, 96, 19);
		panel.add(textField_Nom);
		textField_Nom.setColumns(10);
		
		textField_Prenom = new JTextField();
		textField_Prenom.setBounds(65, 41, 96, 19);
		panel.add(textField_Prenom);
		textField_Prenom.setColumns(10);
		
		textField_Email = new JTextField();
		textField_Email.setBounds(65, 67, 96, 19);
		panel.add(textField_Email);
		textField_Email.setColumns(10);
		
		JButton btnNewButton_Update = new JButton(" Mettre a jour");
		 
		btnNewButton_Update.setBounds(125, 117, 121, 21);
		panel.add(btnNewButton_Update);
		
		JLabel label_info = new JLabel(" ");
		label_info.setForeground(new Color(255, 0, 0));
		label_info.setBounds(123, 59, 234, 21);
		frame.getContentPane().add(label_info);
		panel.setVisible(false);
		

		JButton btnNewButton_ok = new JButton(" OK");
		btnNewButton_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num;
				
				num=textField_numA.getText();
				System.out.println(num);
				num=textField_numA.getText();
				ADHERENT ad;
			  ad=mainMVC.getM().findAdherent(num);
			  if (ad==null) {
				  label_info.setForeground(Color.red);
				  System.out.println("pas trouvé");
				  label_info.setText("numéro inconnu");
				  panel.setVisible(false);
			  }
			  else {
				  textField_numA.setEnabled(false);
				  btnNewButton_ok.setEnabled(false);
				  label_info.setForeground(Color.green);
				  label_info.setText("Vos info:");
				  System.out.println("Trouvé" + ad.getNom());
				   panel.setVisible(true);
				   textField_Nom.setText(ad.getNom());
				   textField_Prenom.setText(ad.getPrenom());
				   textField_Email.setText(ad.getEmail());
				   for(int i=0; i!=ad.getListLivre().size();i++)
					{
					list_livre.add(ad.getListLivre().get(i).Ligneinfo());
					}
					
			  }
				}
		});
		btnNewButton_Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mainMVC.getM().updateAdherent(textField_numA.getText(), textField_Nom.getText(),textField_Prenom.getText(), textField_Email.getText());
					label_info.setText("information mis a jour");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_ok.setBounds(298, 36, 85, 21);
		
		frame.getContentPane().add(btnNewButton_ok);
		
		JButton btnNewButton = new JButton("Accueil");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(396, 36, 85, 21);
		frame.getContentPane().add(btnNewButton);
	}
}
