package disenio;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import db.ApuestaDAO;

public class PanelEstadistica extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable tableNumeros;
    private JTable tableApuestas;
    private JLabel lblTotalApuestas;
    private JLabel lblTotalRecaudado;
    private JLabel lblPozoOro;
    private JLabel lblPozoPlata;

    public PanelEstadistica() {
        setBackground(new Color(250, 250, 210));
        setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(65, 105, 225)));
        setBounds(10, 142, 918, 441);
        setLayout(null);
        
        // Título
        JLabel lblTitulo = new JLabel("ESTADÍSTICAS DEL JUEGO");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(10, 11, 898, 30);
        add(lblTitulo);
        
        // Panel de pozos
        JPanel pnlPozos = new JPanel();
        pnlPozos.setBackground(new Color(65, 105, 225));
        pnlPozos.setBounds(10, 52, 440, 120);
        add(pnlPozos);
        pnlPozos.setLayout(null);
        
        lblTotalApuestas = new JLabel("Total Apuestas: 0");
        lblTotalApuestas.setForeground(Color.WHITE);
        lblTotalApuestas.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTotalApuestas.setBounds(10, 11, 420, 25);
        pnlPozos.add(lblTotalApuestas);
        
        lblTotalRecaudado = new JLabel("Total Recaudado: $0");
        lblTotalRecaudado.setForeground(Color.WHITE);
        lblTotalRecaudado.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTotalRecaudado.setBounds(10, 40, 420, 25);
        pnlPozos.add(lblTotalRecaudado);
        
        lblPozoOro = new JLabel("Pozo de Oro (20%): $0");
        lblPozoOro.setForeground(new Color(255, 215, 0));
        lblPozoOro.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblPozoOro.setBounds(10, 69, 420, 25);
        pnlPozos.add(lblPozoOro);
        
        lblPozoPlata = new JLabel("Pozo de Plata (3.2%): $0");
        lblPozoPlata.setForeground(new Color(192, 192, 192));
        lblPozoPlata.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblPozoPlata.setBounds(10, 94, 420, 25);
        pnlPozos.add(lblPozoPlata);
        
        // Botón actualizar
        JButton btnActualizar = new JButton("🔄 Actualizar");
        btnActualizar.setBackground(new Color(240, 230, 140));
        btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnActualizar.setBounds(468, 52, 440, 120);
        add(btnActualizar);
        
        // Tabla de números más jugados
        JLabel lblNumeros = new JLabel("TOP 10 NÚMEROS MÁS JUGADOS");
        lblNumeros.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNumeros.setBounds(10, 183, 440, 25);
        add(lblNumeros);
        
        tableNumeros = new JTable();
        JScrollPane scrollNumeros = new JScrollPane(tableNumeros);
        scrollNumeros.setBounds(10, 210, 440, 220);
        add(scrollNumeros);
        
        // Tabla de últimas apuestas
        JLabel lblApuestas = new JLabel("ÚLTIMAS 10 APUESTAS");
        lblApuestas.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblApuestas.setBounds(468, 183, 440, 25);
        add(lblApuestas);
        
        tableApuestas = new JTable();
        JScrollPane scrollApuestas = new JScrollPane(tableApuestas);
        scrollApuestas.setBounds(468, 210, 440, 220);
        add(scrollApuestas);
        
        // Acción del botón
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarEstadisticas();
            }
        });
        
        // NO cargar datos al inicio para mejorar rendimiento
        // Los datos se cargarán cuando el usuario haga clic en "Actualizar"
        // o cuando se muestre el panel por primera vez
    }
    
    public void cargarEstadisticas() {
        // 1. Cargar datos generales
        int totalApuestas = ApuestaDAO.contarApuestas();
        double totalRecaudado = ApuestaDAO.sumaCostos();
        double pozoOro = totalRecaudado * 0.20;
        double pozoPlata = totalRecaudado * 0.032;
        
        lblTotalApuestas.setText("Total Apuestas: " + totalApuestas);
        lblTotalRecaudado.setText("Total Recaudado: $" + String.format("%.2f", totalRecaudado));
        lblPozoOro.setText("Pozo de Oro (20%): $" + String.format("%.2f", pozoOro));
        lblPozoPlata.setText("Pozo de Plata (3.2%): $" + String.format("%.2f", pozoPlata));
        
        // 2. Cargar números más jugados
        int[] conteo = ApuestaDAO.contarNumeros();
        
        // Crear array para ordenar
        Integer[][] numerosOrdenados = new Integer[48][2];
        for (int i = 1; i <= 48; i++) {
            numerosOrdenados[i-1][0] = i;        // número
            numerosOrdenados[i-1][1] = conteo[i]; // cantidad de veces jugado
        }
        
        // Ordenar de mayor a menor
        java.util.Arrays.sort(numerosOrdenados, (a, b) -> b[1].compareTo(a[1]));
        
        // Llenar tabla con top 10
        String[] columnasNumeros = {"Posición", "Número", "Veces Jugado"};
        DefaultTableModel modeloNumeros = new DefaultTableModel(columnasNumeros, 0);
        
        for (int i = 0; i < 10 && i < numerosOrdenados.length; i++) {
            Object[] fila = {
                (i + 1),
                numerosOrdenados[i][0],
                numerosOrdenados[i][1]
            };
            modeloNumeros.addRow(fila);
        }
        
        tableNumeros.setModel(modeloNumeros);
        
        // 3. Cargar últimas apuestas
        java.util.List<datos.DatoOro> apuestas = ApuestaDAO.obtenerTodas();
        
        String[] columnasApuestas = {"Jugador", "Fecha", "Números", "Costo"};
        DefaultTableModel modeloApuestas = new DefaultTableModel(columnasApuestas, 0);
        
        // Mostrar las últimas 10
        int inicio = Math.max(0, apuestas.size() - 10);
        for (int i = apuestas.size() - 1; i >= inicio; i--) {
            datos.DatoOro d = apuestas.get(i);
            String numeros = d.getNum1() + "," + d.getNum2() + "," + d.getNum3() + 
                            "," + d.getNum4() + "," + d.getNum5();
            
            Object[] fila = {
                d.getNombreJugador(),
                new java.text.SimpleDateFormat("dd/MM/yyyy").format(d.getFecha()),
                numeros,
                "$" + d.getCostoTotal()
            };
            modeloApuestas.addRow(fila);
        }
        
        tableApuestas.setModel(modeloApuestas);
    }
}
