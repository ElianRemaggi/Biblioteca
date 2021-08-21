package biblioteca;
import Modelo.*;
import Vista.*;
import Controlador.BibliotecaControlador;

public class Biblioteca {

    private InicioVista inicioVista;
    private LibrosVista librosVista;
    private SociosVista sociosVista;
    private PrestamosVista prestamosVista;
    
    public static void main(String[] args) {
        
    InicioVista inicioVista = new InicioVista();
    LibrosVista librosVista = new LibrosVista();
    SociosVista sociosVista = new SociosVista();
    PrestamosVista prestamosVista = new PrestamosVista();
    BibliotecaControlador bibliotecaControlado = new BibliotecaControlador(inicioVista, librosVista, sociosVista, prestamosVista);
    bibliotecaControlado.iniciarInicioVista(inicioVista);
    
    
        
    }
    
}
