package sae201;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Groupe {
	
	public static List<Groupe> listDesGroupes = new ArrayList<>();
	
	public List<Personne> membresGroupe = new ArrayList<>();
	ObservableList<String> listeMembres = FXCollections.observableArrayList();

	private static int nombreGroupe=1;
	private String nom;
	private String numTableDuGroupe = "";
	private int numGroupe = nombreGroupe;
	
    private final IntegerProperty nbPersObservable = new SimpleIntegerProperty(0);
	
	// Constructeur
		public Groupe() {
			this.nom = "Groupe " + Integer.toString(numGroupe);
			nombreGroupe++;
		}
		
		// Getters et setters
		public static void setNombreGroupe(int nb) {
			nombreGroupe = nb;
		}
			
		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public int getNumGroupe() {
			return numGroupe;
		}
		
		public void setNumGroupe(int num) {
			this.numGroupe = num;
		}

		public List<Personne> getMembresGroupe() {
			return membresGroupe;
		}

		public void setMembresGroupe(List<Personne> membresGroupe) {
			this.membresGroupe = membresGroupe;
		}

		public ObservableList<String> getListeMembres() {
			return listeMembres;
		}

		public void setListeMembres(ObservableList<String> listeMembres) {
			this.listeMembres = listeMembres;
		}

		// Méthodes
		public String affiche() {
			return nom;
		}

		public static int getNombreGroupe() {
			return nombreGroupe;
		}

		public void ajouterPersonneDansGroupe(Personne p) {
            membresGroupe.add(p);
            listeMembres.add(p.affiche());
            nbPersObservable.set(membresGroupe.size());
            p.setNumTable(numTableDuGroupe);
        }

		public void retirerPersonneDeGroupe(Personne p) {
			membresGroupe.remove(p);
			listeMembres.remove(p.affiche());
			nbPersObservable.set(membresGroupe.size());
			p.setNumTable("");
		}
		

		
		
	    public IntegerProperty getNbPersDansGrpObservable() {
	        return nbPersObservable;
	    }

		public String getNumTable() {
			return numTableDuGroupe;
		}

		public void setNumTable(String numTable) {
			this.numTableDuGroupe = numTable;
		}
	}

