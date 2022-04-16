package com.company;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.company.Dao.*;
import static com.company.Tools.randomPId;

public class Prets {
	private String id_prets= randomPId();
	private Niveau niveau;
	private Set<Pretspersonnels> pretspersonnels= new HashSet<Pretspersonnels>();
	public Set<Pretspersonnels> getPretspersonnels() {
		return pretspersonnels;
	}
	private double versement;
	private LocalDate vers1=LocalDate.of(0,1,1);
	private LocalDate vers2=LocalDate.of(0,1,1);
	private LocalDate vers3=LocalDate.of(0,1,1);
	private LocalDate vers4=LocalDate.of(0,1,1);
	private LocalDate date;
	private boolean isencoour=true;
	private double balance;

	public void setId_prets(String id_prets) {
		this.id_prets = id_prets;
	}
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Prets() {
	}

	public Prets(String id_prets, String niveau, double versement, LocalDate vers1, LocalDate vers2, LocalDate vers3, LocalDate vers4, LocalDate date, boolean isencoour, double balance) {
		this.id_prets = id_prets;
		for(Niveau n:niveaux){
			if(n.getNiveau().equals(niveau)){
			this.niveau = n;}
		}
		this.versement = versement;
		this.vers1 = vers1;
		this.vers2 = vers2;
		this.vers3 = vers3;
		this.vers4 = vers4;
		this.date = date;
		this.isencoour = isencoour;
		this.balance = balance;
	}

	public String getId_prets() {
		return id_prets;
	}
	public Niveau getNiveau() {
		return niveau;
	}
	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}
	public double getMontantbrut() {
		double montant=0;
		for(Pretspersonnels x:pretspersonnels){
			montant +=x.getMontant()/(1+taux);
		}
		return montant;
	}
	public double getMontant() {
		double montant=0;
		for(Pretspersonnels x:pretspersonnels){
			montant +=x.getMontant();
		}

		return montant;
	}
	public double getInteret() {
		return ((getVersement()*4)-((getVersement()*4)/(1+taux)));
	}

	public double getVersement() {
		return versement;
	}
	public void setVersement(double versement) {
		this.versement = versement;
	}
	public LocalDate getVers1() {
		return vers1;
	}
	public void setVers1(LocalDate vers1) {
		this.vers1 = vers1;
	}
	public LocalDate getVers2() {
		return vers2;
	}
	public void setVers2(LocalDate vers2) {
		this.vers2 = vers2;
	}
	public LocalDate getVers3() {
		return vers3;
	}
	public void setVers3(LocalDate vers3) {
		this.vers3 = vers3;
	}
	public LocalDate getVers4() {
		return vers4;
	}
	public void setVers4(LocalDate vers4) {
		this.vers4 = vers4;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void print(){
		System.out.printf("%5s %5s %30s %30s %5s",id_prets,niveau.getNiveau(),(getVersement()*4),getMontant(),pretspersonnels.size());
	}
	public String stringgson(){
		return id_prets+typeText.delimiteur
				+niveau.getNiveau()+typeText.delimiteur
				+versement+typeText.delimiteur
				+vers1+typeText.delimiteur
				+vers2+typeText.delimiteur+
				vers3+typeText.delimiteur+
				vers4+ typeText.delimiteur
				+date+typeText.delimiteur
				+isencoour+typeText.delimiteur
				+balance;
	}
	@Override
	public String toString() {String x="";
		x+= "Prets{\nNb: La devise est le gourde" +
				"\n\tid_prets='" + id_prets + '\'' +
				"\n\t niveau=" + niveau.getNiveau() +
				"\n\t Montant brut =" + ((getVersement()*4)/(1+taux)) +
				"\n\t Montant net =" + (getVersement()*4) +
				"\n\t interet=" + getInteret() +
				"\n\t versement=" + getVersement() +
				"\n\t balance sur le versement en cours =" + balance
				;
				if(!isencoour)x+="Le pret a ete totalement recouvree!";
				if(vers1.equals(LocalDate.of(0,1,1)))x+="\n\t premier versement non encore recouvre ";
				else{
					x+="\n\t premier versement recouvree le "+vers1;
					if(vers2.equals(LocalDate.of(0,1,1)))x+="\n\t second versement non encore recouvre ";
					else{
						x+="\n\t second versement recouvree le "+vers2;
						if(vers3.equals(LocalDate.of(0,1,1)))x+="\n\t troisieme versement non encore recouvre ";
						else{
							x+="\n\t premier versement recouvree le "+vers3;
							if(vers4.equals(LocalDate.of(0,1,1)))x+="\n\t dernier versement non encore recouvre ";
							else{
								x+="\n\t dernier versement recouvree le "+vers4;
							}
						}
					}
				}
				x+= "\n\t"+getPretspersonnels().size()+" etudiant ont participer a ce pret: ";
				for (Pretspersonnels y:getPretspersonnels()){
					x+="\n\t\t "+y.getStudent().getF_name()+" "+y.getStudent().getL_name()+" d'Id "
							+y.getStudent().getId_student()+" reste "+ (y.getMontant())+" gourdes a payer.(interet inclu.)";
				}
				x+="\n\t Le pret a ete placee le " + date +
				"\n\n\t}";
		return x;
	}

	public void setIsencoour(boolean parseBoolean) {
	}

	public void setPerso(Set<Pretspersonnels> pretspersonnels) {
	}
}
