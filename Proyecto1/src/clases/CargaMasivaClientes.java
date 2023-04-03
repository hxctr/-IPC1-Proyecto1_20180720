package clases;

public class CargaMasivaClientes {
    private String nombre;
    private int edad;
    private String sexo;
    private int NIT;
    private String avatar;

    public CargaMasivaClientes(String nombre, int edad, String sexo, int NIT, String avatar) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.NIT = NIT;
        this.avatar = avatar;
    }

    public CargaMasivaClientes() {
        nombre = "";
        edad = 0;
        sexo = "";
        NIT = 0;
        avatar = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getNIT() {
        return NIT;
    }

    public void setNIT(int NIT) {
        this.NIT = NIT;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
