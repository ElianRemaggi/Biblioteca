package Modelo;

import java.util.Date;

public class Prestamo {
    
    private Ejemplar ejemaplar;
    private Socio socio;
    private Date fechaPrestamo; //caduca a los 5 dias

    public Prestamo(Ejemplar ejemaplar, Socio socio) {
        this.ejemaplar = ejemaplar;
        this.socio = socio;
        Date date = new Date();
        this.fechaPrestamo =  date;
    }

    public Ejemplar getEjemaplar() {
        return ejemaplar;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setEjemaplar(Ejemplar ejemaplar) {
        this.ejemaplar = ejemaplar;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }
    
    
}
