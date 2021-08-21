package services;

import java.sql.*;

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
            System.out.println(isValid ? "*******************************\n"
            + "***Conectado al servidor SQL***\n" 
            + "*******************************\n" : ""
            + "*******************************\n"
            + "...Conéctate al servidor SQL...\n" 
            + "*******************************\n");
            /* System.out.println(isValid ? "SQL Connected" : "Connection Failed");*/
            /* System.out.println(isValid ? "" : "SQL Connection Failed"); */
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
