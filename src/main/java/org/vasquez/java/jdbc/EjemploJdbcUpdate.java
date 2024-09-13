package org.vasquez.java.jdbc;

import org.vasquez.java.jdbc.modelo.Producto;
import org.vasquez.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.vasquez.java.jdbc.repositorio.Repositorio;
import org.vasquez.java.jdbc.util.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbcUpdate {
    public static void main(String[] args) {

        try(Connection conn = ConexionBD.getInstance()) {
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println("=======LISTAR=======");
            System.out.println("id | nombre | precio | fecha");
            repositorio.listar().forEach(System.out::println);
            System.out.println();

            System.out.println("=======OBTENER POR ID=======");
            System.out.println(repositorio.porId(2L));
            System.out.println();

            System.out.println("=======EDITAR NUEVO PRODUCTO=======");
            Producto producto = new Producto(3L,"Teclado Razer mecánico",700,null);
            repositorio.guardar(producto);
            System.out.println("Producto actualizado con éxito.");
            System.out.println("id | nombre | precio | fecha");
            repositorio.listar().forEach(System.out::println);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
