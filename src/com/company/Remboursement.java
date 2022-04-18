package com.company;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.company.Dao.typeText;
import static com.company.Tools.randomRId;

public class Remboursement {
	private String id=randomRId();
	private Niveau niveau;
	private Prets prets;
	private Student student;
	private LocalDate dateRenboursement;
	public Set<RemboursementPersonnel>listDtail= new HashSet<RemboursementPersonnel>();

	public double getMontant(){
		double montant=0;
		for(RemboursementPersonnel x: listDtail){
			montant += x.getMontant();
		}
		return montant;
	}
	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}
	public Remboursement() {
	
	}
	
	
	




	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public Prets getPrets() {
		return prets;
	}



	public void setPrets(Prets prets) {
		this.prets = prets;
	}



	public String getNomVers() {
		return "";
	}


	public Student getStudent() {
		return student;
	}



	public void setStudent(Student student) {
		this.student = student;
	}


	public LocalDate getDateRenboursement() {
		return dateRenboursement;
	}



	public void
	setDateRenboursement(LocalDate dateRenboursement) {
		this.dateRenboursement = dateRenboursement;
	}



	public Set<RemboursementPersonnel> getListDtail() {
		return listDtail;
	}



	public void setListDtail(Set<RemboursementPersonnel> listDtail) {
		this.listDtail = listDtail;
	}

	public String stringgson(){
		return id+typeText.delimiteur+
		niveau.getNiveau()+typeText.delimiteur+
		prets.getId_prets()+typeText.delimiteur+
		student.getId_student()+typeText.delimiteur+
				dateRenboursement;
	}
	@Override
	public String toString() {
		String xe="Remboursement{" +
				"id=" + id +
				"\n\t niveau=" + niveau.getNiveau() +
				"\n\t id du prets=" + prets.getId_prets() +
				//"\n\t nomVers='" + nomVers + '\'' +
				//"\n\t student=" + student.getL_name() + " "+student.getF_name()+
				"\n\t montant=" + getMontant() +
				"\n\t List Etudiant ayant participer a ce remboursement:\n\t";
					for (RemboursementPersonnel x:listDtail) {
							xe+=x.getEtudient().getF_name()+
								" "+x.getEtudient().getL_name()+" d'Id "+
								x.getEtudient().getId_student()+" a placer "
								+x.getMontant()+" gourdes\n\t";}
								xe+="\n\t dateRenboursement=" + dateRenboursement.toString() +
								'}';
					return xe;
					}
	}

