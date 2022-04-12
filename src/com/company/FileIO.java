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

    public static void Write(Set<Object> list,File file){
        System.out.println("entry");
        FileWriter fw=null;
        System.out.println(list);
        try{
            fw=new FileWriter(file,true);
            for(Object el:list){
                fw.write(el+"\t");
            }
            fw.write(System.lineSeparator());
            fw.close();
            System.out.println("entry");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("entry");
    }

    public static void Read(File file1){

        try {

            FileOutputStream fileOut = new FileOutputStream(file1);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        Set <Object> set = new HashSet<>();
//        try{
////            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
////            String line = null;
////            Scanner sc=new Scanner(new FileInputStream("test.txt"));
//
//            Scanner sc=new Scanner(file);
//            while(sc.hasNextLine()){
//                System.out.println(sc.nextLine());
//            }
//            System.out.println(set);
//            sc.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return set;
    }
    public static void WriteToFile(String myData, boolean append) {

        File file = new File("etdr.txt");


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

            Log("Company data saved at file location: "  + " Data: " + myData + "\n");
        } catch (IOException e) {

            Log("Hmm.. Got an error while saving Company data to file " + e.toString());
        }
    }
    public static void Log(String d){
        System.out.println(d);
    }

    public static void ReadFromFile(File file1, int choice) {
        Set <Object> set = new HashSet<>();
        Student student = null;
        Prets pret = null;
        Remboursement rem = null;
        Gson gson = new Gson();
        File file = new File("etdr.txt");
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
                    Log("Company Name: " + student.toString());
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
