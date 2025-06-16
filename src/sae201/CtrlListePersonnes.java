package sae201;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class CtrlListePersonnes {

    @FXML
    private Button bnAnnuler;

    @FXML
    private Button bnValider;
    
    @FXML
    private ListView<String> listViewPers;
    
    Personne getPersonneSelectionner() {
        Personne personneSelectionnee = null;
        String selected = listViewPers.getSelectionModel().getSelectedItem();
        if (selected != null) {
            for (Personne p : Principale.plan.listeToutesLesPersonnes) {
                if (p.affiche().equals(selected)) {
                    personneSelectionnee = p;
                }
            }
        }
        return personneSelectionnee;
    }
    
    @FXML
    void valider(ActionEvent event) {
        Principale.fermerListePersonne();
        int index = SessionContext.currentTableIndex;
        // Transport Attente --> Table
        Principale.plan.listTables.get(index-1).ajouterPersonneTable(getPersonneSelectionner(), index);
        // Transport Table --> Attente
//        Principale.plan.listTables.get(index-1).ajouterPersonneAttente(getPersonneSelectionner());
        Principale.afficherNbPersTable();
    }
    
    @FXML
    void annuler(ActionEvent event) {
    	Principale.fermerListePersonne();
    }
    
    


    
    public void initialize() {
    	
    	listViewPers.getItems().clear();    	
    	listViewPers.setItems(Principale.plan.listePersonneEnAttente);
    	
    	bnValider.disableProperty().bind(
				Bindings.equal(-1,listViewPers.getSelectionModel().selectedIndexProperty()));   	
    }

}
