package com.company;
import java.io.File;
import java.time.LocalDate;

import static com.company.Dao.*;
import static com.company.Menus.menuPrincipale;
import static com.company.Methodes.*;
import static com.company.Tools.*;

public class Main {

    public static void main(String[] args) {
        //test
        operation();
        File file=new File(filename("student"));
        File file1=new File(filename("niveau"));
        FileIO.load(file,1);
        FileIO.load(file1,4);

        menuPrincipale();
//        while (stayed) {
//            try {
//                menuPrincipale();
//            } catch (Exception e) {
//                d("Une erreur a ete detectee quelque part!");
//                d("Nous relancons l'application...");
//            }
//        }
    }
}
