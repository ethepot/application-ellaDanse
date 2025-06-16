package sae201;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Plan {
	ObservableList<String> listePersonneEnAttente = FXCollections.observableArrayList();
	ObservableList<String> listeGroupeEnAttente = FXCollections.observableArrayList();
	
	ArrayList<Table> listTables = new ArrayList<>();;
	ArrayList<Personne> listePersonneAttenteObjet = new ArrayList<>();
	ArrayList<Groupe> listeGroupeAttenteObjet = new ArrayList<>();
	
	ArrayList<Personne> listeToutesLesPersonnes = new ArrayList<>();
	ArrayList<Groupe> listeTousLesGroupes = new ArrayList<>();
	
	public Plan(){
		
	}
	
	// Méthodes
	public void ajouterPersonneEnAttente(Personne p) {
		listePersonneEnAttente.add(p.affiche());
		listePersonneAttenteObjet.add(p);
	}
	
	public void ajouterGroupeEnAttente(Groupe g) {
		listeGroupeEnAttente.add(g.affiche());
		listeGroupeAttenteObjet.add(g);
	}	
	
	public void retirerPersonneEnAttente(Personne p) {
		listePersonneEnAttente.remove(p.affiche());
		listePersonneAttenteObjet.remove(p);
	}
	
	public void retirerGroupeEnAttente(Groupe g) {
		listeGroupeEnAttente.remove(g.affiche());
		listeGroupeAttenteObjet.remove(g);
	}
	
	public void ajouterPersonneDansListeToutesLesPersonnes(Personne p) {
		listeToutesLesPersonnes.add(p);
	}
	
	public void ajouterGroupeDansListeTousLesGroupes(Groupe g) {
		listeTousLesGroupes.add(g);
	}

}

