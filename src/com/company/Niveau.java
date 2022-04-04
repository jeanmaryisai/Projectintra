package com.company;

import java.util.HashSet;
import java.util.Set;



public class Niveau {
	private String niveau;
	private Student leaddr;

	public Student getLeaddr() {
		return leaddr;
	}

	public void setLeaddr(Student leaddr) {
		this.leaddr = leaddr;
	}

	private Set<Student>qteEtudient= new HashSet<Student>();

	public Niveau(String niveau) {
		this.niveau = niveau;
	}

	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	//public String getLeaddr() {
	//	return leaddr;
//	}
	//public void setLeaddr(String leaddr) {
//		this.leaddr = leaddr;
	//}
	public Set<Student> getQteEtudient() {
		return qteEtudient;
	}
	public void setQteEtudient(Set<Student> qteEtudient) {
		this.qteEtudient = qteEtudient;
	}


	@Override
	public String toString() {
		return "Niveau{" +
				"\n\tniveau='" + niveau + '\'' +
				'}';
	}
}
