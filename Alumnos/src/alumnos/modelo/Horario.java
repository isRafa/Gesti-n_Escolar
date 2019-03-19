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

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Horario {    
    
  protected StringProperty dia;
  protected StringProperty horaInicio;
  protected StringProperty horaFin;
  protected StringProperty salon;
  protected StringProperty nrc;
  
    public Horario(String nrc, String horaInicio, String horaFin, String dia, String salon) {
        this.nrc = new SimpleStringProperty(nrc);        
        this.horaInicio = new SimpleStringProperty(horaInicio);
        this.horaFin = new SimpleStringProperty(horaFin);
        this.dia = new SimpleStringProperty (dia);
        this.salon = new SimpleStringProperty(salon);        
    }
    
    public String getDia() {
        return dia.get();
    }

    public void setDia(String dia) {
        this.dia = new SimpleStringProperty (dia);
    }

    public String getHoraInicio() {
        return horaInicio.get();
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = new SimpleStringProperty(horaInicio);
    }

    public String getHoraFin() {
        return horaFin.get();
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = new SimpleStringProperty(horaFin);
    }

    public String getSalon() {
        return salon.get();
    }

    public void setSalon(String salon) {
        this.salon = new SimpleStringProperty(salon);
    }

    public String getNrc() {
        return nrc.get();
    }

    public void setNrc(String nrc) {
        this.nrc = new SimpleStringProperty(nrc);
    }
 
}

