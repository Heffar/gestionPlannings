package main.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Models.Local;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocalDao {

    static final String CHAMP_TITRE = "titre";
    static final String CHAMP_CAPACITE = "capacite";
    static final String CHAMP_COURS = "cours";
    static final String CHAMP_TD = "td";
    static final String CHAMP_TP = "tp";
    static final String TABLENAME = "local";
    Connection connection;


    public ObservableList<Local> getAllLocalsDb(){
        ObservableList<Local> locals = FXCollections.observableArrayList();
        String selectAllQuery = "SELECT " + CHAMP_TITRE + ", " + CHAMP_CAPACITE + ", " +
                CHAMP_COURS +", " + CHAMP_TD + ", " + CHAMP_TP +" FROM "+ TABLENAME;
        connection = DatabaseHelper.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectAllQuery);

            while (rs.next()){
                Local local = new Local();
                String titre = rs.getString(CHAMP_TITRE);
                int capacite = rs.getInt(CHAMP_CAPACITE);
                boolean cours = rs.getBoolean(CHAMP_COURS);
                boolean td = rs.getBoolean(CHAMP_TD);
                boolean tp = rs.getBoolean(CHAMP_TP);

                local.setTitre(titre);
                local.setCapacite(capacite);
                local.setTypeCours(cours);
                local.setTypeTP(tp);
                local.setTypeTD(td);

                locals.add(local);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locals;
    }

    public int insertLocalDb(Local local){

        int response = 0;
        connection = DatabaseHelper.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            String insertQuery = "INSERT INTO " +TABLENAME +" (" + CHAMP_TITRE+", " + CHAMP_CAPACITE +", " +
                    "" + CHAMP_COURS +", "+ CHAMP_TD + ", " + CHAMP_TP + ") " +
                    "VALUES('" + local.getTitre() +"', " + local.getCapacite() +"," +
                    " " + local.isTypeCours() + ", " + local.isTypeTD() +", " + local.isTypeTP()+")";
            response = statement.executeUpdate(insertQuery);
            System.out.println("Insertion réussie");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return response;
    }

    public int deleteLocalDb(String titre){

        int response = 0;
        String deleteQuery = "DELETE FROM " + TABLENAME + " WHERE " + CHAMP_TITRE + " = '" + titre +"'";
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

    public int updateLocalDb(String oldLocalTitre, Local newLocal){
        int response = 0;

        String updateQuery = "UPDATE " + TABLENAME +" SET " + CHAMP_TITRE + "='" + newLocal.getTitre() + "', "
                + CHAMP_CAPACITE + "= "+newLocal.getCapacite() + ", " + CHAMP_COURS + "=" + newLocal.isTypeCours()+
                ", " + CHAMP_TD + "=" + newLocal.isTypeTD() + ", " + CHAMP_TP + "=" + newLocal.isTypeTP()
                + " WHERE " + CHAMP_TITRE + "='" +oldLocalTitre+"'";
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
