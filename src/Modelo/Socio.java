package Modelo;

import java.util.ArrayList;

public class Socio {
    private int NumeroIdentificacion;
    private String Nombre;
    private String Apellido;
    private ArrayList<Ejemplar>Ejemplares;
    private int CantidadMaximaRetiros = 1;

    public Socio(int NumeroIdentificacion, String Nombre, String Apellido) {
        this.NumeroIdentificacion = NumeroIdentificacion;
        this.Nombre = Nombre;
        this.Apellido = Apellido; 
        this.Ejemplares = new ArrayList<>();
    }

    public Socio() {
        //cree este constructor, con el unico fin de obtener un socio para comparar clases
    }

    
    
    public int getNumeroIdentificacion() {
        return NumeroIdentificacion;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setNumeroIdentificacion(int NumeroIdentificacion) {
        this.NumeroIdentificacion = NumeroIdentificacion;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public ArrayList<Ejemplar> getEjemplares() {
        return Ejemplares;
    }

    public int getCantidadMaximaRetiros() {
        return CantidadMaximaRetiros;
    }

    public void setEjemplares(ArrayList<Ejemplar> Ejemplares) {
        this.Ejemplares = Ejemplares;
    }

    public void setCantidadMaximaRetiros(int CantidadMaximaRetiros) {
        this.CantidadMaximaRetiros = CantidadMaximaRetiros;
    }
    
    public void agregarEjemplar(Ejemplar ejemplar){
        this.Ejemplares.add(ejemplar);
    }

    public boolean cupoDisponible(){
        if(this.Ejemplares.size()<this.CantidadMaximaRetiros){
            return true;
        }
        return false;
    }
    
    public Ejemplar devolverEjemplar(Ejemplar prestado){
        if(this.Ejemplares.contains(prestado)){
            this.Ejemplares.remove(prestado);
            return prestado;
        }
        return null;
    }
}
