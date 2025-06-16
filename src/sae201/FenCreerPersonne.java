package sae201;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class FenCreerPersonne extends Stage{	
	public FenCreerPersonne() throws IOException {
		this.setTitle("Créer ");
		Scene sc = new Scene(creerSceneGraph());
		setResizable(false);
		this.setScene(sc);
	}

	private Pane creerSceneGraph() throws IOException {
		// Dans l'instruction suivante, indiquer le chemin complet du fichier disponibilites.fxml 
		File f = new File("bin/CreerPersonne.fxml");
		FXMLLoader loader;
		loader = new FXMLLoader(f.toURI().toURL());
		Pane racine = loader.load();
		return racine;
	}
}
