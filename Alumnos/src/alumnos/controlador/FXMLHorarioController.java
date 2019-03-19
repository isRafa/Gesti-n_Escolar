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
import alumnos.modelo.ExperienciaEducativa;
import alumnos.modelo.ExperienciaEducativaSQL;
import alumnos.modelo.Horario;
import alumnos.modelo.HorarioSQL;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class FXMLHorarioController {
    
    private ObservableList<ExperienciaEducativaSQL> listaExperienciaEducativa;
    
    private ObservableList<HorarioSQL> listaHorario;
    
    private Conexion conexion;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button bttnHorario;

    @FXML
    private Button bttnAgregar;

    @FXML
    private Button bttnEliminar;

    @FXML
    private Button bttnLimpiar;

    @FXML
    private Button bttnSalir;

    @FXML
    private TableView<ExperienciaEducativaSQL> tableViewEE;

    @FXML
    private TableView<HorarioSQL> tableViewHorario;

    @FXML
    private TableColumn<HorarioSQL, String> tcDia;

    @FXML
    private TableColumn<HorarioSQL, String> tcHoraFin;

    @FXML
    private TableColumn<HorarioSQL, String> tcHoraInicio;

    @FXML
    private TableColumn<HorarioSQL, String> tcNrc;

    @FXML
    private TableColumn<HorarioSQL, String> tcSalon;

    @FXML
    private TableColumn<ExperienciaEducativaSQL, String> tcnNombreEE;

    @FXML
    private TableColumn<ExperienciaEducativaSQL, String> tcnNrc;

    @FXML
    private TextField tfDia;

    @FXML
    private TextField tfHoraFin;

    @FXML
    private TextField tfHoraInicio;

    @FXML
    private TextField tfNrc;

    @FXML
    private TextField tfSalon;    


    @FXML
    void initialize() {
        assert bttnHorario != null : "fx:id=\"bttnHorario\" was not injected: check your FXML file 'FXMLAlumnos.fxml'.";
        assert bttnAgregar != null : "fx:id=\"bttnAgregar\" was not injected: check your FXML file 'FXMLHorario.fxml'.";
        assert bttnEliminar != null : "fx:id=\"bttnEliminar\" was not injected: check your FXML file 'FXMLHorario.fxml'.";
        assert bttnLimpiar != null : "fx:id=\"bttnLimpiar\" was not injected: check your FXML file 'FXMLHorario.fxml'.";
        assert bttnSalir != null : "fx:id=\"bttnSalir\" was not injected: check your FXML file 'FXMLHorario.fxml'.";
        assert tableViewEE != null : "fx:id=\"tableViewEE\" was not injected: check your FXML file 'FXMLHorario.fxml'.";
        assert tableViewHorario != null : "fx:id=\"tableViewHorario\" was not injected: check your FXML file 'FXMLHorario.fxml'.";
        assert tcDia != null : "fx:id=\"tcDia\" was not injected: check your FXML file 'FXMLHorario.fxml'.";
        assert tcHoraFin != null : "fx:id=\"tcHoraFin\" was not injected: check your FXML file 'FXMLHorario.fxml'.";
        assert tcHoraInicio != null : "fx:id=\"tcHoraInicio\" was not injected: check your FXML file 'FXMLHorario.fxml'.";
        assert tcNrc != null : "fx:id=\"tcNrc\" was not injected: check your FXML file 'FXMLHorario.fxml'.";
        assert tcSalon != null : "fx:id=\"tcSalon\" was not injected: check your FXML file 'FXMLHorario.fxml'.";
        assert tcnNombreEE != null : "fx:id=\"tcnNombreEE\" was not injected: check your FXML file 'FXMLHorario.fxml'.";
        assert tcnNrc != null : "fx:id=\"tcnNrc\" was not injected: check your FXML file 'FXMLHorario.fxml'.";
        assert tfDia != null : "fx:id=\"tfDia\" was not injected: check your FXML file 'FXMLHorario.fxml'.";
        assert tfHoraFin != null : "fx:id=\"tfHoraFin\" was not injected: check your FXML file 'FXMLHorario.fxml'.";
        assert tfHoraInicio != null : "fx:id=\"tfHoraInicio\" was not injected: check your FXML file 'FXMLHorario.fxml'.";
        assert tfNrc != null : "fx:id=\"tfNrc\" was not injected: check your FXML file 'FXMLHorario.fxml'.";
        assert tfSalon != null : "fx:id=\"tfSalon\" was not injected: check your FXML file 'FXMLHorario.fxml'.";
        
        conexion = new Conexion();
        //Estable la conexion
        conexion.establecerConexion();
        
        //Inicializar lista
        listaExperienciaEducativa = FXCollections.observableArrayList();
        listaHorario = FXCollections.observableArrayList();      
        
        //Llenar lista
        ExperienciaEducativaSQL.llenarInformacion(conexion.getConnection(), listaExperienciaEducativa);
        HorarioSQL.llenarInformacionHorario(conexion.getConnection(), listaHorario);
        
        //Enlazar
        tableViewEE.setItems(listaExperienciaEducativa);
        tcnNrc.setCellValueFactory(new PropertyValueFactory<ExperienciaEducativaSQL, String>("nrc"));
        tcnNombreEE.setCellValueFactory(new PropertyValueFactory<ExperienciaEducativaSQL, String>("nombreEE"));
        
        tableViewHorario.setItems(listaHorario);
        tcNrc.setCellValueFactory(new PropertyValueFactory<HorarioSQL, String>("nrc"));
        tcHoraInicio.setCellValueFactory(new PropertyValueFactory<HorarioSQL, String>("horaInicio"));
        tcHoraFin.setCellValueFactory(new PropertyValueFactory<HorarioSQL, String>("horaFin"));
        tcDia.setCellValueFactory(new PropertyValueFactory<HorarioSQL, String>("dia"));
        tcSalon.setCellValueFactory(new PropertyValueFactory<HorarioSQL, String>("salon"));
        
        gestionarEventosH();
        
        conexion.cerrarConexion();
    }
    
    /**
     * Este método es invocado por el botón "Agregar" y crea la instancia de horario y la agrega a la BD
     */
    public void agregarH(){
      HorarioSQL horario = new HorarioSQL(tfNrc.getText(),
      tfHoraInicio.getText(), tfHoraFin.getText(), tfDia.getText(), tfSalon.getText());
      conexion.establecerConexion();
      int resultado = horario.agregarHorario(conexion.getConnection());
      conexion.cerrarConexion();
      if(resultado==1){
        listaHorario.add(horario);
        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
        mensaje.setContentText("Horario agregado con éxito");
        mensaje.show();
      }
    }
    
    /**
     * Este método es invocado por botón "eliminar" y borra el registro en la BD
     */
    public void eliminarH(){
      conexion.establecerConexion();
      int resultado = tableViewHorario.getSelectionModel().getSelectedItem().eliminarHorario(conexion.getConnection());
      conexion.cerrarConexion();
      if(resultado == 1){
        listaHorario.remove(tableViewHorario.getSelectionModel().getSelectedIndex());
        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
        mensaje.setContentText("Horario eliminado con éxito");
        mensaje.show();
      }
    }
    
    /**
     * Este métedo es invocado por el botón "Limpiar" y sirve para limpiar los campos(textfield's)
     */
    public void limpiarH(){
      tfNrc.setText(null);
      tfHoraInicio.setText(null);
      tfHoraFin.setText(null);
      tfSalon.setText(null);
      tfDia.setText(null);
      bttnAgregar.setDisable(false);
      bttnEliminar.setDisable(true);      
    }
    
    /**
     * Este método es invocado por el botón "Salir" y hace que se cierre la venatana
     */
    public void salirH(){
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
    public void gestionarEventosH(){
      tableViewHorario.getSelectionModel().selectedItemProperty().addListener(
      new ChangeListener<Horario>(){
        @Override
        public void changed(ObservableValue<? extends Horario> observable, Horario valorAnterior, Horario valorSeleccionado) {
          if(valorSeleccionado!=null){
          tfNrc.setText(valorSeleccionado.getNrc());
          tfHoraInicio.setText(valorSeleccionado.getHoraInicio());
          tfHoraFin.setText(String.valueOf(valorSeleccionado.getHoraFin()));
          tfSalon.setText(String.valueOf(valorSeleccionado.getSalon()));
          tfDia.setText(String.valueOf(valorSeleccionado.getDia()));
          bttnAgregar.setDisable(true);
          bttnEliminar.setDisable(false);            
          }
        }  
      }
      );            
         
    }
}
