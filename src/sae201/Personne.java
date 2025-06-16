package sae201;

public class Personne {
	
	private static int nombrePersonne;
	
	private int id;
	private String nom;
	private String prenom;
	private String mail;
	private String numTel;
	private String numTable;

	// Constructeur
	public Personne(String nom, String pre, String mail, String tel) {
		this.id=nombrePersonne;
		this.nom=nom;
		this.prenom=pre;
		this.mail=mail;
		this.numTel=tel;
		nombrePersonne++;
		Principale.plan.listeToutesLesPersonnes.add(this);
	}
	
	// Getters et Setters
	public static int getNombrePersonne() {
		return nombrePersonne;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getNumTable() {
		return numTable;
	}

	public void setNumTable(String numTable) {
		this.numTable = numTable;
	}
	
	// Méthodes
	@Override
	public String toString() {
	    return nom + " " + prenom;
	}

	public String affiche() {
		return nom + " " +prenom;
	}
}