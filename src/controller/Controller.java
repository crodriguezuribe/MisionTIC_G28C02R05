package controller;

import java.util.*;
import java.sql.*;
import model.*;
import services.*;

public class Controller{

    private ArrayList<Req1> req1s;
    private ArrayList<Req2> req2s;
    private ArrayList<Req3> req3s;

    private ArrayList<String> req1s5;
    private ArrayList<String> req2s5;
    private ArrayList<String> req3s5;
    private Conector conectorDB;
    
    public Controller() {
        super();
    }

    public Controller(String url) {
        super();
        this.req1s = new ArrayList<>();
        this.req2s = new ArrayList<>();
        this.req3s = new ArrayList<>();
        
        this.req1s5 = new ArrayList<>();
        this.req2s5 = new ArrayList<>();
        this.req3s5 = new ArrayList<>();
        
        this.conectorDB = new Conector(url);
        // checkConnect(); // Revisa conexión a SQL
    }

/*****************************************************************************
 * Revisa conexión a SQL
 *****************************************************************************/
    public void checkConnect() {
        try {
            if (conectorDB.getConnect().isValid(300)) {
                System.out.println("*******************************\n"
                                + "*  Conectado al servidor SQL  *\n" 
                                + "*******************************\n");
                /*System.out.println("");*/
            }
        } catch (Exception e) {
            System.out.println("*******************************\n"
                            + "*..Conéctate al servidor SQL..*\n" 
                            + "*******************************\n");
            e.printStackTrace();
        }
    }

/*****************************************************************************
 * Reto 04
 *****************************************************************************/
    public ArrayList<Req1> getReq1s() {
        return req1s;
    }

    public ArrayList<Req2> getReq2s() {
        return req2s;
    }

    public ArrayList<Req3> getReq3s() {
        return req3s;
    }

    public void req1() {
        ResultSet resultado1 = conectorDB.getReq1DB();

        try {
            while (resultado1.next()) {
                Req1 requeriemiento1 = new Req1(resultado1.getInt("total"),
                    resultado1.getInt("ID_Proyecto"),
                    resultado1.getString("pagado"),
                    resultado1.getString("Proveedor"));
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
                Req2 requerimiento2 = new Req2(resultado2.getString("Inicio"),
                    resultado2.getInt("financiable"),
                    resultado2.getInt("estrato"),
                    resultado2.getDouble("Proyecto.Porcentaje_Cuota_Inicial * 100"));
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
                Req3 requerimiento3 = new Req3(resultado3.getInt("idProyecto"),
                    resultado3.getString("pagado"),
                    resultado3.getString("clasificacion"));
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

/*****************************************************************************
 * Reto 05
 *****************************************************************************/
    public void req15() {
        req1s5.clear();
        ResultSet rs = conectorDB.getReq1DB();        
        try {
            while (rs.next()) {
                Req1 req1 = new Req1(rs.getInt("total"), 
                    rs.getInt("ID_Proyecto"), 
                    rs.getString("pagado"), 
                    rs.getString("proveedor"));
                req1s5.add(req1.toString());
            }
        } catch (SQLException e) {
            System.out.println("\n***************** Error req15\n");
            e.printStackTrace();
        } 
    }

    public void req25() {
        req2s5.clear();
        ResultSet rs = conectorDB.getReq2DB();      
        try {
            while(rs.next()){
                Req2 req2 = new Req2(rs.getString("Inicio"),
                    rs.getInt("financiable"),
                    rs.getInt("estrato"),
                    rs.getDouble("Proyecto.Porcentaje_Cuota_Inicial * 100"));
                req2s5.add(req2.toString());
            }
        } catch (SQLException e) {
            System.out.println("\n***************** Error req25\n");
            e.printStackTrace();
        }
    }

    public void req35() {
        req3s5.clear();
        ResultSet rs = conectorDB.getReq3DB();      
        try {
            while(rs.next()){
                Req3 req3 = new Req3(rs.getInt("idProyecto"),
                    rs.getString("pagado"),
                    rs.getString("clasificacion"));
                req3s5.add(req3.toString()); 
            }
        } catch (SQLException e) {
            System.out.println("\n***************** Error req35\n");
            e.printStackTrace();
        }
    }

    public ArrayList<String> getReq1s5() {
        return req1s5;
    }

    public void setReq1s5(ArrayList<String> req1s5) {
        this.req1s5 = req1s5;
    }

    public ArrayList<String> getReq2s5() {
        return req2s5;
    }

    public void setReq2s5(ArrayList<String> req2s5) {
        this.req2s5 = req2s5;
    }

    public ArrayList<String> getReq3s5() {
        return req3s5;
    }

    public void setReq3s5(ArrayList<String> req3s5) {
        this.req3s5 = req3s5;
    }
}
