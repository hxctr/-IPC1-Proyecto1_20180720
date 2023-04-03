package clases;

public class CargaMasivaProductos {
    private String nombre;
    private double precio;
    private int cantidad;
    private String imagen;

    public CargaMasivaProductos(String nombre, double precio, int cantidad, String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagen = imagen;
    }

    public CargaMasivaProductos() {
        nombre = "";
        precio = 0;
        cantidad = 0;
        imagen = "imagen";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
