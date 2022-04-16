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

import static com.company.Dao.*;
import static com.company.Tools.d;

public class FileIO {

    public static void WriteToFile(String myData,File file, boolean append) {

        Log("enter");
        if (append == false) {
            file.delete();
        }
        // exists(): Tests whether the file or directory denoted by this abstract pathname exists.
        if (!file.exists()) {

            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Exception Occurred: " + e.toString());
            }
        }

        try {

            // Convenience class for writing character files
            FileWriter writer;
            writer = new FileWriter(file.getAbsoluteFile(), append);

            // Writes text to a character-output stream
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(myData.toString()+"\n");
            bufferWriter.close();
        } catch (IOException e) {

            Log("Hmm.. Got an error while saving  data to file " + e.toString());
        }
    }
    public static void Log(Object d){
        System.out.println(d);
    }

    public static void ReadFromFile(File file, int choice) {
        Set <Object> set = new HashSet<>();
        Student student = null;
        Prets pret = null;
        Remboursement rem = null;
        Gson gson = new Gson();

        if (choice == 1){
            if (!file.exists())
                Log("File doesn't exist");
            try {
                FileReader fw=new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fw);
                String line;
                while((line = bufferedReader.readLine()) != null) {
                    StringReader s  =new StringReader(line);
                    JsonReader myReader = new JsonReader(s);
                    myReader.toString();
                    student = gson.fromJson(myReader, Student.class);
                    Log("student: " + student.toString());
                    students.add(student);
                }
            } catch (Exception e) {
                Log("error load cache from file " + e.toString());
            }
        } else if (choice == 2){
            if (!file.exists())
                Log("File doesn't exist");
            try {
                FileReader fw=new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fw);
                String line;
                while((line = bufferedReader.readLine()) != null) {
                    StringReader s  =new StringReader(line);
                    JsonReader myReader = new JsonReader(s);
                    pret = gson.fromJson(myReader, Prets.class);
                    Log("Pret " + pret.toString());
                    prets.add(pret);
                }
            } catch (Exception e) {
                Log("error load cache from file " + e.toString());
            }
        } else if(choice == 3){
            if (!file.exists())
                Log("File doesn't exist");
            try {
                FileReader fw=new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fw);
                String line;
                while((line = bufferedReader.readLine()) != null) {
                    StringReader s  =new StringReader(line);
                    JsonReader myReader = new JsonReader(s);
                    rem = gson.fromJson(myReader, Remboursement.class);
                    Log("Remboursement:" + rem.toString());
                    remboursements.add(rem);
                }
            } catch (Exception e) {
                Log("error load cache from file " + e.toString());
            }
        }

    }
    public static void load1(File file ,int choice){

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
    public static void write(File file, int choice, boolean ignore){
        if (choice == 1){
            for (Student s : students){
                try {

                    // Convenience class for writing character files
                    FileWriter writer;
                    writer = new FileWriter(file.getAbsoluteFile(), true);

                    // Writes text to a character-output stream
                    BufferedWriter bufferWriter = new BufferedWriter(writer);
                    bufferWriter.write(s.stringgson()+"\n");
                    bufferWriter.close();
                } catch (IOException e) {

                    Log("error load cache from file " + e);
                }
            }
        } else if (choice == 2){
            for (Prets p : prets){
                try {

                    // Convenience class for writing character files
                    FileWriter writer;
                    writer = new FileWriter(file.getAbsoluteFile(), true);

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
                    writer = new FileWriter(file.getAbsoluteFile(), true);

                    // Writes text to a character-output stream
                    BufferedWriter bufferWriter = new BufferedWriter(writer);
                    bufferWriter.write(r.stringgson() +"\n");
                    bufferWriter.close();
                } catch (IOException e) {

                    Log("error load cache from file " + e.toString());
                }
            }
        }

    }

    public static void writePP(Prets pret,File file,boolean append){
        for (Pretspersonnels pp : pret.getPretspersonnels()){
            try {

                // Convenience class for writing character files
                FileWriter writer;
                writer = new FileWriter(file.getAbsoluteFile(), true);

                // Writes text to a character-output stream
                BufferedWriter bufferWriter = new BufferedWriter(writer);
                bufferWriter.write(pp.stringgson() +"\n");
                bufferWriter.close();
            } catch (IOException e) {

                Log("error load cache from file " + e.toString());
            }
        }
    }
    public static void load(File file ,int choice){
        StringTokenizer st;
        if(!file.exists())return;
        List<String> l;
        if (choice == 1) {
            try {
                l = Files.readAllLines(file.toPath());
                for (String s : l) {
                    st = new StringTokenizer(s, "\t");
                    students.add(new Student(st.nextToken(typeText.delimiteur), st.nextToken(typeText.delimiteur), st.nextToken(typeText.delimiteur),
                            st.nextToken(typeText.delimiteur), st.nextToken(typeText.delimiteur), st.nextToken(typeText.delimiteur),st.nextToken(typeText.delimiteur),st.nextToken(typeText.delimiteur),
                            Long.parseLong(st.nextToken(typeText.delimiteur))));
                }

                } catch (IOException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (choice == 2){
            try {
                   l = Files.readAllLines(file.toPath());

                   for (String s : l) {
                    st = new StringTokenizer(s, "\t");
                    prets.add(new Prets(st.nextToken(typeText.delimiteur),
                            st.nextToken(typeText.delimiteur),
                            Double.valueOf(st.nextToken(typeText.delimiteur)),
                            LocalDate.parse(st.nextToken(typeText.delimiteur)),
                            LocalDate.parse(st.nextToken(typeText.delimiteur)),
                            LocalDate.parse(st.nextToken(typeText.delimiteur)),
                            LocalDate.parse(st.nextToken(typeText.delimiteur)),
                            LocalDate.parse(st.nextToken(typeText.delimiteur)),
                            Boolean.valueOf(st.nextToken(typeText.delimiteur)),
                            Double.valueOf(st.nextToken(typeText.delimiteur))));
                }

            } catch (Exception ex) {
                d(ex);
            }
        } else if (choice == 3){
            try {
                l = Files.readAllLines(file.toPath());
                for (String s : l) {
                    st = new StringTokenizer(s, "\t");
                    remboursements.add(new Remboursement());
                }

            } catch (IOException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
