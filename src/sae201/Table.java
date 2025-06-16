package sae201;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Table {
	private int numero;
	private int nbPlacesMax;
	
	ObservableList<String> listePersonneSurTable = FXCollections.observableArrayList();
	ObservableList<String> listeGroupeSurTable = FXCollections.observableArrayList();
	
	ArrayList<Personne> listePersonneSurTableObjet = new ArrayList<>();
	ArrayList<Groupe> listeGroupeSurTableObjet = new ArrayList<>();
	
	// Constructeur
	public Table(int num,int nbPlace) {
		this.numero = num;
		this.nbPlacesMax = nbPlace;
		Principale.plan.listTables.add(this);
	}
	
	public int getNum() {
		return numero;
	}
	
	public String affiche() {
		return "Table n°" + numero + " " + "Nombre de place : " + nbPlacesMax;
	}
	
	// Attente --> table
	public void ajouterPersonneTable(Personne p, int indexTable) {
        listePersonneSurTable.add(p.affiche());
        listePersonneSurTableObjet.add(p);
		Principale.plan.listePersonneAttenteObjet.remove(p);
		Principale.plan.listePersonneEnAttente.remove(p.affiche());
        String intituleTable = "Pt";
        if (indexTable > 10) {
            intituleTable = "Gd";
        }
        p.setNumTable(intituleTable + numero);
        Principale.actualiserStatutTables();

    }
	
	public void enleverPersonneTable(Personne p) {
		listePersonneSurTable.remove(p.affiche());
		listePersonneSurTableObjet.remove(p);
		Principale.plan.listePersonneAttenteObjet.add(p);
		Principale.plan.listePersonneEnAttente.add(p.affiche());
		p.setNumTable("");
        Principale.actualiserStatutTables();

	}
	
	public void ajouterGroupeDansTable(Groupe g,int index) {
		Principale.plan.listeGroupeAttenteObjet.remove(g);
		Principale.plan.listeGroupeEnAttente.remove(g.affiche());
		listeGroupeSurTable.add(g.affiche());
		listeGroupeSurTableObjet.add(g);
		String intituleTable = "Pt";
        if (index > 10) {
            intituleTable = "Gd";
        }
        g.setNumTable(intituleTable + numero);
        for (Personne membre : g.membresGroupe) {
        	if (membre != null) {
    			membre.setNumTable(intituleTable + numero);
        	}
		}
        Principale.actualiserStatutTables();

	}
	
	public void enleverGroupeDeTablePourAttente(Groupe g) {
		Principale.plan.listeGroupeAttenteObjet.add(g);
		Principale.plan.listeGroupeEnAttente.add(g.affiche());
		listeGroupeSurTable.remove(g.affiche());
		listeGroupeSurTableObjet.remove(g);
		String intituleTable = "";
		g.setNumTable(intituleTable);
        for (Personne membre : g.membresGroupe) {
        	if (membre != null) {
    			membre.setNumTable(intituleTable);
        	}
		}
        Principale.actualiserStatutTables();

	}
	
	public int nbPersTable() {
		int indexTable = SessionContext.currentTableIndex;
		
		int nbPers = 0;
        nbPers += Principale.plan.listTables.get(indexTable-1).listePersonneSurTable.size();
        for (Groupe elt : Groupe.listDesGroupes) {
            if (Principale.plan.listTables.get(indexTable-1).listeGroupeSurTableObjet.contains(elt)) {
                nbPers += elt.membresGroupe.size();
            } else {
                System.out.println("Le nombre n'a pas pu se calculer");
            }
        }
        return nbPers;
	}
	
	public int getNbPlacseMax() {
		return nbPlacesMax;

	}
}