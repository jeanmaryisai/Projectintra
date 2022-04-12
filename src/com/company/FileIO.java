package com.company;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import static com.company.Dao.*;
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
    public static void Log(String d){
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
}
