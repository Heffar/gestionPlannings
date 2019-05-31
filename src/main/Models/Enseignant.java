package main.Models;

import java.util.List;

public class Enseignant {
    private String nom, prenom, grade;
    private List<Module> listEnseignement;

    public Enseignant() {
    }

    public Enseignant(String nom, String prenom, String grade, List<Module> listEnseignement) {
        this.nom = nom;
        this.prenom = prenom;
        this.grade = grade;
        this.listEnseignement = listEnseignement;
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

    public List<Module> getListEnseignement() {
        return listEnseignement;
    }

    public void setListEnseignement(List<Module> listEnseignement) {
        this.listEnseignement = listEnseignement;
    }
}
