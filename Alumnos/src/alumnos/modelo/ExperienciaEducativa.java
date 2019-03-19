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

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class ExperienciaEducativa {  
    
protected StringProperty nrc;
protected StringProperty nombreEE;
protected IntegerProperty creditos;
protected IntegerProperty horasTeoricas;
protected IntegerProperty horasPracticas;

public ExperienciaEducativa(String nrc, String nombreEE, int creditos, int horasTeoricas, int horasPracticas) {
    this.nrc = new SimpleStringProperty(nrc);
    this.nombreEE = new SimpleStringProperty(nombreEE);
    this.creditos = new SimpleIntegerProperty(creditos);
    this.horasTeoricas = new SimpleIntegerProperty(horasTeoricas);
    this.horasPracticas = new SimpleIntegerProperty(horasPracticas);
}

public String getNrc() {
        return nrc.get();
    }

    public void setNrc(String nrc) {
        this.nrc = new SimpleStringProperty(nrc);
    }

    public String getNombreEE() {
        return nombreEE.get();
    }

    public void setNombreEE(String nombreEE) {
        this.nombreEE = new SimpleStringProperty(nombreEE);
    }

    public int getCreditos() {
        return creditos.get();
    }

    public void setCreditos(int creditos) {
        this.creditos = new SimpleIntegerProperty(creditos);
    }

    public int getHorasTeoricas() {
        return horasTeoricas.get();
    }

    public void setHorasTeoricas(int horasTeoricas) {
        this.horasTeoricas = new SimpleIntegerProperty(horasTeoricas);
    }

    public int getHorasPracticas() {
        return horasPracticas.get();
    }

    public void setHorasPracticas(int horasPracticas) {
        this.horasPracticas = new SimpleIntegerProperty(horasPracticas);
    }

}
