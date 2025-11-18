package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.DatoOro;

public class ApuestaDAO {

    // INSERT
    public static void guardar(DatoOro d) {
        String sql = "INSERT INTO apuestas (nombre_jugador, fecha, num1, num2, num3, num4, num5, num6, num7, simple, revancha, costo_total) "
                   + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection cn = ConexionMySQL.conectarDB();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, d.getNombreJugador());
            // suponiendo que d.getFecha() es java.util.Date
            ps.setDate(2, new java.sql.Date(d.getFecha().getTime()));
            ps.setInt(3, d.getNum1());
            ps.setInt(4, d.getNum2());
            ps.setInt(5, d.getNum3());
            ps.setInt(6, d.getNum4());
            ps.setInt(7, d.getNum5());
            ps.setInt(8, d.getNum6());
            ps.setInt(9, d.getNum7());
            ps.setBoolean(10, d.isSimple());
            ps.setBoolean(11, d.isRevancha());
            ps.setDouble(12, d.getCostoTotal());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // SELECT TODO
    public static List<DatoOro> obtenerTodas() {
        List<DatoOro> lista = new ArrayList<>();

        String sql = "SELECT * FROM apuestas";

        try (Connection cn = ConexionMySQL.conectarDB();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DatoOro d = new DatoOro();
                d.setNombreJugador(rs.getString("nombre_jugador"));
                d.setFecha(rs.getDate("fecha"));
                d.setNum1(rs.getInt("num1"));
                d.setNum2(rs.getInt("num2"));
                d.setNum3(rs.getInt("num3"));
                d.setNum4(rs.getInt("num4"));
                d.setNum5(rs.getInt("num5"));
                d.setNum6(rs.getInt("num6"));
                d.setNum7(rs.getInt("num7"));
                d.setSimple(rs.getBoolean("simple"));
                d.setRevancha(rs.getBoolean("revancha"));
                d.setCostoTotal(rs.getDouble("costo_total"));

                lista.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Algunas estadísticas básicas
    public static int contarApuestas() {
        String sql = "SELECT COUNT(*) FROM apuestas";
        try (Connection cn = ConexionMySQL.conectarDB();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static double sumaCostos() {
        String sql = "SELECT SUM(costo_total) FROM apuestas";
        try (Connection cn = ConexionMySQL.conectarDB();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}