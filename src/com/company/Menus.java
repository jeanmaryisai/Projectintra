package com.company;
import static com.company.Tools.*;
import static com.company.Methodes.*;

public  abstract class Menus {
    public static int mainMenu(){int i;
        do {
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
    public static void menuPrincipale(){boolean stay=true;
        do {
            switch (mainMenu()){
                case 1:moduleEtudiant();break;
                case 2:modulePret();break;
                case 3:moduleRemboursement();break;
                case 4:politique();break;
                case 5:d("Merci et a bientot");stay=false;
            }
            if(stay)br();
        }while (stay);
    }

    private static void politique() {
        int choix;
        boolean stay = true;
        do {
            do {
                d("Presser 1 pour modifier le taux\n" +
                        "presser 2 pour modifier le montant minimale pour ouvrir un compte\n" +
                        "presser 3 pour retourner au menu principale"
                );
                choix = ei();
            } while (choix != 1 && choix != 2 && choix != 3);

            switch (choix){
                case 1:test(1);break;
                case 2:test(2);break;
                case 3:stay=false;break;

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
        boolean stay = true;
        do {
            do {
                d("Presser 1 pour creer un etudiant\n" +
                        "presser 2 pour rechercher un client\n" +
                        "presser 3 pour lister tous les client\n" +
                        "presser 4 pour modifier un client\n" +
                        "presser 5 pour supprimmer un etudiant\n" +
                        "presser 6 pour retourner au menu principale"
                );
                choix = ei();
            } while (choix != 1 && choix != 2 && choix != 3 && choix != 4&&choix!=5&&choix!=6);

            switch (choix){
                case 1:createEtudiant();break;
                case 2:searchStudent();break;
                case 3:showStudents();break;
                case 6:stay=false;break;
                case 4:modifyStudent();break;
                case 5:deleteStudent();break;
            }
            if(stay)br();
        } while (stay);
    }

    public static void moduleRemboursement() {
        int choix;
        boolean stay = true;
        do {
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
