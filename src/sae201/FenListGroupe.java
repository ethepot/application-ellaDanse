package sae201;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FenListGroupe extends Stage{
	public FenListGroupe() throws IOException {
		this.setResizable(false);
		this.setTitle("Liste des Groupes");
		Scene sc = new Scene(creerSceneGraph());	
		this.setScene(sc);
	}

	private Pane creerSceneGraph() throws IOException {
		// Dans l'instruction suivante, indiquer le chemin complet du fichier disponibilites.fxml 
		File f = new File("bin/ListeGroupe.fxml");
		FXMLLoader loader;
		loader = new FXMLLoader(f.toURI().toURL());
		Pane racine = loader.load();
		return racine;
	}
}