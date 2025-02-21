package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controller.mainMVC;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class View_Catalogue {

	private JFrame frame;

	 
 
	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public View_Catalogue() throws ClassNotFoundException, SQLException {
		mainMVC.getM().getall();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 475, 325);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		List list_Livre = new List();
		list_Livre.setBounds(38, 49, 388, 208);
		frame.getContentPane().add(list_Livre);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\johnd\\Downloads\\lofi-bedroom-night-475x325.jpg"));
		lblNewLabel.setBounds(10, 10, 461, 278);
		frame.getContentPane().add(lblNewLabel);
		
		for(int i=0; i!=mainMVC.getM().getListLivre().size();i++)
		{
		list_Livre.add(mainMVC.getM().getListLivre().get(i).Ligneinfo());
		}
		
		JButton btnNewButton = new JButton("Accueil");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(224, 22, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
 
	}
}
