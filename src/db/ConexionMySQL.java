package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConexionMySQL {
	// Datos para realizar la conexión con la base de datos
		private static String usuario = "root";
		private static String pass = "123456789";
		private static String baseDeDatos = "banco2mn";
		private static String url = "jdbc:mysql://localhost:3306/" + baseDeDatos + "?useSSL=false&serverTimezone=UTC";

		// objetos que manipulan/actualizan la base de datos
		private static Connection conectar;
		private static Statement sentenciaSQL;
		private static ResultSet resultado;

		// Cargar el driver de MySQL para trabajar con JAVA

		public static boolean cargarDriverMySQL() {

			try {
				// Carga del driver de MySQL
				Class.forName("com.mysql.cj.jdbc.Driver");
				return true;

			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Error: No se encuentra el driver de MySQL", "Error fatal...",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}

		} // fin del método cargarDriverMySQL

		// conectar con la base de datos

		public static Connection conectarDB() {

			try {
				conectar = DriverManager.getConnection(url, usuario, pass);

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Error...",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

			return conectar;
		}// fin del método ConectarDB
}
