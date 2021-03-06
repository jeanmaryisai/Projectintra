package com.company;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.company.Dao.*;
public class FileIO {

    public static void Log(Object d){
        System.out.println(d);
    }

    public static void write(File file, int choice, Boolean append){
        if (choice == 1){
            for (Student s : students){
                try {

                    // Convenience class for writing character files
                    FileWriter writer;
                    writer = new FileWriter(file.getAbsoluteFile(), append);

                    // Writes text to a character-output stream
                    BufferedWriter bufferWriter = new BufferedWriter(writer);
                    bufferWriter.write(s.stringgson()+"\n");
                    bufferWriter.close();
                } catch (IOException e) {

                    Log("error load cache from file " + e.toString());
                }
            }
        } else if (choice == 2){
            for (Prets p : prets){
                try {

                    // Convenience class for writing character files
                    FileWriter writer;
                    writer = new FileWriter(file.getAbsoluteFile(), append);

                    // Writes text to a character-output stream
                    BufferedWriter bufferWriter = new BufferedWriter(writer);
                    bufferWriter.write(p.stringgson()+"\n");
                    bufferWriter.close();
                } catch (IOException e) {

                    Log("error load cache from file " + e.toString());
                }
            }
        } else if (choice == 3){
            for (Remboursement r : remboursements){
                try {

                    // Convenience class for writing character files
                    FileWriter writer;
                    writer = new FileWriter(file.getAbsoluteFile(), append);

                    // Writes text to a character-output stream
                    BufferedWriter bufferWriter = new BufferedWriter(writer);
                    bufferWriter.write(r.stringgson()+"\n");
                    bufferWriter.close();
                } catch (IOException e) {

                    Log("error load cache from file " + e.toString());
                }
            }
        }else if (choice == 4){
            for (Remboursement r : remboursements){
                for (RemboursementPersonnel rp:r.getDtailRembos()){

                try {

                    // Convenience class for writing character files
                    FileWriter writer;
                    writer = new FileWriter(file.getAbsoluteFile(), append);

                    // Writes text to a character-output stream
                    BufferedWriter bufferWriter = new BufferedWriter(writer);
                    bufferWriter.write(rp.stringgson()+"\n");
                    bufferWriter.close();
                } catch (IOException e) {

                    Log("error load cache from file " + e.toString());
                }
                }
            }
        }else if (choice == 5){
            for (Niveau niveau : niveaux){
                try {

                    // Convenience class for writing character files
                    FileWriter writer;
                    writer = new FileWriter(file.getAbsoluteFile(), append);

                    // Writes text to a character-output stream
                    BufferedWriter bufferWriter = new BufferedWriter(writer);
                    bufferWriter.write(niveau.stringgson()+"\n");
                    bufferWriter.close();
                } catch (IOException e) {

                    Log("error load cache from file " + e.toString());
                }
            }
        } else if (choice == 6){
            for (Prets p : prets){
                for (Pretspersonnels perso:p.getPretspersonnels()){

                    try {
                        // Convenience class for writing character files
                        FileWriter writer;
                        writer = new FileWriter(file.getAbsoluteFile(), append);

                        // Writes text to a character-output stream
                        BufferedWriter bufferWriter = new BufferedWriter(writer);
                        bufferWriter.write(perso.stringgson()+"\n");
                        bufferWriter.close();
                    } catch (IOException e) {

                        Log("error load cache from file " + e.toString());
                    }
                }
            }
        }
    }
    public static void load(File file ,int choice){

        if (choice == 1) {
            try {
                File fileNiveau = new File("niveau.txt");
                if (file.exists() && fileNiveau.exists()) {
                    Log("exist");
                    students.clear();
                    niveaux.clear();
                    String studentInput, niveauInput;
                    FileInputStream inputStream1 = new FileInputStream(file);
                    FileInputStream inputStream2 = new FileInputStream(fileNiveau);
                    BufferedReader brstudent = new BufferedReader(new InputStreamReader(inputStream1));
                    BufferedReader brniveau = new BufferedReader(new InputStreamReader(inputStream2));

                    while ((niveauInput = brniveau.readLine()) != null) {
                        String[] niveauEl = niveauInput.split(",");
                        Log(brstudent.readLine());
                        while ((studentInput = brstudent.readLine()) != null) {
                            String[] studentEl = studentInput.split(",");
                            if (niveauEl[1].equals(studentEl[0])) {
                                Log("fret");
                                Student student = new Student();
                                Niveau nivo = new Niveau();
                                nivo.setNiveau(niveauEl[0]);
                                nivo.setLeaddr(student);
                                nivo.setIsencour(Boolean.parseBoolean(niveauEl[2]));
                                student.setId_student(studentEl[0]);
                                student.setF_name(studentEl[1]);
                                student.setL_name(studentEl[2]);
                                student.setNiveau(nivo);
                                student.setGendr(studentEl[4]);
                                student.setPhone(studentEl[5]);
                                student.setAdresse(studentEl[6]);
                                student.setPiece(studentEl[7]);
                                student.setPiece_numbr(Long.parseLong(studentEl[8]));
                                niveaux.add(nivo);
                                students.add(student);
                            }
                        }
                    }
                    Log(brstudent.readLine());
                    while ((studentInput = brstudent.readLine()) != null) {
                        String[] studentEl = studentInput.split(",");
                        Student student = new Student();
                        Log(studentEl[1]);
                        Log("exist2");
                        for (Student stud : students) {
                            if (stud.getId_student().equals(studentEl[0])) {
                                continue;
                            } else {
                                for (Niveau niv : niveaux) {
                                    if (niv.getNiveau().equals(studentEl[3])) {
                                        student.setNiveau(niv);
                                    }
                                }
                                Log("exist3");
                                student.setId_student(studentEl[0]);
                                student.setF_name(studentEl[1]);
                                student.setL_name(studentEl[2]);
                                student.setGendr(studentEl[4]);
                                student.setPhone(studentEl[5]);
                                student.setAdresse(studentEl[6]);
                                student.setPiece(studentEl[7]);
                                student.setPiece_numbr(Long.parseLong(studentEl[8]));
                                students.add(student);
                            }
                        }
                    }
                } else{
                    Log("You have to create student first");
                }
                } catch (IOException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (choice == 2){
            Set<Pretspersonnels> pretspersonnels= new HashSet<Pretspersonnels>();
            try {
                File fileperso = new File("pretperso.txt");
                if (file.exists() && fileperso.exists()) {
                    prets.clear();
                    String pretOut, pretpersoOut;
                    FileInputStream inputStream1 = new FileInputStream(file);
                    FileInputStream inputStream3 = new FileInputStream(fileperso);
                    BufferedReader brpret = new BufferedReader(new InputStreamReader(inputStream1));
                    BufferedReader brperso = new BufferedReader(new InputStreamReader(inputStream3));


                    while ((pretOut = brpret.readLine()) != null) {
                        String[] pretEl = pretOut.split(",");

                        Prets pret = new Prets();
                        Niveau nivo = new Niveau();
                        pret.setId_prets(pretEl[0]);
                        for (Niveau niv : niveaux) {
                            if (niv.getNiveau().equals(pretEl[1])) {
                                pret.setNiveau(niv);
                            }
                        }
                        pret.setVersement(Double.parseDouble(pretEl[2]));
                        pret.setVers1(LocalDate.parse(pretEl[3]));
                        pret.setVers2(LocalDate.parse(pretEl[4]));
                        pret.setVers3(LocalDate.parse(pretEl[5]));
                        pret.setVers4(LocalDate.parse(pretEl[6]));
                        pret.setDate(LocalDate.parse(pretEl[7]));
                        pret.setIsencoour(Boolean.parseBoolean(pretEl[8]));
                        pret.setBalance(Double.parseDouble(pretEl[9]));

                        while ((pretpersoOut = brperso.readLine()) != null) {
                            String[] persoEl = pretpersoOut.split(",");
                            if (persoEl[3].equals(pretEl[0])) {
                                Pretspersonnels pretperso = new Pretspersonnels();
                                pretperso.setId(persoEl[0]);
                                pretperso.setPret(pret);
                                pretperso.setMontant(Double.parseDouble(persoEl[2]));
                                for (Student stud : students) {
                                    if (persoEl[1].equals(stud.getId_student())) {
                                        pretperso.setStudent(stud);
                                    }
                                }
                                pretspersonnels.add(pretperso);
                                pret.setPerso(pretspersonnels);
                            }

                        }
                        prets.add(pret);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (choice == 3){
            Set<RemboursementPersonnel> remboursementPersonnels = new HashSet<RemboursementPersonnel>();
            try {

                File fileRemPerso = new File(" RemPerso.txt");
                if (file.exists() && fileRemPerso.exists()) {
                    remboursements.clear();
                    String remOut, remPersoOut;
                    FileInputStream inputStream1 = new FileInputStream(file);
                    FileInputStream inputStream3 = new FileInputStream(fileRemPerso);
                    BufferedReader brRem = new BufferedReader(new InputStreamReader(inputStream1));
                    BufferedReader brRemPerso = new BufferedReader(new InputStreamReader(inputStream3));


                    while ((remOut = brRem.readLine()) != null) {
                        String[] remEl = remOut.split(",");

                        Remboursement rem = new Remboursement();
                        rem.setId(remEl[0]);
                        for (Niveau niv : niveaux) {
                            if (niv.getNiveau().equals(remEl[1])) {
                                rem.setNiveau(niv);
                            }
                        }
                        for (Prets pret : prets) {
                            if (pret.getId_prets().equals(remEl[2])) {
                                rem.setPrets(pret);
                                rem.setMontantVerse(pret);
                            }
                        }
                        for (Student student : students) {
                            if (student.getId_student().equals(remEl[3])) {
                                rem.setStudent(student);
                            }
                        }
                        rem.setDateRenboursement(LocalDate.parse(remEl[4]));

                        while ((remPersoOut = brRemPerso.readLine()) != null) {
                            String[] persoEl = remPersoOut.split(",");
                            if (persoEl[4].equals(remEl[0])) {
                                RemboursementPersonnel remperso = new RemboursementPersonnel();
                                remperso.setId(persoEl[0]);
                                for (Student stud : students) {
                                    if (persoEl[2].equals(stud.getId_student())) {
                                        remperso.setEtudient(stud);
                                    }
                                }
                                for (Prets pret : prets) {
                                    if (persoEl[1].equals(pret.getId_prets())) {
                                        remperso.setPrets(pret);
                                    }
                                }
                                remperso.setRemboursement(rem);
                                remboursementPersonnels.add(remperso);
                            }

                        }
                        rem.setListDtail(remboursementPersonnels);
                        remboursements.add(rem);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
