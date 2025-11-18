package disenio;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.Random;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import datos.Sorteo;
import datos.DatoOro;
import db.SorteoDAO;
import db.ApuestaDAO;

public class PanelSorteo extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel lblNum1, lblNum2, lblNum3, lblNum4, lblNum5, lblExtra;
    private JTextArea txtResultados;
    private JLabel lblPozoOro, lblPozoPlata;

    public PanelSorteo() {
        setBackground(new Color(250, 250, 210));
        setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(65, 105, 225)));
        setBounds(10, 142, 918, 441);
        setLayout(null);
        
        // Título
        JLabel lblTitulo = new JLabel("SORTEO 5 DE ORO");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitulo.setBounds(10, 11, 898, 35);
        add(lblTitulo);
        
        // Panel de números sorteados
        JPanel pnlNumeros = new JPanel();
        pnlNumeros.setBackground(new Color(65, 105, 225));
        pnlNumeros.setBounds(10, 57, 898, 120);
        add(pnlNumeros);
        pnlNumeros.setLayout(null);
        
        JLabel lblTexto = new JLabel("NÚMEROS GANADORES:");
        lblTexto.setForeground(Color.WHITE);
        lblTexto.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTexto.setBounds(10, 11, 878, 25);
        pnlNumeros.add(lblTexto);
        
        // Números principales
        lblNum1 = crearLabelNumero(120, 45);
        pnlNumeros.add(lblNum1);
        
        lblNum2 = crearLabelNumero(220, 45);
        pnlNumeros.add(lblNum2);
        
        lblNum3 = crearLabelNumero(320, 45);
        pnlNumeros.add(lblNum3);
        
        lblNum4 = crearLabelNumero(420, 45);
        pnlNumeros.add(lblNum4);
        
        lblNum5 = crearLabelNumero(520, 45);
        pnlNumeros.add(lblNum5);
        
        // Número extra
        JLabel lblTextoExtra = new JLabel("EXTRA:");
        lblTextoExtra.setForeground(new Color(255, 215, 0));
        lblTextoExtra.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTextoExtra.setBounds(650, 35, 80, 25);
        pnlNumeros.add(lblTextoExtra);
        
        lblExtra = crearLabelNumero(720, 45);
        lblExtra.setBackground(new Color(255, 215, 0));
        pnlNumeros.add(lblExtra);
        
        // Panel de pozos
        JPanel pnlPozos = new JPanel();
        pnlPozos.setBackground(new Color(240, 230, 140));
        pnlPozos.setBounds(10, 188, 440, 80);
        add(pnlPozos);
        pnlPozos.setLayout(null);
        
        lblPozoOro = new JLabel("Pozo de Oro: $0");
        lblPozoOro.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblPozoOro.setBounds(10, 11, 420, 30);
        pnlPozos.add(lblPozoOro);
        
        lblPozoPlata = new JLabel("Pozo de Plata: $0");
        lblPozoPlata.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblPozoPlata.setBounds(10, 45, 420, 30);
        pnlPozos.add(lblPozoPlata);
        
        // Botón realizar sorteo
        JButton btnSortear = new JButton("🎲 REALIZAR SORTEO");
        btnSortear.setBackground(new Color(34, 139, 34));
        btnSortear.setForeground(Color.WHITE);
        btnSortear.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnSortear.setBounds(468, 188, 440, 80);
        add(btnSortear);
        
        // Área de resultados
        JLabel lblResultados = new JLabel("RESULTADOS DEL SORTEO:");
        lblResultados.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblResultados.setBounds(10, 279, 898, 25);
        add(lblResultados);
        
        txtResultados = new JTextArea();
        txtResultados.setEditable(false);
        txtResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollResultados = new JScrollPane(txtResultados);
        scrollResultados.setBounds(10, 305, 898, 125);
        add(scrollResultados);
        
        // Acción del botón
        btnSortear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarSorteo();
            }
        });
        
        // Cargar pozos actuales
        actualizarPozos();
    }
    
    private JLabel crearLabelNumero(int x, int y) {
        JLabel lbl = new JLabel("?");
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 30));
        lbl.setOpaque(true);
        lbl.setBackground(Color.WHITE);
        lbl.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
        lbl.setBounds(x, y, 70, 60);
        return lbl;
    }
    
    private void actualizarPozos() {
        double totalRecaudado = ApuestaDAO.sumaCostos();
        double pozoOro = totalRecaudado * 0.20;
        double pozoPlata = totalRecaudado * 0.032;
        
        lblPozoOro.setText("Pozo de Oro: $" + String.format("%.2f", pozoOro));
        lblPozoPlata.setText("Pozo de Plata: $" + String.format("%.2f", pozoPlata));
    }
    
    private void realizarSorteo() {
        // Verificar que hay apuestas
        int totalApuestas = ApuestaDAO.contarApuestas();
        if (totalApuestas == 0) {
            JOptionPane.showMessageDialog(this,
                "No hay apuestas registradas para sortear.",
                "Sin apuestas",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Confirmar sorteo
        int confirmar = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de realizar el sorteo?\n" +
            "Total de apuestas: " + totalApuestas,
            "Confirmar Sorteo",
            JOptionPane.YES_NO_OPTION);
        
        if (confirmar != JOptionPane.YES_OPTION) {
            return;
        }
        
        // Generar números aleatorios únicos del 1 al 48
        Random random = new Random();
        HashSet<Integer> numerosGenerados = new HashSet<>();
        
        while (numerosGenerados.size() < 6) {
            int num = random.nextInt(48) + 1; // 1 a 48
            numerosGenerados.add(num);
        }
        
        // Convertir a array y asignar
        Integer[] numeros = numerosGenerados.toArray(new Integer[0]);
        
        int num1 = numeros[0];
        int num2 = numeros[1];
        int num3 = numeros[2];
        int num4 = numeros[3];
        int num5 = numeros[4];
        int numExtra = numeros[5];
        
        // Mostrar números en pantalla
        lblNum1.setText(String.valueOf(num1));
        lblNum2.setText(String.valueOf(num2));
        lblNum3.setText(String.valueOf(num3));
        lblNum4.setText(String.valueOf(num4));
        lblNum5.setText(String.valueOf(num5));
        lblExtra.setText(String.valueOf(numExtra));
        
        // Calcular pozos
        double totalRecaudado = ApuestaDAO.sumaCostos();
        double pozoOro = totalRecaudado * 0.20;
        double pozoPlata = totalRecaudado * 0.032;
        
        // Guardar sorteo en BD
        Sorteo sorteo = new Sorteo();
        sorteo.setFecha(new Date());
        sorteo.setNum1(num1);
        sorteo.setNum2(num2);
        sorteo.setNum3(num3);
        sorteo.setNum4(num4);
        sorteo.setNum5(num5);
        sorteo.setNumExtra(numExtra);
        sorteo.setTotalApostado(totalRecaudado);
        sorteo.setPozoOro(pozoOro);
        sorteo.setPozoPlata(pozoPlata);
        
        SorteoDAO.guardar(sorteo);
        
        // Calcular ganadores
        calcularGanadores(num1, num2, num3, num4, num5, numExtra, pozoOro, pozoPlata);
    }
    
    private void calcularGanadores(int n1, int n2, int n3, int n4, int n5, int extra, 
                                   double pozoOro, double pozoPlata) {
        
        List<DatoOro> apuestas = ApuestaDAO.obtenerTodas();
        HashSet<Integer> numerosGanadores = new HashSet<>();
        numerosGanadores.add(n1);
        numerosGanadores.add(n2);
        numerosGanadores.add(n3);
        numerosGanadores.add(n4);
        numerosGanadores.add(n5);
        
        // Contadores de ganadores por categoría
        int ganadores5de5 = 0;
        int ganadores4de5Extra = 0;
        int ganadores4de5 = 0;
        int ganadores3de5Extra = 0;
        int ganadores3de5 = 0;
        int ganadores2de5Extra = 0;
        int ganadores2de5 = 0;
        int ganadoresRevancha = 0;
        
        StringBuilder resultado = new StringBuilder();
        resultado.append("═══════════════════════════════════════════════════════════\n");
        resultado.append("                    RESULTADOS DEL SORTEO\n");
        resultado.append("═══════════════════════════════════════════════════════════\n\n");
        resultado.append("Números ganadores: ").append(n1).append(", ").append(n2).append(", ")
                 .append(n3).append(", ").append(n4).append(", ").append(n5).append("\n");
        resultado.append("Número extra: ").append(extra).append("\n\n");
        resultado.append("Pozo de Oro: $").append(String.format("%.2f", pozoOro)).append("\n");
        resultado.append("Pozo de Plata: $").append(String.format("%.2f", pozoPlata)).append("\n\n");
        resultado.append("───────────────────────────────────────────────────────────\n");
        resultado.append("GANADORES POR CATEGORÍA:\n");
        resultado.append("───────────────────────────────────────────────────────────\n\n");
        
        // Revisar cada apuesta
        for (DatoOro apuesta : apuestas) {
            // Obtener todos los números de la apuesta
            List<Integer> todosLosNumeros = new ArrayList<>();
            todosLosNumeros.add(apuesta.getNum1());
            todosLosNumeros.add(apuesta.getNum2());
            todosLosNumeros.add(apuesta.getNum3());
            todosLosNumeros.add(apuesta.getNum4());
            if (apuesta.getNum5() > 0) todosLosNumeros.add(apuesta.getNum5());
            if (apuesta.getNum6() > 0) todosLosNumeros.add(apuesta.getNum6());
            if (apuesta.getNum7() > 0) todosLosNumeros.add(apuesta.getNum7());
            if (apuesta.getNum8() > 0) todosLosNumeros.add(apuesta.getNum8());
            
            // Verificar si es apuesta de 4 números (modalidad especial)
            boolean esApuesta4Numeros = (todosLosNumeros.size() == 4);
            
            List<List<Integer>> combinaciones;
            
            if (esApuesta4Numeros) {
                // Apuesta de 4 números: agregar automáticamente el primer número ganador
                List<Integer> combinacionEspecial = new ArrayList<>(todosLosNumeros);
                combinacionEspecial.add(n1); // Agregar el primer número del sorteo
                combinaciones = new ArrayList<>();
                combinaciones.add(combinacionEspecial);
            } else {
                // Apuestas normales: generar combinaciones de 5 números
                combinaciones = generarCombinaciones(todosLosNumeros, 5);
            }
            
            // Evaluar cada combinación
            for (List<Integer> combinacion : combinaciones) {
                HashSet<Integer> numerosCombo = new HashSet<>(combinacion);
                
                // Contar aciertos
                int aciertos = 0;
                for (int num : numerosCombo) {
                    if (numerosGanadores.contains(num)) {
                        aciertos++;
                    }
                }
                
                // Verificar si tiene el número extra
                boolean tieneExtra = numerosCombo.contains(extra);
                
                // Clasificar premio
                if (aciertos == 5) {
                    ganadores5de5++;
                } else if (aciertos == 4 && tieneExtra) {
                    ganadores4de5Extra++;
                } else if (aciertos == 4) {
                    ganadores4de5++;
                } else if (aciertos == 3 && tieneExtra) {
                    ganadores3de5Extra++;
                } else if (aciertos == 3) {
                    ganadores3de5++;
                } else if (aciertos == 2 && tieneExtra) {
                    ganadores2de5Extra++;
                } else if (aciertos == 2) {
                    ganadores2de5++;
                }
                
                // Verificar revancha (solo cuenta una vez por apuesta, no por combinación)
                if (apuesta.isRevancha() && aciertos == 5 && combinaciones.indexOf(combinacion) == 0) {
                    ganadoresRevancha++;
                }
            }
        }
        
        // Mostrar resultados
        if (ganadores5de5 > 0) {
            double premioIndividual = pozoOro / ganadores5de5;
            resultado.append("🏆 5 de 5 (Pozo de Oro): ").append(ganadores5de5)
                     .append(" ganador(es) - $").append(String.format("%.2f", premioIndividual))
                     .append(" c/u\n");
        } else {
            resultado.append("5 de 5 (Pozo de Oro): Sin ganadores (acumula)\n");
        }
        
        if (ganadores4de5Extra > 0) {
            double premioIndividual = pozoPlata / ganadores4de5Extra;
            resultado.append("🥈 4 de 5 + Extra (Pozo de Plata): ").append(ganadores4de5Extra)
                     .append(" ganador(es) - $").append(String.format("%.2f", premioIndividual))
                     .append(" c/u\n");
        } else {
            resultado.append("4 de 5 + Extra (Pozo de Plata): Sin ganadores (acumula)\n");
        }
        
        resultado.append("4 de 5: ").append(ganadores4de5).append(" ganador(es) - $9,000 c/u\n");
        resultado.append("3 de 5 + Extra: ").append(ganadores3de5Extra).append(" ganador(es) - $1,800 c/u\n");
        resultado.append("3 de 5: ").append(ganadores3de5).append(" ganador(es) - $450 c/u\n");
        resultado.append("2 de 5 + Extra: ").append(ganadores2de5Extra).append(" ganador(es) - $180 c/u\n");
        resultado.append("2 de 5: ").append(ganadores2de5).append(" ganador(es) - $65 c/u\n");
        
        if (ganadoresRevancha > 0) {
            resultado.append("\n🎉 Revancha 5 de 5: ").append(ganadoresRevancha)
                     .append(" ganador(es) - Pozo Revancha\n");
        }
        
        resultado.append("\n═══════════════════════════════════════════════════════════\n");
        resultado.append("Total de apuestas revisadas: ").append(apuestas.size()).append("\n");
        resultado.append("═══════════════════════════════════════════════════════════\n");
        
        txtResultados.setText(resultado.toString());
        
        JOptionPane.showMessageDialog(this,
            "¡Sorteo realizado exitosamente!\n\n" +
            "Ganadores 5 de 5: " + ganadores5de5 + "\n" +
            "Total de apuestas: " + apuestas.size(),
            "Sorteo Completado",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Genera todas las combinaciones posibles de k elementos de una lista
     * Por ejemplo: [1,2,3,4,5,6] con k=5 genera 6 combinaciones
     */
    private List<List<Integer>> generarCombinaciones(List<Integer> numeros, int k) {
        List<List<Integer>> resultado = new ArrayList<>();
        generarCombinacionesRecursivo(numeros, k, 0, new ArrayList<>(), resultado);
        return resultado;
    }
    
    private void generarCombinacionesRecursivo(List<Integer> numeros, int k, int inicio, 
                                               List<Integer> actual, List<List<Integer>> resultado) {
        // Si ya tenemos k elementos, agregar la combinación
        if (actual.size() == k) {
            resultado.add(new ArrayList<>(actual));
            return;
        }
        
        // Generar combinaciones recursivamente
        for (int i = inicio; i < numeros.size(); i++) {
            actual.add(numeros.get(i));
            generarCombinacionesRecursivo(numeros, k, i + 1, actual, resultado);
            actual.remove(actual.size() - 1);
        }
    }
}
