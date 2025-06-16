package sae201;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CtrlFichePersonne {
	
    @FXML
    private Button bnFermer;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtNbTable;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TextField txtTel;

    @FXML
    void fermer(ActionEvent event) {
    	Principale.fermerFichePersonne();
    }
    
    public void initialize() {
    	bnFermer.setOnAction(e -> fermer(e));
    }
    
    public void init(Personne p) {    	
    	txtNom.setText(p.getNom());
    	txtPrenom.setText(p.getPrenom());
    	txtMail.setText(p.getMail());
    	txtNbTable.setText(p.getNumTable());
    	txtTel.setText(""+p.getNumTel());
    }
}