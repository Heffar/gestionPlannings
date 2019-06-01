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
import main.database.DAOs.ModuleDao;

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

    ModuleDao moduleDao = new ModuleDao();

    public void addModuleBtnClicked(){
        Module module = new Module();

        module.setIntitule(txtIntitule.getText());
        module.setSpecialite(comboSpecialite.getSelectionModel().getSelectedItem().toString());
        module.setNiveau(comboNiveau.getSelectionModel().getSelectedItem().toString());
        module.setTypeCours(checkCours.isSelected());
        module.setTypeTD(checkTD.isSelected());
        module.setTypeTP(checkTP.isSelected());

        if (moduleDao.insertModuleDb(module) > 0){
            allModules.add(module);
        }
    }

    public void deleteModuleBtnClicked() {
        ObservableList<Module> modulesSelected;
        modulesSelected = tableView.getSelectionModel().getSelectedItems();

        modulesSelected.forEach(module -> {
            if (moduleDao.deleteModuleDb(module.getIntitule()) > 0){
                allModules.remove(module);
            }

        });
    }

    public void updateModuleBtnClicked(){
        int position;
        Module updateModule = new Module();
        position = tableView.getSelectionModel().getSelectedIndex();
        String oldModuleTitre = tableView.getItems().get(position).getIntitule();

        updateModule.setIntitule(txtIntitule.getText());
        updateModule.setNiveau(comboNiveau.getSelectionModel().getSelectedItem().toString());
        updateModule.setSpecialite(comboSpecialite.getSelectionModel().getSelectedItem().toString());
        updateModule.setTypeCours(checkCours.isSelected());
        updateModule.setTypeTD(checkTD.isSelected());
        updateModule.setTypeTP(checkTP.isSelected());

        if (moduleDao.updateModuleDb(oldModuleTitre, updateModule) > 0) {
            allModules.set(position, updateModule);
        }
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

        allModules = moduleDao.getAllModulesDb();
        return allModules;
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

        allModules = getAllModules();
        columnIntitule.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        columnNiveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        columnSpecialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        columnCours.setCellValueFactory(new PropertyValueFactory<>("typeCours"));
        columnTD.setCellValueFactory(new PropertyValueFactory<>("typeTD"));
        columnTP.setCellValueFactory(new PropertyValueFactory<>("typeTP"));

        tableView.setItems(allModules);
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
