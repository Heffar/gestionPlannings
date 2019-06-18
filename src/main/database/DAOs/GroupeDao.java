package main.database.DAOs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Models.Groupe;
import main.database.DatabaseHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GroupeDao {

    static final String CHAMP_ID = "id";
    static final String CHAMP_INTITULE = "intitule";
    static final String CHAMP_CAPACITE = "capacite";
    static final String CHAMP_ID_SECTION = "id_section";
    static final String TABLE_NAME = "groupe";

    Connection connection;

    public ObservableList<Groupe> getAllGroupes(){
        ObservableList<Groupe> allGroupes = FXCollections.observableArrayList();

        String getAllSpecialiteQuery = "SELECT * FROM "+TABLE_NAME;

        connection = DatabaseHelper.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getAllSpecialiteQuery);

            while (rs.next()){

                Groupe groupe = new Groupe();
                int id = rs.getInt(CHAMP_ID);
                String intitule = rs.getString(CHAMP_INTITULE);
                int capacite = rs.getInt(CHAMP_CAPACITE);
                int idSection = rs.getInt(CHAMP_ID_SECTION);

                groupe.setId(id);
                groupe.setIntitule(intitule);
                groupe.setCapacite(capacite);
                groupe.setIdSection(idSection);

                allGroupes.add(groupe);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return allGroupes;
    }

    public int addGroupeDb(Groupe groupe){

        int response = 0;

        String addQuery = "INSERT INTO "+ TABLE_NAME + "(" + CHAMP_INTITULE +"," + CHAMP_CAPACITE +", " + CHAMP_ID_SECTION+") VALUES" +
                "('" + groupe.getIntitule() + "'," + groupe.getCapacite() + ", " + groupe.getIdSection()+ ");";
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

    public int deleteGroupeDb(int id){

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

    public int updateGroupeDb(int oldId, Groupe groupe){
        int response = 0;
        String updateQuery = "UPDATE " + TABLE_NAME +
                " SET " + CHAMP_INTITULE + "='" +groupe.getIntitule()+"', " + CHAMP_CAPACITE + "=" + groupe.getCapacite()+ "," +
                "" + CHAMP_ID_SECTION + "="+ groupe.getIdSection() +
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

