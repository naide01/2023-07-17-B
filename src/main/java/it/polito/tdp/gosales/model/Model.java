package it.polito.tdp.gosales.model;

import java.util.*;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.gosales.dao.GOsalesDAO;

public class Model {
	private GOsalesDAO dao;
	private SimpleWeightedGraph<Products, DefaultWeightedEdge> grafo;
	private Map <Integer,Products> mappa;
	
	
	public Model() {
		this.dao = new GOsalesDAO();
		this.mappa = this.dao.getAllProducts();
		
	}
	public void creagrafo(int anno, String brand) {
		this.grafo = new SimpleWeightedGraph<Products, DefaultWeightedEdge> (DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(brand));
		for (Arco a: this.dao.getArchi(anno, brand, mappa)) {
			Graphs.addEdgeWithVertices(this.grafo, a.getP1(),a.getP2(), a.getPeso());
		 }
	}
	public List<Arco> getArchi(String b, Integer a){
		return this.dao.getArchi(a, b, mappa);
	}
	
	public List<Integer> getAnnii(){
		return this.dao.getAnni();
	}

	public List<String> getBrand() {
		return this.dao.getBrand();
	}
	public int numV() {
		return this.grafo.vertexSet().size();
	}
	public int numA() {
		return this.grafo.edgeSet().size();
	}
	
}
