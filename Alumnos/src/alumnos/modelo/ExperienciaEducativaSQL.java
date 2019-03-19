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


public class ExperienciaEducativaSQL extends ExperienciaEducativa implements ExperienciaEducativaDAO {
    
    public ExperienciaEducativaSQL(String nrc, String nombreEE, int creditos, int horasTeoricas, int horasPracticas) {
        super(nrc, nombreEE, creditos, horasTeoricas, horasPracticas);
    }
    
   /**
   * Este método sirve para agregar una EE a la BD
   * @param connection Conexión a la BD
   * @return Retorna la cantidad de registros afectados por la sentencia SQL
   */
    @Override
  public int agregarExperienciaEducativa(Connection connection){
    try {
      PreparedStatement instruccion = connection.prepareStatement("INSERT INTO experiencia_educativa(nrc, nombreEE, "
          + "creditos, horasTeoricas, horasPracticas) VALUES (?, ?, ?, ?, ?)"); //Evitar la inyección SQL y parametrizar
      instruccion.setString(1, nrc.get());//Sustituir el signo de interrogacion por lo que contiene la propiedad de matricula
      instruccion.setString(2, nombreEE.get());
      instruccion.setInt(3, creditos.get());
      instruccion.setInt(4, horasTeoricas.get());
      instruccion.setInt(5, horasPracticas.get());
      return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
    } catch (SQLException ex) {
      Logger.getLogger(ExperienciaEducativa.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
      
    }
  }
  
  /**
   * Este método es sirve para modificar alguna característica de la EE através de sentencias SQL
   * @param connection Conexion a la BD
   * @return Retorna la cantidad de registros afectados
   */
    @Override
  public int modificarExperienciaEducativa(Connection connection){
    try {
      PreparedStatement instruccion = connection.prepareStatement("UPDATE experiencia_educativa SET nombreEE = ?, creditos = ?, horasTeoricas = ?, horasPracticas = ?, WHERE nrc = ?");
      instruccion.setString(1, nombreEE.get()); //Sustituir el signo de interrogacion por lo que contiene la propiedad de matricula
      instruccion.setInt(2, creditos.get());
      instruccion.setInt(3, horasTeoricas.get());
      instruccion.setInt(4, horasPracticas.get());
      instruccion.setString(5, nrc.get());
      return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
    } catch (SQLException ex) {
      Logger.getLogger(ExperienciaEducativa.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
    }
  }
  
  /**
   * Este método es para eliminar a una EE através de su matricula y se utiliza una sentencia SQL
   * @param connection Coexión a la BD
   * @return devuelve la cantidad de registros afectados
   */
    @Override
  public int eliminarExperienciaEducativa(Connection connection){
    try {
      PreparedStatement instruccion = connection.prepareStatement("DELETE FROM experiencia_educativa WHERE nrc = ?");
      instruccion.setString(1, nrc.get());
      return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
    } catch (SQLException ex) {
      Logger.getLogger(ExperienciaEducativa.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
    }
  }
  
  /**
   * Este método carga la información de las EE que hay en la BD através de sentencias SQL.
   * @param connection Conexión a Mysql (BD)
   * @param listaExperienciaEducativa ObservableList de estudiantes
   */
  public static void llenarInformacion(Connection connection, ObservableList<ExperienciaEducativaSQL>listaExperienciaEducativa){
      try {
        Statement statement = connection.createStatement();
        ResultSet resultado = statement.executeQuery("SELECT nrc, nombreEE, creditos, horasTeoricas, horasPracticas FROM experiencia_educativa");
        while(resultado.next()){
          listaExperienciaEducativa.add(new ExperienciaEducativaSQL(
              resultado.getString("nrc"),
              resultado.getString("nombreEE"),
              resultado.getInt("creditos"),
              resultado.getInt("horasteoricas"),
              resultado.getInt("horasPracticas")
            )
          );
        }
      } catch (SQLException ex) {
        Logger.getLogger(ExperienciaEducativa.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
    
}
