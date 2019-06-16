package main.database.DAOs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Models.Specialite;
import main.database.DatabaseHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SpecialiteDao {

    static final String CHAMP_ID = "id";
    static final String CHAMP_INTITULE = "intitule";
    static final String CHAMP_CAPACITE = "capacite";
    static final String TABLE_NAME = "specialite";

    Connection connection;

    public ObservableList<Specialite> getAllSpecialite(){
        ObservableList<Specialite> allSpecialite = FXCollections.observableArrayList();

        String getAllSpecialiteQuery = "SELECT * FROM "+TABLE_NAME;

        connection = DatabaseHelper.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getAllSpecialiteQuery);

            while (rs.next()){

                Specialite specialite = new Specialite();
                int id = rs.getInt(CHAMP_ID);
                String intitule = rs.getString(CHAMP_INTITULE);
                int capacite = rs.getInt(CHAMP_CAPACITE);

                specialite.setId(id);
                specialite.setIntitule(intitule);
                specialite.setCapacite(capacite);

                allSpecialite.add(specialite);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return allSpecialite;
    }

    public int addSpecialiteDb(Specialite specialite){

        int response = 0;

        String addQuery = "INSERT INTO "+ TABLE_NAME + "(" + CHAMP_INTITULE +"," + CHAMP_CAPACITE +") VALUES" +
                "('" + specialite.getIntitule() + "'," + specialite.getCapacite() + ");";
        connection = DatabaseHelper.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            response = statement.executeUpdate(addQuery);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return response;
    }

    public int deleteSpecialiteDb(int id){

        int response = 0;

        String deleteQuery = "DELETE FROM " +TABLE_NAME + " WHERE " + CHAMP_ID + "=" + id;
        connection = DatabaseHelper.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            response = statement.executeUpdate(deleteQuery);
            System.out.println("Suppression réussie");

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return response;
    }

    public int updateSpecialiteDb(int oldId, Specialite specialite){
        int response = 0;
        String updateQuery = "UPDATE " + TABLE_NAME +
                " SET " + CHAMP_INTITULE + "='" +specialite.getIntitule()+"', " + CHAMP_CAPACITE + "=" + specialite.getCapacite()+
                " WHERE "+CHAMP_ID +"=" +oldId;

        connection = DatabaseHelper.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            response = statement.executeUpdate(updateQuery);
            System.out.println("Modification réussie");

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return response;
    }
}
