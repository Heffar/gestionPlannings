package main.Models;

public class Module {

    private String intitule, niveau, specialite;
    private boolean typeCours, typeTD, typeTP;

    public Module() {
    }

    public Module(String intitule, String niveau, String specialite, boolean typeCours, boolean typeTD, boolean typeTP) {
        this.intitule = intitule;
        this.niveau = niveau;
        this.specialite = specialite;
        this.typeCours = typeCours;
        this.typeTD = typeTD;
        this.typeTP = typeTP;
    }


    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public boolean isTypeCours() {
        return typeCours;
    }

    public void setTypeCours(boolean typeCours) {
        this.typeCours = typeCours;
    }

    public boolean isTypeTD() {
        return typeTD;
    }

    public void setTypeTD(boolean typeTD) {
        this.typeTD = typeTD;
    }

    public boolean isTypeTP() {
        return typeTP;
    }

    public void setTypeTP(boolean typeTP) {
        this.typeTP = typeTP;
    }
}
