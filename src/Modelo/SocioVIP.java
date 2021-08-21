package Modelo;

public class SocioVIP extends Socio{
    private float cuotaMensual;

    public SocioVIP(float cuotaMensual, int NumeroIdentificacion, String Nombre, String Apellido) {
        super(NumeroIdentificacion, Nombre, Apellido);
        this.cuotaMensual = cuotaMensual;
        this.setCantidadMaximaRetiros(3);
    }

    public float getCuotaMensual() {
        return cuotaMensual;
    }

    public void setCuotaMensual(float cuotaMensual) {
        this.cuotaMensual = cuotaMensual;
    }
    
    
}
