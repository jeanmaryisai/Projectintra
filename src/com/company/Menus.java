package com.company;
import java.io.File;

import static com.company.Tools.*;
import static com.company.Methodes.*;
import static com.company.Dao.*;
public  abstract class Menus {
    public static int mainMenu(){int i;
        do {d(Dao.students.size());
            d("Menu principale\n" +
                    "Presser 1 pour le module Etudiants" +
                    "\nPresser 2 pour le module prets" +
                    "\nPresser 3 pour le module remboursement." +
                    "\nPresser 4 pour modifier les politiques de la directions" +
                    "\nPresser 5 pour quitter le programme");
            i=ei();
        }while (i!=1&&i!=2&&i!=3&&i!=4&&i!=5);
        return i;
    }
    public static void menuPrincipale(){
        do {
            switch (mainMenu()){
                case 1:moduleEtudiant();break;
                case 2:modulePret();break;
                case 3:moduleRemboursement();break;
                case 4:politique();break;
                case 5:d("Merci et a bientot");
                    Dao.stayed=false;
            }
            if(Dao.stayed)br();
        }while (Dao.stayed);
    }

    private static void politique() {
        int choix;
        boolean stay = true;
        do {
            do {
                d("Presser 1 pour modifier le taux\n" +
                        "presser  2 pour assigner un leader a un niveau\n" +
                        "presser  3 pour redefinir le montant minimale pour pacer un pret\n" +
                        "presser 4 pour retourner au menu principale"
                );
                choix = ei();
            } while (choix != 1 && choix != 2 && choix != 3&& choix != 4);

            switch (choix){
                case 1:setTaux();break;
                case 2:assignerStudent();break;
                case 4:stay=false;break;
                case 3:setMontantmin();

            }
            if(stay)br();
        } while (stay);
    }

    public static void modulePret() {
        int choix;
        boolean stay = true;
        do {
            do {
                d("Presser 1 pour placer un pret\n" +
                        "presser 2 pour afficher tous les prets\n" +
                        "presser 3 pour afficher un prets en details\n" +
                        "presser 4 pour retourner au menu principale"
                );
                choix = ei();
            } while (choix != 1 && choix != 2 && choix != 3 && choix != 4);
            File file = new File(filename("prets"));
            FileIO.load(file,2);
            switch (choix){
                case 1:addPret();break;
                case 2:showPrets();break;
                case 3:displayPret();break;
                case 4:stay=false;break;
            }
            if(stay)br();
        } while (stay);
    }

    public static void moduleEtudiant() {
        int choix;
        File file = new File(filename("student"));
        FileIO.load(file,1);
        boolean stay = true;
        do {
            do {

                d("Presser 1 pour creer un etudiant\n" +
                        "presser 2 pour rechercher un etudiant\n" +
                        "presser 3 pour lister tous les etudiant\n" +
                        "presser 4 pour modifier un etudiant\n" +
                        "presser 5 pour supprimmer un etudiant\n" +
                        "presser 6 pour retourner au menu principale"
                );
                choix = ei();
            } while (choix != 1 && choix != 2 && choix != 3 && choix != 4&&choix!=5&&choix!=6);

            switch (choix){
                case 1:createEtudiant();break;
                case 2:
                    do{d("Presser 1 pour rechercher un etudiant suivant son CIN/NIF/NII\n" +
                            "presser 2 pour rechercher un etudiant suivant son prnom\n" +
                            "presser 3 pour rechercher un etudiant suivant son id\n" +
                            "presser 4 pour retourner au menu principale"
                    );
                    choix = ei();
            } while (choix != 1 && choix != 2 && choix != 3 && choix != 4);

                    switch (choix) {
                        case 1:searchStudentNII();break;
                        case 2:searchStudentName();break;
                        case 3:searchStudentId();
                    }
                    break;
                case 3:showStudents();break;
                case 6:stay=false;break;
                case 4:modifyStudent();break;
                case 5:deleteStudent();break;
            }
            if(stay)br();

        } while (stay);
        for (Student s: students){
            System.out.println(s);
        }
    }

    public static void moduleRemboursement() {
        int choix;
        boolean stay = true;
        do {
            File file2 = new File(filename("prets"));
            File file3 = new File(filename("pretperso"));
            File file = new File(filename("Remboursements"));
            File file1 = new File(filename("student"));
            FileIO.load(file1,1);
            FileIO.load(file2,2);
            FileIO.load(file,3);


            do {
                d("Presser 1 pour placer un remboursement\n" +
                        "presser 2 pour afficher tous les rembousement\n" +
                        "presser 3 pour afficher un rembousement en details\n" +
                        "presser 4 pour retourner au menu principale"
                );
                choix = ei();
            } while (choix != 1 && choix != 2 && choix != 3 && choix != 4);

            switch (choix){
                case 1:newRembousement();break;
                case 2:affRemboursements();break;
                case 3:detailRemboursement();break;
                case 4:stay=false;break;
            }
            if(stay)br();
        } while (stay);
    }

}
