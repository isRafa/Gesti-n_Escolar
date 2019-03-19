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

public interface ExperienciaEducativaDAO {
    public int agregarExperienciaEducativa(Connection connection);
    public int modificarExperienciaEducativa(Connection connection);
    public int eliminarExperienciaEducativa(Connection connection);    
}
