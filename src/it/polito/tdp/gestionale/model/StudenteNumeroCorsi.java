package it.polito.tdp.gestionale.model;

public class StudenteNumeroCorsi {
	
	private Studente studente;
	private int numeroCorsi;
	public StudenteNumeroCorsi(Studente studente, int numeroCorsi) {
		super();
		this.studente = studente;
		this.numeroCorsi = numeroCorsi;
	}
	public Studente getStudente() {
		return studente;
	}
	public void setStudente(Studente studente) {
		this.studente = studente;
	}
	public int getNumeroCorsi() {
		return numeroCorsi;
	}
	public void setNumeroCorsi(int numeroCorsi) {
		this.numeroCorsi = numeroCorsi;
	}
	
	

}
