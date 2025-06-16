package sae201;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class CtrlPlan {
    @FXML
    private Button bnSupprimerGroupe;

    @FXML
    private Button gd10;

    @FXML
    private Button gd11;

    @FXML
    private Button gd12;

    @FXML
    private Button gd13;

    @FXML
    private TextField labelNom;

    @FXML
    private ListView<String> listePersonneAttente;

	@FXML
    private ListView<String> listeGroupeAttente;

    @FXML
    private Button pt10;

    @FXML
    private Button bnSupprimerPersonne;

    @FXML
    private Button bnAjouter;

    @FXML
    private Button bnCreerPersonne;

    @FXML
    private Button gd20;

    @FXML
    private Button pt1;

    @FXML
    private Button bnRecherche;

    @FXML
    private Button pt3;

    @FXML
    private TextField labelPrenom;

    @FXML
    private Button pt2;

    @FXML
    private Button pt5;

    @FXML
    private Button gd14;

    @FXML
    private Button bnCreerGroupe;

    @FXML
    private Button pt4;

    @FXML
    private Button gd15;

    @FXML
    private Button pt7;

    @FXML
    private Button gd16;

    @FXML
    private Button pt6;

    @FXML
    private Button gd17;

    @FXML
    private Button pt9;

    @FXML
    private Button gd18;

    @FXML
    private Button gd2;

    @FXML
    private Button pt8;

    @FXML
    private Button gd1;

    @FXML
    private Button gd19;

    @FXML
    private Button bnSupprimerPlan;

    @FXML
    private Button gd4;

    @FXML
    private Button gd3;

    @FXML
    private Button gd6;

    @FXML
    private Button gd5;

    @FXML
    private Button gd8;

    @FXML
    private Button gd7;

    @FXML
    private Button gd9;
    
    @FXML
    private CheckBox checkBoxStatusTable;

    @FXML
    private VBox legendeStatut1;

    @FXML
    private VBox legendeStatut2;
    
    @FXML
    void creerPersonne(ActionEvent event) {
    	Principale.ouvrireCreerPersonne();
    }

    @FXML
    void supprimerPersonne(ActionEvent event) {
    	String personneSelectionnerString = listePersonneAttente.getSelectionModel().getSelectedItem();
    	Personne personneSelectionnerObjet = null;
    	for (Personne p : Principale.plan.listeToutesLesPersonnes) {
			if(p != null) {
				String nomComplet = p.getPrenom() + " " + p.getNom();
				if(nomComplet.equals(personneSelectionnerString)) {
					personneSelectionnerObjet=p;
					break;
				}
			}
		}

    	Principale.plan.listeToutesLesPersonnes.remove(personneSelectionnerObjet);
    	Principale.plan.listePersonneAttenteObjet.remove(personneSelectionnerObjet);
    	Principale.plan.listePersonneEnAttente.remove(personneSelectionnerString);
    }

    @FXML
    // Peut être faire une méthode à part pour les ajout (jsp si y'en a une qui existe déjà ou pas
    void creerGroupe(ActionEvent event) {
    	Groupe nouveauGroupe = new Groupe();
    	Principale.plan.listeGroupeAttenteObjet.add(nouveauGroupe);
    	Principale.plan.listeGroupeEnAttente.add(nouveauGroupe.affiche());
    	Principale.plan.listeTousLesGroupes.add(nouveauGroupe);
    	
        listePersonneAttente.getSelectionModel().clearSelection();
        listeGroupeAttente.getSelectionModel().clearSelection();
        listeGroupeAttente.getSelectionModel().select(nouveauGroupe.affiche());
        Principale.ouvrirFenMembresDuGroupe(nouveauGroupe);

    }

    @FXML
    void supprimerGroupe(ActionEvent event) {
    	
    	String groupeSelectionnerString = listeGroupeAttente.getSelectionModel().getSelectedItem();
    	Groupe groupeSelectionnerObjet = null;
    	for (Groupe g : Principale.plan.listeTousLesGroupes) {
			if(g != null) {
				if(g.getNom().equals(groupeSelectionnerString)) {
					groupeSelectionnerObjet=g;
					break;
				}
			}
		}
    	if(groupeSelectionnerObjet != null) {
        	for (Personne membre : groupeSelectionnerObjet.membresGroupe) {
    			Principale.plan.listePersonneAttenteObjet.add(membre);
    			Principale.plan.listePersonneEnAttente.add(membre.affiche());
    		}
    	}

    	Principale.plan.listeTousLesGroupes.remove(groupeSelectionnerObjet);
    	Principale.plan.listeGroupeAttenteObjet.remove(groupeSelectionnerObjet);
    	Principale.plan.listeGroupeEnAttente.remove(groupeSelectionnerString);
    }    
    
    public void supprimerGroupeVide(Groupe g) {
    	Principale.plan.listeTousLesGroupes.remove(g);
    	Principale.plan.listeGroupeAttenteObjet.remove(g);
    	Principale.plan.listeGroupeEnAttente.remove(g.affiche());
    	if (g.getNumTable() != "") {
        	Principale.plan.listeGroupeEnAttente.remove(g.affiche());
    	}
    	Groupe.setNombreGroupe(Groupe.getNombreGroupe()-1);
    }
    
    public void enleverGroupeVideTable(Groupe g) {
    	for (Table t : Principale.plan.listTables) {
    		if (t.listeGroupeSurTableObjet.contains(g)) {
    			t.enleverGroupeDeTablePourAttente(g);
    		}
    	}
    }
    
    @FXML
    void ajouter(ActionEvent event) {
    	System.out.println("List view personne " + listePersonneAttente.getSelectionModel().getSelectedIndex());
    	System.out.println("List view groupe " + listeGroupeAttente.getSelectionModel().getSelectedIndex());
    	SessionContext.personneSelectionnerAAJouter=null;
    	SessionContext.groupeSelectionnerAAJouter=null;
    	if (listePersonneAttente.getSelectionModel().getSelectedIndex()>=0){
    		String personneSelectionnee = listePersonneAttente.getSelectionModel().getSelectedItem();
    		for (Personne p : Principale.plan.listeToutesLesPersonnes) {
    		    if (p.affiche().equals(personneSelectionnee)) {
    		        SessionContext.personneSelectionnerAAJouter = p;
    		        break;
    		    }
    		}
    		
    	}else if(listeGroupeAttente.getSelectionModel().getSelectedIndex()>=0) {
        	String groupeSelectionnerString = listeGroupeAttente.getSelectionModel().getSelectedItem();
        	for (Groupe g : Principale.plan.listeTousLesGroupes) {
    			if(g.affiche().equals(groupeSelectionnerString)) {
        			SessionContext.groupeSelectionnerAAJouter = g;
        			break;
        		}
			}
    	}
    	SessionContext.deplacementEnCours=false;
    	Principale.ouvrireAjouterPersATable();
    }

    @FXML
    void supprimerPlan(ActionEvent event) {
    	
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation");
    	alert.setHeaderText("Confirmation pour vider le plan !");
    	alert.setContentText("Êtes vous sûr de vouloir vider le plan ?");
    	
    	ButtonType boutonOui = new ButtonType("Oui");
    	ButtonType boutonNon = new ButtonType("Non");
    	alert.getButtonTypes().setAll(boutonOui, boutonNon);

    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.isPresent() && result.get() == boutonOui) {
    		for (int i = 0; i < Principale.plan.listTables.size(); i++) {
                // Copie de la liste pour éviter les modifications concurrentes
                List<Personne> copiePers = new ArrayList<>(Principale.plan.listTables.get(i).listePersonneSurTableObjet);
                for (Personne p : copiePers) {
                    if (p != null) {
                        Principale.plan.listTables.get(i).enleverPersonneTable(p);
                    }
                }

                List<Groupe> copieGroupes = new ArrayList<>(Principale.plan.listTables.get(i).listeGroupeSurTableObjet);
                for (Groupe g : copieGroupes) {
                    if (g != null) {
                        Principale.plan.listTables.get(i).enleverGroupeDeTablePourAttente(g);
                    }
                }
            }

            Principale.plan.listeTousLesGroupes.clear();
            Groupe.listDesGroupes.clear();
            Principale.plan.listeToutesLesPersonnes.clear();
            Principale.plan.listeGroupeAttenteObjet.clear();
            Principale.plan.listeGroupeEnAttente.clear();
            Principale.plan.listePersonneAttenteObjet.clear();
            Principale.plan.listePersonneEnAttente.clear();
            
            Groupe.setNombreGroupe(1);
    	}        
    }

    @FXML
    void ouvreInfoTable(ActionEvent event) {
        Button clickedButton = (Button) event.getSource(); // Récupère le bouton cliqué
        String id = clickedButton.getId(); // Récupère l'id (ex : "Bt12")
        
        int resultat = Integer.parseInt(id.replaceAll("\\D", "")) + (id.startsWith("gd") ? 10 : 0);
        System.out.println(resultat);
        SessionContext.tableSelectionner=resultat;
    	Principale.ouvrireInfoTable(resultat);
    }

    Boolean presentSurTable(int indiceTable, String nomPrenom) {
    	Boolean estPresent = false;
    	
    	for (String elt : Principale.plan.listTables.get(indiceTable).listePersonneSurTable) {
			if(elt != null) {
    			if (elt.equals(nomPrenom)){
					estPresent = true;
					break;
				}
    		}
		}
    	for (Groupe g : Principale.plan.listTables.get(indiceTable).listeGroupeSurTableObjet) {
    		if (g != null) {
        		for (String elt : g.listeMembres) {
        			if (elt != null) {
        				if (elt.equals(nomPrenom)){
        					estPresent = true;
        					break;
        				}
        			}
    			}
    		}

    	}

    	return estPresent;
    }
    
    int trouverTableOuEstPersonne(String nomPrenom) {
    	int numeroTable = -1;
    	System.out.println(nomPrenom);
    	for (int i = 0; i < Principale.plan.listTables.size(); i++) {
    		if(presentSurTable(i,nomPrenom)) {
    			numeroTable=i;
    			break;
    		}
			
		}
    	return numeroTable;
    }
    
    Groupe presentDansGroupe(String nomPrenom) {
    	Groupe rep = null;
    	for (Groupe g : Principale.plan.listeGroupeAttenteObjet) {
			if (g != null) {
				for (String elt : g.listeMembres) {
					if (elt != null) {
						if (elt.equals(nomPrenom)) {
							rep = g;
						}
					}
				}
			}
		}
    	return rep;
    }
    
    @FXML
    void rechercher(ActionEvent event) {	
        String prenom = labelPrenom.getText();
        String nom = labelNom.getText();
        String nomPrenom = prenom + " " + nom;

        int indexTable = trouverTableOuEstPersonne(nomPrenom);
        boolean estDansListeAttente = Principale.plan.listePersonneEnAttente.contains(nomPrenom);
        
        if(presentDansGroupe(nomPrenom) != null) {
            System.out.println("La personne est dans un groupe en attente");
            int indexGroupe = Principale.plan.listeGroupeEnAttente.indexOf(presentDansGroupe(nomPrenom).affiche());
            System.out.println(indexGroupe);
            listeGroupeAttente.getSelectionModel().select(indexGroupe);
        }else if (estDansListeAttente) {
            System.out.println("La personne est en attente");
            int indexPersonne = Principale.plan.listePersonneEnAttente.indexOf(nomPrenom);
            listePersonneAttente.getSelectionModel().select(indexPersonne);
        } else if (indexTable > -1) {
            System.out.println("La personne est présente sur la table " + indexTable);            
            Principale.genererIdTable(indexTable);
        } else {
            System.out.println("Personne introuvable");
            Alert erreur = new Alert(Alert.AlertType.ERROR);
            erreur.setContentText("ATTENTION : " + nomPrenom + " n'est pas présent dans le plan de table ! ");
            erreur.setTitle("Erreur de recherche");
            erreur.showAndWait();
        }

        labelPrenom.clear();
        labelNom.clear();
    }
    
    
    void doubleClicPersonnes(MouseEvent e) {
        if (e.getClickCount() == 2 && e.getButton() == MouseButton.PRIMARY) {
            String selected = listePersonneAttente.getSelectionModel().getSelectedItem();
            if (selected != null) {
                for (Personne p : Principale.plan.listeToutesLesPersonnes) {
                    if (p.affiche().equals(selected)) {
                        Principale.ouvrirFichePersonne(p);
                        break;
                    }
                }
            }
        }
    }
    
    void doubleClicGroupes(MouseEvent e) {
        if (e.getClickCount() == 2 && e.getButton() == MouseButton.PRIMARY) {
            String selected = listeGroupeAttente.getSelectionModel().getSelectedItem();
            System.out.println("selected " + selected);
            if (selected != null) {
                for (Groupe g : Principale.plan.listeTousLesGroupes) {
                    if (g.affiche().equals(selected)) {
                    	System.out.println(g.affiche());
                        Principale.ouvrirFenMembresDuGroupe(g);
                        break;
                    }
                }
            }
        }
    }
    
    void selectPersonneMoov(String nom) {
    	listePersonneAttente.getSelectionModel().clearSelection();
    	listeGroupeAttente.getSelectionModel().clearSelection();
        listePersonneAttente.getSelectionModel().select(nom);
    }
    
    void selectGroupeMoov(String nom) {
    	listePersonneAttente.getSelectionModel().clearSelection();
    	listeGroupeAttente.getSelectionModel().clearSelection();
        listeGroupeAttente.getSelectionModel().select(nom);
    }
    
    
    // Egale a ajouter personne
    void deplacerPersonne() {
    	System.out.println("List view personne " + listePersonneAttente.getSelectionModel().getSelectedIndex());
    	System.out.println("List view groupe " + listeGroupeAttente.getSelectionModel().getSelectedIndex());
    	SessionContext.personneSelectionnerAAJouter=null;
    	SessionContext.groupeSelectionnerAAJouter=null;
    	if (listePersonneAttente.getSelectionModel().getSelectedIndex()>=0){
    		String personneSelectionnee = listePersonneAttente.getSelectionModel().getSelectedItem();
    		for (Personne p : Principale.plan.listeToutesLesPersonnes) {
    		    if (p.affiche().equals(personneSelectionnee)) {
    		        SessionContext.personneSelectionnerAAJouter = p;
    		        break;
    		    }
    		}
    	}else if(listeGroupeAttente.getSelectionModel().getSelectedIndex()>=0) {
        	String groupeSelectionnerString = listeGroupeAttente.getSelectionModel().getSelectedItem();
        	for (Groupe g : Principale.plan.listeTousLesGroupes) {
        		if(g.affiche().equals(groupeSelectionnerString)) {
        			SessionContext.groupeSelectionnerAAJouter = g;
        			break;
        		}
			}
    	}
    	Principale.ouvrireAjouterPersATable();
    	
    }
        

    
    @FXML
    void afficheStatus(ActionEvent event) {
        boolean estCoche = checkBoxStatusTable.isSelected();
        if(estCoche) {
            legendeStatut1.setVisible(true);
            legendeStatut2.setVisible(true);
            System.out.println("Affiche status");
            for (int indiceTable = 0; indiceTable < Principale.plan.listTables.size(); indiceTable++) {
                Principale.statusTableFen(indiceTable,estCoche);
            }
        }else {
            for (int indiceTable = 0; indiceTable < Principale.plan.listTables.size(); indiceTable++) {
                Principale.statusTableFen(indiceTable,estCoche);
                legendeStatut1.setVisible(false);
                legendeStatut2.setVisible(false);
            }
        }
    }
    
    public void initialize() {
        // Cache la légende du statut des tables
        legendeStatut1.setVisible(false);
        legendeStatut2.setVisible(false);
    	// Afficher sur le plan les personnes
    	listePersonneAttente.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	
    	listePersonneAttente.setItems(Principale.plan.listePersonneEnAttente);	
    	listeGroupeAttente.setItems(Principale.plan.listeGroupeEnAttente);	
    	
    	bnSupprimerPersonne.disableProperty().bind(		
    	Bindings.equal(-1, listePersonneAttente.getSelectionModel().selectedIndexProperty()));
    	
    	bnSupprimerGroupe.disableProperty().bind(		
    	    	Bindings.equal(-1, listeGroupeAttente.getSelectionModel().selectedIndexProperty()));
    	
    	bnAjouter.disableProperty().bind(
    		Bindings.and(
    			Bindings.equal(-1, listePersonneAttente.getSelectionModel().selectedIndexProperty()),
    			Bindings.equal(-1, listeGroupeAttente.getSelectionModel().selectedIndexProperty())
    		)
    	);

    	bnRecherche.disableProperty().bind(
    			labelNom.textProperty().isEmpty().or(labelPrenom.textProperty().isEmpty())
    		);

    	
    	listePersonneAttente.setOnMouseClicked(e -> doubleClicPersonnes(e));
    	listeGroupeAttente.setOnMouseClicked(e -> doubleClicGroupes(e));
    }
}
