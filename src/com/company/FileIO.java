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
    public static void crunchifyWriteToFile(String myData, File file2) {

        File crunchifyFile = new File("etd.txt");


        // exists(): Tests whether the file or directory denoted by this abstract pathname exists.
        if (!crunchifyFile.exists()) {

            try {
                crunchifyFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Exception Occurred: " + e.toString());
            }
        }

        try {

            // Convenience class for writing character files
            FileWriter crunchifyWriter;
            crunchifyWriter = new FileWriter(crunchifyFile.getAbsoluteFile(), true);

            // Writes text to a character-output stream
            BufferedWriter bufferWriter = new BufferedWriter(crunchifyWriter);
            bufferWriter.write(myData.toString()+","+"\t");
            bufferWriter.close();

            crunchifyLog("Company data saved at file location: "  + " Data: " + myData + "\n");
        } catch (IOException e) {

            crunchifyLog("Hmm.. Got an error while saving Company data to file " + e.toString());
        }
    }
    public static void crunchifyLog(String d){
        System.out.println(d);
    }

    public static void crunchifyReadFromFile() {
        Set <Object> set = new HashSet<>();
        Gson gson = new Gson();
        File crunchifyFile = new File("etd.txt");
        Student company = null;
        if (!crunchifyFile.exists())
            crunchifyLog("File doesn't exist");
        try {
            FileReader fw=new FileReader(crunchifyFile);
            BufferedReader bufferedReader = new BufferedReader(fw);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                StringReader s  =new StringReader(line);
                JsonReader myReader = new JsonReader(s);
                company = gson.fromJson(myReader, Student.class);
                crunchifyLog("Company Name: " + company.toString());
                students.add(company);
            }
        } catch (Exception e) {
            crunchifyLog("error load cache from file " + e.toString());
        }

        System.out.println();

    }
}
