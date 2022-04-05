package com.company;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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

    public static Set<Object> Read(File file){
        Set <Object> set = new HashSet<>();
        try{
//            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//            String line = null;
//            Scanner sc=new Scanner(new FileInputStream("test.txt"));

            Scanner sc=new Scanner(file);
            while(sc.hasNextLine()){
                System.out.println(sc.nextLine());
            }
            System.out.println(set);
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }
}
