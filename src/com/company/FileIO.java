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
        students.clear();
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
