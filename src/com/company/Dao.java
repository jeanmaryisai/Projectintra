package com.company;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


import static com.company.Tools.*;


public abstract class Dao {
	public static Set<Niveau> niveaux=new HashSet<Niveau>();
	public static Set<Student> students= new HashSet<Student>();
	public static Set<Prets> prets=new HashSet<Prets>();
	public static Set<Remboursement> remboursements=new HashSet<Remboursement>();
	public static Set<Pretspersonnels> pretspersonnels= new HashSet<>();
	public static Set<RemboursementPersonnel> remboursementPersonnels= new HashSet<>();
	public static double taux=0.1;
	public static double montantPretMin=100.00;

// pas besoin de load
	public static boolean stayed=true;

	public static Niveau niv1= new Niveau("L1");
	public static Niveau niv2= new Niveau("L2");
	public static Niveau niv3= new Niveau("L3");
	public static Niveau niv2a= new Niveau("DUT1");
	public static Niveau niv3a= new Niveau("DUT2");

	public static Fileinfo text=new Fileinfo();
	public static Fileinfo typeText=text;









}
