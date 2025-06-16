package sae201;

import java.util.Optional;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;


public class CtrlInfoTable {

    @FXML
    private Label labelNbPers;

    @FXML
    private Button btOk;

    @FXML
    private Label labelNumTable;

    @FXML
    private Button btMvGrp;

    @FXML
    private Button btViderTable;

    @FXML
    private ListView<String> listViewPers;

    @FXML
    private ListView<String> listViewGrp;

    @FXML
    private Button btAddGrp;

    @FXML
    private Label labelTypeTable;
    
    @FXML
    private Label labelNbPersMax;
    
    @FXML
    private Button btAnnuler;

    @FXML
    private Button btDelPers;

    @FXML
    private Button btDelGrp;

    @FXML
    private Button btAddPers;

    @FXML
    private Button btMvPers;


    
//    private final IntegerProperty zero = new SimpleIntegerProperty(0);


    
    
    void afficheNbPers() {
    	System.out.println("Affichage essayer");
        int nbPers = 0;
//    	int indexTemp = Integer.parseInt(labelNumTable.getText()) + (labelTypeTable.getText().equals("Gd") ? 10 : 0);
    	int indexTemp=SessionContext.currentTableIndex;
    	
    	if(indexTemp >0) {
            // Ajouter les personnes individuelles sur la table
            nbPers += Principale.plan.listTables.get(indexTemp-1).listePersonneSurTable.size();

            // Ajouter les membres des groupes sur la table
            for (Groupe groupe : Principale.plan.listTables.get(indexTemp-1).listeGroupeSurTableObjet) {
                if (groupe != null && groupe.membresGroupe != null) {
                    nbPers += groupe.membresGroupe.size();
                }
            }

            // Affichage dans le label
            System.out.println("nombre de personne sur la table" + nbPers);
            labelNbPers.setText(String.valueOf(nbPers));
    	}
    }

    
    @FXML
    void ajouterPers(ActionEvent event) {
    	Alert alert = new Alert(AlertType.ERROR);
//    	int indexTemp = Integer.parseInt(labelNumTable.getText()) + (labelTypeTable.getText().equals("Gd") ? 10 : 0);
    	int indexTemp=SessionContext.currentTableIndex;
    	alert.setTitle("Erreur !");
    	alert.setHeaderText("La table est pleine ");
    	
    	int nbPers = Principale.plan.listTables.get(indexTemp-1).listePersonneSurTable.size();

        for (Groupe groupe : Principale.plan.listTables.get(indexTemp-1).listeGroupeSurTableObjet) {
            if (groupe != null && groupe.membresGroupe != null) {
                nbPers += groupe.membresGroupe.size();
            }
        }
    	
    	if(nbPers < Principale.plan.listTables.get(indexTemp-1).getNbPlacseMax()) {
    		Principale.ouvrireListPersonne();
    		afficheNbPers();
    	}else {
    		alert.showAndWait();
    	}
    	
    }

    
    private Personne getPersonneSelectionnerInfoTable() {
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
    void retirerPers(ActionEvent event) {
        //Remettre dans le plan
    	int indexTemp=SessionContext.currentTableIndex;
        Personne p = getPersonneSelectionnerInfoTable();

        //Retirer de la table
        Principale.plan.listTables.get(indexTemp-1).enleverPersonneTable(p);
        afficheNbPers();
    }
    
    @FXML
    void deplacerPers(ActionEvent event) {
    	// Enlève la personne de la table, ouvre la fenetre de choix de la table et l'ajoute a l'indice créer
    	int indexTemp=SessionContext.currentTableIndex;
    	//Remettre dans le plan
    	String nomDeplacer = listViewPers.getSelectionModel().getSelectedItem();
    	Personne p = getPersonneSelectionnerInfoTable();
    	//Retirer de la table
    	SessionContext.deplacementEnCours=true;

    	Principale.plan.listTables.get(indexTemp-1).enleverPersonneTable(p);
    	Principale.fermerInfoTable();
    	Principale.selectionPersonneDeplacer(nomDeplacer);
    	Principale.deplacerPersPourFen();
    }

    
    
    @FXML
    void ajouterGrp(ActionEvent event) { 
    	int indexTemp=SessionContext.currentTableIndex;

    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Erreur !");
    	alert.setHeaderText("La table est pleine ");    		
    	
    	int nbPers = Principale.plan.listTables.get(indexTemp-1).listePersonneSurTable.size();

        // Ajouter les membres des groupes sur la table
        for (Groupe groupe : Principale.plan.listTables.get(indexTemp-1).listeGroupeSurTableObjet) {
            if (groupe != null && groupe.membresGroupe != null) {
                nbPers += groupe.membresGroupe.size();
            }
        }
    	if(nbPers < Principale.plan.listTables.get(indexTemp).getNbPlacseMax()) {
    		Principale.ouvrireListGroupe();
    		afficheNbPers();
    	}else {
    		alert.showAndWait();
    	}
    	
        
    }
    
    private Groupe getGroupeSelectionnerInfoTable() {
    	int indexTemp=SessionContext.currentTableIndex;
        String nomSelectionne = listViewGrp.getSelectionModel().getSelectedItem();
    	for (Groupe elt : Principale.plan.listTables.get(indexTemp-1).listeGroupeSurTableObjet) {
    		System.out.println(elt.affiche());
			if (elt.getNom().equals(nomSelectionne)) {
				return elt;
			}
		}
    	return null;
    }

    @FXML
    void retirerGrp(ActionEvent event) {
    	int indexTemp=SessionContext.currentTableIndex;

        //Remettre dans le plan
        Groupe g = getGroupeSelectionnerInfoTable();
        //Retirer de la table

        //Retirer de la table'
        Principale.plan.listTables.get(indexTemp-1).enleverGroupeDeTablePourAttente(g);

        afficheNbPers();
   }
    
    void retirerGrpVide(Groupe g) {
    	System.out.println("je suis là");
    	int indexTemp=SessionContext.currentTableIndex;
        //Retirer de la table'
        Principale.plan.listTables.get(indexTemp-1).enleverGroupeDeTablePourAttente(g);
        afficheNbPers();
   }

    @FXML
    void deplacerGrp(ActionEvent event) {
    	int indexTemp=SessionContext.currentTableIndex;

    	// Retirer le groupe de la table et la remettre en attente
    	String nomGroupeDeplacer = listViewGrp.getSelectionModel().getSelectedItem();
    	Groupe g = getGroupeSelectionnerInfoTable();
    	//Retirer de la table
    	
    	//Retirer de la table'
    	Principale.plan.listTables.get(indexTemp-1).enleverGroupeDeTablePourAttente(g);
    	SessionContext.deplacementEnCours=true;
    	Principale.fermerInfoTable();
    	Principale.selectionGroupeDeplacer(nomGroupeDeplacer);
    	Principale.deplacerPersPourFen();
    	
    }

    @FXML
    void viderTable(ActionEvent event) {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation");
    	alert.setHeaderText("Confirmation pour vider la table !");
    	alert.setContentText("Êtes vous sûr de vouloir vider la table ?");
    	
    	ButtonType boutonOui = new ButtonType("Oui");
    	ButtonType boutonNon = new ButtonType("Non");
    	alert.getButtonTypes().setAll(boutonOui, boutonNon);

    	// Affiche l'alerte et récupère le résultat
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.isPresent() && result.get() == boutonOui) {
    		Principale.viderTablePrincipale();

    	}
               
    }


    @FXML
    void valider(ActionEvent event) {
    	Principale.fermerInfoTable();
    }
    
    void doubleClicPersonnes(MouseEvent e) {
        if (e.getClickCount() == 2 && e.getButton() == MouseButton.PRIMARY) {
            String selected = listViewPers.getSelectionModel().getSelectedItem();
            if (selected != null) {
            	System.out.println("Selected --->" + selected);
                for (Personne p : Principale.plan.listeToutesLesPersonnes) {
                    if (p.affiche().equals(selected)) {
                    	System.out.println(p.affiche());
                        Principale.ouvrirFichePersonne(p);
                        break;
                    }
                }
            }
        }
    }
    
    void doubleClicGroupes(MouseEvent e) {
        if (e.getClickCount() == 2 && e.getButton() == MouseButton.PRIMARY) {
            String selected = listViewGrp.getSelectionModel().getSelectedItem();
            if (selected != null) {
                for (Groupe g : Principale.plan.listeTousLesGroupes) {
                    if (g.affiche().equals(selected)) {
                        Principale.ouvrirFenMembresDuGroupe(g);
                        break;
                    }
                }
            }
        }
    }

    
	void initTable(int idTable) {
		int indexTableSelectionner = SessionContext.tableSelectionner;
    	System.out.println("numero de la table selectionner" + (indexTableSelectionner));
    	
    	if((indexTableSelectionner) >10) {
    		labelTypeTable.setText("Gd ");
    		labelNumTable.setText(String.valueOf((indexTableSelectionner)-10));
    		labelNbPersMax.setText(String.valueOf(12));
    		
    	}else {
    		labelTypeTable.setText("Pt ");
    		labelNumTable.setText(String.valueOf((indexTableSelectionner)));
    		labelNbPersMax.setText(String.valueOf(6));
    	}  
    	SessionContext.currentTableIndex=idTable;
    	
    	listViewPers.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	listViewPers.setItems(Principale.plan.listTables.get(indexTableSelectionner-1).listePersonneSurTable);
    	listViewGrp.setItems(Principale.plan.listTables.get(indexTableSelectionner-1).listeGroupeSurTable);
    	afficheNbPers();

	}

    public void initialize() {    	
		Table tp1 = new Table(1,6);
		Table tp2 = new Table(2,6);
		Table tp3 = new Table(3,6);
		Table tp4 = new Table(4,6);
		Table tp5 = new Table(5,6);
		Table tp6 = new Table(6,6);
		Table tp7 = new Table(7,6);
		Table tp8 = new Table(8,6);
		Table tp9 = new Table(9,6);
		Table tp10 = new Table(10,6);
		Table tg1 = new Table(1,12);
		Table tg2 = new Table(2,12);
		Table tg3 = new Table(3,12);
		Table tg4 = new Table(4,12);
		Table tg5 = new Table(5,12);
		Table tg6 = new Table(6,12);
		Table tg7 = new Table(7,12);
		Table tg8 = new Table(8,12);
		Table tg9 = new Table(9,12);
		Table tg10 = new Table(10,12);
		Table tg11 = new Table(11,12);
		Table tg12 = new Table(12,12);
		Table tg13 = new Table(13,12);
		Table tg14 = new Table(14,12);
		Table tg15 = new Table(15,12);
		Table tg16 = new Table(16,12);
		Table tg17 = new Table(17,12);
		Table tg18 = new Table(18,12);
		Table tg19 = new Table(19,12);
		Table tg20 = new Table(20,12);

		// Créer des personnes
    	Personne p1 = new Personne("Gabin","Chevalier","Mail","Tel");
    	Personne p2 = new Personne("Paolo","Perche","Mail","Tel");
    	Personne p3 = new Personne("Ewen","Thepot","Mail","Tel");
    	Personne p4 = new Personne("Clément","Philippo","Mail","Tel");
    	Personne p5 = new Personne("Titouan","Louis-Renault","Mail","Tel");
    	Personne p6 = new Personne("Nathan","Lehebel","Mail","Tel");
    	Personne p7 = new Personne("Nicolas","Moraux","Mail","Tel");
    	Personne p8 = new Personne("Arthur","Chevalier","Mail","Tel");
    	Personne p9 = new Personne("Thomas","Lahreur","Mail","Tel");
    	Personne p10 = new Personne("Thelio","Bleuzen","Mail","Tel");
    	Personne p11 = new Personne("Kaelig","Hilaire","Mail","Tel");
    	Personne p12 = new Personne("Guewen","Maréchal","Mail","Tel");
    	Personne p13 = new Personne("Jason","Chevalier","Mail","Tel");

    	/////////////////////
    	// Créer un groupe //
    	/////////////////////
    		//Groupe 1
    	Groupe g1 = new Groupe();
    	Groupe.listDesGroupes.add(g1);

    	
    		//Groupe 2
    	
    	Groupe g2 = new Groupe();
    	Principale.plan.listeGroupeEnAttente.add(g2.affiche());
    	Principale.plan.listeGroupeAttenteObjet.add(g2);

    	
    		//Groupe 3
    	Groupe g3 = new Groupe();
    	Principale.plan.listeGroupeEnAttente.add(g3.affiche());
    	Principale.plan.listeGroupeAttenteObjet.add(g3);

    	Principale.plan.listeTousLesGroupes.add(g1);
    	Principale.plan.listeTousLesGroupes.add(g2);
    	Principale.plan.listeTousLesGroupes.add(g3);
    	
    	// Ajouter g1 et p6 à une table    	
    	Principale.plan.listTables.get(0).listeGroupeSurTable.add(g1.affiche());
    	Principale.plan.listTables.get(0).listeGroupeSurTableObjet.add(g1);
    	
    	Principale.plan.listTables.get(0).listePersonneSurTable.add(p6.affiche());
    	Principale.plan.listTables.get(0).listePersonneSurTableObjet.add(p6);
    	
    	// Ajouter des personnes dans les groupes
        g1.ajouterPersonneDansGroupe(p1);
        g1.ajouterPersonneDansGroupe(p2);
        g2.ajouterPersonneDansGroupe(p3);
        g2.ajouterPersonneDansGroupe(p4);
    	g3.ajouterPersonneDansGroupe(p7);
    	
    	// Ajouter au plan (en attente) g2 et p5
    	Principale.plan.ajouterGroupeEnAttente(g2);
    	Principale.plan.ajouterPersonneEnAttente(p5);
    	Principale.plan.ajouterPersonneEnAttente(p8);
    	Principale.plan.ajouterPersonneEnAttente(p9);
    	Principale.plan.ajouterPersonneEnAttente(p10);
    	Principale.plan.ajouterPersonneEnAttente(p11);
    	Principale.plan.ajouterPersonneEnAttente(p12);
    	Principale.plan.ajouterPersonneEnAttente(p13);
	


    	//Grisage de bouton
    	
    	
    	
    	btDelGrp.disableProperty().bind(
				Bindings.equal(-1,listViewGrp.getSelectionModel().selectedIndexProperty()));
    	btDelPers.disableProperty().bind(
				Bindings.equal(-1,listViewPers.getSelectionModel().selectedIndexProperty()));
    	btMvGrp.disableProperty().bind(
				Bindings.equal(-1,listViewGrp.getSelectionModel().selectedIndexProperty()));
    	btMvPers.disableProperty().bind(
				Bindings.equal(-1,listViewPers.getSelectionModel().selectedIndexProperty()));
    	
    	// Si il y a personne ça grise le bouton vider
    	IntegerBinding nbPersBinding = Bindings.createIntegerBinding(
    		    () -> Integer.parseInt(labelNbPers.getText()),
    		    labelNbPers.textProperty()
    		);

		btViderTable.disableProperty().bind(nbPersBinding.isEqualTo(0));
		
        listViewPers.setOnMouseClicked(e -> doubleClicPersonnes(e));
        listViewGrp.setOnMouseClicked(e -> doubleClicGroupes(e));
    	
        // Bind du nombre de personne sur la table
        



        
    }
}
