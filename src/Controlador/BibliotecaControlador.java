package Controlador;

import Modelo.*;
import Vista.*;
import Modelo.Socio;
import Modelo.SocioVIP;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BibliotecaControlador implements ActionListener {

    //vistas
    private InicioVista inicioVista;
    private LibrosVista librosVista;
    private SociosVista sociosVista;
    private PrestamosVista prestamosVista;

    //Modelos
    private ArrayList<Libro> Libros;
    private ArrayList<Socio> socios;
    private ArrayList<Prestamo> prestamos;

    //contador
    private int contadorSocios;

    public BibliotecaControlador(InicioVista inicioVista, LibrosVista librosVista, SociosVista sociosVista, PrestamosVista prestamosVista) {
        //Vistas
        this.inicioVista = inicioVista;
        this.inicioVista.btnLibros.addActionListener(this);
        this.inicioVista.btnSocios.addActionListener(this);
        this.inicioVista.btnPrestamos.addActionListener(this);

        this.librosVista = librosVista;
        this.librosVista.btnAgregarLibro.addActionListener(this);
        this.librosVista.cbLibros.addActionListener(this);
        this.librosVista.btnAgregarEjemplar.addActionListener(this);

        this.sociosVista = sociosVista;
        this.sociosVista.btnAgregarSocio.addActionListener(this);

        this.prestamosVista = prestamosVista;
        this.prestamosVista.btnAprobarPrestamo.addActionListener(this);
        this.prestamosVista.btnDevolver.addActionListener(this);

        //Modelos
        this.Libros = new ArrayList<>();
        this.socios = new ArrayList<>();
        this.prestamos = new ArrayList<>();

        //contador
        this.contadorSocios = 0;

    }

    public void iniciarInicioVista(InicioVista inicioVista) {
        this.inicioVista.setTitle("Biblioteca");
        this.inicioVista.setLocationRelativeTo(null);
        this.inicioVista.setVisible(true);
    }

    public void iniciarLibrosVista(LibrosVista librosVista) {
        this.librosVista.setTitle("Libros");
        this.librosVista.setLocationRelativeTo(null);
        this.librosVista.cbLibros.removeAllItems();
        this.librosVista.setVisible(true);
    }

    public void iniciarSociosVista(SociosVista sociosVista) {
        this.sociosVista.setTitle("Socios");
        this.sociosVista.setLocationRelativeTo(null);
        this.sociosVista.setVisible(true);
    }

    public void iniciarPrestamosVista(PrestamosVista prestamosVista) {
        this.prestamosVista.setTitle("Prestamos");
        this.prestamosVista.setLocationRelativeTo(null);
        this.prestamosVista.setVisible(true);
        this.prestamosVista.cbDevolucion.removeAllItems();
    }

    //Escuchas de acciones 
    public void actionPerformed(ActionEvent e) {

        //inicio 
        if (e.getSource() == inicioVista.btnLibros) {
            this.iniciarLibrosVista(librosVista);
        }
        if (e.getSource() == inicioVista.btnSocios) {
            this.iniciarSociosVista(sociosVista);
        }
        if (e.getSource() == inicioVista.btnPrestamos) {
            this.iniciarPrestamosVista(prestamosVista);
            this.cargarComboboxPrestamo();
        }

        //libros
        if (e.getSource() == librosVista.btnAgregarLibro) {

            if (!isNumeric(this.librosVista.tfCodigoISBN.getText())) {
                JOptionPane.showMessageDialog(librosVista, "EL codigo ISBN solo puede estar compuesto por numeros");
                return;
            }

            Libro libro = new Libro(Long.parseLong(this.librosVista.tfCodigoISBN.getText()), this.librosVista.tfTitulo.getText(), this.librosVista.tfAutor.getText());
            this.Libros.add(libro);
            JOptionPane.showMessageDialog(inicioVista, "Libro ingresado correctamente");
            this.librosVista.cbLibros.addItem(libro.getNombre() + " - " + libro.getAutor());

        }

        //Ejemplares
        if (e.getSource() == this.librosVista.btnAgregarEjemplar) {

            if (!this.isNumeric(this.librosVista.tfNumeroEdicion.getText()) || this.librosVista.tfNumeroEdicion.getText().equals("")) {
                JOptionPane.showMessageDialog(inicioVista, "El numero de edicion, solo puede contener numeros");
                return;
            }

            if (this.librosVista.tfUbicacion.getText().equals("") && !this.librosVista.tfNumeroEdicion.getText().equals("") && this.librosVista.cbLibros.getSelectedIndex() != -1) {
                JOptionPane.showMessageDialog(inicioVista, "Alguno de los campos de carga de ejemplar esta mal ingresado o falta ingresar");
            } else {
                Ejemplar ejemplar = new Ejemplar(this.Libros.get(this.librosVista.cbLibros.getSelectedIndex()), Integer.parseInt(this.librosVista.tfNumeroEdicion.getText()), this.librosVista.tfUbicacion.getText());
                this.Libros.get(this.librosVista.cbLibros.getSelectedIndex()).agregarEjemplar(ejemplar);
                JOptionPane.showMessageDialog(inicioVista, "Ejemplar agregado correctamente");
            }

        }

        if (e.getSource() == this.sociosVista.btnAgregarSocio) {
            if (this.sociosVista.tfApellido.getText().equals("") || this.sociosVista.tfNombre.getText().equals("")) {
                JOptionPane.showMessageDialog(inicioVista, "Alguno de los campos se encuntra sin completar");
                return;
            }

            if (this.sociosVista.rbVIP.isSelected()) {
                SocioVIP socioVip = new SocioVIP(1200, this.contadorSocios, this.sociosVista.tfNombre.getText(), this.sociosVista.tfApellido.getText());
                this.contadorSocios++;
                this.socios.add(socioVip);
                JOptionPane.showMessageDialog(sociosVista,"Socio Vip agregado con exito");

            } else {
                Socio socio = new Socio(contadorSocios, this.sociosVista.tfNombre.getText(), this.sociosVista.tfApellido.getText());
                this.contadorSocios++;
                this.socios.add(socio);
                JOptionPane.showMessageDialog(sociosVista,"Socio agregado con exito");
            }
        }

        if (e.getSource() == this.prestamosVista.btnAprobarPrestamo) {

            if (this.prestamosVista.cbEjemplares.getSelectedIndex() == -1 || this.prestamosVista.cbSocios.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(inicioVista, "Algun campo no se encuentra seleccionado");
                return;
            }

            String textoClienteSeleccionado = this.prestamosVista.cbSocios.getSelectedItem().toString();
            String textoLibroSeleccionado = this.prestamosVista.cbEjemplares.getSelectedItem().toString();
            String[] arrayClienteSeleccionado = textoClienteSeleccionado.split("-");
            String[] arrayLibroSeleccionado = textoLibroSeleccionado.split("-");

            int idClienteSeleccionado = Integer.parseInt(arrayClienteSeleccionado[0]);
            int idLibroSeleccionado[] = {Integer.parseInt(arrayLibroSeleccionado[0]), Integer.parseInt(arrayLibroSeleccionado[1])};

            Ejemplar ejemplarSeleccionado = new Ejemplar(this.Libros.get(idLibroSeleccionado[0]), this.Libros.get(idLibroSeleccionado[0]).getEjemplares().get(idLibroSeleccionado[1]).getNumeroEdicion(), this.Libros.get(idLibroSeleccionado[0]).getEjemplares().get(idLibroSeleccionado[1]).getUbicacion());
            Ejemplar ej = this.Libros.get(idLibroSeleccionado[0]).getEjemplares().get(idLibroSeleccionado[1]);
            if (this.socios.get(idClienteSeleccionado).cupoDisponible()) {
                Prestamo prestamo = new Prestamo(ejemplarSeleccionado, this.socios.get(idClienteSeleccionado));
                this.socios.get(idClienteSeleccionado).agregarEjemplar(ejemplarSeleccionado);
                this.Libros.get(idLibroSeleccionado[0]).quitarEjemplar(ej);
                this.prestamos.add(prestamo);
                JOptionPane.showMessageDialog(inicioVista, "Prestamo generado");

            DefaultTableModel modelo = (DefaultTableModel) this.prestamosVista.tablaPrestamos.getModel();
            String [] fila = {""+idClienteSeleccionado , this.socios.get(idClienteSeleccionado).getApellido()+ " " + this.socios.get(idClienteSeleccionado).getNombre(), this.Libros.get(idLibroSeleccionado[0]).getNombre(), prestamo.getFechaPrestamo().toString() };
            modelo.addRow(fila);
            
            cargarComboboxPrestamo();
            this.prestamosVista.cbDevolucion.addItem(idClienteSeleccionado+"- "+this.socios.get(idClienteSeleccionado).getApellido()+ " " + this.socios.get(idClienteSeleccionado).getNombre()+" -"+this.Libros.get(idLibroSeleccionado[0]).getNombre() +" -"+prestamo.getFechaPrestamo().toString());
            
            //Fin listeners
        }else{
                JOptionPane.showMessageDialog(inicioVista, "El socio no posee cupos disponibles");
            }
    }
       if(e.getSource() == this.prestamosVista.btnDevolver){
           
           String textoDevolucionSeleccionada = this.prestamosVista.cbDevolucion.getSelectedItem().toString();
           String[] arrayDevolucionSeleccionada = textoDevolucionSeleccionada.split("-");
           int idClienteSeleccionado = Integer.parseInt(arrayDevolucionSeleccionada[0]);
           String fechaPrestamo = arrayDevolucionSeleccionada[3].toString();
           boolean escape = false;
           for(Prestamo p : this.prestamos){
               if(p.getSocio().getNumeroIdentificacion()==idClienteSeleccionado && p.getFechaPrestamo().toString().equals(fechaPrestamo)){
                   Prestamo prestamoDevolver = p;
                   this.prestamos.remove(prestamoDevolver);
                   Ejemplar ejemplarDevolver = this.socios.get(idClienteSeleccionado).devolverEjemplar(p.getEjemaplar());
                   
                   for(Libro l : this.Libros){
                       if(l.getCodigoISBN() == prestamoDevolver.getEjemaplar().getLibro().getCodigoISBN()){
                           l.agregarEjemplar(ejemplarDevolver);
                           JOptionPane.showMessageDialog(inicioVista, "El ejemplar se agrego a la biblioteca");
                           escape=true;
                           break;
                       }
                   }
                   
               }
               if(escape==true){
                   break;
               }
           }
           
           this.prestamosVista.cbDevolucion.removeItemAt(this.prestamosVista.cbDevolucion.getSelectedIndex());
           this.cargarComboboxPrestamo();
           
       }
    }
        //Funciones auxiliares
    public static boolean isNumeric(String StringNumerico) {
        try {
            Long.parseLong(StringNumerico);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    
    public void cargarComboboxPrestamo() {
        this.prestamosVista.cbSocios.removeAllItems();
        this.prestamosVista.cbEjemplares.removeAllItems();
        
        for (Socio s : this.socios) {
            this.prestamosVista.cbSocios.addItem(s.getNumeroIdentificacion() + "- " + s.getNombre() + " " + s.getApellido());
        }
        int indexL = 0;
        for (Libro l : this.Libros) {
            int indexej = 0;
            for (Ejemplar ej : l.getEjemplares()) {
                this.prestamosVista.cbEjemplares.addItem(indexL + "-" + indexej + "- " + ej.getLibro().getNombre() + " - " + ej.getLibro().getAutor() + " ubicacion " + ej.getUbicacion());
                indexej++;
            }
            indexL++;
        }
        
    }
    
    public static void eliminarRegistros(final DefaultTableModel modelo) {
        for (int i = modelo.getRowCount() - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }
}
