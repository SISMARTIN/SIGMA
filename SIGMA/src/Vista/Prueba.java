/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.sql.*;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class Prueba {

    private Connection conexion;

    public Prueba() {

    }

    public boolean connectToAccess(String accessFilePath) {

        //Get connection to database
        System.out.println(accessFilePath);
        try {

//                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
// PRUEBA JOSE.HERRERA VES

            conexion = DriverManager.getConnection("jdbc:ucanaccess://F:\\ACCESS\\Trimasa.accdb" );

        } catch (Exception ex) {

            ex.printStackTrace();

            return false;

        }

        return true;

    }

    public Vector ejecutarQuery(String sql) {

        Vector rows = new Vector();

        try {

            Statement stmt = conexion.createStatement();

            stmt.executeQuery(sql);//muestra resultados equivalentes en SQL  a utilizar SELECT

            ResultSet rs = stmt.getResultSet(); //obtiene el resultado de la consulta y lo guarda en rs

             
            if (rs != null) {

                while (rs.next()) {
                     System.out.println(rs.getString(1));
                    Vector ctemp = new Vector();

                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)//for cuetna las columnas en un registro
                    {

                        ctemp.add(rs.getString(i));

                        System.out.println(ctemp.get(i - 1));

                    }

                    rows.add(ctemp);//añado ese registro a una fila

                }

                rs.close();

                stmt.close();

                return rows;

            } else {

                System.out.println("No hay datos");

            }

        } catch (SQLException e) {
            System.out.println("Hubo un error");
        };

        return null;

    }

    public void cerrarConexion() {

        try {

            this.conexion.close();

        } catch (SQLException e) {
        };

    }

}
