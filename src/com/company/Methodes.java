package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.company.Dao.*;
import static com.company.Tools.*;


public abstract class Methodes {
    public static void setTaux(){
        d("L'actuel taux est de "+taux*100 +"%");
        d("Voulez vous poursuivre?");
        if(!isvalide()){
            return;
        }else {
            d("Veuillez entrer le nouveau taux");
            taux=ed()/100;
            d("Nouveau taux: "+taux*100);
        }
    }

    public static void operation() {
        niveaux.add(niv1);
        niveaux.add(niv2);
        niveaux.add(niv3);
        niveaux.add(niv2a);
        niveaux.add(niv3a);
    }

    //student
    public static void createEtudiant( ) {
        d("Entrer le nom de l'etudiant: ");
        String nom = e();
        d("Entrer le prenom de l'etudiant: ");
        String prenom = e();
        String sexe = sexe();
        d("Entrer l'addresse de l'etudiant: ");
        String addresse = e();
        d("Entrer le telephone de l'etudiant: ");
        String telephone = e();
        String piecee = carte();
        d("Entrer le numero de la piece concernee");
        Long piecenum;
        try {
            piecenum = Long.valueOf(e());
        } catch (NumberFormatException e) {
            d("Erreur!!! Format incorrect!!!\n");
            return;
        }
        d("Entre le niveau de l'etudiant: ");
        String niveau = niv();

        Student student = new Student(prenom, nom, sexe, telephone, addresse, piecee, piecenum);
        for (Niveau x : niveaux) {
            if (x.getNiveau().equals(niveau)) {
                student.setNiveau(x);
            }
        }
        students.add(student);

    }

    public static void modifyStudent(){
        System.out.println("Veuillez entrer l'id de l'etudiant que vous voulez modifier");
        String id= e();
        boolean check=false;
        Student student = null;
        for(Student x:students) {
            if(x.getId_student().equals(id)) {
                check =true;student=x;
            }
        }if(!check)
        {System.out.println("L'etudiant n'a pas ete retrouvee");return;
        }
        boolean continu=true;
        while(continu) {
            System.out.println("Presser:\n\t 1- pour modifier le nom");
            System.out.println("Presser:\n\t 2- pour modifier le prenom");
            System.out.println("Presser:\n\t 3- pour modifier l'adresse");
            System.out.println("Presser:\n\t 4- pour modifier le numero de tlf");
            System.out.println("Presser:\n\t 5- pour modifier le niveeau");
            System.out.println("Presser:\n\t 6- pour retouner au menu Principale");


            switch(ei()) {
                case 1:
                    System.out.println("Le nom actuel de l'etudiant est:" +" "+ student.getL_name());
                    System.out.println("Entrer son nouveau Nom");
                    for(Student x:students) {
                        if(x.getId_student().equals(id)) {
                            x.setL_name(Tools.e());}
                    }
                    break;
                case 2:
                    System.out.println("Le nom actuel de l'etudiant est:" + student.getF_name() );
                    System.out.println("Entrer son nouveau Prenom");
                    for(Student x:students) {
                        if(x.getId_student().equals(id)) {
                            x.setF_name(Tools.e());}
                    }
                    break;
                case 3:
                    System.out.println("L'adresse  actuelle de l'etudiant est:" + student.getAdresse());
                    System.out.println("Entrer sa nouvelle adresse");
                    for(Student x:students) {
                        if(x.getId_student().equals(id)) {
                            x.setAdresse(Tools.e());}
                    }

                    break;

                case 4:
                    System.out.println("Le numero de l'etudiant est:" + student.getPhone());
                    System.out.println("Entrer son nouveau numero");
                    for(Student x:students) {
                        if(x.getId_student().equals(id)) {
                            x.setPhone(Tools.e());}
                    }
                    break;
                case 5:
                    System.out.println("Le niveau de l'etudiant est:" + student.getNiveau().getNiveau());
                    System.out.println("Entrer le niveau");
                    String niveaus=niv();
                    for(Student x:students) {
                        if(x.getId_student().equals(id)) {
                            for(Niveau xe:niveaux) {
                                if(xe.getNiveau().equals(niveaus)) {
                                    x.setNiveau(xe);
                                }
                            }

                        }
                    }
                case 6:
                    continu=false;
            }
        }


    }

    public static void deleteStudent() {

        System.out.println("Veuillez entrer l'id de l'etudiant a suprimer");
        String id= e();
        boolean check=false;
        for(Student s:students) {
            if(s.getId_student().equals(id)) {
                students.remove(s);
                d("Etudiant supprimer!!!");
                check=true;break;
            }
        }if(!check)d("on a pas pu trouver l'etudiant");
    }

    public static void searchStudent() {

        System.out.println("Veuillez entrer l'id de l'etudiant a rechercher");
        String id= e();
        boolean check=false;
        for(Student s:students) {
            if(s.getId_student().equals(id)) {
                d(s.toString());
                check=true;
            }
        }if(!check)d("On a pas pu trouver l'etudiant");
    }

    public static void showStudents() {int i=0;
        for(Student a:students) {
           d("Etudiant: "+ a.getF_name()+" "+a.getL_name()+" d'ID: "+a.getId_student());
            i++;

        }
        if(i==0)d("Aucun etudiant enregistree");
    }

    public static void assignerStudent(){
        System.out.println("Veuillez entrer l'id de l'etudiant a que vous souhaiter assigner comme leader");
        String id= e();
        boolean check=false;
        for(Student s:students) {
            if(s.getId_student().equals(id)) {
                d("Veuillez le niveau en question ");
                String niv=niv();
                for (Niveau x:niveaux){
                    if(niv.equals(x.getNiveau())){
                        x.setLeaddr(s);
                        d("Etudiant assigner!!!");
                    }
                }
                check=true;
            }
        }if(!check)d("on a pas pu trouver l'etudiant");

    }

//pret
    public static void addPret() {
        Prets pret=new Prets();
        System.out.println("Entrer votre Id");
        String idl=e();String niveau="";
        boolean check=false;
        for(Niveau x:niveaux){
           try {
               if (idl.equals(x.getLeaddr().getId_student())) {
                   check = true;
                   niveau=x.getNiveau();
                   pret.setNiveau(x);
                   if(x.isencour){
                       d("Vous ne pouver pas placer ce pret puisqu'il y a deja un pret en cours dans votre niveau");
                    return;
                   }
                   d("L'etudiant est le representant du niveau "+niveau);br();
                   break;
               }
           }catch(Exception e){}       }if(!check){
            d("Erreur!!! L'etudiant n'a pas ete trouvee ou n'est pas un representant.");return;
        }


        d("Combien d'etudiants veulent preter dans votre niveau?");
        int yy=ei();int ii=0;
        for(Student x:students){
            try {
            if (x.getNiveau().getNiveau().equals(niveau)) ii++;
            }catch (Exception e){}
        }
        if(ii<yy){
            d("Vous ne pouvez pas placer ce pret !!!\n" +
                    "Vous avez entrer "+yy+" etudiants alors que le nombres d'etudiants inscrit a ce niveau est de "+ii+" donc inferieur a ce nombre.");
            return;
        }

        String ide="";
        List<String> noms= new ArrayList<String>();
        for(int i=1;i<=yy;i++){

            d("Pour le "+i+" etudiant: ");
            d("Entrer l'id: ");
            String id=e();check=false;

            if(noms.contains(id)){
                d("Confusion!!!\nErreur!!!Etudiant double!");
                return;
            }noms.add(id);
            d("Entree le montant"); double montant=ed();
            for(Student x: students){
                if(id.equals(x.getId_student())){
                   try{ if(!x.getNiveau().getLeaddr().getId_student().equals(idl)){
                        d("Cet etudiant n'appartient pas a ce niveau!");
                        return;
                    }
                   }catch (Exception e){
                       d("Cet etudiant n'appartient pas a ce niveau!");
                       return;
                   }
                    check=true;
                    Pretspersonnels pretspersonnels=new Pretspersonnels();
                    pretspersonnels.setStudent(x);
                    pretspersonnels.setMontant(montant);
                    pret.getPretspersonnels().add(pretspersonnels);
                }
            }if(!check){
                d("L'etudiant n'a pas ete retrouvee!!!");return;
            }
        }
        if(pret.getMontantbrut()<montantPretMin){
            d("Vous ne pouver pas placer ce pret car le montant minimum pour placer un pret est de " +
                    montantPretMin+" gourdes,selon les politique de la direction,\n alors que votre montant de pret totalise "+pret.getMontantbrut()
                +" gourdes.");
            return;
        }
        pret.setDate(LocalDate.now());
        pret.setVersement(pret.getMontant()/4);
        pret.setBalance(pret.getMontant()/4);
        d("Le montant emprunter est de " + pret.getMontantbrut() +" gourdes." +
                "\nAvec un taux d'interet de "+taux*100+"% vous devrez payer un somme de "+pret.getMontant());
        prets.add(pret);
        for(Niveau x:niveaux){
            try {
                if (idl.equals(x.getLeaddr().getId_student())) {
                    x.isencour=true;
                    break;
                }
            }catch(Exception e){}
        }
    }

    public static void showPrets(){
        for(Prets x:prets){
            d("Un pret de "+x.getVersement()*4+" gourdes, d'Id " +x.getId_prets()+
                    " Place le "+x.getDate().toString()+
                    "\npar le niveau: "+x.getNiveau().getNiveau()+"\n\n");
        }
    }

    public static void displayPret(){
        System.out.println("Veuillez entrer l'id du pret voulu");
        String id= e();
        boolean check=false;
        for(Prets s:prets) {
            if(s.getId_prets().equals(id)) {
                d(s.toString());
                check=true;
            }
        }if(!check)d("on a pas pu trouver le pret en question");
    }

    public static void newRembousement(){
        Remboursement remboursement=new Remboursement();
        System.out.println("Entrer votre Id");
        String idl=e();
        boolean check=false;
        for(Niveau x:niveaux){
            try{
                if(idl.equals(x.getLeaddr().getId_student())){
                check=true;
                remboursement.setNiveau(x);

                    }
            }
            catch (Exception e){

            }
        }if(!check){
            d("Erreur!!! L'etudiant n'est pas un representant.");return;
        }
        d("Entrer l'id de votre Pret");
        String idP=e();
        check=false;
        for(Prets x:prets){
            if(idP.equals(x.getId_prets())){
                check=true; remboursement.setIdprets(x);test(48);d(remboursement.getIdprets().getId_prets());
            }
        }if(!check){
            {
                d("Erreur!!! Pret introuvable");
                return;
            }
        }

        d("Combien d'etudiants on participer au remboursement?");
        int k=ei();
        for(int i=1;i<=k;i++){
            d("Pour le "+i+" etudiant: ");
            d("Entrer l'id: ");
            String id=e();check=false;
            d("Entree le montant versee"); double montant=ed();
            for(Student x: students){
                if(id.equals(x.getId_student())){
                    check=true;
                    RemboursementPersonnel remboursementPersonnel =new RemboursementPersonnel();
                    remboursementPersonnel.setEtudient(x);
                    remboursementPersonnel.setMontant(montant);
                    remboursement.getDtailRembos().add(remboursementPersonnel);
                    for (Pretspersonnels pretspersonnel : remboursement.getIdprets().getPretspersonnels()) {
                        if(pretspersonnel.getStudent().equals(x)){
                            pretspersonnel.setMontant(pretspersonnel.getMontant()-montant);
                        }
                    }

                }
            }if(!check){
                d("L'etudiant n'a pas ete retrouvee!!!");return;
            }
        }
        d("Le montant total versee est de " + remboursement.getMontant() +" gourdes");
        double Versement= remboursement.getIdprets().getVersement();
        double Montant= remboursement.getMontant();
        double Balance= remboursement.getIdprets().getBalance();

        if(remboursement.getIdprets().getVers1().equals(LocalDate.of(0,1,1))){
            if(Montant< Balance){
                remboursement.getIdprets().setBalance(Balance- Montant);
                d("Il vous reste une balance de "+remboursement.getIdprets().getBalance()+" gourdes sur votre premier versement");
            }
            else if(Montant== Balance){
                d("Vous avez terminer votrer premier versement");
                remboursement.getIdprets().setBalance(Versement);
                remboursement.getIdprets().setVers1(LocalDate.now());
            }
            else{
                d("Vous avez terminer votrer premier versement");
                remboursement.getIdprets().setVers1(LocalDate.now());
                d(Balance);
                if(Montant<(Versement+Balance))
                {
                    remboursement.getIdprets().setBalance(Balance - Montant+ Versement);
                    d("Il vous reste une balance de "+ remboursement.getIdprets().getBalance()+" sur votre deuxieme versement");
                }
                else if(Montant<((Versement*2)+Balance))
                {
                    remboursement.getIdprets().setBalance(Balance - Montant+ (Versement*2));
                    d("Vous avez terminer votre deuxieme versement");
                    remboursement.getIdprets().setVers1(LocalDate.now());
                    remboursement.getIdprets().setVers2(LocalDate.now());
                    d("Il vous reste une balance de "+ remboursement.getIdprets().getBalance()+" sur votre troisieme versement");
                }
                else if (Montant<((Versement*3)+Balance))
                {
                    remboursement.getIdprets().setBalance(Balance - Montant+ (Versement*3));
                    d("Vous avez terminer votre troisieme versement");
                    remboursement.getIdprets().setVers1(LocalDate.now());
                    remboursement.getIdprets().setVers2(LocalDate.now());
                    remboursement.getIdprets().setVers3(LocalDate.now());
                    d("Il vous reste une balance de "+ remboursement.getIdprets().getBalance()+" sur votre derniere versement");
                }

                else if (Montant==((Versement*3)+Balance))
                {
                    remboursement.getIdprets().setBalance(0);
                    d("Vous avez payer l'integralite de votre pret.");
                    remboursement.getIdprets().setVers1(LocalDate.now());
                    remboursement.getIdprets().setVers2(LocalDate.now());
                    remboursement.getIdprets().setVers3(LocalDate.now());
                    remboursement.getIdprets().setVers4(LocalDate.now());
                    for(Niveau x:niveaux){
                        try {
                            if (idl.equals(x.getLeaddr().getId_student())) {
                                x.isencour=false;
                                break;
                            }
                        }catch(Exception e){}
                    }

                }
                else if (Montant>((Versement*3)+Balance))
                {
                    remboursement.getIdprets().setBalance(0);
                    d("Vous avez payer l'integralite de votre pret.");
                    remboursement.getIdprets().setVers2(LocalDate.now());
                    remboursement.getIdprets().setVers3(LocalDate.now());
                    remboursement.getIdprets().setVers4(LocalDate.now());
                    for(Niveau x:niveaux){
                        try {
                            if (idl.equals(x.getLeaddr().getId_student())) {
                                x.isencour=false;
                                break;
                            }
                        }catch(Exception e){}
                    }
                    d("Vous avez "+ (Montant-(Versement*3)
                            -Balance)+" de monnaie restant");
                }

            }
        }
        else if(remboursement.getIdprets().getVers2().equals(LocalDate.of(0,1,1))){
            if(Montant< Balance){
                remboursement.getIdprets().setBalance(Balance- Montant);
                d("Il vous reste une balance de "+remboursement.getIdprets().getBalance()+" gourdes sur votre second versement");
            }
            else if(Montant== Balance){
                d("Vous avez terminer votrer second versement");
                remboursement.getIdprets().setBalance(Versement);
                remboursement.getIdprets().setVers2(LocalDate.now());
            }
            else{
                d("Vous avez terminer votrer second versement");
                remboursement.getIdprets().setVers2(LocalDate.now());
                if(Montant<(Versement+Balance))
                {
                    remboursement.getIdprets().setBalance(Balance - Montant+ Versement);
                    d("Il vous reste une balance de "+ remboursement.getIdprets().getBalance()+" sur votre troisieme versement");
                }
                else if(Montant<((Versement*2)+Balance))
                {
                    remboursement.getIdprets().setBalance(Balance - Montant+ (Versement*2));
                    d("Vous avez terminer votre troisieme versement");
                    remboursement.getIdprets().setVers3(LocalDate.now());
                    d("Il vous reste une balance de "+ remboursement.getIdprets().getBalance()+" sur votre troisieme versement");
                }
                else if (Montant==((Versement*2)+Balance))
                {
                    remboursement.getIdprets().setBalance(0);
                    d("Vous avez payer l'integralite de votre pret.");
                    remboursement.getIdprets().setVers3(LocalDate.now());
                    remboursement.getIdprets().setVers4(LocalDate.now());
                    for(Niveau x:niveaux){
                        try {
                            if (idl.equals(x.getLeaddr().getId_student())) {
                                x.isencour=false;
                                break;
                            }
                        }catch(Exception e){}
                    }
                }
                else if (Montant>((Versement*2)+Balance))
                {
                    remboursement.getIdprets().setBalance(0);
                    d("Vous avez payer l'integralite de votre pret.");
                    remboursement.getIdprets().setVers2(LocalDate.now());
                    remboursement.getIdprets().setVers3(LocalDate.now());
                    remboursement.getIdprets().setVers4(LocalDate.now());
                    for(Niveau x:niveaux){
                        try {
                            if (idl.equals(x.getLeaddr().getId_student())) {
                                x.isencour=false;
                                break;
                            }
                        }catch(Exception e){}
                    }
                    d("Vous avez "+ (Montant-(Versement*2)
                            -Balance)+" de monnaie restant");
                }

            }
        }
        else if(remboursement.getIdprets().getVers3().equals(LocalDate.of(0,1,1))){
            if(Montant< Balance){
                remboursement.getIdprets().setBalance(Balance- Montant);
                d("Il vous reste une balance de "+remboursement.getIdprets().getBalance()+" gourdes sur votre troisieme versement");
            }
            else if(Montant== Balance){
                d("Vous avez terminer votrer troisieme versement");
                remboursement.getIdprets().setBalance(Versement);
                remboursement.getIdprets().setVers3(LocalDate.now());
            }
            else{
                d("Vous avez terminer votre troisieme versement");
                remboursement.getIdprets().setVers3(LocalDate.now());
                if(Montant<(Versement+Balance))
                {
                    remboursement.getIdprets().setBalance(Balance - Montant+ Versement);
                    d("Il vous reste une balance de "+ remboursement.getIdprets().getBalance()+" sur votre dernire versement");
                }
                else if (Montant==(Versement+Balance))
                {
                    remboursement.getIdprets().setBalance(0);
                    d("Vous avez payer l'integralite de votre pret.");
                    remboursement.getIdprets().setVers4(LocalDate.now());
                    for(Niveau x:niveaux){
                        try {
                            if (idl.equals(x.getLeaddr().getId_student())) {
                                x.isencour=false;
                                break;
                            }
                        }catch(Exception e){}
                    }
                }
                else if (Montant>(Versement+Balance))
                {
                    remboursement.getIdprets().setBalance(0);
                    d("Vous avez payer l'integralite de votre pret.");
                    remboursement.getIdprets().setVers4(LocalDate.now());
                    for(Niveau x:niveaux){
                        try {
                            if (idl.equals(x.getLeaddr().getId_student())) {
                                x.isencour=false;
                                break;
                            }
                        }catch(Exception e){}
                    }
                    d("Vous avez "+ (Montant-(Versement)
                            -Balance)+" de monnaie restant");
                }

            }
        }
        else if(remboursement.getIdprets().getVers4().equals(LocalDate.of(0,1,1))){
            if(Montant< Balance){
                remboursement.getIdprets().setBalance(Balance- Montant);
                d("Il vous reste une balance de "+remboursement.getIdprets().getBalance()+" gourdes sur votre derniere versement");
            }
            else if(Montant== Balance){
                remboursement.getIdprets().setBalance(0);
                d("Vous avez payer l'integralite de votre pret.");
                remboursement.getIdprets().setVers4(LocalDate.now());
                for(Niveau x:niveaux){
                    try {
                        if (idl.equals(x.getLeaddr().getId_student())) {
                            x.isencour=false;
                            break;
                        }
                    }catch(Exception e){}
                }
            }
            else if (Montant>Balance)
            {
                remboursement.getIdprets().setBalance(0);
                d("Vous avez payer l'integralite de votre pret.");
                remboursement.getIdprets().setVers4(LocalDate.now());
                for(Niveau x:niveaux){
                    try {
                        if (idl.equals(x.getLeaddr().getId_student())) {
                            x.isencour=false;
                            break;
                        }
                    }catch(Exception e){}
                }
                d("Vous avez "+ (Montant
                        -Balance)+" de monnaie restant");
            }
        }
        else if(!remboursement.getIdprets().getVers4().equals(LocalDate.of(0,1,1))){
            d("Cette dette a ete totalement recouvree.");
        }
        remboursement.setDateRenboursement(LocalDate.now());
        remboursements.add(remboursement);
    }

    public static void affRemboursements(){
        for(Remboursement x:remboursements){
            d("Remboursement "+x.getId()+" place le "+x.getDate()+" sur le pret "+ x.getIdprets().getId_prets()+"\n");
        }
    }

    public static void detailRemboursement(){
        System.out.println("Veuillez entrer l'id du remboursement voulu voulu");
        String id= e();
        boolean check=false;
        for(Remboursement s:remboursements) {
            if(s.getId().equals(id)) {
                d(s.toString());
                check=true;
            }
        }if(!check)d("on a pas pu trouver le pret en question");
    }
}
