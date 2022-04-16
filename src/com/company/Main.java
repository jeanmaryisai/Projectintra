package com.company;
import java.io.File;
import java.time.LocalDate;

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
        student.setId_student("s1");
        File file = new File("");
        for (Niveau x: niveaux) {
            if(x.getNiveau().equals("L3")) {
                student.setNiveau(x);
                System.out.println(x);
            }
        }
        students.add(student);
        Student student2 = new Student("prenoc", "nsdom", "sexe", "telephone", "addresse", "nif",  1);

        for (Niveau x: niveaux) {
            if(x.getNiveau().equals("L2")) {
                student2.setNiveau(x);
                System.out.println(x);
            }
        }
        Student student3 = new Student("prenoc", "nsdom", "sexe", "telephone", "addresse", "nif",  1);
        for (Niveau x: niveaux) {
            if(x.getNiveau().equals("L1")) {
                student3.setNiveau(x);
                System.out.println(x);
            }
        }
        Student student4 = new Student("prenoc", "nsdom", "sexe", "telephone", "addresse", "nif",  1);
        for (Niveau x: niveaux) {
            if(x.getNiveau().equals("DUT1")) {
                student4.setNiveau(x);
                System.out.println(x);
            }
        }
        Student student5 = new Student("prenoc", "nsdom", "sexe", "telephone", "addresse", "nif",  1);
        for (Niveau x: niveaux) {
            if(x.getNiveau().equals("DUT1")) {
                student5.setNiveau(x);
                System.out.println(x);
            }
        }
        student5.setId_student("s55");
        students.add(student5);
        for (Niveau x:niveaux){
            if(x.getNiveau().equals("L3")){
                x.setLeaddr(student);test(4);
                System.out.println(x);
            }
        }
        student4.setId_student("s56");
        students.add(student4);
        student3.setId_student("s564");
        students.add(student3);
        student2.setId_student("s2");
        students.add(student2);
        for (Niveau x:niveaux){
            if(x.getNiveau().equals("L3")){
                x.setLeaddr(student);test(4);
                System.out.println(x);
            }
        }

        for (Niveau x:niveaux){
            if(x.getNiveau().equals("L2")){
                x.setLeaddr(student2);test(4);
            }
        }
        for (Niveau x:niveaux){
            if(x.getNiveau().equals("L1")){
                x.setLeaddr(student3);test(4);
            }
        }
        for (Niveau x:niveaux){
            if(x.getNiveau().equals("DUT1")){
                x.setLeaddr(student4);test(4);
            }
        }

        Prets pret=new Prets();
        for(Niveau x:niveaux){
            try {
                if (x.getLeaddr().getId_student().equals("s1")) {
                    pret.setNiveau(x);break;
                }
            }catch(Exception e){d(" Leader non trouve");}       }
        Pretspersonnels pretspersonnels=new Pretspersonnels();
        pretspersonnels.setStudent(student);
        pretspersonnels.setMontant(200*(1+taux));
        pret.getPretspersonnels().add(pretspersonnels);

        Pretspersonnels pretspersonnelss=new Pretspersonnels();
        pretspersonnelss.setStudent(student2);
        pretspersonnelss.setMontant(200*(1+taux));
        pret.getPretspersonnels().add(pretspersonnelss);
        pret.setVersement(pret.getMontant()/4);
        pret.setBalance(pret.getMontant()/4);

        pret.setDate(LocalDate.now());
        pret.setId_prets("P");
        prets.add(pret);





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
