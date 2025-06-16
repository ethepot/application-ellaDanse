package sae201;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class CtrlListeGroupe {

    @FXML
    private Button bnValider;

    @FXML
    private Button bnAnnuler;
    
    @FXML
    private ListView<String> listViewGroupe;

    private Groupe getGroupeSelectionner() {
        String nomSelectionne = listViewGroupe.getSelectionModel().getSelectedItem();

    	for (Groupe elt : Principale.plan.listeGroupeAttenteObjet) {
			if (elt.getNom().equals(nomSelectionne)) {
				return elt;
			}
		}
    	return null;
    	
    }
    

    // Ajout d'un groupe
    @FXML
    void valider(ActionEvent event) {
        Principale.fermerListeGroupe();

        int index = SessionContext.currentTableIndex;

        Groupe g = getGroupeSelectionner();
        if (g == null) return;

        int capaciteActuelle = Principale.plan.listTables.get(index-1).listePersonneSurTable.size();

        // Ajouter les membres des groupes sur la table
        for (Groupe groupe : Principale.plan.listTables.get(index-1).listeGroupeSurTableObjet) {
            if (groupe != null && groupe.membresGroupe != null) {
            	capaciteActuelle += groupe.membresGroupe.size();
            }
        }
        
        int capaciteMax = Principale.plan.listTables.get(index - 1).getNbPlacseMax();
        int nbNouveaux = g.membresGroupe.size();
        
        
        System.out.println(capaciteMax);
        System.out.println(capaciteActuelle);
        System.out.println(nbNouveaux);
        
        
        if (capaciteActuelle + nbNouveaux > capaciteMax) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur !");
            alert.setHeaderText("Le groupe est trop grand pour cette table ");
            alert.showAndWait();
        } else {
            Principale.plan.listTables.get(index - 1).ajouterGroupeDansTable(g, index);
            Principale.afficherNbPersTable();
        }
    }

    @FXML
    void annuler(ActionEvent event) {
    	Principale.fermerListeGroupe();
    }
    
    public void initialize() {
    	listViewGroupe.getItems().clear();    	
    	listViewGroupe.setItems(Principale.plan.listeGroupeEnAttente);
    	bnValider.disableProperty().bind(
				Bindings.equal(-1,listViewGroupe.getSelectionModel().selectedIndexProperty()));   	
    }

}
