package sae201;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class CtrlAjouterPersonneDansGroupe {

    @FXML
    private Button bnAnnuler;

    @FXML
    private Button bnValider;
    
    @FXML
    private ListView<String> listeAjouterPersonne; 
    
    @FXML
    void annuler(ActionEvent event) {
    	Principale.fermerFenAjouterPersonneGroupe();
    }

    void valider(ActionEvent event, Groupe g) {
    	String selected = listeAjouterPersonne.getSelectionModel().getSelectedItem();
    	System.out.println("Personne selectionner à ajouter" + selected);
    	String input = g.getNumTable();
    	// Vérifie que la chaîne n'est pas vide ou nulle
    	if (input != null && !input.isEmpty()) {
    	    // Extraire lettres et chiffres
    	    String letters = input.replaceAll("[0-9]", ""); // ex: "gd"
    	    String numberStr = input.replaceAll("[^0-9]", ""); // ex: "12"
			int indexTable = SessionContext.currentTableIndex;
			// On récupère le nombre de personne sur la table
			int nbPers = 0;
	        nbPers += Principale.plan.listTables.get(indexTable-1).listePersonneSurTable.size();
	        
	        for (Groupe elt : Principale.plan.listTables.get(indexTable-1).listeGroupeSurTableObjet) {
				nbPers+=elt.membresGroupe.size();
			}    	    
	        // Numéro de la table sur laquelle est le groupe
    	    if (!numberStr.isEmpty()) {
    	        int number = Integer.parseInt(numberStr);
    	        int numTable = (letters.equals("gd")) ? number + 10 : number;

    	        // Vérifie que numTable est bien dans les limites de la liste
    	        if (numTable >= 0 && numTable < Principale.plan.listTables.size()) {
    	        	System.out.println(numTable);
    	            int maxPlaces = Principale.plan.listTables.get(numTable).getNbPlacseMax();

    	            if (nbPers + 1 > maxPlaces) {
    	                Alert alert = new Alert(Alert.AlertType.ERROR);
    	                alert.setContentText("Vous ne pouvez pas ajouter de personne à ce groupe car la table est pleine");
    	                alert.showAndWait();
    	            } else {
    	            	if (selected != null) {
    	    		        for (Personne p : Principale.plan.listePersonneAttenteObjet) {
    	    		            if (p.affiche().equals(selected)) {
    	    		            	Principale.plan.retirerPersonneEnAttente(p);
    	    		            	g.ajouterPersonneDansGroupe(p);
    	    						Principale.fermerFenAjouterPersonneGroupe();
    	    						break;
    	    		            }
    	    		        }
    	    		        Principale.afficherNbPersTable();
    	    		    }
    	            }
	            }
	        }
	    }else {
	    	if (selected != null) {
		        for (Personne p : Principale.plan.listePersonneAttenteObjet) {
		            if (p.affiche().equals(selected)) {
		            	Principale.plan.retirerPersonneEnAttente(p);
		            	g.ajouterPersonneDansGroupe(p);
						Principale.fermerFenAjouterPersonneGroupe();
						break;
		            }
		        }
		    }
	    }
	    
    }
    
    public void initialize() {
    	bnValider.disableProperty().bind(
			listeAjouterPersonne.getSelectionModel().selectedItemProperty().isNull());
    	
    	bnAnnuler.setOnAction(e -> annuler(e));
    	
    	listeAjouterPersonne.setItems(Principale.plan.listePersonneEnAttente);	
    }
    
    public void init(Groupe g) {
    	bnValider.setOnAction(e -> valider(e, g));
	}
}
