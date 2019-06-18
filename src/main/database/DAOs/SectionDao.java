package main.database.DAOs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Models.Section;
import main.database.DatabaseHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SectionDao {

    private static final String TABLE_NAME = "section";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_INTITULE = "intitule";
    private static final String COLUMN_NIVEAU = "niveau";
    private static final String COLUMN_CAPACITE = "capacite";
    private static final String COLUMN_IDSPECIALITE = "id_specialite";

    Connection connection;

    public ObservableList<Section> getAllSections(){
        Section section = new Section();

        int id, idSpecialite, capacite;
        String intitule, niveau;
        ObservableList<Section> allSections = FXCollections.observableArrayList();

        String query = "SELECT * FROM "+TABLE_NAME;

        connection = DatabaseHelper.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                id = rs.getInt(COLUMN_ID);
                intitule = rs.getString(COLUMN_INTITULE);
                niveau = rs.getString(COLUMN_NIVEAU);
                capacite = rs.getInt(COLUMN_CAPACITE);
                idSpecialite = rs.getInt(COLUMN_IDSPECIALITE);

                section.setId(id);
                section.setIntitule(intitule);
                section.setNiveau(niveau);
                section.setCapacite(capacite);
                section.setIdSpecialite(idSpecialite);

                allSections.add(section);

                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allSections;
    }

    public int addSpecialiteDb(Section section){

        int response = 0;

        String addQuery = "INSERT INTO "+TABLE_NAME + "("+COLUMN_INTITULE+", "+COLUMN_CAPACITE+", "+COLUMN_NIVEAU+", "+COLUMN_IDSPECIALITE+") " +
                "VALUES ('"+section.getIntitule()+"', "+section.getCapacite()+", '"+section.getNiveau()+"', "+section.getIdSpecialite()+")";

        connection = DatabaseHelper.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            response = statement.executeUpdate(addQuery);

            System.out.println("Ajout réussi");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return response;
    }

    public int deleteSpecialiteDb(int sectionId){

        int response = 0;

        String deleteQuery = "DELETE FROM " + TABLE_NAME +"" +
                "WHERE id="+sectionId;

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

    public int updateSpecialiteDb(int oldId, Section section){

        int response = 0;

        String updateQuery = "UPDATE " + TABLE_NAME +
                "SET " + COLUMN_INTITULE + "='" + section.getIntitule() + "', " + COLUMN_CAPACITE + "=" + section.getCapacite() + ", " + COLUMN_NIVEAU + "='"+section.getNiveau()
                + "', " + COLUMN_IDSPECIALITE +"=" +section.getIdSpecialite()+
                "WHERE " + COLUMN_ID + "=" + oldId;
        connection = DatabaseHelper.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            response = statement.executeUpdate(updateQuery);

            System.out.println("Modification réussie");
            statement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return response;
    }

    public ObservableList<Section> getSectionsBySpecialite(int idSpecialite){
        Section section = new Section();

        int id, capacite;
        String intitule, niveau;
        ObservableList<Section> allSections = FXCollections.observableArrayList();

        String query = "SELECT * FROM "+ TABLE_NAME +" WHERE id_specialite =" + idSpecialite;

        connection = DatabaseHelper.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                id = rs.getInt(COLUMN_ID);
                intitule = rs.getString(COLUMN_INTITULE);
                niveau = rs.getString(COLUMN_NIVEAU);
                capacite = rs.getInt(COLUMN_CAPACITE);

                section.setId(id);
                section.setIntitule(intitule);
                section.setNiveau(niveau);
                section.setCapacite(capacite);
                section.setIdSpecialite(idSpecialite);

                allSections.add(section);

            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allSections;
    }
}
