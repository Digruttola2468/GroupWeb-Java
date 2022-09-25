package modulos;

public class Jugadores {
    private int idjugadores;
    private String nombre;
    private String apellido;

    public Jugadores(int idjugadores, String nombre, String apellido) {
        this.idjugadores = idjugadores;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Jugadores(int idjugadores, String nombre) {
        this.idjugadores = idjugadores;
        this.nombre = nombre;
    }

    public int getIdjugadores() {
        return idjugadores;
    }

    public void setIdjugadores(int idjugadores) {
        this.idjugadores = idjugadores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
