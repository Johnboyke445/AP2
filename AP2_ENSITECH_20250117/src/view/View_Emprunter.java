package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Window;
import java.sql.SQLException;

import javax.swing.JFrame;

import controller.mainMVC;
import model.ADHERENT;
import model.LIVRE;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class View_Emprunter {

	private JFrame frame;
	private JTextField textField_adherent;
	private JTextField textField_emp;
	private JTextField textField_retour;
	 

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public View_Emprunter() throws ClassNotFoundException, SQLException {
		mainMVC.getM().getall();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Numero_adherent = new JLabel("N° Adherent :");
		Numero_adherent.setBounds(48, 37, 83, 13);
		frame.getContentPane().add(Numero_adherent);
		
		textField_adherent = new JTextField();
		textField_adherent.setBounds(128, 34, 96, 19);
		frame.getContentPane().add(textField_adherent);
		textField_adherent.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 81, 382, 172);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField_emp = new JTextField();
		textField_emp.setBounds(161, 37, 96, 19);
		panel.add(textField_emp);
		textField_emp.setColumns(10);
		panel.setVisible(false);
		textField_emp.setVisible(false);
		
		JLabel lblNewLabel_message = new JLabel(" ");
		lblNewLabel_message.setBounds(31, 60, 347, 13);
		frame.getContentPane().add(lblNewLabel_message);
		
		
		JButton Ok_Emprunter = new JButton("OK");
		Ok_Emprunter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
               	String num;
				
               	num=textField_adherent.getText();
				System.out.println(num);
				num=textField_adherent.getText();
				ADHERENT ad;
			  ad=mainMVC.getM().findAdherent(num);
			  if (ad==null) {	  
				  System.out.println("pas trouvé");
				  panel.setVisible(false);
				  lblNewLabel_message.setText("Erreur Adherent inconnu");				   
			  }
			  else {
				  textField_adherent.setEnabled(false);
				  Ok_Emprunter.setEnabled(false);
				  System.out.println("Trouvé");
				  panel.setVisible(true);
				  lblNewLabel_message.setText("Veuillez entrer l'ISBN du livre emprunter ou retour ");

			  }
				}
		});
		
		JButton btnNewButton_MAJ = new JButton("Emprunt");
		btnNewButton_MAJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ISBN = textField_emp.getText();
				
				LIVRE l;
				l=mainMVC.getM().findlivre(ISBN);
				if(l==null)
				{
					lblNewLabel_message.setText("Erreur ISBN inconnu");
				}
				else
				{
					if(l.getEmprunteur()!=null)
					{
						lblNewLabel_message.setText("Erreur Livre non disponbile");
					}
					else
					{
						try {
							mainMVC.getM().emprunterlivre(textField_emp.getText(), ISBN);
							lblNewLabel_message.setText("Livre emprunté ! vous pouvez saisir un autre ISBN");
							mainMVC.getM().getall();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
		 }
				
		});
		btnNewButton_MAJ.setBounds(269, 31, 85, 31);
		panel.add(btnNewButton_MAJ);
		btnNewButton_MAJ.setVisible(false);
		
		
		JButton btnNewButton_MAJ_1 = new JButton("Retour");
		btnNewButton_MAJ_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ISBN; 
				ISBN = textField_retour.getText();

				LIVRE livre=mainMVC.getM().findlivre(ISBN);

				if (livre==null)
				{
					lblNewLabel_message.setText("ERREUR ISBN inconnu");
				}
				else
				{
					if (livre.getEmprunteur()==null)
					{
						lblNewLabel_message.setText("ERREUR livre pas en cours d'emprunt");
					}
					else
					{
						try {
							mainMVC.getM().retourLivre(ISBN);
							lblNewLabel_message.setText("Livre bien rendu");
						
							mainMVC.getM().getall();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		
		btnNewButton_MAJ_1.setBounds(269, 80, 85, 30);
		panel.add(btnNewButton_MAJ_1);
		btnNewButton_MAJ_1.setVisible(false);
		
		
		Ok_Emprunter.setBounds(234, 33, 85, 21);
		frame.getContentPane().add(Ok_Emprunter);
		 
		JButton btnNewButton_Emprunter = new JButton("Emprunter un livre");
		btnNewButton_Emprunter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
  	        String num;
				  textField_emp.setVisible(true);
				  btnNewButton_MAJ.setVisible(true);
				  System.out.println("Trouvé");
				   
			  }
				
		});
		
		btnNewButton_Emprunter.setBounds(10, 25, 141, 43);
		panel.add(btnNewButton_Emprunter);
		
		
		textField_retour = new JTextField();
		textField_retour.setColumns(10);
		textField_retour.setBounds(161, 81, 96, 19);
		panel.add(textField_retour);
		textField_retour.setVisible(false);
		
		JButton btnNewButton_RETOURNER = new JButton("Retourner un livre");
		btnNewButton_RETOURNER.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String num;
				  textField_retour.setVisible(true);
				  btnNewButton_MAJ_1.setVisible(true);
				  System.out.println("Trouvé");
			}
		});
		btnNewButton_RETOURNER.setBounds(10, 73, 141, 44);
		panel.add(btnNewButton_RETOURNER);
		
		JLabel Emp_2_com = new JLabel(" ");
		Emp_2_com.setBounds(161, 55, 45, 13);
		panel.add(Emp_2_com);
		
		JLabel Emp_1_com = new JLabel(" ");
		Emp_1_com.setBounds(161, 3, 45, 13);
		panel.add(Emp_1_com);
		
		JButton btnNewButton = new JButton("Accueil");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(341, 33, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		 
		
		 
		
		 
	}
}
