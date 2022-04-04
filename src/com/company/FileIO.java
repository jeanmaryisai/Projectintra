package com.company;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileIO {

    public static void Write(Set<Object> list,File file){

        FileWriter fw=null;
        System.out.println(list);
        try{
            fw=new FileWriter(file,true);
            for(Object el:list){
                fw.write(el+"\t");
            }
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Object> Read(File file){
        Set <Object> set = new HashSet<>();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
//            Scanner sc=new Scanner(new FileInputStream("test.txt"));

            String[] tokens=null;
            while( (line = br.readLine())!= null ) {
                // \\s+ means any number of whitespaces between tokens
                tokens = line.split("\\s+");

                for (String val : tokens) {
                    System.out.println(val);
                    set.add(val);
                }
            }
            System.out.println(set);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }
}
