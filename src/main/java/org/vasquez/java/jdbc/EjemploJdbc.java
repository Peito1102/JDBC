package org.vasquez.java.jdbc;

import org.vasquez.java.jdbc.modelo.Producto;
import org.vasquez.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.vasquez.java.jdbc.repositorio.Repositorio;
import org.vasquez.java.jdbc.util.ConexionBD;

import java.sql.*;

public class EjemploJdbc {
    public static void main(String[] args) {

        try(Connection conn = ConexionBD.getInstance()) {
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println("id | nombre | precio | fecha");
            repositorio.listar().forEach(System.out::println);
            System.out.println();

            System.out.println(repositorio.porId(2L));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
