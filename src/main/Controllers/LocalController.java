package main.Controllers;

import com.jfoenix.controls.JFXCheckBox;
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
import main.Models.Local;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LocalController implements Initializable {

    /****Récupérer les champs depuis le fichier FXML
     * Obligatoire d'ajoutre l'annotation @FXML
     * l'ID des champs et widak ayi qu'on va déclarer lazem adiline kiff kiff
     *
     */

    @FXML
    private JFXTextField txtTitre, txtCapacite, txtRechercher;

    @FXML
    private JFXCheckBox checkCours, checkTD, checkTP;

    @FXML
    private TableView<Local> tableView;

    @FXML private TableColumn<Local, String> columnTitre;
    @FXML private TableColumn<Local, Integer> columnCapacite;
    @FXML private TableColumn<Local, Boolean> columnCours;
    @FXML private TableColumn<Local, Boolean> columnTD;
    @FXML private TableColumn<Local, Boolean> columnTP;

    //Liste observable des locaux qui seront affichés dans la tableview
    ObservableList<Local> allLocals = FXCollections.observableArrayList();


    //Méthode d'ajout d'un local
    public void addLocalBtnClicked(){
        Local local = new Local();

        //Récupérer les valeurs des textfields et checkbox
        local.setTitre(txtTitre.getText());
        local.setCapacite(Integer.parseInt(txtCapacite.getText()));
        local.setTypeCours(checkCours.isSelected());
        local.setTypeTD(checkTD.isSelected());
        local.setTypeTP(checkTP.isSelected());

        //Ajouter à la table
        tableView.getItems().add(local);

        //Affichage dans la console juste pour tester
        System.out.println(local.getTitre());
        System.out.println(local.getCapacite());
        System.out.println(local.isTypeCours());
        System.out.println(local.isTypeTD());
        System.out.println(local.isTypeTP());

        //Ajout vers la base de données à faire ultérieurement
        //addLocal();
    }

    //Méthode de suppression d'un local
    public void deleteLocalBtnClicked(){

        ObservableList<Local> localsSelected, allLocals;

        allLocals = tableView.getItems();
        localsSelected = tableView.getSelectionModel().getSelectedItems();

        localsSelected.forEach(allLocals::remove);
    }

    //Méthode de modification d'un local
    public void updateLocalBtnClicked(){
        int position;
        ObservableList<Local> allLocals;
        Local updateLocal = new Local();
        allLocals = tableView.getItems();
        position = tableView.getSelectionModel().getSelectedIndex();

        updateLocal.setTitre(txtTitre.getText());
        updateLocal.setCapacite(Integer.parseInt(txtCapacite.getText()));
        updateLocal.setTypeTD(checkCours.isSelected());
        updateLocal.setTypeTD(checkTD.isSelected());
        updateLocal.setTypeTP(checkTP.isSelected());

        allLocals.set(position, updateLocal);
    }


    private ObservableList<Local> getAllLocals(){

        allLocals.add(new Local("Haloum", 15, true, false, true));
        allLocals.add(new Local("L'banane", 30, true, true, true));
        allLocals.add(new Local("L'kachir", 20, false, false, true));
        allLocals.add(new Local("Heyhey", 15, true, false, true));

        return allLocals;
    }

    private void searchLocals(){
        //FILTER
        FilteredList<Local> filteredLocal = new FilteredList<>(allLocals, p -> true);

        txtRechercher.textProperty().addListener((observable, oldValue, newValue) ->
                filteredLocal.setPredicate(local -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (local.getTitre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false;
                })
        );

        SortedList<Local> sortedData = new SortedList<>(filteredLocal);
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

        columnTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        columnCapacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        columnCours.setCellValueFactory(new PropertyValueFactory<>("typeCours"));
        columnTD.setCellValueFactory(new PropertyValueFactory<>("typeTD"));
        columnTP.setCellValueFactory(new PropertyValueFactory<>("typeTP"));

        tableView.setItems(getAllLocals());
        tableView.getColumns().addAll(columnTitre, columnCapacite, columnCours, columnTD, columnTP);

        searchLocals();
    }
}
