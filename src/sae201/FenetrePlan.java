package sae201;

import java.io.File;
import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FenetrePlan extends Stage{

	private CtrlPlan ctrl;
	private FXMLLoader loader; // déclaration de champ (déjà faite ? sinon ajoute-la en haut)

	public FenetrePlan() throws IOException {
		this.setTitle("Plan");
		Scene sc = new Scene(creerSceneGraph());
		setResizable(false);
		this.setScene(sc);
	}

	private Pane creerSceneGraph() throws IOException {
		// Dans l'instruction suivante, indiquer le chemin complet du fichier disponibilites.fxml 
	    File f = new File("bin/plan.fxml");
	    loader = new FXMLLoader(f.toURI().toURL()); // <-- utilise le champ
	    Pane racine = loader.load();
	    ctrl = loader.getController();
	    return racine;
	}
	
	void statusTableCtrl(int indiceTable, Boolean cocher) {
		String id = "";
		if(indiceTable+1 > 10) {
			id = "gd" + String.valueOf(indiceTable-10+1);
		}else {
			id = "pt" + String.valueOf(indiceTable+1);
		}
		
	    Parent racine = loader.getRoot(); // ou Pane si tu es sûr
	    Node composant = racine.lookup("#" + id);
	    System.out.println(indiceTable);
	    System.out.println(composant.getId());
	    
	    int nbPers = Principale.plan.listTables.get(indiceTable).listePersonneSurTable.size();

		 // Ajoute les membres des groupes déjà présents sur la table
		 for (Groupe g : Principale.plan.listTables.get(indiceTable).listeGroupeSurTableObjet) {
		     nbPers += g.membresGroupe.size();
		 }
	        
        int nbMax = Principale.plan.listTables.get(indiceTable).getNbPlacseMax();
		System.out.println("Nb Personne sur table : " + nbPers);
		System.out.println("Limite de personne : " + nbMax);
	    if(composant != null) {
//    		System.out.println("================");
//    	    System.out.println("num de la table : " + numTable);
//          System.out.println(nbPers);
//          System.out.println(composant.getId());
//          System.out.println("================");
	    	if(cocher) {
	    		if(nbPers==0) {
				    composant.setStyle(
				    	    "-fx-border-color: green;" +
				    	    "-fx-background-color: white;" +
				    	    "-fx-border-radius: 360;" +
				    	    "-fx-border-width: 4"
			    	);
				    
		    	}else if(nbPers == nbMax) {
		    		composant.setStyle(
				    	    "-fx-border-color: red;" +
				    	    "-fx-background-color: white;" +
				    	    "-fx-border-radius: 360;" +
				    	    "-fx-border-width: 4"
			    	);
		    	}else if(nbPers <= nbMax/2) {
		    		composant.setStyle(
				    	    "-fx-border-color: yellow;" +
				    	    "-fx-background-color: white;" +
				    	    "-fx-border-radius: 360;" +
				    	    "-fx-border-width: 4"
			    	);
		    	}else if(nbPers > nbMax/2) {
		    		composant.setStyle(
				    	    "-fx-border-color: orange;" +
				    	    "-fx-background-color: white;" +
				    	    "-fx-border-radius: 360;" +
				    	    "-fx-border-width: 4"
			    	);
		    	}
	    	}else {
	    		composant.setStyle(
			    	    "-fx-border-color: black;" +
			    	    "-fx-background-color: white;" +
			    	    "-fx-border-radius: 360;" +
			    	    "-fx-border-width: 1"
		    	);

	    	}
	    	
	    }
	}
	
	void surligneTablePresent(int num) {
		System.out.println("test");
		String id = "";
		if(num>10) {
			id = "gd" + String.valueOf(1+num-10);
		}else {
			id = "pt" + String.valueOf(1+num);
		}
		
	    Parent racine = loader.getRoot(); // ou Pane si tu es sûr
	    Node composant = racine.lookup("#" + id);

	    if (composant != null) {
		    System.out.println(composant.getId());
		    
	        String styleInitial = composant.getStyle();

		    composant.setStyle(
		    	    "-fx-border-color: #9900cf;" +
		    	    "-fx-background-color: white;" +
		    	    "-fx-border-radius: 360;" +
		    	    "-fx-border-width: 4"

		    	);
		    
		    PauseTransition pause = new PauseTransition(Duration.seconds(5));
	        pause.setOnFinished(e -> composant.setStyle(styleInitial));
	        pause.play();


	    } else {
	        System.out.println("Aucun composant trouvé avec l’ID : " + id);
	    }
	}
	
	void selectPersMoov(String nom) {
		ctrl.selectPersonneMoov(nom);
	}
	
	void selectGrpMoov(String nom) {
		ctrl.selectGroupeMoov(nom);
	}
	
	void deplacerPersPourCtrl() {
		ctrl.deplacerPersonne();
	}
	
    public void actualiserStatutTablesCtrl() {
        ctrl.afficheStatus(null);
    }
    
    public void supprimerGroupe(Groupe g) {
    	ctrl.supprimerGroupeVide(g);
    }
}