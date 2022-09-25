package modulos;

public class Partidos {
    private int id;
    private int nombreLocal;
    private int equipoLocal;
    private int resultadoLocal;
    private int resultadoVisitante;
    private int equipoVisitante;
    private int nombreVisitante;

    public Partidos(int id, int nombreLocal, int equipoLocal, int resultadoLocal, int resultadoVisitante, int equipoVisitante, int nombreVisitante) {
        this.id = id;
        this.nombreLocal = nombreLocal;
        this.equipoLocal = equipoLocal;
        this.resultadoLocal = resultadoLocal;
        this.resultadoVisitante = resultadoVisitante;
        this.equipoVisitante = equipoVisitante;
        this.nombreVisitante = nombreVisitante;
    }

    //Setter and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(int nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public int getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(int equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public int getResultadoLocal() {
        return resultadoLocal;
    }

    public void setResultadoLocal(int resultadoLocal) {
        this.resultadoLocal = resultadoLocal;
    }

    public int getResultadoVisitante() {
        return resultadoVisitante;
    }

    public void setResultadoVisitante(int resultadoVisitante) {
        this.resultadoVisitante = resultadoVisitante;
    }

    public int getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(int equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getNombreVisitante() {
        return nombreVisitante;
    }

    public void setNombreVisitante(int nombreVisitante) {
        this.nombreVisitante = nombreVisitante;
    }
}
