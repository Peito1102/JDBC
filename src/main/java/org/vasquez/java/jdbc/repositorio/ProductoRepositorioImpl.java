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
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
            sql = "UPDATE productos SET nombre=?, precio=? WHERE id =?";
        } else {
            sql = "INSERT INTO productos(nombre,precio,fecha_registro) VALUES(?,?,?)";
        }
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1,producto.getNombre());
            stmt.setInt(2,producto.getPrecio());
            if (producto.getId() != null && producto.getId() > 0) {
                stmt.setLong(3,producto.getId());
            } else {
                stmt.setDate(3,new Date(producto.getFechaRegistro().getTime()));
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void elmimnar(Long id) {
        try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM productos WHERE id = ?")) {
            stmt.setLong(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
