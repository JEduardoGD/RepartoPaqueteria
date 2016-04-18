package mx.trillas.ControlEnvio.front;

import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mx.trillas.ControlEnvio.persistence.pojos.Controlenvio;

public class Departamento {

	/* Solo datos de ejemplo */

	private final ObservableList<Controlenvio> data = FXCollections.observableArrayList(
			new Controlenvio(new Integer(0), "DHL", "Chihuahua", "Maria Dominguez", "Contaduria", "", new Date()),
			new Controlenvio(new Integer(1), "Volaris", "Acapulco", "Sofia Montes", "Sistemas", "", new Date()),
			new Controlenvio(new Integer(2), "Fedex", "Zacatecas", "Mario Gutierrez", "Abogacia", "", new Date()),
			new Controlenvio(new Integer(3), "ODM", "Durango", "Eduardo Ayala", "Pagos", "", new Date()));

	public void departamentoStage(Stage stage) {

		try {
			VBox rootVbox = new VBox(25);
			rootVbox.setAlignment(Pos.CENTER);

			FlowPane nombrePane = new FlowPane(18, 15);
			nombrePane.setAlignment(Pos.CENTER);

			FlowPane destinoPane = new FlowPane(18, 15);
			destinoPane.setAlignment(Pos.CENTER);

			FlowPane abrevPane = new FlowPane();
			abrevPane.setAlignment(Pos.CENTER);

			FlowPane flowButtonsPane = new FlowPane();

			HBox headerPane = new HBox(150);
			Scene scene = new Scene(new Group(), 490, 450);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/departamento.css").toExternalForm());

			((Group) scene.getRoot()).getChildren().addAll(rootVbox);

			Button backButton = new Button("Regresar");
			backButton.getStyleClass().add("backButton");
			backButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Menu menu = new Menu();
					menu.AdminMenuStage(stage);
				}
			});
			backButton.setAlignment(Pos.TOP_LEFT);

			Button modifyButton = new Button("Modificar departamentos");
			modifyButton.getStyleClass().add("modificarRegistroButton");
			modifyButton.setAlignment(Pos.TOP_RIGHT);
			modifyButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Departamento depto = new Departamento();
					depto.modificarDepartamentoStage(stage);
				}
			});

			headerPane.getChildren().addAll(backButton, modifyButton);
			rootVbox.getChildren().addAll(headerPane);

			Text text = new Text("Ingrese el departamento");
			rootVbox.getChildren().addAll(text);

			Label nombreLabel = new Label("Departamento:");
			TextField nombreField = new TextField();

			nombrePane.getChildren().addAll(nombreLabel, nombreField);
			rootVbox.getChildren().addAll(nombrePane);

			Button aceptarButton = new Button("Aceptar");
			aceptarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});

			Button cancelarButton = new Button("Cancelar");
			cancelarButton.setAlignment(Pos.CENTER);
			cancelarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});

			flowButtonsPane.setAlignment(Pos.CENTER);
			flowButtonsPane.getChildren().addAll(aceptarButton, cancelarButton);

			rootVbox.getChildren().addAll(flowButtonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Alta y modificacion de departamentos");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modificarDepartamentoStage(Stage stage) {

		try {
			VBox paneVbox = new VBox();
			FlowPane buttonsPane = new FlowPane();

			Scene scene = new Scene(paneVbox, 400, 450);
			paneVbox.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/departamento.css").toExternalForm());

			TableView<Controlenvio> table = new TableView<Controlenvio>();
			table.setEditable(true);

			TableColumn<Controlenvio, String> idCol = new TableColumn<>("Id");
			idCol.setMinWidth(200);
			idCol.setEditable(false);
			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

			TableColumn<Controlenvio, String> deptoCol = new TableColumn<>("Departamento");
			deptoCol.setMinWidth(200);
			deptoCol.setCellValueFactory(new PropertyValueFactory<>("departamento"));

			deptoCol.setCellFactory(TextFieldTableCell.<Controlenvio> forTableColumn());
			deptoCol.setOnEditCommit((CellEditEvent<Controlenvio, String> t) -> {
				((Controlenvio) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setDepartamento(t.getNewValue());
			});

			table.setItems(data);
			table.getColumns().addAll(idCol, deptoCol);

			paneVbox.setSpacing(10);
			paneVbox.setPadding(new Insets(1));
			paneVbox.getChildren().addAll(table);

			Button aceptarButton = new Button("Aceptar");
			aceptarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			Button cancelButton = new Button("Cancelar");
			cancelButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Departamento depto = new Departamento();
					depto.departamentoStage(stage);
				}
			});
			buttonsPane.setAlignment(Pos.BASELINE_CENTER);
			buttonsPane.getChildren().addAll(aceptarButton, cancelButton);
			paneVbox.getChildren().addAll(buttonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Modificar departamentos");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ConfirmarDepartamentoStage(Stage stage, Controlenvio controlEnvio) {
		DropShadow shadow = new DropShadow();

		try {
			final VBox rootVbox = new VBox();
			FlowPane flowPane = new FlowPane();

			rootVbox.setSpacing(10);
			rootVbox.setPadding(new Insets(30, 30, 30, 30));

			Text text = new Text("Desea guardar los cambios?\n" + "\nDepartamento: " + controlEnvio.getDepartamento());

			Scene scene = new Scene(rootVbox, 450, 270);
			rootVbox.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/confirmar.css").toExternalForm());

			rootVbox.getChildren().addAll(text);

			Button aceptarButton = new Button("Aceptar");
			aceptarButton.setEffect(shadow);
			aceptarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});

			Button cancelarButton = new Button("Cancelar");
			cancelarButton.setEffect(shadow);
			cancelarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});

			Button returnButton = new Button("");
			returnButton.getStyleClass().add("backButton");
			returnButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			
			flowPane.setAlignment(Pos.CENTER);

			flowPane.getChildren().addAll(aceptarButton, cancelarButton);
			rootVbox.getChildren().addAll(flowPane);

			stage.setScene(scene);
			stage.setTitle("Confirmar departamento");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
