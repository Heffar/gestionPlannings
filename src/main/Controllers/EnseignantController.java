package main.Controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Models.Enseignant;
import main.Models.Module;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EnseignantController implements Initializable {

    @FXML
    private JFXTextField txtNom, txtPrenom,txtGrade, txtRechercher;

    @FXML
    private TableView<Enseignant> tableView;

    @FXML private TableColumn<Enseignant, String> columnNom;
    @FXML private TableColumn<Enseignant, String> columnPrenom;
    @FXML private TableColumn<Enseignant, String> columnGrade;
    @FXML private TableColumn<Enseignant, List<Module>> columnEnseignement;

    private ObservableList<Enseignant> allEnseignants = FXCollections.observableArrayList();


    public void addEnseignantBtnClicked(){
        Enseignant enseignant = new Enseignant();

        enseignant.setNom(txtNom.getText());
        enseignant.setPrenom(txtPrenom.getText());
        enseignant.setGrade(txtGrade.getText());

        tableView.getItems().add(enseignant);

        //Ajouter à la base de données ultérieurement
        //addEnseignant(enseignant);
    }

    public void deleteEnseignantBtnClicked(){
        ObservableList<Enseignant> enseignantsSelected, enseignants;
        enseignantsSelected = tableView.getSelectionModel().getSelectedItems();

        enseignants = tableView.getItems();

        enseignantsSelected.forEach(enseignants::remove);
    }

    public void updateEnseignantBtnClicked(){
        int position;
        ObservableList<Enseignant> allLocals;
        Enseignant updateEnseignant = new Enseignant();
        allLocals = tableView.getItems();
        position = tableView.getSelectionModel().getSelectedIndex();

        updateEnseignant.setNom(txtNom.getText());
        updateEnseignant.setPrenom(txtPrenom.getText());
        updateEnseignant.setGrade(txtGrade.getText());

        allLocals.set(position, updateEnseignant);
    }


    private ObservableList<Enseignant> getAllEnseignants(){
        ObservableList<Enseignant> enseignants = FXCollections.observableArrayList();;

        //Récupérer les enseignants
        enseignants.add(new Enseignant("SALIMI", "Salim", "Professeur", new ArrayList<>()));
        enseignants.add(new Enseignant("OMARI","Ramzi","Chercheur", new ArrayList<>()));
        enseignants.add(new Enseignant("LOAZAZZA", "OZAZALSSA", "hey", new ArrayList<>()));


        return enseignants;
    }

    public void searchEnseignants(){
        //FILTER
        FilteredList<Enseignant> filteredLocal = new FilteredList<>(allEnseignants, p -> true);

        txtRechercher.textProperty().addListener((observable, oldValue, newValue) ->
                filteredLocal.setPredicate(enseignant -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (enseignant.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false;
                })
        );

        SortedList<Enseignant> sortedData = new SortedList<>(filteredLocal);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    public void enseignantLoader(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("../Views/enseignant.fxml"));
        Scene scene = new Scene(loader);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);

        window.show();
    }

    public void moduleLoader(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("../Views/module.fxml"));
        Scene scene = new Scene(loader);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);

        window.show();
    }


    public void localLoader(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("../Views/local.fxml"));
        Scene scene = new Scene(loader);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);

        window.show();
    }


    public void groupeLoader(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("../Views/groupe.fxml"));
        Scene scene = new Scene(loader);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);

        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        columnGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        tableView.setItems(getAllEnseignants());
        tableView.getColumns().addAll(columnNom, columnPrenom, columnGrade, columnEnseignement);

        searchEnseignants();
    }
}
