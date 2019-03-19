/**
 * 
 * Descripción: Gestión escolar (estudiantes, EE y horarios)
 * Modificación 18/03/2019 
 * @author Rafael Colorado fernández
 * @version 1.0
 * @since 2019/02/27
 *
 */
package alumnos;

import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Alumnos extends Application {
 
  @Override
  public void start(Stage stage) throws Exception {
    
    Parent root = FXMLLoader.load(getClass().getResource("/alumnos/interfaz/FXMLAlumnos.fxml"));   
    Scene scene = new Scene(root);  
    stage.setScene(scene);
    stage.show();    
 
  }
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  } 
}
