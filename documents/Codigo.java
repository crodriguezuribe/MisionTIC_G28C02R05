import java.util.*;
import java.sql.*;

public class Req1 {

    private int total;
    private int idProyecto;
    private String pagado;
    private String proveedor;

    public Req1() {
        super();
    }

    public Req1(int total, int idProyecto, String pagado, String proveedor) {
        this.total = total;
        this.idProyecto = idProyecto;
        this.pagado = pagado;
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "Req1 [idProyecto=" + idProyecto + ", pagado=" + pagado + ", proveedor=" + proveedor + ", total=" + total
                + "]";
    }
}

public class Req2 {
    
    private String fechaInicio;
    private int financiable;
    private int estrato;
    private double porcetaje;

    public Req2() {
        super();
    }

    public Req2(String fechaInicio, int financiable, int estrato, double porcetaje) {
        this.fechaInicio = fechaInicio;
        this.financiable = financiable;
        this.estrato = estrato;
        this.porcetaje = porcetaje;
    }

    @Override
    public String toString() {
        return "Req2 [estrato=" + estrato + ", fechaInicio=" + fechaInicio + ", financiable=" + financiable
                + ", porcetaje=" + porcetaje + "]";
    }

}

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

public class Conector {

    private Connection connect;

    public Conector(String url) {
        super();
        connectDB(url);
    }

    public ResultSet getReq1DB() {
        // String sql = "SELECT ID_Proyecto as idProyecto, Pagado as pagado, Proveedor as proveedor, sum(cantidad) as total FROM Compra WHERE Pagado = 'No' GROUP BY ID_Proyecto HAVING total > 50;";
        String sql = "SELECT ID_Proyecto, Pagado, Proveedor, sum(cantidad) as total FROM Compra WHERE Pagado = 'No' GROUP BY ID_Proyecto HAVING total > 50;";
        return executeQuery(sql);
    }

    public ResultSet getReq2DB() {
        // String sql = "SELECT Tipo.Estrato as estrato, Proyecto.Fecha_Inicio as fechaInicio, Tipo.Financiable as financiable, Proyecto.Porcentaje_Cuota_Inicial * 100 AS porcentaje FROM Tipo, Proyecto WHERE Proyecto.ID_Tipo = Tipo.ID_Tipo AND Proyecto.Porcentaje_Cuota_Inicial > 0.2 AND Tipo.Estrato >= 5 AND Tipo.Financiable = 0 ORDER BY FechaInicio;";
        String sql = "SELECT Tipo.Estrato, Proyecto.Fecha_Inicio AS inicio, Tipo.Financiable, Proyecto.Porcentaje_Cuota_Inicial * 100 FROM Tipo, Proyecto WHERE Proyecto.ID_Tipo = Tipo.ID_Tipo AND Proyecto.Porcentaje_Cuota_Inicial >= 0.3 AND Tipo.Estrato >= 5 AND Tipo.Financiable = 0 ORDER BY inicio;";
        return executeQuery(sql);
    }

    public ResultSet getReq3DB() {
        // String sql = "SELECT Proyecto.Clasificacion AS clasificacion, Proyecto.ID_Proyecto AS idProyecto, Compra.pagado AS pagado FROM MaterialConstruccion, Compra, Proyecto WHERE MaterialConstruccion.Importado = 'Si' AND Compra.ID_MaterialConstruccion = MaterialConstruccion.ID_MaterialConstruccion AND Proyecto.ID_Proyecto = Compra.ID_Proyecto AND Compra.Pagado IN ('Parcialmente','No') AND Proyecto.Clasificacion IN ('Apartamento','Apartaestudio') AND Proyecto.Acabados = 'No' GROUP BY Proyecto.ID_Proyecto;";
        // String sql = "SELECT Proyecto.Clasificacion, Proyecto.ID_Proyecto, Compra.pagado FROM MaterialConstruccion, Compra, Proyecto WHERE MaterialConstruccion.Importado = 'Si' AND Compra.ID_MaterialConstruccion = MaterialConstruccion.ID_MaterialConstruccion AND Proyecto.ID_Proyecto = Compra.ID_Proyecto AND Compra.Pagado IN ('Parcialmente','No') AND Proyecto.Clasificacion IN ('Apartamento','Apartaestudio') AND Proyecto.Acabados = 'No' GROUP BY Proyecto.ID_Proyecto;";
        String sql = "SELECT Proyecto.Clasificacion, Proyecto.ID_Proyecto as idProyecto, Compra.pagado FROM MaterialConstruccion, Compra, Proyecto WHERE MaterialConstruccion.Importado = 'Si' AND Compra.ID_MaterialConstruccion = MaterialConstruccion.ID_MaterialConstruccion AND Proyecto.ID_Proyecto = Compra.ID_Proyecto AND Compra.Pagado IN ('Parcialmente','No') AND Proyecto.Clasificacion IN ('Apartamento','Apartaestudio') AND Proyecto.Acabados = 'No' GROUP BY idProyecto;";
        return executeQuery(sql);
    }
    
    public Connection getConnect() {
        return connect;
    }

    public Connection connectDB(String url) {
        try {
            connect = DriverManager.getConnection(url, "root", "");
            boolean isValid = connect.isValid(500);
            /* System.out.println(isValid ? "*******************************\n"
            + "***Conectado al servidor SQL***\n" 
            + "**************_****************\n" : ""
            + "*******************************\n"
            + "...Conéctate al servidor SQL...\n" 
            + "*******************************\n"); */
            /* System.out.println(isValid ? "SQL Connected" : "Connection Failed");*/
            /* System.out.println(isValid ? "" : "SQL Connection Failed");*/
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connect;
    }

    public ResultSet executeQuery(String sql) {
        
        ResultSet rs = null;
        
        try {
            Statement stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}

public class Controller {

    private ArrayList<Req1> req1s;
    private ArrayList<Req2> req2s;
    private ArrayList<Req3> req3s;
    private Conector conectorDB;
    
    public Controller() {
        super();
    }

    public Controller(String url) {
        super();
        this.req1s = new ArrayList<>();
        this.req2s = new ArrayList<>();
        this.req3s = new ArrayList<>();        
        this.conectorDB = new Conector(url);
        checkConnect();
    }

    public void checkConnect() {
        try {
            if (conectorDB.getConnect().isValid(300)) {
                /* System.out.println("*******************************\n"
                                + "***Conectado al servidor SQL***\n" 
                                + "*******************************\n"); */
                /*System.out.println("");*/
            }
        } catch (Exception e) {
            System.out.println("*******************************\n"
                            + "...Conéctate al servidor SQL...\n" 
                            + "*******************************\n");
            e.printStackTrace();
        }
    }

    public void req1() {
        ResultSet resultado1 = conectorDB.getReq1DB();

        try {
            while (resultado1.next()) {
                Req1 requeriemiento1 = new Req1(resultado1.getInt("total"), resultado1.getInt("ID_Proyecto"), resultado1.getString("pagado"), resultado1.getString("Proveedor"));
                req1s.add(requeriemiento1);
            }
        } catch (SQLException e) {
            System.out.println("\n***************** Error req1\n");
            e.printStackTrace();
        }
    }

    public void req2() {
        ResultSet resultado2 = conectorDB.getReq2DB();

        try{
            while (resultado2.next()) {
                Req2 requerimiento2 = new Req2(resultado2.getString("Inicio"), resultado2.getInt("financiable"), resultado2.getInt("estrato"), resultado2.getDouble("Proyecto.Porcentaje_Cuota_Inicial * 100"));
                req2s.add(requerimiento2);
                
            }
        } catch (SQLException e){
            System.out.println("\n***************** Error req2\n");
            e.printStackTrace();
        }
    }

    public void req3() {
        ResultSet resultado3 = conectorDB.getReq3DB();
        try{
            while (resultado3.next()) {
                Req3 requerimiento3 = new Req3(resultado3.getInt("idProyecto"), resultado3.getString("pagado"), resultado3.getString("clasificacion"));
                req3s.add(requerimiento3);
                
            }
        } catch (SQLException e){
            System.out.println("\n***************** Error req3\n");
            e.printStackTrace();
        }
    }

    public void printReq1() {
        for (Req1 req1 : req1s) {
                System.out.println(req1);
        }
    }

    public void printReq2() {
        for (Req2 req2 : req2s) {
            System.out.println(req2);
        }
    }

    public void printReq3() {
        for (Req3 req3 : req3s) {
            System.out.println(req3);
        }
    }
}
