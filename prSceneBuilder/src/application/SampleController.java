package application;

import javax.swing.Icon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SampleController {
	
	@FXML
	private TextField txtNombre;
	
	@FXML
	private PasswordField txtContraseña;
	
	@FXML
	private Button btnEnviar;
	
	@FXML
	private Label lblMensaje;
	
	private Icon icono;
	
	@FXML
	public void mostrarMensaje(ActionEvent event) {
		lblMensaje.setText(txtNombre.getText()); 
	}
}