package it.polito.tdp.gosales;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.gosales.model.Arco;
import it.polito.tdp.gosales.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {

    private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCerca;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private ComboBox<Integer> cmbAnno;

    @FXML
    private ComboBox<String> cmbBrand;

    @FXML
    private ComboBox<?> cmbProdotto;

    @FXML
    private TextArea txtArchi;

    @FXML
    private TextArea txtResGrafo;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCercaPercorso(ActionEvent event) {

    }
    @FXML
    void doCreaGrafo(ActionEvent event) {
    	String b = this.cmbBrand.getValue();
    	Integer a = this.cmbAnno.getValue();
    	if (b == null) {
    		this.txtResGrafo.setText("Inserire un brand dal menù a tendina! \n");
    		return;
    	}
    	if (a == null) {
    		this.txtResGrafo.setText("Inserire un anno dal menù a tendina! \n");
    		return;
    	}
    	this.model.creagrafo(a,b);
    	this.txtResGrafo.setText("num vertici: " + this.model.numV() + "\n");
    	this.txtResGrafo.appendText("num archi: " + this.model.numA() + "\n");
    	
    	List<Arco> archi = this.model.getArchi(b, a);
    	List<Arco> stampa = archi.subList(0, 3);
    	
    	for (Arco arco : stampa) {
    		this.txtArchi.appendText(arco + "");
    	}
    	List<String> risultato = new ArrayList<String>();
    	List<String> lista = new ArrayList<String>();
    	
    	for (Arco a2: stampa) {
    		if (lista.contains(a2.getP1().getNumber()+ "")){
    			risultato.add(a2.getP1().getNumber()+ "");
    		}
    		lista.add(a2.getP1().getNumber() + "");
    		
    		if (lista.contains(a2.getP2().getNumber() + "")) {
    			risultato.add(a2.getP2().getNumber() + "");
    		}
    		lista.add(a2.getP2().getNumber() + "");
    	}
    	this.txtArchi.appendText("Nodi ripetuti: " + risultato + "\n");
    	
    }
    @FXML
    void initialize() {
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbAnno != null : "fx:id=\"cmbAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbBrand != null : "fx:id=\"cmbColore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbProdotto != null : "fx:id=\"cmbProdotto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtArchi != null : "fx:id=\"txtArchi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResGrafo != null : "fx:id=\"txtResGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    public void setModel(Model model) {
    	this.model = model;
    	this.cmbAnno.getItems().setAll(this.model.getAnnii());
    	this.cmbBrand.getItems().setAll(this.model.getBrand());
    }

}
