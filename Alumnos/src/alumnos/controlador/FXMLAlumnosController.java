package alumnos.controlador;

import alumnos.modelo.Conexion;
import alumnos.modelo.Estudiante;
import alumnos.modelo.EstudianteSQL;
import static com.sun.javaws.ui.SplashScreen.hide;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class FXMLAlumnosController implements Initializable{
    
    private ObservableList <EstudianteSQL> listaEstudiante;
    
    private Conexion conexion;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonAgregar;
    
    @FXML
    private Button buttonControlEE;

    @FXML
    private Button buttonEditar;

    @FXML
    private Button buttonEliminar;   
    
    @FXML
    private Button buttonLimpiar;

    @FXML
    private Button buttonSalir;

    @FXML
    private TableColumn<EstudianteSQL, String> tablecolumnMaterno;

    @FXML
    private TableColumn<EstudianteSQL, String> tablecolumnMatricula;

    @FXML
    private TableColumn<EstudianteSQL, String> tablecolumnNombre;

    @FXML
    private TableColumn<EstudianteSQL, String> tablecolumnPaterno;

    @FXML
    private TableView<EstudianteSQL> tableviewTabla;
  
    @FXML
    private TextField textfieldMaterno;

    @FXML
    private TextField textfieldMatricula;

    @FXML
    private TextField textfieldNombre;

    @FXML
    private TextField textfieldPaterno;


    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        assert buttonAgregar != null : "fx:id=\"buttonAgregar\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
        assert buttonEditar != null : "fx:id=\"buttonEditar\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
        assert buttonEliminar != null : "fx:id=\"buttonEliminar\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";        
        assert buttonLimpiar != null : "fx:id=\"buttonLimpiar\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
        assert buttonSalir != null : "fx:id=\"buttonSalir\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
        assert buttonControlEE != null : "fx:id=\"buttonnControlEE\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
        assert tablecolumnMaterno != null : "fx:id=\"tablecolumnMaterno\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
        assert tablecolumnMatricula != null : "fx:id=\"tablecolumnMatricula\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
        assert tablecolumnNombre != null : "fx:id=\"tablecolumnNombre\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
        assert tablecolumnPaterno != null : "fx:id=\"tablecolumnPaterno\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
        assert tableviewTabla != null : "fx:id=\"tableviewTabla\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
        assert textfieldMaterno != null : "fx:id=\"textfieldMaterno\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
        assert textfieldMatricula != null : "fx:id=\"textfieldMatricula\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
        assert textfieldNombre != null : "fx:id=\"textfieldNombre\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
        assert textfieldPaterno != null : "fx:id=\"textfieldPaterno\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
        
        conexion = new Conexion();
        
        conexion.establecerConexion();
        //Inicializar lista
        listaEstudiante = FXCollections.observableArrayList();
        //Llenar lista
        EstudianteSQL.llenarInformacion(conexion.getConnection(), listaEstudiante);
        //Enlazar
        tableviewTabla.setItems(listaEstudiante);
        tablecolumnMatricula.setCellValueFactory(new PropertyValueFactory<EstudianteSQL, String>("matricula"));
        tablecolumnNombre.setCellValueFactory(new PropertyValueFactory<EstudianteSQL, String>("nombre"));
        tablecolumnPaterno.setCellValueFactory(new PropertyValueFactory<EstudianteSQL, String>("paterno"));
        tablecolumnMaterno.setCellValueFactory(new PropertyValueFactory<EstudianteSQL, String>("materno"));
        
        gestionarEventos();
        
        conexion.cerrarConexion();
    }
    /**
     * Este método controla los eventos (clic's) en la pantalla
     */
    public void gestionarEventos(){
      tableviewTabla.getSelectionModel().selectedItemProperty().addListener(
      new ChangeListener<Estudiante>(){
        @Override
        public void changed(ObservableValue<? extends Estudiante> observable, Estudiante valorAnterior, Estudiante valorSeleccionado) {
          if(valorSeleccionado!=null){
          textfieldMatricula.setText(valorSeleccionado.getMatricula());
          textfieldNombre.setText(valorSeleccionado.getNombre());
          textfieldPaterno.setText(valorSeleccionado.getPaterno());
          textfieldMaterno.setText(valorSeleccionado.getMaterno());
          buttonAgregar.setDisable(true);
          buttonEliminar.setDisable(false);
          buttonEditar.setDisable(false);
          //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }
        }  
      }
      );
         
    }
    /**
     * Este métedo es invocado por el botón "Limpiar" y sirve para limpiar los campos(textfield's)
     */
    public void limpiar(){
      textfieldMatricula.setText(null);
      textfieldNombre.setText(null);
      textfieldPaterno.setText(null);
      textfieldMaterno.setText(null);
      buttonAgregar.setDisable(false);
      buttonEliminar.setDisable(true);
      buttonEditar.setDisable(true);
    }
    /**
     * Este método es invocado por el botón crea la instancia de estudiante y la agrega a la BD
     */
    public void agregar(){
      EstudianteSQL estudiante = new EstudianteSQL(textfieldMatricula.getText(),
      textfieldNombre.getText(), textfieldPaterno.getText(), textfieldMaterno.getText());
      conexion.establecerConexion();
      int resultado = estudiante.agregarEstudiante(conexion.getConnection());
      conexion.cerrarConexion();
      if(resultado==1){
        listaEstudiante.add(estudiante);
        Alert mensaje = new Alert(AlertType.INFORMATION);
        mensaje.setContentText("Estudiante agregado con éxito");
        mensaje.show();
      }
    }
    /**
     * Este método es invocado por el botón "Editar" y sirve para modificar una instancia de estudiante
     */
    public void modificar(){
      EstudianteSQL estudiante = new EstudianteSQL(textfieldMatricula.getText(),
      textfieldNombre.getText(), textfieldPaterno.getText(), textfieldMaterno.getText());
      conexion.establecerConexion();
      int resultado = estudiante.modificarEstudiante(conexion.getConnection());
      conexion.cerrarConexion();
      if(resultado == 1){
        listaEstudiante.set(tableviewTabla.getSelectionModel().getSelectedIndex(),estudiante);
        Alert mensaje = new Alert(AlertType.INFORMATION);
        mensaje.setContentText("Estudiante modificado con éxito");
        mensaje.show();
      }
    }
    /**
     * Este método es invocado por el botón "Eliminar" y sirve para eliminar uns instancia de estudiante
     */
    public void eliminar(){
      conexion.establecerConexion();
      int resultado = tableviewTabla.getSelectionModel().getSelectedItem().eliminarEstudiante(conexion.getConnection());
      conexion.cerrarConexion();
      if(resultado == 1){
        listaEstudiante.remove(tableviewTabla.getSelectionModel().getSelectedIndex());
        Alert mensaje = new Alert(AlertType.INFORMATION);
        mensaje.setContentText("Estudiante eliminado con éxito");
        mensaje.show();
      }
    }
    
    /**
     * 
     */
    
    public void salir(){
        Alert mensaje = new Alert(AlertType.INFORMATION);
        mensaje.setTitle("Salir");
        mensaje.setContentText("Hasta luego");
        mensaje.setHeaderText(null);
        mensaje.showAndWait();        
        System.exit(0);                
        
    }
    public void llamarControlEE() throws IOException{
        Stage stage = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getResource("/alumnos/interfaz/FXMLExperienciaEducativa.fxml"));   
        Scene scene2 = new Scene(root2);  
        stage.setScene(scene2);
        stage.show();        
    }
    public void llamarHorario() throws IOException{
        Stage stage = new Stage();
        Parent root3 = FXMLLoader.load(getClass().getResource("/alumnos/interfaz/FXMLHorario.fxml"));   
        Scene scene3 = new Scene(root3);  
        stage.setScene(scene3);
        stage.show();        
    }
}
