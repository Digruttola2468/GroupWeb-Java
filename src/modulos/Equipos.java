package modulos;

public class Equipos {
    private int idequipo;
    private String nombreEquipo;

    public Equipos(int idequipo, String nombreEquipo) {
        this.idequipo = idequipo;
        this.nombreEquipo = nombreEquipo;
    }

    public int getIdequipo() {
        return idequipo;
    }

    public void setIdequipo(int idequipo) {
        this.idequipo = idequipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }
}
