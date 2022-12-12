package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
	
	@FXML
	private Button btnBorrar;
	
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
		
		ObservableList listaLibrosBD = getLibrosBD();
		
		tableLibros.setItems(listaLibrosBD); 
	}
	
	private ObservableList<Libro> getLibrosBD () {
		
		/*
		 * Creamos la ObservableList donde almacenaremos
		 * los libros obtenidos de la BD
		 */
		ObservableList<Libro> listaLibrosBD = 
				FXCollections.observableArrayList();
		
		//	Nos conectamos a la BD
		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();
		
		String query = "select * from libros";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Libro libro = new Libro(
						rs.getString("titulo"),
						rs.getString("editorial"),
						rs.getString("autor"),
						rs.getInt("paginas") 
				);
				listaLibrosBD.add(libro);
			}
			
			//Cerramos la conexión
			connection.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return listaLibrosBD;
	}
	
	@FXML
	public void anadirLibro(ActionEvent event) {
		
		if (txtPaginas.getText().isEmpty() ||
				cbEditorial.getSelectionModel().isEmpty() ||
				txtAutor.getText().isEmpty() ||
				txtPaginas.getText().isEmpty()) {
				
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Información incompleta");
			alerta.setHeaderText("Falta información del libro");
			alerta.setContentText("Por favor, introduce todos los campos");
			alerta.showAndWait();
			
		} else {
			if (esNumero(txtPaginas.getText())) {
				Libro libro = new Libro(
						txtTitulo.getText(),
						cbEditorial.getValue().toString(),
						txtAutor.getText(),
						Integer.parseInt(txtPaginas.getText())
				);
					
				listaLibros.add(libro);
				
				txtTitulo.clear();
				cbEditorial.getSelectionModel().clearSelection();
				txtAutor.clear();
				txtPaginas.clear();
				
				DatabaseConnection dbConnection = new DatabaseConnection();
				Connection connection = dbConnection.getConnection();
				
				try {
					String query = "insert into libros (titulo, editorial, autor, paginas) VALUES (?, ?, ?, ?)";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, libro.getTitulo());
					ps.setString(2, libro.getEditorial());
					ps.setString(3, libro.getAutor());
					ps.setInt(4, libro.getPaginas());
					ps.executeUpdate();
					
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ObservableList listaLibrosBD = getLibrosBD();
				tableLibros.setItems(listaLibrosBD);
				
			} else {
				
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error al insertar");
				alerta.setHeaderText
					("No se ha introducido un número en las páginas");
				alerta.setContentText
					("Por favor, introduzca un número en las páginas");
				alerta.showAndWait();
			}
		}
	}
	
	@FXML
	public void borrarLibro(ActionEvent event) { 
		
		int indiceSeleccionado = tableLibros
									.getSelectionModel()
									.getSelectedIndex();
		
		System.out.println
			("Índice a borrar: " + indiceSeleccionado);
		
		if (indiceSeleccionado <= -1) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error al borrar");
			alerta.setHeaderText("No se ha seleccionado ningún libro a borrar");
			alerta.setContentText("Por favor, selecciona un libro para borrarlo");
			alerta.showAndWait();
		} else {
			tableLibros.getItems().remove(indiceSeleccionado);
			tableLibros.getSelectionModel().clearSelection();  
		}
	}
	
	public boolean esNumero (String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
