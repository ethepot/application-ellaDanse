package sae201;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class Principale extends Application{
	
	static public FenInfoTable fenInfoTable;
	static public FenListGroupe fenListGroupe;
	static public FenListPersonne fenListPersonne;
	static public FenetrePlan fenPlan;
	static public FenAjouterPersATable fenAjouterPersATable;
	static public FenCreerPersonne fenCreerPersonne;
	static public FenFichePersonne fenFichePersonne;
	static public FenListMembreGroupe fenListeMembresGroupe;
	static public FenAjouterPersDansGrp fenAjouterPersDansGrp;
	
	static public Plan plan = new Plan();	
	@Override
	public void start(Stage f) throws IOException {
		fenPlan = new FenetrePlan();
		fenInfoTable = new FenInfoTable();
		fenListGroupe = new FenListGroupe();
		fenListPersonne = new FenListPersonne();
		fenPlan = new FenetrePlan();
		fenAjouterPersATable = new FenAjouterPersATable();
		fenCreerPersonne = new FenCreerPersonne(); 
		fenFichePersonne = new FenFichePersonne();
		fenListeMembresGroupe = new FenListMembreGroupe();
		fenAjouterPersDansGrp = new FenAjouterPersDansGrp();
		
		fenPlan.show();
	}
	
	public static void main(String args[]) {
		Application.launch();
	}
	
	///////////////////////////////////////////////
	public static void ouvrirFenAjouterPersonneGroupe(Groupe g) {
		fenAjouterPersDansGrp.initFenetre(g);
		fenAjouterPersDansGrp.show();
	}
	
	public static void fermerFenAjouterPersonneGroupe() {
		fenAjouterPersDansGrp.close();
	}
	///////////////////////////////////////////////
	public static void ouvrirFenMembresDuGroupe(Groupe g) {
		fenListeMembresGroupe.initFenetre(g);
		fenListeMembresGroupe.show();
	}
	
	public static void fermerFenMembresDuGroupe() {
		fenListeMembresGroupe.close();
	}
	
	///////////////////////////////////////////////
	public static void ouvrirFichePersonne(Personne p) {
		System.out.println("Principale --->" + p.affiche());
		fenFichePersonne.initFenetre(p);
		fenFichePersonne.show();
	}
	
	public static void fermerFichePersonne() {
		fenFichePersonne.close();
	}
	///////////////////////////////////////////////
	public static void ouvrireCreerPersonne() {
		fenCreerPersonne.show();
	}
	
	public static void fermerCreerPersonne() {
		fenCreerPersonne.close();
	}
	///////////////////////////////////////////////
	public static void ouvrireAjouterPersATable() {
		fenAjouterPersATable.init();
		fenAjouterPersATable.show();
	}
	
	public static void fermerAjouterPersATable() {
		fenAjouterPersATable.close();
	}
	///////////////////////////////////////////////
	public static void ouvrireInfoTable(int idTable) {
		fenInfoTable.initialisation(idTable);
		fenInfoTable.show();
	}
	
	public static void fermerInfoTable() {
		fenInfoTable.close();
	}
	///////////////////////////////////////////////
	public static void ouvrireListGroupe() {
		fenListGroupe.show();
	}
	
	public static void fermerListeGroupe() {
		fenListGroupe.close();
	}
	///////////////////////////////////////////////
	public static void ouvrireListPersonne() {
		fenListPersonne.show();
	}
	
	public static void fermerListePersonne() {
		fenListPersonne.close();
	}	
	///////////////////////////////////////////////
	public static void ouvrireFenetrePlan() {
		fenPlan.show();
	}
	///////////////////////////////////////////////
	public static void afficherNbPersTable() {
		fenInfoTable.afficheNbPersTableCtrl();
	}
	
	public static void viderTablePrincipale() {
		fenInfoTable.viderTableCtrl();
	}
	
	static public void statusTableFen(int numTable, Boolean cocher) {
		fenPlan.statusTableCtrl(numTable,cocher);
	}
	
	static public void genererIdTable(int num) {
		fenPlan.surligneTablePresent(num);
	}
	
	static public void selectionPersonneDeplacer(String nom) {
		fenPlan.selectPersMoov(nom);
	}
	
	static public void selectionGroupeDeplacer(String nom) {
		fenPlan.selectGrpMoov(nom);
	}
	
	static public void deplacerPersPourFen() {
		fenPlan.deplacerPersPourCtrl();
	}
	
    static public void actualiserStatutTables() {
        fenPlan.actualiserStatutTablesCtrl();
    }
    
    static public void supprimerGroupe(Groupe g) {
    	System.out.println("blablabla"+g.getNumTable());
    	if (g.getNumTable().equals("")) {
        	System.out.println("J suis là");
    	    fenInfoTable.retirerGrpVide(g);
    	}
    	fenPlan.supprimerGroupe(g);
    }
}