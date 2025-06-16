package sae201;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CtrlAjouterATable {

    @FXML
    private Button bnAnnuler;

    @FXML
    private Button bnOK;

    @FXML
    private TextField txtNumTable;
    
    @FXML
    
    private ComboBox<String> comboChoixTypeTable;

    @FXML
    void annuler(ActionEvent event) {
    	Principale.fermerAjouterPersATable();
    }
   

    
    @FXML
    void valider(ActionEvent event) {
    	int tableSelectionner = Integer.parseInt(txtNumTable.getText());
    	
    	Alert alertTablePleine = new Alert(Alert.AlertType.ERROR);
    	alertTablePleine.setContentText("Il n'y a pas la place sur la table !");
    	
    	String typeTableSelectionner = comboChoixTypeTable.getValue();

    	int numeroTable = tableSelectionner; 
    	if(typeTableSelectionner.equals("Grande table")) {
    		numeroTable += 10;
    	}
    	int nbPersonneSurTable = Principale.plan.listTables.get(numeroTable-1).listePersonneSurTableObjet.size();
    	
		for (Groupe elt : Principale.plan.listTables.get(numeroTable-1).listeGroupeSurTableObjet) {
			nbPersonneSurTable += elt.listeMembres.size();
		}

    	if (SessionContext.personneSelectionnerAAJouter != null) {
    		    // OK, on peut l'utiliser
    			if((nbPersonneSurTable+1) > Principale.plan.listTables.get(numeroTable-1).getNbPlacseMax()) {
    				alertTablePleine.showAndWait();
    			}else {
    				ajouterPersAttenteVersTable(numeroTable, SessionContext.personneSelectionnerAAJouter);
    			}

    		} else if (SessionContext.groupeSelectionnerAAJouter != null) {
    		    // Sinon on utilise un groupe
    			if((nbPersonneSurTable+SessionContext.groupeSelectionnerAAJouter.listeMembres.size()) > (Principale.plan.listTables.get(numeroTable-1).getNbPlacseMax())) {
    				alertTablePleine.showAndWait();
    			}else {
    				ajouterGrpAttenteVersTable(numeroTable, SessionContext.groupeSelectionnerAAJouter);
    			}
    		}
    	
    		txtNumTable.clear(); // Vide le champ de texte
    		comboChoixTypeTable.getSelectionModel().clearSelection();
    		comboChoixTypeTable.setPromptText("Type de table");
    		Principale.genererIdTable(numeroTable-1);
    		SessionContext.deplacementEnCours=false;
    		Principale.fermerAjouterPersATable();
    		
    	}

    
    void ajouterGrpAttenteVersTable(int tableSelectionner,Groupe g) {
    	Principale.plan.listTables.get(tableSelectionner-1).ajouterGroupeDansTable(g, tableSelectionner);
    }
    
    void enleverGroupeTableVersAttente(int tableSelectionner,Groupe g) {
    	Principale.plan.listTables.get(tableSelectionner-1).enleverGroupeDeTablePourAttente(g);
    }
    
    void ajouterPersAttenteVersTable(int tableSelectionner, Personne p) {
    	Principale.plan.listTables.get(tableSelectionner-1).ajouterPersonneTable(p, tableSelectionner);
    }
    
    void enleverPersTableVersAttente(int tableSelectionner, Personne p) {
    	Principale.plan.listTables.get(tableSelectionner-1).enleverPersonneTable(p);
    }
   

    public void initialize() {
        bnOK.disableProperty().bind(
            Bindings.createBooleanBinding(() -> {
                String text = txtNumTable.getText().trim();
                String type = comboChoixTypeTable.getValue();

                if (text.isEmpty() || type == null) return true;

                try {
                    int num = Integer.parseInt(text);
                    if (num <= 0) return true;

                    if (type.equals("Petite table") && num > 10) return true;
                    if (type.equals("Grande table") && num > 20) return true;

                    return false; // tout est valide
                } catch (NumberFormatException e) {
                    return true; // invalide si ce n’est pas un entier
                }
            },
            txtNumTable.textProperty(),
            comboChoixTypeTable.valueProperty())
        );
    }
    

    
    void initialisation() {
    	comboChoixTypeTable.setItems(FXCollections.observableArrayList("Petite table", "Grande table"));  
        BooleanProperty actif = new SimpleBooleanProperty(SessionContext.deplacementEnCours);
    	bnAnnuler.disableProperty().bind(actif);
    	
    	
    }
    
    
}
