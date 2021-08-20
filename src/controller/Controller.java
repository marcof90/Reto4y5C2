package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Req1;
import model.Req2;
import model.Req3;
import services.Conector;

public class Controller {

    private ArrayList<Req1> req1s;
    private ArrayList<Req2> req2s;
    private ArrayList<Req3> req3s;

    private Conector conectorDB;

    public Controller(String url) {
        super();
        req1s = new ArrayList<>();
        req2s = new ArrayList<>();
        req3s = new ArrayList<>();
        conectorDB = new Conector(url);
    }

    public void req1() {
        ResultSet rs = conectorDB.getReq1DB();        
        try {
            while (rs.next()) {
                Req1 req1 = new Req1(rs.getInt("total"), 
                rs.getInt("id_proyecto"), 
                rs.getString("pagado"), 
                rs.getString("proveedor"));
                req1s.add(req1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public void req2() {
        ResultSet rs = conectorDB.getReq2DB();        
        try {
            while (rs.next()) {
                Req2 req2 = new Req2(rs.getString("inicio"),
                rs.getInt("Financiable"),
                rs.getInt("estrato"),
                rs.getDouble("Proyecto.Porcentaje_Cuota_Inicial * 100"));
                req2s.add(req2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void req3() {
        ResultSet rs = conectorDB.getReq3DB();
        try {
            while (rs.next()) {
                Req3 req3 = new Req3(rs.getInt("ID_Proyecto"), 
                rs.getString("pagado"),
                rs.getString("Clasificacion"));
                req3s.add(req3);
            }
        } catch (SQLException e) {
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
