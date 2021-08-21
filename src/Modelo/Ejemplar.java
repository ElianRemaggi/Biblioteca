package Modelo;

public class Ejemplar {
    private Libro libro;
    private int NumeroEdicion;
    private String Ubicacion;

    public Ejemplar(Libro libro, int NumeroEdicion, String Ubicacion) {
        this.libro = libro;
        this.NumeroEdicion = NumeroEdicion;
        this.Ubicacion = Ubicacion;
    }

    public Libro getLibro() {
        return libro;
    }

    public int getNumeroEdicion() {
        return NumeroEdicion;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void setNumeroEdicion(int NumeroEdicion) {
        this.NumeroEdicion = NumeroEdicion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }
    
}
