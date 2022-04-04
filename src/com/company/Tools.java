package com.company;

import java.util.Random;
import java.util.Scanner;

import static com.company.Dao.*;

public abstract class Tools {
    public static String niv () {
        String x=e();
        if(x.equals("L1")||x.equals("L2")||x.equals("L3")||x.equals("DU2")||x.equals("DU1")) {
            test(1);return x;
        }System.out.println("Veuillez entrer l'un des niveau suivant: 'L1', 'L2' 'L3' 'DUT1', 'DU2' ");
            return niv();
    }
    public static int ei(){
        try{

            Scanner x=new Scanner(System.in);
            int i=x.nextInt();
            return i;
        }catch (Exception e){
            System.out.println("Please enter an Interger");
            return ei();
        }
    }
    public static long el(){
        try{

            Scanner x=new Scanner(System.in);
            long i=x.nextLong();
            return i;
        }catch (Exception e){
            System.out.println("Please enter an Interger");
            return ei();
        }
    }
    public static String randomSId(){
        Random x=new Random();
        x.nextInt(99999);x.nextInt(99999);
        int i=  (x.nextInt(99999)+100000);
        String v="Ecr-"+String.valueOf(i);
        for(Student h:students){
            if(h.getId_student().equals(v)){
                v=randomSId();
                break;
            }
        }
        return v;
    }
    public static String randomPId(){
        Random x=new Random();
        x.nextInt(99999);x.nextInt(99999);
        int i=  (x.nextInt(99999)+100000);
        String v="Pcr-"+String.valueOf(i);
        for(Prets h: prets){
            if(h.getId_prets().equals(v)){
                v=randomPId();
                break;
            }
        }
        return v;
    }
    public static String randomPPId(){
        Random x=new Random();
        x.nextInt(99999);x.nextInt(99999);
        int i=  (x.nextInt(99999)+100000);
        String v="RPr-"+String.valueOf(i);
       for(Prets xr:prets){
        for(Pretspersonnels h: xr.getPretspersonnels()){
            if(h.getId().equals(v)){
                v=randomPId();
                break;
            }
        }
       }
        return v;
    }
    public static String randomRPId(){
        Random x=new Random();
        x.nextInt(99999);x.nextInt(99999);
        int i=  (x.nextInt(99999)+100000);
        String v="PPr-"+String.valueOf(i);
        for(Remboursement xr: remboursements){
            for(RemboursementPersonnel h: xr.getDtailRembos()){
                if(h.getId().equals(v)){
                    v=randomPId();
                    break;
                }
            }
        }
        return v;
    }

    public static boolean isvalide(){
        String v;
        boolean valide=true;
        do {
            System.out.printf("Presser \"y\" pour oui, \"n\" pour non ");
            Scanner x = new Scanner(System.in);
            v=x.nextLine();
        }while (!v.equals("y")&&!v.equals("n"));
        if(v.equals("n")) valide=false;
        return valide;
    }
    public static String e(){
        Scanner x=new Scanner(System.in);
        String i=x.nextLine();
        return i;
    }
    public static void br(){
        Scanner x=new Scanner(System.in);
        d("Presser enter pour continuer");
        x.nextLine();
    }
    public static void test(int x){
        System.out.println("Test "+x+" succeed");
    }

    public static double ed(){
        try{

            Scanner x=new Scanner(System.in);
            double i=x.nextDouble();
            return i;
        }catch (Exception e){
            System.out.println("Please enter a double!");
            return ed();
        }
    }

    public static void d(Object x){
        System.out.println(x);
    }
}
