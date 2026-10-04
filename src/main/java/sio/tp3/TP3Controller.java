package sio.tp3;

import com.sun.source.tree.Tree;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.util.Callback;
import sio.tp3.Model.Tache;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TP3Controller implements Initializable {
    private HashMap<String, HashMap<String, ArrayList<Tache>>> mesTaches;

    ArrayList<Tache> lesTaches;
    TreeItem racine;
    @FXML
    private ListView<String> lstThemes;
    @FXML
    private ListView<String> lstProjets;
    @FXML
    private TreeView tvTaches;
    @FXML
    private ComboBox<String> cboDeveloppeurs;
    @FXML
    private Button cmdValider;
    @FXML
    private TextField txtNomTache;

    Alert alert = new Alert(Alert.AlertType.ERROR);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mesTaches = new HashMap<>();
        racine = new TreeItem("Mes tâches");
        racine.setExpanded(true);
        tvTaches.setRoot(racine);
        cboDeveloppeurs.getItems().addAll("Enzo", "Noa", "Lilou", "Milo");
        cboDeveloppeurs.getSelectionModel().selectFirst();
        lstThemes.getItems().addAll("Mobile", "Web", "Réseau");
        for (int i = 1; i <= 10; i++) {
            lstProjets.getItems().add("Projet n°" + i);
        }
    }

    @FXML
    public void cmdValiderClicked(Event event) {
        if (lstThemes.getSelectionModel().getSelectedItems().isEmpty()) {
            alert.setTitle("Choix du thème");
            alert.setHeaderText(null);
            alert.setContentText("Sélectionner un thème");
            alert.showAndWait();
            return;
        }
        if (lstProjets.getSelectionModel().getSelectedItems().isEmpty()) {
            alert.setTitle("Choix du projet");
            alert.setHeaderText(null);
            alert.setContentText("Sélectionner un projet");
            alert.showAndWait();
            return;
        }
        if (txtNomTache.getText().isEmpty()) {
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Saisir une tâche");
            alert.showAndWait();
            return;
        }

        String theme = lstThemes.getSelectionModel().getSelectedItem();
        String projet = lstProjets.getSelectionModel().getSelectedItem();
        String nomTache = txtNomTache.getText();
        String developpeur = cboDeveloppeurs.getSelectionModel().getSelectedItem();

        Tache t = new Tache(nomTache, developpeur, false);

        if (mesTaches.get(theme) == null) {
            mesTaches.put(theme, new HashMap<>());
        }
        HashMap<String, ArrayList<Tache>> projets = mesTaches.get(theme);

        if (projets.get(projet) == null) {
            projets.put(projet, new ArrayList<>());
        }
        projets.get(projet).add(t);

        txtNomTache.clear();
    }

    @FXML
    public void tvTachesClicked(Event event)
    {

    }
}