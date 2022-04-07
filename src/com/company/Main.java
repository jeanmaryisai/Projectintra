package com.company;
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
        student2.setId_student("s2");
        students.add(student2);
        for (Niveau x:niveaux){
            if(x.getNiveau().equals("L3")){
                x.setLeaddr(student);test(4);
            }
        }

        Prets pret=new Prets();
        for(Niveau x:niveaux){
            try {
                if (x.getLeaddr().getId_student().equals("Ecr-100793")) {
                    pret.setNiveau(x);break;
                }
            }catch(Exception e){d(" Leader non trouve");}       }
        Pretspersonnels pretspersonnels=new Pretspersonnels();
        pretspersonnels.setStudent(student);
        pretspersonnels.setMontant(200);
        pret.getPretspersonnels().add(pretspersonnels);

        Pretspersonnels pretspersonnelss=new Pretspersonnels();
        pretspersonnelss.setStudent(student2);
        pretspersonnelss.setMontant(200);
        pret.getPretspersonnels().add(pretspersonnelss);
        pret.setVersement(pret.getMontant()/4);
        pret.setBalance(pret.getMontant()/4);

        pret.setDate(LocalDate.now());
        pret.setId_prets("P");
        prets.add(pret);






        while (stayed) {
            try {
                menuPrincipale();
            } catch (Exception e) {
                d("Une erreur a ete detectee quelque part!");
                d("Nous relancons l'application...");
            }
        }
    }
}
