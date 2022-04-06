package com.company;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.company.Tools.randomPId;

public class Prets {
	private String id_prets= randomPId();
	private Niveau niveau;
	private double montant;
	private Set<Pretspersonnels> pretspersonnels= new HashSet<Pretspersonnels>();

	public Set<Pretspersonnels> getPretspersonnels() {
		return pretspersonnels;
	}

	public void setPretspersonnels(Set<Pretspersonnels> pretspersonnels) {
		this.pretspersonnels = pretspersonnels;
	}

	private  double interet;
	private double versement;

	private LocalDate vers1=LocalDate.of(0,1,1);
	private LocalDate vers2=LocalDate.of(0,1,1);
	private LocalDate vers3=LocalDate.of(0,1,1);
	private LocalDate vers4=LocalDate.of(0,1,1);
	private LocalDate date;
	private boolean isencoour=true;
	private double balance;
	public boolean isIsencoour() {
		return isencoour;
	}

	public void setId_prets(String id_prets) {
		this.id_prets = id_prets;
	}

	public void setIsencoour(boolean isencoour) {
		this.isencoour = isencoour;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Prets() {
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
	public double getMontant() {
		montant=0;
		for(Pretspersonnels x:pretspersonnels){
			montant +=x.getMontant();
		}

		return montant;
	}
	public double getInteret() {
		return interet;
	}
	public void setInteret(double interet) {
		this.interet = interet;
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

	@Override
	public String toString() {
		return "Prets{" +
				"\n\tid_prets='" + id_prets + '\'' +
				"\n\t niveau=" + niveau +
				"\n\t montant=" + getMontant() +
				"\n\t interet=" + interet +
				"\n\t versement=" + getVersement() +
				"\n\t balance=" + balance +
				"\n\t vers1=" + vers1 +
				"\n\t vers2=" + vers2 +
				"\n\t vers3=" + vers3 +
				"\n\t vers4=" + vers4 +
				"\n\t date=" + date +
				"\n\t isencoour=" + isencoour +
				'}';
	}
}
