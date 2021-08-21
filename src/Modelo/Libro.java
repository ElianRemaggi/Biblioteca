package Modelo;

import java.util.ArrayList;

public class Libro {
    private long CodigoISBN;
    private String Nombre;
    private String Autor;
    private ArrayList<Ejemplar>Ejemplares;

    public Libro(long CodigoISBN, String Nombre, String Autor) {
        this.CodigoISBN = CodigoISBN;
        this.Nombre = Nombre;
        this.Autor = Autor;
        this.Ejemplares = new ArrayList<>(); 
    }
    
    
    
    public long getCodigoISBN() {
        return CodigoISBN;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getAutor() {
        return Autor;
    }

    public void setCodigoISBN(long CodigoISBN) {
        this.CodigoISBN = CodigoISBN;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public ArrayList<Ejemplar> getEjemplares() {
        return Ejemplares;
    }
              
    public boolean poseeEjemplares(){
        if(this.Ejemplares.isEmpty()){
            return false;
        }
        return true;
    }
    
    public void agregarEjemplar(Ejemplar ejemplar){
        this.Ejemplares.add(ejemplar);
    }
    
    public Ejemplar quitarEjemplar(Ejemplar ejemplar){
        for(Ejemplar e : this.Ejemplares){
            if(e.equals(ejemplar)){
                this.Ejemplares.remove(e);
                return e;
            }
        }
        return null;
    }   
    
    public void reingresoEjemplar(Ejemplar ejemplar){
        this.Ejemplares.add(ejemplar);
    }
    
    
}
