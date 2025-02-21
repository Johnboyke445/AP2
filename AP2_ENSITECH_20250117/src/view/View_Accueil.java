package view;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;

import controller.mainMVC;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
 

public class View_Accueil {

	private JFrame frame;
	private JButton btnNewButton_catalogue;
	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
public View_Accueil() throws ClassNotFoundException, SQLException {
		mainMVC.getM().getall();
		initialize();
		frame.setVisible(true);
		for (int i=0 ; i!=mainMVC.getM().getListLivre().size();i++)
		{ 
			System.out.println(mainMVC.getM().getListLivre().get(i).getTitre());
		}

		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel(" NB livre :");
		lblNewLabel_1.setBounds(104, 42, 101, 13);  
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_nblivre = new JLabel("New label");
		lblNewLabel_nblivre.setBounds(160, 42, 45, 13);
		frame.getContentPane().add(lblNewLabel_nblivre);
		
		lblNewLabel_nblivre.setText(String.valueOf(mainMVC.getM().getListLivre().size()));
		
		JButton btnNewButton = new JButton("CATALOGUE");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					View_Catalogue vc = new View_Catalogue();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        btnNewButton.setBounds(36, 78, 127, 76);
        frame.getContentPane().add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("PROFIL");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					View_Profil vp =new View_Profil();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		
        	}
        });
        btnNewButton_1.setBounds(185, 78, 127, 76);
        frame.getContentPane().add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("EMPRUNT/RETOUR");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					View_Emprunter vp =new View_Emprunter();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnNewButton_2.setBounds(34, 166, 208, 76);
        frame.getContentPane().add(btnNewButton_2);
		
		 
	
		
	     
		
	 
	}
}
