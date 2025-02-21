package controller; // Définition du package "controller"

import java.sql.SQLException; // Importation de la classe SQLException pour gérer les erreurs SQL
import model.model; // Importation du modèle (classe qui gère les données)
import view.View_Accueil; // Importation de la vue (interface graphique)

public class mainMVC { // Définition de la classe principale qui suit le modèle MVC

	private static model m; // Déclaration d'une variable statique de type "model"

	// Getter pour récupérer l'instance du modèle
	public static model getM() {
		return m;
	} 

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// Point d'entrée principal du programme
		System.out.println("AP2"); // Affichage d'un message pour indiquer que le programme démarre

		m = new model(); // Création d'une instance du modèle (gestion des données)
		m.getall(); // Appel d'une méthode du modèle (probablement pour récupérer des données)

		View_Accueil window = new View_Accueil(); // Création d'une instance de la vue (interface utilisateur)
	}
}
