package presentacion.vista;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;
import javax.swing.ImageIcon;
import java.awt.Cursor;

//public class selectMenuReportes {
//
//}

public class selectMenuReportes extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Controlador controlador;
	private VentanaPrincipal ventanaPrincipal;
	private JButton btnConsultaestadisticas;
	private JButton btnConsultacontable;
	public selectMenuReportes(VentanaPrincipal ventanaPrincipal, Controlador controlador) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.controlador = controlador;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 408, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(selectMenuReportes.class.getResource("/prototipos/menuReportes.png")));
		fondo.setBounds(0, 0, 399, 197);
		contentPane.add(fondo);
		
		btnConsultaestadisticas = new JButton("consultaEstadisticas");
		btnConsultaestadisticas.setOpaque(false);
		btnConsultaestadisticas.setBounds(40, 28, 139, 142);
		contentPane.add(btnConsultaestadisticas);
		
		btnConsultacontable = new JButton("consultaContable");
		btnConsultacontable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConsultacontable.setOpaque(false);
		btnConsultacontable.setBounds(205, 28, 163, 142);
		contentPane.add(btnConsultacontable);
	}
	public JButton getBtnConsultaestadisticas() {
		return btnConsultaestadisticas;
	}
	public void setBtnConsultaestadisticas(JButton btnConsultaestadisticas) {
		this.btnConsultaestadisticas = btnConsultaestadisticas;
	}
	public JButton getBtnConsultacontable() {
		return btnConsultacontable;
	}
	public void setBtnConsultacontable(JButton btnConsultacontable) {
		this.btnConsultacontable = btnConsultacontable;
	}
}