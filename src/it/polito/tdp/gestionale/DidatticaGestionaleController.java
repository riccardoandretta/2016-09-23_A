package it.polito.tdp.gestionale;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.gestionale.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DidatticaGestionaleController {

	private Model model;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtMatricolaStudente;

	@FXML
	private TextArea txtResult;

	@FXML
	void doCorsiFrequentati(ActionEvent event) {
		try {
			txtResult.clear();
			
			model.creaGrafo();
			String result = model.printStats();
			txtResult.setText(result);
			
		} catch (RuntimeException e) {
			txtResult.setText("Errore di connessione al DB.");
		}
	}

	@FXML
	void doVisualizzaCorsi(ActionEvent event) {
		try {
			txtResult.clear();
			txtResult.setText("premuto Visualizza Corsi");
		} catch (RuntimeException e) {
			txtResult.setText("Errore di connessione al DB.");
		}
	}

	@FXML
	void initialize() {
		assert txtMatricolaStudente != null : "fx:id=\"txtMatricolaStudente\" was not injected: check your FXML file 'DidatticaGestionale.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DidatticaGestionale.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;
	}

}
