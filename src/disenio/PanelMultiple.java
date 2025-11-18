package disenio;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import datos.DatoOro;

public class PanelMultiple extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField_jugador;
	private JTextField txtNum1;
	private JTextField txtNum2;
	private JTextField txtNum3;
	private JTextField txtNum4;
	private JTextField txtNum5;
	private Date fechaActual;
	private static SimpleDateFormat miFormatoFecha = new SimpleDateFormat("yyyy-MM-dd");
	int num1;
	int num2;
	int num3;
	int num4;
	int num5;
	private static DatoOro datos = new DatoOro();
	private double costo = 45;
	boolean revanchaSeleccionada;
	private JTextField txtNum6;
	private JTextField txtNum7;
	private JTextField txtNum8;
	private int cantidadNumerosSeleccionados = 5;
	private JLabel lblCosto;

	/**
	 * Create the panel.
	 */
	public PanelMultiple() {
		setBackground(new Color(250, 250, 210));
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(65, 105, 225)));
		
		setBounds(10, 142, 918, 441);
		setLayout(null);
		
		JPanel pnlCreacionJugador = new JPanel();
		pnlCreacionJugador.setBackground(new Color(65, 105, 225));
		pnlCreacionJugador.setBounds(10, 11, 898, 89);
		add(pnlCreacionJugador);
		pnlCreacionJugador.setLayout(null);
		
		JLabel lblJugador = new JLabel("Nombre Jugador:");
		lblJugador.setForeground(new Color(255, 255, 255));
		lblJugador.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblJugador.setBounds(89, 20, 188, 51);
		pnlCreacionJugador.add(lblJugador);
		
		textField_jugador = new JTextField();
		textField_jugador.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_jugador.setBounds(279, 29, 169, 35);
		pnlCreacionJugador.add(textField_jugador);
		textField_jugador.setColumns(10);
		
		fechaActual = new Date();
		
		JLabel lblFecha = new JLabel("Fecha: " + miFormatoFecha.format(fechaActual));
		lblFecha.setForeground(new Color(255, 255, 255));
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFecha.setBounds(537, 11, 314, 67);
		pnlCreacionJugador.add(lblFecha);
		
		JLabel lblaviso = new JLabel("Despues se va a cambiar a mayúsculas");
		lblaviso.setForeground(new Color(255, 255, 255));
		lblaviso.setBounds(89, 64, 290, 14);
		pnlCreacionJugador.add(lblaviso);
		
		JPanel pnlApuestaS = new JPanel();
		pnlApuestaS.setBackground(new Color(65, 105, 225));
		pnlApuestaS.setBounds(10, 111, 898, 262);
		add(pnlApuestaS);
		pnlApuestaS.setLayout(null);
		
		JLabel lblNumeros = new JLabel("Numeros:");
		lblNumeros.setForeground(new Color(255, 255, 255));
		lblNumeros.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNumeros.setBounds(10, 57, 106, 67);
		pnlApuestaS.add(lblNumeros);
		
		txtNum1 = new JTextField();
		txtNum1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtNum1.setBounds(126, 70, 86, 41);
		pnlApuestaS.add(txtNum1);
		txtNum1.setColumns(10);
		
		txtNum2 = new JTextField();
		txtNum2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtNum2.setColumns(10);
		txtNum2.setBounds(222, 70, 86, 41);
		pnlApuestaS.add(txtNum2);
		
		txtNum3 = new JTextField();
		txtNum3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtNum3.setColumns(10);
		txtNum3.setBounds(318, 70, 86, 41);
		pnlApuestaS.add(txtNum3);
		
		txtNum4 = new JTextField();
		txtNum4.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtNum4.setColumns(10);
		txtNum4.setBounds(414, 70, 86, 41);
		pnlApuestaS.add(txtNum4);
		
		txtNum5 = new JTextField();
		txtNum5.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtNum5.setColumns(10);
		txtNum5.setBounds(510, 70, 86, 41);
		pnlApuestaS.add(txtNum5);
		
		JPanel pnlRevancha = new JPanel();
		pnlRevancha.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(240, 230, 140)));
		pnlRevancha.setBackground(new Color(250, 250, 210));
		pnlRevancha.setBounds(24, 135, 417, 116);
		pnlApuestaS.add(pnlRevancha);
		pnlRevancha.setLayout(null);
		
		JCheckBox chckbxRevancha = new JCheckBox("Revancha");
		chckbxRevancha.setBounds(6, 7, 405, 102);
		pnlRevancha.add(chckbxRevancha);
		chckbxRevancha.setBackground(new Color(250, 250, 210));
		chckbxRevancha.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxRevancha.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JPanel pnlCosto = new JPanel();
		pnlCosto.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(240, 230, 140)));
		pnlCosto.setBackground(new Color(250, 250, 210));
		pnlCosto.setBounds(471, 135, 417, 116);
		pnlApuestaS.add(pnlCosto);
		pnlCosto.setLayout(null);
		
		lblCosto = new JLabel("Costo Total: " + costo);
		lblCosto.setBounds(10, 11, 397, 94);
		pnlCosto.add(lblCosto);
		lblCosto.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosto.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		// Crear campos adicionales (ocultos inicialmente)
		txtNum6 = new JTextField();
		txtNum6.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtNum6.setColumns(10);
		txtNum6.setBounds(606, 70, 86, 41);
		txtNum6.setVisible(false);
		pnlApuestaS.add(txtNum6);
		
		txtNum7 = new JTextField();
		txtNum7.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtNum7.setColumns(10);
		txtNum7.setBounds(702, 70, 86, 41);
		txtNum7.setVisible(false);
		pnlApuestaS.add(txtNum7);
		
		txtNum8 = new JTextField();
		txtNum8.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtNum8.setColumns(10);
		txtNum8.setBounds(798, 70, 86, 41);
		txtNum8.setVisible(false);
		pnlApuestaS.add(txtNum8);
		
		// Botones para seleccionar cantidad de números
		JButton btn4num = new JButton("4 Números");
		btn4num.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNum5.setVisible(false);
				txtNum6.setVisible(false);
				txtNum7.setVisible(false);
				txtNum8.setVisible(false);
				actualizarCostoMultiple(4);
			}
		});
		btn4num.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn4num.setBackground(new Color(255, 215, 0));
		btn4num.setBounds(10, 13, 150, 46);
		pnlApuestaS.add(btn4num);
		
		JButton btn6num = new JButton("6 Números");
		btn6num.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNum5.setVisible(true);
				txtNum6.setVisible(true);
				txtNum7.setVisible(false);
				txtNum8.setVisible(false);
				actualizarCostoMultiple(6);
			}
		});
		btn6num.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn6num.setBackground(new Color(240, 230, 140));
		btn6num.setBounds(170, 13, 150, 46);
		pnlApuestaS.add(btn6num);
		
		JButton btn7Num = new JButton("7 Números");
		btn7Num.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNum5.setVisible(true);
				txtNum6.setVisible(true);
				txtNum7.setVisible(true);
				txtNum8.setVisible(false);
				actualizarCostoMultiple(7);
			}
		});
		btn7Num.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn7Num.setBackground(new Color(240, 230, 140));
		btn7Num.setBounds(330, 13, 150, 46);
		pnlApuestaS.add(btn7Num);
		
		JButton btn8Num = new JButton("8 Números");
		btn8Num.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNum5.setVisible(true);
				txtNum6.setVisible(true);
				txtNum7.setVisible(true);
				txtNum8.setVisible(true);
				actualizarCostoMultiple(8);
			}
		});
		btn8Num.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn8Num.setBackground(new Color(240, 230, 140));
		btn8Num.setBounds(490, 13, 150, 46);
		pnlApuestaS.add(btn8Num);
		
		// Listener de revancha (simplificado)
		chckbxRevancha.addItemListener(e -> {
		    revanchaSeleccionada = chckbxRevancha.isSelected();
		    actualizarCostoMultiple(cantidadNumerosSeleccionados);
		});
		
		JButton btnApuestaM = new JButton("Crear Apuesta");
		btnApuestaM.setBackground(new Color(65, 105, 225));
		btnApuestaM.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	String nombre = textField_jugador.getText().trim();
		        if (nombre.isEmpty()) {
		            JOptionPane.showMessageDialog(null,
		                "Debe ingresar el nombre del jugador.",
		                "Error de validación",
		                JOptionPane.ERROR_MESSAGE);
		            textField_jugador.requestFocus();
		            return;
		        }
		        
		        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
		            JOptionPane.showMessageDialog(null,
		                "El nombre solo puede contener letras y espacios.",
		                "Error de validación",
		                JOptionPane.ERROR_MESSAGE);
		            textField_jugador.requestFocus();
		            return;
		        }

		        try {
		            int num1 = Integer.parseInt(txtNum1.getText());
		            int num2 = Integer.parseInt(txtNum2.getText());
		            int num3 = Integer.parseInt(txtNum3.getText());
		            int num4 = Integer.parseInt(txtNum4.getText());
		            
		            // Números adicionales (pueden ser 0 si no están visibles)
		            int num5 = 0, num6 = 0, num7 = 0, num8 = 0;
		            
		            if (txtNum5.isVisible() && !txtNum5.getText().isEmpty()) {
		                num5 = Integer.parseInt(txtNum5.getText());
		            }
		            if (txtNum6.isVisible() && !txtNum6.getText().isEmpty()) {
		                num6 = Integer.parseInt(txtNum6.getText());
		            }
		            if (txtNum7.isVisible() && !txtNum7.getText().isEmpty()) {
		                num7 = Integer.parseInt(txtNum7.getText());
		            }
		            if (txtNum8.isVisible() && !txtNum8.getText().isEmpty()) {
		                num8 = Integer.parseInt(txtNum8.getText());
		            }

		            // Crear lista de números para validar
		            java.util.List<Integer> numeros = new java.util.ArrayList<>();
		            numeros.add(num1);
		            numeros.add(num2);
		            numeros.add(num3);
		            numeros.add(num4);
		            if (num5 > 0) numeros.add(num5);
		            if (num6 > 0) numeros.add(num6);
		            if (num7 > 0) numeros.add(num7);
		            if (num8 > 0) numeros.add(num8);

		            // Validar rango
		            for (int n : numeros) {
		                if (n < 1 || n > 48) {
		                    JOptionPane.showMessageDialog(null,
		                        "Los números deben estar entre 1 y 48.",
		                        "Error de rango",
		                        JOptionPane.ERROR_MESSAGE);
		                    limpiarCamposNumeros();
		                    return;
		                }
		            }

		            // Validar repetidos
		            java.util.Set<Integer> unicos = new java.util.HashSet<>(numeros);
		            if (unicos.size() != numeros.size()) {
		                JOptionPane.showMessageDialog(null,
		                    "No se pueden repetir los números.",
		                    "Error de validación",
		                    JOptionPane.ERROR_MESSAGE);
		                limpiarCamposNumeros();
		                return;
		            }

		            // Guardar datos
		            datos.setNombreJugador(textField_jugador.getText().toUpperCase());
		            datos.setNum1(num1);
		            datos.setNum2(num2);
		            datos.setNum3(num3);
		            datos.setNum4(num4);
		            datos.setNum5(num5);
		            datos.setNum6(num6);
		            datos.setNum7(num7);
		            datos.setNum8(num8);
		            datos.setRevancha(chckbxRevancha.isSelected());
		            datos.setFecha(fechaActual);
		            datos.setSimple(false); // Es múltiple
		            datos.setCostoTotal(costo);

		            // Guardar en BD
		            db.ApuestaDAO.guardar(datos);

		            JOptionPane.showMessageDialog(null,
		                "✅ Apuesta múltiple guardada!\n\n"
		                + "Jugador: " + datos.getNombreJugador() + "\n"
		                + "Números: " + numeros.toString() + "\n"
		                + "Revancha: " + (datos.isRevancha() ? "SÍ" : "NO") + "\n"
		                + "Costo: $" + datos.getCostoTotal(),
		                "Apuesta Registrada",
		                JOptionPane.INFORMATION_MESSAGE);

		            // Limpiar campos
		            textField_jugador.setText("");
		            limpiarCamposNumeros();
		            chckbxRevancha.setSelected(false);
		            txtNum6.setVisible(false);
		            txtNum7.setVisible(false);
		            txtNum8.setVisible(false);
		            textField_jugador.requestFocus();

		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null,
		                "Debe ingresar solo números en los campos.",
		                "Error de formato",
		                JOptionPane.ERROR_MESSAGE);
		            limpiarCamposNumeros();
		        }
		    }
		});

		btnApuestaM.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnApuestaM.setBounds(366, 384, 198, 46);
		add(btnApuestaM);
		
		
	}
	
	
	private void limpiarCamposNumeros() {
	    txtNum1.setText("");
	    txtNum2.setText("");
	    txtNum3.setText("");
	    txtNum4.setText("");
	    txtNum5.setText("");
	    txtNum6.setText("");
	    txtNum7.setText("");
	    txtNum8.setText("");
	    txtNum1.requestFocus();
	}
	
	private void actualizarCostoMultiple(int cantidad) {
	    cantidadNumerosSeleccionados = cantidad;
	    costo = DatoOro.calcularPrecioMultiple(cantidad, revanchaSeleccionada);
	    lblCosto.setText("Costo Total: $" + String.format("%.2f", costo));
	}
}

