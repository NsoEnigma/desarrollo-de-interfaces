package application;

import java.awt.Panel;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			/*Button btn = new Button("Click Aqui:");
			btn.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent eventz) {
					System.out.println("Hola mundo.");
				}
			});
			
			Label lbl = new Label("Hola mundo.");
			
			//Para insertar cosas en el panel, lo creamos.
			StackPane panel = new StackPane();
			
			panel.setAlignment(lbl, Pos.TOP_CENTER);
			panel.setAlignment(btn, Pos.CENTER);
			panel.getChildren().addAll(lbl,btn);*/
			
			//Creamos la escena, y decimos el panel que queremos y las caracteristicas de este.
			
			Button btn1 = new Button("Boton 1");
			Button btn2 = new Button("Boton 2");
			Button btn3 = new Button("Boton 3");
			Button btn4 = new Button("Boton 4");
			
			
			GridPane panel = new GridPane();
			
			panel.setVgap(15);
			panel.setHgap(15);
			panel.add(btn1, 0, 0);
			panel.add(btn2, 1, 0);
			panel.add(btn3, 0, 1);
			panel.add(btn4, 1, 1);
			
			/*VBox panelVertical = new VBox(15);
			panelVertical.setPadding(new Insets(15));
			panelVertical.getChildren().addAll(btn1,btn2,btn3);
			BorderPane bp = new BorderPane();
			bp.setRight(panelVertical);*/
			/*bp.setCenter(btn1);
			bp.setRight(btn2);
			bp.setTop(btn3);*/
			
			//HBox vBoxPanel = new HBox(15);
			//vBoxPanel.setPadding(new Insets(15));
			//vBoxPanel.getChildren().addAll(btn1,btn2,btn3);
			Scene scene = new Scene(panel,400,300);
			
			//Ponemos la escena en el escenario.
			primaryStage.setScene(scene);
			//Ponemos titulo a la ventana.
			primaryStage.setTitle("Introduccion a JavaFX");
			//Ponemos un icono de aplicacion.
			primaryStage.getIcons().add(new Image("/application/angry.png"));
			//Mostramos el escenario.
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
