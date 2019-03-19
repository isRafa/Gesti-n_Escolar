/**
 * 
 * Descripción: Gestión escolar (estudiantes, EE y horarios)
 * Modificación 18/03/2019 
 * @author Rafael Colorado fernández
 * @version 1.0
 * @since 2019/02/27
 *
 */
package alumnos.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {
  
  private Connection connection;
  public String url = "jdbc:mysql://localhost/bd_Estudiante";
  private String usuario="root";
  private String contrasena = "Cuatrocientos4";

  public Connection getConnection() {
    return connection;
  }

  public void setConnection(Connection connection) {
    this.connection = connection;
  }
  
  /**
   * Este método crea la conexión con Mysql
   */
  public void establecerConexion(){
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection(url, usuario, contrasena);
          } catch (ClassNotFoundException ex) {
      Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
      Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  /**
   * Método que cierra la conexión con la BD
   */
  public void cerrarConexion(){
    try {
      connection.close();
    } catch (SQLException ex) {
      Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
