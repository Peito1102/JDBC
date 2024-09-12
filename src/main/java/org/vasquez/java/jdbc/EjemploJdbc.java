package org.vasquez.java.jdbc;

import org.vasquez.java.jdbc.util.ConexionBD;

import java.sql.*;

public class EjemploJdbc {
    public static void main(String[] args) {

        try(Connection conn = ConexionBD.getInstance();
            Statement stmt = conn.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM productos")) {
            System.out.println("id | nombre | precio | fecha");
            while (resultado.next()) {
                System.out.print(resultado.getInt(1) + " ");
                System.out.print(resultado.getString(2) + " ");
                System.out.print(resultado.getInt(3) + " ");
                System.out.println(resultado.getDate(4));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
