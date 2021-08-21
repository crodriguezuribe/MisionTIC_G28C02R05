package model;

public class Req3 {
    
    private int idProyecto;
    private String pagado;
    private String clasificacion;

    public Req3(){
        super();
    }
    
    public Req3(int idProyecto, String pagado, String clasificacion) {
        this.idProyecto = idProyecto;
        this.pagado = pagado;
        this.clasificacion = clasificacion;
    }
    
    @Override
    public String toString() {
        return "Req3 [clasificacion=" + clasificacion + ", idProyecto=" + idProyecto + ", pagado=" + pagado + "]";
    }
}
