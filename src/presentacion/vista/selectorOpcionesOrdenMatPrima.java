package presentacion.vista;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Rectangle;

public class selectorOpcionesOrdenMatPrima extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Controlador controlador;
	@SuppressWarnings("unused")
	private VentanaPrincipal ventanaPrincipal;
	private final JButton btnAgregarOrdenMatPrima = new JButton("New button");
	private final JButton btnGestionarOrdenes = new JButton("New button");
	
	public selectorOpcionesOrdenMatPrima (VentanaPrincipal ventanaPrincipal, Controlador controlador){
		this.ventanaPrincipal = ventanaPrincipal;
		this.controlador = controlador;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 411, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(selectorOpcionesOrdenMatPrima.class.getResource("/prototipos/menuSolicMateriaPrima.png")));
		fondo.setBounds(0, 0, 395, 208);
		contentPane.add(fondo);
		btnAgregarOrdenMatPrima.setOpaque(false);
		btnAgregarOrdenMatPrima.setBounds(47, 30, 138, 154);
		contentPane.add(btnAgregarOrdenMatPrima);
		btnGestionarOrdenes.setOpaque(false);
		btnGestionarOrdenes.setBounds(221, 30, 138, 154);
		contentPane.add(btnGestionarOrdenes);

	}

	public JButton getBtnAgregarOrdenMatPrima() {
		return btnAgregarOrdenMatPrima;
	}

	public JButton btnGestionarOrdenes() {
		return btnGestionarOrdenes;
	}

	public JButton getBtnGestionarOrdenes() {
		return btnGestionarOrdenes;
	}
}