package it.polito.tdp.gestionale.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.gestionale.db.DidatticaDAO;

public class Model {

	private List<Corso> corsi;
	private List<Studente> studenti;
	private DidatticaDAO didatticaDAO;

	private Graph<Nodo, DefaultEdge> grafo;
	private List<Nodo> nodi;
	
	
	public Model() {
		didatticaDAO = new DidatticaDAO();
		corsi = didatticaDAO.getTuttiICorsi();
		studenti = didatticaDAO.getTuttiStudenti();
		nodi = new ArrayList<>();
	}
	
	public List<Corso> getCorsi(){
		return corsi;
	}
	
	public List<Studente> getStudenti(){
		return studenti;
	}
	
	public List<Iscrizione> getIscrizioni(){
		return didatticaDAO.getIscrizioni();
	}
		
	public void creaGrafo() {
		
		grafo = new SimpleGraph<Nodo, DefaultEdge>(DefaultEdge.class);
		
		nodi.addAll(this.corsi);
		nodi.addAll(this.studenti);
		
		Graphs.addAllVertices(grafo, nodi);
		
		System.out.println("Vertici: "+grafo.vertexSet().size());
		
		List<Iscrizione> iscrizioni = this.getIscrizioni();
		
		for (Iscrizione i : iscrizioni) {
			if(grafo.vertexSet().contains(i.getStudente())) {
				Graphs.addEdgeWithVertices(grafo, i.getStudente(), i.getCorso());
			}
		}
		
		System.out.println("Archi: "+grafo.edgeSet().size());
	}
	
	public String printStats() {
		
		List<StudenteNumeroCorsi> freq = didatticaDAO.getNumeroIscrizioniPerStudente();	
		String result = "";
		
		int max = 0;
		for (StudenteNumeroCorsi s : freq) {
			if(s.getNumeroCorsi() > max)
				max = s.getNumeroCorsi();
		}		
		
		for (int i=1; i<=max; i++) {
			int conta = 0;
			for(Studente s : studenti) {
				if(grafo.edgesOf(s) != null && i ==	grafo.edgesOf(s).size()) {
					conta++;
				}
			}
			result += "Numero studenti iscritti a "+i+" Corsi: "+conta+"\n";
		}
		return result;
		
	}
}
