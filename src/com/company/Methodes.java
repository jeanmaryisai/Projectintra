package com.company;

import com.google.gson.Gson;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static com.company.Dao.*;
import static com.company.Tools.*;
import static com.company.FileIO.*;


public abstract class Methodes {
    //other stuff
    public static void setTaux(){
        d("L'actuel taux d'interet est de "+taux*100 +"%");
        d("Voulez vous poursuivre?");
        if(!isvalide()){
            return;
        }else {
            d("Veuillez entrer le nouveau taux\nnb:entree le taux en pourcentage(un chiffre), 100,50,10");

            taux=ed();
            d("Nouveau taux: "+taux*100+"%");
        }
    }

    public static void setMontantmin(){
        d("L'actuel montant minimum est de "+montantPretMin);
        d("Voulez vous poursuivre?");
        if(!isvalide()){
            return;
        }else {
            d("Veuillez entrer le nouveau montant ");

            montantPretMin=ed();
            d("Nouveau taux: "+montantPretMin+" gourdes");
        }
    }

    public static void operation() {
        niveaux.add(niv1);
        niveaux.add(niv2);
        niveaux.add(niv3);
        niveaux.add(niv2a);

    }


    //student
    public static void createEtudiant( ) {

        // add file

        File file = new File(filename("student"));
        File file2 = new File(filename("niveau"));
//        FileIO.ReadFromFile(file,1);
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
        for(Student st:students){
            if(st.getPiece_numbr()==piecenum && st.getPiece().equals(piecee)){
                d("Le numero de piece est deja pris!!!");
                return;
            }
        }
        d("Entre le niveau de l'etudiant: ");
        String niveau = niv();
        Student student = new Student(prenom, nom, sexe, telephone, addresse, piecee, piecenum);

        Log(niveaux.toString());
        for (Niveau x : niveaux) {
            if (x.getNiveau().equals(niveau)) {
                student.setNiveau(x);
                students.add(student);
            }
        }
        FileIO.write(file,1,false);
        //add file to file and specify the file path with extension
        FileIO.write(file2,5,false);

    }

    public static void modifyStudent(){

        // declaring file to load student file
        File file = new File(filename("student"));
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
                    d("Le nom actuel de l'etudiant est:" +" "+ student.getL_name());
                    System.out.println("Entrer son nouveau Nom");
                    for(Student x:students) {
                        if(x.getId_student().equals(id)) {
                            x.setL_name(e());}
                    }
                    break;
                case 2:
                    System.out.println("Le prenom actuel de l'etudiant est:" + student.getF_name() );
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
                    System.out.println("Le niveau actuel de l'etudiant est:" + student.getNiveau().getNiveau());
                    if(student.getNiveau().getLeaddr().equals(student)){
                        d("Attention!!! l'etudiant en question est un representant de sa classe.\n" +
                                "Toute modification de son niveau entrainera sa demission" +
                                " en tant que representant.(ce qui laissera son ex niveau sans representant)" +
                                "\n veuillez vous rendre dans le secteur 'politique de la direction' " +
                                "pour modifier les representant.");br();
                        d("Etes vous sur de vouloir continuer?");
                        if (!isvalide())return;
                    }
                    System.out.println("Entrer le nouveau niveau");
                    String niveaus=niv();
                    if(niveaus.equals(student.getNiveau().getNiveau())){
                        d("Vous avez entrer le meme niveau!");return;
                    }
                    for(Student x:students) {
                        if(x.getId_student().equals(id)) {
                            if(student.getNiveau().getLeaddr().equals(student)){
                                x.getNiveau().setLeaddr(null);
                            }
                            for(Niveau xe:niveaux) {
                                if(xe.getNiveau().equals(niveaus)) {
                                    x.setNiveau(xe);
                                }
                            }

                        }
                    }d("Operation reussi avec succes!");break;
                case 6:
                    continu=false;

            }
        br();}
        FileIO.write(file,1,false);
    }

    public static void deleteStudent() {
        File file = new File(filename("student"));
        System.out.println("Veuillez entrer l'id de l'etudiant a suprimer");
        String id= e();
        boolean check=false;
        for(Student s:students) {
            if(s.getId_student().equals(id)) {
                for (Niveau n:
                     niveaux) {
                    if(n.getLeaddr().equals(s)){
                        d("L'etudiant en question est le representant de son niveau, en " +
                                "le supprimant le niveau sera sans representant.\nNd:Pour assigner un r" +
                                "epresentant veuillez vous rendre dans la section 'politiques de la direction.'");
                        d("Voulez vous poursuivre?");
                        if(!isvalide())return;
                        n.setLeaddr(null);break;
                    }
                }
                students.remove(s);
                d("Etudiant supprimer!!!");
                check=true;break;
            }
        }if(!check)d("on a pas pu trouver l'etudiant");
        FileIO.write(file,1,false);
    }

    public static void searchStudentId() {

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

    public static void searchStudentName() {

        System.out.println("Veuillez entrer le prenom de l'etudiant a rechercher");
        String nom= e();
        boolean check=false;
        List<Student> etudiants=new ArrayList<Student>();
        for(Student s:students) {
            if(s.getF_name().equals(nom)) {
                etudiants.add(s);
                check=true;
            }
        }if(!check){
            d("On a pas pu trouver l'etudiant");return;
        }
        if(etudiants.size()==1){
            etudiants.get(0).toString();return;
        }
        d("On a trouvee "+etudiants.size()+" etudiants du meme prenom dans la salle");
        int choix=0;
        do {int iterration=1;
            for (Student a : etudiants) {
                d("Pressez "+iterration+" pour afficher:");
                d("\tEtudiant: " + a.getF_name() + " " + a.getL_name() + " d'ID: " + a.getId_student());
            iterration++;}
            choix=ei();
        }while (choix>etudiants.size());
        d(etudiants.get(choix-1).toString());

    }

    public static void searchStudentNII() {

        System.out.println("Veuillez entrer le nif/cin ou NII de l'etudiant a rechercher");
        long id= el();
        boolean check=false;
        for(Student s:students) {
            if(s.getPiece_numbr()==id) {
                d(s.toString());
                check=true;
            }
        }if(!check)d("On a pas pu trouver l'etudiant");
    }

    public static void showStudents() {int i=0;
        System.out.printf("%5s %20s %20s %5s %5s %20s", "Id", "Prenom", "Nom", "Niveau","sexe","CIN/NIF/NII");
        d("\n-----------------------------------------------------------------------------------");
        for(Student a:students) {
           a.print();
            i++;
            d("\n");
        }d("\n");
        if(i==0)d("Aucun etudiant enregistree");
    }

    public static void assignerStudent(){
        System.out.println("Veuillez entrer l'id de l'etudiant a que vous souhaiter assigner comme leader");
        String id= e();
        boolean check=false;
        File file = new File(filename("student"));
        File file1 = new File(filename("niveau"));
        FileIO.load(file,1);
        for(Student s:students) {
            if(s.getId_student().equals(id)) {
                for (Niveau x:niveaux){
                    if(s.getNiveau().equals(x)){try{
                        if(x.getLeaddr().equals(s)){
                            d("L'eleve est deja representant!");return;
                        }
                        d("L'actuel representant du niveau " +x.getNiveau()+" est"+
                                x.getLeaddr().getF_name()+" "+x.getLeaddr().getL_name()+" d'id "+x.getLeaddr().getId_student()
                            +"\nVeuillez confirmer son remplacement par " +
                                s.getF_name()+" "+s.getL_name()+" d'id "+s.getId_student());
                        if(!isvalide()){
                            d("Action interrompu!!!");return;
                        }
                    }catch (Exception e){}
                        x.setLeaddr(s);
                        d("Etudiant assigner comme nouveau representant du niveau "+s.getNiveau().getNiveau());
                        FileIO.write(file1,5,false);
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
        for(Student x:students){d(x.getNiveau().getNiveau());
            try {
            if (x.getNiveau().getNiveau().equals(niveau)){ ii++;}
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
                    pretspersonnels.setPret(pret);
                    pretspersonnels.setStudent(x);
                    pretspersonnels.setMontant((montant)*(1+taux));
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
        File file4 = new File(filename("niveau"));
        FileIO.write(file4,5,false);
        File file = new File(filename("prets"));
        FileIO.write(file,2,true);
        File file2 = new File(filename("pretperso"));
        FileIO.writePP(pret,file2,true);
    }

    public static void showPrets(){
        int i=0;
        System.out.printf("%5s %5s %30s %30s %5s","Id","Niveau","Montant","Montant(restant)","nmbre d'etudiant");

        d("\n------------------------------------------------------------------------------------------------");
        for(Prets a:prets) {
            a.print();
            i++;
            d("\n");
        }d("\n");
        if(i==0)d("Aucun pret enregistree");
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

    //rembousement
    public static void newRembousement(){
        File file1 = new File(filename("student"));
        FileIO.load(file1,1);
        File file3 = new File(filename("Remboursements"));
        FileIO.load(file3,3);
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
            if(idP.equals(x.getId_prets()))
            {if(!x.getNiveau().getLeaddr().getId_student().equals(idl)){
                d("le pret n'est pas dans le niveau du representant!");return;
            }
                check=true; remboursement.setPrets(x);d("Il vous reste "+x.getMontant()+" gourdes au total, a payer sur ce pret.");
                d("Voulez vous poursuivre ?");
                if(!isvalide())return;
            }
        }if(!check){
            {
                d("Erreur!!! Pret introuvable");
                return;
            }
        }

        d("Combien d'etudiants ont, participer au remboursement?");
        int k=ei();List<Student>studentList=new ArrayList<>();
        for(int i=1;i<=k;i++){
            d("Pour le "+i+" etudiant: ");
            d("Entrer l'id: ");
            String id=e();check=false;
             double montant;
            for(Student x: students){
                if(id.equals(x.getId_student())){
                    if(studentList.contains(x)){
                        d("Erreur etudiant double!!!");return;
                    }studentList.add(x);
                    remboursement.setStudent(x);
                    if(!x.getNiveau().getLeaddr().getId_student().equals(idl)){
                        d("Erreur!!! L'etudiant n'appartient pas a ce niveau.");return;
                    }
                    for(Prets p:prets){
                        if(p.getNiveau().getLeaddr().getId_student().equals(idl)){
                            break;
                        }
                    }
                    check=true;

                    RemboursementPersonnel remboursementPersonnel =new RemboursementPersonnel();
                    remboursementPersonnel.setEtudient(x);
                    remboursementPersonnel.setRemboursement(remboursement);
                    for (Pretspersonnels pretspersonnel : remboursement.getPrets().getPretspersonnels()) {

                        if(pretspersonnel.getStudent().getId_student().equals(x.getId_student())){
                            d("L'etudiant reste une somme personnel de "+(pretspersonnel.getMontant())+" " +
                                    "gourdes a payer en tout!");
                            br();
                            d("Entree le montant versee"); montant=ed();
                            if(pretspersonnel.getMontant()<montant){
                                d("Le montant est superieur a la dette personnel contractee!!");
                                d("Action interrompu!!!");return;
                            }
                            remboursementPersonnel.setMontant(montant);
                            remboursement.getListDtail().add(remboursementPersonnel);
                            pretspersonnel.setMontant(pretspersonnel.getMontant()-montant);

                        }
                    }

                }
            }if(!check){
                d("L'etudiant n'a pas ete retrouvee!!!");return;
            }
        }
        d("Le montant total versee est de " + remboursement.getMontant() +" gourdes");
        double Versement= remboursement.getPrets().getVersement();
        double Montant= remboursement.getMontant();
        double Balance= remboursement.getPrets().getBalance();

        if(remboursement.getPrets().getVers1().equals(LocalDate.of(0,1,1))){
            if(Montant< Balance){
                remboursement.getPrets().setBalance(Balance- Montant);
                d("Il vous reste une balance de "+remboursement.getPrets().getBalance()+" gourdes sur votre premier versement");
            }
            else if(Montant== Balance){
                d("Vous avez terminer votrer premier versement");
                remboursement.getPrets().setBalance(Versement);
                remboursement.getPrets().setVers1(LocalDate.now());
            }
            else{
                d("Vous avez terminer votrer premier versement");
                remboursement.getPrets().setVers1(LocalDate.now());
                d(Balance);
                if(Montant<(Versement+Balance))
                {
                    remboursement.getPrets().setBalance(Balance - Montant+ Versement);
                    d("Il vous reste une balance de "+ remboursement.getPrets().getBalance()+" sur votre deuxieme versement");
                }
                else if(Montant<((Versement*2)+Balance))
                {
                    remboursement.getPrets().setBalance(Balance - Montant+ (Versement*2));
                    d("Vous avez terminer votre deuxieme versement");
                    remboursement.getPrets().setVers1(LocalDate.now());
                    remboursement.getPrets().setVers2(LocalDate.now());
                    d("Il vous reste une balance de "+ remboursement.getPrets().getBalance()+" sur votre troisieme versement");
                }
                else if (Montant<((Versement*3)+Balance))
                {
                    remboursement.getPrets().setBalance(Balance - Montant+ (Versement*3));
                    d("Vous avez terminer votre troisieme versement");
                    remboursement.getPrets().setVers1(LocalDate.now());
                    remboursement.getPrets().setVers2(LocalDate.now());
                    remboursement.getPrets().setVers3(LocalDate.now());
                    d("Il vous reste une balance de "+ remboursement.getPrets().getBalance()+" sur votre derniere versement");
                }

                else if (Montant==((Versement*3)+Balance))
                {
                    remboursement.getPrets().setBalance(0);
                    d("Vous avez payer l'integralite de votre pret.");
                    remboursement.getPrets().setVers1(LocalDate.now());
                    remboursement.getPrets().setVers2(LocalDate.now());
                    remboursement.getPrets().setVers3(LocalDate.now());
                    remboursement.getPrets().setVers4(LocalDate.now());
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
                    remboursement.getPrets().setBalance(0);
                    d("Vous avez payer l'integralite de votre pret.");
                    remboursement.getPrets().setVers2(LocalDate.now());
                    remboursement.getPrets().setVers3(LocalDate.now());
                    remboursement.getPrets().setVers4(LocalDate.now());
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
        else if(remboursement.getPrets().getVers2().equals(LocalDate.of(0,1,1))){
            if(Montant< Balance){
                remboursement.getPrets().setBalance(Balance- Montant);
                d("Il vous reste une balance de "+remboursement.getPrets().getBalance()+" gourdes sur votre second versement");
            }
            else if(Montant== Balance){
                d("Vous avez terminer votrer second versement");
                remboursement.getPrets().setBalance(Versement);
                remboursement.getPrets().setVers2(LocalDate.now());
            }
            else{
                d("Vous avez terminer votrer second versement");
                remboursement.getPrets().setVers2(LocalDate.now());
                if(Montant<(Versement+Balance))
                {
                    remboursement.getPrets().setBalance(Balance - Montant+ Versement);
                    d("Il vous reste une balance de "+ remboursement.getPrets().getBalance()+" sur votre troisieme versement");
                }
                else if(Montant<((Versement*2)+Balance))
                {
                    remboursement.getPrets().setBalance(Balance - Montant+ (Versement*2));
                    d("Vous avez terminer votre troisieme versement");
                    remboursement.getPrets().setVers3(LocalDate.now());
                    d("Il vous reste une balance de "+ remboursement.getPrets().getBalance()+" sur votre troisieme versement");
                }
                else if (Montant==((Versement*2)+Balance))
                {
                    remboursement.getPrets().setBalance(0);
                    d("Vous avez payer l'integralite de votre pret.");
                    remboursement.getPrets().setVers3(LocalDate.now());
                    remboursement.getPrets().setVers4(LocalDate.now());
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
                    remboursement.getPrets().setBalance(0);
                    d("Vous avez payer l'integralite de votre pret.");
                    remboursement.getPrets().setVers2(LocalDate.now());
                    remboursement.getPrets().setVers3(LocalDate.now());
                    remboursement.getPrets().setVers4(LocalDate.now());
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
        else if(remboursement.getPrets().getVers3().equals(LocalDate.of(0,1,1))){
            if(Montant< Balance){
                remboursement.getPrets().setBalance(Balance- Montant);
                d("Il vous reste une balance de "+remboursement.getPrets().getBalance()+" gourdes sur votre troisieme versement");
            }
            else if(Montant== Balance){
                d("Vous avez terminer votrer troisieme versement");
                remboursement.getPrets().setBalance(Versement);
                remboursement.getPrets().setVers3(LocalDate.now());
            }
            else{
                d("Vous avez terminer votre troisieme versement");
                remboursement.getPrets().setVers3(LocalDate.now());
                if(Montant<(Versement+Balance))
                {
                    remboursement.getPrets().setBalance(Balance - Montant+ Versement);
                    d("Il vous reste une balance de "+ remboursement.getPrets().getBalance()+" sur votre dernire versement");
                }
                else if (Montant==(Versement+Balance))
                {
                    remboursement.getPrets().setBalance(0);
                    d("Vous avez payer l'integralite de votre pret.");
                    remboursement.getPrets().setVers4(LocalDate.now());
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
                    remboursement.getPrets().setBalance(0);
                    d("Vous avez payer l'integralite de votre pret.");
                    remboursement.getPrets().setVers4(LocalDate.now());
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
        else if(remboursement.getPrets().getVers4().equals(LocalDate.of(0,1,1))){
            if(Montant< Balance){
                remboursement.getPrets().setBalance(Balance- Montant);
                d("Il vous reste une balance de "+remboursement.getPrets().getBalance()+" gourdes sur votre derniere versement");
            }
            else if(Montant== Balance){
                remboursement.getPrets().setBalance(0);
                d("Vous avez payer l'integralite de votre pret.");
                remboursement.getPrets().setVers4(LocalDate.now());
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
                remboursement.getPrets().setBalance(0);
                d("Vous avez payer l'integralite de votre pret.");
                remboursement.getPrets().setVers4(LocalDate.now());
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
        else if(!remboursement.getPrets().getVers4().equals(LocalDate.of(0,1,1))){
            d("Cette dette a ete totalement recouvree.");
        }
        remboursement.setDateRenboursement(LocalDate.now());
        remboursements.add(remboursement);
        System.out.println(remboursements.toString());

        File file5 = new File(filename("prets"));
        File file7 = new File(filename("pretperso"));
        FileIO.write(file5,2,false);
        File file6 = new File(filename("niveau"));
        FileIO.write(file6,5,false);
        FileIO.write(file7,6,false);
        File file = new File(filename("Remboursements"));
        File file2 = new File(filename("remperso"));
        FileIO.write(file,3,true);
        FileIO.write(file2,4,true);

    }

    public static void affRemboursements(){

        for(Remboursement x:remboursements){
            d(x.toString());
            d("Remboursement "+x.getId()+" place le "+x.getDateRenboursement()+" sur le pret "+ x.getPrets().getId_prets()+"\n");
        }
    }

    public static void detailRemboursement(){
        File file2 = new File(filename("Remboursements"));
        FileIO.load(file2,3);
        d("yesy");
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
