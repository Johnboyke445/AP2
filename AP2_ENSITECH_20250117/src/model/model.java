package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class model {
	private ArrayList<LIVRE> ListLivre;
	private ArrayList<AUTEUR> ListAuteur;
	private ArrayList<ADHERENT> ListAdherent;
	private Connection conn;

	public void getall() throws SQLException, ClassNotFoundException {
	    // On vide les listes
	    ListAdherent.clear();
	    ListAuteur.clear();
	    ListLivre.clear();
	    ListLivre = new ArrayList<LIVRE>();
	    ListAuteur = new ArrayList<AUTEUR>();
	    ListAdherent = new ArrayList<ADHERENT>();

	    Statement stmt = conn.createStatement();
	    ResultSet resultats;
	    String requete;

	    // Chargement des auteurs
	    requete = "SELECT * FROM auteur";
	    resultats = stmt.executeQuery(requete);
	    while (resultats.next()) {
	        AUTEUR a = new AUTEUR(resultats.getString(1), resultats.getString(2), resultats.getString(3), resultats.getString(4), resultats.getString(5));
	        ListAuteur.add(a);
	    }

	    // Chargement des livres
	    requete = "SELECT * FROM livre";
	    resultats = stmt.executeQuery(requete);
	    while (resultats.next()) {
	        LIVRE l = new LIVRE(resultats.getString(1), resultats.getString(2), resultats.getInt(3), null, null);
	        ListLivre.add(l);
	    }

	    // Chargement des adhérents
	    requete = "SELECT * FROM adherent";
	    resultats = stmt.executeQuery(requete);
	    while (resultats.next()) {
	        ADHERENT ad = new ADHERENT(resultats.getString(1), resultats.getString(2), resultats.getString(3), resultats.getString(4), new ArrayList<LIVRE>());
	        ListAdherent.add(ad);
	    }
	    

	    // Associer livres et adhérents
	    requete = "SELECT ISBN, adherent FROM livre, adherent WHERE livre.adherent = adherent.num";
	    resultats = stmt.executeQuery(requete);
	    String ISBN, numadherent;
	    while (resultats.next()) {
	        ISBN = resultats.getString(1);
	        numadherent = resultats.getString(2);
	        findlivre(ISBN).setEmprunteur(findAdherent(numadherent));
	        findAdherent(numadherent).ajouterLivre(findlivre(ISBN));
	    }

	    // Associer livres et auteurs
	    requete = "SELECT ISBN, auteur FROM livre, auteur WHERE livre.auteur = auteur.num";
	    resultats = stmt.executeQuery(requete);
	    String numauteur;
	    while (resultats.next()) {
	        ISBN = resultats.getString(1);
	        numauteur = resultats.getString(2);
	        findlivre(ISBN).setAuteur(findauteur(numauteur));
	    }
	}

	public AUTEUR findauteur(String num)
	{
		for(int i=0;i<ListAuteur.size();i++)
		{
			if(ListLivre.get(i).getISBN().equals(num))
			{
				return ListAuteur.get(i);
			}
		}

		return null;
	}

	public LIVRE findlivre(String ISBN)
	{
		for(int i=0;i<ListLivre.size();i++)
		{
			if(ListLivre.get(i).getISBN().equals(ISBN))
			{
				return ListLivre.get(i);
			}
		}
		return null;
	}
	public ADHERENT findAdherent(String num)
	{
		for(int i=0;i<ListAdherent.size();i++)
		{
			if(ListAdherent.get(i).getNum().equals(num))
			{
				return ListAdherent.get(i);
			}
		}

		return null;
	}
	public void updateAdherent(String num, String nom, String prenom, String email) throws SQLException {
		String $sql="UPDATE `adherent` SET `nom` = '"+nom+"', `prenom` = '"+prenom+"', `email` = '"+email+"' WHERE `adherent`.`num` = '"+num+"';";
		 Statement stmt = conn.createStatement();
		    int maj = stmt.executeUpdate($sql);
		    System.out.println("maj= "+ maj);
	}
	

	public model() throws ClassNotFoundException, SQLException {
	    // Initialisation des listes
	    ListLivre = new ArrayList<LIVRE>();
	    ListAuteur = new ArrayList<AUTEUR>();
	    ListAdherent = new ArrayList<ADHERENT>();
	    
	    // Configuration de la base de données
	    String BDD = "ap2"; // Nom de la base de données
	    String url = "jdbc:mysql://localhost:3306/" + BDD; // URL de connexion
	    String user = "root"; // Nom d'utilisateur
	    String passwd = ""; // Mot de passe (vide ici)
	    
	   // connection à sa BDD hostinger
	    
	     //       String BDD="u937355202_JohnDBDD";
	      //      String url="jdbc:mysql://193.203.168.138:3306/"+BDD+ "?characterEncoding=utf8";
	         //   String user="u937355202_JohnD";
	         //   String passwd="JohnD1830$";

	    // Chargement du driver JDBC
	    Class.forName("com.mysql.jdbc.Driver");
	    
	    // Connexion à la base de données
	    Connection conn = DriverManager.getConnection(url, user, passwd);
	    System.out.println("Connection OK");

	    // Stocker la connexion dans l'attribut
	    this.conn = conn;
	}


	public ArrayList<LIVRE> getListLivre() {
		return ListLivre;
	}

	public void setListLivre(ArrayList<LIVRE> listLivre) {
		ListLivre = listLivre;
	}

	public ArrayList<AUTEUR> getListAuteur() {
		return ListAuteur;
	}

	public void setListAuteur(ArrayList<AUTEUR> listAuteur) {
		ListAuteur = listAuteur;
	}

	public ArrayList<ADHERENT> getListAdherent() {
		return ListAdherent;
	}

	public void setListAdherent(ArrayList<ADHERENT> listAdherent) {
		ListAdherent = listAdherent;
	}

	public void emprunterlivre(String num, String ISBN) throws SQLException {
	    // Requête SQL pour attribuer un livre à un adhérent en mettant à jour la colonne "adherent"
	    String req;
	    req = "UPDATE `livre` SET `adherent` = '" + num + "' WHERE `livre`.`ISBN` = '" + ISBN + "'";
	    
	    // Création d'un objet Statement pour exécuter la requête
	    Statement stmt = conn.createStatement();
	    
	    // Exécution de la mise à jour et récupération du nombre de lignes affectées
	    int maj = stmt.executeUpdate(req);
	}

	public void retourLivre(String ISBN) throws SQLException {
	    // Requête SQL pour rendre un livre en supprimant l'adhérent associé (mettre NULL)
	    String req;
	    req = "UPDATE `livre` SET `adherent` = NULL WHERE `livre`.`ISBN` = '" + ISBN + "'";
	    
	    // Création d'un objet Statement
	    Statement stmt = conn.createStatement();
	    
	    // Exécution de la requête de mise à jour
	    int maj = stmt.executeUpdate(req);
	}
}
