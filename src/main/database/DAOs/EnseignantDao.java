package main.database.DAOs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Models.Enseignant;
import main.database.DatabaseHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EnseignantDao {

    static final String CHAMP_ID = "id";
    static final String CHAMP_NOM = "nom";
    static final String CHAMP_PRENOM = "prenom";
    static final String CHAMP_GRADE = "grade";
    static final String TABLE_NAME = "enseignant";

    Connection connection;

    public ObservableList<Enseignant> getAllEnseignantsDb(){
        int id;
        String nom, prenom, grade;
        ObservableList<Enseignant> allEnseignants = FXCollections.observableArrayList();

        String getAllEnseignantsQuery = "SELECT * FROM "+TABLE_NAME;

        connection = DatabaseHelper.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getAllEnseignantsQuery);

            while (rs.next()){

                Enseignant enseignant = new Enseignant();
                id = rs.getInt(CHAMP_ID);
                nom = rs.getString(CHAMP_NOM);
                prenom = rs.getString(CHAMP_PRENOM);
                grade = rs.getString(CHAMP_GRADE);

                enseignant.setId(id);
                enseignant.setNom(nom);
                enseignant.setPrenom(prenom);
                enseignant.setGrade(grade);

                allEnseignants.add(enseignant);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return allEnseignants;
    }

    public int addEnseignantDb(Enseignant enseignant){

        int response = 0;

        String addQuery = "INSERT INTO "+ TABLE_NAME + "(" + CHAMP_NOM +"," + CHAMP_PRENOM +"," + CHAMP_GRADE +") VALUES" +
                "('" + enseignant.getNom() + "','" + enseignant.getPrenom() + "','" + enseignant.getGrade()+ "');";
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

    public int deleteEnseignantDb(int id){

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

    public int updateEnseignantDb(int oldId, Enseignant enseignant){
        int response = 0;
        String updateQuery = "UPDATE " + TABLE_NAME +
                " SET " + CHAMP_NOM + "='" +enseignant.getNom()+"', " + CHAMP_PRENOM + "='" + enseignant.getPrenom()+ "'," +
                "" + CHAMP_GRADE + "='"+ enseignant.getGrade() + "'" +
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
