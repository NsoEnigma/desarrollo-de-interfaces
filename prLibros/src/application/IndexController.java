package application;

import java.awt.Button;
import java.awt.event.ActionEvent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class IndexController {
	
	@FXML
	private TextField txtTitulo;
	
	@FXML
	private ChoiceBox cbEditorial;
	
	@FXML
	private TextField txtAutor;
	
	@FXML
	private TextField txtPaginas;
	
	@FXML
	private TableView <Libro> tableLibros;
	
	@FXML
	private TableColumn <Libro, String> columnTitulo;
	
	@FXML
	private TableColumn <Libro, String> columnEditorial;
	
	@FXML
	private TableColumn <Libro, String> columnAutor;
	
	@FXML
	private TableColumn <Libro, Integer> columnPaginas;
	
	@FXML
	private Button btnAnadir;
	
	private ObservableList<Libro> listaLibros =
		FXCollections.observableArrayList(
				new Libro("La Biblia", "Planeta", "Jesús", 500)
		);
	
	public ObservableList<String> listaEditoriales = 
		FXCollections.observableArrayList(
			"Planeta",
			"Altaya",
			"Kadokawa",
			"Penguin Libros" 
		);
	
	@FXML
	private void initialize() {
		
		cbEditorial.setItems(listaEditoriales);
		
		columnTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		columnEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
		columnAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		columnPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
		
		tableLibros.setItems(listaLibros); 
	}
	
	
	@FXML
	public void anadirLibro(ActionEvent event) {
		if (esNumero(txtPaginas.getText())) {	
		Libro L = new Libro(
				txtTitulo.getText(),
				cbEditorial.getValue().toString(),
				txtAutor.getText(),
				Integer.parseInt(txtPaginas.getText())
		);
		
		listaLibros.add(L);
		
		txtTitulo.clear();
		cbEditorial.getSelectionModel().clearSelection();
		txtAutor.clear();
		txtPaginas.clear();
				
	}else {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Error al insertar");
		alerta.setHeaderText("No se ha introducido un número en las páginas.");
		alerta.setContentText("Por favor inroduzca un número en las páginas");
		alerta.showAndWait();
		}
	}
	
	@FXML
	public void borrarLibro(ActionEvent event) {
		System.out.println("Borrando un libro");
	}
	
	public boolean esNumero (String s) {
		try{
			Integer.parseInt(s);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}
	}
}
