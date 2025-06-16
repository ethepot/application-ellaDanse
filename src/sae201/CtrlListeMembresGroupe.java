package sae201;


import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CtrlListeMembresGroupe {
	@FXML Label txtNumGroupe;

    @FXML
    private Button bnAjouter;

    @FXML
    private Button bnFermer;

    @FXML
    private Button bnSupprimer;

    @FXML
    private ListView<String> listeMembres;

    void ajouterPersonne(ActionEvent event, Groupe g) {
    	if(g.listeMembres.size()+1>12){
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setContentText("La taille maximal d'un groupe a été atteinte (12 Personnes max) !");
    	}else {
        	Principale.ouvrirFenAjouterPersonneGroupe(g);
    	}
    }

    void fermer(ActionEvent event, Groupe g) {
    	if (g.listeMembres.isEmpty()) {
        	Principale.supprimerGroupe(g);
    	}
    	Principale.fermerFenMembresDuGroupe();
    }

    void supprimerPersonne(ActionEvent event, Groupe g) {
        String selected = listeMembres.getSelectionModel().getSelectedItem();
        if (selected != null) {
            for (Personne p : g.getMembresGroupe()) {
                if (p.affiche().equals(selected)) {
                    g.retirerPersonneDeGroupe(p);
                    Principale.plan.ajouterPersonneEnAttente(p);
                    Principale.afficherNbPersTable();
                    break;
                }
            }
        }
    }
    
    void doubleClicPersonnes(MouseEvent e) {
        if (e.getClickCount() == 2 && e.getButton() == MouseButton.PRIMARY) {
            String selected = listeMembres.getSelectionModel().getSelectedItem();
            if (selected != null) {
                for (Personne p : Principale.plan.listeToutesLesPersonnes) {
                	System.out.println(p.affiche());
                    if (p.affiche().equals(selected)) {
                        Principale.ouvrirFichePersonne(p);
                        break;
                    }
                }
            }
        }
    }
    
    public void init(Groupe g) {
	    txtNumGroupe.setText(""+g.getNumGroupe());
	    listeMembres.setItems(g.listeMembres);
	    
	    bnSupprimer.disableProperty().bind(Bindings.equal(-1, listeMembres.getSelectionModel().selectedIndexProperty()));
	    
        bnAjouter.disableProperty().bind(
                g.getNbPersDansGrpObservable().greaterThanOrEqualTo(12)
            );  
	    
	    bnAjouter.setOnAction(e -> ajouterPersonne(e, g));
	    bnSupprimer.setOnAction(e -> supprimerPersonne(e, g));
    	bnFermer.setOnAction(e -> fermer(e, g));
	    listeMembres.setOnMouseClicked(e -> doubleClicPersonnes(e));
    }
}