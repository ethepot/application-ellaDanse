package sae201;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FenInfoTable extends Stage{
	
	private CtrlInfoTable ctrl;

	
	public FenInfoTable() throws IOException {
		this.setResizable(false);
		this.setTitle("Information table");
		Scene sc = new Scene(creerSceneGraph());	
		this.setScene(sc);
	}

	private Pane creerSceneGraph() throws IOException {
		// Dans l'instruction suivante, indiquer le chemin complet du fichier disponibilites.fxml 
		File f = new File("bin/InfoTable.fxml");
		FXMLLoader loader;
		loader = new FXMLLoader(f.toURI().toURL());
		Pane racine = loader.load();
		ctrl = loader.getController();
		return racine;
	}
	
	void afficheNbPersTableCtrl() {
		ctrl.afficheNbPers();
	}
	
	void viderTableCtrl() {
        int indexTemp = SessionContext.currentTableIndex;
        Table table = Principale.plan.listTables.get(indexTemp - 1);

        // Vider les personnes
        for (int i = 0; i < table.listePersonneSurTable.size(); i++) {
            Personne personne = table.listePersonneSurTableObjet.get(i);
            Principale.plan.listTables.get(indexTemp).enleverPersonneTable(personne);

        }

        table.listePersonneSurTable.clear();
        table.listePersonneSurTableObjet.clear();

        // Vider les groupes
        for (int i = 0; i < table.listeGroupeSurTable.size(); i++) {
            String nomGroupe = table.listeGroupeSurTable.get(i);
            Groupe groupe = table.listeGroupeSurTableObjet.get(i);

            if (!Principale.plan.listeGroupeEnAttente.contains(nomGroupe)) {
                Principale.plan.listeGroupeEnAttente.add(nomGroupe);
            }
            if (!Principale.plan.listeGroupeAttenteObjet.contains(groupe)) {
                Principale.plan.listeGroupeAttenteObjet.add(groupe);
            }
        }

        table.listeGroupeSurTable.clear();
        table.listeGroupeSurTableObjet.clear();

        // Mettre à jour l'affichage
        ctrl.afficheNbPers(); // si tu as déjà cette méthode

        // Facultatif : message de confirmation console
        System.out.println("Table " + indexTemp + " vidée.");
    }
	
	void retirerGrpVide(Groupe g) {
		ctrl.retirerGrpVide(g);
	}
	

	
	void initialisation(int idTable) {
		ctrl.initTable(idTable);
	}
}
