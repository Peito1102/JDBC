package org.vasquez.java.jdbc.repositorio;

import org.vasquez.java.jdbc.modelo.Producto;
import org.vasquez.java.jdbc.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<Producto> {
    private Connection getConnection() throws SQLException {
        return ConexionBD.getInstance();
    }
    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();
        try(Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM productos")) {
            while (rs.next()) {
                Producto p = new Producto(rs.getLong(1),rs.getString(2),rs.getInt(3),rs.getDate(4));
                productos.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productos;
    }

    @Override
    public Producto porId(Long id) {
        Producto producto = null;
        try(PreparedStatement stmt = getConnection()
                .prepareStatement("SELECT * FROM productos WHERE id = ?")) {
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                producto = new Producto(rs.getLong(1),rs.getString(2),rs.getInt(3),rs.getDate(4));;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producto;
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void elmimnar(Long id) {

    }
}
