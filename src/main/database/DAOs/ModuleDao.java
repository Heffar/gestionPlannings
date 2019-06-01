package main.database.DAOs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Models.Module;
import main.database.DatabaseHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModuleDao {

        static final String CHAMP_INTITULE = "intitule";
        static final String CHAMP_NIVEAU = "niveau";
        static final String CHAMP_SPECIALITE = "specialite";
        static final String CHAMP_TD = "td";
        static final String CHAMP_COURS = "cours";
        static final String CHAMP_TP = "tp";
        static final String TABLENAME = "module";
        Connection connection;


        public ObservableList<Module> getAllModulesDb(){
            ObservableList<Module> modules = FXCollections.observableArrayList();
            String selectAllQuery = "SELECT " + CHAMP_INTITULE + ", " + CHAMP_NIVEAU + ", " +
                    CHAMP_SPECIALITE +", "+ CHAMP_COURS + ", "+ CHAMP_TD + ", " + CHAMP_TP +" FROM "+ TABLENAME;
            connection = DatabaseHelper.getInstance().getConnection();

            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(selectAllQuery);

                while (rs.next()){
                    Module module= new Module();
                    String titre = rs.getString(CHAMP_INTITULE);
                    String niveau = rs.getString(CHAMP_NIVEAU);
                    String specialite = rs.getString(CHAMP_SPECIALITE);
                    boolean cours = rs.getBoolean(CHAMP_COURS);
                    boolean td = rs.getBoolean(CHAMP_TD);
                    boolean tp = rs.getBoolean(CHAMP_TP);

                    module.setIntitule(titre);
                    module.setSpecialite(specialite);
                    module.setNiveau(niveau);
                    module.setTypeCours(cours);
                    module.setTypeTP(tp);
                    module.setTypeTD(td);

                    modules.add(module);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return modules;
        }

        public int insertModuleDb(Module module){

            int response = 0;
            connection = DatabaseHelper.getInstance().getConnection();
            try {
                Statement statement = connection.createStatement();
                String insertQuery = "INSERT INTO " +TABLENAME +" (" + CHAMP_INTITULE +", " + CHAMP_NIVEAU +", " +
                        "" + CHAMP_SPECIALITE +", "+ CHAMP_COURS + ", " +CHAMP_TD + ", " + CHAMP_TP + ") " +
                        "VALUES('" + module.getIntitule() +"', '" + module.getNiveau() +"','" + module.getSpecialite()+"',"
                         + module.isTypeCours() + ", " + module.isTypeTD() +", " + module.isTypeTP()+")";
                response = statement.executeUpdate(insertQuery);
                System.out.println("Insertion réussie");
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return response;
        }

        public int deleteModuleDb(String intitule){

            int response = 0;
            String deleteQuery = "DELETE FROM " + TABLENAME + " WHERE " + CHAMP_INTITULE + " = '" + intitule +"'";
            connection = DatabaseHelper.getInstance().getConnection();

            try {
                Statement deleteLocal = connection.createStatement();
                response = deleteLocal.executeUpdate(deleteQuery);
                deleteLocal.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return response;
        }

        public int updateModuleDb(String oldModuleIntitule, Module newModule){
            int response = 0;

            String updateQuery = "UPDATE " + TABLENAME +" SET " + CHAMP_INTITULE + "='" + newModule.getIntitule() + "', "
                    + CHAMP_NIVEAU + "= '"+newModule.getNiveau() + "', " + CHAMP_SPECIALITE + "='" + newModule.getSpecialite()+"',"
                    + "cours=" +newModule.isTypeCours()+ ", " + CHAMP_TD + "=" + newModule.isTypeTD() + ", " + CHAMP_TP + "=" + newModule.isTypeTP()
                    + " WHERE " + CHAMP_INTITULE + "='" +oldModuleIntitule+"'";
            connection = DatabaseHelper.getInstance().getConnection();
            try {
                Statement updateStatement = connection.createStatement();
                response = updateStatement.executeUpdate(updateQuery);

                updateStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return response;
        }
    }
