/**
 * 
 * Descripción: Gestión escolar (estudiantes, EE y horarios)
 * Modificación 18/03/2019 
 * @author Rafael Colorado fernández
 * @version 1.0
 * @since 2019/02/27
 *
 */

package alumnos.controlador;

import alumnos.modelo.Conexion;
import alumnos.modelo.Estudiante;
import alumnos.modelo.EstudianteSQL;
import alumnos.modelo.ExperienciaEducativa;
import alumnos.modelo.ExperienciaEducativaSQL;
import static com.sun.javaws.ui.SplashScreen.hide;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class FXMLExperienciaEducativaController {
    
    private ObservableList<ExperienciaEducativaSQL> listaExperienciaEducativa;
    
    private Conexion conexion;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnSalirEE;

    @FXML
    private TableColumn<ExperienciaEducativaSQL, String> tableColumnCreditos;

    @FXML
    private TableColumn<ExperienciaEducativaSQL, String> tableColumnHorasPracticas;

    @FXML
    private TableColumn<ExperienciaEducativaSQL, String> tableColumnHorasTeoricas;

    @FXML
    private TableColumn<ExperienciaEducativaSQL, String> tableColumnNombreEE;

    @FXML
    private TableColumn<ExperienciaEducativaSQL, String> tableColumnNrc;

    @FXML
    private TableView<ExperienciaEducativaSQL> tableViewExperienciaEducativa;

    @FXML
    private TextField textFieldCreditos;

    @FXML
    private TextField textFieldHorasPracticas;

    @FXML
    private TextField textFieldHorasTeoricas;

    @FXML
    private TextField textFieldNombreEE;

    @FXML
    private TextField textFieldNrc;


    @FXML
    void salir(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert btnAgregar != null : "fx:id=\"btnAgregar\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
        assert btnEditar != null : "fx:id=\"btnEditar\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
        assert btnEliminar != null : "fx:id=\"btnEliminar\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";        
        assert btnLimpiar != null : "fx:id=\"btnLimpiar\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
        assert btnSalirEE != null : "fx:id=\"btnSalirEE\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
        assert tableColumnCreditos != null : "fx:id=\"tableColumnCreditos\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
        assert tableColumnHorasPracticas != null : "fx:id=\"tableColumnHorasPracticas\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
        assert tableColumnHorasTeoricas != null : "fx:id=\"tableColumnHorasTeoricas\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
        assert tableColumnNombreEE != null : "fx:id=\"tableColumnNombreEE\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
        assert tableColumnNrc != null : "fx:id=\"tableColumnNrc\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
        assert tableViewExperienciaEducativa != null : "fx:id=\"tableViewExperienciaEducativa\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
        assert textFieldCreditos != null : "fx:id=\"textFieldCreditos\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
        assert textFieldHorasPracticas != null : "fx:id=\"textFieldHorasPracticas\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
        assert textFieldHorasTeoricas != null : "fx:id=\"textFieldHorasTeoricas\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
        assert textFieldNombreEE != null : "fx:id=\"textFieldNombreEE\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
        assert textFieldNrc != null : "fx:id=\"textFieldNrc\" was not injected: check your FXML file 'FXMLExperienciaEducativa.fxml'.";
        
        conexion = new Conexion();
        
        //Establece la conexion
        conexion.establecerConexion();
        
        //Inicializar lista
        listaExperienciaEducativa = FXCollections.observableArrayList();
        
        //Llenar lista
        ExperienciaEducativaSQL.llenarInformacion(conexion.getConnection(), listaExperienciaEducativa);
        
        //Enlazar
        tableViewExperienciaEducativa.setItems(listaExperienciaEducativa);
        tableColumnNrc.setCellValueFactory(new PropertyValueFactory<ExperienciaEducativaSQL, String>("nrc"));
        tableColumnNombreEE.setCellValueFactory(new PropertyValueFactory<ExperienciaEducativaSQL, String>("nombreEE"));
        tableColumnCreditos.setCellValueFactory(new PropertyValueFactory<ExperienciaEducativaSQL, String>("creditos"));
        tableColumnHorasTeoricas.setCellValueFactory(new PropertyValueFactory<ExperienciaEducativaSQL, String>("horasTeoricas"));
        tableColumnHorasPracticas.setCellValueFactory(new PropertyValueFactory<ExperienciaEducativaSQL, String>("horasPracticas"));
        
        gestionarEventosEE();
        
        conexion.cerrarConexion();
    }
    
    /**
     * Este método es invocado por el botón crea la instancia de EE y la agrega a la BD
     */
    public void agregarEE(){
      ExperienciaEducativaSQL experiencia_educativa = new ExperienciaEducativaSQL(textFieldNrc.getText(),
      textFieldNombreEE.getText(), Integer.valueOf(textFieldCreditos.getText()), Integer.valueOf(textFieldHorasTeoricas.getText()), Integer.valueOf(textFieldHorasPracticas.getText()));
      conexion.establecerConexion();
      int resultado = experiencia_educativa.agregarExperienciaEducativa(conexion.getConnection());
      conexion.cerrarConexion();
      if(resultado==1){
        listaExperienciaEducativa.add(experiencia_educativa);
        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
        mensaje.setContentText("EE agregado con éxito");
        mensaje.show();
      }
    }
    
    /**
     * Este método es invocado por el botón "Editar" y sirve para modificar una instancia de EE
     */
    public void modificarEE(){
      ExperienciaEducativaSQL experiencia_educativa = new ExperienciaEducativaSQL(textFieldNrc.getText(),
      textFieldNombreEE.getText(), Integer.valueOf(textFieldCreditos.getText()), Integer.valueOf(textFieldHorasTeoricas.getText()), Integer.valueOf(textFieldHorasPracticas.getText()));
      conexion.establecerConexion();
      int resultado = experiencia_educativa.modificarExperienciaEducativa(conexion.getConnection());
      conexion.cerrarConexion();
      if(resultado == 1){
        listaExperienciaEducativa.set(tableViewExperienciaEducativa.getSelectionModel().getSelectedIndex(),experiencia_educativa);
        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
        mensaje.setContentText("EE modificado con éxito");
        mensaje.show();
      }
    }
    
    /**
     * Este métedo es invocado por el botón "Limpiar" y sirve para limpiar los campos(textfield's)
     */
    public void eliminarEE(){
      conexion.establecerConexion();
      int resultado = tableViewExperienciaEducativa.getSelectionModel().getSelectedItem().eliminarExperienciaEducativa(conexion.getConnection());
      conexion.cerrarConexion();
      if(resultado == 1){
        listaExperienciaEducativa.remove(tableViewExperienciaEducativa.getSelectionModel().getSelectedIndex());
        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
        mensaje.setContentText("EE eliminado con éxito");
        mensaje.show();
      }
    }
    
    /**
     * Este métedo es invocado por el botón "Limpiar" y sirve para limpiar los campos(textfield's)
     */
    public void limpiarEE(){
      textFieldNrc.setText(null);
      textFieldNombreEE.setText(null);
      textFieldCreditos.setText(null);
      textFieldHorasTeoricas.setText(null);
      textFieldHorasPracticas.setText(null);
      btnAgregar.setDisable(false);
      btnEliminar.setDisable(true);
      btnEditar.setDisable(true);
    }
    /**
     * Este método es invocado por el botón "Salir", y sirve para salir del sistema.
     */
    public void salirEE(){
        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
        mensaje.setTitle("Salir");
        mensaje.setContentText("Hasta luego");
        mensaje.setHeaderText(null);
        mensaje.showAndWait();
        //System.exit(0);        
    }
    
    /**
     * Este método controla los eventos (clic's) en la pantalla
     */
    public void gestionarEventosEE(){
      tableViewExperienciaEducativa.getSelectionModel().selectedItemProperty().addListener(
      new ChangeListener<ExperienciaEducativa>(){
        @Override
        public void changed(ObservableValue<? extends ExperienciaEducativa> observable, ExperienciaEducativa valorAnterior, ExperienciaEducativa valorSeleccionado) {
          if(valorSeleccionado!=null){
          textFieldNrc.setText(valorSeleccionado.getNrc());
          textFieldNombreEE.setText(valorSeleccionado.getNombreEE());
          textFieldCreditos.setText(String.valueOf(valorSeleccionado.getCreditos()));
          textFieldHorasTeoricas.setText(String.valueOf(valorSeleccionado.getHorasTeoricas()));
          textFieldHorasPracticas.setText(String.valueOf(valorSeleccionado.getHorasPracticas()));
          btnAgregar.setDisable(true);
          btnEliminar.setDisable(false);
          btnEditar.setDisable(false);          
          }
        }  
      }
      );            
         
    }

}
