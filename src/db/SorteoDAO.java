package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import datos.Sorteo;

public class SorteoDAO {
    
    // Guardar sorteo
    public static void guardar(Sorteo s) {
        String sql = "INSERT INTO sorteos (fecha, num1, num2, num3, num4, num5, num_extra, " +
                     "total_apostado, pozo_oro, pozo_plata) VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        try (Connection cn = ConexionMySQL.conectarDB();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setTimestamp(1, new java.sql.Timestamp(s.getFecha().getTime()));
            ps.setInt(2, s.getNum1());
            ps.setInt(3, s.getNum2());
            ps.setInt(4, s.getNum3());
            ps.setInt(5, s.getNum4());
            ps.setInt(6, s.getNum5());
            ps.setInt(7, s.getNumExtra());
            ps.setDouble(8, s.getTotalApostado());
            ps.setDouble(9, s.getPozoOro());
            ps.setDouble(10, s.getPozoPlata());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Obtener todos los sorteos
    public static List<Sorteo> obtenerTodos() {
        List<Sorteo> lista = new ArrayList<>();
        String sql = "SELECT * FROM sorteos ORDER BY fecha DESC";
        
        try (Connection cn = ConexionMySQL.conectarDB();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Sorteo s = new Sorteo();
                s.setId(rs.getInt("id"));
                s.setFecha(rs.getTimestamp("fecha"));
                s.setNum1(rs.getInt("num1"));
                s.setNum2(rs.getInt("num2"));
                s.setNum3(rs.getInt("num3"));
                s.setNum4(rs.getInt("num4"));
                s.setNum5(rs.getInt("num5"));
                s.setNumExtra(rs.getInt("num_extra"));
                s.setTotalApostado(rs.getDouble("total_apostado"));
                s.setPozoOro(rs.getDouble("pozo_oro"));
                s.setPozoPlata(rs.getDouble("pozo_plata"));
                lista.add(s);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return lista;
    }
    
    // Obtener último sorteo
    public static Sorteo obtenerUltimo() {
        String sql = "SELECT * FROM sorteos ORDER BY fecha DESC LIMIT 1";
        
        try (Connection cn = ConexionMySQL.conectarDB();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                Sorteo s = new Sorteo();
                s.setId(rs.getInt("id"));
                s.setFecha(rs.getTimestamp("fecha"));
                s.setNum1(rs.getInt("num1"));
                s.setNum2(rs.getInt("num2"));
                s.setNum3(rs.getInt("num3"));
                s.setNum4(rs.getInt("num4"));
                s.setNum5(rs.getInt("num5"));
                s.setNumExtra(rs.getInt("num_extra"));
                s.setTotalApostado(rs.getDouble("total_apostado"));
                s.setPozoOro(rs.getDouble("pozo_oro"));
                s.setPozoPlata(rs.getDouble("pozo_plata"));
                return s;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
