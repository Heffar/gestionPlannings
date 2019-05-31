package main.Controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
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
import main.Models.Module;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModuleController implements Initializable {

    @FXML
    private JFXTextField txtIntitule, txtRechercher;

    @FXML
    private JFXCheckBox checkCours, checkTD, checkTP;

    @FXML
    private JFXComboBox comboNiveau, comboSpecialite;

    @FXML
    private TableView<Module> tableView;

    @FXML private TableColumn<Module, String> columnIntitule;
    @FXML private TableColumn<Module, String> columnNiveau;
    @FXML private TableColumn<Module, String> columnSpecialite;
    @FXML private TableColumn<Module, Boolean> columnCours;
    @FXML private TableColumn<Module, Boolean> columnTD;
    @FXML private TableColumn<Module, Boolean> columnTP;

    private ObservableList<Module> allModules = FXCollections.observableArrayList();


    public void addModuleBtnClicked(){
        Module module = new Module();

        module.setIntitule(txtIntitule.getText());
        module.setSpecialite(comboSpecialite.getSelectionModel().getSelectedItem().toString());
        module.setNiveau(comboNiveau.getSelectionModel().getSelectedItem().toString());
        module.setTypeCours(checkCours.isSelected());
        module.setTypeTD(checkTD.isSelected());
        module.setTypeTP(checkTP.isSelected());

        allModules.add(module);
        tableView.getItems().add(module);
    }

    public void deleteModuleBtnClicked() {
        ObservableList<Module> modulesSelected, modules;
        modulesSelected = tableView.getSelectionModel().getSelectedItems();

        modules = tableView.getItems();

        modulesSelected.forEach(modules::remove);
    }

    public void updateModuleBtnClicked(){
        int position;
        ObservableList<Module> allLocals;
        Module updateModule = new Module();
        allLocals = tableView.getItems();
        position = tableView.getSelectionModel().getSelectedIndex();

        updateModule.setIntitule(txtIntitule.getText());
        updateModule.setNiveau(comboNiveau.getSelectionModel().getSelectedItem().toString());
        updateModule.setSpecialite(comboSpecialite.getSelectionModel().getSelectedItem().toString());
        updateModule.setTypeCours(checkCours.isSelected());
        updateModule.setTypeTD(checkTD.isSelected());
        updateModule.setTypeTP(checkTP.isSelected());

        allLocals.set(position, updateModule);

    }


    private void searchModules(){
        //FILTER
        FilteredList<Module> filteredLocal = new FilteredList<>(allModules, p -> true);

        txtRechercher.textProperty().addListener((observable, oldValue, newValue) ->
                filteredLocal.setPredicate( module -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (module.getIntitule().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false;
                })
        );

        SortedList<Module> sortedData = new SortedList<>(filteredLocal);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    private ObservableList<Module> getAllModules(){
        ObservableList modules = FXCollections.observableArrayList();

        modules.add(new Module("Module1", "L3", "GL", true, false, false));
        modules.add(new Module("Module2", "M1", "ASR", true, true, false));
        modules.add(new Module("Module3", "L1", "RESYD", true, false, false));
        modules.add(new Module("Module4", "L2", "USELESS", false, false, false));
        modules.add(new Module("L'banane", "L2", "USELESS", false, false, false));
        modules.add(new Module("Haloum", "L2", "USELESS", false, false, false));

        return modules;
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
        columnIntitule.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        columnNiveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        columnSpecialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        columnCours.setCellValueFactory(new PropertyValueFactory<>("typeCours"));
        columnTD.setCellValueFactory(new PropertyValueFactory<>("typeTD"));
        columnTP.setCellValueFactory(new PropertyValueFactory<>("typeTP"));

        tableView.setItems(getAllModules());
        tableView.getColumns().addAll(columnIntitule, columnNiveau, columnSpecialite, columnCours, columnTD, columnTP);

        searchModules();
        comboNiveau.getItems().addAll(
                "L1",
                "L2",
                "L3",
                "M1",
                "M2"
        );

        comboSpecialite.getItems().addAll(
                "GL",
                "ASR",
                "MI",
                "RESYD",
                "IA"
        );

    }

}
