package clases;

public class CargaMasivaVentas {
    private String codigoVenta;
    private int NITDelCliente;
    private String nombreDelProducto;
    private int cantidadComprada;

    public CargaMasivaVentas(String codigoVenta, int NITDelCliente, String nombreDelProducto, int cantidadComprada) {
        this.codigoVenta = codigoVenta;
        this.NITDelCliente = NITDelCliente;
        this.nombreDelProducto = nombreDelProducto;
        this.cantidadComprada = cantidadComprada;
    }

    public CargaMasivaVentas(){
        codigoVenta = "";
        NITDelCliente = 0;
        nombreDelProducto = "";
        cantidadComprada = 0;
    }

    public String getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(String codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public int getNITDelCliente() {
        return NITDelCliente;
    }

    public void setNITDelCliente(int NITDelCliente) {
        this.NITDelCliente = NITDelCliente;
    }

    public String getNombreDelProducto() {
        return nombreDelProducto;
    }

    public void setNombreDelProducto(String nombreDelProducto) {
        this.nombreDelProducto = nombreDelProducto;
    }

    public int getCantidadComprada() {
        return cantidadComprada;
    }

    public void setCantidadComprada(int cantidadComprada) {
        this.cantidadComprada = cantidadComprada;
    }
}
