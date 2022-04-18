package com.company;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LoggingPermission;

import static com.company.Tools.*;
import static com.company.Dao.*;
import static com.company.Tools.d;

public class FileIO {


    public static void Log(Object d) {
        System.out.println(d);
    }


    public static void write(File file, int choice, Boolean append) {
        if (choice == 1) {
            try {
                FileWriter writer;
                writer = new FileWriter(file.getAbsoluteFile(), append);
                BufferedWriter bufferWriter = new BufferedWriter(writer);
                for (Student s : students) {
                    bufferWriter.write(s.stringgson() + "\n");
                }
                bufferWriter.close();
                writer.close();
            } catch (IOException e) {

                Log("error load cache from file " + e.toString());
            }


        } else if (choice == 2) {
            for (Prets p : prets) {
                try {

                    // Convenience class for writing character files
                    FileWriter writer;
                    writer = new FileWriter(file.getAbsoluteFile(), append);

                    // Writes text to a character-output stream
                    BufferedWriter bufferWriter = new BufferedWriter(writer);
                    bufferWriter.write(p.stringgson() + "\n");
                    bufferWriter.close();
                    writer.close();
                } catch (IOException e) {

                    Log("error load cache from file " + e.toString());
                }
            }
        } else if (choice == 3) {
            for (Remboursement r : remboursements) {
                try {

                    // Convenience class for writing character files
                    FileWriter writer;
                    writer = new FileWriter(file.getAbsoluteFile(), append);

                    // Writes text to a character-output stream
                    BufferedWriter bufferWriter = new BufferedWriter(writer);
                    bufferWriter.write(r.stringgson() + "\n");
                    bufferWriter.close();
                    writer.close();
                } catch (IOException e) {

                    Log("error load cache from file " + e.toString());
                }
            }
        } else if (choice == 4) {
            for (Remboursement r : remboursements) {
                for (RemboursementPersonnel rp : r.getDtailRembos()) {
                    System.out.println(rp.getEtudient());
                    try {

                        // Convenience class for writing character files
                        FileWriter writer;
                        writer = new FileWriter(file.getAbsoluteFile(), append);

                        // Writes text to a character-output stream
                        BufferedWriter bufferWriter = new BufferedWriter(writer);
                        bufferWriter.write(rp.stringgson() + "\n");
                        bufferWriter.close();
                        writer.close();
                    } catch (IOException e) {

                        Log("error load cache from file " + e.toString());
                    }
                }
            }
        } else if (choice == 5) {

                try {

                    // Convenience class for writing character files
                    FileWriter writer;
                    writer = new FileWriter(file.getAbsoluteFile(), append);
                    // Writes text to a character-output stream
                    BufferedWriter bufferWriter = new BufferedWriter(writer);
                    for (Niveau niveau : niveaux) {
                        bufferWriter.write(niveau.stringgson() + "\n");
                    }
                    bufferWriter.close();
                    writer.close();
                } catch (IOException e) {

                    Log("error load cache from file " + e.toString());
                }

        } else if (choice == 6) {
            for (Prets p : prets) {
                for (Pretspersonnels perso : p.getPretspersonnels()) {

                    try {
                        // Convenience class for writing character files
                        FileWriter writer;
                        writer = new FileWriter(file.getAbsoluteFile(), append);

                        // Writes text to a character-output stream
                        BufferedWriter bufferWriter = new BufferedWriter(writer);
                        bufferWriter.write(perso.stringgson() + "\n");
                        bufferWriter.close();
                        writer.close();
                    } catch (IOException e) {

                        Log("error load cache from file " + e.toString());
                    }
                }
            }
        }
    }



    public static void writePP(Prets pret, File file, boolean append) {
        for (Pretspersonnels pp : pret.getPretspersonnels()) {
            try {

                // Convenience class for writing character files
                FileWriter writer;
                writer = new FileWriter(file.getAbsoluteFile(), true);

                // Writes text to a character-output stream
                BufferedWriter bufferWriter = new BufferedWriter(writer);
                bufferWriter.write(pp.stringgson() + "\n");
                bufferWriter.close();
            } catch (IOException e) {

                Log("error load cache from file " + e.toString());
            }
        }
    }

    public static void load(File file, int choice) {

        if (choice == 1) {
            try {

                if (file.exists()) {
                    students.clear();
                    String studentInput, niveauInput;
                    FileInputStream inputStream1 = new FileInputStream(file);
                    BufferedReader brstudent = new BufferedReader(new InputStreamReader(inputStream1));

                    while ((studentInput = brstudent.readLine()) != null) {
                        String[] studentEl = studentInput.split(",");
                        Student student = new Student(studentEl[0],studentEl[1],studentEl[2],studentEl[3],studentEl[4],studentEl[5],studentEl[6],studentEl[7],Long.parseLong(studentEl[8]));
                        students.add(student);
                    }
                    brstudent.close();
                } else {
                    Log("You have to create student first");
                }
            } catch (IOException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (choice == 2) {
            Set<Pretspersonnels> pretspersonnels2 = new HashSet<Pretspersonnels>();
            try {
                File fileperso = new File(filename("pretperso"));
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
                                        Log(stud.getId_student());
                                        pretperso.setStudent(stud);
                                    }
                                }
                                pret.pretspersonnels.add(pretperso);
                            }

                        }
                        prets.add(pret);
                        if (brperso.readLine() == null) {
                            inputStream3.getChannel().position(0);
                            brperso = new BufferedReader(new InputStreamReader(inputStream3));

                        }


                    }
                    brpret.close();
                    brperso.close();
                }

            } catch (IOException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (choice == 3) {
            Set<RemboursementPersonnel> remboursementPersonnels = new HashSet<RemboursementPersonnel>();
            try {

                if (file.exists()) {
                    remboursements.clear();
                    String remOut;
                    FileInputStream inputStream1 = new FileInputStream(file);

                    BufferedReader brRem = new BufferedReader(new InputStreamReader(inputStream1));


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
                            Log(pret.toString());
                            if (pret.getId_prets().equals(remEl[2])) {

                                rem.setPrets(pret);
                            }
                        }
                        for (Student student : students) {
                            if (student.getId_student().equals(remEl[3])) {
                                rem.setStudent(student);
                            }
                        }
                        rem.setDateRenboursement(LocalDate.parse(remEl[4]));
                        remboursements.add(rem);
                    }
                    brRem.close();
                }

            } catch (IOException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (choice == 4){

            if (file.exists()) {
                try{
                    String niveauInput;
                    FileInputStream inputStream1 = new FileInputStream(file);
                    BufferedReader brniveau = new BufferedReader(new InputStreamReader(inputStream1));
                    while ((niveauInput = brniveau.readLine()) != null) {
                        String[] niveauEl = niveauInput.split(",");
                        for(Niveau n: niveaux){
                            if(niveauEl[0].equals(n.getNiveau())){
                                n.setLeaddrs(niveauEl[1]);
                                n.setIsencour(Boolean.parseBoolean(niveauEl[2]));
                            }
                        }
                    }
                    brniveau.close();
                } catch (IOException ex) {
                    Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }
}