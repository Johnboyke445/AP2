package model;


public class LIVRE {

	private String ISBN;
	private String titre;
	private int prix;
	private AUTEUR Auteur;
	private ADHERENT Emprunteur;
	public LIVRE(String iSBN, String titre, int prix,AUTEUR auteur, ADHERENT emprunteur) {
		super();
		ISBN = iSBN;
		this.titre = titre;
		this.prix = prix;
		Auteur = auteur;
		Emprunteur = emprunteur;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public AUTEUR getAuteur() {
		return Auteur;
	}
	public void setAuteur(AUTEUR auteur) {
		Auteur = auteur;
	}
	
	public ADHERENT getEmprunteur() {
		return Emprunteur;
	}
	public void setEmprunteur(ADHERENT emprunteur) {
		Emprunteur = emprunteur;
	}
	public String Ligneinfo() {
		String ligne;
		ligne=ISBN+": "+ titre;
		if(Auteur==null) {
			ligne=ligne+"_(Auteur inconnu)";	
		}
		else {
			ligne=ligne+"_Auteur:"+ Auteur.getNom();	
		}
		if(Emprunteur==null) {
			ligne+="_dispo";
		}
		else {
			ligne+="_emprunté";
		}
		return ligne;
	}


public String infoEMP() {
	String ligne1;
	ligne1=ISBN ;
 
	if(Emprunteur==null) {
		ligne1+=" : dispo";
	}
	else {
		ligne1+=" : emprunté";
	}
	return ligne1;
}
}
