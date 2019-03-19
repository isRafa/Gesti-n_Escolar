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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author Rafael
 */
public class EstudianteSQL extends Estudiante implements EstudianteDAO {

    public EstudianteSQL(String matricula, String nombre, String paterno, String materno) {
        super(matricula, nombre, paterno, materno);
    }
    
   /**
   * Este método sirve para agregar un estudiante a la BD
   * @param connection Conexión a la BD
   * @return Retorna la cantidad de registros afectados por la sentencia SQL
   */
    @Override
  public int agregarEstudiante(Connection connection){
    try {
      PreparedStatement instruccion = connection.prepareStatement("INSERT INTO estudiante(matricula, nombre, "
          + "paterno, materno) VALUES (?, ?, ?, ?)"); //Evitar la inyección SQL y parametrizar
      instruccion.setString(1, matricula.get());//Sustituir el signo de interrogacion por lo que contiene la propiedad de matricula
      instruccion.setString(2, nombre.get());
      instruccion.setString(3, paterno.get());
      instruccion.setString(4, materno.get());
      return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
    } catch (SQLException ex) {
      Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
      
    }
  }
  
   /**
   * Este método es sirve para modificar alguna característica del estudiante através de sentencias SQL
   * @param connection Conexion a la BD
   * @return Retorna la cantidad de registros afectados
   */
    @Override
  public int modificarEstudiante(Connection connection){
    try {
      PreparedStatement instruccion = connection.prepareStatement("UPDATE estudiante SET nombre = ?, paterno = ?, materno = ? WHERE matricula = ?");
      instruccion.setString(1, nombre.get()); //Sustituir el signo de interrogacion por lo que contiene la propiedad de matricula
      instruccion.setString(2, paterno.get());
      instruccion.setString(3, materno.get());
      instruccion.setString(4, matricula.get());
      return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
    } catch (SQLException ex) {
      Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
    }
  }
  
   /**
   * Este método es para eliminar a un estudiante através de su matricula y se utiliza una sentencia SQL
   * @param connection Coexión a la BD
   * @return devuelve la cantidad de registros afectados
   */
    @Override
  public int eliminarEstudiante(Connection connection){
    try {
      PreparedStatement instruccion = connection.prepareStatement("DELETE FROM estudiante WHERE matricula = ?");
      instruccion.setString(1, matricula.get());
      return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
    } catch (SQLException ex) {
      Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
    }
  }
  
   /**
   * Este método carga la información que hay en la BD através de sentencias SQL.
   * @param connection Conexión a Mysql (BD)
   * @param listaEstudiante ObservableList de estudiantes
   */
  public static void llenarInformacion(Connection connection, ObservableList<EstudianteSQL>listaEstudiante){
      try {
        Statement statement = connection.createStatement();
        ResultSet resultado = statement.executeQuery("SELECT matricula, nombre, paterno, materno FROM estudiante");
        while(resultado.next()){
          listaEstudiante.add(new EstudianteSQL(
              resultado.getString("matricula"),
              resultado.getString("nombre"),
              resultado.getString("paterno"),
              resultado.getString("materno")
            )
          );
        }
      } catch (SQLException ex) {
        Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
    
}
