package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conector {

    private Connection connect;

    public Conector(String url) {
        super();
        connectDB(url);
    }
    
    public ResultSet getReq1DB() {

        String sql = "SELECT sum(cantidad) as total, id_proyecto, pagado, proveedor"+
                    " FROM Compra WHERE pagado = 'No' GROUP BY id_Proyecto HAVING (total) > 50";    
        // String sql = "Escribe aquí la Primera query";    
        return executeQuery(sql);
    }

    public ResultSet getReq2DB() {
        String sql = "SELECT Proyecto.Fecha_Inicio as inicio, Tipo.Financiable, Tipo.Estrato, Proyecto.Porcentaje_Cuota_Inicial * 100 "+
        "FROM Tipo, Proyecto WHERE Proyecto.ID_Tipo = Tipo.ID_Tipo AND Proyecto.Porcentaje_Cuota_Inicial >= 0.3 "+
        "AND Tipo.Estrato >= 5 AND Tipo.Financiable = 0 ORDER BY inicio";
        // String sql = "Escribe aquí la Segunda query";    
        return executeQuery(sql);
    }

    public ResultSet getReq3DB() {
        String sql = "SELECT proyecto.ID_Proyecto, compra.pagado, proyecto.Clasificacion "+
        "FROM materialconstruccion, compra, proyecto WHERE materialconstruccion.Importado = 'Si' "+
        "AND compra.ID_MaterialConstruccion = materialconstruccion.ID_MaterialConstruccion "+
        "AND proyecto.ID_Proyecto = compra.ID_Proyecto AND compra.Pagado IN ('NO', 'Parcialmente') "+
        "AND proyecto.Clasificacion IN ('Apartamento','Apartaestudio') AND proyecto.Acabados = 'No' "+
        "GROUP BY proyecto.ID_Proyecto";    
        // String sql = "Escribe aquí la Tercera query";    
        return executeQuery(sql);
    }

    public Connection connectDB(String url) {
        try {
            connect = DriverManager.getConnection(url, "root", "");
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