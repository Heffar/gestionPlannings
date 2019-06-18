package main.Models;

public class Enseignant {
    private int id;
    private String nom, prenom, grade;

    public Enseignant() {
    }

    public Enseignant(int id, String nom, String prenom, String grade) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
