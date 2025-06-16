package sae201;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;

public class CtrlCreerPersonne {
    @FXML
    private TextField txtPrenom;

    @FXML
    private Button bnFermer1;

    @FXML
    private TextField txtTel;
    
    @FXML
    private Label erreurTel;

    @FXML
    private TextField txtMail;

    @FXML
    private Button bnValider;

    @FXML
    private TextField txtNom;

   
    @FXML
    void valider(ActionEvent event) {
        if (txtPrenom.getText().isEmpty() || txtNom.getText().isEmpty() || txtTel.getText().isEmpty() || txtMail.getText().isEmpty()) {
            Alert erreur = new Alert(AlertType.ERROR, "Veuillez remplir tous les champs.", ButtonType.OK);
            erreur.setTitle("Champs vides");
            erreur.showAndWait();
            return;
        }

        String prenom = txtPrenom.getText();
        String nom = txtNom.getText();
        String tel = txtTel.getText();
        String mail = txtMail.getText();
        Personne p = new Personne(prenom,nom,mail,tel);

        Principale.plan.ajouterPersonneEnAttente(p);
        Principale.plan.ajouterPersonneDansListeToutesLesPersonnes(p);
        txtPrenom.clear();
        txtNom.clear();
        txtTel.clear();
        txtMail.clear();
        Principale.fermerCreerPersonne();
    }
    
    public void verifTel(KeyEvent event) {
	    if (estValide(txtTel.getText()) ) {
	    	erreurTel.setVisible(false);
	    } else {
	    	erreurTel.setVisible(true);
	    }
	    if (txtTel.getText().length() > 10){
    		txtTel.deletePreviousChar();
			event.consume();
			Alert erreur = new Alert(AlertType.ERROR, "Le numéro de téléphone doit être sur 10 caractères. Veuillez modifier ce champ.", ButtonType.OK);
			erreur.setTitle("Téléphone : format incorrect");
			erreur.showAndWait();
		}
	}
    
    public boolean estValide(String str) {
		return str.matches("\\d*");
    }

    @FXML
    void fermer(ActionEvent event) {
    	Principale.fermerCreerPersonne();
    }
    
    public void initialize() {
	    erreurTel.setVisible(false);
	    txtTel.setTooltip(new Tooltip("Maximum 10 chiffres"));
	    txtTel.setOnKeyTyped(e -> verifTel(e));
    }
}