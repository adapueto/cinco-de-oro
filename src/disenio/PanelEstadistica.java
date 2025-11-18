package disenio;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class PanelEstadistica extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelEstadistica() {
		setBackground(new Color(250, 250, 210));
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(65, 105, 225)));
		
		setBounds(10, 142, 918, 441);
		setLayout(null);
		
		
	}

}
