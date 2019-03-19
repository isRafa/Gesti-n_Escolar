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

public interface EstudianteDAO {
    public int agregarEstudiante(Connection connection);
    public int modificarEstudiante(Connection connection);
    public int eliminarEstudiante(Connection connection);   
}
