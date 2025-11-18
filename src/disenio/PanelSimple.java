package disenio;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import datos.DatoOro;
import db.ApuestaDAO;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class PanelSimple extends JPanel {

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

	/**
	 * Create the panel.
	 */
	public PanelSimple() {
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
		pnlApuestaS.setBounds(10, 111, 898, 220);
		add(pnlApuestaS);
		pnlApuestaS.setLayout(null);
		
		JLabel lblNumeros = new JLabel("Numeros:");
		lblNumeros.setForeground(new Color(255, 255, 255));
		lblNumeros.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNumeros.setBounds(69, 12, 134, 67);
		pnlApuestaS.add(lblNumeros);
		
		txtNum1 = new JTextField();
		txtNum1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtNum1.setBounds(213, 25, 86, 41);
		pnlApuestaS.add(txtNum1);
		txtNum1.setColumns(10);
		
		txtNum2 = new JTextField();
		txtNum2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtNum2.setColumns(10);
		txtNum2.setBounds(319, 25, 86, 41);
		pnlApuestaS.add(txtNum2);
		
		txtNum3 = new JTextField();
		txtNum3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtNum3.setColumns(10);
		txtNum3.setBounds(425, 25, 86, 41);
		pnlApuestaS.add(txtNum3);
		
		txtNum4 = new JTextField();
		txtNum4.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtNum4.setColumns(10);
		txtNum4.setBounds(533, 25, 86, 41);
		pnlApuestaS.add(txtNum4);
		
		txtNum5 = new JTextField();
		txtNum5.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtNum5.setColumns(10);
		txtNum5.setBounds(644, 25, 86, 41);
		pnlApuestaS.add(txtNum5);
		
		JPanel pnlRevancha = new JPanel();
		pnlRevancha.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(240, 230, 140)));
		pnlRevancha.setBackground(new Color(250, 250, 210));
		pnlRevancha.setBounds(24, 93, 417, 116);
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
		pnlCosto.setBounds(471, 93, 417, 116);
		pnlApuestaS.add(pnlCosto);
		pnlCosto.setLayout(null);
		
		JLabel lblCosto = new JLabel("Costo Total: " + costo);
		lblCosto.setBounds(10, 11, 397, 94);
		pnlCosto.add(lblCosto);
		lblCosto.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosto.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		chckbxRevancha.addItemListener(e -> {
		    revanchaSeleccionada = chckbxRevancha.isSelected();
		    costo = DatoOro.calcularPrecioSimple(true, revanchaSeleccionada);
		    lblCosto.setText("Costo Total: " + costo);
		});
		
		JButton btnApuestaS = new JButton("Crear Apuesta");
		btnApuestaS.setBackground(new Color(65, 105, 225));
		btnApuestaS.addActionListener(new ActionListener() {
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
		            int num5 = Integer.parseInt(txtNum5.getText());

		            int[] numeros = { num1, num2, num3, num4, num5 };

		            // Validar rango permitido (1 a 48)
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
		            for (int i = 0; i < numeros.length; i++) {
		                for (int j = i + 1; j < numeros.length; j++) {
		                    if (numeros[i] == numeros[j]) {
		                        JOptionPane.showMessageDialog(null,
		                            "No se pueden repetir los números.",
		                            "Error de validación",
		                            JOptionPane.ERROR_MESSAGE);

		                        limpiarCamposNumeros();
		                        return;
		                    }
		                }
		            }

		            // Si está todo OK, guardar los datos
		            datos.setNombreJugador(textField_jugador.getText().toUpperCase());
		            datos.setNum1(num1);
		            datos.setNum2(num2);
		            datos.setNum3(num3);
		            datos.setNum4(num4);
		            datos.setNum5(num5);
		            datos.setRevancha(chckbxRevancha.isSelected());
		            datos.setFecha(fechaActual);
		            datos.setSimple(true);
		            datos.setCostoTotal(costo);

		            // Guardar en base de datos
		            ApuestaDAO.guardar(datos);

		            JOptionPane.showMessageDialog(null,
		                "✅ Apuesta guardada exitosamente!\n\n"
		                + "Jugador: " + datos.getNombreJugador() + "\n"
		                + "Números: " + num1 + ", " + num2 + ", " + num3 + ", " + num4 + ", " + num5 + "\n"
		                + "Revancha: " + (datos.isRevancha() ? "SÍ" : "NO") + "\n"
		                + "Costo: $" + datos.getCostoTotal(),
		                "Apuesta Registrada",
		                JOptionPane.INFORMATION_MESSAGE);

		            // Limpiar campos
		            textField_jugador.setText("");
		            limpiarCamposNumeros();
		            chckbxRevancha.setSelected(false);
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

		btnApuestaS.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnApuestaS.setBounds(370, 361, 198, 46);
		add(btnApuestaS);
		
		
	}
	
	
	private void limpiarCamposNumeros() {
	    txtNum1.setText("");
	    txtNum2.setText("");
	    txtNum3.setText("");
	    txtNum4.setText("");
	    txtNum5.setText("");
	    txtNum1.requestFocus();
	}
}
