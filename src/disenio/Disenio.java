package disenio;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Disenio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelPrincipal = null;
	private static PanelSimple crearApuestaSimple = new PanelSimple();
	private static PanelMultiple crearApuestaMultiple = new PanelMultiple();
	private static PanelSorteo crearSorteo = new PanelSorteo();
	private static PanelEstadistica crearEstadistica = new PanelEstadistica();

	/**
	 * Create the frame.
	 */
	public Disenio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 954, 633);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 240, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlTitulo = new JPanel();
		pnlTitulo.setBackground(new Color(65, 105, 225));
		pnlTitulo.setBounds(10, 11, 918, 58);
		contentPane.add(pnlTitulo);
		pnlTitulo.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitulo = new JLabel("¡CINCO DE ORO!");
		lblTitulo.setForeground(new Color(240, 230, 140));
		lblTitulo.setBackground(new Color(224, 255, 255));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		pnlTitulo.add(lblTitulo);
		
		JPanel pnlMenu = new JPanel();
		pnlMenu.setBackground(new Color(240, 230, 140));
		pnlMenu.setBounds(10, 80, 918, 51);
		contentPane.add(pnlMenu);
		pnlMenu.setLayout(null);
		
		JButton btnSimple = new JButton("Simple");
		btnSimple.setForeground(new Color(255, 255, 255));
		btnSimple.setBackground(new Color(65, 105, 225));
		btnSimple.setOpaque(true);
		btnSimple.setBorderPainted(false);
		btnSimple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				contentPane.remove(panelPrincipal);
				contentPane.remove(crearApuestaMultiple);
				contentPane.remove(crearEstadistica);
				contentPane.add(crearApuestaSimple);
				repaint();
			}
		});
		btnSimple.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSimple.setBounds(10, 11, 204, 29);
		pnlMenu.add(btnSimple);
		
		JButton btnMultiple = new JButton("Múltiple");
		btnMultiple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				contentPane.remove(panelPrincipal);
				contentPane.remove(crearApuestaSimple);
				contentPane.remove(crearEstadistica);
				contentPane.add(crearApuestaMultiple);
				repaint();
			}
		});
		btnMultiple.setForeground(new Color(255, 255, 255));
		btnMultiple.setBackground(new Color(65, 105, 225));
		btnMultiple.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMultiple.setBounds(243, 11, 204, 29);
		btnMultiple.setOpaque(true);
		btnMultiple.setBorderPainted(false);
		pnlMenu.add(btnMultiple);
		
		JButton btnSorteo = new JButton("Sorteo");
		btnSorteo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				contentPane.remove(panelPrincipal);
				contentPane.remove(crearApuestaSimple);
				contentPane.remove(crearApuestaMultiple);
				contentPane.remove(crearEstadistica);
				contentPane.add(crearSorteo);
				repaint();
			}
		});
		btnSorteo.setForeground(new Color(255, 255, 255));
		btnSorteo.setBackground(new Color(65, 105, 225));
		btnSorteo.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSorteo.setBounds(473, 11, 204, 29);
		btnSorteo.setOpaque(true);
		btnSorteo.setBorderPainted(false);
		pnlMenu.add(btnSorteo);
		
		JButton btnEstadistica = new JButton("Estadística");
		btnEstadistica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				contentPane.remove(panelPrincipal);
				contentPane.remove(crearApuestaSimple);
				contentPane.remove(crearApuestaMultiple);
				contentPane.add(crearEstadistica);
				
				// Cargar estadísticas cuando se muestra el panel
				crearEstadistica.cargarEstadisticas();
				
				repaint();
			}
		});
		btnEstadistica.setForeground(new Color(255, 255, 255));
		btnEstadistica.setBackground(new Color(65, 105, 225));
		btnEstadistica.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEstadistica.setBounds(704, 11, 204, 29);
		btnEstadistica.setOpaque(true);
		btnEstadistica.setBorderPainted(false);
		pnlMenu.add(btnEstadistica);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(65, 105, 225));
		panelPrincipal.setBounds(10, 142, 918, 441);
		contentPane.add(panelPrincipal);
		
		

	}
}
