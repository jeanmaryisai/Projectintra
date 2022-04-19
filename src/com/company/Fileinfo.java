package com.company;

public class Fileinfo {
    public String nom;
    public String extention;
    public String delimiteur;

    public String getDelimiteur() {
        return delimiteur;
    }

    public void setDelimiteur(String delimiteur) {
        this.delimiteur = delimiteur;
    }

    public Fileinfo(String nom, String extention, String delimiteur) {
        this.nom = nom;
        this.extention = extention;
        this.delimiteur = delimiteur;
    }
    public Fileinfo(String extention) {
        this.extention = extention;
    }
    public Fileinfo() {

    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }
}
