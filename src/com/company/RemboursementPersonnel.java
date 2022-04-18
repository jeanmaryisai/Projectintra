package com.company;


import static com.company.Dao.students;
import static com.company.Dao.typeText;
import static com.company.Tools.randomRPId;

public class RemboursementPersonnel {
	private String Id=randomRPId();
	private Prets prets;
	private Student etudient;
	private double montant;
	private Remboursement remboursement;
	
	public RemboursementPersonnel() {
	
	}

	public RemboursementPersonnel(String id, Student etudient,Prets prets, double montant) {
		super();
		Id = id;
		this.prets = prets;
		this.etudient = etudient;
		this.montant = montant;
	}
	public RemboursementPersonnel(String id, String etudient, double montant) {
		Id = id;
		for(Student s:students){
			if(s.getId_student().equals(etudient))
		this.etudient = s;}
		this.montant = montant;
	}

	public Remboursement getRemboursement() {
		return remboursement;
	}

	public void setRemboursement(Remboursement remboursement) {
		this.remboursement = remboursement;
	}

	public String getId() {
		return Id;
	}

	public Prets getPrets() {
		return prets;
	}

	public void setPrets(Prets prets) {
		this.prets = prets;
	}
	public void setId(String id ) {
		this.Id = id;
	}

	public Student getEtudient() {
		return etudient;
	}

	public void setEtudient(Student etudient) {
		this.etudient = etudient;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String stringgson(){
		return  Id+typeText.delimiteur+

		etudient.getId_student()+typeText.delimiteur+
				montant+typeText.delimiteur+
		remboursement.getId();
	}

	@Override
	public String toString() {
		return "RemboursementPersonnel {" +
				"\n\tId=" + Id +
				"\n\t prets=" + prets +
				"\n\t etudient Id=" + etudient.getId_student() +
				"\n\t etudient nom et prenom=" + etudient.getF_name() +etudient.getL_name()+
				"\n\t montant=" + montant +
				'}';
	}
}
