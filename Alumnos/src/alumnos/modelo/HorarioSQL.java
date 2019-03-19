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

public class HorarioSQL extends Horario implements HorarioDAO {
    
    public HorarioSQL(String nrc, String horaInicio, String horaFin, String dia, String salon) {
        super(nrc, horaInicio, horaFin, dia, salon);
    }
    
   /**
   * Este método sirve para agregar un horario a la BD
   * @param connection Conexión a la BD
   * @return Retorna la cantidad de registros afectados por la sentencia SQL
   */
    @Override
  public int agregarHorario(Connection connection){
    try {
      PreparedStatement instruccion = connection.prepareStatement("insert into horario (salon, "
              + "horaInicio, horaFin, dia, idExperienciaEducativa) select * from (select ?, ?, ?, ?, (select idExperienciaEducativa "
              + "from experiencia_educativa where nrc= ?)) as tmp where not exists (select salon, horaInicio, horaFin, "
              + "dia from horario where (dia = ? and idExperienciaEducativa=(select idExperienciaEducativa from experiencia_educativa where nrc= ?)) "
              + "or (salon=? and horaInicio=? and horaFin=? and dia=?)) limit 1;");
      instruccion.setString(1, salon.get());//Evitar la inyección SQL y parametrizar
      instruccion.setString(2, horaInicio.get());//Sustituir el signo de interrogacion por lo que contiene la propiedad de matricula
      instruccion.setString(3, horaFin.get());
      instruccion.setString(4, dia.get());
      instruccion.setString(5, nrc.get());
      instruccion.setString(6, dia.get());     
      instruccion.setString(7, nrc.get());
      instruccion.setString(8, salon.get());
      instruccion.setString(9, horaInicio.get());
      instruccion.setString(10, horaFin.get());
      instruccion.setString(11, dia.get());
      return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
    } catch (SQLException ex) {
      Logger.getLogger(Horario.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
      
    }
  }
  
   /**
   * Este método es para eliminar un horario através del NRC y dia, mediante una sentencia SQL
   * @param connection Coexión a la BD
   * @return devuelve la cantidad de registros afectados
   */
    @Override
  public int eliminarHorario(Connection connection){
    try {
      PreparedStatement instruccion = connection.prepareStatement("DELETE FROM horario WHERE (select idExperienciaEducativa from experiencia_educativa where nrc = ? and dia = ?)");
      instruccion.setString(1, nrc.get());
      instruccion.setString(2, dia.get());
      return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
    } catch (SQLException ex) {
      Logger.getLogger(Horario.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
    }
  }
  
   /**
   * Este método carga la información de los horarios que hay en la BD através de sentencias SQL.
   * @param connection Conexión a Mysql (BD)
   * @param listaHorario ObservableList de horarios
   */
  public static void llenarInformacionHorario(Connection connection, ObservableList<HorarioSQL>listaHorario){
      try {
        Statement statement = connection.createStatement();
        ResultSet resultado = statement.executeQuery("SELECT b.nrc, h.horaInicio, h.horaFin, h.dia, h.salon "
                + " FROM experiencia_educativa b join horario h "
                + "where h.idExperienciaEducativa and b.idExperienciaEducativa");
        while(resultado.next()){
          listaHorario.add(new HorarioSQL(
              resultado.getString("nrc"),
              resultado.getString("horaInicio"),
              resultado.getString("horaFin"),
              resultado.getString("dia"),
              resultado.getString("salon")
            )
          );
        }
      } catch (SQLException ex) {
        Logger.getLogger(Horario.class.getName()).log(Level.SEVERE, null, ex);
      }
  }        
}
