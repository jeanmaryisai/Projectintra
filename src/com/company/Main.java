package com.company;
import static com.company.Dao.*;
import static com.company.Menus.menuPrincipale;
import static com.company.Methodes.*;
import static com.company.Tools.d;
import static com.company.Tools.test;

public class Main {

    public static void main(String[] args) {
        //test
        operation();
        Student student = new Student("prenom", "nom", "sexe", "telephone", "addresse", "piecee",  00000);
        for (Niveau x: niveaux) {
            if(x.getNiveau().equals("L3")) {
                student.setNiveau(x);test(3);
            }
        }
        students.add(student);
        Student student2 = new Student("prenoc", "nsdom", "sexe", "telephone", "addresse", "piecee",  001000);
        for (Niveau x: niveaux) {
            if(x.getNiveau().equals("L3")) {
                student.setNiveau(x);test(3);
            }
        }
        students.add(student2);
        for (Niveau x:niveaux){
            if(x.getNiveau().equals("L3")){
                x.setLeaddr(student);test(4);
            }
        }


        menuPrincipale();
    }
}
