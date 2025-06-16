package sae201;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FenFichePersonne extends Stage{

	private CtrlFichePersonne ctrl;

	public FenFichePersonne() throws IOException {
		this.setTitle("Fiche Personne");
		Scene sc = new Scene(creerSceneGraph());
		setResizable(false);
		this.setScene(sc);
	}

	private Pane creerSceneGraph() throws IOException {
		// Dans l'instruction suivante, indiquer le chemin complet du fichier disponibilites.fxml 
		File f = new File("bin/FichePersonne.fxml");
		FXMLLoader loader;
		loader = new FXMLLoader(f.toURI().toURL());
		Pane racine = loader.load();
		ctrl = loader.getController();
		return racine;
	}
	
	public void initFenetre(Personne p){
		System.out.println("Fen ----> " + p.affiche());
		ctrl.init(p);
	}
}
